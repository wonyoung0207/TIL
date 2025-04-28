## vue의 Vee-Validate 라이브러리

---

> [참고 사이트1](https://velog.io/@pear/Vee-Validate-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%82%AC%EC%9A%A9%EB%B2%95)

## Vee-Validate

### 정의

- vue에서 제공하는 라이브러리고, 유효성검사에 사용된다. 

### 핵심 기능

1. ValidationProvider
2. ValidationObserver
3. extend
4. rules

### 이용방법

#### 1. VeeValidate 를 전역 사용하기 위해 import

- 전역에서 해당 컴포넌트를 자유롭게 사용하기 위해 main.js에 선언한다. 

  ```javascript
  import Vue from 'vue';
  import VeeValidate from 'vee-validate';
  import App from './src/App';
  import { router } from './src/router';
  import store from './src/store';
  
  Vue.config.productionTip = false;
  
  new Vue({
    render: h => h(App),
    router,
    store,
    VeeValidate
  }).$mount('#root');
  ```

#### 2. VeeValidate에서 기능 꺼내기 

- 라이브러리에서 제공하는 기능들 중 사용하고자 하는 기능을 import로 꺼내준다. 

  - 해당 기능들은 VeeValidate에서 거의 항상 사용하는 기능들이다. 

  ```javascript
  import { ValidationObserver, ValidationProvider, extend } from 'vee-validate';
  ```

#### 3. Validation Rule 중 사용하고자 하는 rule 꺼내오기 

- Vee-Validate는 기본적으로 제공하는 rule이 있다. 

  - `vee-validate/dist/rules`  경로에 있는 rule을 꺼내서 사용하면 된다. 

- 만약 사용자가 rule을 직접 만들고 싶다면 `extend` 를 사용해 만들면 된다. 

  ```javascript
  import {
    numeric,
    alpha_dash,
    alpha_spaces,
    required
  } from 'vee-validate/dist/rules';
  ```

#### 4. ValidationProvider 에 rule과 v-slot 설정

- ValidationProvider 에 rules 라는 이름으로 사용하고 싶은 rule을 넘겨주고,

  - ValidationProvider 에 errors 라는 이름의 v-slot을 생성한다. (provider 가 errors를 자동으로 생성해준다.)
  - Ex  : `v-slot="{errors}"`

- slot

  - 부모 컴포넌트에서 자식 컴포넌트에게 컨텐츠를 전달하거나 재사용 가능한 템플릿을 정의하는 데 사용한다. 
  - 컨텐츠를 전달하거나 템플릿을 정의할 수 있다. 

- errors 를 보여주고 싶은 태그 내부에 `{{ errors[0] }}` 의 형태로 사용한다. 

  - error는 기본적으로 배열 내부에 메세지를 설정하는대로 여러개 추가되거나 덮어쓰기 되는 구조이다. 
  - 따라서 디폴트인 [0] 을 명시해서 메시지를 불러온다. 

- 예시

  ```vue
  <ValidationProvider rules="alpha_dash" v-slot="{ errors }">
    <input v-model="value" type="text">
    <span>{{ errors[0] }}</span>
  </ValidationProvider>
  ```

- 여러가지 rule을 적용하고 싶다면 `|` 를 사용해서 연속적으로 작성해준다. 

  ```vue
  <ValidationProvider rules="alpha_dash|numeric|required" v-slot="{ errors }">
    <input v-model="value" type="text">
    <span>{{ errors[0] }}</span>
  </ValidationProvider>
  ```

#### 5. 새로운 rule 추가 방법

- extend 를 이용해 새로운 rule을 추가할 수 있다. 

  - odd 라는 이름으로 

  ```javascript
  import { extend } from 'vee-validate';
  
  extend('odd', value => {
    return value % 2 !== 0;
  });
  ```

#### 6. 에러 메시지 변경하기 

- 메시지 변경에는 크게 2가지 방법이 있다. 
  1. 기존 rule의 메시지 변경
  2. 파라미터로 원하는 값을 받아와 return 으로`{_field_}` 를 사용해 메시지 표시 

1. 기존 rule의 message 를 변경 

   ```javascript
   extend('required', {
     ...required,
     message: '필수 입력항목입니다.'
   });
   ```

2. 파라미터로 원하는 값 받아와 return 으로 메시지 표시 

   - 파라미터로 값을 넘겨주고 필드 이름을 가져와 에러 메시지에 표시 
   - `{_field_}` 는 ValidationProvider 에서 기본적으로 제공하는 기능으로 따로 명시하지 않아도 사용가능하다. 
   - ValidationProvider의 name 으로 설정한 값이 `{_field_}` 에 자동으로 매핑된다. 

   ```vue
   <!-- 최소 글자수를 검사 -->
   <validation-provider
        name="아이디"
        rules="required|alpha_dash|min:5|max:20"
        v-slot="{ errors, classes }"
    >
     <p class="validation-text">{{ errors[0] }}
     </p>
   </validation-provider>
   ```

   ```java
   // 최소 글자수를 검사 
   extend('min', {
     validate(value, { min }) {
       if (value.length >= min) {
         return true;
       }
       return '{_field_}는 {min} 글자 이하이어야 합니다.'; // 출력 : 아이디는 5글자 이하이여야 합니다. 
     },
     params: ['min']
   });
   
   // 한글, 영문, 숫자만 들어올 수 있도록 판별 
   extend('korAlphaNum', {
     validate: value => {
       let regex = /^[가-힣|aA-zZ|0-9]*$/.test(value);
       if (!regex) {
         return '올바른 한글, 영문, 숫자만 입력해주세요.';
       } else {
         return true;
       }
     }
   });
   
   extend('check_ip', {
       validate: value => {
           let regex = /^(?:\d{1,3}\.){3}\d{1,3}$/.test(value);
           if (!regex) {
             return dictionary.ko.names.connect_ip + '에 유효한 IP 주소 또는 도메인 이름을 입력하세요.';
           } else {
             return true;
           }
         }
     });
   
   extend('check_port', {
       validate: value => {
           // let regex = /^[1-9]\d*$/.test(value);
           let regex = /^([1-9]|[1-9]\d{1,3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/.test(value); // 0~65535 까지만 입력가능 
           if (!regex) {
             return dictionary.ko.names.connect_port + '에 유효한 port 번호를 입력해주세요.';
           } else {
             return true;
           }
         }
     });
   ```

#### 7. 매개변수 추가 

1. `<validation-provider>`에서 `check_position:1.5`와 같이 넘김
2. `params: ['max']`에 따라 VeeValidate는 문자열 `"1.5"`를 `max`로 파싱
3. 내부에서 `validate(value, { max })`가 호출됨
   1. 예: `validate('1.6', { max: '1.5' })`

| 개념                       | 설명                                                         |
| -------------------------- | ------------------------------------------------------------ |
| `params: ['max']`          | 커스텀 rule이 받을 **매개변수의 이름**을 선언                |
| `validate(value, { max })` | 첫 번째는 입력값, 두 번째는 `{ max: 넘긴값 }` 형태의 객체    |
| 사용 방법                  | `<validation-provider :rules="check_position:someValue">`로 넘김 |

```vue
<validation-provider
  name="position"
  :rules="`required|check_position:${max}`"
  v-slot="{ errors }"
>
```

```js
import { extend } from "vee-validate";
extend("range_decimal", {
  params: ['min', 'max'], // ✅ 파라미터 이름 정의
  validate(value, { min, max }) {
    const num = parseFloat(value);
    if (isNaN(num)) {
      return "숫자를 입력해주세요.";
    }

    if (num < parseFloat(min) || num > parseFloat(max)) {
      return `값은 ${min} ~ ${max} 사이여야 합니다.`;
    }

    return true;
  },
});
```

## 예시

```vue
<ValidationProvider name="tel" rules="numeric">
    <input type="text"  maxlength="4" @input="inputNumberFunction($event, 3)" v-model="userinfo.lastTel" autocomplete="off" id="input-last-tel" class="form-control fontHelp2 input-box-number-spinner-hide">
</ValidationProvider>

methods: {
    inputNumberFunction(a,b){
        // 숫자일때만
        var reg =/^[0-9]+$/;
        if(reg.test(a.data)){
        }
        else{
            if(b==1){
                this.userinfo.firstTel=this.userinfo.firstTel.slice(0, -1)
            }
            else if(b==2){
                this.userinfo.secondTel=this.userinfo.secondTel.slice(0, -1)
            }
            else{
                this.userinfo.lastTel=this.userinfo.lastTel.slice(0, -1)
            }

        }
    },
}
```

