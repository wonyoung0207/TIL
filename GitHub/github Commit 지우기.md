# GIt Commit 지우는 방법

---

> [참고사이트 ](https://abled.tistory.com/13)

## Commit 확인하기 

```bash
git log
```

## 최신 Commit 지우기 

```bash
$ git reset HEAD^
```

- 이전에 했던 Commit 을 지운다. 
- HEAD가 이전에 Commit 한 것

## 특정 commit 취소하기 

- 특정 커밋으로 되돌아가 그 이후의 커밋을 삭제하는 방법을 

  - git reset 명령 다음에 **커밋 해시**를 적어주면 됩니다.

- 커밋 해쉬는 git log 명령어를 통해 얻을 수 있다. 

  ```bash
  wony@ 바탕 화면/깃허브/University-Programming-Classes (master)
  $ git log
  commit 4a55b92dca686acb3830809ee0b8200ff445356d (HEAD -> master, origin/master, origin/HEAD)
  Author: wonyoung0207 <ahnwy27@gmail.com>
  Date:   Sat Oct 8 15:07:59 2022 +0900
  
      Uploade 학교수업 3 Large File Storage & BFG 적용
  
  commit 73a77e4542b4e1925a454ad7b56d9e2ad60cfdda
  Author: wonyoung0207 <ahnwy27@gmail.com>
  Date:   Sat Oct 8 13:46:55 2022 +0900
  
      학교수업 올리기
  
  commit 7343559aa7a3170a9559f0da6b18386c3491bc41
  Author: WonYoung Ahn <66015941+wonyoung0207@users.noreply.github.com>
  Date:   Sat Oct 8 13:36:31 2022 +0900
  
      Initial commit
  ```

  - commit 뒤에오는 해쉬값이 "커밋해쉬" 이다. 
    - ex ) Head (최신) 커밋 : 4a55b92dca686acb3830809ee0b8200ff445356d
    - 3번째 커밋으로 옮기는 방법 : 
      - git reset 7343559aa7a3170a9559f0da6b18386c3491bc41
  - 커밋해쉬를 이용하면 다른 커밋들은 삭제되지 않고 지정한 커밋만 최신 커밋으로 이동한다. 
    - 해당커밋 이후의 커밋들을 삭제하고 해당 커밋과 전 커밋만 남기고 싶다면 git reset에 **hard**라는 옵션을 붙여주면된다. 

## 해당 커밋이후에 만들어진 커밋 다 삭제하기 

- 수정 내용까지 취소하고 싶으시다면 git reset에 **hard**라는 옵션을 붙여주시면 됩니다.

- hard옵션은 커밋과 스테이징, 파일 수정을 하기 전 상태로 작업 트리를 되돌리는 옵션, **이 옵션으로 되돌린 내용은 복구할 수 없으니** 주의해야한다. 

  ```bash
  $ git reset --hard 7343559aa7a3170a9559f0da6b18386c3491bc41
  ```

  - 이렇게하면 해당 커밋이후에 했던 커밋들이 지워진다. 

## reset의 옵션

| --soft  | 최근 커밋을 하기 전 상태로 작업 트리를 되돌립니다.           |
| ------- | ------------------------------------------------------------ |
| --mixed | 최근 커밋과 스테이징을 하기 전 상태로 작업 트리를 되돌립니다. 옵션 없이 git reset 명령을 사용할 경우 이 옵션을 기본으로 합니다. |
| --hard  | 최근 커밋과 스테이징, 파일 수정을 하기 전 상태로 작업 트리를 되돌립니다. 이 옵션으로 되돌린 작업은 복구할 수 없습니다. |

- 