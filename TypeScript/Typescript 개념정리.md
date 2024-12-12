# TypeScript 개념정리

---

>

##  typeScript정리

1. typescript 는 정적타입을 지원해 자바스크립트의 타입추론 문제를 해결한 언어이다.
   - typescript ( 정적언어 ) < === > javascript (동적언어)
2. 트렌스 컴파일 과정을 통해 자바스크립트 파일로 변환된다. 
3. 생성자에서 접근지정자(private, public ..) 를 붙이면 프로퍼티( 멤버변수 ) 로 인식한다. 
4. if( e instanceof Error) => instatnceof 는 타입가드로, e가 Error형임을 보장한다. 
   - 타입가드 사용하면 e의 내장함수를 사용할 수 있다. 
5. 인터페이스는 여러 타입을 지정하기위해 사용한다. 
6. boardId와 clubId는 항상 동일하다. 

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

  