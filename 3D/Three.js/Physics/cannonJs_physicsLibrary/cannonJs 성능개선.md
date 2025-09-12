#  Cannon.js 성능 최적화 기능 정리

---

>[cannonJs docs](https://schteppe.github.io/cannon.js/docs/classes/Box.html)

## 정리

- **Broadphase** = "충돌 후보 줄이기"
  - `Naive` = 모든 쌍 검사 (느림, 기본)
  - `Grid` = 격자 기반 검사
  - `SAP` = 축 정렬 기반 검사 (빠름, 추천)
- **Sleep** = "안 움직이는 Body는 검사 제외"
  - `world.allowSleep = true`
  - 필요할 때만 다시 깨움 → 성능 최적화

## 1. 브로드페이즈 (Broadphase)

- **개념**: 모든 Body 쌍을 다 비교하면 `O(n^2)`라서 느림 → "충돌할 가능성이 있는 후보"만 추려내는 단계.
- **종류**:
  1. **NaiveBroadphase** (기본값)
     - 모든 Body를 서로 다 비교 (가장 단순, 가장 느림).
  2. **GridBroadphase**
     - 공간을 격자로 나누고, 같은 칸/이웃 칸에 있는 Body만 충돌 검사.
  3. **SAPBroadphase** (Sweep and Prune)
     - Body를 축(보통 x축) 기준으로 정렬 후, 겹칠 가능성이 있는 것만 검사.
     - 일반적으로 가장 빠르고 효율적.
     - 단, Body가 매우 빠르게 움직이면 충돌 누락 버그가 드물게 발생할 수 있음.
- **설정**:

```js
world.broadphase = new CANNON.SAPBroadphase(world)
```

## 2. 수면 (Sleeping)

- **개념**: 거의 안 움직이는 Body를 "잠재움(sleep)" 상태로 만들어, 충돌 검사에서 제외.
- **장점**: 쓸데없이 고정된 물체까지 계속 검사하지 않으므로 성능 향상.
- **설정**:

```js
world.allowSleep = true
```

- **관련 속성**:
  - `sleepSpeedLimit`: 이 값 이하의 속도가 되면 Body를 잠재울 수 있음.
  - `sleepTimeLimit`: 일정 시간 이상 거의 안 움직이면 실제로 Sleep 상태 전환.
- 즉, Body가 멈춰있으면 연산에서 빼버리고, 다시 충돌이 오거나 힘이 가해지면 깨어남.