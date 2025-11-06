# @Scheduled → TriggerTask 변경 배경

---

>

## 학습개기 

- 초기에는 “스레드 점유 없이 일정 주기로 반복되는 간단한 작업”을 위해 `@Scheduled(fixedDelay)` 를 사용했으나,  **실행 시점마다 DB의 최신 값을 반영해 동적으로 주기를 변경할 필요가 생겨**  `TriggerTask` 기반의 동적 스케줄 구조로 변경함.

## `@Scheduled(fixedDelay)` 사용 배경

- **목적:**
   스케줄 작업 실행 후 일정 시간(`fixedDelay`)이 지난 뒤 다음 작업을 실행하기 위함.
   (`fixedDelay`는 *이전 실행이 완료된 시점*을 기준으로 다음 실행이 예약됨)
- **장점:**
  - 이전 작업이 완료될 때까지 기다린 뒤 실행되므로 **동시 실행(병렬 충돌) 없음**
  - 실행 대기 중에는 **스레드가 점유되지 않음** → CPU/메모리 효율적
  - 간단한 주기 작업이나 정적 반복 스케줄에 적합
- **단점:**
  - `@Scheduled` 값(`fixedDelay`, `cron`)은 **애플리케이션 시작 시점에 고정됨**
  - 실행 중 DB 값이나 외부 설정이 변경되어도 **스케줄 주기가 즉시 반영되지 않음**
- 요약:
  - 정적 주기에서는 적합하지만, **동적 주기(DB 기반)** 이 필요한 요구사항에는 한계가 있음.

##### `@Scheduled` 예제 

```java
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleScheduledTask {

    // 5초마다 반복 실행 (이전 실행이 끝난 후 5초 뒤 재실행)
    @Scheduled(fixedDelay = 5000)
    public void runTask() {
        System.out.println("[@Scheduled] 실행 시간: " + System.currentTimeMillis());
    }
}
```

## `TriggerTask` 로 변경한 이유

- **요구사항 변화:**
  - 새로운 스케줄 실행 시마다 **DB에서 최신 설정 값을 읽어** 매번 다른 `delay`(또는 interval)로 실행 주기를 조정해야 함.
- **TriggerTask 특징:**
  - `TriggerTask` 는 **매 실행 시점마다 다음 실행 시각을 동적으로 계산** 가능
  - DB 조회 결과나 로직에 따라 다음 실행 간격을 자유롭게 변경 가능
  - `TaskScheduler` 기반이므로, `fixedDelay` 와 동일하게 실행 중에만 스레드 점유.
    - 다음 예약 시에는 스레드 점유하지 않음
- **장점:**
  - **동적 주기 반영 가능:** 
    - 매번 실행 전 DB 값 재조회
  - **자원 효율 동일:** 
    - 실행 완료 후 스레드 반환 (`@Scheduled(fixedDelay)` 와 동일한 비동기 동작)
  - 정교한 제어: 
    - 특정 조건(예: 상태, 시간대)에 따라 실행 스킵 또는 지연 설정 가능
- **단점:**
  - 직접 `SchedulingConfigurer` 코드로 등록해야 함
  - 설정이 다소 복잡 (`@Scheduled` 보다 구현량 많음)
- 요약:
  - DB 값 기반의 **실시간 주기 변경**이 필요하므로 고정값 기반인 `@Scheduled` 대신 **TriggerTask + SchedulingConfigurer** 구조로 전환.

##### `TriggerTask` 예제 

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.Instant;
import java.util.Date;

@Configuration
@EnableScheduling
public class DynamicTriggerTask implements SchedulingConfigurer {

    // 동적으로 바뀌는 delay (예: DB 조회 결과라고 가정)
    private long dynamicDelay = 3000; // 3초

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.addTriggerTask(
            // 실행할 작업 (Runnable)
            () -> System.out.println("[TriggerTask] 실행 시간: " + System.currentTimeMillis()),

            // 다음 실행 시각 계산 (Trigger)
            triggerContext -> {
                // 실제로는 여기서 DB 조회해서 delay 갱신 가능
                dynamicDelay = getDelayFromDB();  
                Instant nextExecution = Instant.now().plusMillis(dynamicDelay);
                return Date.from(nextExecution);
            }
        );
    }

    // DB에서 delay 값 읽어온다고 가정
    private long getDelayFromDB() {
        // 예: DB값이 매 실행마다 3초 → 5초 → 2초 이런 식으로 바뀔 수 있음
        return (long) (Math.random() * 5000) + 1000; // 1~6초 사이 랜덤
    }
}
```

## 결론

| 항목              | @Scheduled(fixedDelay) | TriggerTask                 |
| ----------------- | ---------------------- | --------------------------- |
| 스케줄 주기       | 정적 (앱 시작 시 고정) | 동적 (매 실행 시 계산 가능) |
| DB 기반 주기 변경 | 불가능                 | 가능                        |
| 스레드 점유       | 실행 중에만 점유       | 실행 중에만 점유            |
| 구현 난이도       | 쉬움                   | 중간 (직접 등록 필요)       |
| 활용 예           | 단순 반복, 정기 배치   | 설정값 기반 동적 스케줄     |

