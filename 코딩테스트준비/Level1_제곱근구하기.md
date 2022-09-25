

# < Level 1 > 

# 제곱근 구하기 

---

## 문제설명 

- 임의의 양의 정수 n에 대해, n이 어떤 양의 정수 x의 제곱인지 아닌지 판단하려 합니다.
  n이 양의 정수 x의 제곱이라면 x+1의 제곱을 리턴하고, n이 양의 정수 x의 제곱이 아니라면 -1을 리턴하는 함수를 완성하세요.


## 제한사항 

- n은 1이상, 50000000000000 이하인 양의 정수입니다.

## 입출력 예

| n    | return |
| ---- | :----: |
| 121  |  144   |
| 3    |   -1   |

## 풀이 

1. 내풀이 

   ```java
   long answer=0;
   answer = (long) Math.sqrt(n);// 여기서 그냥 Math.sqrt 하면 소숫점도 나온다. 
   //근데 long형으로 바꿔주면서 소수점이 버려지기 때문에 다시 Math.pow() 를 쓰면 받은 제곱근 값과 달라지는 것이다. 
   System.out.println("squr : " + answer);
   
   if(n == (long)Math.pow(answer,2)){
   
       answer = (long)Math.pow(answer+1,2);
       //			System.out.println("pow : " + (long)Math.pow(answer,2));
   } else{
       //			System.out.println("pow : " + (long)Math.pow(answer,2));
       answer = -1;
   }
   ```

2. 좋아요 많은 풀이 

   ```java
   
   class Solution {
     public long solution(long n) {
         if (Math.pow((int)Math.sqrt(n), 2) == n) {
               return (long) Math.pow(Math.sqrt(n) + 1, 2);
           }
           return -1;
     }
   }
   
   
   class Solution {
     public long solution(long n) {
       double i = Math.sqrt(n);
       return Math.floor(i) == i ? (long) Math.pow(i + 1, 2) : -1;
     }
   }
   ```

3. 별로 좋지 않은 풀이 

   ```java
   class Solution {
     public long solution(long n) {
         long answer = 0;
   
         for (long i = 1; i <= n; i++) {// 이렇게 하면 n이 엄청 큰 수 일때 그 수만큼 if를 체크한다.
             if (i * i == n) {
                 answer = (i + 1) * (i + 1);
                 break;
             }
             else answer = -1;
         }
         return answer;
     }
   }
   ```

   

## 사용된 개념

- 제곱근
  - Math.pow() : 해당 숫자의 제곱을 구한다. 
  - Math.sqrt() :  해당 숫자의 제곱근을 구한다. sqrt()는 리턴값이 double 일 수 있기 때문에 받는 값을 Double로 받아야한다. 
    - 이때 double 값을 int 형인 정수로 변환해서 받으면 sqrt하기 전 입력된 숫자가 정수 제곱근을 가지는지 판별할 수 있다. 
