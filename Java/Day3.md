# Day3 

---

> 생성자와 메모리 
>
> 2  - 15

## 생성자

- 생성자의 역할은 객체가 갖는 **필드의 초기화**이며 모든 클래스는 하나 이상의 생성자를 갖는다. 
- new 라는 동적할당자를 이용해 인스턴스화(객체 생성)시 호출된다. 
- 생성자의 이름은 클래스 이름과 동일해야한다. 
- 개발자가 생성자를 생성해놓지 않으면 컴파일이 자동으로 생성자를 생성해준다.  ( 디폴트 생성자 )
- 대부분 public 의 접근지정자를 가진다. 

## 메모리 구조

1. Static
   - 메소드의 바이트 코드, static 변수가 할당. 
2. Stack
   - **지역변수,매개변수**가 할당되는 영역으로, 초기화가 진행되지 않는다. 
3. Heap
   - 모든 **인스턴스 객체**가 할당되는 영역으로, **가비지 컬렉터에 의해 자동 초기화** 진행 

```java
public class Test{
    public static void main(String [] args){
        Account newAccount = new Account();// 해당 변수는 Stack 영역에 할당 
    }
}

public class Account {// Heap 영역에 할당
    ...
}
```

- Garbage Collector 
  - Stack 영역에 있는 변수가 Heap 영역의 인스턴스 객체를 가리키고 있다. 이떄 Stack 영역의 변수가 사용을 다하면 Heap 영역에 있는 인스턴스와 연결이 끊어지는데, 이때 Garbage Collector 가 해당 인스턴스를 지워준다. 

### static

- 정적 키워드로, 정적 필드, 정적 메소드를 선언할 때 사용된다. 
  - 반대의 의미로는 인스턴스 필드 , 인스턴스 메소드가 있다. 
- 프로그램이 끝날때 까지 Static 영역에 존재하며 지워지지 않는다.
- static이 붙은 필드는 **정적필드**라고 하며, 해당 클래스로 만들어진 모든 인스턴스들이 공유하는 변수이다. 
- **정적필드를 클래스 변수 라고**도 한다. 
- **Static 키워드를 가진 필드와 메소드는  프로그램 시작시점에 생성된다.** 

### final 

- 한번의 초기화만 가능하여 이후에 값을 대입, 변경할 수 없게 만드는 키워드이다.
- **상속을 허용하지 않으며** **오버라이딩을 금지**를 한다. 
- 전달인자 (매개변수 ) 로 전달될 때 final키워드를 붙이면 해당 매개변수를 변경할 수 없게 한다. 
- 클래스에 final 을 붙이면 해당 클래스를 상속할 수 없다. 

### 사용자 정의 상수 

- 정적필드(static) 에 final 키워드를 적용하여 만들 수 있는 변수 
- 따라서 **한번 초기화 시켜 변경하지 못하는 변수**로 만들고(final 키워드 특징), **프로그램 시작부터 종료까지 남겨지는 변수**로 만들면 ( Static 키워드 특징 ) 사용자 정의 상수가 만들어진다. 
- 특징으로는 변수의 이름은 모두 대문자로 사용하며, 단어와 단어를 연결할 때는 ' _ ' 를 사용하여 연결한다. 
  - public static final double PI = 3.14;
  - public static final String USER_NAME = "abc";