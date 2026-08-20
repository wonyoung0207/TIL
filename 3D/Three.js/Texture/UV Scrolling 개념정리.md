# 3D 모델 UV Scrolling 개념정리 

---

>

## UV Scrolling 이란? 

- UV Scrolling은 3D 메쉬의 정점(Vertex) 위치나 3D 모델 자체를 실제로 이동시키지 않고, **표면에 입혀진 텍스처 좌표(UV Coordinate)의 Offset(오프셋)을 연속적으로 변경하여 표면이 움직이는 듯한 시각적 효과를 내는 기술**이다. 
- 컨베이어 벨트, 도로, 러닝머신, 물결, 에너지 흐름 등에 활용



## 핵심 개념

##### 1. RepeatWrapping (무한 반복 랩핑 모드)
* **개념**: 기본 Three.js 텍스처는 `ClampToEdgeWrapping`(끝부분 늘어남) 모드이다. 따라서 텍스처를 끊김 없이 계속 흘러가게 하려면 U축(가로)과 V축(세로) 랩핑 모드를 **`RepeatWrapping`**으로 변경해야 한다. 
* **주의사항**: 텍스처 생성 후 랩핑 모드를 코드에서 변경한 경우, 반드시 **`texture.needsUpdate = true`**를 설정해야 GPU 마테리얼 파이프라인에 변경 사항이 즉시 반영된다. 

```typescript
texture.wrapS = THREE.RepeatWrapping // U축 (Horizontal) 반복
texture.wrapT = THREE.RepeatWrapping // V축 (Vertical) 반복
texture.needsUpdate = true           // GPU 셰이더 갱신 필수
```



##### 2. UV Edit 시 Texture 방향 동기화 (3D 툴 ↔ Code)

* **개념**: 3D 모델링 툴(Blender 등)에서 UV Unwrapping 시 텍스처가 펼쳐진 방향(U축: Horizontal / V축: Vertical)과 Three.js 코드에서의 `offset` 변경 축이 상호 일치해야 한다. 
  * 나같은 경우엔 옆면과 밑에 면이 반대 방향으로 움직이는 문제가 있어 180 Rotation 을 적용해 해결했다.

* **축 매칭 원리**:
  * 3D 툴의 **U축** 방향으로 결이 펼쳐진 경우 ->  **`texture.offset.x`** 조정
  * 3D 툴의 **V축** 방향으로 결이 펼쳐진 경우 ->  **`texture.offset.y`** 조정
* **동기화 실패 증상**: 텍스처가 수평으로 배치되었는데 `offset.x` 대신 `offset.y`만 변경하면 텍스처 결의 옆면만 밀려서 움직이지 않는 것처럼 보입니다.



##### 3. mapKeys (모든 Texture Map 동시 스크롤의 필요성)
* **햇갈렸던 포인트**: `map` (Color Map) 뿐만 아니라 `normalMap`, `roughnessMap`, `bumpMap` 등 모든 `mapKeys`를 같이 업데이트해 줘야 하는지.
* 꼭 **입체감/질감이 있는 PBR 재질이라면 반드시 모든 Map을 동시 스크롤해야 한다.**
  * `map` (Color): 표면의 색상과 무늬
  * `normalMap`: 표면의 오목볼록한 홈과 입체적인 음영/입체감
  * `roughnessMap` / `metalnessMap`: 표면의 광택 및 반사도
* **이유**: 
  * `map`(색상)만 이동시키고 `normalMap`(입체 음영)을 이동시키지 않으면, **무늬는 흘러가는데 입체적인 홈과 그림자는 제자리에 멈춰 있는 어색한 그래픽 착시 오류**가 발생하기 때문이다. 



##### 4. UV Offset 이동, 방향 및 속도 제어

* **개념**: 프레임마다 `offset` 수치를 누적해서 변경하되, **DeltaTime(프레임 간 시간 격차)**을 곱해주어야 모니터 주사율(60Hz, 144Hz 등)에 상관없이 일정 속도로 부드럽게 이동합니다.
* **방향 & 속도 수식**:
  
  ```js
  // ── UV Scrolling (벨트 작동 중 시각적 텍스처 오프셋 이동) ──
  if (isRunning && beltTextures.length > 0) {
    const moveSpeed = config.beltForce.x || 2
    const speedMultiplier = 0.2 
  
    beltTextures.forEach((texture) => {
      const deltaOffset = moveSpeed * deltaTime * speedMultiplier
      texture.offset.y = (texture.offset.y - deltaOffset) % 1
    })
  }
  ```
* **진행 방향**:
  * `texture.offset.y -= deltaOffset`: 텍스처 전진 (일반적인 컨베이어 방향)
  * `texture.offset.y += deltaOffset`: 텍스처 후진 (역방향)
* **메모리 절약 (`% 1`)**: offset 값은 `0 ~ 1` 사이 소수점 값이 유지되도록 `% 1` (모듈로 연산)을 적용해 준다. 



##### 5. Map 표면 특정 (Targeting Specific Surface Mesh)

* **개념**: 3D GLTF 모델 전체의 텍스처를 스크롤하면 고정되어 있어야 할 **옆면/프레임(`conveyor-motor`)**까지 같이 흘러가는 오류가 생깁니다.
* **표면 타겟팅 방법**:
  1. `traverse`로 GLTF 자식 메쉬 탐색
  2. 고정 구조물(`motor`, `frame`, `stand` 등)은 제외하고, 실제 **움직여야 하는 표면 메쉬(`conveyor-belt`)만 이름으로 특정**
  3. 표면 메쉬에 적용된 Material 내의 Color map, Normal map, Roughness map 등 모든 Texture Map에 UV Scrolling 적용

---

## 핵심 구현 코드

```typescript
import * as THREE from 'three'

/** 1. 컨베이어 벨트 표면 텍스처 추출 및 RepeatWrapping 설정 */
function setupBeltTextures(textureScene: THREE.Object3D): THREE.Texture[] {
  const beltTextures: THREE.Texture[] = []
  const mapKeys = ['map', 'normalMap', 'roughnessMap', 'metalnessMap', 'bumpMap', 'aoMap']

  textureScene.traverse((child: THREE.Object3D) => {
    if (child instanceof THREE.Mesh) {
      const nameLower = child.name.toLowerCase()

      // ⚠️ 고정 부위(motor)는 제외하고, 실제 벨트 표면(belt) 메쉬만 특정
      if ((nameLower.includes('conveyor-belt') || nameLower.includes('belt')) && !nameLower.includes('motor')) {
        const materials = Array.isArray(child.material) ? child.material : [child.material]
        
        materials.forEach((mat: any) => {
          if (!mat) return
          mapKeys.forEach((key) => {
            if (mat[key] && mat[key] instanceof THREE.Texture) {
              const texture = mat[key] as THREE.Texture
              
              // 1️⃣ RepeatWrapping & GPU 갱신 설정
              texture.wrapS = THREE.RepeatWrapping
              texture.wrapT = THREE.RepeatWrapping
              texture.needsUpdate = true
              
              if (!beltTextures.includes(texture)) {
                beltTextures.push(texture)
              }
            }
          })
        })
      }
    }
  })

  return beltTextures
}

/** 2. 매 프레임(tick)마다 UV Offset 이동 실행 */
function updateUVScroll(beltTextures: THREE.Texture[], moveSpeed: number, deltaTime: number, isRunning: boolean) {
  if (!isRunning || beltTextures.length === 0) return

  // 3️⃣ 속도 배율 및 이동량 계산
  const speedMultiplier = 0.2
  const deltaOffset = moveSpeed * deltaTime * speedMultiplier

  beltTextures.forEach((texture) => {
    // 2️⃣ 3D 툴 UV Edit 방향에 맞춘 offset 축 이동 (V축 기준)
    texture.offset.y = (texture.offset.y - deltaOffset) % 1
  })
}
```

---

## 요약 정리

| 구분 | 핵심 내용 |
| :--- | :--- |
| **RepeatWrapping** | `wrapS`/`wrapT`를 `RepeatWrapping`으로 지정 후 `needsUpdate = true` 필수! |
| **UV 축 동기화** | Blender UV 배치축(U/V)에 맞춰 코드의 `offset.x` 또는 `offset.y`를 매칭 |
| **Offset 이동** | `deltaTime` 기반으로 부드럽게 계산하며, 부호(`+`/`-`)로 진행 방향 제어 |
| **표면 특정** | `traverse` 시 옆면/모터 메쉬는 제외하고 **움직이는 표면 메쉬만 특정**해 텍스처 스크롤 |