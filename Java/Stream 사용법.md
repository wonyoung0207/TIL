# Stream 사용법

---

>객체 리스트에서 Stream() 을 사용한 함수형 프로그래밍 방법
>
>[내가 정리한 내용](https://github.com/wonyoung0207/TIL/blob/master/Java/Day9.md)
>
>[참고 사이트1](https://isntyet.github.io/java/java-stream-%EC%A0%95%EB%A6%AC(sort)/)
>
>[참고 사이트2](https://ssamdu.tistory.com/7)
>
>[참고 사이트3](https://myhappyman.tistory.com/78)

## 스트림 ( Stream )

### 특징

- 간결하고 가독성 있는 처리가 가능하다. 

- 람다를 필요로 하기 때문에 람다를 이해하고 사용할 수 있어야 한다. 

- 한번 생성한 스트림은 한번 사용 후 다시 사용할 수 없다.

  - 따라서 선언 후 한번 사용되었거나 전체 데이터에 대한 처리 ( 람다표현식 ) 가 끝나면 결과 반환 후 없어진다. 

    ```java
    Stream<String> s = list.stream(); // Stream 객체 생성 
    System.out.println(s.count()); // stream 객체 한번 사용 
    s.forEach(System.out :: println); // Error 발생 => stream 객체는 한번 사용하면 다시 사용하지 못한다. 
    ```

### 사용방법

#### 예시 데이터 형태 및 값

| 번호 | 이름   | 가진돈 |    생일    |
| :--: | :----- | :----- | :--------: |
|  1   | jojae  | 2900   | 1991-02-26 |
|  2   | haha   | 1000   | 2003-03-02 |
|  3   | arabia | 30000  | 2001-04-06 |
|  4   | cici   | 150    | 1982-05-16 |
|  5   | zzang  | 40000  | 1910-06-26 |
|  6   | ssu    | 200000 | 2012-07-11 |
|  7   | kuku   | 150    | 1991-02-27 |

```java
public class Human {
    private Long idx;
    private String name;
    private Integer money;
    private LocalDate birth;
}
```

#### 1. Comparable 인터페이스 사용 ( 기본 사용법 )

###### 1. 객체가 아닌 그냥 숫자 List 인 경우 

- sorted() 함수를 사용하면 정렬할 수 있다. 

  ```java
  List<Integer> numbers = List.of(5, 2, 3, 9, 4);
  
  numbers.stream().sorted().collect(Collectors.toList());
  // 내림차순일 경우
  numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
  ```

##### 2. 객체로 이루어진 List의 경우

- 총 2가지 방법이 존재한다. 

  1. Comparable 인터페이스 상속받아서 compareTo() 메소드 구현
  2. sorted() 메소드의 매개변수로 Comparator.comparing() 메소드 사용 

- sorted() 메소드를 사용하기 위해서는 사용할 객체에 Comparable 인터페이스의 compareTo() 메소드가 구현되어있어야 한다. 

  - 따라서 객체에 어떤 기준을 가지고 sort 할 것인지 구현해놔야 한다. 

- **선행원소와 후행원소의 차이가 int 값으로 리턴된다.** 

  - ex) {1,3,2} 의 리스트인 경우, 인덱스 1과2를 비교하게 되면 1-3 = -2 가 나오게 된다.
    - 결과값이 음수이면 시스템은 오름차순이 디폴트라서 정렬시 두 원소의 위치를 변경하지 않는다. 

  - 리턴값에 따른 결과
    - 음수 or 동일 = 두 원소 위치 변경 x 
    - 양수 = 두 원소 위치 변경 o

- Comparable 사용해서 구현 

  ```java
  // comparTo() 메소드 구현 
  public class Human implements Compareable<Human> {
      ...
  
          @Override
          public int compareTo(Human o){
          	return this.name.compareTo(o.name);// 결과값이 양수,음수,0 으로 나타난다. 음수일경우 두 원소 위치 변경 안함 
      }
  }
  
  
  // 정렬 
  public class MainClass {
      public static void main(String[] args){
          List<Human> list = new List<Human>();// 선언 및 생성 ->new 붙으면 생성임 
          list.add(new Human(...));
          list.add(new Human(...));
          list.add(new Human(...));
          
          // List 다른방법으로 구현
          List<Human> humans = Arrays.asList(
              new Human(...),    
              new Human(...),    
              new Human(...)
          );
          // ======================
  
          sortedTest1();
          
          public void sortedTest1() {
              List<Human> sortedHumans = humans.stream()
                  .sorted() // human객체의 compareTo() 메소드가 자동으로 호출 
                  .collect(Collectors.toList())
                  .forEach(System.out::println);
          }
      }
  }
  
  ```



#### 2. Comparator 인터페이스 사용 

- sorted() 메소드 안에서 Comparator.comparing() 메소드를 이용한다. 

  - 객체에서 compareTo() 사용해 구현하는 방법보다 해당 방법이 더 많이 사용된다. 

- Comparator.comparing() 사용해서 구현 

  ```java
  List<Human> HumanList = List.of(
              new Human(...),
              new Human(...),
              new Human(...),
              new Human(...),
              new Human(...);
  
  // 오름차순 정렬
  List<Human> stdList = HumanList.stream()
      .filter( s -> s.getName().equals("안원영"))
      .sorted(Comparator.comparing(Human::getNo)) // Human객체의 No라는 프로퍼티를 기준으로 정렬 
      .collect(Collectors.toList());          
  
  // 역순 정렬
  List<Human> stdRList = HumanList.stream()
         .sorted(Comparator.comparing(Human::getNo).reversed())
         .collect(Collectors.toList());    
  ```
  
  
