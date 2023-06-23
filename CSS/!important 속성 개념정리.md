

# !important

---

>[참고 사이트1](https://www.codingfactory.net/10372)

## !important

### 정의

- CSS 속성을 여러번 정의했을 경우 나중에 설정한 값이 CSS로 적용된다. 
- 따라서 현재 상황에서 설정한 CSS 값이 나중에 설정한 값으로 변경되는것을 방지하기위해 사용한다. 

### 사용법

```css
property: value !important;
```

### 예시

```css
/* 1 */
p {
  color: red;
}
p {
  color: blue;
}
/* 이 경우 나중에 선언된 blue가 적용된다. */

/* 2 */
p {
  color: red !important;
}
p {
  color: blue;
}
p {
  color: green !important;
}
/* color : green에 !important 가 없었다면 red가 적용된다.  */
/* 현재 코드로는 green이 적용된다. */
```

