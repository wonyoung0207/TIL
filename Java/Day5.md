# Day5

---

> Object 클래스 및 기본 제공 메소드 
>
> 추상 클래스 
>
> 인터페이스 
>
> 3-14

## Object 클래스 

### 정의

모든 자바 클래스가 상속하는 최상위 클래스이다. 

### 종류 ( 대표적으로 많이 사용되는 메소드 )

#### clone()

- 인스턴스 **객체를 깊은복사하기 위한 메소드**이다. ==> 완전한 복제 
- 조심해야 할것이 ( 단순히 정보만 복사하는지(얕은복사) VS 동일한 객체의 인스턴스로 복사하는지 (깊은복사 )) 이다. 
  - clone복제를 하는 이유는 같은 정보를 가졌지만 다른 주소를 가진 인스턴스를 만들기 위함이다. 
  - **깊은복사 결과 :** heap 영역이 따로 할당되어 인스턴스 주소값이 다르게 설정됨 
- 얕은 복사 VS 깊은 복사
  - **얕은 복사**를 한다면 heap영역에는 하나의 주소만 존재하고 해당 주소를 Stack 영역의 서로다른 변수가 가리키는 형태가 된다. ( **변수 하나의 값을 바꾸면 다른 변수에도 영향을 미친다.** )
  - **깊은복사는** 같은정보도 가지면서 heap에는 다른 주소값을 가지는 것을 뜻한다.  ( **정보를 바꿀경우 다른 변수에 영향을 미치지 않는다.** )

#### equals()

- 객체를 비교해주는 메소드로, 해당 객체가 완전히 동일한지 체크할 수 있다. 
- '==' 연산자와의 차이점
  - '=='연산자는 객체의 hashCode가 같은지를 비교하고 , equals()는 해당 객체들의 값이 같은지 비교한다. 

#### getClass()

- 클래승의 정보가 필요할 때 이용한다. 

#### hashCode()

- 객체를 식별하는 정수값을 반환하는 메소드이다. 

#### toString()

- 객체의 대표적 내용( 필드들 ) 을 출력해주는 메소드
- 인스턴스화 한 객체를 호출할 경우, 자동으로 객체.toString() 메소드가 호출된다. 

---

## 추상클래스 

### 정의

- 하나 이상의 추상 메소드를 갖는 클래스 

- 추상 메소드 : 메소드의 바디(구현내용)가 없이 헤드(접근지정자와 메소드이름)만 가진 메소드 

  ```java
  public abstract void draw();
  ```

### 사용 목적

- 추상 클래스를 상속받은 곳에서 추상 메소드 구현을 강제하기 위해 사용한다. 
- 일반 클래스를 상속받았을 경우는 오버라이딩을 사용자 마음대로 할 수 있게된다. 

### 추상 클래스 형태

- public abstract class Shape{ }

### 특징 

- 추상클래스는 new 를 통해 객체를 인스턴스화 시킬 수 없다. => 부모 역할로만 사용할 수 있다.
- 추상클래스의 추상 메소드는 하위에서 반드시 구현되야한다. 

---

## 인터페이스 

### 정의

- 추상 메소드만 가지며 interface 키워드를 이용해 정의한다. 

### 형태

```java
public interface IShape{
    public abstract void play();
}
```

### 특징

- 상속과 달리 둘 이상의 인터페이스를 동시에 상속받을 수 있다. 

- 상속과 달리 implements 키워드를 통해 상속받는다. 

- 인터페이스가 가지는 추상 메소드 구현을 강제한다. ( 일반 상속은 강제하지 않음 )

- 필드를 정의할 수 있다. ( 하지만 , 필드의 형태는 **public static final** 으로 '**사용자 정의 상수**' 가 된다. )

- **default 메소드**를 사용할 수 있다. 

  - 해당 메소드는 그 자체로 완전한 메소드로, **구현 클래스에서 강제가 아닌 선택적으로 재정의 해서 사용할 수 있게 한다.** 

  - 추가된 이유는 , 나중에 추가된 메소드에 대해 해당 인터페이스를 상속받은 모든 곳에서 강제적으로 메소드를 구현해야하는데 어려움이 있어 추가되었다. 

    ```java
    default void defaultMethod(){}
    ```

### 역할

- 클래스간의 관계를 느슨하게 관리하도록 도와줌 
  - 느슨하다 ==>  한곳의 내용이 변경되더라도 다른 곳에 영향을 주지 않는다.



