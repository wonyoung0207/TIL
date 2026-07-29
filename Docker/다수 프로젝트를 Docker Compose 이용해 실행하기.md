# Docker Compose를 이용한 다중 프로젝트 구동 요약 가이드 (폐쇄망 환경)

본 문서는 **인터넷이 연결되지 않은 폐쇄망(Air-Gapped) 환경**에서 여러 독립 프로젝트(백엔드 API, 다중 프론트엔드 Web App, 데이터베이스, 공간정보 서버 등)를 **Docker Compose** 기반으로 유연하고 손쉽게 일괄 구동하기 위해 설계된 인프라 구축 패턴의 핵심 요약입니다.

---

## 1. 핵심 아키텍처 및 특징

```text
infra/
├── .env                           # 중앙 환경변수 및 외부 빌드 경로 주입
├── docker-compose.yml             # 컨테이너 의존성, 헬스체크, 볼륨 마운트 총괄
├── database/init/                 # 최초 구동 시 자동 실행될 SQL 덤프 및 권한 설정
└── Build/                         # 호스트 외부 주입용 빌드 파일 디렉터리
    ├── api/                  # Spring Boot 실행 JAR 파일
    ├── admin/                # Nginx 설정 및 어드민 Web dist
    └── control/              # 관제 Web dist
```

---

## 2. 핵심 구성 요소 상세 분석

### 1) `.env` (환경변수 중앙 관리)
* **목적**: 보안 비밀번호, 포트 바인딩, 그리고 **외부 주입 빌드 파일 경로**를 한곳에서 일괄 관리.
* **주요 구성**:
  * DB 포트 및 계정 정보 (`MYSQL_DATABASE`, `MYSQL_USER`, `MYSQL_PASSWORD`)
  * 호스트 디렉터리 빌드 결과물 경로 주입 (`BACKEND_JAR_PATH`, `FRONTEND_ADMIN_DIST_PATH` 등)
* **이점**: 환경별(개발/테스트/폐쇄망 운영) 설정 변경 시 `docker-compose.yml` 코드를 수정하지 않고 `.env` 파일 변경만으로 유연하게 대응.

### 2) `docker-compose.yml` (오케스트레이션 & 헬스체크)
* **외부 바인드 마운트 (Read-Only)**:
  * 컨테이너 이미지를 매번 새로 빌드하지 않고, 호스트의 `./Build/` 디렉터리에 위치한 실행 JAR, Web 정적 asset(`dist/`), `nginx.conf`를 `:ro`(Read-Only)로 직접 마운트하여 컨테이너 재빌드 없이 소스/설정 즉시 반영.
* **정밀 헬스체크 기반 의존성 제어 (`depends_on.condition: service_healthy`)**:
  * DB 대용량 덤프 복원이 완료되어 MariaDB 헬스체크(`mariadb-admin ping`)가 정상(Healthy) 판정될 때까지 백엔드(`api`) 구동을 대기시킴으로써 구동 시점의 DB 연결 에러 방지.
  * `mariadb` (Healthy) ➔ `backend` ➔ `frontend-admin` 순서의 안전한 순차 기동 보장.

### 3) `Build/admin/nginx.conf` (Nginx 프록시 & 웹 통합)
* **동적 DNS 리졸버 적용 (`resolver 127.0.0.11 valid=30s ipv6=off;`)**:
  * Nginx 기동 시 타깃 서비스(GeoServer, MapProxy, Backend API 등)가 아직 띄워지지 않았더라도 변수(`set $backend_target ...`)와 Docker 내장 DNS 리졸버를 활용하여 Nginx 구동 크래시(Crash) 방지.
* **복수 Web 서비스 단일 Nginx 통합 서빙**:
  * `/` 경로(**어드민**)와 `^~ /control` 경로(**관제 서브 서비스**)를 단일 Nginx 포트(80) 상에서 정규식 우회 및 별도 root 설정으로 병합 제공.
* **통합 프록시 셋업**:
  * `/api` (REST API 라우팅), `/ws` (웹소켓 통신), `/geoserver`, `/wmts` 등 이종 서비스 간 CORS 이슈 없이 단일 포트를 통해 투명 프록시 전달.

### 4) `database/init` (DB 초기 데이터 자동 구축)
* **`/docker-entrypoint-initdb.d` 메커니즘 활용**:
  * MariaDB 공식 컨테이너의 바인드 마운트 특성을 활용하여, 호스트 `./database/init` 내의 대용량 SQL 덤프(`01-dump-issk.sql`) 및 권한 파일(`03-grants.sql`)을 최초 컨테이너 생성을 할 때 자동으로 이관/실행.
  * 별도의 수동 DB 스키마 생성 절차 없이 `docker compose up -d` 한 줄로 데이터베이스 구축 완료.

---

## 3. 폐쇄망(Offline) 이관 및 구동 워크플로우

1. **외부 인터넷 PC (이미지 단일 패키징)**:
   
   ```bash
   docker pull eclipse-temurin:11-jre
   docker pull nginx:alpine
   docker pull mariadb:11.4
   docker pull kartoza/geoserver:2.25.0
   docker save -o required-images.tar eclipse-temurin:11-jre nginx:alpine mariadb:11.4 kartoza/geoserver:2.25.0
   ```
2. **폐쇄망 이관**:
   * `required-images.tar` 및 `infra` 저장소 폴더 전체를 망연계/USB로 폐쇄망 서버에 전달.
3. **폐쇄망 서버 구동**:
   
   ```bash
   # 도커 이미지 로드
   docker load -i required-images.tar
   
   # 전체 서비스 원클릭 백그라운드 구동
   cd infra
   docker compose up -d
   ```
   
   ```dockerfile
   # DB container 접속
   docker exec -it mariadb_server mariadb -u {userName} -p {pwd} {DB_name}
   
   # docker container  삭제 (named volume 까지 삭제해야함)
   docker compose down -v
   
   # docker 실행 
   docker compose up -d
   
   # docker restart 
   docker compose restart {container-name}
   ```
   

---

## 요약 및 핵심 이점

- **빌드와 인프라의 분리**: 컨테이너 이미지를 재생성할 필요 없이 호스트의 `./Build/` 정적 파일 및 JAR만 교체 후 컨테이너 재시작만으로 서비스 업그레이드 가능.
- **순차적 자동화 구동**: 헬스체크 기반의 의존성 관리로 DB 복원 완료 시점에 맞춰 서비스가 안정적으로 순차 기동.
- **폐쇄망 이관 최적화**: 4개의 Base Docker 이미지만 `.tar`로 가져가면 환경 설정 파일(.env), Nginx 설정, DB 덤프까지 한 번에 구동 가능한 표준 인프라 템플릿.