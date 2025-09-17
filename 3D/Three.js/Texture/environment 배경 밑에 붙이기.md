# 배경 밑에 붙이는 방법 정리

---

>

## 1. 기본 환경맵 설정

```js
env.mapping = THREE.EquirectangularReflectionMapping
env.colorSpace = THREE.SRGBColorSpace

scene.background = env     // 화면 배경
scene.environment = env    // PBR 머티리얼 반사/굴절
```

- 이 방식은 **무한한 구(SkyDome)**처럼 배경을 보여주지만, 바닥/지면 개념은 없음.

## 2. 큐브맵 스카이박스

```js
const cubeMap = new THREE.CubeTextureLoader().load([
  'px.jpg', 'nx.jpg',
  'py.jpg', 'ny.jpg',
  'pz.jpg', 'nz.jpg'
])

scene.background = cubeMap
scene.environment = cubeMap
```

- 환경맵을 6장의 큐브 이미지로 구성 → **완전한 박스 형태**.
- 바닥은 표현되지만, 여전히 “지면에 닿는” 효과는 없음.

## 3. GroundedSkybox (돔 + 바닥)

```js
import { GroundedSkybox } from 'three/examples/jsm/objects/GroundedSkybox.js'

const params = { radius: 100, height: 20 }
const skybox = new GroundedSkybox(env, params.radius, params.height)
scene.add(skybox)
```

- `radius`: 스카이돔 반경
- `height`: 바닥 평면의 높이

- HDRI를 단순히 구 전체에 맵핑하는 게 아니라, **지면과 하늘을 동시에 표현**.
  - 즉, **배경이 바닥에 붙어 보이는 효과** 제공.
- 조심할점
  - skyBox 가 감싸는 형태로 구현되는것. 
  - **외부 배경 background 일부 속성을 바꿔도 적용 안됨** (`backgroundBlurriness, backgroundIntensity, backgroungRotationY 등등 적용 안됨`)

## 4. 실제 Plane 지면 추가

```js
const plane = new THREE.Mesh(
  new THREE.PlaneGeometry(1000, 1000),
  new THREE.MeshStandardMaterial({
    color: 0x888888,
    roughness: 1,
    metalness: 0
  })
)
plane.rotation.x = -Math.PI / 2
plane.position.y = 0 // 바닥 높이
plane.receiveShadow = true
scene.add(plane)
```

- 환경맵만으로는 지면이 없으므로,  실제 Plane Mesh를 추가해서 **오브젝트들이 놓일 바닥**을 만들어야 한다.

## 5. GUI로 Skybox 조절

```js
const params = { radius: 100, height: 20 }

gui.add(params, 'radius', 10, 500, 1).onChange(v => {
  scene.remove(skybox)
  skybox = new GroundedSkybox(env, v, params.height)
  scene.add(skybox)
})

gui.add(params, 'height', 1, 100, 1).onChange(v => {
  scene.remove(skybox)
  skybox = new GroundedSkybox(env, params.radius, v)
  scene.add(skybox)
})
```

- 실시간으로 radius, height를 바꿔가며 **바닥에 맞게 Skybox 조정 가능**.

## 정리

- **scene.background + environment**: 무한 배경 (지면 없음)
- **CubeTextureLoader**: 큐브맵 기반 배경 (지면 표현 O, 하지만 평평하지 않음)
- **GroundedSkybox**: HDRI를 지면까지 붙여서 표현 (돔+바닥 효과)
- **Plane Mesh**: 실제 지면 오브젝트를 만들어서 배치 (가장 확실한 방법)

- “배경이 밑에 붙어야 한다”면 **GroundedSkybox** 또는 **Plane Mesh 추가**를 함께 쓰는 게 정석