# vue 의 보간법 {{ }}(머스테쉬)

---

>[머스테쉬 (보간법)](https://codelabs-vue.web.app/s02)
>
>[vue 의 directive](https://poew.tistory.com/782)
>
>[디렉티브](https://velog.io/@falling_star3/Vue.js-%EB%94%94%EB%A0%89%ED%8B%B0%EB%B8%8CDirectives%EB%9E%80)

## 보간법 ( Interpolations )

### 정의 

1. 템플릿 문법이라고 불리는 것으로, `{{ }}` 의 형태로 사용된다. 
2. 이렇게 중괄호를 두겹(`Double Curly Brace` ) 으로 사용하는 방법을 `머스테쉬 또는 보간법`이라고 부른다. 
   1. 다른 언어에서는 머스테쉬(Mustache), vue에서는 보간법으로 불린다. 

### 특징 

1. javascript의 간단한 문법을 `{{ }}` 안에서 사용할 수 있다. 

   ```html
   <div id="app">
     <h1>{{ new Date() }}</h1>
     <h1>{{ (Math.floor(Math.random()*6)%2 == 1) ? '홀' : '짝' }}</h1>
     <h1>{{ ('내'+'힘들다').split('').reverse().join('') + '!' }}</h1>
   </div>
   ```

2. 바인딩 

   1. 보간법으로 사용된 변수는 바인딩이 적용된다. 
   2. 즉, 보간법 안의 변수가 어딘가에서 변경된다면 자동으로 vue가 인식하고 값을 변경해준다. 

3. 보간법안에 HTML 태그 사용 불가 

   1.  [XSS 공격 문제 ](https://ko.wikipedia.org/wiki/사이트_간_스크립팅) 를 방지하기 위해 보간법 문법은 모든 문자열을 입력한 그대로 출력하는 것을 기본동작으로 한다. 
   2.  즉, `<div> 보간법 </div>` 을 사용하면 div태그가 아닌 문자 `<div>` 로 출력된다. 
   3.  해당 문제를 해결하기 위해 **디렉티브(Directive)** 를 사용할 수 있다. 

## 디렉티브 ( Directive )

### 정의

1. Vue.js에서 사용할 수 있는 문법으로, html 태그의 속성(Attribute)에 v-라는 접두를 가진 특수한 속성들을 사용하는것을 뜻한다. 
2. 사용자가 직접디렉티브를 만들수도 있다. 

### 디렉티브 구조

<img src="./images/디렉티브 구조.png" width="500">

1. **디렉티브(directives)** : `v-` 접두사가 있는 특수 속성으로 디렉티브의 값(value)이 변경될 때 특정 효과를 반응적으로 DOM에 적용하는 것을 말한다.
2. **전달인자(Argument)** : 일부 디렉티브는 디렉티브명 뒤에 콜론(:)으로 표기되는 전달인자를 가질 수 있다. 예를 들어, `v-bind` 디렉티브는 반응적으로 HTML 속성을 갱신하는 데 사용한다.
3. **수식어(Modifiers)** : 수식어는 점(.)으로 표시되는 특수 접미사로 디렉티브가 특별한 방식으로 바인딩되어야 함을 나타낸다.

### 기본 제공 디렉티브

| 디렉티브                  | 기능                                                         |
| ------------------------- | ------------------------------------------------------------ |
| v-text                    | 속성값의 내용을 그대로 text로 출력                           |
| v-html                    | 속성값의 내용을 html적용해서 출력                            |
| v-show                    | css의 display속성으로 보였다 숨겼다 기능 (jQuery이 show/hide메소드에 대응) |
| v-if , v-else , v-else-if | 조건부 렌더링, 조건이 해당하는 DOM 요소만 렌더링             |
| v-for                     | 리스트 렌더링, interable한 객체를 반복해서 랜더링할 때 사용  |
| v-on                      | 이벤트 핸들링, click과 같은 이벤트를 설정할 때 사용 (약어로 @사용 가능) |
| v-bind                    | 데이터 바인딩, html 속성에 인스턴스 속성 및 메소드 사용 시 (약어로 :사용 가능) |
| v-model                   | 폼 입력 바인딩, 폼 태그의 입력값과 변수에 양방향 바인딩을 설정 |
| v-pre                     | 내부 값에 템플릿 문법을 적용하지 않을 때 사용 (디렉티브, 컴포넌트 포함) |
| v-clock                   | 인스턴스가 템플릿 문법을 적용할때 까지 DOM 속성에 남아있음 (로딩 구현시) |
| v-once                    | 내부에 템플릿 문법이 한번만 적용 (반응형을 해제)             |

### 예시 

```vue
<head>
  <title>Vue Template - Directives</title>
</head>
<body>
  <div id="app">
    <a v-if="flag">두잇 Vue.js</a>
    <ul>
      <li v-for="system in systems">{{ system }}</li>
    </ul>
    <p v-show="flag">두잇 Vue.js</p>
    <h5 v-bind:id="uid">뷰 입문서</h5>
    <button v-on:click="popupAlert()">경고 창 버튼</button>
  </div>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <script>
    new Vue({
      el: '#app',
      data: {
        flag: true,
        systems: ['android', 'ios', 'window'],
        uid: 10
      },
      methods: {
        popupAlert: function() {
          return alert('경고 창 표시')
        }
      }
    })
  </script>
</body>
</html>
```