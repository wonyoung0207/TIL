# Day4

---

>Stage 02 코드구조 추가 
>
>Stage02 코드 변경 부분 
>
>실행시 에러 ( Error )
>
>문법 ( split(), map의 반복문 내장함수 keys() , next() )

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

### 고친부분

- MemberHelper

  - 이렇게하면 무조건 club의 0번째가 삭제됨 

  ```typescript
  remove(club : TravelClub , clubMember : ClubMember ) : void {
      const index = clubMember.name.indexOf(clubMember.name);
  console.log('clubMember.name.indexOf : ' + index);
  club.members.splice(index, 1);
  }
  ```

  - 바꾼코드

    ```typescript
    remove(club : TravelClub , clubMember : ClubMember ) : void {
        let index : number = 0;
        for( let c of club.members){
            if(c.email === clubMember.email ){
                break;
            }
            index++;
        }
    // const index = clubMember.name.indexOf(clubMember.name);
    console.log('clubMember.name.indexOf : ' + index);
    club.members.splice(index, 1);
    }
    ```

- MemberWindow 

  - 밑의 코드는 그냥 일반 멤버로 설정됨 

  ```typescript
  if(!this.memberHelper.hasMembers(this.currentClub.name)){
      newMember.role = memberRole as RoleInClub;
  }
  ```

  - 클럽에 멤버가 아무도 없다면 해당 사람을 president로 설정하도록 만듬 

    ```typescript
    if(!this.memberHelper.hasMembers(this.currentClub.name)){
        newMember.role = memberRole as RoleInClub;// 멤버가 없으면 그 사람을 president로 설정
    }
    ```

- ClubWindow

  - register() 부분에서 newClub 객체를 생성하면 foundedDate 가 자동 셋팅됨 ( 생성자 안에 있음 ). 근데 객체 생성 후 또 foundedDate 해줌. 

    ```typescript
    // newClub.foundedDate = DateUtil.today(); // => 어차피 생성자에서 생성함 . 삭제해도 됨 
    ```

    


## 실행 에러

1. try - catch 에서 error메시지 띄우기 

   - 기존 error 의 타입이 any라 그냥 쓸수 있었지만 v4.4 부터 error object이 unknown type으로 정의되어 그냥 사용하면 ts error가 발생한다. 
   - 해결방법은 3가지이다. 
     1. 버전낮추기
     2. any 타입으로 변경 
     3. 새로운 Error타입 만들기 ( interface 적용해서 타입표명 )

   ```typescript
   // 방법 1
   try {
     // your logic
   } catch (error: any) {
     // error logging
     console.log(error.message)
   }
   
   // 방법2
   catch (error) {
     let msg = (error as Error).message;
   }
   ```

---

## 문법

### arr.splice( 시작위치, 삭제할 갯수 )

- 배열의 값을 삭제할 때 사용된다. 
- 배열의 시작위치부터 몇개를 삭제할 지 정한다. 

### Map

- 내장함수 

  - hasNext() : 다음 객체가 존재하는지 확인하는 메소드 => true , false 로 리턴

  - **next()** : 다음 요소를 선택하도록 반환  => 읽어올 요소가 남아있는지 확인 

    - 리턴값은 두개의 파라미터로(value,done) 이루어진 객체이다. 
    - **value : iterator의 다음번째 값** 
    - **done : 마지막 값이 사용된 경우 true로 변경됨 . 그 전까지는 계속 false**

  - ketset() :  저장된 모든 키를 객체형태로 반환한다.

    - iterator : collection 에 있는 요소들을 순회하는 인터페이스

  - **keys()**  : map에 있는 key값들을 iterator 객체로 반환 

    - 리턴형식은 A new Map iterator object. 

    ```java
    map.put("A", 1);
    map.put("B", 2);
    map.put("C", 3);
    Set keyset = map.keySet();
    System.out.println("Key set values are" + keyset);// (result) Key set values are [A,B,C]
    ```

  - 질문

    1. map.keys() 가 반환하는 데이터 형식은?? 

       - 리턴형식은 A new Map iterator object.

    2. iterator.next() 가 반환하는 형태는 어떻게 되나요??

       - value 와 done으로 이루어진 객체 반환 

         - value 에는 iterator 의 다음번째 인덱스 값. done은 마지막 

       - 다음값이 있으면 done 이 true됨 

         ```typescript
         console.log(iterator.next()); // { value: 0, done: false }
         console.log(iterator.next()); // { value: 1, done: false }
         console.log(iterator.next()); // { value: 2, done: false }
         console.log(iterator.next()); // { value: undefined, done: true }
         ```

    3. map 에 내장되어있는 함수( keys, next 등 ) 에 대한 공식 문서를 찾아보고 싶습니다. 어느 사이트를 들어가야 map에 대한 공식 자료를 찾을 수 있을까요??

       - https://developer.mozilla.org/  : 해당 사이트에서 찾을 수 있다. 

- 자바 방법

  ```java
  // 방법1
  Iterator<String> keys = map.keySet().iterator();
  while( keys.hasNext() ){
      String key = keys.next();
      System.out.println( String.format("키 : %s, 값 : %s", key, map.get(key)) );
  }
  
  // 방법2
  for( Map.Entry<String, String> elem : map.entrySet() ){
      System.out.println( String.format("키 : %s, 값 : %s", elem.getKey(), elem.getValue()) );
  }
  
  // 방법3
  for( String key : map.keySet() ){
      System.out.println( String.format("키 : %s, 값 : %s", key, map.get(key)) );
  }
  ```

  