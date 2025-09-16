# MeshStandardMaterial 빛 반사 문제 정리

---

> 

## 문제 현상
- `MeshStandardMaterial`로 만든 Mesh가 **검은색으로만 보임**
- `color` 값을 지정했는데도 화면에 색상이 전혀 나오지 않음

```js
const material = new THREE.MeshStandardMaterial({
  color: 0xaaaaaa,
  metalness: 1,
  roughness: 0,
});
```

## 원인

- `MeshStandardMaterial`은 **물리 기반 렌더링(PBR)** 머티리얼
  - 표면이 스스로 빛을 내지 않고 **빛과 환경을 반사**해야 색상이 보임
  - 특히 `metalness: 1` + `roughness: 0` → "거울 같은 금속"
- 단순 조명만 있으면 반사할 환경이 없어 **검게 보임**

## 해결 방법

##### 1. 광원 추가하기

- 조명을 추가하면 색상이 나타남.
- 조금 밝아지긴 하지만 어두움 ( 빛 광량 늘려도 같음)
  - environment 빛 추가로 해결함 

```js
const ambientLight = new THREE.AmbientLight(0xffffff, 0.3);
scene.add(ambientLight);

const directionalLight = new THREE.DirectionalLight(0xffffff, 1);
directionalLight.position.set(5, 10, 5);
scene.add(directionalLight);
```

##### 2. 환경맵(Environment Map) 추가하기

- 금속성 재질은 **환경반사**가 있어야 현실적인 색상이 보임.

```js
const cubeTextureLoader = new THREE.CubeTextureLoader();
const environmentMap = cubeTextureLoader.load([
  "px.png", "nx.png",
  "py.png", "ny.png",
  "pz.png", "nz.png",
]);

scene.background = environmentMap;
scene.environment = environmentMap;
```

##### 3. HDRI 환경맵 사용하기 (권장)

- 더 자연스러운 반사를 위해 HDRI 텍스처 사용.

```js
const pmremGenerator = new THREE.PMREMGenerator(renderer);
const envMap = pmremGenerator.fromScene(new THREE.RoomEnvironment()).texture;

scene.environment = envMap;
```

## 핵심 요약

- `MeshBasicMaterial` → 조명 필요 없음, 항상 색 보임
- `MeshStandardMaterial` / `MeshPhysicalMaterial` → **조명 + 환경맵 필수**
- 금속(`metalness=1`)은 특히 **환경맵이 없으면 검게 보임**