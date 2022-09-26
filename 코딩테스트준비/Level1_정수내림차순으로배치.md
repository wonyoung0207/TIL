

# < Level 1 > 

# 정수 내림차순으로 배치 

---

## 문제설명 

- 함수 solution은 정수 n을 매개변수로 입력받습니다. n의 각 자릿수를 큰것부터 작은 순으로 정렬한 새로운 정수를 리턴해주세요. 예를들어 n이 118372면 873211을 리턴하면 됩니다.


## 제한사항 

- `n`은 1이상 8000000000 이하인 자연수입니다.

## 입출력 예

| n      | return |
| ------ | :----: |
| 118372 | 873211 |

## 풀이 

1. 내풀이 

   ```java
   public static long solution(long n) {
       long answer = 0;
       ArrayList<Long> num = new ArrayList<>();
       String s = "";
   
       while(n > 0 ) {
   
           num.add((n % 10));
           n = n / 10;
       }
   
       Collections.sort(num);
       Collections.reverse(num);
   
       for(Long a : num) {
           s += a + "";
       }
   
       answer = Long.parseLong(s);
       return answer;
   }
   ```

2. 좋아요 많은 풀이 

   ```java
   public int reverseInt(int n){
       res = "";
       Integer.toString(n).chars().sorted().forEach(c -> res = Character.valueOf((char)c) + res);
       return Integer.parseInt(res);
   }
   ```

3. 노가다 풀이 

   ```java
   public int reverseInt(int n){
       String arr[]=String.valueOf(n).split("");
   
       Arrays.sort(arr);
       String str = "";
   
       for(int i=arr.length-1; i>=0; i--){
           str+= arr[i];
       }
   
       return Integer.parseInt(str);
   }
   ```

   

## 사용된 개념

- ArrayList 정렬

  ```java
  collections.sort(list); // 정렬
  Collections.reverse(num); // 거꾸로 
  ```

  
