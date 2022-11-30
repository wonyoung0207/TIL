# Day1

---

> typescript에 필요한 라이브러리 
>
> typescript 란?
>
> export와 import 사용방법 
>
> .gitignore 파일 작성하기 
>
> gitlab 레포지토리 삭제방법 

## 설치 목록

1. gitlab 설치
2. typora 설치 
   - markdown 사용하기 위해 
3. intellij 설치 
   - typescript 사용하기 위해 
4. node js설치
   - intellij  에서 run 하기위해서 필요함 
5. npm 에서 typescript 다운 
   - typescript를 사용하기 위해서는 npm을 이용해 이용 툴을 다운받아줘야한다. 
   - npm 으로 ts-node 툴 설치해야 typescript파일에서 컴파일과 실행 두 가지를 한번에 할 수있다. 

---

## TypeScript

### 정의

- 자바스크립트에 타입을 부여한 언어
- 컴파일러 (tsc) 를 이용해 자바스크립트로 변환된다. 

### 장점

- 정적 타입을 지원한다.

  - 변수를 정의하면서 타입을 함께 지정하여 해당 타입만 대입 가능하게 한다. 

  ```javascript
  let num : number = 202; // 이렇게 뒤에 : 를 이용해 자바스크립트에서는 사용하지 못했던 타입정의를 할 수 있다.  
  ```

  - 정적타입 지원의 장점은 컴파일 단계에서 오류를 쉽게 포착할 수 있게 한다는 것이다. 
  - 가독성을 높이고 예측할 수 있게 하며 디버깅을 쉽게 한다. 

### 트렌스 컴파일

- 타입스크립트 언어를 자바스크립트로 변환하는 것

### 타입추론

- 정적 타입을 명시하지 않고 대입하는 값을 통해 타입을 유추해 결정하는 것 

  - 타입을 써주지 않고 대입하는 값을 봐야 해당 변수의 타입을 알 수 있다

- 따라서 TypeScript에서는 타입추론이 일어나지 않도록 파라미터에 꼭 타입을 정해주는것이 좋다. 

  - 정하지 않으면 any가 자동배정된다. 

  ```javascript
  for( let i : number = 0; i < data.length; i++){ // 타입추론 x
  }
  
  for( let i = 0; i < data.length; i++){ // 타입추론 o
  }
  ```

### 타입스크립트 개발 환경 구축 필요 모듈

- 타입스크립트를 자바스크립트로 변환하기 위해서는 typescript.js라는 모듈이 필요하다. 
  - nodejs를 설치 후 npm을 이용해서 typescript를 설치하면 tsc(컴파일러)를 이용해 *.ts 파일에 대한 컴파일을 할 수 있다.
  - npm 으로 ts-node를 추가적으로 설치하면 컴파일과 실행을 한번에 수행할 수 있다.  

### 접근지정자

1. private 
2. public
3. protected

- typescript에서는 접근지정자를 생성자 파라미터에서 정의하면  해당 파라미터들을 **프로퍼티 ( 객체의 요소 )** 로 인식한다. 

```typescript
class a {
    // private num : number;
    // public str : string;	
    constructor(public num : number, public str : string, public complete : boolean = false ){ // default 파라미터 지정 => 생성시 디폴트로 설정됨 
        
        this.num = num;
        this.str = str;
        this.complete = complete;
    }
}
```

### 가변인자

- typescript는 JavaScript에서 지원하는 가변인자 함수를 지원하지 않는다. 

- 가변인자 함수 : 함수의 파라미터 갯수를 정하지 않고 arguments를 이용해 객체 배열로 사용하는 것 

  - 이때 arguments는 배열형태이고, 해당 배열 안에있는 것은 객체 (object ) 타입이다. 

  ```javascript
  function add() {
    let result = 0;
      for (let i in arguments) {
          result += arguments[i]
      }    return result
  }
  add(1, 2, 3) // 6
  add(1, 2, 3, 4, 5) // 15
  ```

- 따라서 TypeScript 에서는 함수의 오버로딩을 통해 가변인자와 같은 효과를 구현한다. 

  - 오버로딩 함수가 'any' 타입을 이용한 함수를 호출하는 방식으로 진행된다. 

    ```javascript
    function add(first : number, second : number) : number; // 오버로딩 함수 
    function add(first : string, second : string) : string; // 오버로딩 함수 
    
    function add(first : any, second : any) : any{ // 가변인자와 같은 효과 낼수 있게 함 
        console.log(first + second );
    }
    
    add(10, 20);
    add('10', '20');
    ```

### 함수의 리턴타입 지정 

- TypeScript의 리턴타입 여러개로 지정하기 
  - 리턴타입을 지정하지 않아도 TypeScript가 return 을 통해 타입추론을 해서 Error를 안나게 해준다. 
  - 하지만 타입을 지정해주는것이 좋다. 

```typescript
class a {
    constructor(public userName : string, public todoItems : [] = [] ){}
	getTodoById(id : number) : TodoItem | undefined { 
        // find 함수는 해당 배열에서 값을 찾아 가장 처음 인덱스를 리턴한다.  
        // 리턴타입이 todoItem 일수도 있고 만약 아무것도 찾지못하면 undefinde 이기 때문에 2개의 리턴타입을 지정해야한다. 
        return this.todoItems.find( (item) => item.id === id ); 
        // item은 todoItems에서 하나씩 꺼내온 객체로, todoItem 객체 ( 해당객체 안에 id가 있다. ) 이다.  
    }
    
    addTodo(task : string) : number{
        while( this.getTodoById(this.nextId)){ // 다음값이 있으면 증가 
            this.nextId++;
        }
    }
}
```

- void 와 never  차이점

  - void : 함수의 반환값이 없을 경우 사용 

    - 변수의 타입으로 사용될경우 undefined , null 값만 대입이 가능하다. 

  - never : 함수의 반환값이 절대 발생하지 않는다는 것을 의미 

    ```typescript
    function overError (message? : string) : never {
        throw Error(message);//에러를 발생시켜서 리턴값이 절대 없음 
    }
    ```

### 배열안에 여러 타입 지정

- 배열안에 여러 타입이 올 수 있도록 지정해줄 수 있다. 

  ```typescript
  const myArr : (string | number)[] = ['abc', 13]; // 선언 및 초기화
  // 이때, myArr 는 string과 number 타입만을 받을 수 있다. 이 이외의 값이 들어오면 컴파일 중 에러발생 
  ```

### 튜플 타입 

- 배열보다 제약사항을 더 가진 배열이라고 이해하면 된다. 

- 배열과 차이점

  - 배열 : 가변적 ( 요소의 수 같은것을 변환시킬 수 있다. ) 
  - 튜플 : 불변성  

- 튜플타입은 **저장되는 요소에 순서와 수에 제약을 두고자 할 때 사용**한다. 

  ```typescript
  // 배열 
  const arr : (string | number)[] = [12, 'abc']; // 지정한 타입의 순서와 상관 x
  
  // 튜플
  const arr : [string, number] = ['abc', 12]; // 타입지정한 순서와 일치해야함
  
  // 튜플 에러나는 경우 
  const arr : [string, number] = [12, 'abc']; // 타입지정과 순서 맞춰야함 
  const arr : [string, number] = ['abc', 12, 'c'];  // 타입지정이 1번째 인덱스까지 정의되어있는데 초기화를 2번쨰 인덱스까지 함 
  
  ```

  

---

## export 와 import 

- `import/export` 문은 스크립트의 맨 위나 맨 아래에 올 수 있는데 이 둘엔 차이가없다. 

  - 따라서 앞, 뒤 어느곳에 와도 정상작동한다. 

- import/export 문은 블록 `{...}`안에선 동작하지 않는다

  - 따라서 블록 밖에 어느곳에서나 import/export 해주는것이 좋다. 

- export

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

- import 

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

---

## Git

### gitignore 파일

- 프로젝트에서 원하지 않는 파일을 git 이 관리하지 않도록 하는 파일 
- 해당 파일 안에 특정 파일 이름 및 파일형태를 적으면 해당 파일은 git의 관리에서 제외되어 push했을 경우 올라가지 않는다. 
- 전체 폴더에서 적용하려면 앞에 /**/ 을 붙여줘야한다. 

```
.idea/

/**/package.json

/**/*.js

*.iml
```

### git이 관리중인 파일을 .gitignore 로 제외시키는 방법 

- gitignore 파일은 **git track(추적)하지 않도록 설정하는 역할**을 한다.
- 이미 관리되고 push 한 파일은 후에 .gitignore에 추가해도 없어지지 않는다. 
  - 이유는 git프로젝트를 열어 작업을 할 때 indexing이라는 작업을하는데 이미 인덱싱이 된 파일은 계속 추적이 되고 있어서이다. 
- 그래서 "**git rm -r --cached .**"명령어를 통해서 캐시를 비워 인덱싱을 해제하는 작업이 필요하고 캐시만 비우면 다시 정상적으로 gitignore가 적용 된다.

### gitlab 레포지토리 삭제

1.  Your Project > 삭제할 프로젝트 클릭
2.  Settings > General 클릭
3.  맨 아래쪽 Advanced 항목에서 ‘expand’ 클릭
4.  다시 맨 아래쪽으로 내리면 Delete project 항목이 있음‘Delete project’ 클릭
