# Cannon.js Body 힘 관련 메서드 정리

---

>

## 정리

- **Force** = “밀고 있는 상태”
- **Impulse** = “탁! 치고 가는 상태”
- **World** = “세상의 좌표계 기준”
- **Local** = “Body 자신 기준”

## 1. **applyForce(force, worldPoint)**

- **세계 좌표(World space)** 기준으로 힘을 가함.
- 단위: 뉴턴(N) → `F = m * a`
- 프레임마다 반복 적용해야 지속적인 가속이 일어남.

```js
body.applyForce(
  new CANNON.Vec3(0, 100, 0),    // 위쪽으로 힘
  body.position                  // 힘이 적용되는 지점 (world 좌표)
)
```

👉 바람, 중력처럼 **지속적으로 작용하는 힘**에 적합.

------

## 2. **applyImpulse(impulse, worldPoint)**

- **세계 좌표(World space)** 기준으로 **즉시 속도 변화**를 줌.
- 단위: 충격량 (Impulse = Force × Δt).
- 한 번 호출하면 바로 속도 변화 → 즉시 튀어나가는 효과.

```js
body.applyImpulse(
  new CANNON.Vec3(0, 10, 0),   // 위로 충격량
  body.position
)
```

👉 총알 맞았을 때, 공 튀기기, 순간적인 충돌 등에 적합.

------

## 3. **applyLocalForce(force, localPoint)**

- **로컬 좌표(Local space)** 기준으로 힘을 가함.
- 로컬 좌표는 Body 자신의 좌표계(회전 포함).
- 예: `new CANNON.Vec3(0, 0, 1)` → 항상 Body 앞쪽 방향.

```js
body.applyLocalForce(
  new CANNON.Vec3(0, 0, 50),  // Body 앞쪽으로 힘
  new CANNON.Vec3(0, 0, 0)    // Body 로컬 중심점
)
```

👉 자동차, 비행기 같은 오브젝트가 **자기 기준으로 앞으로 나아가야 할 때** 유용.

------

## 4. **applyLocalImpulse(impulse, localPoint)**

- **로컬 좌표(Local space)** 기준으로 즉시 속도 변화를 줌.
- applyImpulse와 동일하지만, 좌표계가 Body 기준.

```js
body.applyLocalImpulse(
  new CANNON.Vec3(0, 5, 0),   // Body 기준 위쪽
  new CANNON.Vec3(0, 0, 0)    // Body 중심점
)
```

👉 로봇 점프, 로컬 방향으로 순간적인 발사 같은 효과에 사용

##  차이 요약

| 메서드              | World vs Local | Force vs Impulse | 특징                                |
| ------------------- | -------------- | ---------------- | ----------------------------------- |
| `applyForce`        | World          | Force            | 지속적인 힘, 가속 (프레임마다 적용) |
| `applyImpulse`      | World          | Impulse          | 순간 속도 변화 (충돌, 점프)         |
| `applyLocalForce`   | Local          | Force            | Body 방향 기준 지속적인 힘          |
| `applyLocalImpulse` | Local          | Impulse          | Body 방향 기준 순간 속도 변화       |

