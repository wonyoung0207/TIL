# js DOM, BOM 개념정리 

---

>[짐코딩-js DOM](https://www.youtube.com/watch?v=aTGhKjoZeao&list=PLlaP-jSd-nK9LiA2n07uBhzOn9wI53xGV&index=2)

## DOM ( Document Object Model )

1. 문서 객체 모델 
2. 문서를 객체화 한다. 
3. **스크립트 언어로 HTML 요소를 제어할 수 있도록 웹 문서를 객체화 한것** 
4. DOM은 Tree 구조를 가진다. 

##### 종류

1. 문서 노드

   ```js
   console.log(document); // 문서 객체 출력
   console.log(document.title); // HTML 문서의 제목 가져오기
   document.title = "New Title"; // HTML 문서의 제목 변경
   ```

2. 요소 노드

   ```js
   let box = document.getElementById("box"); // 요소 선택
   // querySelector
   console.log(box.tagName); // 태그 이름 출력: DIV
   console.log(box.innerHTML); // 요소의 내용 가져오기: Hello World!
   box.innerHTML = "New Content!"; // 요소의 내용 변경

3. 속성 노드 

   ```js
   let img = document.getElementById("image");
   console.log(img.getAttribute("src")); // 속성값 가져오기: example.jpg
   img.setAttribute("src", "new-image.jpg"); // 속성값 변경
   img.setAttribute("alt", "New Image"); // 새 속성 추가 또는 수정

4. 텍스트 노드 

   ```js
   let textNode = document.getElementById("text").firstChild;
   console.log(textNode.nodeValue); // 텍스트 노드 값 가져오기: Hello, DOM!
   textNode.nodeValue = "Updated Text!"; // 텍스트 내용 변경

5. 주석 노드 

   ```js
   let comments = document.createComment("This is a new comment");
   document.body.appendChild(comments); // 주석 추가

6. 문서 조각 노드

   1. 가상 노드로, 성능 최적화를 위해 여러 요소를 한 번에 DOM에 추가할 때 사용

   ```js
   let fragment = document.createDocumentFragment();
   for (let i = 0; i < 5; i++) {
     let li = document.createElement("li");
     li.textContent = `Item ${i + 1}`;
     fragment.appendChild(li); // 조각에 추가
   }
   document.getElementById("list").appendChild(fragment); // DOM에 한 번에 추가

7. 이벤트

   ```js
   document.getElementById("btn").addEventListener("click", function () {
     alert("Button clicked!");
   });
   ```

##### 요소선택

```js
document.getElementById("idName"); // ID로 선택
document.getElementsByClassName("className"); // 클래스명으로 선택
document.getElementsByTagName("tagName"); // 태그명으로 선택
document.querySelector("#idName"); // CSS 선택자로 선택
document.querySelectorAll(".className"); // CSS 선택자로 여러 요소 선택
```

---

## BOM (Browser Object Mode)

1. 브라우저 객체 모델로, **브라우저의 창이나 프레임을 조작할 수 있도록 제공**되는 JavaScript 객체 모델
2. 브라우저를 제어할 수 있도록 모델링한것 

##### 종류

1. window

   1. 브라우저 창 자체를 나타내는 최상위 객체입니다. BOM의 모든 객체는 `window` 객체의 속성으로 존재
   2. **window 는 생략이 가능하다.** 

   ```js
    console.log(window.innerWidth);  // 현재 브라우저 창의 너비
   window.close() // 탭 닫기 
    
   // window 는 생략 가능 
   close() // 탭닫기 
   alert('메시지') // 메시지 
   ```

2. history

   1. 브라우저의 방문 기록을 관리하는 객체

   ```js
   history.back();     // 뒤로 가기
   history.forward();  // 앞으로 가기
   history.go(-1);     // 한 페이지 뒤로 이동
   history.go(2);      // 두 페이지 앞으로 이동
   ```

3. location

   1. 현재 페이지의 URL 정보를 제공하고, 페이지 이동을 제어할 수 있는 객체

   ```js
   console.log(location.href);    // 현재 페이지의 URL
   location.href = "https://example.com"; // 해당 URL로 이동
   
   console.log(location.hostname); // 도메인 이름
   console.log(location.pathname); // 경로
   console.log(location.search);   // 쿼리스트링

4. screen

   1. 사용자의 디스플레이 화면 정보를 제공하는 객체입니다.

   ```js
   console.log(screen.width);  // 화면의 너비
   console.log(screen.height); // 화면의 높이
   console.log(screen.availWidth);  // 사용 가능한 화면 너비
   console.log(screen.availHeight); // 사용 가능한 화면 높이

5. navigator

   1. 브라우저에 대한 정보를 제공하는 객체

   ``` js
   console.log(navigator.userAgent); // 사용자 브라우저 정보
   console.log(navigator.language);  // 사용자의 언어 설정
   ```

6. alert()`, `confirm()`, `prompt()

   1. 대화 상자를 생성할 수 있는 메서드를 제공하는 객체

   ```js
   alert("알림 메시지!"); // 경고 창 띄우기
   
   var isConfirmed = confirm("계속 진행하시겠습니까?"); // 확인 창 띄우기
   console.log(isConfirmed); // true 또는 false 반환
   
   var name = prompt("이름을 입력하세요:", "기본값"); // 사용자 입력받기
   console.log(name);
   ```



## BOM과 DOM의 차이점

| 항목      | BOM                                                       | DOM                                          |
| --------- | --------------------------------------------------------- | -------------------------------------------- |
| 정의      | 브라우저 창과 관련된 객체 모델                            | HTML 문서의 구조를 객체로 표현한 모델        |
| 제어 대상 | 브라우저 창, URL, 기록, 화면, 사용자 인터페이스 등        | HTML 요소 및 내용                            |
| 객체 종류 | `window`, `navigator`, `location`, `history`, `screen` 등 | `document` 객체를 통해 접근 가능한 HTML 요소 |
| 예시 기능 | 페이지 이동, 화면 크기 확인, 쿠키 관리, 팝업 창 제어 등   | HTML 태그 추가, 변경, 삭제, 이벤트 핸들링    |