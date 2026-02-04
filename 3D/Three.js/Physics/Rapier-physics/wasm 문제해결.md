# Wasm 문제해결 

---

>

## 문제발생

- vue3, vite 환경에서 rapier 를 사용하려고 하니 Wasm 을 인식할 수 없다는 에러 문구가 발생했다. 

- 알아보니 rapier 는 내부적으로 Wasm 형식을 사용하고 있고, Vite 는 현제 버전에서 Wasm 을 지원하지 않아 에러가 발생하는 것이였다. 

  >  `"ESM integration proposal for Wasm" is not supported currently. Use vite-plugin-wasm or other community plugins…`

##  Wasm(.wasm) 이란? 

- **Wasm = WebAssembly** 의 줄임말
- 브라우저에서 JS 말고도 **C, C++, Rust 같은 언어로 짠 코드**를 **아주 빠른 이진 형식(.wasm)** 으로 돌릴 수 있게 해주는 기술이다. 
  - JS: 사람이 읽기 좋은 스크립트
  - Wasm: **머신이 읽기 좋은 이진 코드** (속도, 성능 좋게)
- Rapier 같은 물리 엔진이 **복잡한 수학 계산**을 빠르게 돌려야 함.
  - 그래서 내부 핵심 로직을 Wasm(.wasm)으로 만들어놓고 JS에서 그걸 불러다가 쓰는 구조라고 보면 됨.

---

## 에러 발생 이유 

- **`.wasm` 파일을 ES Module(ESM)처럼 바로 import** 하는 건 Vite에서 아직 기본 지원 안 한다.
- 플러그인(vite-plugin-wasm)이나 `?init` / `?url` 같은 방식을 써라.”

##### 문제의 핵심:

1. Rapier 같은 라이브러리가 내부에서 `.wasm` 파일을 **ESM 스타일**로 다루고 있고

   ```
   import module from './rapier_wasm_bg.wasm'; // 이런 식의 패턴
   ```

2. 근데 Vite는 **“Wasm을 ESM처럼 직접 import하는 실험 스펙”**(ESM integration proposal)을 아직 기본 기능으로 켜두지 않았음

3. 그래서 Vite가 “이건 아직 지원 안 해, 대신 `vite-plugin-wasm` 같은 걸 써라” 라고 하면서 에러를 뱉는 것

------

## 해결방법

- **`vite-plugin-wasm`** 와 **`vite-plugin-top-level-await`** 플러그인 사용으로 Wasm 인식하도록 함 
  - 플러그인 사용시 **Wasm 파일 로드 → 초기화 → JS 코드에서 사용** 흐름을 Vite가 문제없이 처리할 수 있게됨 

- **`vite-plugin-wasm`**
  - Vite가 `.wasm` 파일을 제대로 모듈로 인식하고 빌드/번들할 수 있게 도와줌
  - 즉, “Wasm + ESM 조합”을 처리해주는 역할
- **`vite-plugin-top-level-await`**
  - 일부 브라우저/환경에서 **파일 최상단에서 `await`** 쓰는 걸 제대로 지원하도록 도와줌
  - Wasm 초기화 코드가 `await import()` 같은 패턴을 쓸 때 브라우저 호환성을 맞춰주는 용도

------

## 정리

- **Wasm(.wasm)** 
  - 브라우저에서 빠른 실행을 위해 컴파일된 이진 코드 (Rapier 같은 물리엔진이 내부에서 사용)
- 에러 이유 
  - Vite가 **“wasm을 ES 모듈처럼 직접 import”** 하는 방식을 기본 지원 안 해서
- 해결
  - `vite-plugin-wasm` 같은 플러그인으로 “Wasm + ESM 조합”을 처리하게 해주거나, `.wasm?init`, `.wasm?url` 방식으로 import 방식을 바꾸는 것.

