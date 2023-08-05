# vuejs 3  Composition API

---

>

## composition API

- import해서 가져온 API 함수들을 사용하여 컴포넌트의 로직를 정의한다.

- setup() { } : 해당 함수 안에서 수행된다. 

  - **setup** 훅이 추가된 것을 볼 수 있는데 컴포지션 API를 사용하기 위한 진입점 즉, **초기화 지점**이다.
  - 기존의 생명주기와 비교해보자면, beforeCreate 이전에 setup이 호출된다. 
  
  ```vue
  <template>
  <div class="name" > {{ abc('wony') }} </div> // 함수이기 떄문에 () 를 붙여줘야한다.
  <div class="name" > {{ abc(name) }} </div> // 함수를 호출  
  </template>
  
  <script> 
      export default {
          setup() {
              const name = 'wony Test';
              const abc = (name) => { // 함수를 변수에 넣는다. 
                  return 'hello' + name;
              };
              return {
                  name,
                  abc 
              };
          }
      }
  </script>
  
  <style>
      .name {
          color : red;
      }
  </style>
  

## Vue v3 에서 바뀐점
1. template 태그안에서 모든 태그를 하나의 태그로 묶어줘야 했었는데, vue3에서는 묶어주지 않아도 된다. 

2. 리액티브한 데이터 변경
   1. **ref 사용**
   
      1. 컴포넌트 내에서 데이터 변경을 위해 ref()를 사용해야한다. 
      2. vue2에서는 컴포넌트의 데이터를 변경하면 바로 적용되었다.
      3. 하지만 vue3에서는 ref() 라는 함수로 값을 감싸줘야 데이터의 업데이트가 정상적으로 template 태그에 출력된다. 
      4. ref로 감싸주면 해당 변수를 사용할 때 `변수.value` 의 형태로 데이터를 꺼내야한다. value를 생략하면 오류난다. 
   
   2. ref() 사용법 
   
        ```vue
        <template>
            {{name}}
            <div class="name" > {{ updateName() }} </div>  
        </template>
        
        <script> 
            import { ref } from 'vue';
            export default {
                setup() {
                    const name = ref({ // ref() 안에는 배열, object, 기본데이터형 이 들어갈 수 있다. 
                        id : 1,
                    });
        
                    const updateName = () =>{
                        name.value.id = 2;
                        // name.id 를 하면 에러남. 꼭 value를 쓰고 그다음에 값을 찾아야함 
                    }
        
                    return {
                        name,
                    };
                }
            }
        </script>
        ```
   
   3. reactive 사용
   
        - ref() 사용시 `.value` 를 붙여줘야 하는데, reactive() 를 사용하면 `.value` 를 사용하지 않아도 된다. 
   
        - 단, **ref()** 의 매개변수로 **'기본 데이터형, 배열, object'** 형식이 올수 있지만, **reactive()** 의 매개변수에는 **'배열, object'** 밖에 올 수 없다. 

          ```vue
          <template>
              {{name}}
              <div class="name" > {{ updateName() }} </div>  
          </template>
          
          <script> 
              import { reactive } from 'vue';
              export default {
                  setup() {
                      const name = reactive({
                          id : 1,
                      });
          
                      const updateName = () =>{
                          name.id = 2; // reactive() 사용하면 .value를 생략할 수 있다. 
                      }
                      return {
                          name,
                      };
                  }
              }
          </script>
          ```
