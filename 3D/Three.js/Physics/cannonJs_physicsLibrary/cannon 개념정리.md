#  Cannon.js 주요 개념

---

>

##  정리

- **World**: 물리 시뮬레이션 공간 (중력, 시간 관리)
- **Body**: 물리 물체 (위치, 질량, 속도)
- **Shape**: Body의 충돌 모양 (Sphere, Box, Plane 등)
- **Material/ContactMaterial**: 마찰·반발 같은 재질 속성
- **Step**: 매 프레임마다 물리 계산 실행 → Mesh에 반영

## 1. World (물리 세계)

- Cannon의 모든 물리 계산은 **World** 안에서 일어남.
- 중력, 시간 단위, 물체(body)들이 여기 등록됨.

```js
const world = new CANNON.World()
world.gravity.set(0, -9.82, 0) // y축 아래 방향으로 중력
```

## 2. Body (물체)

- **물리 시뮬레이션 대상이 되는 객체**.
- 위치, 속도, 질량, 회전, 모양(Shape)을 가짐.
- Three.js의 `Mesh`와 1:1로 매칭해서 씀.

```js
const sphereBody = new CANNON.Body({
  mass: 1, // 0이면 고정된 물체(Static body)
  position: new CANNON.Vec3(0, 10, 0),
  shape: new CANNON.Sphere(1) // 반지름 1
})
world.addBody(sphereBody)
```

## 3. Shape (모양)

- Body에 붙는 **충돌 모양**.
- 실제 렌더링은 Three.js Mesh가 하고, Cannon에서는 충돌만 계산.

대표적인 Shape:

```js
new CANNON.Sphere(radius)
new CANNON.Box(new CANNON.Vec3(width/2, height/2, depth/2))
new CANNON.Plane()
```

## 4. Material & ContactMaterial (재질)

- **재질**: 물체의 마찰, 반발력(bounciness) 설정.
- **접촉 재질(ContactMaterial)**: 두 물체가 만났을 때 상호작용 정의.

```js
const defaultMaterial = new CANNON.Material('default')
const contact = new CANNON.ContactMaterial(
  defaultMaterial, 
  defaultMaterial, 
  { friction: 0.1, restitution: 0.7 }
)
world.addContactMaterial(contact)
```

## 5. Step (물리 계산 실행)

- World는 `step()`으로 매 프레임마다 업데이트.
- 이때 Cannon의 Body 위치를 Three.js Mesh 위치에 반영해야 함.

```js
function animate() {
  world.step(1/60) // 60fps 기준 시뮬레이션 진행

  // cannon body → three mesh 위치 동기화
  mesh.position.copy(sphereBody.position)
  mesh.quaternion.copy(sphereBody.quaternion)

  renderer.render(scene, camera)
  requestAnimationFrame(animate)
}
```

## 기본 예제 (Three.js + Cannon.js)

```js
// World 생성
const world = new CANNON.World()
world.gravity.set(0, -9.82, 0)

// 바닥 Plane
const groundBody = new CANNON.Body({
  mass: 0, // 고정
  shape: new CANNON.Plane()
})
groundBody.quaternion.setFromEuler(-Math.PI/2, 0, 0) // 눕히기
world.addBody(groundBody)

// 구체 Body
const sphereBody = new CANNON.Body({
  mass: 1,
  shape: new CANNON.Sphere(1),
  position: new CANNON.Vec3(0, 5, 0)
})
world.addBody(sphereBody)

// Three.js Mesh랑 연결
const sphereMesh = new THREE.Mesh(
  new THREE.SphereGeometry(1, 32, 32),
  new THREE.MeshStandardMaterial({ color: 0xff0000 })
)
scene.add(sphereMesh)

// 애니메이션 루프
function animate() {
  world.step(1/60)

  sphereMesh.position.copy(sphereBody.position)
  sphereMesh.quaternion.copy(sphereBody.quaternion)

  renderer.render(scene, camera)
  requestAnimationFrame(animate)
}
animate()
```

