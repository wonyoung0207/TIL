# tsconfig 별칭 vue 인식 문제해결

---

>

**문제**:

1. tsconfig.json 설정파일에서 paths에 `@` 경로 설정해줬는데도 `@/test.vue`  vue 파일 사용시 vscode가 파일 경로를 찾지 못함(화면은 나옴)
2. 즉, `tsconfig.app.json`에 `paths: { "@/*": ["src/*"] }`와 `.vue` 선언(`shims-vue.d.ts`)을 넣었어도, VSCode/TS 언어 서버가 해당 파일을 “프로젝트 구성”으로 인식하지 않으면 `@/componentTest.vue` 같은 경로와 `.vue` 파일 타입이 적용되지 않음.

**핵심**:

1. `tsconfig.app.json > include` 목록에 `vite.config.ts`를 추가하자, 에디터가 “이 폴더를 이 tsconfig로 전부 관리해야 한다”고 판단하여
2. `@/...` 별칭이 정상 매핑되고
3. `shims-vue.d.ts`로 `.vue` 파일을 모듈로 인식 되면서 오류 없이 인텔리센스가 동작하게 됨.

```json
{
  "compilerOptions": {
    "baseUrl": ".",
    "paths": { "@/*": ["src/*"] },
    "types": ["vite/client", "node"]
  },
  "include": [
    "src/**/*.ts",
    "src/**/*.vue",
    "src/**/*.d.ts"
    "vite.config.ts" // 얘 없으면 vue 컴포넌트 안잡힘 
  ]
}
```

