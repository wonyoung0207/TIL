## Vue CLI
- vue와 관련된 패키지를 추가할 때 명령어로 간단하게 추가할 수 있도록 도와주는 도구이다. 
## 라우터 ( router )
- 변경된 부분을 찾고 router에 설정되어있는 컴포넌트를 표시할수 있도록 도와주는 컴포넌트이다. 
- 사용법
- ```vue 
    <router-link to="/" > Home </router-link>
    <router-link to="/about"> about!!! </router-link>

    <router-vie/>
- /src/router/index.html 에 routes 배열안에 object 형식으로 path와 주소이름, 연결된 컴포넌트 가 정의되어있다. 따라서 `router-link`의 to 에 해당하는 주소이름과 routes 가 매칭되어 설정되어있는 컴포넌트를 `router-view` 태그에 표시한다. 
- ```vue
    const routes=[
        {
            path : '/',
            name : 'home',
            component : Home
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
- **따라서 router-link를 누르면 router-view 부분을 routes 의 배열에서 찾아 설정되어있는 컴포넌트를 표시한다. **

## 싱글파일 컴포넌트 ( Single File Component )
- 파일 하나에 컴포넌트 한개를 넣는 것을 뜻한다. 
  - vuejs는 여러개의 컴포넌트들을 이용해 페이지를 구성하는 방식이다. 
- 컴포넌트 파일 생성시 규칙이 있다. 
  - 크게 3가지 태그를 가진다. 
- 1. **template**
    - html 코드같은 태그의 형태가 들어간다.
- 2. **script**
    - data같이 컴포넌트 안에서 구현했었던 object들이 들어간다. 
- 3. **style**
    - template 지정 태그에 쓰이는 스타일이 들어간다.
    - 이때 **scoped** 를 추가해줘야한다. 그래야 현재 컴포넌트의 template 태그에만 스타일이 적용된다.  
- ```vue
    <template>
        <div>
            <h1> test vue js single file component </h1>
        </div>
    </template>

    <script>
        export defualt{
            data() : {
                return {
                    name : 'wonyouh',
                }
            }
        }
    </script>

    <style scoped> 
    // scoped를 넣지 않으면 view 어플리케이션 전체에 스타일이 적용되기 때문이다.
        h1{
            color : red
        }
    </style>


## 자식 컴포넌트에 데이터 보내기 
- props 를 사용해 표시한다. 
- 상위 컴포넌트에서 자식 컴포넌트를 사용할 떄, 해당 컴포넌트 안에서 props의 이름으로 값을 보내주면 하위 컴포넌트의 props 에서 해당 데이터를 받아 사용할 수 있다.
- ```vue
    // 상위 컴포넌트 
    <template>
        <div>
            <wonyougComponent myname="wy"> </wonyougComponent>
        </div>
    </template>

    <script>
    import wonyougComponent from '@/components/wonyougComponent.vue' // @는 src폴더를 가리킨다. 

        export defualt{
            components : { // 컴포넌트를 등록해줘야 사용할 수 있다. 
                wonyougComponent,// 이 이름으로 template에서 컴포넌트로 사용할 수 있다. 
            }
            data() : {
                return {
                }
            }
        }
    </script>

    // 하위 컴포넌트 wonyougComponent.vue
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

- **props** 를 받아서 사용할 떄 하위 컴포넌트에서 props의 **값을 바꾸면 안된다.** 따라서 만약 값을 바꾸고 싶다면 자식에서 바꾸지 않고 부모 컴포넌트에서 바꿔야 한다. 
  - **emit** 을 사용해 부모로 값을 변경할 수 있다. 

## 부모 컴포넌트로 데이터 보내기 (emit)
- 하위에서 `@input="$emit('보낼 데이터 이름', $보낼 데이터 )";` 의 형태로 보낼 수 있다. -> input이벤트 발생시 위로 데이터 전달 
- 상위에서는 `<myName :name="name" @(emit에 적은 데이터이름)="실행함수이름">`의 형태로 데이터를 받아서 사용할 수 있다. 
  - emit으로 올린 데이터 이름으로 이벤트가 발생해 실행된다. 
  - 값의 사용은 함수의 매개변수로 값이 들어오기 떄문에 매개변수로 받아 사용한다.
## Slot
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
    </template>`


## 뷰 인스턴스 사이클 
- new Vue를 통해 뷰 인스턴스가 생성되고 죽기 까지의 과정을 `뷰 인스턴스 사이클` 이라고 한다. 