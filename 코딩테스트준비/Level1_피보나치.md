

# < Level 1 > 

# 같은 숫자는 싫어   

> 배열 또는 제귀 사용 가능

---

## 문제설명 

- 피보나치 수는 F(0) = 0, F(1) = 1일 때, 1 이상의 n에 대하여 F(n) = F(n-1) + F(n-2) 가 적용되는 수 입니다.

- 2 이상의 n이 입력되었을 때, n번째 피보나치 수를 1234567으로 나눈 나머지를 리턴하는 함수, solution을 완성해 주세요.
  - Tip. 제한사항때문에 몫은 무조건 0이 된다. 따라서 나머지는 나누는 숫자만 구하면 된다.  나누는 숫자는 피보나치 fibo(n) 의 숫자이다. 


## 제한사항 

- n은 2 이상 100,000 이하인 자연수입니다.

## 입출력 예

| n    | return |
| ---- | ------ |
| 3    | 2      |
| 5    | 5      |

## 풀이 

```java
public class fibo {
	public static void main(String[] args) {
		fibo c = new fibo();
		int testCase = 3;
		System.out.println(c.fibonacci(testCase));
	}

	public long fibonacci(int num) {
		long answer = 0;
		long[] dp = new long[num+1];
		dp[0]=0;dp[1]=1;

		for(int i = 2 ; i<=num;i++){
			dp[i] = dp[i-1]+dp[i-2];
		}

		return dp[num];
	}
}

```




---

## 사용된 개념

1. 피보나치 
   - 피보나치란 F(n) = F(n-1) + F(n-2) 로 증가하는 숫자 규칙이다. 
   - 예를 들어, arr[2] = arr[0] + arr[1] 를 성립한다. 
2. 재귀가 아닌 for문 사용 
   - 재귀 호출 대신 for문을 사용해서 첫 번째, 두 번째, 세 번째, ..., `n`번째 피보나치 수를 순서대로 구한다.
   - 이러한 풀이 방식을 **동적 계획법**(**Dynamic Programming**)이라고 한다.
3. int 가 아닌 Long 을 사용한 이유
   - 재귀는 50정도만 되어도 그 숫자가 int의 범위를 벗어나게 된다. 따라서 범위가 더 넓은 Long 을 데이터형으로 설정해 줘야한다. 