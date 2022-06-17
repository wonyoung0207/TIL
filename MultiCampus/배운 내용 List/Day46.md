# Day46

---

>세미프로젝트 Day04

# 세미프로젝트 Day04

> ERD 완성
>
> 개발환경 세팅후 팀원과 GIthub 로 공유
>
> DDL, DML 완성후 eclipse로 Test 해보기 

## ERD 완성

<img src="../images/SemiProject/ERD2.png">

- 유의할 내용 

  - 카트페이지
    - 카트에서 선택
    - 카트에서 결제하기 버튼 클릭

  - 주문페이지
    - 위쪽 구매목록에서는 카트에서 제품 정보와 구매금액 총합 받아와서 구매할 아이템 띄우기
    - 주문 테이블(폼) 작성
    - 결제하기 버튼을 누르면
      1. 주문테이블의 한 행이 작성됨
      2. 카트에서 선택한 제품 데이터를 쿼리스트링 등으로 전송하여 주문상세 테이블의 컬럼이 되어야 함
  - 정리
    - 결제하기 클릭시 주문 페이지가 만들어진다. 
    - 주문페이지에서 작성 완료를 누르면 주문 테이블이 만들어진다.



## 환경세팅 & Github 

- 환경 세팅 후 Github 로 팀원과 공유

- 담당 테이블에 대한 Frame, VO, mybatis, Mapper, Test, Controller(Main) 을 작성하고 CRUD를 수행해본다. 

- git 주소 : [Team2 Github](https://github.com/minsiks/Team2-Semi_Project)

- [CRUD]()

  

## DDL  & DML  작성

### 담당 테이블

- 김민식
  - cust
  - addrlist
- 서예린
  - buy
  - shoes_cnt
- 안원영
  - product
  - buy_detail
- 유정아
  - review
  - cate
  - cart

### 1. DDL 

- DDL (Data Define Language) 
  + **Create, Drop, Alter **
  + 테이블 생성, 드랍 제약상황 
  + 테이블 관리에 사용 

### 2. DML

- DML (Data Manipulation Language) 
  + **Select, Insert, Delete, Update**
  + 테이블의 내용들을 다루는 명령어
  + 데이터 관리에 사용 





