## css 의 autocomplete 속성

---

>

## autocomplete

1.  <input> 요소에서 자동 완성 기능을 사용할지 여부를 명시
    1. 즉, 한번 입력한 값을 기억할지에 대한 기능 
    2. 예를들어 text로 검색한 input 박스가 있다고 했을때, `autocomplate=true` 이면 text 가 자동완성 기능으로 제공된다. 

### 예시

<img src="./images/autocomplete 적용후.png" width="500">

```html
<form action="/examples/media/action_target.php" method="get">
    이름 : <input type="text" name="st_name"><br>
    학번 : <input type="text" name="st_id" autocomplete="on"><br>
    학과 : <input type="text" name="department" autocomplete="off"><br>
    <input type="submit">
</form>
```





