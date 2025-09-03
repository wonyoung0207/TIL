## Material 옵션 

| 분류                | 속성        | 타입             | 설명                            |
| ------------------- | ----------- | ---------------- | ------------------------------- |
| 🎨 **색상 / 텍스처** | **`color`** | `Color` or `hex` | 기본 색상 (`0xffffff` 등)       |
|                     | **`map`**   | `Texture`        | 컬러 텍스처                     |
|                     | `alphaMap`  | `Texture`        | 투명도 정의                     |
|                     | `aoMap`     | `Texture`        | 주변광 차폐 (ambient occlusion) |
|                     | `lightMap`  | `Texture`        | 조명 베이크맵                   |
|                     | `envMap`    | `Texture`        | 환경 반사 맵 (하늘, HDR 등)     |

------

| 분류                 | 속성              | 타입      | 설명                      |
| -------------------- | ----------------- | --------- | ------------------------- |
| 💡 **물리 기반(PBR)** | **`metalness`**   | `0 ~ 1`   | 금속성 (1.0 = 완전 금속)  |
|                      | **`roughness`**   | `0 ~ 1`   | 표면 거칠기 (0 = 반짝임)  |
|                      | `metalnessMap`    | `Texture` | 금속성 맵                 |
|                      | `roughnessMap`    | `Texture` | 거칠기 맵                 |
|                      | **`normalMap`**   | `Texture` | 표면 디테일 표현용 노멀맵 |
|                      | `displacementMap` | `Texture` | 지오메트리 변형 (높낮이)  |
|                      | `bumpMap`         | `Texture` | 가벼운 표면 돌기 표현     |

------

| 분류                     | 속성                | 타입         | 설명                                         |
| ------------------------ | ------------------- | ------------ | -------------------------------------------- |
| ✨ **투명 / 반사 / 광택** | **`transparent`**   | `boolean`    | true 시 `opacity` 사용 가능                  |
|                          | **`opacity`**       | `0 ~ 1`      | 투명도                                       |
|                          | **`side`**          | `THREE.Side` | 앞면/뒷면 렌더링 여부                        |
|                          | **`wireframe`**     | `boolean`    | 와이어프레임 표시                            |
|                          | `emissive`          | `Color`      | 자발광 색상                                  |
|                          | `emissiveIntensity` | `number`     | 발광 강도                                    |
|                          | `reflectivity`      | `0 ~ 1`      | 반사율 (`Phong`에서 주로 사용)               |
|                          | `clearcoat`         | `0 ~ 1`      | 추가 반짝임 (PhysicalMaterial 전용)          |
|                          | `ior`               | `1.0 ~ 2.33` | 굴절률 (유리/물 표현, PhysicalMaterial 전용) |

------

| 분류       | 속성          | 타입             | 설명                                 |
| ---------- | ------------- | ---------------- | ------------------------------------ |
| ⚙️ **기타** | `depthWrite`  | `boolean`        | 깊이 버퍼 기록 여부                  |
|            | `depthTest`   | `boolean`        | 깊이 테스트 수행 여부                |
|            | `alphaTest`   | `0 ~ 1`          | 픽셀 알파 값이 이 값보다 작으면 버림 |
|            | `blending`    | `THREE.Blending` | 블렌딩 방식 지정                     |
|            | `flatShading` | `boolean`        | 평면 쉐이딩 적용 여부                |

------

##  가장 많이 쓰는 핵심 옵션 TOP 10

1. `color`
2. `map`
3. `metalness`
4. `roughness`
5. `normalMap`
6. `opacity`
7. `transparent`
8. `wireframe`
9. `side`
10. `envMap`

```js
const material = new THREE.MeshStandardMaterial({
  color: 0xffffff,
  metalness: 0.8,
  roughness: 0.3,
  map: textureLoader.load('doorColor.jpg'),
  normalMap: textureLoader.load('doorNormal.jpg'),
  transparent: true,
  opacity: 0.9,
})
```

