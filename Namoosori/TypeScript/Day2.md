# Day2

---

> Map
>
> 전개연산자 
>
> 제네릭
>
> Type allas

## TypeScript

### Map

- 순서가 없고 key와 value로 구성되어 있는 Collection

- 사용법

  ```typescript
  930
  class TodoCollection{
      private itemMap : Map<number, TodoItem>; // Map은 선언 후 무조건 초기화되어야한다. 
      constructor(public userName : string, todoItems : TodoItem[] ){
          this.itemMap = new Map<number , TodoItem>();
          todoItems.forEach( (item) => itemMap.set(item.id, item)); // Map은 set으로 값을 넣고 get으로 읽는다. 
          
      }
      
      getTodoItems(includeComplete : boolean) : TodoItem[] {
      	return [...this.itemMap.values()].filter(
              (item) => includeComplete || !item.complete
          );// 전개연산자를 이용해 Map의 value들을 배열로 변환한다.  
  	}
  }
  
  ```

- 전개연산자 ( Spread Operator ) => ' ... ' 을 사용 

  - 배열 혹은 객체요소를 전개하기위해 사용되는 연산자.  

  - 배열에 있는 값들을 다른 배열로 옮길 때 사용 

    ```typescript
    let arr: number[] = [1,2];
    let arr2: number[] = [...arr, 3, 4];
    ```

---

## 제네릭

### 정의

- 어떤타입의 객체를  넣고 어떤타입으로 꺼낼지 명시적으로 넣을 수 있는 형태
- 재사용 가능한 클래스 , 함수를 만들기 위해 다양한 타입에서 사용 가능 하게 하기위해 사용하는 것 
- 모든 타입의 객체를 다루면서 객체 타입의 무결성을 지킬 수 있다. 

### 사용법

```typescript
class Box<T>{
    constructor(private fruit : T) {}
    getFruit() : T {
        return this.fruit;
    }
}

const box : Box<Orange> = new Box(new Orange(5));
console.log(box.getFruit().getName());

// Compile Error 발생 
const strBox = new Box('Banana');
console.log(strBox.getFruit().getName()); // 여기서 에러발생 : 이유는 box안의 값이 fruit 객체가 아닌 string 이기 때문에 fruit 객체 안에있는 getName() 을 호출할 수 없다. 
```

---

## Type Allas

### 정의

- 기존에 정의되어있는 타입이 아닌 사용자가 만들고자 하는 타입을 만들 때 사용한다. 
- 새로운 타입을 정의하는 방법은 크게 2가지이다. 
  1. Type Allas
  2. interface

### 사용법

- type 이라는 키워드를 이용해 type 다음에 오는 변수를 타입의 이름으로 만들고 ' = ' 를 사용해 타입을 지정한다. 

```typescript
// type allas 를 사용한 경우 
type mynum = number;
const num : mynum = 14;

type UserType = {name : string, age : number};
const testUser : UserType = {name : 'kim', age : 14};

// type allas 를 사용하지 않은경우 
const user : {name : string, age : number} = {name : 'kim', age : 14};
```

---

## 사용자 입력받기

- 사용자로부터 입력받기 위해서는 
- **readline-sync**
  - readline의 경우, 입력을 비동기적으로 처리함.
  - 입력을 동기적으로 처리하기 위한 package임.
  - readline-sync -npm
- sync VS async
  - 모든 메소드는 sync 와 async로 구분할 수 있다. 

## 에러

- Could not find a declaration file for module 'readline-sync'

  - 모듈을 찾지 못할때이다. readline-sync 모듈을 사용하기 위해서는 npm으로 install 해야하는데, install 하고도 에러가 났다. 

  - 다음 코드로 npm install을 하니 해결되었다

    ```
    npm install --save @types/readline-sync
    ```

    - 질문하기 : 왜 install 그냥 했을때는 모듈을 인식하지 못하는가 => 내가 보기엔 External Libraries 안에 있는 @types 폴더 안에 readline 모듈이 있다. 이곳에 추가해줘야하는건가???
    - 만약 맞다면 왜 packge.json 파일의 dependency에 추가가 안되나??? 해당 폴더 상위에 packge.json이 없는 이유는 뭔가??

- **pm(Node Package Manager)**은 **프로젝트에 필요한 라이브러리를 다운로드 또는 관리 할 수 있도록 해주는 프로그램**

  -  **--save 옵션은 package.json의 dependency 항목에 모듈을 추가한다는 의미**

---

## 문법

### trim()

- 문자열의 양 끝의 공백을 제거한 문자열을 반환 

### question ( ' 콘솔 출력 ')

- 해당 함수를 사용하기 위해서는 import {question} from 'readline-sync' 로 모듈을 불러와 사용해야한다. 

### 반복문

1. for of
   - 반복문을 돌며 속성 값 , 즉 value 를 출력한다. 
2. for in
   - 반복문을 돌며 속성 이름 , 즉 index를 출력한다.

```javascript
let arr = [3,5,7];
arr.foo = 'hello';

for(let i in arr){
    console.log(i); // 0,1,2,foo 가 출력된다. => 즉 속성이름이 출력된다. 
}

for(let i of arr){
    console.log(i); // 3,5,7,hello 가 출력된다. => 즉 속성값이 출력된다. 
}
```

---

## git

- git init 으로 로컬 저장소가 된 폴더를 다시 일반폴더로 변경하는 방법
  - rm -rf .git

