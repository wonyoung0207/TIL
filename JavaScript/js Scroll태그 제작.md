# js Scroll태그 제작

---

>

## Scroll 태그 

1. 먼저 기본 태그인 `<div>` 태그를 `<scroll>` 태그로 만들기 위해서는 크게 2가지 설정이 필요하다. 
   1. max-width
      1. 요소의 높이나 너비의 제한
   2. overflow 
      1. 요소의 높이나 너비의 제한 초과시 어떻게 처리할 것인지
2. 즉, max-width로 인해 요소의 너비를 컨텐츠가 초과하게 되고, overflow 속성을 이용해 초과된 요소에 대한 처리를 하게 된다.  

### overflow 

1. 콘텐츠가 요소의 크기를 초과할 때 어떻게 처리될지를 정의
2. 종류
   1. `overflow-y: auto`
      1. 세로 방향으로 스크롤을 자동으로 표시
   2. `overflow-x: auto`
      1. 가로 방향으로 스크롤을 자동으로 표시
   3. `overflow: hidden`
      1. 콘텐츠가 넘쳐도 스크롤을 표시하지 않음
   4. `overflow: scroll`
      1. 항상 스크롤을 표시

## 예시

```vue
<html>
	<div v-html="videoEventText" class="scrollable-textarea text-left"></div>
<html>

<script>
makeVideoEventText(){
  this.videoEventText = null; 
  let resultText = ""; 
  let timeText = "11:16:57.342"; 
  let event = "<span style='color: blue; font-weight: bold;'>[검지 타입]</span>"; 
  let eventType = "이륜차"; 

  for(let i = 0; i < 50; i++){
    resultText += "<div>" + timeText + " " + event + " " + eventType + "</div>"; // html
  }

  this.videoEventText = resultText;

},    
</script>
    
<style>
.scrollable-textarea {
  max-height: 280px; /* 원하는 높이 설정 */
  overflow-y: auto; /* 세로 스크롤 활성화 */
  border: 1px solid #ccc; /* textarea처럼 보이도록 테두리 추가 */
  padding: 10px; /* 내부 여백 */
  white-space: pre-wrap; /* 공백과 줄바꿈 유지 */
  background-color: #f9f9f9; 
}
</style>
```

