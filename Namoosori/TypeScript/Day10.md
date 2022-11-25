# Day 10

---

>Stage03 

## 최종 순서 정리 

1. Menu  에서 Console  객체 생성
2. Console 클래스의 메소드를 호출
   - 이때 메소드는 사용할 기능 ( find, register 등) 의 기능을 한다. 
3. Console 에서 Service 객체를 생성한 후 입력받은 값을 Service객체로 넘겨준다. 
   - Service객체로 입력한 데이터 넘길 떄 DTO 객체로 만들어서 전달함 
4. 이때 Service 객체는 interface 로 **ServiceLogicLycler** 에 모여있고, createClubService 의 메소드형태 안에서 ServiceLogic 객체를 **싱글톤** 방식으로 생성한다.
5. ServiceLogic에는 Store 객체가 있어 Store객체를 통해 Memory와 직접적인 CRUD를 진행한다. 

### 정리하자면, Console객체에서 ServiceLogicLycler 객체를 통해 ServiceLogic에 접근하고, ServiceLogic안에서 StoreMapLycler 객체의 getInstance() 메소드를 통해 Memory객체생성 후 안에있는 Entity 별 Map 에 접근한다. 

1. ServiceLogic 에서 StoreMapLycler 를 통해 Store객체를 생성한다. 
2. **StoreMapLycler** 는 **싱글톤** 객체로, 모든 Store를 requestClubStore() 의 형태로 싱글톤화 시켜 가지고있다.
3. requestClubStore() 메소드 안에서는 ClubMapStore 객체를 생성하게 되고, 이것을 통해 ServiceLogic에서 Store객체를 사용할 수 있게된다. 
4. ServiceLogic 의 Store 객체를 통해 구현된 ClubMapStore에는 CRUD가 구현되어 있어 MemoryMap 객체를 생성해 데이터에 직접적으로 접근한다. 
5. **MemotyMap**은 **싱글톤** 객체로, Map의 형태로 모든 Entity들의 저장된 데이터를 가진다. 

### 따라서 ServiceLogic에서 MapStore 객체를 생성해 데이터저장소에 접근을 한다. 이때 데이터의 형태를 변형해야 하는데, 이때 사용되는것이 DTO 이다. 

1. DTO에는 FromEntity() 메소드와 ToClassName() 메소드가 구현되어있고, 해당 메소드를 통해 데이터의 형태를 변형한다. 
2. FromEntity() 는 데이터를 View로 리턴할 떄 사용하고, ToClassName() 은 MemoryMap에 데이터를 변경할 때 사용한다.
3. fromEntity와 toClassName은 ServiceLogic에서 구현된다. 

---
## 싱글톤 객체 

- 자기 자신의 static 메소드에서 자기 자신 객체를 생성. 
- 생성자에서 멤버변수 다 null로 초기화 => 딱 한번만 생성되도록 함 

### ServiceLogicLycler

- ServiceLogic 객체 생성 => ClubServiceLogic 형태
- createClubService() 메소드 안에서 객체 생성
- shareInstance() 를 통해 안에있는 메소드들에 접근

### StoreMapLycler

- 모든 Store객체를 생성 => ClubMapStore 형태
- requestClubStore() 메소드 안에서 객체생성
- getInstance() 를 통해 안에있는 메소드들에 접근 

### MemoryMap

- 모든 Entity를 Map형태로 가짐
- getInstance() 를 통해 안에있는값에 접근 



---

##  typeScript정리

- typescript 는 정적타입을 지원해 자바스크립트의 타입추론 문제를 해결한 언어이다.
  - typescript ( 정적언어 ) < === > javascript (동적언어)
- 트렌스 컴파일 과정을 통해 자바스크립트 파일로 변환된다. 
- 생성자에서 접근지정자(private, public ..) 를 붙이면 프로퍼티( 멤버변수 ) 로 인식한다. 
- if( e instanceof Error) => instatnceof 는 타입가드로, e가 Error형임을 보장한다. 
  - 타입가드 사용하면 e의 내장함수를 사용할 수 있다. 
- 인터페이스는 여러 타입을 지정하기위해 사용한다. 
- boardId와 clubId는 항상 동일하다. 

