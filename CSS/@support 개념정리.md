# @supports  개념정리 

---

> [@supports 란?](https://developer.mozilla.org/ko/docs/Web/CSS/@supports)
>
> [@support 기능 예제](https://abcdqbbq.tistory.com/71)

## @supports

### 정의

- 주어진 하나 이상의 CSS 기능을 브라우저가 지원하는지에 따라 다른 스타일 선언을 할 수 있게 만든다. 
  - 따라서, **브라우저가 해당 기능을 지원하는지 확인하는 쿼리**이다. 
  - 이것을 **기능 쿼리(feature query)**라고 부른다.

### 형태

- css 입력 영역에 `@supports (조건) { 적용할 css내용 } `을 입력하면 된다.

```css
@supports (display: grid) { /* 브라우저에서 display : grid 를 지원하는 경우 실행 */ 
  div { /* 지원한다면 div 의 display 를 grid로 변경  */
    display: grid; 
      /* display: 요소를 어떻게 표시할지를 정함.  */
      /* grid : 2차원 레이아웃으로 구현할 수 있도록 한다. flex보다 더 복잡한 레이아웃을 구성할 수 있도록한다.  */
  }
}
```

### at-rule ( @ 으로 표시된 것 )

- @ 기호를 쓰고 다음에 사용 기능을 적는다. 
- 조건부 규칙이라고도하며 @ 기호 다음에 오는 기능에 따라 각각의 다른 기능들을 적용한다. 
  - @import : CSS 엔진에게 외부 스타일 시트를 포함하도록 알림.
  - @media :  장치가 미디어 질의(*media query*)를 사용하여 정의된 조건의 기준을 만족하면 해당 콘텐츠를 적용하는 조건부 그룹 규칙.
  - @support :브라우저가 주어진 조건을 지원하는지 유무를 판단한다. 

### 사용방법

- 해당 기능을 사용할때는 기본css를 지정해주는것이 좋다. 

  - 왜냐하면 브라우저가 해당 기능을 지원하거나 안하거나를 판별해서 CSS를 지정하는데 이때 CSS 에대한 디폴트가 없으면 안이쁨  

1. and 와 or 연산자 사용 

   ```css
   /* display:table과 display:table-cell을 동시에 지원하는 브라우저에서 .list와 .list li 에 입력한 css가 적용된다. */
   @supports (display:table) and (display:table-cell){
     .list{display:table;}
     .list li{display:table-cell;}
   }
   
   @supports (transform:rotate(45deg)) or (-webkit-transform:rotate(45deg)){
     .box{transform:rotate(45deg);}
   }
   ```

2. not 연산자 사용 

   ```css
   @supports not (display:flex) { /* display 가 flex가 아닌경우 실행  */
     .contents{overflow:hidden;}
     .contents div{float:left; margin:0 5px;}
   }
   ```

3. selector 연산자 사용 ( 어떤 태그를 선택하는 지 )

   ```css
   /* 직계 자손 선택자 (>) 를 지원하는 브라우저일 경우에 .contents > .box에 입력한 css를 적용할 수 있게 만든다 */
   @supports selector(A > B){
     .contents > .box {background:#000;}
   }
   ```





- @property
  - 변수를 선언하고 해당 변수가 어떤 역할을 하는지 지정해서 공통적으로 사용하고 싶은 CSS 스타일을 지정할 수 있다. 
- image() 
  - 단점은 프라우저에서 지원하지 않느다. 
  - 이미지를 가져와서 원하는 이미지 부분만 잘라서 사용할 수 있게 한다. 
    - 기존에는 해당 이미지의 전체 크기만 설정할 수 있었다. 
- 