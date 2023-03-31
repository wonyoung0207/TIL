# Day6

---

> 배열
>
> String 클래스
>
> StringBuffer와 StringBuilder
>
> 4-3

## Final 의 활용 

- 클래스에 붙었을 경우
  - 상속할 수 없다. 
- 메소드에 붙었을 경우
  - 자식에서 상속시 재정의를 할 수 없어 그대로 사용해야한다. 

---

## 배열

### 정의

- 동일한 타입의 값들을 저장할 수 있는 자료구조

### 선언 및 생성

- 선언된 배열은 초기화 후에 사용해야한다. 

  ```java
  int[] arr; //배열타입 [] 배열명; 
  arr = new int[5]; // int 타입 5개의 방을 만들고 arr이 heap영역을 가리킨다 .
  ```

### 배열 복사와 정렬

- 두 변수가 동일한 배열을 참조하지 않게 하려면 **Arrays.copyOf(배열, 배열길이)** 를 사용한다. 

- **Arrays.sort(배열)** 을 사용해 배열 내부의 요소를 정렬한다. 

- **Arrays.copyOfRange( 원본 배열, 시작점, 끝점 )**

  - 배열을 어디서부터 어디까지 복사해라 

    ```java
    Arrays.copyOfRange(arr, 0, index ) // 불필요한 공간을 복사하는것을 방지할 수 있다. 
    ```

---

## 문자열 클래스 

### 정의

- String 클래스로, 문자열 처리를 위한 기능들( 메소드들 ) 을 가지고 있다. 

- 문자열은 Char형태의 배열에 저장되는 것이다. 

  ```java
  String str = "java pro";
  // 형태 : 총 8개의 char 로 되어있는 배열로 저장이 된다. 
  // 따라서 char[] c = {'j','a','v','a',' ','p','r','o'}
  ```


### 변경내용 

- java 9 부터 **문자체계가 char[] 에서 byte 기반으로 변경**되었다. 
  - 데이터타입이 char 에서 byte로 1byte 만큼 공간이 줄어들었다.
  - 기존에는 char[] 로써 한 문자당 2byte의 공간을 잡아 'A' 같은문자의 경우 1byte의 공간만 숫자로 표기되고 남은 1byte는 0 으로 채워져 공간이 낭비되었다. 
  - java 9 이후부터는 한 문자당 1byte의 공간이 할당되어 'A' 에 경우 1byte에 딱 맞게 공간을 할당할 수 있게 되었다. 


### 초기화 방법 및 형태

- String객체 초기화 방법

  1. 리터럴 이용 ( 문자열 넣기 )
  2. 객체 생성 (new String)

- **String pool** 

  - new로 생성하면 heap영역 자체에 객체로 선언되는것이고, 리터럴이용하면 string pool 에 생기게 된다. ( 차이가 있음, )

  - 문자열을 저장하면 **heap 영역에 있는 String pool** 에 해당 문자가 있는지 검색한 후 없으면 공간을 잡는다. 

  - 공간을 잡고 참조변수가 해당 String pool에 있는 주소를 가리키게 된다. 

  - 문제는, **참조변수의 이름이 다른데 같은 문자열을 저장했을 경우**이다. 

    - 이 경우 **String pool이 기존에 존재하던 문자열의 주소를 가리키게 하기** 때문에 다른 이름의 변수가 동일한 주소를 가리키는 문제가 발생한다. 
  
      ```java
      String s1 = 'java'; // 리터럴 방법으로 초기화 
      String s2 = new String('java'); // 객체생성 방법으로 초기화 
      String s3 = 'java'; // s1과 같은 주소를 가리킨다. ( String pool 의 주소 )
      
      System.out.println(s1 == s2); // false
      System.out.println(s1 == s3); // true
      ```
  
  - 따라서, 문자열 비교시 equals() 메소드를 이용해 비교해야 한다.
  
    - **== 연산자**는 비교하고자 하는 두개의 대상의 **주소값을 비교**하고,
  
    - String클래스의 **equals 메소드**는 비교하고자 하는 두개의 **대상의 값 자체를 비교**한다.
  


### StringBuffer, StringBuilder

#### 사용이유

- **문자열을 연결(더할때) 시키는 concat() 메소드** 사용시 **메모리 누수 ( 기존 heap 주소를 아무것도 가리키지 않는것 ) 가 발생**하게 된다. 
- 메모리 누수를 막기위해 사용되는것이 Buffer 와 builder이다. 

#### 특징

- **String 클래스와 다르게 가변의 특성**을 가진다. 
- 초기화시 리터럴 방식이 아닌 객체생성 방법으로 초기화 해야한다. 
- concat() 이 아닌 **append()** 메소드를 이용한다. 
- 새로운 주소를 할당하는 것이 아닌 **기존 heap에 할당되어있는 공간에서 확장**하는 개념이다. 

#### 형태 

```java
StringBuilder sb = new StringBuilder("java");
System.out.printLn(sb.capacity());// capacity() 는 버퍼의 크기로, 기본 16Byte이다. append하면 자동으로 늘어난다. 
sb.append("Program study");
```

#### 차이점

- **동기화 처리 여부**에서 차이가 있다. 

  - 동기화 처리란, 여러개의 스레드가 같은 데이터를 사용해야하는 경우 해당 데이터를 어떤 스레드가 사용중이라면 다른 스레드가 해당 데이터를 사용할 수 없도록 만드는것이다. 

  - 따라서 **여러개의 스레드가 같은 데이터를 실수없이 사용하도록 하는것이 "동기화 처리 " 의 이유**이다. 

    ``` java
    // synchronized 키워드를 통해 동기화를 구현할 수 있다. 
    public synchronized int compareTo(StringBuffer another){}
    ```

- 스레드란, 작업의 작은 단위를 뜻한다. 

- StringBuffer는 **멀티 스레드** 프로그램에서 사용된다. 

  - 동기화 처리가 되어있다. 
  - 멀티 스레드란, 하나의 프로세스 안에 여러게의 스레드가 존재하는것을 말한다. 
  - 체팅프로그램이 멀티 스레드의 예이다. 

- StringBuilder는 **단일 스레드** 프로그램에서 사용된다. 

  - 동기화 처리가 되어있지 않다 

#### 정리 

- 성능면에서는 단일 스레드 처리인 StringBuilder가 더 뛰어나지만, 멀티 스레드의 환경이라면 StringBuffer를 무조건 사용해야한다. 

---

## Wrapper 클래스

### 정의

- Primitive Type(기본 데이터 타입들(8가지))을 객체로 만들어 사용할 수 있게 하는 클래스 
  - 기본 데이터 타입 : int, double, byte, boolean, short , char , long, float

### 사용방법

```java
Integer intWrap = Integer.valueOf(10); // Wrapper 클래스 형태로 바꿈
int num = intWrap.intValue(); // Wrapper 클래스를 기본 데이터 타입으로 변환 

Integer intWrap = 10;  // AutoBoxing : 위의 메소드를 사용하지 않아도 자동으로 boxing 된다. 
int num = intWrap; // UnBoxing : 자동으로 unboxing 된다. 
```
