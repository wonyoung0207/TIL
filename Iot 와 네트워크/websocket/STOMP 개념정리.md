# STOMP 개념 정리

---

>

## STOMP란?

- **STOMP (Simple Text Oriented Messaging Protocol)** 는 **WebSocket 위에서 동작하는 메시징 프로토콜**이다.

> WebSocket은 “연결 방식”
>
> STOMP는 “메시지 규칙(프로토콜)”

- 즉, **STOMP는 WebSocket을 메시지 브로커처럼 사용하기 위한 규칙**

------

## STOMP 사용이유 

##### WebSocket 특징

- 양방향 연결
- send / receive 만 제공
- **메시지 의미, 목적지, 구독 개념 없음**

##### STOMP 특징 

- 구독
- 브로드캐스트
- 특정 대상에게 전달
- 메시지 라우팅

STOMP는 이걸 **표준화된 방식으로 해결**함.

------

## STOMP 동작 구조

```json
// 1 연결
Browser ── ws://localhost:8080/ws-event ──▶ registerStompEndpoints

// 2 메시지 송신
Browser ── /app/chat.send ──▶ Controller (@MessageMapping)

// 3 메시지 수신
Controller / Kafka ── /topic/public ──▶ STOMP Broker ──▶ Browser
```

- WebSocket 위에 **메시지 계층을 하나 더 얹은 구조**

---

## STOMP 이용 방법 (Spring + Frontend 기준)

##### 1. Frontend (STOMP Client)

```js
import { Client } from '@stomp/stompjs';

const client = new Client({
  brokerURL: 'ws://localhost:8080/ws',
  reconnectDelay: 5000,
});

client.onConnect = () => {
  client.subscribe('/topic/public', message => {
    console.log(JSON.parse(message.body));
  });

  client.publish({
    destination: '/app/chat.send',
    body: JSON.stringify({ msg: 'hello' })
  });
};

client.activate();
```

##### 2. Backend (Spring WebSocket)

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // WebSocket 연결 생성 (클라이언트가 WebSocket으로 접속할 URL을 정의) -> 오직 연결만
    // ws://localhost:8080/ws-event 
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-event").setAllowedOriginPatterns("*").withSockJS();
    }

    // 메시지를 어디로 보내고 받을지 (destination 규칙)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // /topic 으로 시작하는 destination은 서버가 클라이언트에게 직접 보내는 메시지다
        config.setApplicationDestinationPrefixes("/app"); // app 으로 시작하는 메시지는 서버 컨트롤러로 전달해라
    }
}
```

##### kafka 메시지를 ws 의 broker 로 보냄 

```java
/topic/public
/topic/chat
/topic/private.user1

    
Client → Server  : /app/**
Server → Client  : /topic/**
```

##### 3. 메시지 수신 처리

1. `/topic/public` 조회
2. 등록된 sessionId 목록 확인
3. 각 session의 WebSocket으로 전송

```java
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    // kafka 수신 메시지 ( kafka 로 부터 메시지 받아옴 )
    @KafkaListener(topics = "kafka-chat", groupId = "${spring.kafka.consumer.group-id:group-chat}")
    public void consume(ChatMessage message) {
        log.info("Consuming Message: {}", message);
        if (message.getRecipient() != null && !message.getRecipient().isEmpty()) {
            // Private message
            messagingTemplate.convertAndSend("/topic/private." + message.getRecipient(), message);
            // Also send to sender so they see their own private message in the chat log (optional, but good for UI)
            messagingTemplate.convertAndSend("/topic/private." + message.getSender(), message);
        } else {
            // Public message
            // kafka에 메시지 왔어 → 이 이벤트를 보고 싶은 모든 웹 클라이언트에게 알려줘
            // WebSocket을 직접 쓰는 코드”가 아니라 이미 연결된 WebSocket 파이프라인에 메시지를 흘려보내는 코드
            messagingTemplate.convertAndSend("/topic/public", message);
        }
    }
}
```

------

## STOMP vs WebSocket  비교

##### 기능 비교 표

| 항목          | WebSocket 단독 | STOMP           |
| ------------- | -------------- | --------------- |
| 연결          | O              | O               |
| 목적지 개념   | x              | o               |
| pub/sub       | x              | o               |
| 브로드캐스트  | 직접 구현      | 자동            |
| 메시지 라우팅 | 직접           | Broker 처리     |
| 재연결        | 직접           | 라이브러리 지원 |
| 확장성        | 낮음           | 높음            |
| 코드 복잡도   | 높아짐         | 낮음            |

------

## Kafka + STOMP 조합의 강점

```
Kafka → Consumer → STOMP → Browser
```

- Kafka: 메시지 신뢰성 / 저장
- STOMP: 실시간 전달 / 구독 관리
- Frontend: 단순 구독만 하면 됨

**역할이 완벽히 분리됨**

------

## STOMP의 한계

- WebSocket 위에 계층이 하나 더 있음 (오버헤드)
- 대규모 트래픽에는 외부 Broker(RabbitMQ 등) 필요
- 초저지연 시스템에는 부적합

------

## 최종 요약

> **WebSocket은 연결**
>  **STOMP는 메시지 규칙**
>  **STOMP는 WebSocket을 “메시징 시스템”으로 만든다**