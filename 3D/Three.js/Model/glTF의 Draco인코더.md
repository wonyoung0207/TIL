# glTF의 Draco인코더

---

>

## 동작 방식

- `GLTFLoader` 는 glTF 안의 `"extensionsUsed"` 필드를 보고,
  - 만약 `KHR_draco_mesh_compression` 이 있으면 → `DRACOLoader` 에게 압축 해제를 위임한다.  
- 일반 glTF(압축 없음)는 이 확장이 없기 때문에 → 그냥 원래 방식대로 로딩된다. 
- 즉, 
  - **드라코 압축 glTF O** → `DRACOLoader` 동작
  - **드라코 압축 glTF X** → `DRACOLoader` 무시

## 차이점

- **둘 다 glTF 포맷** (`.gltf` or `.glb`)

- **차이점은 meshes 구조**

  - 일반 glTF → `attributes` 가 직접 bufferView 참조
  - Draco glTF → `extensions.KHR_draco_mesh_compression` 를 통해 압축 bufferView 참조
  - 따라서 `GLTFLoader + DRACOLoader` 를 써야만 복원 가능

- 만약 드라코 glTF 인데 드라코 디코더 연결 안하면 다음 에러 발생함 

  ```js
  Error: THREE.GLTFLoader: No DRACOLoader instance provided.
      at new GLTFDracoMeshCompressionExtension (three_examples_jsm_loaders_GLTFLoader__js.js?v=23339c3a:1095:13)
      at GLTFLoader.parse (three_examples_jsm_loaders_GLTFLoader__js.js?v=23339c3a:356:41)
      at Object.onLoad (three_examples_jsm_loaders_GLTFLoader__js.js?v=23339c3a:229:15)
      at chunk-QUPN4MTR.js?v=23339c3a:23453:39
  ```

##### 드라코 압축 안된 glTF

```js
"meshes": [
  {
    "primitives": [
      {
        "attributes": {
          ...
        },
        "indices": 3,
        "mode": 4
      }
    ]
  }
],

"bufferViews": [
  { "buffer": 0, "byteOffset": 0, "byteLength": 840 },
  { "buffer": 0, "byteOffset": 840, "byteLength": 1680 },
  { "buffer": 0, "byteOffset": 2520, "byteLength": 560 }
],
"buffers": [
  { "uri": "model.bin", "byteLength": 3080 }
]

```

##### 드라코 압축 된 glTF

```json
"meshes": [
  {
    "primitives": [
      {
        "attributes": {
          ...
        },
        "mode": 4,
        "extensions": {
          "KHR_draco_mesh_compression": {
            "bufferView": 0,
            "attributes": {
              "POSITION": 0,
              "NORMAL": 1,
              "TEXCOORD_0": 2
            }
          }
        }
      }
    ]
  }
],

"bufferViews": [
  { "buffer": 0, "byteOffset": 0, "byteLength": 512 }  // 압축된 바이트 스트림
],
"buffers": [
  { "uri": "model.bin", "byteLength": 512 }
],
"extensionsUsed": ["KHR_draco_mesh_compression"]
```

## 드라코 디코더 사용방법

- `public/draco/` 폴더에는 다음 파일들이 필요
  - `draco_decoder.js`
  - `draco_decoder.wasm`

```js
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader'
import { DRACOLoader } from 'three/examples/jsm/loaders/DRACOLoader'

const loader = new GLTFLoader()
const dracoLoader = new DRACOLoader()

// 디코더 파일 경로 (public/draco/ 폴더에 wasm/js 파일 넣기)
dracoLoader.setDecoderPath('/draco/')

loader.setDRACOLoader(dracoLoader)

loader.load('/models/compressedModel.glb', (gltf) => {
  scene.add(gltf.scene)
})
```

## 드라코 glTF 만드는 방법

##### Blender에서 내보내기

1. 모델을 Blender에 불러오기 (`.fbx`, `.obj`, `.gltf`, `.glb` 등)
2. `File → Export → glTF 2.0 (.glb/.gltf)` 선택
3. Export 옵션에서:
   - **Compression** 항목 체크
   - `Geometry → Compression → Draco` 활성화
   - 압축 레벨(level)과 속성 유지 여부 선택
4. Export → Draco 압축 glTF 생성 완료

#####  압축 결과

- **파일 크기**: 메시 중심 모델에서 50~90% 줄어듦
- **glTF 구조**: `meshes.primitives` 안에 `extensions.KHR_draco_mesh_compression` 이 추가됨
- **로드 조건**: Three.js에서는 반드시 `DRACOLoader` 세팅 필요

##### 정리

- Draco 압축 glTF는 **일반 glTF의 geometry 부분만 Draco로 압축**된 형태
- 만드는 방법:
  1. Blender Export 시 Draco 옵션 켜기 (가장 쉬움)
  2. glTF-Transform CLI 사용 (`gltf-transform draco`)
  3. glTF-Pipeline CLI 사용 (`gltf-pipeline -d`)
- 압축하면 용량은 줄지만, 디코딩 비용이 생김 → 상황에 맞게 선택