# export 와 import 

---

>

## export 와 import 

- `import/export` 문은 스크립트의 맨 위나 맨 아래에 올 수 있는데 이 둘엔 차이가없다. 
  - 따라서 앞, 뒤 어느곳에 와도 정상작동한다. 

- import/export 문은 블록 `{...}`안에선 동작하지 않는다

  - 따라서 블록 밖에 어느곳에서나 import/export 해주는것이 좋다. 


## export

- JavaScript는 export와 import를 이용해 모듈을 내보내고 가져와 사용할 수 있다. 

- 이때 내보내는 방법이 다양하다. 

  1. 선언부 앞에 붙이기 

  ```javascript
  export let num = [1,2,5,6]; // num을 내보냄 
  export class a { // class a를 내보냄 
      constructor(a : string){
          this.a = a;
      }
  }
  export function fa(){
      console.log('aaa');
  }
  ```

  2. 선언부와 떨어진 곳에 붙이기
     - 이때 export나 import의 위치는 상관없다. 하지만 관행적으로 import는 맨 앞에, export는 맨 뒤에 적어준다. 

  ```javascript
  let num = [1,2,5,6]; 
  class a {
      constructor(a : string){
          this.a = a;
      }
  }
  function fa(name : string){
      console.log(name);
  }
  export {a, num, fa};
  ```

- export default 

  - 모듈은 크게 두가지 유형으로 나뉜다. 

    1. 복수의 함수가 있는 라이브러리 형태의 모듈 ( 함수 여러개를 각각 export 하여 내보내는 모듈 )
    2. 개체 하나만 선언되어있는 모듈( class 같이 한 덩이로 export하여 내보내는 모듈 ) => 이 방법을 많이 사용함 

  - '해당 모듈엔 개체가 하나만 있다'는 사실을 명확히 나타낼 때 export default를 사용해 위의 2번방법으로 사용한다.

    - 따라서 export default는 파일당 하나만 존재할 수 있고, 해당 파일은 하나의 객체 ( class )만 있다는 사실을 나타낸다.  

  - export default를 사용하면  가져오기 할 때 개발자가 원하는 대로 이름을 지정해 줄 수 있다. 

    ```javascript
    // => export default user  했을 때, default는 하나의 객체만 내보내기를 보장하므로 파일경로만 재대로 설정해 준다면 모듈 이름을 사용자가 마음대로 설정하여 사용할 수 있다. 
    // 하지만 마음대로 사용하는 경우 코드 가독성이 떨어지기 때문에 규칙을 정해 파일 이름의 앞을 대문자로 변경하여 가져오는것이 좋다. 
    import User from './user.js'; 
    new User('John'); // new를 사용하는 이유는 export 할때 객체를 new하지 않아서이다. 만약 new를 사용하지 않고 싶다면 export할 때 'export default new user' 로 하면 된다. 
    ```

## import 

1. 가지치기(tree-shaking)

   - 빌드 툴이 실제 사용되는 함수가 무엇인지 파악하고 사용되지 않는 함수는 포함하지 않아 불필요한 코드를 제거하는 최적화 과정 
   - 예를들어, 함수 여러개를 export 하여 내보냈을 경우, 사용할 함수의 내보낸 명칭만 사용해 다른 함수들을 import하지 않는다. 
   - 장점은 코드 구조를 파악하기가 쉬워 리팩토링이나 유지보수에 도움이 된다. 

   ```javascript
   import fa as f from './test.js';
   
   f('John');
   f('John');
   ```

- as 로 모듈 이름 바꿔서 가져오기 

  - 이름 변경해서 import하기

    ```javascript
    import {sayHi as hi, sayBye as bye} from './say.js'; // sayHi라는 export한 함수를 hi로 변경해 사용할 수 있다. 
    
    hi('John');
    bye('John'); 
    
    export {sayHi as hi, sayBye as bye}; // export할때도 as를 사용해 이름을 변경해 내보낼 수 있다. 
    import {hi, bye} from './say.js'; // 내보낼 때의 이름을 이용해 import할 수 있다.
    ```

