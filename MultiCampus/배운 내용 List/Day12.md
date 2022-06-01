# 12일차
---

> 자바 API 에 대해 학습한다. 
>
> 컬렉션에 대해 학습한다. 



## 1. Object

   + 모든 클래스의 루트이다.

   + 따라서 클래스들은 모두 object 를 상속받아 만들어진다. 

   + ```java
     //object는 최상위 클래스이다. 
     Object obj = new String("abc");
     Object obj2 = new CustomerVO("id01", "pwd01", "lee");
     
     //obj2에서 CustomerVO 객체에 있는 값들을 사용하기 위해서는 타입 캐스팅을 다시 해야한다. 
     CustomerVO c = (CustomerVO) obj2;//타입 캐스팅 
     System.out.println(c.getName());
     
     Object obj3 = 10;//10이라는게 객체 타입으로 바뀌어서 들어간다. 
     Object obj4 = new Integer(10);
     ```

## 2. String

   + 자주 사용하는 키워드
     1. str.charAt(3)
        + 3번째 있는 char를 가져옴 
     2. str.indexOf('c')
        + c가 몇번째 인덱스에 있는지 출력 
     3. str.substring(1,3)
        + 1~3전까지 출력
     4. str.trim();
        + 공백 제거
     5. str.toCharArray()
        + String을 char형의 배열로 만들어 리턴 
     6. str.split(',')
        + ,을 기준으로 String배열로 나눠 출력 

## 3. String vs StringBuffer

   1. StringBuffer
      + 내용이 유지되어 변경시 자유롭게 변경이 가능하다. 
   2. String 
      + 한번 정한 값은 바꿀 수 없다. 

## 4. Date

   + 날짜를 가지고 있는 패키지

   + java.util.Date 에 있다. 

   + SimpleDateFormat 

     + 해당 객체를 이용해서 형식을 정할 수 있다. 
     + a = SimpleDateFormat(YYYY.MM.dd  hh:mm:ss)
       + a.format(date)

   + ```java
     Date date = new Date();
     SimpleDateFormat format1;
     format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     out.print(format1.format(date));
     
     Calendar now = Calendar.getInstance();
     //Calendar 추상클래스이기 때문에 객체 생성 못함 
     int year = now.get(Calendar.YEAR);
     int month = now.get(Calendar.MONTH) + 1;//월은 + 1 해야함 
     int day = now.get(Calendar.DAY_OF_MONTH);//일을 출력한다. 
     int ampm = now.get(Calendar.AM_PM);//오전, 오후를 출력 
     int hour = now.get(Calendar.HOUR);//시간  
     ```
     
   + Format 패턴 문자

     + Y  : 년
     + M : 월
     + d : 일
     + a : 오전/오후
     + h : 시간
     + m : 분
     + s : 초 
     + E : 요일 

## 5. 컬렉션

   + 여러개의 데이터를 담을 수 있는 것 
   + List, Set 의 최상위 클래스이다. 
   + 인터페이스 분류
     1. 컬렉션 
        1. ArrayList : 데이터가 순서대로 들어간다. 
        2. HashSet : **중복 데이터**가 들어갈 수 없다. 순서가 없다 .
           + 구슬주머니라고 생각하면 된다. 
     2. Map 
        + HashMap :**key와 value** 로 데이터가 저장된다. 

## 6. Stack

   + **LIFO 구조**
     + Stack 은 first in last out 구조이다. 
   + **되돌리기** 사용을 생각하면 된다. 이전 실행이 stack에 저장되므로 순차적으로 되돌리기가 가능한 것이다. 
   + 메소드
     + st.push("a")
     + st.pop();

## 7. Queue

   + **FIFO구조**
     + 처음넣은 것을 처음에 꺼낼 수 있다.

8. **제네릭**
   + 객체 생성시 여러 타입을 매치할 수있게 만들어 준다.
     + 번거로운 **타입 캐스팅**을 줄이기 위해 나왔다. 
   + 타입을 클래스 내부가 아닌 외부에서 사용자가 정할 수 있도록 만든다. 
   + 장점
     + 제네릭을 사용하면 잘못된 타입이 들어올 수 있는 것을 컴파일 단계에서 방지할 수 있다. 
     + 클래스 외부에서 타입을 지정해주기 때문에 따로 타입 체크하고 변환해줄 필요가 없다. 
