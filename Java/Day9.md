# Day9

---

> Java8 이후 변경내용 
>
> 스트림 
>
> 스트림 연산 및 사용 메소드 
>
> 5-6강 

## 스트림 ( Stream )

### 특징

- 간결하고 가독성 있는 처리가 가능하다. 

- 람다를 필요로 하기 때문에 람다를 이해하고 사용할 수 있어야 한다. 

- 한번 생성한 스트림은 한번 사용 후 다시 사용할 수 없다.

  -  따라서 선언 후 한번 사용되었거나 전체 데이터에 대한 처리 ( 람다표현식 ) 가 끝나면 결과 반환 후 없어진다. 

    ```java
    Stream<String> s = list.stream(); // Stream 객체 생성 
    System.out.println(s.count()); // stream 객체 한번 사용 
    s.forEach(System.out :: println); // Error 발생 => stream 객체는 한번 사용하면 다시 사용하지 못한다. 
    ```

### 사용 방법

```java
// 1. 30살 이상 추출
// 2. 나이순 정렬
// 3. 이름만 추출 

// 람다 표현식으로 사용 
List<String> list = customer.stream() //  스트림 객체로 변경 
    .filter(customer -> customer.getAge() > 30 ) //  중간연산1 ( Stream 객체 반환 )
    .sort() //  중간연산2 ( Stream 객체 반환 )
    .map(Customer :: getName()) // 중간 연산3 ( Stream 객체 반환 )
    .collect(Collectors.toList()); // 최종 연산 (collection 객체 반환 )
```

---

## Stream 인터페이스 

- BaseStream 을 상속하는 인터페이스 . -> Stream API의 최상위 인터페이스이다. 
- **메소드 종류** => 스트림 객체에만 적용 가능 
  1. long **count**() : 항목의 수를 반환 
  2. Stream **concat**(Stream, Stream) : 파라미터로 전달되는 두 개의 스트림 연결 후 반환 
  3. R **Collection**(Collection) : 스트림 항목들을 컬렉션 타입의 객체로 반환
  4. Stream **filter**(Predicate) :  파라미터의 조건에 따라 필터링하고 결과 항목들을 스트림 형태로 반환 
  5. void **forEach**(Comsumer) : 순회 ( 최종 연산 )
  6. Optional **reduce**(BinaryOperator) : 데이터를 소모하고 그 결과를 반환 
  7. Objectp[] **toArray**() : 배열 객체로 반환 
  8. Stream **sorted**()

---

## Stream 객체 생성 방법 

1. Collection 객체의 stream() 메소드를 통해 ( 일반적으로 많이 사용 )

   - Collection 객체들은 디폴트로 **stream() 메소드**를 가진다.  해당 메소드는 **stream 객체로 만들어주는 메소드**이다. 

   - Collection 객체 ( List, Set, Queue ) 와 Map 인터페이스 는 default 메소드로 stream() 메소드를 가진다. 

     ```java
     List<String> list = new ArrayList<>();
     Stream<String> s = list.stream();// stream 객체 생성 

2. 스트림 빌러를 통해 ( 잘 사용 안함 )

   - 스트림 자체적으로 데이터를 생성하고 처리할 때 사용 

   - accept() , add() , build() 메소드를 통해 최종적으로 Stream 객체를 얻는다. 

     ```java
     Stream.Builder<String> builder = Stream.builder(); // builder 객체 생성 
     builder.accept("Kim"); // 데이터 추가 
     builder.accept("Lee");
     builder.accept("Park");
     
     Stream<String> s = builder.build(); // build() 메소드로 Stream객체 생성 
     stream.forEach(System.out :: println)
     ```

---

## Stream 연산 

- **스트림을 이용한 연산**은 각 연산의 연결을 통해 **파이프라인**을 구성하여 진행한다. 
- 메소드를 통해 다양한 연산을 조합할 수 있다. 

###  연산 3 단계

- 스트림 객체 생성 : collection 에 있는 stream() 메소드 이용
- 중간 연산 ( map() , filter() ... )
- 최종 연산 ( forEach(), count() .. ) 

#### 중간연산

- **반환 타입이 Stream**으로, 다른 연산을 진행할 수 있다. 
- 종류
  - filter
  - map
  - limit
  - sorted
  - distinct
  - peek
  - skip

#### 최종연산

- **void 또는 컬렉션 타입으로 반환**하여 가공된 스트림을 결과값으로 만든다. 
- 종류
  - **forEach**() : 스트림의 각 요소를 소비하며 람다 적용 ( void 타입)
  - **count**() : 요소의 수 반환 (Long타입)
  - **collect**() : List, Map형태의 컬렉션 반환 
  - **sum**() : 모든 요소 합 반환 
  - **reduce**() : 요소 하나씩 줄여가며 연산 수행 후 결과 반환 (Optinal 반환 )

### 필터링 :  filter() , distinct()  

- filter() 
  - 전체 데이터에서 불필요한 데이터를 필터링하고 원하는 데이터를 추출하기 위해 사용 
  - 중간 연산에 해당하는 것으로, stream 객체로 반환된다. 
- distinct() 
  - 중복 제거를 위한 메소드 
  - 중복 제거를 히기 위해선 기준이 필요하다. ( ex- 나이를 기준으로 할 것인지 이름이 같을 떄 중복제거를 할 것인지 )
  - 병렬 스트림의 경우 distinct() 를 사용하면 성능이 떨어진다. 

```java
stream.filter( customer -> customer.getAge() > 30 )
    .distinct()
    .forEach(System.out::println);
```

### 정렬 : sorted()

- 특정 조건에 따른 데이터 정렬을 위한 메소드로, 중간 단계연산에서 사용된다. => stream 형태로 반환 
- 종류
  1. Comparable 인터페이스의 compareTo() 메소드 정의 ( 파라미터로 아무것도 제공안했을 경우 실행 )
  2. Comparator 에 있는 comparing() 메소드 이용 (파라미터로 Comparator 객체 사용 )
- **sorted() 를 사용하기 위해서는 반드시 Comparable 인테페이스를 구현한 객체여야 한다.** 
  - Comparable<> 인터페이스를 상속받아 **compareTo() 메소드를 사용자가 재정의**한 후 사용 가능하다. 
  - 만약 정의되어 있지 않다면 **Comparator**가 제공하는 default, static 메소드를 이용해 정렬이 가능하다. 

```java
//1.
// sorted() 사용하기 위해 재정의 한 compareTo() 메소드 
public class Customer implements Comparable<Customer> {
    @Override
    public int compareTo(Customer customer){
        if(this.age > customer.getAge()){
            return 1;
        }else if(this.age == customer.getAge()){
            return 0;
        }else{
            return -1;
        }
    }
}

// compareTo() 메소드 사용법
customer.stream().sorted().forEach(System.out::println);


// 2.
// Comparator 인터페이스 사용 
customer.stream().sorted(Comparator.comparing(Customer::getName())).forEach(System.out::println);

```

### 타입 변경 : map()

- 스트림이 관리하는 **데이터를 다른 형태의 데이터로 변환**할 수 있도록 한다. => 중간연산에 해당한다. 
  - 따라서 stream() 메소드를 이용해서 stream 객체로 만들면 타입은 이전에 있던 collection\<T> 의 T 부분의 타입을 가진다. 
  - 이떄 데이터를 특정 타입의 데이터만 추출 하기 위해 사용되는 것이 map()  이다. 

```java
List<String> names = customer.stream() // stream 객체는 <Customer> 타입을 가진다. 
    .map(Customer::getName) // map()을 이용해 "Customer타입 -> Customer객체의 getName()타입" 으로 변경한다. 
    .collect(Collectors.toList()); // 모아서(collect) List형태로 만든다. (toList)
```

### 순회 : forEach()

- 최종 연산의 결과로 처리 결과를 바로 확인할 수 있는 연산. 

### collection 반환 : collect()

- 최종 연산으로, stream 객체 타입의 데이터를 collection(list, set queue) , map 형식으로 변경해서 리턴해 준다. 
- 매개변수의 값으로 **Collector**에 있는 메소드를 사용한다. 

```java
clubMap.values().stream()
    .filter(club -> club.getClubName().equals(clubName))
    .collect(Collectors.toList());
```



