# Typescript의 타입 정의 방법

---

>

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

type UserType = {name : string, age : number}; // 2개의 타입을 가지는 객체형태를 만들 수 있다. 
const testUser : UserType = {name : 'kim', age : 14};

// type allas 를 사용하지 않은경우 
const user : {name : string, age : number} = {name : 'kim', age : 14};


// 함수에 type allas  적용 전 
getItemCounts() : {total : number, incomplete : number }{
    return {
        total : this.itemMap.size,
        incomplete : this.getTodoItems(false).length
    }
}

// 함수에 type allas 적용 후 
type ItemCouns = {
    total : number,
    incomplete : number
}

getItemCounts() : ItemCouns{
    return {
        total : this.itemMap.size,
        incomplete : this.getTodoItems(false).length
    }
}

```

### As 문법

- type Assertion in TypeScript ( **타입표명** )

- 명시적 타입 캐스팅과 비슷하게 사용할 수 있다. 다만, 실제로 타입이 변경되는 것은 아니고 이런 타입이 들어올 것이라는 것을 프로그래머가 명시해주는 것

  ```typescript
  function handler(event: Event) {
  	let element = event as unknown as HTMLElement; // Event 타입이라는 것을 타입표명 했지만 as를 통해 더 구체적으로 나타내줌  
  }
  function handler (event: Event) {
    let mouseEvent = event as MouseEvent;
  }
  ```
