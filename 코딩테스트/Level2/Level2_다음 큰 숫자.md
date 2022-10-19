

# < Level 2 > 

# 다음 큰 숫자 

> 

---

## 문제설명 

- 자연수 n이 주어졌을 때, n의 다음 큰 숫자는 다음과 같이 정의 합니다.

  - 조건 1. n의 다음 큰 숫자는 n보다 큰 자연수 입니다.
  - 조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같습니다.
  - 조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수 입니다.

  예를 들어서 78(1001110)의 다음 큰 숫자는 83(1010011)입니다.

  자연수 n이 매개변수로 주어질 때, n의 다음 큰 숫자를 return 하는 solution 함수를 완성해주세요.


## 제한사항 

- n은 1,000,000 이하의 자연수 입니다.

## 입출력 예

| n    | result |
| ---- | ------ |
| 78   | 83     |
| 15   | 23     |

입출력 예#2
15(1111)의 다음 큰 숫자는 23(10111)입니다.

## 풀이 

1. 내가 푼 풀이 

   ```java
   public int solution(int n) {
       int answer = 0;
       int cnt = 0;
       int n_binary_cnt = binary_cnt(n);
       int n_binary_str_len = Integer.toBinaryString(n).length();
       int i= 0;
       //System.out.println("n_binary_cnt : " + n_binary_cnt);
   
       while(true ){
           n = n + 1;
           if(n_binary_cnt == binary_cnt(n)){
               answer = n;
               break;
           }
           //System.out.println("answer : " + n);
           i++;
       }
   
   
       return answer;
   }
   
   public int binary_cnt(int num){//숫자를 받아서 1의 갯수를 돌려줌 
       char[] ch = Integer.toBinaryString(num).toCharArray();
       int cnt = 0;
   
       for(char c : ch){
           if(c == '1'){
               cnt++;
           }
       }
   
       return cnt;
   }
   ```

2. 간단한 풀이 

   ```java
   public int nextBigNumber(int n)
   {
       int a = Integer.bitCount(n);
       int compare = n+1;
   
       while(true) {
           if(Integer.bitCount(compare)==a)
               break;
           compare++;
       }
   
       return compare;
   }
   ```

3. 간단한 풀이 

   ```java
   public int nextBigNumber(int n)
   {
       int cnt = Integer.bitCount(n);
       while(Integer.bitCount(++n) != cnt) {}
       return n;
   }
   ```

   



---

## 사용된 개념

1. Integer.bitCount(n);
   - 정수를 매개변수로  넣으면 해당 수를 2진 비트로 변경 후 1의 갯수를 리턴해준다. 
     - ex
       1. 1111(2) → 4
       2. 1101(2) → 3
