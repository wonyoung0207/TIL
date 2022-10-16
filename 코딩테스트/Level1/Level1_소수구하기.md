

# < Level 1 > 

# 소수구하기

> 소수 구하는 방법 

---

## 문제설명 

- 소수면 true 소수가 아니면 false값으로 return 하는 프로그램을 작성하시오


## 제한사항 

- 입력은 2이상의 자연수로 주어진다. 

## 입출력 예

| 번호 | 입력값 | 출력값   |
| ---- | ------ | -------- |
| 1    | `4`    | `false ` |
| 2    | `17`   | `true`   |

## 풀이 

1. 내가 푼 풀이 

   ```java
   import java.util.Scanner;
    
   public class Main {
   	public static void main(String[] args) {
    
   		Scanner in = new Scanner(System.in);
   		System.out.println(is_prime(in.nextInt()));
      
   	}
   	public static boolean is_prime(int number) {
   		// 소수면 true
   		// 들어온수에 루트씌워서 판별 
   		
   		for(int i=2; i<= Math.sqrt(number); i++){
   			//System.out.print(i);
   			if(number % i == 0){// 나눠서 0나오면 소수 아님 
   				return false;
   			}
   		}
   		
   		return true;
   		
   	}
   }
   ```


---

## 사용된 개념

1. 소수 구하기 

   - 소수를 구할때는 들어온 수에 루트를 씌우고 2부터 루트씌운 수 까지 반복문을 돌려 소수를 찾는다. 

   - 이때 주의할점은 0,1,2는 따로 판별해주어야 한다. 

     ```java
     // 0 과 1 은 소수가 아니다
     if(number < 2) {
         System.out.print("소수가 아닙니다");
         return;
     }
     
     // 2 는 소수다
     if(number == 2) {
         System.out.print("소수입니다");
         return;
     }
     ```
