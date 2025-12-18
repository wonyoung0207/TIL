# Scheduler + Executor(ThreadPool) 연관 문제 정리

## 1. 문제 상황 요약

- `ThreadPoolTaskScheduler`로 주기 작업 실행
- 스케줄 작업 내부에서 `@Async` 메소드 호출
- 스케줄을 `cancel(false)` 했는데 내부 진행하던 작업(파일 zip 만들기)이 멈춰버림 

---

## 2. 핵심 원인 (가장 중요)

> **Scheduler와 Executor는 완전히 다른 스레드 풀이다.**

```
ThreadPoolTaskScheduler (예약/주기)
 └─ runTask()
     └─ @Async
         └─ ThreadPoolTaskExecutor (비동기 실행)
```

- Scheduler 중단 ≠ Async 중단
- 서로 생명주기, 관리 주체, 취소 방식이 다름

---

## 3. 각 컴포넌트 역할 정리

### ThreadPoolTaskScheduler

- 역할: **언제 실행할지 결정**
- 내부: `ScheduledExecutorService`
- 관리 대상: `ScheduledFuture`
- cancel(false):
  - 다음 스케줄 실행 안함 
  - 현재 실행 중 작업 진행 
- Async 작업 제어 불가

---

### ThreadPoolTaskExecutor (@Async)

- 역할: **즉시 비동기 실행**
- 내부: `ExecutorService`
- 호출 즉시 큐에 등록
- Scheduler 중단 여부와 무관하게 실행
- Scheduler에서 cancel 불가

---

## 4. 왜 문제가 되는가?

### 오해 포인트

- “스케줄을 취소했는데 작업이 왜 계속 도나?”
- → 실제로는 **Async Executor가 정상 동작 중**

### 실무 위험

- 서버 종료 시 파일 작업 중단
- 동일 requestId 재실행 시 충돌
- ZIP/IO 작업 중복 실행

---

## 5. cancel(false)의 실제 의미

```java
scheduledFuture.cancel(false);
```

- Scheduler 기준:
  - 다음 실행 예약 제거
- Async 기준:
  - 아무 영향 없음
- 이미 던져진 비동기 작업은 끝까지 실행됨

---

## 6. 스레드 문제 진단 포인트

### 로그로 구분

```java
Thread.currentThread().getName()
```

- scheduler-* → 스케줄러 스레드
- async-* / task-* → executor 스레드

### Executor 상태 확인

```java
executor.getActiveCount();
executor.getQueueSize();
```

---

## 결론

> Scheduler와 Executor의 역할 분리를 이해하지 못해서 생기는 설계 

- Scheduler = 예약 관리
- Executor = 실행 관리
- 둘은 자동으로 연결되지 않는다