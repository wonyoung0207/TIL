# @property 개념정리 

---

>[참고 사이트1](https://developer.mozilla.org/en-US/docs/Web/CSS/@property)

## @property

### 정의

- 변수를 이용해 CSS 스타일을 손쉽게 지정할 수 있다. 
- 변수를 선언하고 해당 변수가 어떤 역할을 하는지 지정해서 공통적으로 사용하고 싶은 CSS 스타일을 지정할 수 있다. 

### 형태

```css
@property --property-name { /* 프로퍼티 이름을 --property-name로 설정. 내가 설정하고싶은 이름으로 변경해도 됨  */
  syntax: "<color>"; /* 해당 프로퍼티 변수가 가지는 값의 유형 */
  inherits: false; /* 위의 태그로부터 값을 상속 받을것인지 설정.  */
  initial-value: red; /* 초기값 설정  */
}
```

### 종류

1. color

   ```css
   @property --mycolor { 
     syntax: "<color>"; 
     inherits: false; 
     initial-value: red; 
   }
   
   .link { //변수 사용 
       color:var(--mycolor); // red
   }
   
   .link:hover{ // 해당 변수로 설정된 모든 색상을 blue로 변경 
       --mycolor : blue; // blue
   }
   
   .link:first-child:hover{ 
       --mycolor : false; // red -> inital-value 에 설정한 값으로 설정함 
   }
   ```

2. percentage

   ```css
   @property --a { 
     syntax: "<percentage>"; 
     inherits: false; 
     initial-value: 40%; 
   }
   ```

3. url

   ```css
   @property --a { 
     syntax: "<url>"; 
     inherits: false; 
     initial-value: url("logo.jpg"); 
   }
   ```

4. transform-function

   ```css
   @property --a { 
     syntax: "transform-function"; 
     inherits: false; 
     initial-value: translateX(10px); 
   }
   ```

   