# EventQueue 이용 센서 감지

---

> 

## 목적

- 특정 범위 좌표 안에 Mesh 물체가 들어오면 감지 후 로봇팔을 이용해 물체를 옮기고 싶었음
  - 감지 후 EventBus 이용해 물체 옮기는 기능 호출 
- 즉, 특정 영역 감지 기능구현이 필요해 학습하게 됐다. 

## 이벤트 감지 

- `h1`, `h2`: collider handle (숫자 ID)
- `started: boolean`
  - `true`  → 이번 step에서 **새로 접촉이 시작됨**
  - `false` → 이번 step에서 **접촉이 끝남 (떨어짐)**

```js
function tick() {
  // 1. 입력 / 로직 업데이트
  // ...

  // 2. 물리 step (이때 eventQueue에 이벤트 쌓임)
  world.step(eventQueue);
  
// 나같은 경우 JS 버전이였기 때문에 drainCollisionEvents 를 사용했어야 함 
  eventQueue.drainCollisionEvents((h1, h2, started) => {
     // 이 프레임에서 새로 붙거나 떨어진 collider pair 전부 다 들어옴
     const c1 = world.getCollider(h1);
     const c2 = world.getCollider(h2);
     if (c1 !== sensorCollider && c2 !== sensorCollider) return;
  });
}
```

##### 중요!! 이벤트 감지 활성화 

- **이벤트는 “이벤트가 활성화된 collider들”에 대해서만 생성되고, `setActiveEvents` 안 한 애들은 아예 이벤트 안 생김.**

- 센서 collider만 이벤트 생성 켬

  ```js
  sensorCollider.setActiveEvents(RAPIER.ActiveEvents.COLLISION_EVENTS);
  ```

- 이 collider가 관련된 충돌에 대해서만→ eventQueue에 이벤트가 쌓이고→ drainCollisionEvents로 넘어옴

## 충돌 Event 비교 

| 항목                   | `drainCollisionEvents`                        | `drainIntersectionEvents`            |
| ---------------------- | --------------------------------------------- | ------------------------------------ |
| 이벤트 종류            | **충돌(contact) 시작/끝**                     | **교차(겹침) 시작/끝**               |
| 콜백 시그니처          | `(h1, h2, started: boolean)`                  | `(h1, h2, intersecting: boolean)`    |
| ActiveEvents 플래그    | `ActiveEvents.COLLISION_EVENTS`               | `ActiveEvents.INTERSECTION_EVENTS`   |
| sensor collider와 관계 | 센서는 contact 안 만들어서 보통 여기 안 씀    | **센서 전용/트리거용**으로 많이 씀   |
| 정보                   | “접촉이 생겼냐/끝났냐”                        | “겹치고 있냐/아니냐 (안에서/밖에서)” |
| 사용 예                | 데미지, 바닥 접촉, 완전한 물리 충돌 기반 로직 | 트리거 존, 영역 감지, proxim         |

##### 1. drainCollisionEvents -> JS 에도 있음 

- “충돌” 기준 → 물리적으로 닿아서 contact 해석이 일어나는 상황

> “누가 누구랑 부딪혔을 때 hp 깎기”
>
> “바닥에 떨어졌는지 체크”
>
> “총알이 벽/적에 맞았을 때” 등 **실제 충돌 기반 로직**

```js
eventQueue.drainCollisionEvents((h1, h2, started) => {
  // ...
});
```

##### 2. drainIntersectionEvents -> Rust 에만 있음 

- “부딪혔는지”보다 “**영역 안에 들어왔는지/벗어났는지**”에 초점
- contact force, penetration 깊이 같은 건 안 줌 → 단순 true/false 상태
- sensor → contact(충돌) 대신 intersection 이벤트만 발생

> “어떤 물체가 특정 범위 안에 들어왔는지” 체크
>
> 실제로 튕기거나 밀 필요는 없고, 상태만 알고 싶을 때

```js
eventQueue.drainCollisionEvents((h1, h2, started) => {
  // ...
});
```

