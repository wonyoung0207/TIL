# MPA vs SPA

---

> MPA와 SPA 개념정리
>
> MPA, SPA 에서 CSR과 SSR 개념과의 연관성 

## MPA

### MPA (  Multiple Page Application )

- 여러개의 Page로 구성된 어플리케이션으로, 페이지 요청시마다 새로 렌더링되어 화면을 표시한다. 
- 기존 우리가 사용하는 웹 방식으로, Client 가 페이지를 요청하면 Server에 저장되어있는 페이지를 Response 해줘서 보여준다. 
- 따라서 페이지마다 고유 URL  즉, 한 사이트이더라도 여러 html 페이지를 가진다.

---

## SPA 

### SPA ( Single Page Application )

- 한개의 page로 이루어진 어플리케이션으로, 새로운 페이지 요청시 기존 페이지에서 변경되는 데이터 부분만 다시 렌더링해 표시한다. 
- 처음 URL로 서버에 요청을 보내면 그 결과로 index.html 페이지(첫 페이지)를 Response 한다. 
- 이때 리액트가 컴포넌트화 시켜서 변경 내용이 있을 경우 컴포넌트만 변경해서 다른 페이지로 변경된것 처럼 보이게 된다. 
  - 이 현상의 단점은 새로고침시 첫 페이지인 index.html로 다시 돌아가는것과 북마크 ( 지금 보고있는 페이지 ) 설정이 불가능하다는 단점이 있다. 
  - 따라서 이러한 URL 변경에 따른 페이지 변화를 감지하는 역할을 하는것이 **Router**이다. 
- 정리하자면, SPA는 첫 페이지를 이용해 리액트가 컴포넌트를 변경하여 마치 다른페이지인것처럼 나타낸다. ( URL이 하나이다. )

---

## CSR과 SSR 개념과의 연관성

- **SPA를 CSR(Client Side Rendering) 방식으로 렌더링**한다고 말한다.
- **MPA를 SSR(Server Side Rendering) 방식으로 렌더링**한다고 말한다.