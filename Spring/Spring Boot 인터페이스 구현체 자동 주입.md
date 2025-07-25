# Spring Boot 인터페이스 구현체 자동 주입

---

>

## 문제상황 

- Kafka 를 하던 도중 topic List 를 저장해놓는 List 변수가 있었는데, 해당 클레스 어디서도 세팅하는게 보이지 않았다. 
- 그래서 알아보니, 인터페이스로 구현해놓고 implement로 받은 `@component` 객체들이 자동으로 List 변수로 만들어져 `@Bean` 으로 등록된다는 것을 발견했다. 
- 이 방식은 Kafka 소비자, 이벤트 처리, 확장 가능한 다형적 구조 설계 시 유용함.

## 개요

- Spring에서 인터페이스 구현체를 List 형태로 자동 주입받는 방식 정리
- Spring에서는 특정 인터페이스를 구현한 클래스들이 Bean으로 등록되어 있을 경우,  다음과 같이 해당 인터페이스 타입의 `List` 필드에 **자동으로 모든 구현체 인스턴스들을 주입**받을 수 있다.
- 따라서 해당 내용을 정리하고자 한다. 

```java
@Autowired
private List<InfraMessageListener> infraMessageListenerList;
```

## 처리 순서

1. `@Component`가 붙은 클래스들을 스캔하여 Bean으로 등록
2. `InfraMessageListener`를 구현한 Bean을 모두 수집
3. `List<InfraMessageListener>` 필드가 있으면 해당 리스트로 자동 주입

## 인터페이스 및 구현체 구조 예시

##### 인터페이스 정의

```java
public interface InfraMessageListener {
    String getTopicName();
    void onMessage(Map<String, Object> message);
}
```

##### 구현체 클래스들

```java
@Component
public class VdsEventListener implements InfraMessageListener {
    @Override
    public String getTopicName() { return "t1"; }

    @Override
    public void onMessage(Map<String, Object> message) {
        // 처리 로직
    }
}

@Component
public class LidarEventListener implements InfraMessageListener {
    @Override
    public String getTopicName() { return "t2"; }

    @Override
    public void onMessage(Map<String, Object> message) {
        // 처리 로직
    }
}
```

- 이와 같이 `@Component`를 붙이면 Spring이 자동으로 Bean으로 등록한다.

##### 주입 받는 쪽

```java
@Component
public class KafkaEventListener implements MessageListener<String, Object>, InitializingBean {

    @Autowired
    private List<InfraMessageListener> infraMessageListenerList;

    // 이후 이 리스트를 순회하며 각 topic에 대해 onMessage 처리 가능
    private String topicSelect(){
        for (InfraMessageListener listener : infraMessageListenerList) {
            if (listener.getTopicName().equals(topic)) {
                listener.onMessage(messageMap);
            }
        }
    }
}
```



