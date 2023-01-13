# GitHub 프로필 꾸미기

---

> [참고사이트](https://pgmjun.tistory.com/21)

## 1. 레포지토리 생성 

- 메인 프로필을 만들기 위해서는 자기 자신의 ID로 된 레포지토리를 생성해야 한다.
  - 나의 ID로 레포지토리를 생성하면 특수 레포지토리로 설정되어 README.md 파일을 꼭 같이 생성해야한다는 주의 문구가 뜬다. 
  - 나의 경우엔 " wonyoung0207 " 로 진행했다. 
- 레포지토리 생성 후 clone을 받아 README.md 파일을 통해 프로필을 꾸밀 수 있다. 

<img src="./images/profile0.png" width=700 >

---

## 2. 헤더 꾸미기 

- README.md 파일의 가장 처음 오는 헤더를 오픈 API를 사용해 꾸밀 수 있다. 

  - [capsule-render API 정의 사이트](https://github.com/kyechan99/capsule-render)

- 이곳에 가면 헤더 사용법을 알 수 있다. 

  - 나의 경우엔 다음과 같은 코드를 사용했다. 

    ```
    ![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=WonYoung%20Ahn&fontSize=70)
    ```

- 주요 속성 

  - type
    - 디자인을 나타냄. wave가 default 로 설정되어 있고, 다양한 type들이 있다. 
  - color
    - 헤더의 색상. 대부분 계속해서 바뀌는 auto 색상을 적용. 
  - text
    - 헤더에 출력할 내용 

<img src="./images/profile1.png" width=600 >

---

## 3. 뱃지 꾸미기 

- 뱃지들은 자신이 습득한 기술들을 보기좋게 나열할 떄 사용한다. 

  - 뱃지들의 형태와 사용방법은 밑의 링크에서 알 수 있다.
  - [뱃지 제공 사이트 ](https://shields.io/)

- 사용 형태는 다음과 같다. 

  ```html
  // 형태 
  <img src="https://img.shields.io/badge/기술이름-#제외색상번호?style=for-the-badge&logo=아이콘이름&logoColor=white">
  
  // 예
  <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white"/> 
  ```

  - Java-007396 : 기술 이름과 색상 코드이다. 
    - 뱃지들 각각의 색상 코드는 밑의 링크에서 찾을 수 있다. 
    - [뱃지 색상 코드 ](https://simpleicons.org/)
  - style : 뱃지의 디자인으로, 여러 종류가 존재한다. 
  - logo : 뱃지의 로고를 정하는 부분으로, 모두 소문자로 작성한다. 
  - logoColor : 뱃지의 색상을 정하는 부분이다. 대부분 white로 설정하지만, 뱃지 배경 색상이 밝은경우 black을 사용한다. 

### 내 뱃지 

```html
<div align=center> 
    <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white"/> 
    <img src="https://img.shields.io/badge/Html5-E34F26?style=flat-square&logo=html5&logoColor=white"/> 
    <img src="https://img.shields.io/badge/Css-1572B6?style=flat-square&logo=css3&logoColor=white"/> 
    <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black"/> 
    <img src="https://img.shields.io/badge/TypeScript-3178C6?style=flat-square&logo=typescript&logoColor=white"/> 
    <img src="https://img.shields.io/badge/Jquery-0769AD?style=flat-square&logo=jquery&logoColor=white"/>
    <img src="https://img.shields.io/badge/JSP-007396?style=flat-square&logo=jsp&logoColor=white">
    <img src="https://img.shields.io/badge/C++-00599C?style=flat-square&logo=c%2B%2B&logoColor=white"/>
    <img src="https://img.shields.io/badge/C-A8B9CC?style=flat-square&logo=C&logoColor=white">
    <img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=python&logoColor=white"/> 
    <img src="https://img.shields.io/badge/Linux-FCC624?style=flat-square&logo=linux&logoColor=black"/> 
    <br>
    <img src="https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=react&logoColor=black"/> 
    <img src="https://img.shields.io/badge/Node.js-339933?style=flat-square&logo=Node.js&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white"/> 
    <img src="https://img.shields.io/badge/Flutter-02569B?style=flat-square&logo=flutter&logoColor=white"/>
    <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=flat-square&logo=bootstrap&logoColor=white"/>
    <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=Thymeleaf&logoColor=white"/>
    <img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=flat-square&logo=apachetomcat&logoColor=black"/>
	<br>
    <img src="https://img.shields.io/badge/MySql-4479A1?style=flat-square&logo=mysql&logoColor=white"/> 
    <img src="https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=mariaDB&logoColor=white"/> 
    <img src="https://img.shields.io/badge/MongoDB-47A248?style=flat-square&logo=MongoDB&logoColor=white"/>
    <img src="https://img.shields.io/badge/Firebase-FFCA28?style=flat-square&logo=firebase&logoColor=black"/>
    <br>
    <img src="https://img.shields.io/badge/Github-181717?style=flat-square&logo=github&logoColor=white"/>
    <img src="https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white"/>
    <img src="https://img.shields.io/badge/Notion-000000?style=flat-square&logo=Notion&logoColor=white"/>
</div>
```

<img src="./images/profile2.png" width=800px>

---

## 4. Stats 꾸미기 

- 오픈 api를 통해 내 github의 전체 상태를 등급으로 알 수 있다. 

- 해당 api는 밑의 깃허브에 정의되어 있다. 

  - [git Stats 사이트](https://github.com/anuraghazra/github-readme-stats)

- 형태 

  ```
  ![Anurag's github stats](https://github-readme-stats.vercel.app/api?username=wonyoung0207&show_icons=true&theme=merko)
  ```

  - username부분만 자신의 github ID 로 번경해주면 된다. 
  - 색상을 변경하고 싶다면 theme 부분을 변경하면 된다. 

- Error ( 2023-01-13 ) 수정

  - Cannot read properties of undefined (reading 'user') => 파일을 찾지 못해 stats가 안나오는 에러 발생 

  - 해결방법 

    ```
    // 해결방법 => https://github-readme-stats-git-masterrstaa-rickstaa.vercel.app/api? 로 주소변경 
    
    ![Wonyoung Ahn's github stats](https://github-readme-stats-git-masterrstaa-rickstaa.vercel.app/api?username=wonyoung0207&show_icons=true&theme=merko&bg_color=00000000)
    ```


<img src="./images/profile3.png" width=600px>

---

## 5. 완성 화면 

<img src="./images/profile4.png" >
