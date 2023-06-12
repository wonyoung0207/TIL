# Inner , Outer Join 개념정리 

<img src="./images/inner,outer조인.jpg" width="500"

### Inner Join

1. 교집합을 뜻하는 조인으로, 겹치지 않는 행이 존재한다면 그 행은 결과에서 제외된다. 

### Outer Join 

- 합집합을 뜻하는 조인으로, 기준점이 되는 테이블을 중점으로 기준점의 테이블 행을 모두 가져온다. 
  - 만약 기준점에만 데이터가 존재한다면, 기준점에 있는 행을 가져와 데이터를 넣고 없는 데이터는 null로 표시한다. 
- 따라서 기준이 되는 테이블의 행은 모두 표시되고 join 테이블에는 있지만 기준 테이블에는 없는 데이터는 null로 표시된다. 

1. left join
   - left outer join을 그냥 left join으로 부른다. 
   - 왼쪽 테이블을 기준으로 해당 테이블에 있는 모든 행 정보를 가져온다 . 
2. right join
3. full join