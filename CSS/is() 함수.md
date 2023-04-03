# is() 함수 

---

>[참고 사이트1](https://7942yongdae.tistory.com/73)
>
>[참고 사이트2](https://frontdev.tistory.com/entry/CSS-%EA%B0%80%EC%83%81-%EC%84%A0%ED%83%9D%EC%9E%90-is-%EC%99%80-where)

## is()

### 정의

- 반복적으로 적어줘야할 선택자들을 한번묶어 스타일을 적용할 수 있다. 
- 선택자 목록을 이용해 다양한 태그에 동시에 스타일을 적용한다.
  - 차례로 선택자를 써서 연결한다. 

### 사용방법

```css
/* is() 사용 전 */
section section h1,
section article h1,
section aside h1,
section nav h1,
article section h1,
article article h1,
article aside h1,
article nav h1,
aside section h1,
aside article h1,
aside aside h1,
aside nav h1,
nav section h1,
nav article h1,
nav aside h1,
nav nav h1 {
  font-size: 20px;
}


/* is() 사용 후 */
:is(section, article, aside, nav) :is(section, article, aside, nav) h1 {
  font-size: 20px;
}
```