# MongoDB 개념정리

---

>[참고 사이트1](https://hoing.io/archives/1379)
>
>[참고 사이트2](https://www.ibm.com/kr-ko/cloud/learn/mongodb)
>
>[참고 사이트3](https://velog.io/@ckstn0777/MongoDB%EB%9E%80)

## [MongoDB](https://www.mongodb.com/)

<img src="./images/MongoDB로고.png" width="500">

### 정의

- MongoDB는 C++로 작성된 오픈소스 **문서지향(Document-Oriented)** 크로스 플랫폼 데이터베이스이다.
  - **비관계형 데이터베이스 관리 시스템**으로 테이블 및 행 대신 유연한 문서를 활용해 다양한 데이터 형식을 처리하고 저장한다.
  - RDBMS (관계형 데이터베이스 관리 시스템) 과 다른 형태를 가진다. 
- 웹 애플리케이션과 인터넷 기반을 위해 설계된 데이터베이스 관리 시스템이다. 

### 장점

1. 직관적인 데이터 모델

   1. **데이터베이스 관리 시스템(DBMS)**을 필요로 하지 않으므로, 사용자가 다변량 데이터 유형을 손쉽게 저장하고 쿼리할 수 있는 탄력적인 데이터 저장 모델을 제공한다. 
   2. 개발자의 데이터베이스 관리를 간소화할 뿐 아니라, 뛰어난 확장성을 갖춘 크로스 플랫폼 애플리케이션 및 서비스 환경을 구축할 수 있게 한다. 

2. JSON 형식 표기 

   - **JSON(JavaScript 객체 표기법) 형식**으로 지정되어 다양한 유형의 데이터를 저정한다. 

     - 정보를 행(row) 대신 도큐먼트(document) 에 저장한다. 

   - 도큐먼트 예시 

     ```Json
     {
         "_id": "5f2ad6b54866e5109dd2367b"
         "username": "홍길동",
         "hashedPassword": "비밀번호",
     }
     ```

3. 복잡한 Join 연산이 필요하지 않음 

   - 도큐먼트 기반의 데이터 모델은 풍부하고 **계층적인 구조**의 데이터를 표현 할 수 있다.
     - 따라서 관계형 데이터베이스에서 필요한 여러 테이블 간의 **복잡한 조인 연산이 없어도 된다.**
   - 예를들어, 상품관리 차원에서 SQL은 여러가지 조인을 통해 상품의 정보를 표시해야하지만, NoSQL은 하나의 도큐먼트에 해당 상품의 정보들을 모두 표시할 수 있다. 