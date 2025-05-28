# Pinia Store 두가지 정의 방법

---

### 1. **Option Store (Option Syntax)**

```js
export const useSomethingStore = defineStore('something', {
  state: () => ({}),
  actions: {},
  getters: {},
})
```

- **state, actions, getters** 속성 필수
- 익숙한 Vuex 스타일

### 2. **Setup Store (Composition API/Setup Syntax)**

```js
export const useSomethingStore = defineStore('something', () => {
  // 여기에 ref, computed, function 등 모두 직접 선언!
  const counter = ref(0)
  const double = computed(() => counter.value * 2)
  function increment() { counter.value++ }

  return { counter, double, increment }
})
```

- **ref, computed, function**을 직접 선언/반환
- **return에 포함되는 모든 것이 state/함수/게터 역할**
- **`state`, `actions`, `getters`라는 키워드는 없음!**

## 즉, 

- **state 역할**: `ref` 등으로 선언한 변수 (예: `layerStateList`)
- **actions 역할**: 함수(예: `toggleLayer`)
- **getters 역할**: computed나 함수(예: `getLayerMetaMap`, `getLayerStates`)

**→ `return { ... }`로 내보내는 모든 값이 컴포넌트에서 "store의 속성"으로 접근됨**

---

## Option Store vs Setup Store 비교

### 1. **Option Store (옵션 문법)**

```js
export const useExampleStore = defineStore('example', {
  state: () => ({
    count: 0,
  }),
  actions: {
    increment() { this.count++ }
  },
  getters: {
    double: (state) => state.count * 2
  }
})
```

#### **장점**

- Vuex(2.x/3.x)와 매우 유사 → Vuex 쓰던 개발자에게 익숙함
- **타입 지원(자동 완성)**이 간단하게 잘 됨 (특히 Typescript)
- 단순하고 명확. 진입장벽 낮음
- Pinia 공식문서의 예제 대부분도 Option Store

#### **단점**

- Composition API의 강점을 100% 활용하기 어려움
- Pinia의 life-cycle, watch, computed 등 Vue 3 고유 기능 활용엔 제약

### 2. **Setup Store (Setup/Composition 문법)**

```js
export const useExampleStore = defineStore('example', () => {
  const count = ref(0)
  const double = computed(() => count.value * 2)
  function increment() { count.value++ }
  return { count, double, increment }
})
```

#### **장점**

- **Composition API 패턴과 완벽 호환** (ref, computed, watch 등 자유롭게 사용)
- **reactive, provide/inject, 라이프사이클(onMounted 등) 다 활용 가능**
- Typescript에서 더 "유연하고 강력한 타입 추론" 가능(복잡한 로직에 강함)
- 복잡한 store(모듈별 코드 분리, 복합 구조, 외부 hooks/유틸 연동 등)에 최적
- Pinia 최신 문서에서 권장(미래지향적)

#### **단점**

- 초보자 입장에선 구조가 익숙하지 않을 수 있음
- 단순한 상태+액션만 있을 때 오히려 verbose(코드가 길어질 수 있음)
- Option Store에 비해 "state/actions/getters 구분"이 약간 모호할 수 있음