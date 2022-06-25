# Day52

---

> 세미프로젝트 Day09
>

# 세미프로젝트 Day09

> 개인 개발 
>
> 개발 내용 : Mypage ( 활동정보-회원탈퇴, 배송현황 - 주문취소, 상세정보   )
>
> 개발내용 합치기 

## onclick으로 객체와 해당 태그 정보 넘기기 

```html
function update_num(c,count) {
	$('#total'+c.id).text(c.pprice * $(count).val());
}

<input th:id="count" class="form-input stepper-input" type="number" data-zeros="true" th:value="${c.count}" th:onclick="update_num([[${c}]],this)" min="1" max="1000">

<td th:id="'total'+${c.id}">10000</td>
```

## ppt templete 선택

- [google docs 이용 같이 ppt 만들기](https://docs.google.com/presentation/d/1upFYaKb7vqLy3j93h6VlixmRY24de5CrrNheaTwdWNI/edit#slide=id.p)
- [리바인 회의 무료 템플릿](https://slidesgo.com/ko/)
- [네이버제공 무료 ppt](https://hangeul.naver.com/2014/document)

