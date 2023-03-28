

# < Level 1 > 

# 나누어 떨어지는 숫자 배열 

> ArrayList
>
> Iterator
>
> Collections

---

## 문제설명 

- array의 각 element 중 divisor로 나누어 떨어지는 값을 오름차순으로 정렬한 배열을 반환하는 함수, solution을 작성해주세요.
  divisor로 나누어 떨어지는 element가 하나도 없다면 배열에 -1을 담아 반환하세요.


## 제한사항 

- arr은 자연수를 담은 배열입니다.
- 정수 i, j에 대해 i ≠ j 이면 arr[i] ≠ arr[j] 입니다.
- divisor는 자연수입니다.
- array는 길이 1 이상인 배열입니다.

## 입출력 예

| arr           | divisor | return        |
| ------------- | ------- | ------------- |
| [5, 9, 7, 10] | 5       | [5, 10]       |
| [2, 36, 1, 3] | 1       | [1, 2, 3, 36] |
| [3,2,6]       | 10      | [-1]          |

- 입출력 예#1
  arr의 원소 중 5로 나누어 떨어지는 원소는 5와 10입니다. 따라서 [5, 10]을 리턴합니다.
- 입출력 예#2
  arr의 모든 원소는 1으로 나누어 떨어집니다. 원소를 오름차순으로 정렬해 [1, 2, 3, 36]을 리턴합니다.

## 풀이 

1. 내가 푼 풀이 

   ```java
   public int[] solution(int[] arr, int divisor) {
       int[] answer = {};
       int cnt = 0;
       int size = 0;
       ArrayList<Integer> list = new ArrayList<Integer>();
   
       for(int i=0; i < arr.length; i++){
           if(arr[i] % divisor == 0){
               list.add(arr[i]);
               size++;
           }
       }
       if(size == 0){
           answer = new int[1];
           answer[0] = -1;
           return answer;
       }
       answer = new int[size];
       // 정렬
       Collections.sort(list);
   
       //데이터 옮기기 
       
   //	    for(Integer num : list) {
   //	    	answer[cnt] = num;
   //	    	cnt++;
   //	    }
       Iterator<Integer> iter = list.iterator(); //Iterator 선언 
       while(iter.hasNext()){//다음값이 있는지 체크
           answer[cnt] = iter.next(); //값 출력
           cnt++;
       }
   
       return answer;
   }
   ```

2. 간단한 풀이

   ```java
   int[] answer = Arrays.stream(arr).filter(factor -> factor % divisor == 0).toArray();
   if(answer.length == 0) answer = new int[] {-1};
   java.util.Arrays.sort(answer);
   ```

   


---

## 사용된 개념

1. ArrayList 

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
        - java.util.Collections 써줘야함 
   
        ```java
        // 오름차순 정렬
        Collections.sort(list)
        // 내림차순으로 정렬        
        Collections.sort(list, Collections.reverseOrder());     // [a, C, B, A]
        
        Collections.reverse(list);
        ```
   
     2. List.sort() 이용
     
        ```java
         // 오름차순으로 정렬        
        list.sort(Comparator.naturalOrder());        
        System.out.println("오름차순 : " + list);  // [A, B, C, a] 
        // 내림차순으로 정렬        
        list.sort(Comparator.reverseOrder());        
        System.out.println("내림차순 : " + list); // [a, C, B, A]
        ```
