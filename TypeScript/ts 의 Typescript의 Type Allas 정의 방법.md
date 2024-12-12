# Typescript의 Type Allas 정의 방법

---

>

## Type Allas

### 정의

- 기존에 정의되어있는 타입이 아닌 사용자가 만들고자 하는 타입을 만들 때 사용한다. 
- 새로운 타입을 정의하는 방법은 크게 2가지이다. 
  1. Type Allas
  2. interface

## 스타일 가이드

1. type Allas 에서 타입설정 시 뒷부분엔 세미콜론(`;`)을 사용하는 것을 권장

2. `type`이나 `interface`를 정의할 때, **`;`(세미콜론)**과 **`,`(콤마)**는 모두 사용할 수 있다. 

   ```
   export type styleMenu = {
     title: string;           // 문자열
     value: string;           // 문자열
     layer: string;           // 문자열
     imagery: {               // 객체 형태
       show: boolean;         // `show`는 반드시 boolean 값
     };
     bgImage: File;           // 이미지 파일 (예: PNG 파일)
   };
   ```

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

## 옵셔널 프로퍼티 

1. 인터페이스에서 사용하는 `옵셔널 프로퍼티` 방법을 type allas 에서도 사용할 수있다. 
2. `?` 를 사용하면 선택속성으로, 값이 들어오지 않아도 오류가 발생하지 않는다. 

```ts
type User = {
  name: string;         // 필수 속성
  age?: number;         // 선택 속성
};
```

##### 주의할점

1. 타입 안전성을 위해 옵셔널 속성에 접근할 때는 항상 존재 여부를 확인해야한다. 

2. 왜냐하면 `undefined` 상태일 수 있기 때문이다. 

   ```js
   const showImagery = menu1.imagery?.show ?? false; // imagery가 없으면 기본값 false
   ```

   
