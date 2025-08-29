## **FlyControls**

- 비행기처럼 6자유도(앞/뒤/좌/우/상/하)로 자유롭게 나는 컨트롤. 시뮬레이션/비행모드에 적합.

```js
import { FlyControls } from 'three/examples/jsm/controls/FlyControls.js'
const controls = new FlyControls(camera, renderer.domElement)
controls.movementSpeed = 10
controls.rollSpeed = Math.PI / 12
```

## **TransformControls**

- 씬 안의 물체를 손으로 잡아 움직이는 도구
- 씬 안의 오브젝트(메시)를 **마우스로 직접 이동(Translate), 회전(Rotate), 크기 조절(Scale)** 할 수 있게 해주는 컨트롤러.
- 모델 에디터, 오브젝트 배치 툴, 씬 에디팅 UI 같은 곳. (예**: Blender처럼 화면에서 직접 잡아 움직이는 기능**)
- `control.attach(object)` / `control.detach()` 로 대상 오브젝트 붙였다 떼기.

```js
import { TransformControls } from 'three/examples/jsm/controls/TransformControls.js'

const control = new TransformControls(camera, renderer.domElement)
control.attach(mesh)         // 특정 mesh에 부착
scene.add(control)
control.setMode('translate') // 'translate' | 'rotate' | 'scale'
```

## **FirstPersonControls**

- 마우스로 시야 회전, 키보드(WASD)로 이동하는 1인칭 게임 스타일 컨트롤.

```js
import { FirstPersonControls } from 'three/examples/jsm/controls/FirstPersonControls.js'
const controls = new FirstPersonControls(camera, renderer.domElement)
controls.lookSpeed = 0.1
controls.movementSpeed = 5
```

## **PointerLockControls**

- 브라우저의 Pointer Lock API를 사용해 마우스 커서를 숨기고 1인칭 FPS 스타일 시야 제어.

```js
import { PointerLockControls } from 'three/examples/jsm/controls/PointerLockControls.js'
const controls = new PointerLockControls(camera, renderer.domElement)
document.addEventListener('click', () => controls.lock()) // 클릭 시 포인터락
```

## **OrbitControls**

- 마우스로 드래그해서 궤도(orbit)처럼 회전, 줌(휠), 팬 가능. 3D 모델 뷰어 등에 가장 흔히 사용.

```js
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js'
const controls = new OrbitControls(camera, renderer.domElement)
controls.enableDamping = true
```

## **TrackballControls**

- 트랙볼처럼 자유롭게 회전/줌/팬 가능한 컨트롤. 좀 더 직관적인 조작감 필요할 때 사용.

```js
import { TrackballControls } from 'three/examples/jsm/controls/TrackballControls.js'
const controls = new TrackballControls(camera, renderer.domElement)
controls.dynamicDampingFactor = 0.1 
```

##### **정리**

- **FlyControls** → 비행 시뮬레이터 같은 자유 비행
- **FirstPersonControls** → FPS 게임 같은 1인칭 이동
- **PointerLockControls** → 브라우저 포인터 락 기반 1인칭 FPS 스타일
- **OrbitControls** → 모델 뷰어나 씬 탐색용 궤도 회전
- **TrackballControls** → 트랙볼 같은 자유로운 회전/팬/줌
- 가장 흔하게 쓰이는 건 `OrbitControls`이고, FPS류 게임이면 `PointerLockControls`가 적합하다. 