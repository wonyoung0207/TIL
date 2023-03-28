

# < Level 1 > 

# 나머지가1이되는 수 찾기 

> 

---

## 문제설명 

- 자연수 `n`이 매개변수로 주어집니다. `n`을 `x`로 나눈 나머지가 1이 되도록 하는 가장 작은 자연수 `x`를 return 하도록 solution 함수를 완성해주세요. 답이 항상 존재함은 증명될 수 있습니다.


## 제한사항 

- 3 ≤ `n` ≤ 1,000,000

## 입출력 예

| n    | result |
| ---- | ------ |
| 10   | 3      |
| 12   | 11     |

- 12를 11로 나눈 나머지가 1이고, 11보다 작은 자연수 중에서 문제의 조건을 만족하는 수가 없으므로, 11을 return 해야 합니다.

## 풀이 

1. 내가 푼 풀이 

   ```java
   public int solution(int n) {
       int answer = 0;
   
       for(int i=2; i*i<=n; i++) {
           if((n-1) % i == 0) {// 0이라면 약수이다. 약수가 자기자신밖에 없으면 실행안됨 
               answer = i;
               break;
           }
       }
       if(answer == 0){// 자기자신이 약수일경우 
           answer = n-1;
       }
       return answer;
   }
   ```

   ``` java
   for (int i = 2; i <= Math.sqrt(n); i++)
       if ((n - 1) % i == 0) {
           answer = i;
           flag = true;
           break;
       }
   }
   // i*i <= n 을 다르게 쓰면 i <= Math.sqrt(n); 로 사용가능하다. 
   ```

   

2. 특이한 풀이 

   ```java
   public int solution(int n) {
           return IntStream.range(2, n).filter(i -> n % i == 1).findFirst().orElse(0);
       }
   ```

3. 많은 사람들이 푼 풀이 

   ```java
   public int solution(int n) {
       int answer = 0;
       for (int i=2; i<n; i++) {
           if (n % i == 1) {
               answer = i;
               break;
           } 
       }
       return answer;
   }
   ```

   


---

## 사용된 개념

1. 약수 구하기 
   - 약수는 해당 수에 루트를 씌운 수까지만 구하면 나머지를 구할 수 있다. 
   - 예를들어 126의 약수를 구해보자. 
     - 루트 126은 11보다 크고 12보다 작다. 그리고 126의 약수들 중 11 이하인 것은 1, 2, 3, 6, 7, 9이다. 그러므로 나머지 약수들은 126을 1, 2, 3, 6, 7, 9로 나눈 126, 63, 42, 21, 18, 14이고, 더이상은 없다.
   - 수학적으로 막 적어보았지만, 중요한 것은 결론이다. 바로 n의 약수들 중 √n 이하인 것들만 구하면, 나머지는 n을 그것들로 나눠 구할 수 있다는 것이다.
2. 발생에러 
   - java.lang.ArithmeticException
     - 정수는 0 으로 나눌 수 없기 때문에 나오는 에러 
