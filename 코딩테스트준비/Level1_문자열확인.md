

# < Level 1 > 

# 문자열 확인 

> character 의 isDisital() 사용
>
> 정규식 사용 

---

## 문제설명 

- 문자열 s의 길이가 4 혹은 6이고, 숫자로만 구성돼있는지 확인해주는 함수, solution을 완성하세요. 예를 들어 s가 "a234"이면 False를 리턴하고 "1234"라면 True를 리턴하면 됩니다.



## 제한사항 

- `s`는 길이 1 이상, 길이 8 이하인 문자열입니다.
- `s`는 영문 알파벳 대소문자 또는 0부터 9까지 숫자로 이루어져 있습니다.

## 입출력 예

| s      | return |
| ------ | ------ |
| "a234" | false  |
| "1234" | true   |

## 풀이 

1. try, catch 사용 

   ```java
   class Solution {
     public boolean solution(String s) {
         if(s.length() == 4 || s.length() == 6){
             try{
                 int x = Integer.parseInt(s);
                 return true;
             } catch(NumberFormatException e){
                 return false;
             }
         }
         else return false;
     }
   }
   ```

2. 정규식 이용 

   ```java
   import java.util.*;
   
   class Solution {
     public boolean solution(String s) {
           if (s.length() == 4 || s.length() == 6) return s.matches("(^[0-9]*$)");
           return false;
     }
   }
   ```

3. character 에 있는 isDigit 함수 이용 

   ```java
   public boolean solution(String s) {
       boolean answer = true;
       for(int i=0; i< s.length(); i++){
           if (!Character.isDigit(s.charAt(i))){
               answer = false;
               return answer;
           }
       }
       if(s.length() == 4 || s.length() == 6){
           return true;
       }
       return false;
   }
   ```

   


---

## 사용된 개념

1. 정규식

   - 정규 표현식을 작성하는 방법은 자바 API java.util.regex 패키지를 사용해야 합니다. 자바에서 정규표현식을 사용할때에는 java.util.regex 패키지 안에 있는 Pattern클래스와 Matcher클래스를 주로 사용합니다.

   - matches() 메서드의 첫번째 매개값은 정규표현식이고 두번째 매개값은 검증 대상 문자열입니다. 검증 후 대상문자열이 정규표현식과 일치하면 true, 그렇지 않다면 false값을 리턴합니다. 

     | **정규 <br />표현식** | **설명**                                                     |
     | :-------------------: | :----------------------------------------------------------- |
     |           ^           | 문자열 시작                                                  |
     |           $           | 문자열 종료                                                  |
     |           .           | 임의의 한 문자(단 \은 넣을 수 없음)                          |
     |           *           | 앞 문자가 없을 수도 무한정 많을 수도 있음                    |
     |           +           | 앞 문자가 하나 이상                                          |
     |           ?           | 앞 문자가 없거나 하나 있음                                   |
     |          [ ]          | 문자의 집합이나 범위를 나타내며 두 문자 사이는 - 기호로 범위를 나타냅니다. [] 내에서 ^ 가 선행하여 존재하면 not을 나타낸다. |
     |          { }          | 횟수 또는 범위를 나타냅니다.                                 |
     |          ( )          | 소괄호 안의 문자를 하나의 문자로 인식                        |
     |          \|           | 패턴 안에서 or 연산을 수행할 때 사용                         |
     |           \           | 정규 표현식 역슬래시(\)는 확장문자 (역슬래시 다음에 일반 문자가 오면 특수문자로 취급하고 역슬래시 다음에 특수문자가 오면 그 문자 자체를 의미) |
     |          \b           | 단어의 경계                                                  |
     |          \B           | 단어가 아닌것에 대한 경계                                    |
     |          \A           | 입력의 시작 부분                                             |
     |          \G           | 이전 매치의 끝                                               |
     |          \Z           | 입력의 끝이지만 종결자가 있는 경우                           |
     |          \z           | 입력의 끝                                                    |
     |          \s           | 공백 문자                                                    |
     |          \S           | 공백 문자가 아닌 나머지 문자                                 |
     |          \w           | 알파벳이나 숫자                                              |
     |          \W           | 알파벳이나 숫자를 제외한 문자                                |
     |          \d           | 숫자 [0-9]와 동일                                            |
     |          \D           | 숫자를 제외한 모든 문자                                      |
     |         (?i)          | 앞 부분에 (?!)라는 옵션을 넣어주게 되면 대소문자는 구분하지 않습니다. |

   - 자주 사용하는 문법 

     1. 숫자 : ^[0-9]*$
     2. 영문 대소문자  :  ^[a-zA-Z]*$
     3. 한글 : ^[가-힣]*$

   - 예시

     ```java
     String name = "홍길동";
     String tel = "010-1234-5678";
     String email = "test@naver.com";
     
     //유효성 검사
     boolean name_check = Pattern.matches("^[가-힣]*$", name);
     boolean tel_check = Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", tel);
     boolean email_check = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);
     
     ```

     