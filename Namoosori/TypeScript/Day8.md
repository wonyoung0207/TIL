# Day 8

---

>Stage03 
>
>
>

## Stage 03 코드 구조

### 게시판

- Posting 이 게시글, Board가 게시판 
  - SocialBoard는현재 board의 정보를 담고있다. 

- 여행클럽 안에 게시판이 있고 게시판 안에 게시글이 있다.
  - 따라서 게시판을 만들때 어떤 여행클럽의 게시판인지 전달한다. => 클럽 하나당 하나의 게시판을 가진다. 
  - 게시글을 생성할 때 무조건 어떤 게시판인지 매개변수로 할당받는다. => 게시글마다 구별할 수 있는 key(usid) 가 존재한다. 
- Main 메뉴는 3개로 나뉜다.
  1. 클럽 관리
  2. 회원관리
  3. 게시판 관리
- 게시판 관리 메뉴를 통해 게시글을 등록, 수정, 조회, 삭제 등의 기능을 수행한다. 

### 게시글

- Posting 객체로, 해당 글은 boardId와 유일한 Key값인 usid를 가진다. 
  - usid 의 형태는   ${this.clubId} : ${this.sequence++} 이다. 

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
  - 이때 서비스는 인터페이스이고, **일을 처리하는 메소드들은 Logic에 구현**되어있다. 

- 구현체가 없는 추상 메소드의 집합으로, **서비스를 설계하고 틀을 잡기 위해 사용**한다. 
  - 따라서 같은 서비스들을 묶어서 인터페이스로 만든다. 
- 게시글 서비스를 인터페이스화 시키고 수행하는 클래스를 만들어 구현체에서 게시글 인터페이스가 이용된다, 
- **서비스 로직**은 **정합성(적합한지 아닌지 체크.) 체크나 계산 등의 처리에만 집중**하고, 저장소에 접근할 일은 스토어에게 위임한다.
- 서비스의 기능들은 Logic 클래스에서 구현한다. 

### 저장 로직 ( Store )

- 저장로직은 저장소에 접근할 수 있는 객체또는 인터페이스이다. 
- MapStore가 map 형태로 데이터 가지고 있는 클래스이고, 해당 클래스는  Store 클래스로부터 implements 받은 클래스이다. 
- 해당 MapStore 객체는 싱글톤 객체로, 하나의 객체만 만들어질 수 있도록 ClubStoreMapLycler 에서 관리한다. 

### dto

- 서버와 view의 중간에서 데이터 전달 역할을 하는 객체이다. 
  - DTO는 fromEntity (DB로부터 가져오는 것) 와 toMethod ( view로 보내주는 것 ) 로 구성되어있다.

### TravelClubDto 와 MemberDto 에 있는 membershipList 의 차이점

- TravelClubDto 에있는 membershipList는 해당 클럽의 멤버십에 가입한 memberList이다. 
- MemberDto 에 있는 membershipList는 멤버 개인이 가입한 membership의 list이다. 

### 실행구조 MVC . 

- StoryAssistant.ts : 프로그램을 실행, ui/menu의 Main Menu로 진입한다
- M (model )
  - da.map : Model (DB와 DB조작 CRUD 메소드가 있는 인터페이스/클래스)
  - io : 총 DB역할을 담당하는 메모리맵
  - ~store / ~storeCycler : CRUD 추상메소드의 인터페이스와 그 사이클러 인터페이스
  - ~MapStore : ~store의 구현 클래스로 실제로 CRUD 액션을 구현, MapCycler 역시 동일.
  - service : Controller/Presenter (화면과 DB를 이어주고 사이의 실제적인 조건 검증 및 액션을 수행하는 메소드)
- C (Controller)
  - DTO : 객체 정보의 **입출납을** 담당하는 데이터 바구니(fromEntity로 가져오고, to속성~ 으로 DTO에 담은 데이터를 transfer해서 쏴준다.
  - service / serviceCycler : **데이터의 가공을 담당**하는 액션 메소드의 선언 인터페이스와 그 cycler
  - ~serviceLogic / serviceLogicCycler : ~service의 구현 클래스(실제 액션이 구현되는 부분)
- V (view)
  - ui : view (사용자가 만나는 화면 및 화면에서의 선택을 송신하는 메소드)
  - console : 사용자가 메뉴에서 선택한 기능을 service에 전달하는 역할
  - menu : 사용자가 실제 접하는 화면 구현부(디스플레이, 메뉴선택)
  - view : 상위 메뉴에 종속된 디테일한 화면 구현부(게시판 화면, 게시글 화면)

### 실행 순서

1. ui의 Menu를 화면에 띄운다

2. menu에서 선택한 번호를 console에 담아 실질적으로 일을 처리하는 service를 호출한다. 

3. service는 일을하기 위한 로직을 실행하기 위해 Logic 클래스를 호출하고, 이때 필요한 데이터를 Store 로 부터 가져온다. 

   1. logic에서 StoreMapLycler 객체로 Store객체를 생성한다. 
   2. Logic 에서 Service 에 있는 추상메소드를 구현하여 해당 메소드들 안에서 Store객체를 이용해 CRUD를 진행한다. 

4. 이때 Store에서 데이터를 주고받을때 사용되는 객체는 DTO로, fromEntity 와 toMethod 로 데이터의 입출력을 실행한다. 

5. 실질적으로 데이터가 저장되어있는 곳은 IO로, IO에서 데이터를 꺼내기 위한 객체가 바로 MapStore이다. 

6. MapStore은 CRUD를 위한 객체로, 데이터를 담는 객체이다. 

   

---

## 



## 코드 작성하면서 해볼거

1. club id 찍어보기 
2. posting 에있는 모든 usid 찍어보기 

