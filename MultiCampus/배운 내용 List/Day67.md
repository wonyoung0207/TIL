# Day67

---

> Final Project 

# Final Project

>개인 개발 시작 - 메인페이지 movie detail 페이지와 연결, google search 기능 사용 
>
>리뷰 rank 페이지 개발 - 페이징 처리 query

## 페이징 쿼리

- 출력될 페이지의 데이터만 나눠서 가져오는 것을 페이징(Paging)

- 페이징 쿼리를 작성하는 방법에는 3가지가 있다. 
  - Rownum 
  - 모든 SQL에 그대로 삽입해서 사용할 수 있는 가상의 컬럼(Column) 입니다. Select를 통해 출력되는 결과 테이블에 행(row) 번호를 매겨주는 기능입니다. 
  - limit 
    -  LIMIT이란 select 문을 통해 데이터를 검색할 때, 검색 결과의 특정부분만 반환 받고자 할때 사용할 수 있는 기능입니다.
  - top