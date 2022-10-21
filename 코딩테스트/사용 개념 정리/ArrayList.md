# ArrayList 

- ArrayList는 배열(array)을 기반으로 두고 있다

- List 인터페이스는 java의 Collection을 확장한 인터페이스 이다.

- index를 기반으로 list가 구성된다. 

- java.util.ArrayList 써줘야함 

- 장점

  - 데이터에 순서가 있음
  - 데이터의 중복 저장 허용
  - index를 통한 검색 빠름

- 단점 

  - 데이터 추가, 삭제가 느림

- 선언

  ```java
  ArrayList<Integer> list = new ArrayList<Integer>();
  ```

- List에서 사용할 수 있는 함수 

  |       메서드        |                             설명                             |
  | :-----------------: | :----------------------------------------------------------: |
  |  boolean add(E e)   |  요소 하나를 배열에 추가한다. E는 요소의 자료형을 의미한다.  |
  |     int size()      |           배열에 추가된 요소 전체 개수를 반환한다.           |
  |  E get(int index)   |         배열의 index 위치에 있는 요소 값을 반환한다.         |
  | E remove(int index) | 배열의 index 위치에 있는 요소 값을 제거하고 그 값을 반환한다. |
  |  boolean isEmpty()  |                 배열이 비어있는지 확인한다.                  |

- 출력 방법

  1. 향산된 for문 사용 

     - 사용시 선언이 `ArrayList<Integer> list = new ArrayList<Integer>()` 로 되어있어야 한다.

       - `ArrayList list = new ArrayList<Integer>()` 로 쓰면 오류남 

         ```java
         for(Integer num : list) {
             answer[cnt] = num;
             cnt++;
         }
         ```

  2. iterator 사용 

     - java.util.Iterator 써줘야함

       ```java
       Iterator<Integer> iter = list.iterator(); //Iterator 선언 
       while(iter.hasNext()){//다음값이 있는지 체크
           answer[cnt] = iter.next(); //값 출력
           cnt++;
       }
       ```

- List 정렬

  1. Collections.sort() 이용 

     -  Collections 클래스의 sort 함수 이용 
     -  java.util.Collections 써줘야함 

     ```java
     // 오름차순 정렬
     Collections.sort(list)
     // 내림차순으로 정렬        
     Collections.sort(list, Collections.reverseOrder());     // [a, C, B, A]
     
     Collections.sort(num);
     Collections.reverse(num);// 기존 list를 반대로 뒤집는것 
     ```
  
  2. List.sort() 이용
  
     ```java
      // 오름차순으로 정렬        
     list.sort(Comparator.naturalOrder());        
     System.out.println("오름차순 : " + list);  // [A, B, C, a] 
     // 내림차순으로 정렬        
     list.sort(Comparator.reverseOrder());        // 
     System.out.println("내림차순 : " + list); // [a, C, B, A]
     ```
  
- Collections 

  1. 최솟값 구하기 

     ```java
     Integer minimum = Collections.min(arrayList);
     ```

     