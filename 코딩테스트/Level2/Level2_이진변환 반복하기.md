

# < Level 2 > 

# 이진변환 반복하기 

> 10진수를 2진수로 변경하는 함수 Integer.toBinaryString(int i )
>
> StringBuilder의 정의와 사용이유 

---

## 문제설명 

- 0과 1로 이루어진 어떤 문자열 x에 대한 이진 변환을 다음과 같이 정의합니다.

  1. x의 모든 0을 제거합니다.
  2. x의 길이를 c라고 하면, x를 "c를 2진법으로 표현한 문자열"로 바꿉니다.

  예를 들어, `x = "0111010"`이라면, x에 이진 변환을 가하면 `x = "0111010" -> "1111" -> "100"` 이 됩니다.

  0과 1로 이루어진 문자열 s가 매개변수로 주어집니다. s가 "1"이 될 때까지 계속해서 s에 이진 변환을 가했을 때, 이진 변환의 횟수와 변환 과정에서 제거된 모든 0의 개수를 각각 배열에 담아 return 하도록 solution 함수를 완성해주세요.


## 제한사항 

- s의 길이는 1 이상 150,000 이하입니다.
- s에는 '1'이 최소 하나 이상 포함되어 있습니다.

## 입출력 예

| s                | result  |
| ---------------- | ------- |
| `"110010101001"` | `[3,8]` |
| `"01110"`        | `[3,3]` |
| `"1111111"`      | `[4,1]` |

입출력 예 #1

- "110010101001"이 "1"이 될 때까지 이진 변환을 가하는 과정은 다음과 같습니다.

| 회차 | 이진 변환 이전 | 제거할 0의 개수 | 0 제거 후 길이 | 이진 변환 결과 |
| ---- | -------------- | --------------- | -------------- | -------------- |
| 1    | "110010101001" | 6               | 6              | "110"          |
| 2    | "110"          | 1               | 2              | "10"           |
| 3    | "10"           | 1               | 1              | "1"            |

- 3번의 이진 변환을 하는 동안 8개의 0을 제거했으므로, `[3,8]`을 return 해야 합니다.

## 풀이 

1. 내가 푼 풀이 

   ```java
   private static int zero_cnt = 0; // 제거한 0의 갯수 
   private static int binary_cnt = 0;// 2진변환 카운트 
   
   
   public static int[] solution(String s) {
       int[] answer = {};
       String binary_str = "";//2진 변환된 문자열
   
       // 0 제거하는 곳 
       while(!s.equals("1")) {
           String arr_ch = "";// 0을 제거한 문자열 
           arr_ch = remove_zero(s);
           System.out.println("arr_ch : " + arr_ch.toString());
           
           // 0제거한 문자열의 길이를 이진수로 변환하는 곳 
           binary_str = change_binary(arr_ch);
           s = binary_str;
       }
   
       answer = new int[2];
       answer[0] = binary_cnt;
       answer[1] = zero_cnt;
   
       System.out.println(answer[0] + ", " + answer[1]);
       return answer;
   }
   
   public static String remove_zero(String s) {
       char[] arr = s.toCharArray();
       String arr_ch = "";// 0을 제거한 문자열 
   
       for(char c : arr) {
           if(c=='0') {
               zero_cnt++;// 0의 갯수 카운팅 
   
           }else {// 1인경우 
               arr_ch += "1";
           }
       }
       return arr_ch;
   }
   
   public static String change_binary(String s) {//0 지운 후 2진수로 변환하는 곳 
       int len = s.length();
       String binary_str = "";
       int remainder = 0; // 나머지 
       int division = 0; // 몫
   
       while(len != 0) {
           remainder = len % 2;
           len = len / 2;
           binary_str = (remainder) + binary_str;
       }
   
       System.out.println("binary_ str : " + binary_str);
       binary_cnt++; //2진변환 수 증가 
       return binary_str;
   }
   ```

2. 좋아요 많은 풀이 

   ```java
   public int[] solution(String s) {
       int[] answer = new int[2];
       int temp;
       while( !s.equals("1") ) {
           answer[1] += s.length();
           s = s.replaceAll("0", "");
           temp = s.length();
           s = Integer.toBinaryString(temp);
           //System.out.println("s : " + s ); 
           answer[0]++;
           answer[1] -= temp;
       }
       return answer;  
   }
   ```

3. 좋아보였던풀이   

   ```java
   public int[] solution(String s) {
       int[] answer = {0, 0};
       while (!s.equals("1")) {
           String binStr = removeZero(s, answer);
           s = toBinary(binStr.length(), answer);
       }
       return answer;
   }
   
   public String removeZero(String s, int[] a) {
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < s.length(); i++) {
           if (s.charAt(i) == '1') {
               sb.append(s.charAt(i));
           }
       }
       a[1] += (s.length() - sb.toString().length());
       return sb.toString();
   }
   
   public String toBinary(int d, int[] a) {
       a[0]++;
       return Integer.toBinaryString(d);
   }
   
   ```

   



---

## 사용된 개념

1. 전역변수 사용해도 되지만 선언 어렵다면 배열 하나 선언해서 넘겨줘도됨

   ```java
   int[] a = new int[2]; 
   
   fuc(int[] a){
       a[0]++;//2진변환 카운트
       a[1]++ // 0 제거 카운트 
   }
   ```

2. 10진수를 2진수로 변경하는 함수 

   - Integer.toBinaryString(숫자)
   - **10진수를 2진수,8진수,16진수로 변환 할 때, Integer 클래스의 toBinaryString, toOctalString, toHexString 함수를 사용**
     - int i = 127;
     - String binaryString = Integer.toBinaryString(i); //2진수
     - String octalString = Integer.toOctalString(i);  //8진수
     - String hexString = Integer.toHexString(i);    //16진수

3. StringBuilder 사용하는 이유 

   - 정의 

     - ***immutable한 String 연산을 효율적이고 효과적으로 하기 위해 StringBuilder를 사용하는 것.***
     - StringBuilder의 메서드는 원본에영향을 끼친다

   - 사용이유 

     - String은 String은 immutable (Immutable이란 변하지 않는다는 뜻) 하므로 값을 수정하면 새로운 객체를 만들어 내서 메모리를 잡아먹고 시간도 잡아먹게된다. 
     - 하지만 StringBuilder는 같은 주소에 값만 수정되는 개념이 되게되서 훨씬 빠르게 작동하게 되는 것
     - StringBuilder에서 만들어지는 String은 mutable하다. 즉, 변경이 가능하다. StringBuilder는 미리 메모리 공간을 확보해두기 때문에(특정 크기의 char 배열을 만들어두기 때문에) toString 메소드를 호출하여 immutable한 String을 생성하기 전까지는 메모리 공간 안에서 char를 변경하고 추가하고 삭제할 수 있기 때문이다.

   - 정리

     - String은 변경되지 않는 문자열로, String 값을 변경한다는 의미는 이전 문자열을 복사 후 새로운 문자열을 생성하여 해당 문자열의 주소를 가리키게한다.
     - StringBuilder는 이러한 문제를 해결하기 위해 나온 것으로, 내부적으로 일정량의 메모리를 할당받고 값을 변경하더라도 새로생성하는 것이 아닌 변경하는 개념으로 이해하면 된다 

   - 예시

     ```java
     String str = "abcdefghijk";
     StringBuilder sb = new StringBuilder(str); // 문자열 -> StringBuilder
     System.out.println("문자열 String 로 변환 : " + sb.toString()); // 출력결과 : 문자열 String 로 변환 : abcdefghijk
     System.out.println("문자열 추출 : " + sb.substring(3,7)); // 출력결과 : 문자열 추출 : defg
     System.out.println("문자열 연결 : " + sb.append("xyz")); // 출력결과 : 문자열 연결 : abcdefghijkxyz
     System.out.println("문자열의 길이 : " + sb.length()); // 출력결과 : 문자열의 길이 : 14
     System.out.println("용량의 크기 : " + sb.capacity()); // 출력결과 : 용량의 크기 : 27
     System.out.println("문자열 역순 변경 : "  + sb.reverse()); // 출력결과 : 문자열 역순 변경 : zyxkjihgfedcba
     
     //StringBuilder의 메서드는 원본에영향을 끼친다
     ```

   - 사용할수 있는 내장함수 

     1. sb.toString() 
        - 문자열로 반환
     2. sb.substring(3,7)
        - 문자열 추출 
     3. sb.append("xyz")
        - 문자열 뒤에 연결
     4. sb.insert(인덱스, 값)
        - 원하는 부분에 값 연결 
     5. sb.delete(인덱스, 인덱스)
        - 특정부분의 인덱스부터 인덱스까지 값을 삭제한다. 
     6. sb.indexOf(값)
        - 값의 인덱스를 리턴 
     7. sb.replace(인덱스, 인덱스, 값)
        - 인덱스부터 인덱스까지 값으로 변경
     8. sb.length())
        - 문자열 길이 
     9. sb.capacity())
        - 문자열 크기 
     10. sb.reverse())
         - 문자열 반대로 
         - 

     
