# camera 종류 

---

>

## 렌즈 종류 (시야각과 관련 )

1. 광각 렌즈 (35mm 이하) 
   - 멀리서 찍은것같은 느낌 (많은 내용 담을 수 있음)
   - 화각이 넒음 
   - 84 ~ 63 도
2. 표준 렌즈 (50mm)
   - 사람 눈과 비슷
   - 47도
3. 망원 렌즈 (85mm 이상)
   - 확대해 찍은것같은 느낌
   - 화각이 좁음 
   - 28 ~ 8도

## Near 와 Far

1. Near 
   - 카메라 시점 시작 위치
2. Far
   - 카메라 시점이 끝나는 위치 
3. 즉, Near와 Far 범위에서 벗어난 Obj는 Threejs 의 canvas에서 그려지지 않는다. 
   - **카메라가 볼 수 있는 최소, 최대 거리**

## Position

1. object 가 나를 바라보는 방향 
   1. 즉, `마이너스(-)` 이면 내가 보는 시점보다 뒤에있는거. (안보임) 
2. `lookAt` 
   - 카메라가 해당 좌표를 바라보게 함 

## `updateProjectionMatrix`

- 카메라 요소 중 하나라도 변경되면 호출되어야 한다. (threejs 에서 만들어놓았기 때문에)

```js
// 카메라 생성 
// fov: 카메라 시야각 (커질수록 멀리서 찍음)
// aspect 종횡비 (가로 세로 기준)
// Near & Far :  카메라가 볼 수 있는 최소, 최대 거리
camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000)
controls = new OrbitControls( camera, renderer.domElement );
controls.update();
camera.position.z = 7
camera.lookAt(0, 0, 0) // 카메라가 바라보는 방향 설정

// 화면 반응형 
function onWindowResize() {
  if (camera && renderer) {
    // 카메라 종횡비 (도형 찌그러짐 )
    camera.aspect = window.innerWidth / window.innerHeight 
    camera.updateProjectionMatrix()
    renderer.setSize(window.innerWidth, window.innerHeight)
  }
}

function animate() {
  frameId = requestAnimationFrame(animate)
  Object.values(meshObj).forEach(mesh => {
    if(mesh == meshObj.floorGeo) {
      mesh.rotation.x = -Math.PI / 2 // x축으로 90도 회전 → 바닥으로 눕힘
      mesh.receiveShadow = true
      return
    }
    // mesh.rotation.x += speedX.value
    mesh.rotation.y += speedY.value
    // mesh.rotation.z += speedZ.value
  })

  spotHelper.update() // ← 반드시 필요!
  renderer.render(scene, camera)
}

```

