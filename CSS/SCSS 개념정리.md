# SCSS ( Sassy CSS ) 개념 정리

---

>

## SCSS

### 정의

- Sassy CSS의 줄임말로, CSS의 확장 문법 중 하나이다. 
- CSS와 거의 유사한 문법을 사용한다. 
  - 기존의 CSS 코드를 쉽게 재사용할 수 있다. 

### 장점

1. 효율적이고 생산적인 CSS 작업을 가능하게 한다. 
2. 코드의 가독성과 재사용성을 향상시킨다. 

### 주요 기능

1. 중첩 규칙(Nested Rules)

   - 요소와 하위 요소의 **스타일 규칙을 중첩하여 작성**할 수 있어서 스타일의 계층 구조를 더 명확하게 표현할 수 있다.

     ```scss
     .card {
       background-color: #f0f0f0;
       
       .header {
         font-size: 16px;
       }
     }
     ```

2. 변수(Variables)

   - **변수를 사용**하여 스타일 값들을 저장하고 **재사용**할 수 있습니다. 이를 통해 일관된 스타일을 쉽게 유지할 수 있다. 

     ```scss
     $primary-color: #007bff;
     
     .button {
       background-color: $primary-color;
     }
     ```

3. 믹스인(Mixins)

   - 믹스인은 재사용 가능한 스타일 규칙의 블록을 정의하여 **다른 규칙에서 쉽게 포함시킬 수 있도록 한다**. 

   - 함수처럼 매개변수를 사용할 수 있어 **동적인 스타일**을 생성할 수도 있다.

     ```scss
     @mixin button-style {
       border: 1px solid #ccc;
       padding: 10px;
       background-color: #f0f0f0;
     }
     
     .button {
       @include button-style;
     }
     ```

4. 연산(Operations)

   - 수치 연산(덧셈, 뺄셈, 곱셈 등)을 지원하여 스타일 값들을 계산할 수 있다.

     ```scss
     .header {
       font-size: $base-font-size * 1.5;
     }
     ```

5. 조건문(Conditionals)과 반복문(Loops)

   - **if 문과 반복문을 사용**하여 더 동적인 스타일 규칙을 만들 수 있다.

     ```scss
     $btn-color: red;
     
     @if $btn-color == red {
       .button {
         background-color: red;
       }
     } @else {
       .button {
         background-color: blue;
       }
     }
     ```



