

# Day7

---

> 예외처리 (try, catch, finally, throw, throws)
>
> 컬렉션 ( List, Set , Map, Queue) 
>
> iterator
>
> 제네릭
>
> 4-11

## 예외처리

### 정의

- 프로그램 실행도중 발생하는 예외적인 상황을 처리하기 위한 방법

### 종류

1. **Throwable** ( 예외 클래스 최상위에 있는 클래스 )
2. **Exception** ( 예측할 수 있는 에러. 핸들링이 가능 )
   - checked Exception : 예외처리를 반드시 해야하는 예외처리
     - 컴파일 타임에 에러가 발생해 실행이 불가 => 빨간줄로 인해 코드에 에러가 난것을 볼 수 있음 
     - 반드시 **try~catch 블록**으로 묶어줘야한다. 
   - Unchecked Exception : 예외처리가 강제화 되지 않는 예외처리 
3. **Error** ( 예측할 수 없는 에러. 메모리 부족 에러  )

```java
// Unchecked Exception
String str = null;
System.out.println(str.toString());

// checked Exception
InputStream in = System.in;
in.read(); // 빨간줄 에러 => try - catch 로 묶어줘야함
```

### 사용방법

#### 1. 사용 키워드

- try - catch
  - try : 해당 블록 안에있는 코드는 예외가 발생할 확률이 존재한다는 것을 의미 
  - catch : 만약 try 블록에서 예외가 발생했을 경우 처리하는 블록 (여러개의 catch 사용 가능 )
- finally
  - 예외 발생여부에 관계없이 항상 실행되는 코드를 적는 구간. try- catch와 같이 사용된다.
- throw ( 예외 발생 )
  - 예외를 발생시키는 키워드
- throws ( 예외 전파 )
  - 발생된 예외상황을 메소드를 호출한 곳으로 전달하는 키워드 
  - 해당 메소드안에서 예외상황이 발생되면 throws

#### 2. 방법

```java
try { 
    // 예외 발생할 수 있는 곳 
} catch(예외종류) {
  	// 예외 발생시 실행되는 구간   
} finally{
    // 무조건 실행되는 코드 
}
```

```java
// 사용자 정의 예외 클래스 작성 
public class MyErrorException extends Exception{
    public MyErrorException(String msg ){
        super(msg);
    }
}

public int withdraw(int money) throws MyErrorException{// 해당 메소드 호출한 곳으로 예외처리를 넘김 => 그곳에서 try - catch 사용해야함 
    	if(balace < money){
            throw new MyErrorException("잔액이 부족합니다. "); // 강제적으로 예외 발생시킴 
        }
        balance -= money;
        return balance;
    }
}
```

---

## Collection (컬렉션)

### 정의

- 순서없는 객체들의 집합으로, **인터페이스이다**. 
- 데이터를 저장할 때 어디에 저장하는지에 따라 성능차이가 존재한다.

### 종류

1. List
   - 순차적 나열로 순서 지정이 가능한 객체들의 집합 
   - 종류
     - ArrayList
     - LinkedList
     - Vector
2. Set
   - 중복을 허용하지 않는 객체들의 집합
   - 종류
     - HashSet
     - LinkedHashSet
     - TreeSet
3. Queue
   - FIFO( First In First Out) : 처음 저장하는 객체가 가장 먼저나오는 구조 
4. Map
   - 키와 그 키에 대응하는 객체들로 이루어진 집합 ( Key, Value 로 이루어짐 ) 
   - 종류
     - HashMap
     - LinkedHashMap
     - TreeMap

### 주요 메소드 

1. add()
   - 새로운 요소를 삽입 . 중복 요소 허용하지 않는경우 false 반환 
2. clear()
   - 모든 요소를 제거 
3. contains()
   - 파라미터로 전달되는 객체가 요소로 존재하는지를 확인. 
4. isEmpty()
   - 해당 컬렉션이 포함한 요소가 0인지 반환
5. remove()
   - 파라미터로 전달된 객체를 제거 
6. size()
   - 요소의 개수를 반환 
7. iterator()
   - 해당 컬렉션이 포함하고 있는 요소들을 순회하기 위한 iterator 객체 반환 

### List

#### 1. ArrayList

- 특징
  - 순차적으로 요소를 관리하고 중복값을 허용한다. 
  - 배열과 마찬가지로 인덱스를 통해 접근한다. 
  - 고정길이 저장공간으로 요소 관리 ( 길이를 정해줘야 한다. )
  - **내부배열의 용량(capacity)** 를 넘어 요소를 저장할 경우 **자동으로** 내부적으로 **용량을 늘린 새로운 배열**을 만들어 요소를 옮겨 담는다. ( **배열과의 차이점** )
- 동작 방식 
  - 요소들 중간에 데이터를 저장할 경우 **ArrayList가 내부적(자동)**으로 **인덱스들을 뒤로 옮겨주고 데이터가 추가**된다. 
  - 단, 데이터의 추가, 삭제가 빈번할경우 성능이 떨어질 수 있다. 따라서 추가 삭제가 빈번하지 않은 곳에서 적합하다. 
- 정리
  - 길이를 신경쓰지 않아도 된다.
  -  요소 추가시 인덱스 부분을 신경쓰지 않아도 된다.
  - 단, 데이터의 추가, 삭제가 빈번하면 성능이 떨어진다. 

#### 2. LinkedList

- 특징
  - 요소들을 저장할 때, **노드(node)** 를 연결해서 저장한다. 
  - 연속된 공간(고정길이 ) 가 아닌 **서로다른 공간**을 구성하고, **node를 통해 공간을 가리켜** 만들어진다. 
  - 따라서 길이(용량)의 개념이 없다. 
- 동작방식
  - 요소의 추가는 노드의 추가를 의미
  - 노드가 다음 노드를 가리킨다. 
  - 노드 추가, 삭제시 해당 노드의 앞 뒤 노드에 연결 포인트를 변경하는 방법으로 추가 삭제가 일어난다. 

### Set

- 특징
  - 저장하는 요소의 **중복을 허용하지 않는다**. 
  - 하나의 요소를 꺼낼 수 있는 방법(get)이 없다. ( **전체를 꺼내는 방식으로 사용**해야 함 ) => Iterator 사용 

### Map

- 특징 
  - **Collection 인터페이스를 상속하지 않는다.** 
    - 따라서 list, set 과 메소드가 다르다. 
    - Entry 인터페이스를 상속받는다. 
  - 키와 값을 가진 객체의 순서 쌍으로 이루어져 있다. 
  - **중복이 불가능**
- 종류
  - HashMap
    - 순서를 보장하지 않는다. 
  - LinkedHashMap
    - 요소를 추가한 순서( get() )를 보장한다. => 순서가 있다. 
  - TreeMap
    - 저장된 요소의 키를 기준으로 한 순서를 보장한다. => 키로 정렬된 순서 보장 .
- 사용방법
  - get() : 데이터 가져오기 
  - put() : 데이터 꺼내기 
  - 전체 데이터 가져오는 방법
    1. key값을 이용해 전체를 꺼낸 후, 반복문을 통해 값 꺼내기
    2. iterator 사용
    3. stream 사용

### iterator 

- 컬렉션 객체가 가지고 있는 요소들을 모두 꺼낼 때 사용하는 인터페이스 

- 진행 순서 

  1. iterator() 메소드를 호출 
  2. 해당 메소드는 가지고 있는 요소들을 정렬하고 , 정렬한 첫 값의 이전을 가리키는 iterator를 반환한다. 
  3. iterator 객체에 있는 hashNext() 메소드를 통해 다음 값이 있는지 확인
  4. 만약 다음 값이 있다면 iterator객체의 next() 메소드를 이용해 값을 꺼냄
  5. 3번과 4번을 반복 

- 사용방법

  ```java
  // 1 .
  // Collection 인터페이스를 상속받은 컬렉션인 경우 => List, Queue, Set
  Iterator<Customer> iterator = list.iterator();
  while(iterator.hashNext()){// iterator가 가리키는 포인터에서 다음 포인터에 값이 존재하는지 확인 
      Customer c = iterator.next();// 다음으로 포인터 옮기고 해당 값을 리턴 
  }
  
  
  // 2 .
  // Collection 인터페이스를 상속받지않은 컬렉션인 경우 => Map
  // map 에 있는 values() 메소드를 이용 
  Collection<Customer> col = map.values(); // 모든 value들을 collection 타입의 인스턴스로 반환함
  Iterator<Customer> iterator = col.iterator();
  while(iterator.hashNext()){
      Customer c = iterator.next();
  }
  ```

---

## 제네릭( Generic )

### 정의

- **데이터의 타입을 제안하기 위해 사용한다.** 
  - 만약 list에 각기 다른 타입의 데이터(객체)가 저장된다면 해당 타입들은 저장시 **Up-casting이 발생**하여 **하위 객체에만 존재하는 메소드나 필드를 사용할 수 없게 된다.** 
  - 이러한 상황을 방지하기 위해 제네릭을 사용한다. 

### 사용방법

- < T >  : <> 를 사용해 안에 어떠한 타입이 올 수 있음을 말하고, 안에있는 T를 이용해 해당 데이터 타입처럼 사용된다. 
- 데이터의 타입을 명확히 선언할 수 있고, 정확한 데이터의 사용 여부를 컴파일 시점에서 확인할 수 있다.
- 객체를 수집, 관리하는 컬렉션을 이용할 때 사용한다. 

```java
public class Box<T> {
    private T item;
    public Box(T item){
        this.item = item;
    }
    public T getItem(){
        return item;
    }
}

Box<Apple> box = new Box(new Apple(10));// 만약 제네릭을 사용하지 않는다면 다른 객체타입들도 다 들어갈 수 있어 저장된 데이터 타입을 알기 어려워진다. 
Apple apple = box.getItem();
```



