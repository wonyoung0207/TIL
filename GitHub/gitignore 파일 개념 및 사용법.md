# .gitignore 정리

---

>[참고사이트1](https://velog.io/@psk84/.gitignore-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0)
>
>[참고사이트2](https://growingarchive.tistory.com/244)
>
>[참고사이트3](https://programming119.tistory.com/105)
>
>[참고사이트4](https://jojoldu.tistory.com/307)
>
>[참고사이트5](https://parkjye.tistory.com/28)

##  .gitignore 파일 개념 

- 특수한 파일로, 버전관리를 하지 않고 github에 올리고싶지 않을 때 사용하는 폴더이다.
- 해당 파일 안에 버전관리 하고싶지 않은 파일의 이름을 넣으면 버전관리에서 git이 무시한다.
- 하지만, **이전에 버전관리로 등록해 놓은 파일은 이후에 .gitignore 에 등록해도 git에서 계속해서 버전관리를 한다. **
- 프로젝트 진행시 README.md 파일과 .gitignore 파일을 맨 처음 만드는것이 좋다.
  - 버전관리되고 있는 파일이 있으면 .gitignore 로 관리가 안되기 때문에
- [gitignore 자동 추가 사이트](https://www.toptal.com/developers/gitignore)

	## 사용방법

1. gitignore.io 사이트에 들어가서 제외할 파일유형을 작성 후 복사한다. 

2. 파일생성

   - vsCode 같은 편집기에서 .gitignore 파일을 생성한다. 

3. 생성한 파일 안에 git의 관리에서 제외될 파일을 적는다. 

   - 다음과같은 방법으로 제외 파일을 작성한다. 

     ```bash
     # 파일 제외 (파일명.확장자)
     파일명.txt
      
     # 현재 경로에 있는 파일만 제외 (다른 경로의 동일한 파일명은 추적)
     /파일명.txt
      
     # 특정 경로안의 특정 파일 제외
     폴더명/파일명.txt
      
     # 특정 폴더안의 파일 전부 제외
     폴더명/
      
     # 해당 확장자 파일 전체 제외
     *.txt
      
     # 예외
     !제외할 파일명.txt
     ```

   - 패턴

     ```bash
     '#'로 시작하는 라인은 무시한다.
     표준 Glob 패턴을 사용한다.
     슬래시(/)로 시작하면 하위 디렉터리에 적용되지(recursivity) 않는다.
     디렉터리는 슬래시(/)를 끝에 사용하는 것으로 표현한다.
     느낌표(!)로 시작하는 패턴의 파일은 무시하지 않는다.
     ```

## .gitignore 파일이 동작 안할경우

- push후 gitignore적용이 되지 않을경우 아래의 명령어를 통해 원격 저장소 파일을 제거후 다시 push한다.

  ```bash
  git rm -r --cached .
  git add. 
  git commit -m "커밋메세지"
  git push origin {브랜치명}
  ```

- 적용후 위와 같이 기존에 있던 불필요했던 파일 또는 디렉토리가 제거 된것을 확인할 수 있다.

- 그리고 이후 추가되지 않아야되는 정보는 추가하여 실수로 잘못된 파일이 원격 저장소에 올라가는것을 방지 할수 있다.