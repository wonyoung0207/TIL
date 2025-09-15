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

##  Raycaster 동작 원리

- **시작점(origin)과 광선의 방향(direction)을 정해** 광선 객체를 생성한다.

1. **Ray(광선) 정의**

   - 시작점(origin) + 방향(direction)으로 무한 직선을 만든다. 

   - 예제 코드:

     ```js
     const rayOrigin = new THREE.Vector3(-3, 0, 0) // 시작 좌표
     const rayDirection = new THREE.Vector3(1, 0, 0) // x축 방향
     rayDirection.normalize()
     raycaster.set(rayOrigin, rayDirection)
     ```

   -  이 경우 `( -3, 0, 0 )` 에서 시작해서 **+X 방향**으로 뻗어나가는 광선이 된다. 

2. **광선 발사**

   - `raycaster.intersectObjects(objectsToTest)` 를 호출하면, **지정한 객체 배열과 교차하는지 검사**한다. 
   - 교차점이 있으면 `intersects` 배열에 정보(거리, 좌표, 맞은 객체)가 들어간다. 

3. 즉, 

   - **Raycaster는** 시작점 + 방향으로 무한히 뻗는 광선을 만들어, 지정한 객체와 충돌 여부를 검사함.
   - **normalize()는** 방향 벡터의 길이를 항상 1로 만들어 계산 안정성을 확보하는 단계.
   - 즉, “광선을 제대로 쏘려면 방향 벡터를 단위 벡터로 만들어야 한다”는 뜻.

## `normalize()` 의 의미

- `rayDirection` 은 **방향을 나타내는 벡터**
- 하지만 Raycaster 내부 계산은 “방향”만 필요하지 “길이”는 중요하지 않다. 
- 예:
  - `(10, 0, 0)` 도 +X 방향
  - `(1, 0, 0)` 도 +X 방향
- 하지만 길이가 다르면 **계산할 때 스케일링 문제가 생길 수 있음**
- 그래서 `normalize()` 로 벡터의 길이를 항상 `1`로 맞춰주는게 일반적이다. 
  - 즉, `rayDirection.normalize()` = “벡터를 방향만 유지한 채 길이 1로 만들어라"

##### normalize() 를 안 하면?

- 길이가 큰 벡터일 경우 → 거리 계산 시 불필요하게 곱해져 교차 지점 위치가 잘못 나올 수 있음
- 길이가 작은 벡터일 경우 → 광선이 사실상 `0`에 가까워져서 계산 오류 가능

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

