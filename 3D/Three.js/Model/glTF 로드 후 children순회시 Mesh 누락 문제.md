# glTF 불러온 후 `children` 순회 시 Mesh 누락 문제 정리

---

>

## 문제 상황

- glTF 모델을 불러온 뒤 `scene.mesh.children`을 `for`문으로 순회하면서 Mesh를 다른 Scene/Object3D에 `.add()` 할 때,
  - 일부 Mesh가 누락됨
  - 새로고침할 때마다 누락되는 Mesh가 달라짐

## 원인

1. Three.js에서 **Object3D는 동시에 여러 부모를 가질 수 없음**
    → `.add()` 하면 자동으로 기존 부모에서 `.remove()` 됨
2. 이 과정에서 `children` 배열이 **순회 도중에 실시간 변경**됨
   - **첫 번째 요소를 옮기면 배열에서 제거**됨
   - **나머지 요소들이 앞으로 땡겨지면서 인덱스가 밀림**
   - `for`문은 인덱스를 증가시키므로 중간 요소가 건너뛰어짐
3. 그 결과:
   - 루프 도중 일부 Mesh가 누락
   - 새로고침마다 빠지는 대상이 달라지는 “랜덤” 현상 발생

```js
  // 첫 Mesh 가져오기 -> 여기서 가져오면 해당 Mesh 는 glTF 에서 제거되서 뒤에서 gltfScene 복제할때 추가 안됨 
  const childOne = gltf.scene.children[0]
  childOne.position.set(2, 0, 0)
  scene.add(childOne)

  const children = [...gltf.scene.children]
  for(const child of children)
  {
      scene.add(child)
  }
```

## 해결 방법

- **배열 복사 후 순회**

  ```js
  const meshes = [...scene.mesh.children]
  for (const mesh of meshes) {
    newScene.add(mesh)
  }
  ```

- **역순 for 루프**

  ```js
  for (let i = scene.mesh.children.length - 1; i >= 0; i--) {
    newScene.add(scene.mesh.children[i])
  }
  ```

- **`.traverse()` + `.clone()` (원본 유지하고 싶을 때)**

  ```js
  scene.traverse((child) => {
    if (child.isMesh) newScene.add(child.clone())
  })
  ```

## 정리

- **문제**: `children` 배열이 순회 중 변경되어 일부 Mesh가 누락됨
- **이유**: `.add()` 시 기존 부모에서 자동 제거 → 배열 인덱스 밀림
- **해결책**: 배열 복사본 순회 / 역순 순회 / `traverse + clone`