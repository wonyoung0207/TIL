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