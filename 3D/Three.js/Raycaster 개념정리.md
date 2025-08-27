# Raycaster 개념정리 (three.js)

---

>

## Raycaster란?
- three.js에서 **광선(ray)** 을 쏴서 씬(Scene)의 오브젝트와 교차 여부를 계산하는 도구.
- 주로 **마우스 피킹(클릭/호버로 객체 선택)**, **레이저 포인터**, **시선 추적** 등에 사용.

## 좌표계 개념

##### 브라우저 좌표 (event.clientX / event.clientY)
- 브라우저 이벤트에서 제공하는 **픽셀 단위 좌표**
- 원점: **왼쪽 위 (0,0)**
- 오른쪽/아래로 갈수록 값이 증가
- 화면 해상도에 의존 (1920x1080 화면이면 X 최대 1919, Y 최대 1079)

##### NDC 좌표 (Normalized Device Coordinates)
- three.js / WebGL 표준 좌표계
- 범위: X, Y, Z 모두 **-1 ~ +1**
- 원점: **화면 중앙 (0,0)**
- 오른쪽이 +X, 위쪽이 +Y, 깊이 방향이 Z

##### 변환이 필요한 이유
- `Raycaster.setFromCamera(mouse, camera)` 는 **NDC 좌표**를 입력으로 요구
- 따라서 브라우저 좌표를 **NDC로 변환**해야만 Raycaster가 올바른 광선을 생성할 수 있음

##### 변환 공식
```js
mouse.x =  (event.clientX / window.innerWidth)  * 2 - 1;
mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;
```

## 사용 순서

1. **마우스 좌표 → NDC 변환**
2. `raycaster.setFromCamera(mouse, camera)` 로 광선 생성
3. `raycaster.intersectObjects(objects, recursive)` 로 교차 검사
4. 반환 배열을 확인해 가장 가까운 오브젝트(`hits[0]`)를 사용

## 간단 예제

```js
const raycaster = new THREE.Raycaster();
const mouse = new THREE.Vector2();

function onClick(event) {
  // 1. 마우스 좌표(브라우저 좌표)를 NDC(Threejs 좌표)로 변환
  mouse.x = (event.clientX / window.innerWidth) * 2 - 1;
  mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;

  // 2. 카메라 기준으로 광선 생성
  raycaster.setFromCamera(mouse, camera);

  // 3. 씬 전체 검사 (true = 자식까지 포함)
  const hits = raycaster.intersectObjects(scene.children, true);

  // 4. 가장 가까운 오브젝트 처리
  if (hits.length > 0) {
    const hit = hits[0].object;
    hit.material.color.set(0xff0000); // 클릭된 오브젝트 색 변경
  }
}
window.addEventListener('click', onClick);
```

## 정리 

- **Raycaster** = 3D 씬에서 광선을 쏘아 객체 교차 검사
- **브라우저 좌표 → NDC 변환**은 필수
- `intersectObjects(scene.children, true)`로 씬 전체를 검사 가능
- 반환값은 **가장 가까운 교차점부터 정렬된 배열**

---

## 레이저 포인터 추가

```js
// 2) 빨간 레이저 라인 (Line)
const laserGeom = new THREE.BufferGeometry().setFromPoints([new THREE.Vector3(), new THREE.Vector3()]);
const laserMat = new THREE.LineBasicMaterial({
  color: 0xff0000,
  depthTest: false,           // 씬 뒤에 가려지지 않게 하려면 false (원하면 true로)
  transparent: true,
  opacity: 0.95,
});
const laser = new THREE.Line(laserGeom, laserMat);
laser.renderOrder = 999;      // 다른 것 위에 그리기 (depthTest:false일 때 유용)
scene.add(laser);

// 3) 끝점 표시용 빨간 점
const hitDot = new THREE.Mesh(
  new THREE.SphereGeometry(0.025, 16, 16),
  new THREE.MeshBasicMaterial({ color: 0xff0000 })
);
hitDot.visible = false;
scene.add(hitDot);

// 피킹 대상(성능 위해 선정)
const pickables = [box];

// 마우스 → NDC 변환
function updateMouseNDC(e) {
  const rect = renderer.domElement.getBoundingClientRect();
  mouse.x = ((e.clientX - rect.left) / rect.width) * 2 - 1;
  mouse.y = -((e.clientY - rect.top) / rect.height) * 2 + 1;
}

// 레이저 업데이트 (마우스 이동 시)
function onPointerMove(e) {
  updateMouseNDC(e);
  raycaster.setFromCamera(mouse, camera);

  const origin = raycaster.ray.origin; // 카메라 위치(원근 카메라 기준)
  let end;

  const hits = raycaster.intersectObjects(pickables, true);
  if (hits.length) {
    end = hits[0].point;             // 충돌 지점까지
    hitDot.position.copy(end);
    hitDot.visible = true;
  } else {
    end = origin.clone().add(raycaster.ray.direction.clone().multiplyScalar(100)); // 고정 길이
    hitDot.visible = false;
  }

  // 라인 시작/끝점 갱신
  laser.geometry.setFromPoints([origin, end]);
}

// 포인터 이동 이벤트 연결
renderer.domElement.addEventListener('pointermove', onPointerMove);
```

