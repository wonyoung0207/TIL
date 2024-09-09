# npm capacitor 라이브러리

---

>[참고 사이트1](https://medium.com/@lyslg1018/ionic-capacitor-ae9f7e691e70)

## capacitor 

### 정의

1. 한가지 코드로 Ios, Android, Web에서 작동하는 웹 앱을 쉽게 build 할 수 있도록 해주는 크로스 플랫폼 앱
2. 일관된 웹 중심의 API 세트를 제공하여 앱이 가능한 한 웹 표준에 가깝게 유지하면서 이를 지원하는 플랫폼에서 풍부한 기본 장치 기능에 액세스 가능하도록 해준다.

### capacitor package 설치

```bash
npm install --save @capacitor/cli @capacitor/core

# 안드로이드스튜디오 사용하는 경우 
npm install @capacitor/android
```

### Capacitor 이용  프로젝트 빌드 순서 

```bash
# 1. Capacitor 프로젝트 초기화 (capacitor.config.js 파일 생성)
npx cap init

# 2. Android 플랫폼 추가
npx cap add android 

# 3. 프로젝트 빌드 
npm run build

# 4. Capacitor 동기화 ( build 내용을 capacitor 이용해 동기화 하는 과정 )
npx cap sync

# 5. Android 스튜디오에서 프로젝트 열기
npx cap open android

# 6. 안드로이드 스튜디오 빌드
'project/android' 경로에서 `./gradlew build ` 로 빌드 

# 7. 빌드 파일 확인 
`/android/app/build/outputs/apk` 위치에서 debug폴더나 release폴더 확인
```

### capacitor.config.js 파일 구조

```js
// capacitor.config.js
{
  "appId": "com.example.app",
  "appName": "appName",
  "webDir": "dist",
  "bundledWebRuntime": true,
  "server": {
    "allowNavigation": [
      백엔드IP
    ],
    "cleartext": true
  }
}
```

