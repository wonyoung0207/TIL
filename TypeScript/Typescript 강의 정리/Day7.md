# Day 7

---

>Stage03 시작 
>
>DTO와 DAO , Entit 개념
>
>in 연산자 
>
>Array.from()

## Stage 03 코드 구조

### **명령 접수**(TravelClubConsole) 로 메뉴를 보여줌 

- 해당 명령 접수의 메뉴는 2가지로 나뉨
  - 여행클럽
  - 클럽회원

- 메뉴별로 처리 객체에게 일을 건내는 일은 **콘솔**인 ClubWinow, MemberWindow가 함 

### **처리자**

- 처리자는 명령 접수를 받아 처리하는 것으로,  한명의 처리자가 '여행클럽' 과 '클럽회원' 두가지를 맡으면 복잡하기 때문에 2개로 나눈다
  - TravelClubCoordinator : 여행 클럽의 기능을 맡는다.;
  - ClubMenberHelper (클럽 회원 처리 도우미 ) : 클럽회원과 관련된 기능을 맡는다.
  - ClubMemberHelper는 TravelClubCoordinator를 돕는 도우미이므로 정보를 소유하지 못한다. 

### **짐꾼** 

- MapStorage 라는 저장공간에서 데이터를 꺼내거나 저장하기 위해 데이터의 운반을 하는 객체
  - 처리자가 짐꾼에게 Storage에서 정보 찾아달라고 부탁함 
- TravelClubStroe : 여행클럽에 대한 정보를 담는다. 
- ClubMemberStore : 클럽 멤버에 대한 정보를 담는다. 

### 게시판

- Posting 이 게시글, SocialBoard가 게시판 
- 여행클럽 안에 게시판이 있고 게시판 안에 게시글이 있다.
  - 따라서 게시판을 만들때 어떤 여행클럽의 게시판인지 전달한다. => 클럽 하나당 하나의 게시판을 가진다. 
  - 게시글을 생성할 때 무조건 어떤 게시판인지 매개변수로 할당받는다. => 게시글마다 구별할 수 있는 key(usid) 가 존재한다. 
- Main 메뉴는 3개로 나뉜다.
  1. 클럽 관리
  2. 회원관리
  3. 게시판 관리
- 게시판 관리 메뉴를 통해 게시글을 등록, 수정, 조회, 삭제 등의 기능을 수행한다. 

### 멤버십

- ClubMembership 클래스를 중간에 두어 클럽에 여러 회원이 가입하고 회원은 여러 클럽에 가입할 수 있게 한다. 
  - 멤버십은 회원정보를 가지고있는 클래스가 아니라 회원정보 중 해당 클럽에 가입한 회원정보를 나타내는 클래스이다. 

- CommunityMember 가 회원 개인의 정보를 담는 클래스
- TravelClub 은 클럽정보를 담고있고, 멤버십을 중간에 두고 CommunityMember와 연결된다. 
- 시스템에 등록된 회원을 클럽의 구성원으로 추가하는 일을 하는 클래스이다. 
  - 따라서 멤버십 메뉴로 들어가면 대상 클럽을 먼저 찾고 선택해야한다. 
- 메인메뉴의 클럽관리를 선택하면 클럽 멤버십 관리로 이동할 수 있다. 

### 인터페이스 ( Service )

- service에 요청을 보내 결과값을 console이 받는다. 
- 클럽 콘솔은 요청을 접수해 클럽 서비스에게 요청하고 결과값을 받으면서 협업한다. 
  - 이때 서비스는 인터페이스이고, 일을 처리하는 메소드들은 Logic에 구현되어있다. 

- 구현체가 없는 추상 메소드의 집합으로, **서비스를 설계하고 틀을 잡기 위해 사용**한다. 
  - 따라서 같은 서비스들을 묶어서 인터페이스로 만든다. 
- 게시글 서비스를 인터페이스화 시키고 수행하는 클래스를 만들어 구현체에서 게시글 인터페이스가 이용된다, 
- **서비스 로직**은 **정합성(적합한지 아닌지 체크.) 체크나 계산 등의 처리에만 집중**하고, 저장소에 접근할 일은 스토어에게 위임한다. 

---

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

---

## Entity

### 정의

- 실체, 객체를 뜻한다. 
  - entity는 사람, 물건, 개념, 정의 등과 같이 명사이다. 
  - 업무상 관리가 필요한 것에 사용된다 .
  - '서비스 안에서 저장되어야하는 어떤 것' 이라 할 수 있다

---

### 이해 안가는 부분 코드

1. PostingConsole

   ```typescript
   for(const postingDto of postings){
       console.log(`[${index}] ,` + postingDto.postingDtoInfo);
   }
   ```

   

---

## 문법

### In 연산자

- 형태 

  - 속성 in 객체명

- n 연산자는 기본적으로 **객체 용**이다.

  - 배열에다 in 연산자를 쓰면, 인덱스를 검사하게 되는 꼴이 된다.
  - 객체에다 in 연산자를 사용하면 , 객체안에 있는 메소드, 멤버변수를 탐색한다. 
  - 따라서 in 연산자는 **특정 메소드, 프로퍼티 key값 이름의 존재 여부를 검증하기 위한 목적**으로 많이 사용된다.

- 예제

  ```typescript
  // 배열에 사용한 경우 
  var trees = new Array("redwood", "bay", "cedar", "oak", "maple");
  0 in trees         // true를 반환합니다.
  3 in trees         // true를 반환합니다.
  (1 + 2) in trees   // true를 반환합니다. 연산자 우선 순위에 의하여 이 구문의 괄호는 없어도 됩니다.
  6 in trees         // false를 반환합니다.
  "bay" in trees     // false를 반환합니다. 당신은 배열의 내용이 아닌, 인덱스 값을 명시하여야 합니다.
  "length" in trees  // true를 반환합니다. length는 Array(배열) 객체의 속성입니다.
  
  
  
  // 객체에 사용한 경우 
  myObj = {
    no: 1,
    name: 'webisfree',
    getName: function() { return this.name; }
  }
   
  'no' in myObj; // true
  'name' in myObj; // true
  'getName' in myObj; // true
  'url' in myObj; // false
  ```

### Number() 함수로  null 값변경

- 자바스크립트에서는 null값을 Number()형태로 변경하면 0으로 인식한다. 

  ```typescript
  if(this.autoIdMap.get(className) === undefined){
      this.autoIdMap.set(className, Number(club.getId()));// usid = '' 을 Number형태로 변경하면 0이나온다. 
  }
  ```

- null은 0으로, undefined는 NaN으로 반환한다. 

### Array.from()

- 유사배열 객체를 얕은 복사 배열로 바꾸는데 사용한다. => 따라서 객체형태의 유사 배열을 배열의 형태로 바꿔준다.

  - 여기서는 Map.values() 함수를 써서 Iterator 형태로 만들었기 때문에 변환이 배열형으로 변환이 필요하다. 

  ```typescript
  const clubs = Array.from(this.clubMap.values()); // Map.values() 는 배열이 아닌 Iterator객체로 반환한다. 
  
  // Map Iterator 형태 
  const clubIter = this.clubMap.values();
  console.log(clubIter.next().value);
  ```

  

