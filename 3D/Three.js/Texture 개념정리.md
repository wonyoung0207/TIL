# Texture 개념정리

---

>- [폴리곤닷컴](http://poliigon.com/)
>- [3d텍스처.me](http://3dtextures.me/)
>- [arroway-textures.ch](http://arroway-textures.ch/)

## 텍스처란?

- **이미지를 3D 객체 표면에 입히는 것**
- 텍스처는 `THREE.Texture` 객체로 다루며, 이미지 외에도 **색상, 반복, 회전, 보간** 등 다양한 옵션을 설정할 수 있음.
- 더 작은 Texture 가 성능측면에서는 더 좋다. 
  - **작은 Texture 를 이용해 파일 용량을 줄이고 보간과 밉맵 으로 더 좋게 표현하면 성능 향상됨** 

## 주요 속성 정리

| 속성명            | 설명                                                         |
| ----------------- | ------------------------------------------------------------ |
| `repeat`          | 텍스처를 x, y 방향으로 얼마나 반복할지 설정. `Vector2` 타입  |
| `offset`          | 텍스처를 얼마나 밀어서 시작할지 (기준 좌표를 이동)           |
| `rotation`        | 텍스처를 얼마나 회전시킬지 (단위: 라디안)                    |
| `center`          | 텍스처 회전/스케일의 기준점 (0~1, 기본값: `(0, 0)` = 좌하단) |
| `wrapS`, `wrapT`  | 텍스처가 경계를 넘어갔을 때 어떻게 표시할지 결정 (`RepeatWrapping`, `ClampToEdgeWrapping`, `MirroredRepeatWrapping`) |
| `minFilter`       | 텍스처가 축소될 때 픽셀 보간 방식                            |
| `magFilter`       | 텍스처가 확대될 때 픽셀 보간 방식                            |
| `generateMipmaps` | 밉맵(Mipmaps**)**을 생성할지 여부 (기본값: `true`)           |
| `colorSpace`      | 색상 공간 설정 (`THREE.SRGBColorSpace` 권장)                 |

## 대표 속성 예시

##### 반복 (`repeat`)

```js
texture.repeat.set(2, 3)  // x축 2번, y축 3번 반복
texture.wrapS = THREE.RepeatWrapping
texture.wrapT = THREE.RepeatWrapping
```

##### 기준점 및 회전 (`center`, `rotation`)

```js
texture.center.set(0.5, 0.5)            // 중심을 정중앙으로 설정
texture.rotation = Math.PI * 0.25      // 45도 회전
```

##### 이동 (`offset`)

```js
texture.offset.set(0.5, 0.25)  // 오른쪽으로 0.5, 아래로 0.25 이동
```

##### 필터링 (확대/축소)

```js
const texture = textureLoader.load('brick.jpg')

// 필터링
texture.minFilter = THREE.LinearMipMapLinearFilter  // 축소 + 밉맵 + 부드러움
texture.magFilter = THREE.LinearFilter              // 확대 시 부드럽게

// 밉맵 설정
texture.generateMipmaps = true
```

---

## Filtering  vs  Mipmapping 

##### Filtering (필터링)

- 텍스처가 **확대되거나 축소될 때** 픽셀들이 깨지지 않게 보정해주는 방식
- 주요 종류:

| 이름                               | 설명                                                      |
| ---------------------------------- | --------------------------------------------------------- |
| `THREE.NearestFilter`              | 가장 가까운 픽셀 하나만 사용 → **블록처럼 뭉툭한 픽셀화** |
| `THREE.LinearFilter`               | 주변 픽셀들과 평균 → **부드러운 결과** (기본값)           |
| `THREE.NearestMipMapNearestFilter` | 밉맵 중 가장 가까운 해상도 하나 + Nearest 필터            |
| `THREE.LinearMipMapLinearFilter`   | 밉맵 해상도 2개를 보간 + 픽셀도 보간 (기본값)             |

```js
texture.minFilter = THREE.LinearFilter       // 축소할 때
texture.magFilter = THREE.NearestFilter      // 확대할 때
```

##### Mipmapping (밉맵핑)

- 텍스처의 **축소 버전 이미지들을 미리 만들어 놓고**, 멀리 있는 오브젝트에 쓸 때 **가장 적절한 크기의 텍스처**를 자동으로 선택하는 기술
- 왜 필요할까?
  - 텍스처가 화면에서 아주 작게 보일 때 → 원본 텍스처를 그대로 쓰면 **계단 현상(aliasing)** 발생
  - 그래서 작은 버전을 미리 만들어 두고, 그걸 씀
  - 적절한 크기의 텍스처로 자동 전환 → 더 **부드럽게 보이고**, **렌더링 성능도 올라감** 💨
- 장점
  - 장점: 성능 ↑, 깜빡임 ↓, 화질 ↑ (멀리 있는 물체에 특히 효과적)

| 장점      | 설명                                              |
| --------- | ------------------------------------------------- |
| 성능 향상 | GPU가 작은 텍스처만 샘플링하므로 연산량 감소      |
| 품질 향상 | 멀리 있을 때 텍스처 깜빡이거나 깨지는 현상 줄어듦 |

```js
texture.generateMipmaps = true   // 기본값 true
texture.minFilter = THREE.LinearMipMapLinearFilter // 밉맵 포함 필터
```

##### Filtering vs Mipmapping 비교

| 항목      | Filtering                  | Mipmapping                            |
| --------- | -------------------------- | ------------------------------------- |
| 역할      | 픽셀을 **어떻게 보간**할지 | 작아질 때 **어떤 이미지 크기**를 쓸지 |
| 대상      | 확대 or 축소 시 텍스처     | 축소될 때만 사용됨                    |
| 예시 문제 | 깨짐, 흐릿함, 픽셀화       | 계단현상, 깜빡임, 무늬 깨짐           |
| 성능 영향 | 미세하게 있음              | 많이 좋음 (VRAM 사용 ↑)               |

---

## 전체 예시코드

```js
const texture = textureLoader.load('brick.jpg')

texture.wrapS = THREE.RepeatWrapping // 해당 속성 있어야 offset, repeat 정상동작함 
texture.wrapT = THREE.RepeatWrapping
texture.repeat.set(2, 2)
texture.offset.set(0.2, 0.2)
texture.center.set(0.5, 0.5)
texture.rotation = Math.PI * 0.25

texture.minFilter = THREE.LinearMipMapLinearFilter
texture.magFilter = THREE.LinearFilter
texture.generateMipmaps = true

texture.colorSpace = THREE.SRGBColorSpace
```

