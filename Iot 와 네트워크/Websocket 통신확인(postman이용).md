## 핵심 정리

| 구분         | WebSocket                           | REST (HTTP)               |
| ------------ | ----------------------------------- | ------------------------- |
| 연결 방식    | **지속적인 연결**                   | **요청-응답 단발성 연결** |
| 통신 방법    | 프레임(message) 단위로 주고받음     | HTTP 요청(GET, POST 등)   |
| Postman 지원 | WebSocket 탭에서 메시지 송수신 가능 | 일반 요청 탭 사용         |
| 예시         | `"hello"` 프레임 보냄               | `POST /api/data`          |



## Postman 최신 버전은 WebSocket 탭 제공

1. Postman 실행 → 상단 탭에서 **"New" → WebSocket Request** 클릭

2. URL 입력:

   ```url
   ws://localhost:8080/ws
   ```

3. 메시지 입력:

   ```json
   {
     "type": "FETCH_DATA",
     "region": "BUSAN"
   }
   ```

4. "Send" 클릭 → 메시지 전송

5. 서버가 응답을 보내면 아래 패널에 실시간으로 응답이 표시됨