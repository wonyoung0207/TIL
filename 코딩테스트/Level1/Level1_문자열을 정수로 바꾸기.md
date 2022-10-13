

# < Level 1 > 

# 문자열을 정수로 바꾸기 



---

## 문제설명 

- 문자열 s를 숫자로 변환한 결과를 반환하는 함수, solution을 완성하세요.


## 제한사항 

- s의 길이는 1 이상 5이하입니다.
- s의 맨앞에는 부호(+, -)가 올 수 있습니다.
- s는 부호와 숫자로만 이루어져있습니다.
- s는 "0"으로 시작하지 않습니다.

## 입출력 예

- 예를들어 str이 "1234"이면 1234를 반환하고, "-1234"이면 -1234를 반환하면 됩니다.
  str은 부호(+,-)와 숫자로만 구성되어 있고, 잘못된 값이 입력되는 경우는 없습니다.

## 풀이 

1. 내가 푼 풀이 

   ```java
   public static int solution(String s) {
       int answer = 0;
   
       if(s.charAt(0) > 0 && s.charAt(0) < 9) {
           answer = Integer.parseInt(s);
       }else {
           answer = checkString(s.charAt(0), s);
       }
       return answer;
   }
   public static int checkString(char c, String s){
       String s2 = "";
       int sign = 1;
   
       if(c == '-') {
           sign = -1;
       }
   
       for(int i=1; i< s.length(); i++) {
           System.out.println(s.charAt(i));
           s2 += s.charAt(i);
       }
       return sign*Integer.parseInt(s2);
   }
   
   public static void main(String args[]) {
       String s1 = "-1234";
       System.out.println(solution(s1));    	
   }
   ```
   
   2. 가장 쉬운 풀이
   
      ```java
      Integer.parseInt(s)
      // parseInt로 할 경우 - 나 + 를 인식해 자동으로 출력해준다. 
      // 문자열 "-1234"라면 정수 -1234로 출력한다. 
      ```
   
   3. 인상깊은 풀이 ( 아스키코드 이용 풀이 )
   
      ```java
      public int getStrToInt(String str) {
          boolean Sign = true;
          int result = 0;
      
          for (int i = 0; i < str.length(); i++) {
              char ch = str.charAt(i);
              if (ch == '-')
                  Sign = false;
              else if(ch !='+')
                  result = result * 10 + (ch - '0');// '0' 은 48을 의미한다. 
              // 따라서 아스키코드 표로 인해 문자 '7' 에서 문자 '0' 을 빼면
              // 정수 ( 55-48= 7 ) 이 나온다. 
          }
          return Sign?1:-1 * result;
      }
      //아래는 테스트로 출력해 보기 위한 코드입니다.
      public static void main(String args[]) {
          StrToInt strToInt = new StrToInt();
          System.out.println(strToInt.getStrToInt("-1234"));
      }
      ```
   
   4. 아스키코드 이용 풀이 2
   
      ```java
      public int getStrToInt(String str) {
          boolean plus = true;
      
          int value = 0;
          for (char ch : str.toCharArray()) {
              if (ch == '-') {
                  plus = false;
      
              } else {
                  int num = ch - 48;
                  value = num+value*10;
              }
      
          }
          return (plus==true)? value : -1*value;
      
      }
      ```
   
      


---

## 사용된 개념

1. 자바는 eval() 함수를 사용할 수 없다. 
2. parse() 함수 사용
   - 변환시 - 나 + 기호가 있으면 인식하는지 
   - parseInt() 함수는 -와 + 를 인식해서 자동으로 음수, 양수 를 표시해준다. 

3. 아스키코드 표 이용

   ```java
   0 = 0x30 ( 48)
   1 = 0x31 ( 49)
   2 = 0x32 ( 50)
   3 = 0x33 ( 51)
   4 = 0x34 ( 52)
   5 = 0x35 ( 53)
   6 = 0x36 ( 54)
   7 = 0x37 ( 55)
   8 = 0x38 ( 56)
   9 = 0x39 ( 57)
   // 예를들어 , 문자 8에서 숫자 8을 얻기 위해서는 문자 0을 뺴면된다. 
   // '8' - '0' = 숫자8
   // 따랗서 56 - 48 = 8 이 나온다. 
   ```

   
