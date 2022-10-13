

# < Level 1 > 

# 이상한문자 만들기 

> String의 split(a,b) 개념이용 => split의 두번째 인자 

---

## 문제설명 

- 문자열 s는 한 개 이상의 단어로 구성되어 있습니다. 각 단어는 하나 이상의 공백문자로 구분되어 있습니다. 각 단어의 짝수번째 알파벳은 대문자로, 홀수번째 알파벳은 소문자로 바꾼 문자열을 리턴하는 함수, solution을 완성하세요.
- "try hello world"는 세 단어 "try", "hello", "world"로 구성되어 있습니다. 각 단어의 짝수번째 문자를 대문자로, 홀수번째 문자를 소문자로 바꾸면 "TrY", "HeLlO", "WoRlD"입니다. 따라서 "TrY HeLlO WoRlD" 를 리턴합니다.


## 제한사항 

- 문자열 전체의 짝/홀수 인덱스가 아니라, 단어(공백을 기준)별로 짝/홀수 인덱스를 판단해야합니다.
- 첫 번째 글자는 0번째 인덱스로 보아 짝수번째 알파벳으로 처리해야 합니다.

## 입출력 예

| s                 | return            |
| ----------------- | ----------------- |
| "try hello world" | "TrY HeLlO WoRlD" |

## 풀이 

1. 내가 푼 풀이 

   ```java
   public String solution(String s) {
       String answer = "";
       String[] split = s.split(" ",-1);        
   
       for(String a : split){
   
           for(int i=0; i < a.length(); i++){
               if(i % 2 == 0){//짝수
                   answer += Character.toUpperCase(a.charAt(i));
               }else{
                   answer += Character.toLowerCase(a.charAt(i));
               }
           }
   
           answer += " ";
       }
   
       answer = answer.substring(0,answer.length()-1);
   
       return answer;
   }
   ```

2. 좋아요 많은 풀이 

   ```java
   public String solution(String s) {
   
       String answer = "";
       int cnt = 0;
       String[] array = s.split("");
   
       for(String ss : array) {
           cnt = ss.contains(" ") ? 0 : cnt + 1;
           answer += cnt%2 == 0 ? ss.toLowerCase() : ss.toUpperCase(); 
       }
       return answer;
   }
   ```

3. 성능 좋은 풀이 

   ```java
   public String solution(String s) {
       char[] chars = s.toCharArray();
       int idx = 0;
   
       for (int i = 0; i < chars.length; i++) {
           if (chars[i] == ' ')
               idx = 0;
           else
               chars[i] = (idx++ % 2 == 0 ? Character.toUpperCase(chars[i]) : Character.toLowerCase(chars[i]));
       }
   
       return String.valueOf(chars);
   }
   ```

   


---

## 사용된 개념

1. split 함수 

   - Split ( String regex)
     - 첫 인자만 가질경우, 해당 String을 기준으로 split한다. 
   - Split ( String regex, int limit)
     - **두번째인자 limit이 양수일 경우**
       - 첫번째 인자 : split할 값 
       - 두번째 인자 : 나눌 배열의 크기 ( 만약 2라면 split 을 2까지만 함 )
     - **두번째인자 limit이 음수일 경우**
       - 첫번째 인자 : split할 값 
       - 두번째 인자 : 리턴 배열의 마지막에, 빈 문자열이 오더라도 이 원소를 생략하지 않음 
       - 따라서 뒤에 공백이오면 양수일경우 생략해버리는데 음수로 해놓으면 뒤에 공백이 와도 생략되지 않고 배열로 리턴해준다. 

   ```java
   String str = "apple,banana,,orange,grape,,";
   String[] strArr = str.split(",", 0);
   String[] strArr = str.split(",", -1);
   
   // 결과값 
   // split 의 두번째인자 0인경우 => 마지막에 인자 없으면 인식 x
   "apple"   "banana"	""	"orange"	"grape"
   
   // split 의 두번째인자 음수인경우 => 마지막에 인자 없어도 인식
   "apple"	"banana"	""	"orange"	"grape"	""	""
   ```

   

   

   
