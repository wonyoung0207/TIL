# Day3

---

> Stage 02 시작 
>
> 티어와 레이어 개념 정리 
>
> 정규표현식 
>
> 논리연산자 특이한 사용 ( !!, || , && )
>
> 타입표명 (  AS 문법)
>
> interface ( 덕타이핑 )
>
> 유니온 (Union)

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


## 티어와 레이어 

### 티어

- 물리적인 서버
- 처리대상 (UI , 로직, 데이터) 가 주로 티어와 일치한다. 
- 실행 프로세스와 거의 일치한다. 
- 종류
  - 클라이언트 티어(개인 pc) , WAS티어 ( WAS 서버), 데이터 티어( DB서버 )

### 레이어

- 시스템의 논리구조
- 레이어 나누는 기준
  - 서로 다른 작업은 서로 다른 레이어에서 한다.
  - 인접레이어의 변화 충격을 흡수하는 레이어를 둔다. 
- 종류
  - UI레이어, 프리젠테이션 레이어, 서비스 발행 레어이어, 비즈니스 로직 레이어,데이터접근 레이어, 데이터 레이어 등으로 나눌 수 있다. 

### 역할 나누기

1. 표현
2. 처리
3. 저장

- 3가지로 나눠 역할을 나누면 구현하기 쉽다. 

---

## 문법

### Static 과 final

- static : 정적변수
  - 객체에 소속된 멤버가 아닌 클래스에 고정된 멤버. 
  - 별도의 메모리 영역에 적재되어 모든 객체가 메모리를 공유한다. 
  - static 변수는 Heap 영역이 아닌 Static 영역에 할당. 
  - Grarbase Collection 의 관리영역 밖이기 때문에 프로그램이 끝날 때 까지 해당 메모리 영역을 차지한다. 따라서 남발하면 프로그램에 악영향을 줄 수 있다. 
- final : 변하지 않는 값 (상수) == const
  - 초기화시 설정된 값을 변경할 수 없다. 

### 정규표현식

- 특정 패턴의 문자열을 찾기 위한 표현 방식 
- 형식 
  - 슬래시(/) 사이에는 매칭시킬 패턴을 써준다. 
  - 슬래시 다음에는 옵션을 설정하는 플래그를 써준다. 

- 정규표현식 검색 패턴 
  - \[  ] : 괄호안의 문자들 중 하나
  - \[ ^문자 ] 괄호안의 문자를 제외한 것 
  - ^문자열 : 특정 문자열로 시작 
    - ^ : 정규표현식 시작 
  - 문자열$ : 특정 문자열로 끝남
  - \b : 단어의 처음 / 끝 
  - $: 정규표현식 끝
  - \ (역슬레쉬) : 다음 오는 문자가 특별한 문자임을 나타낸다. 
    - \n : 새줄
    - '\\\ ': \ 를 의미 
- 정규표현식 갯수 패턴
  - ? : 최대 한번
  - \* : 여러개 포험
  - \+ : 최소 한개
  - {1,3} : 1개 이상 3개 이하

- 플래그
  - g ( Global ): 매칭되는 모든 문자 검색 ( 안쓰면 매칭되는 첫 문자만 검색 )
  - i  ( ignore case) : 대소문자 구분 안함 
  - m ( Multi line ) : 여러 행의 문자열에 대해 검색 

```typescript
isValidEmailAddress(email: string): boolean {
    const ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    return !!email.match(ePattern);
}
// 1. ^ : 정규표현식 시작 
// 2. [a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]
// 3. @ : @ 중심으로 나뉨 ( email ) 
// 4. (\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]) // => IP 주소 나타냄. 
// 5. | => 또는 
// 6. (([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,})
// 7. $: 정규표현식 끝

```


### ("문자열").match.( / 정규표현식 / 플래그)

- 문자열에서 정규표현식에 매칭되는 항목들을 배열로 반환 

### 논리연산자의 특이한 사용 

- or 연산자 ( || ) : 첫번째 값이 존재하면 첫번째 값을 리턴함. 그렇지 않다면 2번쨰 값 리턴 
  - 
- and 연산자 ( && ) : 두개의 값이 모두 true이면 가장 마지막값을 리턴함 .
  - 둘 중 하나라도 false면 false를 리턴한다. 

```typescript
// OR 연산자 
newValueMap.set('phoneNumber', newPhoneNumber || foundMember.phoneNumber); // => newPhoneNumber에 값이 있으면 해당값이 map의 value로 들어가고, 만약 없다면 foundMember.phoneNumber 가 value로 들어간다. 

// And 연산자 
// 조건문 사용 
if(person.age > 20 ){ 
	console.log('운전가능 ')
} else{
    console.log('운전불가 ')
}
// 논리연산자 사용 
console.log( person.age > 19 && '운전가능 ') // person.age >19 와 '운전가능' 이 참이기 때문에 마지막 값인 '운전가능' 을 리턴함 
```

### 논리 연산자 !! 두개 쓰는 이유 

- 한개인 경우 = 부정

- 두개인 경우 = 긍정 ( 부정의 부정 )

  - Boolean형으로 형변환을 위해 사용됨
  - 예를들어 0을 false로, 1을 true로 만들어줄 때에 사용한다. 

- 자바스크립트의 기본 데이터 타입

  - **다음 6가지만 false가 출력되고 이외는 모두 true이다.** 

  | ””        | 빈 문자열          |
  | --------- | ------------------ |
  | false     | 기본 boolean false |
  | NaN       | Not a Number       |
  | undefined | 정의되지 않은 값   |
  | null      | Null값             |
  | 0         | 숫자 기본값        |

### 날짜

- getFullYear() 
  -  getYear() 은 현재연도에서 1900을 빼는것. 현재 연도 구하려면 getFullYear()사용해야함
- getMonth()
  - 현재월을 구하기 위해선 'getMonth() + 1 '을 해야한다. 
  - 왜냐하면 getMonth() 의 값이 0~11까지이기 때문이다. 
- getDate()
  - getDay() 가 아님. 
    - 주(week)에 대한 일(day)를 리턴 => 월요일이면 1, 일요일이면 0 을 리턴 
    - 일요일이 0이다. 

  - getDate() 가 


### Map

- map.keys() : map 의 모든 key들에 대한 iterator 를 배열의 형태로 리턴한다. 

### As 문법

- type Assertion in TypeScript ( **타입표명** )

- 명시적 타입 캐스팅과 비슷하게 사용할 수 있다. 다만, 실제로 타입이 변경되는 것은 아니고 이런 타입이 들어올 것이라는 것을 프로그래머가 명시해주는 것

  ```typescript
  function handler(event: Event) {
  	let element = event as unknown as HTMLElement; // Event 타입이라는 것을 타입표명 했지만 as를 통해 더 구체적으로 나타내줌  
  }
  function handler (event: Event) {
    let mouseEvent = event as MouseEvent;
  }
  ```

### indexOf()

- string.**indexOf**(searchvalue, position)
- 문자열에서 특정 문자열을 찾고 검색된 문자열이 첫번째로 나타나는 **위치** index를 리턴함

---

## Union

### 정의

- TypeScript는 타입들의 조합을 통해 새로운 타입을 정의할 수 있다. 이때 사용하는 것이 union이다.
- 내가 원하는 타입만을 지정하고 싶을 때 사용 => any 타입쓰면 어떤 타입이든지 들어올 수 있기 때문 

### 특징

- 유니온 타입을 사용하면 사용된 두 클래스에 공통인 멤버에만 접근이 가능하다. 
- 따라서 각 타입이 가지는 **고유멤버( 타입이 가지는 내장함수)는 사용할 수 없다**.  => **TypeGuard** 를 사용해 해결할 수 있다. 
- typeguard 방법 3가지 
  1. **typeof 연산자** : number, string, boolean 같은 기본 타입만 체크 가능 
  2. 사용자 정의함수
  3. **instanceof**  : Array 같은 객체 타입 체크 가능 

### 사용방법

- Union Operator( | ) 이용 

```typescript
// 하나의 타입 지정
a(shape : any) { } // 다양한 타입을 받고자 할 떄 any를 쓰고 any는 데이터 타입이든지 들어올 수 있다. 

// 유니온 타입 지정 
 let numOrstr : number | string; // 문자열과 숫자만 담을 수 있다. 

numOrstr = 'abc';
numOrstr.split() // 유니온타입을 사용하면 string 에 있는 고유멤버인 split() 함수를 사용할 수 없게된다
// 따라서 typeguard 를 이용해 해결할 수 있다. 

// typegard 적용 => typeof
if(typeof numOrstr === 'string'){
    numOrstr.split();
}
// typeguard 적용 => instanceof 
if(numOrstr instanceof Array){
    
}

```

---

### 구조분해할당

- 객체에 있는 값을 쉽게 사용할 수있는 방법 

```javascript
let {name, id} = person01;
// person01이라는 객체에서 name, id 라는 key 값을 찾아 value를 리턴한다. 
```

---

## Interface

### 정의

- typeScript 에서의 interface는 여러 타입의 속성을 interface 하나로 나타내기 위해 사용된다. 

### 특징

- 인터페이스에서 정의하는 메소드는 모두 추상메소드이다. 

- 클래스와 다르게 인스턴스화 (객체화 ) 할 수 없다. 

- 인터페이스를 이용해 변수, 함수, 클래스에 타입을 지정할 수 있다. 

  ```typescript
  // interface 적용 전. => 변수마다 모두 타입을 지정해줘야한다. 
  const todo : {id : string, task : string , complete : boolean } = {
      id : 'abc',
      task : 'task',
      complete : true
  }
  
  // interface 적용 후 => 여러군대서 재활용 가능 
  interface Itodo{
      id : string,
      task : string;
      complete : boolean
  }
  
  const todo : Itodo = {
      id : 'abc',
      task : 'task',
      complete : true
  }
  ```

### 사용시 만족해야할 조건

1. 인터페이스 사용한 변수에서는 인터페이스 안에서 정의된 타입들을 모두 정의해줘야한다. 

   - 옵셔널 프로퍼티 
     - 인터페이스에서 필수요소가 아닌 선택적 요소로 변경한 프로퍼티
     - '?' 키워드를 이용하면 변경할 수 있다.  

   ```typescript
   interface Shape {
       p1 : number[],
       p2 : number[],
       area? : number
   }
   
   let rectangle : Shap = {
       p1 : [1,2,3],
       p2 : [4,5,6],
       area : 12
   }
   
   // 이때 area 는 '?' 를 붙인 선택적 요소이기 때문에 다음과 같이 생략할 수 있다. 
   let rectangle : Shap = {
       p1 : [1,2,3],
       p2 : [4,5,6]
   }
   ```

2. readonly 사용

   - interface 정의 시 프로퍼티 앞에 readonly를 사용하면 해당 인터페이스가 적용된 객체안에 있는 readonly 프로퍼티는 추후 값을 변경할 수 없다. 
     - 따라서 readonly는  const와 동일한 기능을 한다. 
   - const는 변수선언시 사용, interface의 readonly는 특정 프로퍼티를 상수화 시키고자 할 때 사용 

   ```typescript
   interface p {
       readonly x : number,
       y : number,
   }
   
   let test : p = {
       x : 10, 
       y : 4
   }
   test.x = 11 //  변경시 에러남. readonly이기 때문에 
   ```

3. Class와의 관계

   - 클래스 구현에 interface를 사용했다면 해당 클래스는 interface의 프로퍼티( 멤버변수 ) 와 메소드를 모두 요소로 구현해야한다. 
     - interface는 기본적으로 추상화가 적용되기 때문에  implements 한 곳에서 모두 구현해야한다. 

### 덕 타이핑 (duck typing)

- 특정 인터페이스에서 정의한 프로퍼티나 메소드를 갖고있는 클래스는 해당 인터페이스를 구현한 것으로 인정하는 것 
  - 따라서 implements 를 하지 않은 클래스더라도 해당 인터페이스의 요소 ( 프로퍼티, 추상메소드 등) 를 가지고 있다면 implements한 것과 동일시 하게 본다. 

