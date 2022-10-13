

# < Level 1 > 

# x만큼간격이있는n개의숫자

> 

---

## 문제설명 

- 함수 solution은 정수 x와 자연수 n을 입력 받아, x부터 시작해 x씩 증가하는 숫자를 n개 지니는 리스트를 리턴해야 합니다. 다음 제한 조건을 보고, 조건을 만족하는 함수, solution을 완성해주세요.


## 제한사항 

- x는 -10000000 이상, 10000000 이하인 정수입니다.
- n은 1000 이하인 자연수입니다

## 입출력 예

| x    | n    | answer       |
| ---- | ---- | ------------ |
| 2    | 5    | [2,4,6,8,10] |
| 4    | 3    | [4,8,12]     |
| -4   | 2    | [-4, -8]     |

## 풀이 

1. 내가 푼 풀이 

   ```java
   public static long[] solution(int x, int n) {
       long[] answer = {};
       answer = new long[n];
   
       answer[0] = x;
   
       for(int i=1; i< n; i++) {
           answer[i] = x + answer[i-1];
           System.out.println(answer[i]);
       }
   
       return answer;
   }
   ```

2. 다른풀이

   ```java
   public long[] solution(long x, int n) {
         long[] answer = new long[n];
         for(int i = 0; i < n; i++){
             answer[i] = x * (i + 1);
         }
         return answer;
     }
   ```

3. ArrayList 사용

   ```java
   public long[] solution(int x, int n) {
       List<Long> list = new ArrayList<>();
   
       for (long i = 1; i <= n; i++) {
           list.add((long)x * i);
       }
   
       long[] arr = new long[list.size()];
   
       for (int j = 0; j < list.size(); j++) {
           arr[j] = list.get(j);
       }
   
       return arr;
   }
   ```

   


---

## 사용된 개념

1. 
