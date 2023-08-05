# javascript 의 객체 요소 값 다루기

---

> [참고 사이트1](https://velog.io/@eenooyos/TILJavaScript-%EA%B0%9D%EC%B2%B4%EC%9D%98-%EA%B0%92%EC%9D%84-%EC%B6%94%EA%B0%80%EC%88%98%EC%A0%95-%EC%82%AD%EC%A0%9C%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95)

### 사용 이유

- 자바 스크립트의 객체는 많은 기능을 내장하고 있다. 
- 따라서 요소값을  명령어와 표기법을 이용해 손쉽게 다룰 수 있다. 

### 형태

- 중괄호 `{ }` 안에 key, value의 형태로 들어간다. 

```js
var result = { 
    name : "wony",
    job : "it"
}
```

### 표기법

1. **점 표기법(dot notation)** 

   -  **key값이 동적으로 변할 때 사용에 한계**가 있으며, 숫자로 시작할 수 없고, 변수를 포함할 수 없음.

   ```js
   let obj = {
     fruit : "favorite fruit",
     "favorite fruit" : {
     apple : "first",
     peach : "second",
     banana : "third"
     }
   };
   obj.stock = 100; // 속성 추가
   obj.fruit = "soyoon"; // 속성 변경
   console.log(obj.fruit); // 속성 출력 
   ```

2. **대괄호 표기법(bracket notation)** 

   - **key값이 변수일 때 주로 사용**하며 숫자, 변수, 공백 모두 사용할 수 있음.

   ```js
   obj["stock"] = 100; // 속성 추가 
   obj["fruit"] = "soyoon"; // 속성 변경
   console.log(obj["fruit"]); // 속성 출력
   ```

### 객체의 값 **삭제** 

- 객체의 속성을 **삭제**할 때는 **delete 연산자를 사용**.

  ```js
  // delete obj.id;
  // delete obj.product['product type']);
  
  let obj = {
    cat: 'meow',
    dog: 'woof',
    duck: 'quack',
    cow: 'moo',
  };
  
  delete obj.cat;
  delete obj['dog'];
  delete obj[0]; //‘array’의 특정 ‘index’를 지워버리면 그 자리가 ’empty’가 됨.
  ```



