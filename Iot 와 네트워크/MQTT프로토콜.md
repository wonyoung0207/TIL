# MQTT 개념정리

---

> MQTT 프로토콜에 대해 정리한다. 

## MQTT ( Message Queue Telemetry Transport )

### 정의

- 신뢰성이 낮은 네트워크를 위해 고안된 **비동기식 경량형 프로토콜**이다. 
- **Client - Broker 구조**로 **Publish / Subscribe 모델**로 통신한다. 
  - Iot 프로토콜 중 하나로 경량 어플리케이션 프로토콜이다. 
  - broker가 둘 사이에 있어 둘중 하나가 데이터를 저장하고 다른 하나가 저장된 데이터를 가져가는 방식으로 진행된다. 

### CoAP 프로토콜과 차이점

- CoAP은 1:1로 통신하는 프로토콜이고, MQTT는 다중으로 통신하는 프로토콜이다. 
- MQTT는 Client가 데이터 요청과 생성 둘다 할 수 있지만, CoAP의 Client는 데이터 요청밖에 할 수 없다. 

### 사용처

- Iot 환경과 같이 다수의 저사양 센서 및 액추에이터들이 생산한 원격 측정 데이터의 전송에 적합하다. 

### 통신구조

- **Publish / Subscript 모델**로 , 발행자는 자신의 **데이터**에 해당하는 **토픽(데이터)을 발행하여 브로커에게 전달**하고, **브로커는 해당 토픽의 구독을 원하는 구독자에게 전달.** 
  - Client는 pub도 될 수 있고, sub 도 될 수 있다.
- Broker를 중간에 두고 Client가 통신하는 형태를 가진다. 
  - Pub - Broker - Sub 구조를 가진다. 
- Broker는 **Topic(메시지 큐 또는 데이터 라고 함)** 를 가진다. 
  - Broker는 메모리에 Topic별로 구독하는 Subscribe를 가진다. 

### 진행 순서

1. Subscribe(구독자) 가 Broker에게 Publish(발행자)를 구독한다고 알린다.
2. Broker는 구독자 정보를 가지고 있게된다. 
3. Publish가 Broker에게 데이터를 보낸다. 
4. Broker는 데이터를 받아 해당 Publish를 구독하고 있는 Subscribe로 Topic을 발행한다. 

### Session 과 Subscription

1. Session
   - **Broker와 Client**의 **일시적 연결**을 의미 
   - Client와 Broker의 **모든 통신은 Session 을 통해 이루어짐** 
2. Subscription
   - Session과는 조금 다른형태를 가진다. 
   - **Topic과 Client**의 **가상연결을 의미**한다. 
   - 종류
     1. Transient
        - 세션 생성되어 있을때만 메시지 전송 가능 
     2. Durable
        - 세션 끊어져도 Broker에 메시지 저장해 놨다가 Session 생성되면 메시지 전송 

### Wildcard

- Topic의 형태를 나타내기 위한 것으로, 여러 Topic을 등록할 경우 사용한다. 
  - Topic의 형태는 트리구조로 표시한다.
- 종류
  1. Single Level : chatboot / roomA / + / profile
     - \+ 는 해당 레벨에 한하여 Topic을 확장할 수 있음을 의미한다. 
  2. Multi Level : chatboot / roomA / #
     - \# 은 topic의 마지막에만 사용가능하며 해당 레벨의 하위Topic을 모두 확장하는 것을 의미한다. 