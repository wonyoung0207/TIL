# Quaternion vs Euler Angles 차이점 정리 

---

>

## 1. 개념 비교 Summary

| 구분                  | Euler Angles (`rotation`)                      | Quaternion (`quaternion`)                             |
| :-------------------- | :--------------------------------------------- | :---------------------------------------------------- |
| **표현 방식**         | X, Y, Z 3개 축의 회전 각도 `(x, y, z)`         | 3D 회전축 + 회전 각도의 4차원 벡터 `(x, y, z, w)`     |
| **직관성**            | 높음 (사람이 몇 도 회전했는지 이해하기 쉬움)   | 낮음 (수학적 복소수/벡터 개념으로 구성됨)             |
| **짐벌락 현상**       | **발생함** (특정 각도에서 축이 겹쳐 회전 꼬임) | **발생하지 않음** (모든 방향 회전 안전)               |
| **회전 보간 (SLERP)** | 부자연스러움 (축별 독립 각도 변화로 궤적 꼬임) | 최단 경로로 매우 부드럽게 보간 가능                   |
| **주요 용도**         | UI 입력, 슬라이더, 초반 직관적 설정            | 3D 물리 엔진(Rapier, Unity), 카메라/캐릭터 애니메이션 |

---

## 2.  Quaternion 사용이유? (Euler Angles와의 차이점)

### 1. 짐벌락 (Gimbal Lock) 해결
- **오일러 각**: X, Y, Z 축 회전을 **순차적**으로 적용하므로, 특정 축(예: Y축 90도)이 회전할 때 나머지 두 축(X축과 Z축)이 동일한 평면 상에 겹치게 됩니다. 이로 인해 **1개의 회전 자유도를 잃어버리는 현상**이 발생한다. 
- **쿼터니언**: 특정 축 순서에 의존하지 않고 **3D 공간 상의 임의의 3D 회전축(Axis Vector)을 기준으로 한 번에 회전**시키므로 짐벌락이 구조적으로 발생하지 않는다. 

### 2. 부드러운 회전 보간 (SLERP: Spherical Linear Interpolation)
- 두 회전 상태 사이를 연결할 때 오일러 각을 쓰면 회전 궤적이 일그러진다. 
- 쿼터니언은 구면 선형 보간(SLERP)을 사용하여 A 지점에서 B 지점으로 회전할 때 **최단 회전 경로를 따라 일정한 속도로 부드럽게 이동**시킨다. 

### 3. 3D 물리 엔진과의 연동 표준
- Three.js 내부 메쉬는 사람이 다루기 편하도록 `rotation(Euler)`을 제공하지만, 내부 연산 및 Rapier/PhysX 등 모든 3D 물리 엔진은 **회전 상태를 Quaternion으로 관리**힌다. 

---

## 3. Three.js & Rapier 연동 핵심 패턴

- Three.js에서 오일러 각을 변경하면 내부적으로 `quaternion`이 자동으로 계산 된다.

```typescript
// 1. Three.js visual mesh 회전 (Euler)
mesh.rotation.set(rotX, rotY, rotZ);

// 2. Rapier 물리 바디 동기화 (Quaternion)
// Three.js가 자동 업데이트한 quaternion을 물리 엔진에 동기화
body.setRotation(mesh.quaternion, true);

// 3. 회전된 방향 벡터 계산 (Quaternion 응용)
// 로컬 이동 속도를 현재 메쉬 회전에 맞춰 월드 속도로 변환
const worldVelocity = localVelocity.clone().applyQuaternion(mesh.quaternion);
body.setLinvel(worldVelocity, true);