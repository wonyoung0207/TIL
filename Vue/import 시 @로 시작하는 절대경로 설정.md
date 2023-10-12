## vue import 시 @/ 로 시작하는 절대경로 설정하기 

---

>[참고 사이트1](https://devbirdfeet.tistory.com/105)
>
>[참고 사이트2](https://mikkeller.tistory.com/26)

### 필요 이유

- **상대경로**를 이용하면 파일의 위치가 변경되었을 경우 import 의 경로가 변경되 에러가 발생할 수 있다. 
  - 따라서 관리하기가 쉽지 않다. 
- 관리를 용이하기 위해 **'절대경로'** 를 사용하는것이 좋다. 
- `@` 를 붙여 `src/ ` 폴더부터 시작하는 것으로 설정할 수 있다. 
  - 설정은 jsconfig.json 파일을 통해 이루어진다. 

```js
import Demo from '../../demo/basic/Demo' // 상대경로
import Demo from '@/demo/basic/Demo'	 // 절대경로
```

### 사용방법

1. 프로젝트 최상단 루트에 **jsconfig.json** 이라는 파일을 만들어준다.

<img src="./images/jsconfig.json 파일 위치.png" width="300">

1. jsconfig.json 에 아래 내용을 붙여준다. 

   - 그럼 최상단 루트 내에서 @/붙여 절대경로를 자유로이 사용할 수 있다.

   ```json
   // 예시 1
   {
     "compilerOptions": {
       "baseUrl": ".",
       "paths": {
         "~/*": [
           "./*"
         ],
         "@/*": [
           "./src/*"
         ],
       }
     },
     "exclude": [
       "node_modules",
       "dist"
     ]
   }
   
   // 예시 2
   {
       "compilerOptions": {
           "baseUrl": ".",
           "paths": {
               "@/*": ["src/*"],
               "@core/*": ["src/@core/*"],
           }
       }
   }
   
   ```

   

