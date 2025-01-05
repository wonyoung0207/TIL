# vue3 watchEffect 개념정리

---

>

## `watchEffect()`란?

1. `watchEffect()`는 **Vue 3 Composition API**에서 제공하는 반응형 감시 함수로, 내부에서 사용하는 **반응형 데이터가 변경될 때마다 자동으로 실행**됩니다.
2. 기존의 `watch()`와 달리, **의존성을 자동으로 추적**하여 감시할 변수를 명시적으로 지정할 필요가 없습니다.

## `watchEffect()` vs `watch()` 차이점

1. `watchEffect()`는 Vue 3의 **반응형 데이터를 자동으로 감지**하여 실행되므로, `watch()`보다 더 간결하고 사용하기 편리함.
2. 하지만 비동기 코드, 이벤트 리스너 같은 경우 **정리 함수(`onCleanup`)를 활용**해야 불필요한 실행을 방지할 수 있음.
3. `watch()`는 특정 변수를 감시할 때 적합하지만, `watchEffect()`는 여러 개의 반응형 데이터를 감시할 때 강력함.

| 비교 항목            | `watchEffect()`                                  | `watch()`                         |
| -------------------- | ------------------------------------------------ | --------------------------------- |
| **의존성 자동 추적** | O (자동 추적)                                    | X (명시적으로 지정 필요)          |
| **즉시 실행 여부**   | O (등록 즉시 실행)                               | X (데이터가 변경될 때만 실행)     |
| **콜백 매개변수**    | 없음                                             | (newValue, oldValue) 제공         |
| **사용 예**          | 복잡한 계산식, 여러 개의 반응형 변수를 감시할 때 | 특정 데이터 변경을 감지해야 할 때 |

```ts
import { ref, watch, watchEffect } from 'vue';

export default {
  setup() {
    const count = ref(0);

    // count가 변경될 때마다 실행 (이전 값과 비교 가능)
    watch(count, (newVal, oldVal) => {
      console.log(`watch: Count 변경됨: ${oldVal} → ${newVal}`);
    });

    // count가 변경될 때마다 실행 (자동 감지)
    watchEffect(() => {
      console.log(`watchEffect: Count가 ${count.value}로 변경됨`);
    });

    const increment = () => {
      count.value++;
    };

    return { count, increment };
  },
};
```

## `watchEffect()` 기본 사용법

1. `count.value`가 변경될 때마다 `watchEffect()`가 실행되어 **자동으로 반응형 상태를 추적**합니다.

```ts
import { ref, watchEffect } from 'vue';

export default {
  setup() {
    const count = ref(0);

    watchEffect(() => {
      console.log(`Count가 변경됨: ${count.value}`);
    });

    const increment = () => {
      count.value++;
    };

    return { count, increment };
  },
};
```

## **`computed()` vs `watchEffect()` 비교**

| 비교 항목              | `computed()`                            | `watchEffect()`                                       |
| ---------------------- | --------------------------------------- | ----------------------------------------------------- |
| **반응형 데이터 감지** | O (의존성 기반)                         | O (자동 추적)                                         |
| **즉시 실행 여부**     | X (처음에는 실행 안 됨)                 | O (등록 즉시 실행됨)                                  |
| **값 반환 여부**       | O (반응형 값 반환)                      | X (부수 효과 실행)                                    |
| **캐싱 지원**          | O (값이 변경되지 않으면 재계산 안 함)   | X (항상 실행)                                         |
| **사용 목적**          | 값을 계산하여 새로운 반응형 데이터 제공 | 데이터 변경 시 특정 동작 수행 (예: API 호출, 로깅 등) |

| 상황                                                       | `computed()` | `watchEffect()` |
| ---------------------------------------------------------- | ------------ | --------------- |
| **반응형 데이터를 기반으로 새로운 값을 계산**              | ✅            | ❌               |
| **데이터 변경 시 특정 동작 수행 (API 호출, DOM 업데이트)** | ❌            | ✅               |
| **즉시 실행 여부**                                         | ❌            | ✅               |
| **캐싱 지원 여부**                                         | ✅            | ❌               |
| **값을 반환하는가?**                                       | ✅            | ❌               |

```ts
import { ref, computed } from 'vue';

export default {
  setup() {
    const price = ref(100);
    const quantity = ref(2);

    // 1. computed()를 사용하여 합계 계산 (캐싱됨)
    const totalPrice = computed(() => price.value * quantity.value);
      
    // 2. watchEffect()를 사용하여 반응형 데이터 변경 감지
        watchEffect(() => {
        console.log(`Count 값 변경됨: ${count.value}`);
    });


    return { price, quantity, totalPrice };
  }
};
```

