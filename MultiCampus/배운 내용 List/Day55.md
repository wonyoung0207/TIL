# Day55

---

> NCP (Naver Cloud Platform)
>
> 리눅스 기본 명령어 정리 
>
> git branch 에 대해 학습하기 

## NCP (Naver Cloud Platform)

- 서버생성
  1. 서버 이미지 선택 
     1. 이미지 타입 - os
     2. os 이미지 타입 - all
     3. 서버타입 - Compact
     4. 서버 이미지 이름 - centos-7.2-64
  2. 서버 타입

- putty
  - server에 접속하기 위한 도구
  - 포트 포워딩 정보를 이용해서 접속한다. 
    - Server 에 접속만 가능한 포트와 포워딩이다. 

- workbench 같은 다른곳에서 접속하기 위해서는 Server에 설정되어있는 공인 IP 로 접속해야한다. 

- 비공인 ip 

  - 서버에서 사용되는 ip로 , **이름표** 정도로 이해하면 된다. 
  - 클라우드 안에서의 ip 

- 공인 ip 

  - 전세계 어디서든 접속할 수 있는 주소 

- | 0.0.0.0/0              | 1-65535                        |
  | ---------------------- | ------------------------------ |
  | 어떤 ip에서나 접속가능 | 1번~ 65535 까지 포트 사용 가능 |

---

## Linux 기본 명령어

> [기본 명령어 정리 ](https://cafe.naver.com/2022webservice?iframe_url=/MyCafeIntro.nhn%3Fclubid=30692828)

### 1.자동완성 

- Tap

### 2.vi편집기 실행

- vi test.txt 
  - test.txt 파일이 있으면 실행되고, 없으면 새로 만들어서 실행된다.
- [vi 명령어](https://www.notion.so/96d921122e3f4fd2b36064e3b0d2353a)
  - w : write
  - q : quit
  - q! : 변경사항 있을때도 강제 종료 

### 3. 파일 내용 확인

- cat  "파일 이름.파일형식"

### 4. 파일 및 디렉토리

- 디렉토리 변경
  - 절대 패스 : cd /home/student 
  - 상대 패스 : cd../home/student 
- 현재 작업 디렉토리의 위치와 상관없이 홈 디렉토리로 이동
  -  cd 
- 현재 작업 디렉토리 절대 패스로 출력
  - pwd
- 디렉토리 파일목록 출력
  - ls "디렉토리명" 
    - 간략히 보기
  - ls -l 
    - 자세히 보기
  - ls -a 
    - 숨김파일 보기(.으로 시작하는 파일)
  - ls -al  
    - 숨김파일까지 자세히 보기
    - -al 로 표시되는 파일은 파일 종류, 접근권한, 링크수, 소유자, 소유그룹, 파일크기, 마지막 수정일시, 숨김파일등 으로 나온다.
  - [파일 종류](https://www.notion.so/0b1699b0b7b6437f891e216e2c25c860)
- 디렉토리 생성 및 삭제
  - mkdir  "디렉토리 이름"
    - 생성
  - rmdir "디렉토리 이름"
    - 삭제 - 디렉토리 안에 다른 파일이나 디렉토리가 존재하지 않아야만 가능  
- 파일 복사, 삭제, 파일 및 디렉토리 이동 
  - cp  "파일명.확장자"  "(복사한)파일명.확장자" 
    - 복사 
  - rm  "파일명.확장자"
    - 삭제
  - rm -r filename 
    - 삭제시 삭제할건지 물어봄
  - rm -rf filename
    - 강제 삭제 
  - mv  "파일명.확장자"  "이동할 경로
    - 이동
  - "/"(변경가능)파일명.확장자"
  - mv "이동할 대상"  "디렉토리 이름" 
    - 이동할 대상의 이름은 그대로 유지

### 5. file 종류 표시

- file 대상파일경로(혹은 파일경로패턴)
- 파일 찾기 
  - find 검색시작위치 -name "파일명패턴"# 특정 파일명 패턴을 갖는 파일들을 검색해서 지우기find 검색시작위치 -name "파일명패턴" -delete

### 6. 파일 내려받기

- wget 다운로드URL 

  - 이상하게 저장되는 경우가 있어서 다음 명령어 사용 많이함

- 다른 이름으로 저장하기 : wget -O 저장될 파일이름 다운로드URL

- 이어받기 : wget -c 다운로드URL

- 압축하기 : tar zcvf 생성될압축파일이름 압축할원본파일_혹은_디렉토리

- 해제하기 : tar zxvf 압축파일_이름

  - c : 새로운 묶음 만듬
  - x : 묶인 파일 풀어줌
  - f : 묶음 파일의 이름 지정 옵션
  - v : 묶음 파일을 풀거나 묶을 때 과정을 화면에 출력

- zip 명령어 압축/해제 : zip 압축파일이름 압축대상파일이름 

- unzip 파일이름 : gzip 명령어와 달리 묶음 파일을 거치지 않고 바로 압축/해제 가능

  ```
  // 디렉토리를 ZIP 형태로 묶은 파일 전송
  scp -P 60003 a.zip root@27.96.128.120:/root
  
  // CentOS 접속 후 압축 해제 
  unzip  a.zip
  ```

### 7. 파일 설치

- yum (파일 설치)
  - man yum : yum 에 대한 메뉴얼 

### 8. command 지우기 

- ctrl + l : 모든 내용 지우기 

---

## 설치 

### JDK 1.8 설치

```
yum  list  java*jdk-devel
yum install  java-1.8.0-openjdk-devel.x86_64

java -version
```

### mysql 설치 

1. [참고 사이트 ](https://cafe.naver.com/2022webservice?iframe_url_utf8=%2FArticleRead.nhn%253Fclubid%3D30692828%2526articleid%3D317%2526referrerAllArticles%3Dtrue)

2. install 

   ```
   1) yum install -y https://dev.mysql.com/get/mysql80-community-release-el7-5.noarch.rpm
   
   2) yum repolist enabled | grep "mysql.*"
   
   3) yum install -y mysql-server
   
   4) mysql -V
   ```

3. Configuring Server

   1. start & stop

      ```
      systemctl enable mysqld # 재부팅 시 자동 시작하도록 설정
      systemctl start mysqld # 서비스 시작
      systemctl status mysqld # 서비스 구동 여부 확인
      ```

   2. Setting root password

      ```
      grep "temporary password" /var/log/mysqld.log
      ```

   3. root 로 접속 후 비밀번호 변경 ( 반드시 영문 대소, 숫자, 특수문자 들어가야함 )

      ```
      mysql -u root -p
      
      mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY '변경할 비밀번호';
      mysql> exit;
      ```

   4. 비밀번호 정책 변경 

      ```
      mysql -u root -p
      
      mysql> SHOW VARIABLES LIKE 'validate_password%';
      mysql> SET GLOBAL validate_password.length = 5; # 다섯자리까지만..
      mysql> SET GLOBAL validate_password.number_count = 0; # 숫자 필요없엉
      mysql> SET GLOBAL validate_password.policy=LOW; # 정책 수준은 낮게..
      mysql> SET GLOBAL validate_password.mixed_case_count = 0; # 대소문자 필요없엉
      mysql> SET GLOBAL validate_password.special_char_count = 0; # 특수문자 필요없엉
      ```

   5. 사용자 생성 및 데이터베이스 생성 

      ```
      mysql -u root -p
      
      mysql> use mysql;
      mysql> select host, user from user;
      
      mysql> CREATE USER '아이디'@'%' identified by '비밀번호'; # 외부접속만 가능한 계정 생성
      mysql> CREATE DATABASE DB이름 default character set utf8;
      mysql> GRANT ALL PRIVILEGES ON DB이름.* to '아이디'@'%'; # 해당 DB에 대한 권한 부여
      mysql> flush privileges; # 새로고침 똭!
      mysql> select host,user from user; # 다시 확인
      ```

### mysql 삭제

1. mysql stop 하기
   systemctl stop mysqld

2. version 확인
   yum list installed | grep mysql

3. 삭제
   yum remove -y mysql-community-*

4. dir 삭제
   cd /var/lib
   mysql 관련 dir 삭제
   rm -rf mysql


---


## Git branch

> [다중 branch 예시](https://backlog.com/git-tutorial/kr/stepup/stepup2_6.html)
>
> [git 특강 주소](https://url.kr/r9p3oi)

### 브렌치 개념

- ranch는 `나뭇가지`라는 뜻의 영어 단어입니다.
- 즉 `브랜치`란 나뭇가지처럼 여러 갈래로 작업 공간을 나누어 **독립적으로 작업**할 수 있도록 도와주는 Git의 도구입니다.
- 장점
  1. 브랜치는 독립 공간을 형성하기 때문에 원본(master)에 대해 안전합니다.
  2. 하나의 작업은 하나의 브랜치로 나누어 진행되므로 체계적인 개발이 가능합니다.
  3. 특히나 Git은 브랜치를 만드는 속도가 굉장히 빠르고, 용량도 적게 듭니다.
- 그래도 브랜치 꼭 써야하나요?
  1. 일단 master 브랜치는 상용을 의미합니다. 그래서 언제든 세상에 공개되어 있습니다.
  2. 만약 상용에 에러가 있어서 고쳐야 한다면 어떻게 해야할까요?
  3. 고객들이 사용하고 있는데, 함부로 버전을 되돌리거나 삭제할 수 있을까요?
  4. 따라서 브랜치를 통해 별도의 작업 공간을 만들고, 그곳에서 되돌리거나 삭제를 합니다.
  5. 브랜치는 완전하게 독립이 되어있어서 어떤 작업을 해도 master에는 영향을 끼치지 못하죠.
  6. 그리고 이후에 에러를 해결했다면? 그 내용을 master에 반영할 수도 있습니다!
  7. 이러한 이유 때문에 Git에서 브랜치는 정말 강력한 기능 중의 하나라고 할 수 있습니다.

### 방법

1. git clone '주소' 
   - github에 있는 repository 내용을 가져온다. 
2. 해당 파일을 git 으로 관리하기 
   - clone 으로 가져온 repository를 'git init ' 명령어를 이용해 git이 관리하는 파일로 만든다. 
3. git branch b1
   - 브랜치 b1 을 생성
4. git switch b1
   - 브랜치 b1으로 Head 변경
5. b1 에서 작업 후 add , commit , push b1 -> push 할때 master가아닌 b1으로 해야함 
6. github에서 'request branch ' 신청 후 수락

### 같은부분을 고친 branch 합치기

- 만약 같은 부분을 고친 2개의 브랜치를 master로 합칠경우 merge error가 난다. 
- 둘 중 어떤것을 고를것인지 git이 보여준다. 선택하고 다시 merge 하면 된다. 
