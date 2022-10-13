

# < Level 1 > 

# 핸드폰 번호 가리기 

> String 관련 함수 정리 

---

## 문제설명 

- 프로그래머스 모바일은 개인정보 보호를 위해 고지서를 보낼 때 고객들의 전화번호의 일부를 가립니다.
  전화번호가 문자열 phone_number로 주어졌을 때, 전화번호의 뒷 4자리를 제외한 나머지 숫자를 전부 `*`으로 가린 문자열을 리턴하는 함수, solution을 완성해주세요.


## 제한사항 

- phone_number는 길이 4 이상, 20이하인 문자열입니다.

## 입출력 예

| phone_number  | return        |
| ------------- | ------------- |
| "01033334444" | "*******4444" |
| "027778888"   | "*****8888"   |

## 풀이 

1. 내가 푼 풀이 

   ```java
   public String solution(String phone_number) {
       String answer = "";
       int len = 0;
       len = phone_number.length();
   
       for(int i=0; i < len - 4; i++){
           answer += "*";
       }
   
       answer = answer + phone_number.substring(len-4,len);
   
       return answer;
   }
   ```

2. 특이한 풀이 

   ```java
   public String solution(String phone_number) {
       char[] ch = phone_number.toCharArray();
       for(int i = 0; i < ch.length - 4; i ++){
           ch[i] = '*';
       }
       return String.valueOf(ch);
   }
   ```

3. 정규식 이용 풀이 

   ```java
   public String solution(String phone_number) {
       return phone_number.replaceAll(".(?=.{4})", "*");
   }
   ```

   


---

## 사용된 개념

### String 관련 함수 

#### 1. indexOf 

- 지정한 문자가 대상 문자열의 몇번째에 위치한 인덱스인지 확인한다.

- **지정한 문자가 문자열에 존재하지 않는 경우, -1을 반환한다.**

- ```java
  String a = "I have a book";
  System.out.println(a.indexOf('z')); // -1
  System.out.println(a.indexOf('a')); // 3
  ```

#### 2. replace

- 문자열에 지정한 문자가 있으면 새로 지정한 문자로 바꿔준다.

- ```java
  String a = "I have a book";
  System.out.println(a.replace('I', 'Y')); // Y have a book
  System.out.println(a); // I have a book
  System.out.println(a.replace('z', 'Y')); // I have a book
  ```

#### 3. split

- 지정한 문자로 대상 문자열을 나누어 배열을 반환한다.

- ```java
  String str = "A:B:C:D:abcd";
  String[] split = str.split(":");
  for (String s:split) System.out.print(s);
  System.out.println();
  System.out.println(split[4]);
  // ABCDabcd
  // abcd
  ```

#### 4. substring

- 문자열에 지정한 범위에 속하는 문자열을 반환한다.

- 작범위의 값은 포함하고, 끝나는 범위의 값은 포함하지 않는다.

- ```java
  String a = "ABCDEFG";
  System.out.println(a.substring(0,2)); // AB
  System.out.println(a); // ABCDEFG
  ```

#### 5. toLowerCase or toUpperCase

- 문자열에 존재하는 대문자를 소문자로 바꾼 새로운 문자열을 반환한다.

- ```java
  String a = "abcDEFG";
  System.out.println(a.toLowerCase()); // abcdefg
  System.out.println(a); // abcDEFG
  ```

#### 6. trim

- 문자열에 존재하는 공백을 제거할 때, 사용한다.

- **문자열의 앞, 뒤 쪽에 있는 공백만 제거할 수 있으며 가운데 존재하는 공백은 제거할 수 없고, replace() 함수를 사용해야 한다.**

- ```java
  String a = "   a  aaa  a   ";
  System.out.println(a.trim()); // a  aaa  a
  System.out.println(a); //    a  aaa  a
  ```

#### 7. contains

- 두 개의 String 값을 비교하여 비교 대상 String 문자열을 포함하고 있는지 여부를 확인한다.

- 비교 대상 문자열을 포함하고 있으면 true, 그렇지 않으면 false를 반환한다.

- ```java
  String a = "I have a book";
  System.out.println(a.contains("have")); // true
  System.out.println(a.contains("z")); // false
  ```

#### 8. charAt

- 지정한 index번째 문자를 반환한다.

- ```java
  String a = "I have a book";
  System.out.println(a.charAt(0)); // I
  System.out.println(a.charAt(20)); // 예외 발생.
  ```

#### 9 concat

- 문자열과 문자열을 결합해 새로운 문자열을 반환한다.

- ```java
  String a = "I";
  String b = " have";
  System.out.println(a.concat(b)); // I have
  System.out.println(a); // I
  System.out.println(b); //  have
  ```

#### 10. format

- 서식 문자열을 이용해서 서식화된 문자열을 반환한다.

- ```java
  int i = 123456789;
  System.out.println(String.format("%,d",i)); // 123,456,789
  ```

#### 11. matches

- 지정한 정규 표현식과 일치할 때, true를 반환하고 그렇지 않으면 false를 반환한다.

- ```java
  int i = 123456;
  String str1 = String.format("%,d", i); // 123,456
  String str2 = "123456";
  boolean matcher = str1.matches(str2);
  System.out.println(matcher); // false
  ```

