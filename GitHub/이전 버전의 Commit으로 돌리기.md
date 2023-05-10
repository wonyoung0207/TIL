# 이전 버전의 Commit 으로 되돌리기 

---

## 방법

1. checkout 이용
   - 현재 소스를 해당 버전으로 돌아가게 한다. 
2. revert
   - reset과 달리 commit의 기록을 유지하면서 상태를 되돌린다. 
   - commit을 지우지 않고 새로운 commit을 추가하면서 내가 원하는 곳으로 되돌아가는 기능이다. 
3. reset 이용
   - 원하는 commit 기록까지 전부 되돌려서 중간에 변경사항들이 조건에 따라 남기도 하고 없어지기도 한다. 
   - 종류
     1. 그냥 reset
        -  reset으로 돌아온 커밋 이후의 변경사항은 모두 **unstaged** 영역에 남는다.
     2. **soft** reset
        - reset으로 돌아온 커밋 이후의 변경사항은 모두 **staged** 영역에 남긴다
     3. **hard** reset
        - 선택한 commit 까지에 대한 변경사항을 모두 제거한다. 
   - 따라서 진짜 과거가 필요없다면 reset을 혹시나 몰라서 남겨나야 겠다면 revert를 사용하면 된다. 
4. Discard
   - 현재 파일 수정후 저장된 상태에서 **Commit을 하지 않았다면**, 다시 직**전 버전의 파일로 돌아갈 수 있다**. 
     - 이때 사용되는 명령어가 Discard이다. 
     - 따라서 소스 코드에서 변경된 부분만을 버리고, 변경되기 전 상태의 소스로 되돌린다. 

## 이용 방법

1. `git log`	 를 통해 Commit 들의 해시코드를 확인한다. 

2. checkout, revert, reset 명령어와 함께 해시코드를 이용해 되돌린다. 

   ```bash
   # 1. Checkout
   # 현재 소스가 해당 버전으로 돌아간다.
   git checkout '복사한 4자리 이상 해시코드'
   
   # 최신 커밋으로부터 한단계 전으로 되돌린다. -> 1부분 2,3.... 으로 변경해 사용가능 
   git checkout head~1
   
   # 되돌아오기(해당 브랜치의 최신 커밋으로 돌리기)
   git checkout '브랜치 이름'
   
   
   # 2. revert
   # 이전 커밋으로 되돌리기. 
   git revert head~1 
   git revert '커밋해시코드'
   
   
   # 3. reset
   git reset --hard '커밋해시코드' 
   혹은 id(해시코드) 대신 
   git reset --hard 'HEAD~ (전단계), HEAD~2 (두 커밋 전), HEAD~3 (세 커밋 전), 같이 사용 가능하다.'
   ```

   