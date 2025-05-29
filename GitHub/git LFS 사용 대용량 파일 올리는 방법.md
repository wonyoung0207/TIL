# Git 용량초과 해결방법

---

> [참고사이트1](https://vateran.tistory.com/51)
>
> [참고사이트2](https://velog.io/@luvlik207/github%EC%97%90-%EB%8C%80%EC%9A%A9%EB%9F%89-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C%ED%95%98%EA%B8%B0)
>
> [참고사이트3](https://medium.com/@stargt/github%EC%97%90-100mb-%EC%9D%B4%EC%83%81%EC%9D%98-%ED%8C%8C%EC%9D%BC%EC%9D%84-%EC%98%AC%EB%A6%AC%EB%8A%94-%EB%B0%A9%EB%B2%95-9d9e6e3b94ef)
>
> [참고사이트4](https://mintaku.tistory.com/14)
>
> [BFG 사이트](https://rtyley.github.io/bfg-repo-cleaner/)
>
> [LFS 사이트](https://git-lfs.github.com/)

## 문제점

1. Bitbucket과는 달리 Github에는 기본적으로 100MB 이상의 파일을 올릴 수 없다.
2. 깃허브 저장소에는 용량 제한이 없지만, 파일 하나당 100메가의 제한이 있다.
   따라서, 파일이 50메가만 넘어가도 warning 메시지가 뜨고,
   100메가가 넘어가는 파일을 push 하려는 경우에 다음과 같은 에러 메시지가 나온다.

```bash
remote: Resolving deltas: 100% (3/3), completed with 3 local objects.
remote: error: GH001: Large files detected. You may want to try Git Large File Storage - https://git-lfs.github.com.
remote: error: Trace: 51efcb860cd7c76aa349c5f931ebc96bedfc30763349d284be7278a271fb56ae
remote: error: See http://git.io/iEPt8g for more information.
remote: error: File ***/***.exe is 144.19 MB; this exceeds GitHub's file size limit of 100.00 MB
To https://github.com/***/***.git
 ! [remote rejected] main -> main (pre-receive hook declined)
error: failed to push some refs to 'https://github.com/***/***.git'
```

## 해결책 

- Git LFS 설치로 해결할 수 있다. 
  - [Git LFS 다운로드 페이지](https://git-lfs.github.com/)
- LFS 란?
  - Large File Storage 의 약자이다 
  - LFS(Large File Storage) 는 큰 파일을 처리하기 위한 github에서 만든 오픈소스이다.
  - 원래 github 에는 큰 용량의 이진파일을 처리하는데 적합하지 않다. 바이너리는 소스코드와는 다르게 diff 를 용이하게 처리하는데 어려움이 있기 때문에 이진파일인 경우 형상관리를 하는데에 문제가 있을 수 밖에 없는것이다. 
  - 바이너리를 계속 Push 하게 되면 github 에서는 해당 파일에 대한 history 를 관리하기 위해 용량을 낭비하게 되는 것이다.
  - 그래서 LFS 는 대용량 파일을 그대로 동일한 repository 에 저장하지 않고 분리된 Storage에 저장을 해두고, 해당 파일을 마치 C의 포인터처럼 가리키고 있는 방식으로 동작하게 된다. 
- 따라서, 대용량 파일을 Git 내부의 텍스트 포인터로 대체하고, 파일들을 원격 서버에 저장하는 방법이다. 

## LFS 이용방법

1. Git LFS 설치

   - [Git LFS 다운로드 페이지](https://git-lfs.github.com/)

2. 다운받은 lfs를 인스톨하기 

   - 명령어 : git lfs install
     - 관리하고자 하는 레포지토리(로컬 프로젝트 경로) 에서 cmd 로 설치 명령을 입력한다.

3. lfs로 관리할 track 설정

   - 명령어 : git lfs track "*.lib"
     - 100MB 이상의 파일을 lfs 로 관리 등록을 한다.
   - 명령어 : $ git lfs track “\*.exe”
     - exe파일을 lfs로 관리등록한다. 

4. 다시 커밋하기 

   - git commit -m "메시지"

   - 이전에 100MB 이상 파일을 커밋한 적이 없다면 이대로 push 하면 된다.
   - **다음 에러발생시 해결방법**
     - commit 했을때, 이전에 100MB넘는 파일을 commit 시도했다면 에러가 발생한다. 
     - 해결방법 
       - [BFG Repo-Cleaner 이용](https://rtyley.github.io/bfg-repo-cleaner/)

## BFG 이용방법

- 기존 Commit에서 100MB보다 큰 파일의 로그를 강제로 없애줘야 한다.

1. BFG 사이트에서 bfq-x.x.x.jar (x.x.x는 버전 ) 파일을 다운받는다.

   - 이때, .jar 파일은 java 파일이므로 컴퓨터에 JDK가 설치되어있어야 한다. 
   - JDK 설치 후 환경변수에서 JAVA_HOME 과 path에 Java/bin 폴더를 추가해야한다. 
   
2. 대상이 되는 Repository에서 다음과 같이 그동안의 Commit에 포함된 100MB 이상의 파일을 정리하는 명령을 실행한다.

   ```bash
   java -jar bfg-1.14.0.jar --strip-blobs-bigger-than 100M
   ```

   - 코드 복사 시 bfg 파일 버전을 주의하기 바란다. 자신이 다운 받은 파일 버전을 확인하고 입력하자.

   - **다음 에러발생시 해결방법**

     ```bash
     Scanning packfile for large blobs: 6092
     Scanning packfile for large blobs completed in 67 ms.
     Warning : no large blobs matching criteria found in packfiles - does the repo need to be packed?
     Please specify tasks for The BFG :
     bfg 1.13.0
     ....
     ```

     - 해결방법

       - 해당 레포지토리에서 git bash -> git repack && git gc 
       - 명령어 적용 후 , 앞에서 사용했던 "java -jar bfg-1.14.0.jar --strip-blobs-bigger-than 100M" 명령어 다시실행 

     - 정상작동시 출력화면 

       ```bash
       Scanning packfile for large blobs: 7241
       Scanning packfile for large blobs completed in 70 ms.
       Found 2 blob ids for large blobs - biggest=198766846 smallest=179020480
       Total size (unpacked)=377787326
       Found 1573 objects to protect
       Found 4 commit-pointing refs : HEAD, refs/heads/master, refs/remotes/origin/HEAD, refs/remotes/origin/master
       
       Protected commits
       .....
       ```

3. 실행이 완료되면 다시 commit과 push를 진행해준다.



