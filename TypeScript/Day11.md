# Day 10

---

>Stage03 

## 세부적 내용

- boardId와 ClubID는 동일하다. 

- TravelClub 만 AutoIdEntity이다. 

- membership.memberEmail과 memberId는 같은값이다. 

- console에서 try, catch 사용 
  - while() 문으로 입력받기 . 그 안에서 try catch 사용
  
- 콘솔에서 입력받을 값들은 DTO에보면 알수 있다. 
  - ex) register시 입력값은 dto의 생성자 보면 됨 
  
- postingservie에서 boardid찾으려면 findbybodardId 메소드 이용 -> 해당 메소드에서 current.boardid하면 됨

- Array.from은 serviceLogic 에서 사용 

- 콘솔에서 찾고자 하는 값이 있다면 해당 함수는 serviceLogic 에서 찾으면 된다. 

- console에서 current 객체 가지고있어야 하는 콘솔
  - ClubMembershipConsole => currentClub
  - PostingConsole => currentBoard
  
- board는 clubName을 이용해 찾느다. 

- membershipList 가진 Entity
  - CommunityMember
  - TravelClub 
  
- 콘솔의 remove와 modify 에서 this.findOne() 사용함 => 해당 함수는 targetDto 또는 null 객체반환

- 메소드 뜻

  - by : 뒤에오는것을 이용해 찾아라
  - in : 뒤에오는것을 넣어라 

  ```
  if('getId' in club || 'setAutoId' in club){
              if(this.autoIdMap.get(className) === undefined){
                  this.autoIdMap.set(className , Number(club.getId()));
              }
              let keySequence = this.autoIdMap.get(className);
              if(keySequence !== undefined ){
                  const autoId = keySequence.toString();
  
                  club.setAutoId(autoId);
  
                  this.autoIdMap.set(className, ++keySequence);
                  console.log('clubMapStore for create club : ' + keySequence);
              }
          }
  ```
