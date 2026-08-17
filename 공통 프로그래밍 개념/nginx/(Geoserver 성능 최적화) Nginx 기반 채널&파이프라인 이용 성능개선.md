# Nginx worker_connections & keepalive_requests 성능 최적화

---

>

## 1. 개요 (Overview)

- 지도의 타일맵 및 GeoServer WMS 레이어를 대량으로 호출할 때 발생하는 Nginx 엔진 차원의 동시 소켓 병목과 커넥션 재연결 오버헤드를 해결하기 위해 **`worker_connections`** 및 **`keepalive_requests`** 2가지 핵심 설정을 최적화 



## 2. 핵심 최적화 내용 

##### 1.  `worker_connections` (Nginx 동시 소켓 수용량 확대)

* **개념**: Nginx 워커 프로세스 1개가 동시에 다룰 수 있는 최대 네트워크 소켓(커넥션) 수
* **AS-IS**: 기본값 **`512`** (타일 요청 폭주 시 웹서버 차원의 소켓 한도에 도달할 위험 존재)
* **TO-BE**: **`worker_connections 2048;`** (4배 확장) + `multi_accept on;` + `use epoll;`
* **개선 효과**:
  * Nginx 웹서버가 동시에 수용할 수 있는 동시 접속 및 타일 수신 소켓 용량이 **4배로 확대**된다. 
  * 순간적인 대량 요청 시 소켓 고갈로 인한 요청 거부 및 네트워크 체류 현상을 방지합니다.

##### 2. `keepalive_requests` (파이프라인 당 수용 요청 한도 확대)

* **개념**: 한 번 연결된 하나의 TCP 연결 통로(Keepalive Connection)에서 통로를 닫지 않고 연속으로 처리할 수 있는 최대 요청 수
* **AS-IS**: 기본값 **`100`** (타일 100개를 받아오면 통로가 강제로 닫히고 다시 TCP 연결을 맺음)
* **TO-BE**: **`keepalive_requests 10000;`** (100배 확장)
* **개선 효과**:
  * 지도를 지속해서 이동하거나 축척(Zoom)을 조작해도 통로가 끊기지 않고 10,000번까지 상시 유지
  * 100번째 타일마다 발생하던 주기적 재연결 지연(TCP Handshake 오버헤드) 및 멈칫거림 현상을 **완벽히 제거**한다. 



## 3. 적용 코드 요약 

### `nginx_main.conf` (Nginx 메인 설정)

```nginx
events {
    worker_connections 2048; # 동시 채널 소켓 용량 4배 확장
    multi_accept        on;   # 동시 접속 즉시 수락
    use                 epoll; # 리눅스 고성능 epoll 이벤트 모델 사용
}
```

### `nginx.conf` (서버 / location 프록시 설정)

```nginx
location /geoserver {
    proxy_pass http://geoserver_backend;
    proxy_http_version 1.1;
    proxy_set_header Connection "";

    keepalive_requests 10000; # 한 통로당 처리 한도 100배 확장 (기본 100 -> 10,000)
}
```



## 4. 요약 및 성능 비교 

| 설정 항목                | 기존 (AS-IS)   | 개선 후 (TO-BE)           | 핵심 개선 결과                              |
| :----------------------- | :------------- | :------------------------ | :------------------------------------------ |
| **`worker_connections`** | 512개 (기본값) | **2048개 (4배 확대)**     | Nginx 동시 처리 소켓 한도 4배 확장          |
| **`keepalive_requests`** | 100개 (기본값) | **10,000개 (100배 확대)** | 주기적 재연결 끊김 방지 및 통로 지속 재사용 |

