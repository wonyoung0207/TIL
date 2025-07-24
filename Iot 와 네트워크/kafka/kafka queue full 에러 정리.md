# kafka queue full 에러 정리

---

>

## 문제상황 

- 백엔드에서 kafka 에 연결해놓은 queue 에서 try-catch 로 queue full 이 발생 
  - queue size와 용량 확인해봤을 때 queue 의 저장공간이 많이 남아있는것을 확인 
- 즉, kafka 에서 queue full 에러가 발생했지만 내부 다른 곳에서 queue full 이 발생하고 있었음 

## 원인 

- Kafka에서 소비한 메시지를 WebSocket을 통해 브라우저로 전송하는 과정에서 `queue full` 오류가 지속적으로 발생함.
- 해당 문제는 단순히 Kafka consumer의 큐가 가득 찬 것이 아닌, **WebSocket 비동기 전송 큐의 적제 현상**에서 발생중이였음
- 문제 에러 로그 

```
[pool-2-thread-*] ERROR c.m.r.w.listener.KafkaEventListener - Queue full
doWebSockerProc : Queue full
```

## 시스템 구조 요약

### 1. Kafka → Listener

- Kafka 메시지를 수신하면 `KafkaEventListener.onMessage()`가 호출되어 내부 `dataQueue`에 적재함.

### 2. Listener → WebSocket

- `dataQueue`에 들어온 메시지를 `take()`로 꺼내어 `doMessage()`에서 처리하며, 이후 `webSocketHandler.sendMessage()`로 전달됨.

### 3. WebSocket → 클라이언트

- WebSocket 전송은 각 세션별 `ClientSessionHandler`가 관리하는 `msgQueue`를 통해 처리되며, 해당 큐가 가득 차면 `queue full` 에러 발생.

## 원인 분석 요약

| 원인 항목                       | 설명                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| `LinkedBlockingQueue` 크기 초과 | Kafka listener 내부의 `dataQueue` 또는 `ClientSessionHandler.msgQueue`가 한계를 초과함 |
| WebSocket 전송 지연             | WebSocket 전송이 빠르게 소비되지 못하고 메시지가 누적됨      |
| 클라이언트 부재 or 응답 지연    | 연결된 WebSocket 세션이 없거나 브라우저가 느려 전송 큐가 쌓임 |
| 비동기 큐에 메시지 강제 추가    | `msgQueue.add()` 사용 시, full 상태면 예외 발생              |

```java
// kafka 용량 확인방법 
int remaining = dataQueue.remainingCapacity();
log.info("topicName : {}, 남은 공간: {}" , topicName, remaining);
```

## 개선방법

1. ###  WebSocket 전송 큐의 overflow 대응

   ```java
   java복사편집if (!msgQueue.offer(msg)) {
       msgQueue.clear(); // 또는 poll()로 오래된 것 제거
       msgQueue.offer(msg); // 마지막 메시지만 보냄
   }
   ```

2. ws queue 크기 증설

   ```java
   // kafka 내부의 websocketHandler queue 크기 증설 
   private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(1024 * 1024);
   ```

## 결론

- Kafka와 WebSocket 간의 연결 구조에서 메시지 병목은 Kafka 수신보다 WebSocket 소비 속도에 의해 발생할 수 있다. 
- 따라서 WebSocket 메시지 전송 처리에 대한 흐름 제어, 큐 overflow 대응 전략, 그리고 모니터링 로그를 체계적으로 갖추는것이 중요하다.

