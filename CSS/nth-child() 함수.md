# nth-child() 함수

---

>[참고 사이트 1](https://www.codingfactory.net/10178)
>
>[자식 자손 선택자 ](https://sectumsempra.tistory.com/57)

## nth-child() 

### 정의

- 형제 요소 중 an+b번째 요소들을 선택하는 선택자

### 형태 

```css
$( ':nth-child(an+b)' )
```

- `a`와 `b`는 상수, `n`은 변수
- `n`에는 음이 아닌 정수(0, 1, 2, 3, ···)가 차례대로 대입된다. 
- `an+b` 대신에 `even`, `odd`를 사용할 수 있다.

### 사용방법

```html
<!doctype html>
<html lang="ko">
  <head>    
    <script>
      jQuery( document ).ready( function() {
        $( 'ol li:nth-child(2)' ).addClass( 'jb-red' );
        // 다른 사용법
        $( 'ol li:nth-child(2n+1)' ).addClass( 'jb-red' ); // 홀수만 변경 
        $( 'ol li:nth-child(even)' ).addClass( 'jb-red' ); // 짝수만 변경 
      } );
    </script>
  </head>
  <body>
    <ol>
      <li>One</li>
      <li>Two</li> <!-- nthLcild(2) 로 선택되는 태그  -->
      <li>Three</li>
      <li>Four</li>
      <li>Five</li>
      <li>Six</li>
      <li>Seven</li>
      <li>Eight</li>
      <li>Nine</li>
      <li>Ten</li>
    </ol>
  </body>
</html>
```

- `ol li:nth-child(2)`
  - ol 요소의 자식 요소인 li의 형제 요소 중에서 두 번째 요소를 선택
- 자식과 자손 선택자 
  - ' ' : 띄어쓰기 하면 **자손 선택자**이다. 
  - \> : 해당 기호 사용하면 **자식 선택자** 이다. 
  - 자식 선택자(s1>s2)는 바로 아래 있는것만 선택하는 것으로 s1의 s2까지의 태그만 선택된다.
  - 후손 선택자(s1 s2)는 아래 있는 해당 태그에 속한 모두를 선택하기 때문에 s1과 s2, s2 밑에 있는 태그 모두를 선택한다. 
- 따라서 중복 태그 내에서 원하는 태그만 지정할 때 사용된다. 

### of 와 함께 사용 

```html
<style> 
li : nth-chlid(2).link{ /* li를 기점으로 2번째 li를 선택한 후 .link 요소인지 판별한다음 선택  */
    
}

li : nth-child(2 of .link){  /* li의 .link 이름으로 되어있는 것중에서 2번째 요소를 선택  */
    
}
</style> 
<body>
	<ol>
		<li> 1
		<li class="link"> 2 <!-- nth-child(2).link 사용시 선택  -->
		<li> 3
         <li class="link"> 4 <!-- nth-child(2 of .link) 사용시 선택  -->
	</ol>
</body>
```

