# StringBuilder 사용하는 이유 

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