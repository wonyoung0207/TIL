| **문법**       | **설명**                                      | **예시 코드**                                                |
| -------------- | --------------------------------------------- | ------------------------------------------------------------ |
| 타입 주석      | 변수, 함수의 타입을 명시                      | `let count: number = 5;`   `function greet(msg: string): void {}` |
| 타입 추론      | 타입을 명시하지 않아도 TypeScript가 추론      | `let name = "Alice"; // string`   `let age = 30; // number`  |
| any 타입       | 모든 타입 허용                                | `let value: any = "Hello";`   `value = 42;`                  |
| unknown 타입   | 모든 타입 허용하지만 사용 전 확인 필요        | `let value: unknown = 42;`   `if (typeof value === "number") {}` |
| null/undefined | 값이 `null` 또는 `undefined`일 수 있음을 명시 | `let name: string | null`                                    |
| 배열과 튜플    | 배열 요소의 타입 지정 및 고정된 길이          | `let nums: number[] = [1, 2, 3];`   `let tuple: [string, number] = ["A", 1];` |
| 제네릭         | 타입을 동적으로 정의                          | `function identity<T>(val: T): T { return val; }`   `identity<string>("A");` |
| 인터페이스     | 객체 구조를 정의                              | `interface Person { name: string; age: number; }`   `const p: Person = { name: "A", age: 25 };` |
| 클래스         | 클래스와 생성자에 타입 지정                   | `class Person { name: string; constructor(name: string) { this.name = name; }}` |
| 타입 단언      | 타입을 강제로 지정                            | `let len: number = (val as string).length;`                  |


**변수에 타입 지정**

```typescript
let isComplete: boolean = true;
```

**함수에 타입 지정**

```typescript
function add(x: number, y: number): number {
  return x + y;
}
```

**객체와 인터페이스**

```typescript
interface Car {
  model: string;
  year: number;
}
const myCar: Car = { model: "Tesla", year: 2022 };
```

**제네릭 함수**

```typescript
function wrap<T>(value: T): T[] {
  return [value];
}
const wrapped = wrap<number>(5);
```

**클래스와 타입 지정**

```typescript
class Person {
  name: string;
  constructor(name: string) {
    this.name = name; // this.name이 string 타입임을 명확히 처리
  }
}

const person = new Person("Alice");
console.log(person.name); // "Alice"
```

### In 연산자

- 형태 

  - 속성 in 객체명

- n 연산자는 기본적으로 **객체 용**이다.

  - 배열에다 in 연산자를 쓰면, 인덱스를 검사하게 되는 꼴이 된다.
  - 객체에다 in 연산자를 사용하면 , 객체안에 있는 메소드, 멤버변수를 탐색한다. 
  - 따라서 in 연산자는 **특정 메소드, 프로퍼티 key값 이름의 존재 여부를 검증하기 위한 목적**으로 많이 사용된다.

- 예제

  ```typescript
  // 배열에 사용한 경우 
  var trees = new Array("redwood", "bay", "cedar", "oak", "maple");
  0 in trees         // true를 반환합니다.
  3 in trees         // true를 반환합니다.
  (1 + 2) in trees   // true를 반환합니다. 연산자 우선 순위에 의하여 이 구문의 괄호는 없어도 됩니다.
  6 in trees         // false를 반환합니다.
  "bay" in trees     // false를 반환합니다. 당신은 배열의 내용이 아닌, 인덱스 값을 명시하여야 합니다.
  "length" in trees  // true를 반환합니다. length는 Array(배열) 객체의 속성입니다.
  
  
  
  // 객체에 사용한 경우 
  myObj = {
    no: 1,
    name: 'webisfree',
    getName: function() { return this.name; }
  }
   
  'no' in myObj; // true
  'name' in myObj; // true
  'getName' in myObj; // true
  'url' in myObj; // false
  ```

### 