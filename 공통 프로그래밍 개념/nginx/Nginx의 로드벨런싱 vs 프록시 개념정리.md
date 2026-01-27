# Nginx의 로드벨런싱 vs 프록시 개념정리

---

>

## 요약

> **프록시 = 대신 전달해주는 역할**
>  **로드밸런싱 = 여러 서버 중 하나를 골라주는 전략**

**nginx는 “프록시도 하고, 로드밸런서도 한다”**

------

## 프록시 (Proxy)란?

##### 개념

- 클라이언트가 직접 서버에 접근하지 않고,**nginx가 중간에서 요청을 받아 대신 서버에 전달**

```js
Client → Nginx → Backend Server
```

##### nginx에서의 프록시 기능

```js
location /api/ {
    proxy_pass http://backend;
}
```

##### 프록시가 하는 일

- 요청 전달 (HTTP / WebSocket)
- 헤더 수정
- SSL 종료 (HTTPS → HTTP)
- 경로 변경 (`/api` → `/`)
- 보안 (서버 직접 노출 방지)

------

## 로드밸런싱 (Load Balancing)이란?

##### 개념

- 프록시 역할을 하면서**요청을 여러 서버로 분산**

```js
Client → Nginx → Server A
                  Server B
                  Server C
```

##### nginx에서의 로드밸런싱

```js
upstream backend {
    server 10.0.0.1:8080;
    server 10.0.0.2:8080;
    server 10.0.0.3:8080;
}
location /api/ {
    proxy_pass http://backend;
}
```

##### nginx 로드밸런싱 방식

- round-robin (기본)
- least_conn
- ip_hash
- weight

##### 핵심

- **로드밸런싱은 “프록시의 확장 개념”**

------

## 프록시 vs 로드밸런서 (nginx 기준)

| 구분          | 프록시     | 로드밸런서   |
| ------------- | ---------- | ------------ |
| 목적          | 대신 전달  | 분산 처리    |
| 서버 수       | 1대도 가능 | 보통 여러 대 |
| nginx 역할    | 중계자     | 분배자       |
| upstream 필요 | x          | o            |
| 성능/확장     | 제한적     | 확장 가능    |

##### nginx는 프록시야? 로드밸런서야?

- **둘 다** 가능 
  - `proxy_pass` 쓰면 → 프록시
  - `upstream` + `proxy_pass` 쓰면 → 로드밸런서

##### 로드밸런싱 없이 프록시만 쓴다”는 말?

- **서버 1대짜리 프록시 구조** (나의 경우엔 이렇게만 사용했음)
- nginx 이용 백엔드 프록시 구조를 사용했었음 

```
Client → Nginx → Spring Server
```

------

## 예시

##### 프록시만 사용하는 경우

- SPA + API 서버
- SSL termination
- 포트 숨기기

```js
location /api/ {
    proxy_pass http://localhost:34011;
}
```

##### 로드밸런싱 사용하는 경우

- API 서버 다중 인스턴스
- 트래픽 많음
- 장애 대비

```js
upstream api {
    least_conn;
    server 10.0.0.1:34011;
    server 10.0.0.2:34011;
}

location /api/ {
    proxy_pass http://api;
}
```