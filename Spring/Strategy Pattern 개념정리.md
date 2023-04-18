# Strategy Pattern 개념정리

---

>[참고 사이트1](https://victorydntmd.tistory.com/292)
>
>[참고 사이트2](https://gmlwjd9405.github.io/2018/07/06/strategy-pattern.html)

## Strategy Pattern 

### 정의

- **행위**를 클래스로 **캡슐화**해 **동적**으로 행위를 **자유롭게 바꿀 수 있게** 해주는 패턴
- **전략 패턴**이라고도 하며 **객체의 행위를 동적**으로 바꾸고 싶은 경우 직접 수정하지 않고 **전략을 바꿔주기만 함으로써** 행위를 유연하게 확장하는 패턴이다.
  - 따라서 객체가 하는 **유사한 행위들을 전략으로 만들어놓고** 필요할 때마다 **동적**으로 **전략을 호출**해 객체의 행위를 동적으로 만들 수 있다.  
- 직접 내용을 수정하던 인터페이스 방식에서 전략변경을 통해 행위를 변경하는 방식으로 사용함 

### 전략

- 전략이란 어떤 목적을 달성하기 위해 일을 수행하는 방식, 비즈니스 규칙, 문제를 해결하는 알고리즘을 뜻한다. 

### 구조

<img src="./images/strategy-pattern.png" width="600">

1. **Strategy**
   - **인터페이스**나 **추상 클래스**로 외부에서 동일한 방식으로 알고리즘을 호출하는 방법을 명시
   - 어떤 행위로 **전략**을 나눌지 설정하는 부분
2. **ConcreteStrategy**
   - 스트래티지 패턴에서 명시한 **알고리즘을 실제로 구현한 클래스**
   - **전략별 행위**에 대해 기술하는 부분 
3. **Context**
   - 스트래티지 **패턴을 이용**하는 역할을 수행한다. 
   - 필요에 따라 동적으로 구체적인 전략을 바꿀 수 있도록 **setter 메서드**(‘집약 관계’)를 제공한다.
     - setter 메소드에 전략 클래스를 연결시켜 전략을 동적으로 변경할 수 있다. 

```java
// 1. Strategy 부분
public interface MovableStrategy { // 전략 인터페이스 
    public void move();
}

// 2. ConcreteStrategy 부분
public class RailLoadStrategy implements MovableStrategy{ // 전략 구현 1
    public void move(){
        System.out.println("선로를 통해 이동");
    }
}

public class LoadStrategy implements MovableStrategy{ // 전략 구현 2
    public void move() {
        System.out.println("도로를 통해 이동");
    }
}
```

```java
public class Moving { 
    // 3. 전략 패턴을 이용하는 곳  => Context 부분
    private MovableStrategy movableStrategy;

    public void move(){
        movableStrategy.move();
    }

    public void setMovableStrategy(MovableStrategy movableStrategy){
        this.movableStrategy = movableStrategy;
    }
}
```

```java
// 전략을 통한 동적인 행위 변경 
public class Client {
    public static void main(String args[]){
        Moving train = new Train();
        Moving bus = new Bus();

        /*
            기존의 기차와 버스의 이동 방식
            1) 기차 - 선로
            2) 버스 - 도로
         */
        train.setMovableStrategy(new RailLoadStrategy()); // 이동 전략 : 선로 설정
        bus.setMovableStrategy(new LoadStrategy()); // 이동 전략 : 도로 설정

        train.move();
        bus.move();

        /*
            선로를 따라 움직이는 버스가 개발
         */
        bus.setMovableStrategy(new RailLoadStrategy()); // 이동 전략 수정 : 선로
        bus.move();
    }
}
```

### 결론

- 행위에 따른 전략을 만들어 행위가 변경되었을 때 손쉽게 전략 변경을 통해 해결할 수 있다. 
