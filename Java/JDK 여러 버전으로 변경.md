## JDK 여러 버전 전환하기

---

>[참고 사이트1](https://computer-science-student.tistory.com/467)

### 사용 이유

- 설치한 여러개의 JDK를 매번 설정을 바꾸지 않고 cmd에서 간단하게 명령어를 입력해 변경해주는 방법에 대해 정리한다. 
  - 이떄 기본적으로 자바 환경변수가 기본적으로 설정되어 있어야 한다. 

### JDK 설정 방법

1. 내 PC 우 클릭> 속성 > 고급 시스템 설정 > 환경 변수로 이동

2. 시스템 변수 새로 만들기

   <img src="./images/jdk 버전변경1.png" width="700">

3. 변수 이름 : **JAVA_HOME**, 변수 값 : **다운로드받은 JDK 경로** 입력 후 확인버튼 클릭 
   ( ex : **C:\Program Files\Java\openjdk-1.8** )

4. Path 시스템 변수 편집

   <img src="./images/jdk 버전변경1-1.png" width="500">

5. 새로만들기 > **%JAVA_HOME%\bin** 입력 > 확인 버튼 클릭

6. 환경 변수창의 확인 버튼을 누른 후 시스템 창을 닫는다.

### JDK 버전 쉽게 변경할 수 있도록 변경 하는 방법

1. 여러 JDK가 설치되어 있는 폴더(`C:\Program Files\Java`\)에 scripts라는 폴더를 생성

   - 즉, Java 폴더에 "scripts" 이름을 가진 폴더를 만든다. ( 환경설정에서 해당 폴더를 Path에 추가해줄거임 )

2. 환경변수 설정 -> 시스템 변수 영역 -> Path 변수 클릭 후 편집 

3. Path 변수 편집에서 '새로 만들기' -> scripts 파일 경로 추가 (`C:\Program Files\Java\scripts`)

4. 각각의 JDK 마다 .bat 파일 만들기 

   - 해당 폴더에서 바로 만들면 문제가 발생할 수 있으니 다른곳에서 메모장으로 만들고 난 후 .bat 으로 변경한다음 옮기기

   - `.bat` 폴더들은 scripts 폴더 하위에 있어야 한다. 

     ```cmd
     ## 형태
     @echo off
     set JAVA_HOME={JDK 주소}
     set Path=%JAVA_HOME%\bin;%Path%
     echo Java 8 activated.
     
     ## 예시 
     @echo off
     set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_351
     set Path=%JAVA_HOME%\bin;%Path%
     echo Java 8 activated.
     java -version
     ```

   - 만약 java8과 java11이 있다고 하면 아래와 같이 `java8.bat`과 `java11.bat`을 생성하면 된다. 

     - 파일 경로와 파일명은 사람마다 다를 수 있다.

     <img src="./images/jdk 버전변경1-2.png" width="400">

5. cmd에서 .bat 파일명을 cmd에 입력하여 버전이 변경되는것을 확인. 

   <img src="./images/jdk 버전변경2.png" width="700">













