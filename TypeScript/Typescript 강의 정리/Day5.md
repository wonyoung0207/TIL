# Day5

---

>Stage 02 실행 순서 
>
>싱글톤 패턴
>
>Null 과 undefined 차이점 

## Stage 02 코드 구조

- **명령 접수**(TravelClubConsole) 로 메뉴를 보여줌 
  - 해당 명령 접수의 메뉴는 2가지로 나뉨
    - 여행클럽
    - 클럽회원
- 메뉴별로 처리 객체에게 일을 건내는 일은 **콘솔**인 ClubWinow, MemberWindow가 함 
- **처리자**는 명령 접수를 받아 처리하는 것으로,  한명의 처리자가 '여행클럽' 과 '클럽회원' 두가지를 맡으면 복잡하기 때문에 2개로 나눈다
  - TravelClubCoordinator : 여행 클럽의 기능을 맡는다.;
  - ClubMenberHelper (클럽 회원 처리 도우미 ) : 클럽회원과 관련된 기능을 맡는다.
  - ClubMemberHelper는 TravelClubCoordinator를 돕는 도우미이므로 정보를 소유하지 못한다. 
- **짐꾼** 
  - MapStorage 라는 저장공간에서 데이터를 꺼내거나 저장하기 위해 데이터의 운반을 하는 객체
    - 처리자가 짐꾼에게 Storage에서 정보 찾아달라고 부탁함 
  - TravelClubStroe : 여행클럽에 대한 정보를 담는다. 
  - ClubMemberStore : 클럽 멤버에 대한 정보를 담는다. 

### 실행순서

1. StoryAssistant 가 ClubMenu를 호출
2. ClubMenu는 2가지 길이 있다
   1. Club 에 대한 업무
   2. Club Member 메뉴로 이동
3. ClubMenu 에서 일을 처리하기 위해 ClubWindow를 이용해 처리자인 ClubCoordinator 에게 전달한다. 
4. ClubCoordinator 는 일을 처리하기위한 데이터를 Storage에서 받아온다. 
5. Storage에서 데이터를 받거나 저장하기 위해서는 무조건 Store 객체를 이용해야한다. 

---

## 싱글톤 패턴 

- MapStorage 에서 private constructor() 로 하여 다른 클래스에서 MapStorage 객체 생성을 할 수 없게 만듬 

### 정의

- 싱글톤이란 소프트웨어 전체에서 인스턴스를 하나만 생성해서 유지하는 디자인 패턴
- 보통 객체를 만들 때 하나의 생성자로 여러 개의 서로 다른 객체를 만들 수 있다. 반면 싱글톤 패턴의 객체는 단 하나의 객체만 존재하게 된다. 

### 사용방법

1. 객체를 만들고 해당 객체의 생성자에 private을 걸어 직접적으로 객체생성을 못하게 한다. 
2. 객체안에 static 함수를 두어 함수에 접근하고 해당 함수 안에서 객체를 딱 1번 생성한다. 
   - 만약 이전에 만든 객체가 있다면 객체를 생성하지 않고 만들어져있던 객체를 리턴한다. 

```typescript
class MapStorage{
    private static uniqueInstance : MapStorage;
    clubMap : Map<string, TravelClub>;
    
    private constructor(){ // 싱글톤 패턴=> 객체의 직접적인 생성을 막는다. 
        this.clubMap = new Map<string, TravelClub>;

    }
    static getInstance() : MapStorage{ // 해당 함수를 통해 객체를 1번 만들고 이용할 수 있다. 
        if(!this.uniqueInstance){
            this.uniqueInstance = new MapStorage();// mapstorage공간만들기
        }
        return this.uniqueInstance;
    }
}
```

---

## Null 과 Undefined 차이점

### 공통점

- 둘다 **없음**을 나타내는 값이다. 

### null

- 변수는 존재하나, **null 로 (값이) 할당된 상태.** 즉 null은 **자료형이 정해진(defined) 상태**
- typeof 연산자로 타입 확인시 **Object** 타입이 나온다. 

### undefined

- 변수는 존재하나, **어떠한 값으로도 할당되지 않아** **자료형이 정해지지(undefined) 않은 상태**
- typeof 연산자로 타입 확인시 **undefined** 타입이 나온다. 

```javascript
var var1; //undefined (어떤 값도 할당되지 않아서 자료형을 알 수 없음)
var var2 = null; //null (null로 (값이) 할당되어서 자료형을 알 수 있음 - null의 자료형은 object)

undefined == null //true
undefined === null //false
```



