## DTO 

### 정의

- DTO ( Data Transfer Object ) : 데이터 전송 객체로, 프로세스 간 **데이터를 전달하는 객체**를 의미
  - 자바에서는 계층간 (Controller , View, Business Layer) 데이터 교환을 위한 자바 빈즈( Java Beans) 를 의미한다. 
  - DTO ===> **계층간 데이터 교환이 이루어 질 수 있도록 하는 객체**
- DTO는 로직을 가지지 않는 데이터 객체이다. 
  - getter , setter 메소드만 가진 클래스이다. 

### DAO vs DTO

- DAO ( Data Access Object)
  - DAO는 D**B의 data에 접근하기 위한 객체**로 직접 DB에 접근하여 데이터를 삽입, 삭제, 조회 등 조작할 수 있는 기능을 수행한다.
  - MVC 패턴의 Model에서 이와 같은 일을 수행한다.
- DTO ( Data Transfer Object )
  - 계층간 데이터 교환을 위한 객체로, 데이터의 전송과 계층의 분리를 돕는다. 
  - getter , setter로만 이루어져있다. 

### 사용이유

- JSP 페이지의 로직 부분을 분리해서 코드를 재사용함으로써 프로그램의 효율을 높이기 위해서 사용한다.
  - 데이터를 모아 **한번에 전달하는 방법**이 고안되었고, 이때 모은 데이터 객체를 객체를 DTO라고 한다.
- 유닛 테스트 할 때 굉장히 쉬워진다. 
- 유지보수가 쉬워진다. 

### MVC 패턴

- 비즈니스 처리 로직(Model)과 UI 영역 (View)은 서로의 존재를 인지하지 못하고, Controller가 중간에서 Model과 View의 연결을 담당한다.
- Model과 View를 분리함으로써 서로의 의존성을 낮추고 독립적인 개발을 가능하게 한다. 
- Controller는 View와 Model의 데이터를 주고받을 때 별도의 DTO를 주로 사용한다. 