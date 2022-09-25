

# < Level 1 > 

# 자릿수더하기 

---

## 문제설명 

- 자연수 N이 주어지면, N의 각 자릿수의 합을 구해서 return 하는 solution 함수를 만들어 주세요.
  예를들어 N = 123이면 1 + 2 + 3 = 6을 return 하면 됩니다.


## 제한사항 

- N의 범위 : 100,000,000 이하의 자연수

## 입출력 예

| N    | answer |
| ---- | ------ |
| 123  | 6      |
| 987  | 24     |

## 풀이 

1. 내풀이 

   ```java
   String a = n + "";
   for(int i=0; i< a.length(); i++) {
       answer += Integer.parseInt(a.substring(i,i+1));
   }
   ```

2. 좋아요 많은 풀이 

   ```java
   while(n > 0) {
       num = n % 10;
       answer += num;
       n = n / 10; 
   }
   ```

3. 내가 풀려했던 풀이 

   ```java
   // String 은 그자체로 객체로, 배열로 사용하기 위해서는 배열로 생성해주어야 한다. 
   // 나는 String 이 배열이라고 착각했다. 
   public int solution(int n) {
       int answer = 0;
       String[] array = String.valueOf(n).split("");
       for(String s : array){
           answer += Integer.parseInt(s);
       }
       return answer;
   }
   ```

   

## 사용된 개념

