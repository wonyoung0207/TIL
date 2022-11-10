# Day2

---

> 3-2 ~  4-3강 까지의 내용 

## 함수 

### 종류

```javascript
// 1. 함수의 선언문 =>  함수의 호이스팅이 적용되어 함수 정의 전에 해당 함수를 호출해도 문제가 안된다. 
function testFunc(){//전역함수로 적용됨 
    console.log("Test Function!");

}
testFunc();

// 2. 함수의 표현식 => 함수의 호이스팅이 적용되지 않아 함수 선언 후 사용할 수 있다. 
let testFunc02 = function(){
    console.log("Test Func02");
}

testFunc02();

// 3. new Function()
```

- 사용예시

  ```javascript
  function isNumber( number ){ // 숫자인지 아닌지 판별 => ㅓ매개변수 자료형 생략가능 
      return !isNaN(parseInt(number)); // 숫자면 true, 숫자 아니면 ( a, b...) false리턴 
  }
  
  function isOperation( operation ){ // 연산자를 찾는 함수 
      let operations = '+-*/';
      return operations.indexOf( operation ) !== -1; // indexOf로 매개변수에 있는 값이 operations 변수에 있는 문자열 중 하나라면 해당 자리 인덱스를 리턴하고 , 문자열 이외의 값이라면 -1을 리턴한다. 
  }
  ```

----

## 모듈

### 정의

- 자바스크립트 코드를 담고있는 파일로, 여러 기능들에 관한 코드가 모여있는 하나의 파일. 
- import 와 export 개념 이해하기 

## 장점

- 유지보수성 
- 네임스페이스화
- 재사용성

### 사용방법

- 모듈을 정의하고 export 하여 해당 모듈을 내보낸 후 다른 곳에서 import 하여 모듈을 사용할 수 있다. 

- CommonJS 방식

  ```javascript
  // 모듈화 할 파일 
  const printHelloWorld = () => {
    console.log('Hello Wolrd');
  };
  
  module.exports = {
    printHelloWorld
  };
  ```

  ```javascript
  // 모듈화한 파일을 불러와 사용하는 곳 ( main.js )
  const func = require('./a.js'); // 같은 디렉토리에 있다고 가정
  func.printHelloWorld();
  ```

- ES6 방식.

  - **import 방식 2가지 차이점 알기**

    1. export 된 것을 사용할 것인지 

       ```javascript
       import { isNumber, isOperator } from './foo.js'; // export 된 것을 사용
       
       isNumber(10);
       ```

    2. export default 된 것을 사용할 것인지 

       ```javascript
       import foo123 from './foo.js'; // export default 된것을 사용 => 이름을 변경해서 사용할 수 있다. 
       foo123.isNumber();
       ```

  ```javascript
  // ( foo.js )파일
  export default function (x, y) {// 함수 자체를 내보내는 방법 
    return x + y;
  }
  
  export default { // 객체의 형태로 전체적으로 내보내는 방법 
     	isNumber, // 함수 이름 
      isOperator
  }; 
  
  export default 'bar'
  // 'bar' 라는 데이터를 export하여 
  // 다른 파일에서 import foo from '파일경로' 로 해서 foo 를 찍으면 export한 값인 'bar' 가 출력된다. 
  ```

  ```javascript
  import { foo } from './foo.js'; // export한 모듈 foo 를 가져온다. 
  import { isNumber, isOperator } from './foo.js'; // export 된 것을 사용
  import foo123 from './foo.js'; // export default 된것을 사용 => 이름을 변경해서 사용할 수 있다. 
  
  console.log(foo);
  ```

---

## 배열

- 자바스크립트에서 배열은 객체형태이다. 
- 다른 언어와 비교해서 배열이 사용방식은 같지만 구조는 다르다. 

### 정의

- **유사한 데이터들**을 모아서 관리할 수 있는 리스트 형태의 데이터 집합이다. 
- 배열의 요소는 일반 변수와 마찬가지로 저장되는 값에 의해 데이터 타입이 정해진다. 
  - **다른 언어와 차이점이 여기있다.**
- 자바스크립트 배열에는 길이가 정해지지 않고 넣는만큼 크기가 변경된다. 
- 따라서 배열의 인덱스 방마다 다른 객체타입이 적용되어있을 수 있다. -> 넣는 데이터에 따라 타입이 다르게 들어가기 때문에 조심해야한다. 

### 대표 내장 메소드

- push() , pop()

  ```javascript
  let numbers = [10, 5, 6, 4];
  
  numbers.push(55); // 배열 마지막에 55 추가 
  numbeers.pop() // 배열의 마지막 요소 제거 
  numbers.unshift(-55); // 배열의 첫번째에 -55 추가 
  numbers.shift(); // 배열의 첫번째 요소 제거 
  ```

- map()

  - 배열의 내용을 **순회**할 때 사용 
  - map( 사용 함수 ) => map() 을 적용시키면 해당 배열을 돌면서 어떤 함수를 적용시킬지를 물어보는 것임 
  - 따라서 numbers.map( function( number ){}) => number라는 매개변수에 배열의 값이 하나씩 들어간다. 

  ```javascript
  numbers.map( number => console.log(`number : ${number}`)); // 배열의 모든 값들을 순회하면서 출력 
  ```

- filter()

  - 배열에서 내가 원하는 값만을 꺼낼 때 사용 
  - 하나하나의 요소를 꺼내서 true나 false로 결과가 나오고 true인 값만 배열로 리턴해준다. 

  ```javascript
  let evenNum = numbers.filter( number => number % 2  === 0);
  // 2의 배수만 배열의 형태로 evenNum 에 저장됨 
  ```