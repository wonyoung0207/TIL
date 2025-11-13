



# Vertex Shader 개념정리

---

>

## mesh 와 Vertex Shader관계 

- material이 geometry를 아는 게 아니라, draw 호출 시 렌더러가 geometry의 버퍼를 셰이더 attribute에 꽂아준다. 

  - 즉, shader 는 material 에 붙지만 shader의 계산은 mesh 가 만들어지고 나서 호출되고 랜더러가 geometry의 정점 정보를 shader와 연결해준다. 

  ```ts
  geometry = new THREE.PlaneGeometry(2, 2, 512, 512)
  
  material = new THREE.ShaderMaterial({
      vertexShader: glslVertexShader,
      fragmentShader: glslFragmentShader,
      uniforms:
      {
          uTime: { value: 0 },
      }, 
      // wireframe: true 
  })
  
  oceanMesh = useCreateMesh(
      geometry,
      material,
  )
  ```

  

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

## 정점과 Vertex Shader 와의 관계

- Vertex Shader 는 각 정점마다 한번씩 호출되고 실행된다. 

  - 즉, plane Geometry 가 10개의 정점으로 구성되어있다면 총 10번의 vertex Shader 가 호출되는것이다. 

- 예를들어, 파도 모양을 만드는 vertex Shader 가 있다고 하자. 

  - 여기서 파도의 높낮이는 elevation 값에 의해 정해진다. 
    - modelPosition 은 현재 정점 하나를 **월드(World) 공간**으로 옮긴 결과이다. 
    - `xyz(로컬 공간에서 넘어온 하나의 정점)`  + `modelMatrix(메시에 적용된 이동/회전/스케일)` 된 값이다. 
  - `elevation` 값은 geometry의 정점 갯수 만큼 호출되어 계산된다. 
    - 즉, 각각의 정점마다 다른 `elevation` 이 적용되는 것이다. 

  ```glsl
  void main()
  {
      vec4 modelPosition = modelMatrix * vec4(position, 1.0);
  
      // 파도 Big 물결 모양  
      float elevation = sin(modelPosition.x * uBigWavesFrequency.x + uTime * uBigWavesSpeed) *
                        sin(modelPosition.z * uBigWavesFrequency.y + uTime * uBigWavesSpeed) *
                        uBigWavesElevation;
  
  
      modelPosition.y += elevation;
  
      vec4 viewPosition = viewMatrix * modelPosition;
      vec4 projectedPosition = projectionMatrix * viewPosition;
  
      gl_Position = projectedPosition;
  
      vUv = uv;
      vElevation = elevation;
  }