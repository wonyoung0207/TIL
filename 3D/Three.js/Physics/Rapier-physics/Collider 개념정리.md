# Collider 개념정리

---

>

## Collider 란? 

- **collider**는 이름 그대로 “**충돌을 계산하기 위한 물리적 형상**”이고, ‘눈에 보이는 모델’이 아니라 ‘**물리 엔진이 사용하는 보이지 않는 껍데기**’라고 이해하면 된다. 
  - 렌더링용 아님
  - 물리 계산 전용
  - 충돌 판정의 기준

---

## 사용 이유 

-  3D 모델끼리 부딪히면 안 되는 이유:
  - 3D 모델(메쉬)은 **너무 복잡함**
  - 삼각형 수가 수천~수만 개
  - 매 프레임 삼각형 vs 삼각형 충돌 계산 → **성능 폭발**
- 그래서 **단순화된 도형**인 Collider 를 3D Mesh 에 붙여 사용해야함 

------

## 핵심 역할

##### 1. 충돌 판정 (Collision Detection)

- 두 객체가 닿았는지
- 겹쳤는지
- 얼마나 파고들었는지

##### 2. 접촉 정보 계산 (Contact Manifold)

- 충돌 지점
- 충돌 법선(normal)
- 침투 깊이(penetration depth)

##### 3.  물리 반응 계산의 입력값

- 튕김 (반발력)
- 미끄러짐 (마찰)
- 멈춤 / 밀림

##### 즉, 

- RigidBody는 “**어떻게 움직일지**”
- Collider는 “**어디서 부딪히는지**”

------

## collider vs mesh 

| 구분      | Mesh          | Collider      |
| --------- | ------------- | ------------- |
| 목적      | 화면에 보이기 | **물리 계산** |
| 복잡도    | 매우 높음     | **매우 단순** |
| 렌더링    | O             | X             |
| 충돌 계산 | X             | O             |

------

## collider의 대표적인 형태

### Box / Cuboid

- 가장 빠름
- 차량, 건물, 벽

```js
ColliderDesc.cuboid(x, y, z)
```

### Sphere (Ball)

- 회전 계산 간단
- 바퀴, 공

```js
ColliderDesc.ball(radius)
```

### Capsule

- 사람, 캐릭터
- 넘어지지 않음

```js
ColliderDesc.capsule(height, radius)
```

### Convex Hull

- 오목하지 않은 복잡한 형태
- 성능과 정확도의 중간

### Trimesh

- 실제 지형
- **비쌈 / 정적 객체 전용**

```js
ColliderDesc.trimesh(vertices, indices)
```

------

## collider 동작구조 

```
RigidBody (물체의 질량·속도·힘)
    ↑
Collider (충돌 모양)
```

- Collider는 **RigidBody에 붙어서 동작**
- Collider 혼자서는 의미 없음

- “벽” → RigidBody + Collider
- “차량” → RigidBody + 여러 Collider

| 상황               | 결과               |
| ------------------ | ------------------ |
| collider 없음      | 다른 물체 통과     |
| collider 위치 오류 | 허공에서 충돌      |
| collider 크기 오류 | 공중부양 / 땅 뚫음 |
| collider 과도      | 성능 저하          |

------

## 예시 (차량 기준)

###### 눈에 보이는 것

- 차 모델 (복잡한 메쉬)

##### 물리용

- 차체: Box Collider
- 바퀴: Cylinder / Sphere Collider

```
[차 모델]
   ↓
[Box Collider + Sphere Collider x4]
```

------

## 요약

- Collider는 “물리 엔진이 충돌을 계산하기 위해 쓰는 보이지 않는 단순한 도형”이며, 실제 모델 대신 빠르고 안정적인 물리 계산을 가능하게 한다.