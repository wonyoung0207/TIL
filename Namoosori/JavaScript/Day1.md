# Day1

---

> 1 ~ 3-1 강 까지의 내용 



## 정의 

- 프로토타입 기반의 스크립트 언어로 객체지향 개념을 지원
- 다중패러다임 언어로 객체지향, 절차지향 프로그래밍 가능 
- 타입에 대한 제약이 느슨하다. 
  - 프로그램의 유연성이 뛰어나 코드를 작성하는데 어려울 수 있다. 
- 동적인 타입체계
  - 변수에 타입을 지정하지 않는다. 
  - 어떤 값을 넣는지에 따라 변수타입이 정해진다. 



## 사용범위

- 웹 환경 ( 클라이언트-react , 서버-node.js)  
- 모바일 환경
- 데스크탑 환경 



## ECMAScript ( ES )

- JavaScript 의 표준화를 위해 만들어진 스크립트 언어
- 1997년 ES1 ~ 현재 ES11 까지 발전하였다. => ES6 부터 많은 변화가 있었다. 



## 변수 선언 종류

- Var

  - 함수나 전역의 범위를 가진다. 
  - ES5 기준으로 많이 사용 
  - var 는 hosting이 적용됨 

- let

  -  해당 변수가 선언된 블록 ({ }) , 구문 또는 표현식 내에서만 유효하다. 

  - ES6부터 나온 개념 

  - 전역 객체의 속성을 생성하지 않으며 hosting이 적용되지 않는다. 

    - hosting : 변수 선언하기 전에 사용되는것을 허용하는 것 

    - hosting을 막는것이 안전하여 더 좋다. 

      ```javascript
      function hosting(){
          console.log(text); // 선언전에 해당 변수가 사용됨. => let 는 hosting 적용안되서 에러남 
          let text;
      }
      ```

- const

  - ES6 부터 추가되어 상수(사용자 정의 상수) 를 정의할 때 사용한다. 

  - let키워드처럼 블록범위를 가지며 선언과 동시에 초기화 한다. 

    ```javascript
    // 가능
    const PI = 3.14; 
    
    // 불가능 
    const pI;
    PI = 3.14;
    // 에러남 -> const 타입은 선언과 동시에 초기화 되어야함
    ```



## 원시 타입 종류

1. 숫자 ( Number )
   - 실수와 정수를 구분하지 않고 모든 숫자를 8byte 의 실수로 처리 
   - 숫자 관련된 특별한 상수 
     1. NaN : Not a Number 로, 계산식의 결과가 숫자가 아님을 나타내는 상수 
     2. Infinity : 무한대를 나타내는 상수로 어떤 수를 0으로 나누거나 Infinity 를 어떤 수로 나눈 식의 결과를 나타낸다. 
2. 문자열
   - 문자열을 16비트의 Unicode 문자체계를 사용 
   - char형태가 없고 'a' 의 형태도 문자열로 사용한다. 
     - 작은 따옴표. 큰 따옴표 둘 다 문자열로 사용된다. 
3. boolean 
   - 비교 연산의 결과 값으로 true와 false로 나뉜다. 
4. null 
   - null 은 비어있거나 없음을 뜻함. 
5. undefined
   - undefined는 값이 초기화되지 않았음 ( 정의되지 않음 ) 을 뜻한다. 
   - 해당 변수의 타입이 결정되어있지 않다. = 값이 들어가지 않았다. 



## 연산자에서 구분해야 할 것 

1. "===" vs "=="

   - 둘의 차이점은 자료형 까지 비교하는지 아닌지의 여부이다. 

   - JavaScript는 자료형이 동적이기 때문에 넣은 값에 따라 자료형이 결정된다. 

   - 따라서 비교연산자 사용시 변수 타입을 let으로 동일하게 설정하더라도 값에의해 다르게 인식될 수 있다. 

   - 이때 자료형까지 비교하고자 한다면 "===" 연산자를 사용해야 한다.

     ```javascript
     let num = 10;
     let str = "10";
     
     console.log('Num == str' + (num == str)); // true
     console.log('Num === str' + (num === str)); // false
     ```

   - 따라서 비교연산을 할때는 반드시 "===" 를 사용해 자료형까지 비교해야한다. 



## 함수 

- JavaScript의 함수는 일급 객체로, 변수에 담거나 전달인자와 반환값으로 전달할 수 있다. 
  - 따라서 함수의 코드를 변수에 담을 수 있다. 

### 함수의 정의

- 자바스크립트에서는 주로 함수 표현식으로 많이 사용한다. 
  - 이유는 자바스크립트는 전달인자로 함수를 전달할 수 있는 일급객체이기 때문이다.

1. 함수 선언문 

   ```javascript
   // 해당 방법은 선언시 브라우저에서 전역 객체로 들어가게 된다. 
   function 함수이름 ( 매개변수1, 매개변수2 ...){
       //함수 내용
   }
   ```

2. 함수 표현식

   ```javascript
   let a = function ( 매개변수1, 매개변수2 ...){
       //함수 내용
   }
   ```

### 함수의 매개변수 

- 자바스크립트의 매개변수는 전달인자와 매개변수의 개수가 일치하지 않더라도 호출이 가능하다. 

- 함수 객체가 가지고 있는 주요 프로퍼티 중 arguments 라는 것이 있다. 

  - 해당 프로퍼티 이용하면 **arguments 가 전달인자들을 배열로 받는다.**

  ```javascript
  function sum (x,y,z){
      let result = x + y + z;
      return result;
  }
  console.log( sum(10)) // NaN => 계산시 매개변수가 부족하여 숫자가 아님을 리턴함 
  console.log( sum(10,20)) // NaN
  console.log( sum(10,20,30)) // 60
  
  
  function sum (x,y,z){
      let result = x + y + z;
      for(let i = 0; i<arguments.length; i++){
          result += arguments[i];
      }
      return result;
  }
  ```

- default 파라미터와 rest 파라미터

  - rest(나머지) 파라미터란 ES6부터 적용되어 임의의 수를 갖는 매개변수를 선언할 수 있다. 
  - 배열 형태로 사용되며 반드시 매개변수 목록 마지막에 선언해야한다. 

  ```javascript
  function sum1 ( x, y = 0, z=1){// default 파라미터 정의 
      
  }
  
  function sum2 ( totalvalue, ...restValues){ // rest 파라미터 사용 
      for(let value of restValues){
          totalvalue -= value;
      }
  }
  
  console.log( sum2(100, 10)) // 100 - 10
  console.log( sum2(100,10,10)) // 100-10-10
  console.log( sum2(100,10,10,10)) // 100-10-10-10
  
  ```

### Arrow Function

- function 이라는 키워드 대신에 화살표 => 를 사용해 함수를 선언하는 방법 

- 화살표를 기점으로 왼쪽에는 전달인자(파라미터 ), 오른쪽에는 함수의 실행문이 들어간다. 

  ```javascript
  const arr = [1,2,3].map( x => x * x);
  ```

- 화살표 함수는 익명함수로 생성자의 역할로 사용할 수 없다. 

  - 주로 전달인자로 사용되어 콜백함수 ( 결과값을 return 하는 함수 )로 쓰인다. 



