

# < Level 1 > 

# 짝수와 홀수 

> 

---

## 문제설명 

- 정수 num이 짝수일 경우 "Even"을 반환하고 홀수인 경우 "Odd"를 반환하는 함수, solution을 완성해주세요.


## 제한사항 

- num은 int 범위의 정수입니다.
- 0은 짝수입니다.

## 입출력 예

| num  | return |
| ---- | :----: |
| 3    | "Odd"  |
| 4    | "Even" |

## 풀이 

1. 내풀이 

```java
class Solution {
    public String solution(int num) {
        String answer = "";
        
        if(num % 2 == 0){
            answer = "Even";
        }else{
            answer = "Odd";
        }
        
        return answer;
    }
}
```

```java
// 만약 if(num % 2 == 1) 로 했다면, num의 수가 음수 일수 있기 때문에 -1 도 해줘야 한다. 

class Solution {
    public String solution(int num) {
        if(num%2 == 1 || num%2 == -1)
            return "Odd";
        return "Even";
    }
}
```

2. 다른 풀이 

```java
return num % 2 == 0 ? "Even": "Odd";
```

3. 특이한 풀이 ( 비트 연산을 이용한 풀이 )

```java
 String result = (num & 1) == 0 ? "Even" : "Odd";
```

## 사용된 개념

- int 의 범위는 음수도 포함이다. 따라서 num 이 -9 일경우 나누기 값은 -1 이다. 
- 간단한 조건식은 3항식 사용해도 된다. 
- 비트연산자 이용해서 하기 
  - & : and를 뜻하고 두 비트가 모두 1인 경우만 1이 된다. 
  - | : or 를 뜻하고 두 비트가 모두 0인 경우만 0이 된다. 
- 논리연산자 VS 비트 연산자 
  - 논리연산자 : ||, &&  를 뜻하며 True, False 를 사용할 때 사용된다. 
  - 비트연산자 : |, & 로 쓰이며 0010 , 0001 같은 비트로 표현된다. 

