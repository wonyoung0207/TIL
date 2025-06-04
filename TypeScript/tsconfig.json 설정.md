# tsconfig.json 설정 파일 

---

>

## tsconfig.json (루트)

- 프로젝트 전반을 아우르는 “레퍼런스” 역할만 함
- 직접 컴파일 옵션 없이, 어떤 하위 설정 파일들을 참조할지(`tsconfig.app.json`, `tsconfig.node.json`)만 나열

## tsconfig.app.json (애플리케이션 코드용)

- `src/**/*.ts`, `src/**/*.vue` 같은 **실제 앱 소스 파일을 컴파일·검사**하기 위한 설정
- `@/...` 별칭(alias) 정의, `.vue` 파일 모듈 선언(shims-vue.d.ts), Vite 전역 타입(`import.meta.env`) 및 Node 내장 모듈 타입을 포함

## tsconfig.node.json (Node 스크립트용)

- `vite.config.ts` 같은 **Node.js 환경**에서 실행되는 설정 파일만 타입 검사하기 위한 설정
- Node 내장 모듈(`path`, `url`) 타입만 로드하고, 실제 JavaScript 출력은 생성하지 않도록(`noEmit`) 구성



### 1. `compilerOptions`

- **`target`**
   출력할 JavaScript 버전을 지정. 보통 `ESNext`나 `ES2020` 등 최신 문법을 쓰기 위해 설정합니다.

- **`module`**
   모듈 시스템 형태를 지정. Vite + ES Modules 환경에서는 `"ESNext"` 또는 `"NodeNext"`를 사용합니다.

- **`moduleResolution`**
   모듈을 어떻게 찾아갈지(예: Node 스타일) 설정. 일반적으로 `"Node"` 또는 `"Bundler"`를 씁니다.

- **`strict`**
   엄격 모드(모든 엄격 검사 옵션 켜기). `true`로 두면 타입 안정성을 높여 줍니다.

- **`jsx`**
   JSX/TSX를 사용할 때 처리 방식. Vue 프로젝트라면 `"preserve"`로 남겨 두고 컴파일 시점에 Vue 컴파일러가 처리하도록 합니다.

- **`resolveJsonModule`**
   `.json` 파일을 모듈처럼 import할 수 있게 해 줍니다.

- **`esModuleInterop`**
   CommonJS–ESM 호환을 돕는 설정. 외부 라이브러리를 `import foo from "foo"` 형태로 끌어올 때 주로 사용합니다.

- **`skipLibCheck`**
   `node_modules` 하위 선언 파일(`.d.ts`) 검사를 건너뛰어 빌드 속도를 올립니다.

- **`lib`**
   프로젝트에서 참조할 런타임 API 집합. `["ESNext", "DOM"]`처럼 지정하면 최신 자바스크립트 기능과 DOM API를 모두 사용할 수 있습니다.

- **`baseUrl`**
   절대 경로(import 경로 별칭 기준)를 지정. 보통 `"."`(프로젝트 루트)로 설정합니다.

- **`paths`**
   별칭(alias)을 정의. 예를 들어

  ```json
  "paths": {
    "@/*": ["src/*"]
  }
  ```

  로 두면, `import Foo from "@/components/Foo.vue"`를 `src/components/Foo.vue`로 자동 해석해 줍니다.

- **`types`**
   타입 선언 패키지의 범위를 지정.

  - `"vite/client"`: Vite가 제공하는 `import.meta.env`, `import.meta.hot` 같은 전역 타입
  - `"node"`: Node.js 내장 모듈(`path`, `url` 등) 타입
     두 개를 동시에 쓸 때는 `["vite/client", "node"]`처럼 배열 형태로 나열합니다.

- **`tsBuildInfoFile`**
   빌드 캐시용 파일 위치. `Vite`가 리빌드를 빠르게 하기 위해 사용하는 정보가 저장됩니다.

------

### 2. `include`

```json
"include": [
  "src/**/*.ts",
  "src/**/*.tsx",
  "src/**/*.vue",
  "src/**/*.d.ts",
  "vite.config.ts"
]
```

- 이 배열에 들어간 패턴에 매칭되는 파일만 타입스크립트가 검사 대상으로 삼습니다.
  - `src/**/*.ts`, `src/**/*.vue`: 앱 소스 코드(.ts, .vue)를 포함
  - `src/**/*.d.ts`: `shims-vue.d.ts` 같은 선언 파일을 포함
  - `vite.config.ts`: Vite 설정 파일도 한 번에 타입 검사하려면 넣습니다.

------

### 3. `exclude`

```json
"exclude": ["node_modules", "dist"]
```

- 검사 대상에서 제외할 폴더나 파일을 지정합니다.
- 보통 `node_modules`와 빌드 결과물(`dist`)을 제외합니다.

------

### 4. 전체 예시

```json
{
  "compilerOptions": {
    "target": "ESNext",
    "module": "ESNext",
    "moduleResolution": "Node",
    "strict": true,
    "jsx": "preserve",
    "resolveJsonModule": true,
    "esModuleInterop": true,
    "skipLibCheck": true,
    "lib": ["ESNext", "DOM"],

    "baseUrl": ".",
    "paths": {
      "@/*": ["src/*"]
    },

    "types": ["vite/client", "node"],
    "tsBuildInfoFile": "./node_modules/.tmp/tsconfig.app.tsbuildinfo"
  },
  "include": [
    "src/**/*.ts",
    "src/**/*.tsx",
    "src/**/*.vue",
    "src/**/*.d.ts",
    "vite.config.ts"
  ],
  "exclude": [
    "node_modules",
    "dist"
  ]
}
```

------

### 요약

1. **`compilerOptions`**
   - 빌드 대상(JS 버전, 모듈 해석 방식 등)
   - 별칭(alias) 설정(`baseUrl` + `paths`)
   - Vite 전역 및 Node 타입 선언(`types`)
   - 엄격 모드 옵션들
2. **`include`**
   - `.ts`, `.vue`, `.d.ts`, `vite.config.ts` 등 검사할 파일 경로 패턴
3. **`exclude`**
   - `node_modules`, `dist` 등 검사에서 제외할 경로

이 설정 하나만 있으면, Vue 3 + Vite 프로젝트에서 **`@/…`** 별칭, **`.vue` import**(`shims-vue.d.ts` 포함), **`import.meta.env`**, **Node 내장 모듈(`path`, `url`)** 타입까지 모두 정상 동작합니다.