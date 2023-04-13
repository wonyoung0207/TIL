# Vue js2 기초 익히기 기본강좌

---

>[코지코더 vuejs2 기초강좌](https://www.youtube.com/watch?v=gZBKGn0wQXU&list=PLB7CpjPWqHOtYP7P_0Ls9XNed0NLvmkAh&index=1)

## 01. 뷰 인스턴스 생성 

- new Vue({ }) 의 형태로 인스턴스를 생성하고 태그에 연결한다. 

```vue
<body>
    헬로 월드 1
    <div id="app">
        <!-- 뷰 인스턴스 연결하는 곳  -->
        {{ name }}    
    </div>

    <script>
        new Vue({
            // 뷰 인스턴스 생성
            el: "#app", // 연결할 태그 이름 
            data : { // 뷰 인스턴스에서 사용할 데이터 
                name : "wonyoung"
            }
        })
    </script>
</body>
```

## 02 뷰 인스턴스에서 Method 사용

- 키워드가 ' methods ' 라는것에 유의하기 
- 자바스크립트와 동일한 방법으로 함수 사용해줄 수 있다. 
- this라는 곳에 data 에 들어가있는 값이 모두 들어가있기 때문에 바로 사용할 수 있다. 

```vue
<body>
    <div id="app">
        <!-- 뷰 인스턴스 연결하는 곳  -->
        {{ nextYear('hello') }}    
    </div>

    <script>
        new Vue({// 뷰 인스턴스 생성
            el: "#app", // 연결할 태그 이름 
            data : { // 뷰 인스턴스에서 사용할 데이터 
                person : {
                    name : 'wonyoung',
                    age : 28
                }
            },
            methods : { // method 가 아니라 methods 이다. 
                nextYear( a ){ // 자바스크립트처럼 사용하면 됨 
                    // vue가 this라는 곳에 data 에 있는 모든 값들을 넣어준다. 
                    return a + '! ' + this.person.name + '는 내년에 ' + (this.person.age + 1) + '살 입니다. ' ;
                },
                otherMethod : function(){// 이렇게 function 을 써서 메소드 구현할 수도 있다. 하지만 위에서 사용한게 더 간단함 
                    this.nextYear();
                } 
            }
        })
    </script>
</body>
```

## 03 데이터 바인딩

- " v-bin : " 를 이용해서 데이터를 바인딩 할 수 있다. 
  - 여기서 "v-bind" 를 생략하고 " : " 만 써서 사용할 수도 있다.

```vue
<body>
    <div id="app">
        <!-- <input v-bind:type="type" v-bind:value="inputData"> -->
        <!-- vue에서 지원하는 기능으로인해 v-bind 라는 문구를 생략하고 : 만 써서 이용가능  -->
        <input :type="type" :value="inputData">
        <a :href="myGithubConnection('wonyoung0207')"> 내 깃주소 연결 
    </div>

    <script>
        new Vue({
            el: "#app", 
            data : {
                inputData : '값을 넣어주세요. ',// 데이터 바인딩 시 사용할 변수이름과 값
                type : 'text', // 데이터 바인딩
                github : 'https://github.com/',
            },
            methods : { 
                myGithubConnection(myGithubAdd){
                    return this.github + myGithubAdd;
                }
            }
        })
    </script>
</body>
```

## 04. 이벤트 바인딩 

- " v-on : " 을 사용해서 이벤트를 바인딩 시킨다. 

  - 이때 **이벤트 수식어**기능이 있어 preventDefault() 같은 이벤트 메소드를 쉽게 사용할 수 있다. 
  - "v-on : " 을 **@ 로 대체**해서 사용할 수 있다. 
  - **태그안에서 메소드 호출시 ' ( ) ' 를 붙이지 않아도 된다.** 

- **이벤트 수식어 종류**

  - `.stop` : 이벤트 전파 중지 

    ```html
    <a @click.stop="doThis"></a>
    ```

  - `.prevent` : submit 이벤트가 더이상 페이지 리로드 하지 않게함 

    ```html
    <form @submit.prevent="onSubmit"></form>
    ```

  - `.self` 

  - `.capture` 

  - `.once`

  - `.passive`

- [**입력키 수식어** 종류](https://ko.vuejs.org/guide/essentials/event-handling.html#event-modifiers)

  - 키보드 이벤트를 수신할 때 , 특정키를 확인해야하는 경우가 있다. keyup, keydown 같은 이벤트를 쉽게 이용하도록 키 수식어를 지원한다. 

  - `.enter`

    ```html
    <!-- Alt + Enter -->
    <input @keyup.alt.enter="clear" />
    <!-- Ctrl + Click -->
    <div @click.ctrl="doSomething">시작하기</div>
    ```

  - `.tab`

  - `.delete` ("Delete" 및 "Backspace" 키 모두 캡처)

  - `.esc`

  - `.space`

  - `.up`

  - `.down`

  - `.left`

  - `.right`

  - `.ctrl`

  - `.alt`

  - `.shift`

  - `.meta`

```vue
<body>
    <div id="app">
        {{ year }}
        <br>
        <button v-on:click="addYear"> 날짜 더하기 </button>
        <button v-on:click="minusYear"> 날짜 빼기 </button>
        <br>

        <form v-on:submit.prevent="submit">
            <input type="text">
            <!-- submit 의 특징은 데이터를 보내고나면 화면을 리로드 시킨다는 것이다. 따라서 preventDefault 를 false로 해줘야한다. -->
            <!-- vue에서는 .prevent 라는 이벤트 수식어를 통해 더 쉽게 사용할 수 있다.  -->
            <button type="submit"> submit </button>
        </form>
    </div>

    <script>
        new Vue({
            el: "#app", 
            data : {
                year : '2023',
            },
            methods : { 
                addYear(){
                    this.year++;
                },
                minusYear(){
                    this.year--;
                },
                submit(){
                    alert('123');
                },
            }
        })
    </script>
</body>
```

## 05 양방향 바인딩

- 양방향으로 데이터 바인딩을 할 떄 양방향 바인딩을 사용한다. 
  - form 에서 많이 사용된다. 
- 양방향 데이터 바인딩이란, 메모리의 데이터가 변경되면 **화면에 적용**되고, 화면의 데이터가 변경되어도 **메모리에 있는 데이터를 변경**하는것이다. 즉, **화면과 메모리 둘다에서 데이터의 변화가 일어나는 것**을 말한다. 
  - 예를들어 **단방향**은 데이터가 변경되었을 때 화면에 해당 내용이 적용되는것을 뜻하는 반면, 양방향은 화면의 데이터 변화를 통해서 메모리에 있는 데이터또한 변경되는것을 뜻한다. 
- **" v-model " 키워드**를 사용하면 양방향으로 데이터가 연결되어 처리된다. 

```vue
<body>
    <div id="app">
        <form v-on:submit.prevent="submit">
            <!-- 노가다 양방향 데이터 바인딩 ( 이벤트이용 ) -->
            <input type="text" :value="text" @keyup="updateText"><br>
            <!-- <input type="text" :value="text" v-on:keyup="updateText"><br> 위와 같은 결과냄 -->
            {{ text }}<br>

            <!-- 더 쉬운 양방향 데이터 바인딩  (v-model 키워드 사용 )-->
            <input type="text" v-model="text"><br>
        </form>
    </div>

    <script>
        new Vue({
            el: "#app", 
            data : {
                text : '',
            },
            methods : { 
                updateText(event) {
                    this.text = event.target.value; // event 발생시 생성되는 객체로, event 안에는 발생한 이벤트와 관련 정보들이 들어있다. 
                },
            }
        })
    </script>
</body>
```

## 06 Computed 속성

- [Vuejs 공식_computed 캐싱](https://ko.vuejs.org/guide/essentials/computed.html)
- Methods 와 Computed 의 차이점 
  1. 캐싱 차이 
     - 캐싱
       - 한 번 연산해놓은 값을 반복적으로 출력할 때 다시 한번 연산하지 않도록 돕는 기능 
       - computed 에서 캐싱기능을 사용한다. 
     - methods 
       - 처음 new Vue를 호출했을때 실행되는것이 아니라 여러번 호출시에 여러번 실행되면서 값을 반환한다. 
       - 따라서 호출시에 메소드 안에있는 **코드들을 중복으로 실행**해야함 
     - computed
       - 처음 new Vue를 했을 떄 값이 **한번** 생성되어 호출시에 해당 값을 그냥 반환만 하는 형태 
       - 따라서 맨처음 vue 생성시에 값을 이용해 먼저 실행해놓고 값을 반환한다. 
  2. 호출의 차이 
     - methods 
       - 메소드를 호출하기 위해서는 ' ( ) ' 를 붙여줘야 한다. 안붙이면 변수로 인식하기 떄문에 
     - computed
       - ' ( ) ' 를 붙이지 않고 변수처럼 선언할 수 있다. 
- 사용이유 
  1. 메소드보다 빠르다. 
  2. 데이터의 바인딩으로 인해 **computed 안에서 사용한 값이 변경**된다면 computed 를 사용한 부분을 **자동으로 최신 데이터로 업데이트**해줌 

```vue
<body>
    <div id="app">
        <!-- split하면 한글자씩 arr로 저장함. join하면 배열을 이어줌  -->
        노가다 코드 : {{ msg.split('').reverse().join('') }} 
        <br>
        <!-- 메소드 사용시 뒤에 () 를 붙여줘야 함  -->
        methods 이용 : {{ reverseMessage1() }}
        <br>
        <!-- 메소드와 다르게 변수처럼 사용함.  -->
        computed 이용 : {{ reverseMessage2 }}
        <br>
        <button @click="changeMsg"> text 변경 </button>
    </div>

    <script>
        new Vue({
            el: "#app", 
            data : {
                msg : '내이름은 안원영 입니다. ',
            },
            methods : {  // 호출시 실행되며 중복으로 호출해도 함수안의 코드를 계속해서 호출함 
                reverseMessage1(){
                    return this.msg.split('').reverse().join('');
                },
                changeMsg(){
                    this.msg = '텍스트 변경';
                }
            },
            computed : { // 메소드와는 다르게 vue가 생성될 때 한번 실행되고 그 이후에는 값을 가지고있어 함수를 실행하는 것이 아닌 바로 값을 리턴 
                // 만약 computed 안에서 사용된 vue data의 값이 변경되면 , 데이타 바인딩으로 인해 사용된 곳의 데이터들도 전부 변경된다. 
                reverseMessage2(){
                    return this.msg.split('').reverse().join('');
                }
            }
        })
    </script>
</body>
```

## 07. watch 속성

- 데이터를 감시하고 싶을때 사용한다. 
- 형태
  - 감시데이터이름(newVal , oldVal ) 
  - newVal : 변경되서 새로 바뀐 데이터 값
  - oldVal : 변경전 데이터 값 

```vue
<body>
    <div id="app">
        computed 이용 : {{ reverseMessage2 }}
        <br>
        <button @click="changeMsg"> text 변경 </button>
        <br>
        {{ updateData }}
    </div>
    <script>
        new Vue({
            el: "#app", 
            data : {
                msg : '내이름은 안원영 입니다. ',
                updateData : '업데이트 전 ',
            },
            methods : {  // 호출시 실행되며 중복으로 호출해도 함수안의 코드를 계속해서 호출함 
                changeMsg(){
                    this.msg = '텍스트 변경';
                }
            },
            computed : {               
                reverseMessage2(){
                    return this.msg.split('').reverse().join('');
                }
            },
            watch : { // 어떤 데이터를 감시할 것인지 체크 후 해당 데이터가 변경되면 실행됨 
                msg(newVal, oldVal) { // newVal은 새로 바뀐 데이터의 값, oldVal 은 바뀌기 전의 값이 들어간다. 
                    this.updateData = '업데이트 후 ';
                    console.log(newVal, oldVal);
                }
            }
        })
    </script>
</body>
```

## 08. 클래스 & 스타일 바인딩

- 태그의 스타일에 바인딩을 사용할 수 있다. 
  - 클래스를 2개 적용시키기 위해서는 ' , ' 를 사용한다.
  - 클래스 바인딩시 ' :  ' 를 class 앞에 붙여줘야 한다.
  - 클래스 이름에 ' - ' 가 있다면 ' ' 를 사용해 클래스 이름을 묶어줘야 한다.

```vue
<head>
	<style>
        /* 클래스별 스타일 지정  */
        .red {
            color : red;
        }
        .font-bold {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- 클래스를 2개 적용시키기 위해서는 ' , ' 를 사용한다.  -->
        <!-- 클래스 바인딩시 ' :  ' 를 class 앞에 붙여줘야 한다.  -->
        <!-- 클래스 이름에 ' - ' 가 있다면 ' ' 를 사용해 클래스 이름을 묶어줘야 한다. -->
        <div :class="{ red : isRed, 'font-bold' : isBold }"> Color Text Test </div>
        <button @click="changeText"> 클래스 이용 폰트 스타일 변경하기 </button>
        <br>
        <!-- 태그에 있는 style속성으로 바인딩해서 변경하기  -->
        <div :style="{ color : color, fontSize : size}"> Color and size Change </div>
    </div>
    <script>
        new Vue({
            el: "#app", 
            data : {
                isRed : false, // 클래스에 false를 적용하면 클래스이름으로 지정되지 않는다. 
                isBold : false,
                color : 'red',
                size : '30px',
            },
            methods : {  
                changeText(){ // 해당 메소드 호출시 클래스의 적용을 토글시킬 수 있다. 
                    this.isBold = !this.isBold;
                    this.isRed = !this.isRed;
                }
            },
        })
    </script>
</body>
```

## 09 v-if 와 v-show 이용

- v-if 와 v-else-if , v-else

  - **조건문**에 사용된다. 
  - 조건에따라 설정한 태그 자체가 렌더링 될것인지 안될것인지 결정할 수 있다. 
  - 따라서 false면 태그를 렌더링하지 않아 표시되지 않는다. 

- **template 태그** 

  - **여러개 태그**에 동시에 v-if 적용하고 싶을 경우 

  - template 태그 예제 

    ```vue
    <template v-if="nummber === 1">
    	<div> 1 </div>
    	<div> 2 </div>
    	<div> 3 </div>
    </template>
    ```

- v-if 와 v-show 차이점 

  - 공통점
    - 둘다 화면에 표시 or 표시x 할때 이용한다. 
  - 차이점
    - **v-if**는 조건에 따라 **태그 자체가 렌더링**되어 false면 아예 태그자체를 페이지에 불러오지 않는다(**렌더링 x**). 
    - **v-show** 는 **항상 렌더링** 되어 태그가 표시되지만 show의 값이 false라면 **' display : none ' 을 이용**해 해당 태그가 **화면에 표시되지 않도록 한다**. 
  - 따라서 변화가 많은 경우 v-show를 사용하고, 런타임시 조건이 바뀌지않으면  v-if의 사용을 권장한다. 

```vue
<body>
    <div id="app">
        <button @click="toggle"> Toggle </button>
        <!-- 간단한 if문을 통해 조건문의 결과가 true이면 태그 렌더링 o , false이면 태그 자체를 렌더링하지 않음   -->
        <div v-if="show"> v-if 이용 Show Data </div><br>
        <!-- v-show는 v-if와 다르게 무조건 렌더링 되기 때문에 html파일에 표시된다. 하지만 false일때 'display : none' 을 적용해 태그를 보이지 않게 만드는 것 뿐이다. -->
        <div v-show="show"> v-show 이용 Show Data </div>
        <br>

        <button @click="increase"> add nummber </button>
        <!-- template 태그를 이용해 여러 태그를 묶을 수 있다.  -->
        <template v-if="nummber === 1">
            <div>1</div>
            <div>1</div>
            <div>1</div>
        </template>
        <div v-else-if="nummber > 1 && nummber <= 3">
            <div>1~3 까지 </div>
        </div>        
        <div v-else>
            3초과
        </div>
        <br>
    </div>
    <script>
        new Vue({
            el: "#app", 
            data : {
                show : true,
                nummber : 1,
            },
            methods : {  
                toggle(){
                    this.show = !this.show;
                },
                increase(){b.  ., 
                    this.nummber++;
                }
            },
        })
    </script>
</body>
```

## 10 v-for  이용

- 리스트 렌더링에 사용된다. 
- in-place-patch 로인해 **key 를 바인딩** 해주는것이 좋다. 
- 형태
  - v-for="item in items" 
  - item은 별칭, items는 배열 
  - :key = "item + '-' + item.name"
    - in-place-patch 전략때문에 key를 사용해줘야한다. 

```vue
<body>
    <div id="app">
        <div>
            {{ people[0].name }} {{ people[0].age}}
        </div>
        <div>
            {{ people[1].name }} {{ people[1].age}}
        </div>
        <div>
            {{ people[2].name }} {{ people[2].age}}
        </div>
        <br><hr>
        <!-- p of people 로 써도 됨  -->
        <!-- 이렇게 반복문 하면 고유 key를 설정해주는 것이 좋다.  -->
        <!-- (p, index) in peple  을 사용해서 index도 사용할 수 있다.  -->
        <div v-for="p in people" :key="p.id + ' : ' + name">
            {{ p.name }} {{ p.age }}
        </div>
    </div>
    <script>
        new Vue({
            el: "#app", 
            data : {
                people : [
                    { id : 1, name : 'a', age : 11},
                    { id : 2, name : 'b', age : 12},
                    { id : 3, name : 'c', age : 13},
                ]
            },
        })
    </script>
</body>
```

## 11 여러개의 Vue 인스턴스 생성 

- Vue객체를 변수에 넣어서 다른 뷰에서 컨트롤 할 수 있다. 
- el 을 통해 **Vue를 연결한 태그 안에서만 해당 Vue객체에 구현한 methods 를 사용**할 수있다. 
  - 만약 app 안에 있는 methods 를 사용하고 싶다면 ' div의 id=app ' 으로 되어있는 태그 안에서만 사용할 수 있다. 

```vue
<div id="app">
    {{ name }}
    <!-- 해당 버튼이 app 태그 안에 있어야 vue 기능을 사용할 수 있다.  -->
    <button @click="updateApp2"> change Text </button>
</div>
<!-- vue 객체를 연결시키지 않으면 vue 기능을 사용하지 못한다.  -->
<div id="app1">
    {{ name }}
</div>
<div id="app2">
    {{ name }}
</div>
<script>
    const app = new Vue({
        el: "#app", 
        data : {
            name : 'wony1',
        },
        methods : {
            updateApp2() {// this는 vue 자체를 의미한다. 
                console.log('a')
                this.name = 'wony1 Update';
                app2.name = 'wony2 update';
            },
            changeText(){
                this.name = 'a';
            }
        }
    })
    const app2 = new Vue({
        el: "#app2", 
        data : {
            name : 'wony2',
        },
    })
</script>
</body>
```

## 12 Vue 컴포넌트 

- 컴포넌트 
  - 사용자 지정 엘리먼트(태그)로,  태그의 명칭을 내가 정해줄 수 있다. 
    - HTML Element를 확장하고 재사용 가능한 형태로 구현하는 것
    - vue 컴포넌트는 하나의 Vue 인스턴스로 본다. 

  - 따라서 무조건 Vue인스턴스 안에서 사용할 수 있는데, 전역/지역 으로 선언하는 것에 따라 다르게 동작하게 된다. 

- 컴포넌트 등록 범위
  - Vue.js 에서는 '전역 / 지역' 으로 컴포넌트의 범위를 나눌 수 있다. 
  - 설계 부분에서 컴포넌트의 범위를 지정해주는것이 중요 포인트이다. 

- 컴포넌트 전역변수
  - 전역등록을하면 안좋은 이유가 컴포넌트를 사용하지 않더라도 최종 빌드에 들어가있어 사용자가 내려받아야할 자바스크립트의 양이 불필요하게 커지게 된다. 

- 컴포넌트 데이터 전달 
  - 컴포넌트 간 데이터를 전달해야 하는 경우가 있는데 부모에서 자식으로 는 `props`를 사용하고, 반대로 자식에서 부모로 전달할 때는 `events($emit)`를 사용한다. 

1. Component 전역변수로 사용 

   ```vue
   <body>
       <div id="app">
           {{ name }}
           <my-buttom> </my-buttom>
       </div>
       <script>
           Vue.component('hello-world',{
               template : '<div> hello world!! </div>'
           })
           Vue.component('my-buttom', {
               // 백틱을 사용하는 이유는 한줄로 사용할 떄는 '' 로 되지만 여러줄일 경우 ``(백틱) 으로 묶어줘야한다. 
               // 또한 Vue.js 2버전에서는 template의 내용이 하나의 태그로 묶여있어야 에러가 나지 않는다. 
               template : `
               <div>
                   {{name}} <br>
                   <button @click="changeText" > change </button> 
                   <hello-world> </hello-world>
               </div>
               `// hello-world 라는 컴포넌트를 안에서 사용할 수 있다.
               ,
               // vue 인스턴스의 data 처럼 object 형식으로 사용하면 안된다. 
               // 왜냐하면 data를 참조형태로 가져오기 때문에 데이터 변환시 다른곳에도 영향을 끼치게된다.
               // 따라서 함수 형태로 작성해주는것이 좋다. 
               // data : {
               //     name : 'wony',
               // },
               data() {
                   return {
                       name : 'wony'
                   }
               },
               methods : {
                   changeText(){
                       this.name = 'Update Text complate!!';
                   }
               }
           })
           const app = new Vue({
               el: "#app", 
               data : {
                   name : 'first Text',
               },
               methods : {
               }
           })
       </script>
   </body>
   ```
   
2. Component 지역변수로 사용 

   ```vue
   <body>
       <div id="app">
           <my-button> </my-button>
       </div>
   
       <script>
           // 컴포넌트 전역 설정
           Vue.component('hello-world',{
               template : '<div> hello world!! </div>'
           })
           // 컴포넌트 지역 설정 
           const myButton = {
               template : `
               <div>
                   {{name}} <br>
                   <button @click="changeText" > change </button> 
                   <hello-world> </hello-world> 
               </div>
               `
               ,
               data() {
                   return {
                       name : 'wony'
                   }
               },
               methods : {
                   changeText(){
                       this.name = 'Update Text complate!!';
                   }
               }
           };
   
           const app = new Vue({ // app이라는 클래스 태그 안에서만 my-button 컴포넌트를 사용할 수 있게됨 
               el: "#app", 
               component : { // 컴포넌트를 지역으로 사용 
                   'my-button' : myButton,
               },
           })
       </script>
   </body>
   ```


## 13 Vue CLI

- vue와 관련된 패키지를 추가할 때 명령어로 간단하게 추가할 수 있도록 도와주는 도구이다. 

## 14 라우터 ( router )

- 변경된 부분을 찾고 router에 설정되어있는 컴포넌트를 표시할수 있도록 도와주는 컴포넌트이다. 

- 사용법

  ```vue 
  <router-link to="/" > Home </router-link>
  <router-link to="/about"> about!!! </router-link>
  
  <router-view/>
  ```

- /src/router/index.html 에 routes 배열안에 object 형식으로 path와 주소이름, 연결된 컴포넌트가 정의되어있다.

  - 따라서 **`router-link`의 to 에 해당하는 주소이름과 routes 가 매칭되어 설정되어있는 컴포넌트를 `router-view` 태그에 표시**한다. 

  ```js
  const routes=[
      {
          path : '/',
          name : 'home',
          component : Home // import Home from home.vue -> 컴포넌트로 되어있는 파일 
      },
      {
          path : '/about',
          name : 'about',
          component : () => import('../views/About.vue')
      }
  ]
  
  const router = new VueRouter({
      mode : 'history'.
      base : process.env.BASE_URL,
      routes
  })
  ```

- **router-link를 누르면 router-view 부분을 routes 의 배열에서 찾아 설정되어있는 컴포넌트를 표시한다. **

## 15 싱글파일 컴포넌트 ( Single File Component )

- 파일 하나에 컴포넌트 한개를 넣는 것을 뜻한다. 

  - vuejs는 **여러개의 컴포넌트들을 이용해 페이지를 구성하는 방식**이다. 

- 컴포넌트 파일 생성시 규칙이 있다. 

  - 크게 3가지 태그를 가진다. 

  1. **template**
       - **html 코드**같은 태그의 형태가 들어간다.
  2. **script**
       - data같이 컴포넌트 안에서 구현했었던 **object들**이 들어간다. 
  3. **style**
     - **template 태그**에 쓰이는 **스타일**이 들어간다.
       - 이때 **scoped** 를 추가해줘야한다. 그래야 현재 컴포넌트의 template 태그에만 스타일이 적용된다.  
       - scoped를 넣지 않으면 view 어플리케이션 전체에 스타일이 적용되기 때문이다.

- 예시

  ```vue
  <template>
      <div>
          <h1> test vue js single file component </h1>
      </div>
  </template>
  
  <script>
      export defualt{
          data() : {
              return {
                  name : 'wonyoung',
              }
          },
      }
  </script>
  
  <style scoped> 
      h1{
          color : red
      }
  </style>
  ```


## 16 자식 컴포넌트에 데이터 보내기 

- **props 를 사용해 표시**한다. 

- 상위 컴포넌트에서 자식 컴포넌트를 사용할 떄, 해당 컴포넌트 안에서 props의 이름으로 값을 보내주면 하위 컴포넌트의 props 에서 해당 데이터를 받아 사용할 수 있다.

- 예시

  ```vue
  // 상위 컴포넌트 
  <template>
      <div>
          <wonyougComponent myname="wy"> </wonyougComponent>
      </div>
  </template>
  
  <script>
  import wonyoungComponent from '@/components/wonyoungComponent.vue' // @는 src폴더를 가리킨다. 
      export defualt{
          components : { // 컴포넌트를 등록해줘야 사용할 수 있다. 
              wonyoungComponent,// 이 이름으로 template에서 컴포넌트로 사용할 수 있다. 
          }
          data() : {
              return {
              }
          }
      }
  </script>
  
  // 하위 컴포넌트 wonyoungComponent.vue
  <template>
      <div>
          {{ myname }} // 상위에서 props로 데이터 내려준것을 사용한다. 
      </div>
  </template>
  
  <script>
      export defualt{
          props : {// 상위 컴포넌트에서 내려준 props
              myname : {
                  type : String, // props의 타입을 지정할 수 있다. 다른타입이 오면 에러뜸 
                  required : true, // props를 보내지 않으면 에러발생하게 만듬
                  default : 'default text',// 아무 내용없을 경우 대체 
              }
          }
          data() : {
              return ;
          }
      }
  </script>
  ```

- **props** 를 받아서 사용할 떄 하위 컴포넌트에서 props의 **값을 바꾸면 안된다.** 따라서 만약 값을 바꾸고 싶다면 자식에서 바꾸지 않고 부모 컴포넌트에서 바꿔야 한다. 

  - **emit** 을 사용해 부모로 값을 변경할 수 있다. 

## 17 부모 컴포넌트로 데이터 보내기 (emit)

- 하위에서 `@input="$emit('보낼 데이터 이름', $보낼 데이터 )";` 의 형태로 보낼 수 있다. -> input이벤트 발생시 위로 데이터 전달 
- 상위에서는 `<myName :name="name" @(emit에 적은 데이터이름)="실행함수이름">`의 형태로 데이터를 받아서 사용할 수 있다. 
  - emit으로 올린 데이터 이름으로 이벤트가 발생해 실행된다. 
  - 값의 사용은 함수의 매개변수로 값이 들어오기 떄문에 매개변수로 받아 사용한다.

## 18 Slot

- 상위 컴포넌트에서 하위 컴포넌트의 원하는 부분에 html 코드를 추가하고싶을 떄 사용하는 기능이다. 

- v-slot 은 \#으로 대체할 수 있다. 

  ```vue
    // 상위 
    <template>
        <div>
            <wony>
                <template v-slot:wyheader>
                    <p> header
                </template>
                <template v-slot:default>
                    <p> default 
                </template>
            </wony>
        </div>
    </template>
  
  // 하위 
  <template>
      <div>
          // 상위 v-slot의 name이 wyheader 인곳과 연결되어있다. 
          <slot name="wyheader"> 
          </slot>
  
          // 상위 v-slot:default와 연결되어있다. 
          <slot></slot> 
      </div>
  </template>
  ```


## 19 뷰 인스턴스 사이클 

- new Vue를 통해 뷰 인스턴스가 생성되고 죽기 까지의 과정을 `뷰 인스턴스 사이클` 이라고 한다. 

## 20 Vuex

- 여러 계층으로 컴포넌트가 이루어져 있을 떄, 하위의 값을 높은곳의 상위계층으로 올리려면 여러번의 emit을 해줘야한다. 
  - emit한 값을 받아서 또 emit 해주는 형식 
  - 이러한 복잡한 구조를 없애기 위해 나온것이 Vuex이다. 

## 21 Store

- Vuex에서 제공하는 `Store` 라는 곳에 데이터를 저장하고 관리한다. 

  -  따라서 데이터를 한곳에 모아 관리하여 이러한 문제를 해결해준다.

- Vuex안에는 4가지의 object가 들어간다. 

  -  **state**
     -  **데이터**가 들어가는 곳 
  -  **mutations**
     -  State의 **데이터를 업데이트** 하는 곳 
     -  이곳에서만 데이터를 변경할 수 있다.
     -  actions로부터 일을 받아 **데이터를 업데이트**한다. 
  -  **actions**
     -  함수가 들어가는 곳 (methods)
     -  **비동기적인 일을 처리**하고 **mutations 로 보내준다.** 
  -  **getters**
     -  vue의 **Computed**와 비슷하다. 
     -  methods의 기능을 하지만 차이점이 컴포넌트 **선언시 미리 값을 계산**에 가지고 있어 호출했을 경우 함수를 수행하는 것이 아닌 함수의 결과값을 리턴해준다.  
        -  따라서 methods 보다 빠르게 값을 전달할 수 있다. 

- 예시 

  ```js
  import Vue from 'vue';
  import Vuex from 'vuex';
  
  Vue.use(Vuex); // vue에다가 Vuex 플러그인을 사용한다고 알린다. 
  
  export default new Vuex.Store({ // Vuex에서 제공하는 Store 기능을 사용한다. 
    state : {
      todos : [ 
        { id : 1, text : 'buy a car', checked : false},
        { id : 2, text : 'play game', checked : false},
      ]
    },
    mutations : {
      ADD_TODO(state, value){
        state.todos.push({
          id : Math.random(),
          text : value,
          checked : false
        })
      },
      TOGGLE_TODO(state, {id, checked}){
        const index = state.todos.findIndex(todo => {return todo.id === id;});
        state.todos[index].ckecked = checked;
      }
    },
    actions : {
      addTodo(context, value){
          // context안에 여러 기능들이 들어가 있다. ( commit, dispatch 등등... ) -> 다른 object들에서도 사용할 수 있음 
          
          // 여기부분에 비동기 작업이 들어간다. 
          setTimeout(function() {
            context.commit('ADD_TODO'. value); // commit 으로 mutations에 있는 함수를 사용할 수 있다. 
          }, 2000) // 2초후에 안에있는 함수를 시작 
      }
    },
    getters : {
      nummberOfCompletedTodo : state => {
        return state.todos.filter(todo => todo.checked).length;
      }
  
    }
  })
  
  // main.js 에서 store 사용선언
  import Vue from 'vue';
  import App from './App.vue';
  import store from './store';
  
  new Vue({
    store, // store를 사용하겠다고 선언 
    render : h=>h(App),
  }).$mount('#app')
  ```

### Vuex Store의 State Object

- **데이터를 관리**하는 Store 의 Object중 하나
- 사용방법
  - 사용 : `this.$store.state.data1` 의 형식으로 store의 state 데이터를 가져올 수 있다. 

### Vuex Store의 mutations Object

- **state안에 있는 데이터를 변경**하기 위한 Object
- 사용 : `this.$store.commit('mutations의 함수 이름',value)` 의 형식으로 mutation 함수에 정의되어있는 함수로 state의 데이터를 변경할 수 있다.
- 장점중 하나는 디버깅 할 때 쉽게 볼 수있다는 것이다. 
  - chrome 에서 제공하는 확장자 `Vue.js devtools` 를 이용하면 된다. 

### Vuex Store의 actions Object 

- **비동기 작업**으로 state의 데이터를 변경할 때 사용한다. 
- 사용 : `this.$store.dispach('action에 있는 함수이름', value)` 의 형태로 비동기 적 state 데이터를 변경할 수 있다. 
- 비동기 작업을 하기 위해 axios 를 사용하기도 한다. 

### Vuex Store의 Getters Object

- vue의 컴포넌트에 있는 computed 와 비슷한 기능 
- 미리 함수를 구동시켜 놓고 값을 리턴시켜준다. 
- `this.$store.getters.f1` 의 형태로 사용가능하다. 

## 22 Vuex modules

- store안에 직접선언하는 것이 아닌 Store 를 모듈화해서 파일 형식으로 데이터를 가져온다. 

- 예시 

  ```js
  // user.js 모듈
  export default {
    state : {
      todos : [
      ]
    },
    mutations : {
      ADD_TODO(state, value){
        state.todos.push({
  
        });
      },
    },
    actions : {
      addTodo({commit }, value){
  
      }
    },
    getters : {
  
    }
  }
  
  // vuex store 
  import Vue from 'vue';
  import Vuex from 'vuex';
  import todo from './modules/user';// user.js 파일을 import 함 
  
  Vue.use(Vuex);
  export default new Vuex.Store({
    modules : { // store에서 사용되는 4개의 object를 모듈화한 .js파일을 적어준다. 
      // user : user, // js es6부터 변수와 object이름이 동일하면 밑에처럼 user만 써줘도 됨 
      user,
    }
  })
  ```
