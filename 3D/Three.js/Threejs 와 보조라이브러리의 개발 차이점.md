# Threejs 와 보조라이브러리의 개발 차이점

---

>

## 학습 이유 

- vue3 + typescript + threejs 이용해 프로젝트를 진행하고 있었다. 
  - 그런데, render 시점이나 event 관리, mesh, model 선언 등 관리해야할 사항들이 너무 많고 복잡해지는걸 느꼈다. 
- 그러다 이런 생성 및 렌더를 컴포넌트를 이용해 보조해주는 라이브러리가 vue와 react에 있다는것을 알게 되었다. 
  - vue :  `@tresjs/drei`
  - react : `@react-three/drei`

## Three.js vs @tresjs/drei 비교 요약

| 구분                        | **Three.js (기본)**                                          | **@tresjs/drei (Vue 3 + TresJS)**       |
| --------------------------- | ------------------------------------------------------------ | --------------------------------------- |
| **환경**                    | JavaScript / 직접 DOM 제어                                   | Vue 3 + 선언형 3D 컴포넌트              |
| **렌더링 제어**             | `requestAnimationFrame()` 직접 호출                          | `useRenderLoop()` 자동 프레임 루프      |
| **카메라 / 조명 / 씬 생성** | 직접 코드로 `new THREE.Scene()`, `new THREE.PerspectiveCamera()` | `<TresCanvas>` 내부에서 자동 관리       |
| **코드 스타일**             | 명령형 (imperative)                                          | 선언형 (declarative, Vue Template 기반) |
| **목표 구조**               | 개발자 수동 제어 중심                                        | 직관적 컴포넌트 중심 구조               |
| **생산성**                  | 낮음 (모든 설정 수동)                                        | 높음 (자동 세팅 + 편의 컴포넌트 다수)   |
| **적합한 경우**             | WebGL 튜닝, 커스텀 렌더링                                    | 빠른 프로토타입, UI/3D 통합             |
| **예시 출력 결과**          | 동일한 3D 평면 (조명 + 머티리얼 적용)                        | 동일                                    |

## Three.js 단독 버전

```js
import * as THREE from 'three'

// Scene, Camera, Renderer 설정
const scene = new THREE.Scene()
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000)
camera.position.set(0, 3, 5)

const renderer = new THREE.WebGLRenderer({ antialias: true })
renderer.setSize(window.innerWidth, window.innerHeight)
document.body.appendChild(renderer.domElement)

// 평면 mesh
const geometry = new THREE.PlaneGeometry(5, 5)
const material = new THREE.MeshStandardMaterial({ color: 'greenyellow' })
const plane = new THREE.Mesh(geometry, material)
plane.rotation.x = -Math.PI / 2
scene.add(plane)

// 조명 추가
const light = new THREE.DirectionalLight(0xffffff, 1)
light.position.set(1, 2, 3)
scene.add(light)

// 렌더 루프
function animate() {
  requestAnimationFrame(animate)
  plane.rotation.z += 0.01
  renderer.render(scene, camera)
}
animate()
```

- 모든 구성요소(Scene, Light, Renderer 등)를 직접 생성하고 연결해야 함.

## @tresjs/drei (Vue 3 + TresJS) 버전

```vue
<script setup lang="ts">
import { OrbitControls } from '@tresjs/drei'
</script>

<template>
  <TresCanvas shadows>
    <TresPerspectiveCamera :position="[0, 3, 5]" />
    <ambientLight :intensity="1.5" />
    <directionalLight :position="[1, 2, 3]" :intensity="4" />

    <!-- 평면 mesh -->
    <mesh rotation-x="-Math.PI / 2" receive-shadow>
      <planeGeometry :args="[5, 5]" />
      <meshStandardMaterial color="greenyellow" />
    </mesh>

    <!-- drei 유틸 -->
    <OrbitControls />
  </TresCanvas>
</template>
```

- 카메라, 조명, 루프, 렌더러를 자동으로 관리.
- 선언형 문법으로 유지보수와 재사용이 쉬움.

## 핵심 차이 요약

| 항목                    | Three.js                             | @tresjs/drei                                        |
| ----------------------- | ------------------------------------ | --------------------------------------------------- |
| **Scene 구성**          | 수동 생성                            | `<TresCanvas>` 자동 관리                            |
| **렌더 루프**           | `animate()` 직접 작성                | `useRenderLoop()` or 자동 처리                      |
| **Geometry / Material** | `new THREE.PlaneGeometry()`          | `<planeGeometry />` 태그                            |
| **카메라 / 조명**       | 수동 추가                            | `<TresPerspectiveCamera />`, `<directionalLight />` |
| **카메라 컨트롤**       | `OrbitControls` 직접 임포트 & 초기화 | `<OrbitControls />` 컴포넌트 제공                   |
| **코드 길이**           | 길고 절차적                          | 짧고 선언적                                         |
| **개발 난이도**         | 높음                                 | 낮음                                                |

## 결론

- **three.js**:
   모든 렌더링 로직을 직접 제어해야 하는 “저수준 API”
   → 세밀한 제어가 필요할 때 적합
- **@tresjs/drei** (Vue 3):
   선언형 컴포넌트로 3D 장면을 쉽게 구성
   → UI와 3D를 통합한 앱 개발에 최적