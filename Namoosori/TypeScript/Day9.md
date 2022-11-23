# Day 9

---

>Stage03 
>

## Stage 03 코드 구조

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


### 실행결과

1. club 생성시 usid 는 1부터 늘어난다. 

### 참고할 것

- posting의 usid 는 게시판에있는 postingId로 설정한다.
  - board 안에 -> posting 안에 ->  comment(댓글)
- retrievByName 호출시 어떤 결과 나오는지 





