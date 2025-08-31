## Geometry의 구조 

### 1. **BufferGeometry란?**

- Three.js에서 3D 도형을 정의하는 가장 기본적이고 효율적인 **지오메트리 구조체**.
- 모든 도형(큐브, 평면, 커스텀 도형 등)은 내부적으로 `BufferGeometry`를 기반으로 만들어진다.

### 2. **Float32Array란?**

- 정점(vertex) 데이터를 저장하는 **숫자 배열**.
- WebGL은 부동소수점 32비트 배열만 처리할 수 있으므로 `Float32Array`를 사용함.

##### 예시:

```js
const vertices = new Float32Array([
  0, 0, 0,   // 정점 1
  1, 0, 0,   // 정점 2
  0, 1, 0    // 정점 3
])
```

### 3. **BufferAttribute란?**

- `Float32Array` 데이터를 몇 개씩 묶어서 의미 있게 해석하는 **속성 래퍼**.
- 즉, "이 배열은 x,y,z 세 개씩 묶어서 정점 위치로 쓸 거야"라고 알려주는 역할.
  - 2D는 2로 설정하면 됨 

##### 예시:

```js
geometry.setAttribute('position', new THREE.BufferAttribute(vertices, 3))
// 3 → x, y, z 묶어서 하나의 정점으로 처리
```

### 4. **setIndex()로 삼각형 구성**

- 정점들을 어떻게 연결해서 삼각형으로 만들지 정의하는 **인덱스 배열**.
- 배열의 어떤 정점을 연결할지 결정 
  - 예시) ( 0, 1, 2 ) + ( 2, 1, 3) => 첫 지점의 정점과 두번째 지점 정점을 연결  

```js
const indices = new Uint16Array([
    0, 1, 2,
    2, 1, 3
])
geometry.setIndex(new THREE.BufferAttribute(indices, 1))
```

### 5. **결과 구조 요약**

```js
BufferGeometry
├─ attributes
│   ├─ position → Float32Array (x, y, z) * N
│   ├─ normal   → Float32Array (x, y, z) * N (선택)
│   └─ uv       → Float32Array (u, v) * N (선택)
└─ index        → Uint16Array (정점 인덱스 나열)
```

## 정리

1. **Float32Array** → 숫자 배열 (정점 데이터 저장)
2. **BufferAttribute** → 몇 개씩 끊어서 의미 부여 (ex: 3 = x, y, z)
3. **geometry.setAttribute()** → 위치/색상/노멀 등 연결
4. **geometry.setIndex()** → 정점을 삼각형으로 연결