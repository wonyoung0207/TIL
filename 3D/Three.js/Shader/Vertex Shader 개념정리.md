



## Vertex Shader 구조

1. 세 개(`projectionMatrix`, `viewMatrix`, `modelMatrix`) 의 구조로 되어있다. 
   1. 이 값들을 이용해 3D 좌표를 화면에 그리기 위해 거치는 3단계 변환 행렬을 가진다. 
2. 역할 
   1. **modelMatrix**: 물체를 세상에 배치
   2. **viewMatrix**: 세상을 카메라 시점으로 변환
   3. **projectionMatrix**: 카메라 화면(2D)에 투영

| 행렬 이름            | 역할                                | 변환 방향                                | 담당하는 값 (주요 요소)                                      |
| -------------------- | ----------------------------------- | ---------------------------------------- | ------------------------------------------------------------ |
| **modelMatrix**      | **물체 자체의 위치/회전/크기 변환** | 로컬 공간 → 월드 공간                    | - `position` (이동) - `rotation` (회전) - `scale` (크기)     |
| **viewMatrix**       | **카메라의 위치와 방향 적용**       | 월드 공간 → 카메라(뷰) 공간              | - 카메라의 위치 (`camera.position`) - 카메라의 방향 (`camera.lookAt`) |
| **projectionMatrix** | **3D → 2D 투영 변환**               | 카메라 공간 → 클립 공간 (스크린 투영 전) | - 시야각(FOV) - 종횡비(aspect) - near/far 거리(깊이 범위)    |

### 전체 변환 흐름

```js
local position
   ↓ modelMatrix
world position
   ↓ viewMatrix
camera-relative position
   ↓ projectionMatrix
screen-space position
```

- vertex shader 예시

```glsl
gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position, 1.0);
```