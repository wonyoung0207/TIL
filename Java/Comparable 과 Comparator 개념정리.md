# Comparable 과 Comparator 인터페이스 

---

>문자열 비교에 사용되는 2개의 인터페이스에 대해 정리한다. 
>
>[참고 사이트1](https://st-lab.tistory.com/243)

## Comparable 과 Comparator 인터페이스 차이점 

1. 비교 대상의 차이 
   - Comparable은 **"자기 자신과 매개변수 객체를 비교"**하는 것 
     - **ComparTo() 메소드**를 재정의해서 사용
   - Comparator는 **"두 매개변수 객체를 비교"**한다는 것
     - **Compare() 메소드**를 재정의해서 사용
   - 따라서 **Comparable**은 **자기 자신과 파라미터로 들어오는 객체를 비교**하는 것이고, **Comparator**는 자기 자신의 상태가 어떻던 상관없이 **파라미터로 들어오는 두 객체를 비교**하는 것이다. 
     - 즉, **본질적으로 비교한다는 것 자체는 같지만, 비교 대상이 다르다는 것**이다.
2. 패키지의 차이 
   - Comparable은 lang패키지에 있기 때문에 import 를 해줄 필요가 없지만, Comparator는 util패키지에 있다.

---

## Comparable 인터페이스 

### 정의

- java.lang.Comparable< T> 
- CompareTo()메소드를 재정의해야한다.
  - 자신과 인자로 전달받는 타 원소와 비교하여 정수를 리턴하는 형태를 가진다.
  - 따라서 결과값이 int로, 0,1,-1로 리턴된다. 


### CompareTo( T other )

- 기준이 되는 객체와 매개변수로 넘어온 객체를 비교한다.

  - 결과값이 int형으로, 음수, 양수, 0 의 방식으로 리턴된다. 

- **선행원소와 후행원소의 차이가 int 값으로 리턴된다.** 

  - ex) {1,3,2} 의 리스트인 경우, 인덱스 1과2를 비교하게 되면 1-3 = -2 가 나오게 된다.
    - 결과값이 음수이면 시스템은 오름차순이 디폴트라서 정렬시 두 원소의 위치를 변경하지 않는다. 

  - 리턴값에 따른 결과
    - 음수 or 동일 = 두 원소 위치 변경 x 
    - 양수 = 두 원소 위치 변경 o

- 예시

  ```java
  class Student implements Comparable<Student>{
  	int no, score;
      public Student(int no, int score){
      	super();
          this.no = no;
          this.score = score;
     	}
      
      @override
      public int compareTo(Student o){
      	return this.no - o.no;
      }
  }
  ```

  

---

## Comparator 인터페이스 

### 정의

- java.lang.Comparator< T> 
- 비교메소드의 종류가 2개 있다.
  1. Compare() 메소드 재정의
     - 자신과 상관없이 매개변수로 들어온 두 값을 비교하는 형태를 가진다. 
     - 결과값이 int형으로, 음수, 양수, 0 의 방식으로 리턴된다. 
  2. Comparing() 메소드 재정의
     - Java8 부터 나온 개념으로, 내부적으로 두 객체를 비교해서 Comparator 형태로 반환한다.

### Compare( T o1, T o2 )

- 매개변수로 온 두 객체를 비교한다.

- 예시

  ```java
  // Student 클래스는 위와 동일 
  class StudentComparator implements Comparator<Student>{
  	@override
      public int compare(Student o1, Student o2){
      	return o1.no - o2.no;
      }    
  ```

### Comparing (T :: getA)

- 매개변수로 객체의 타입과 해당 객체에서 비교할 필드의 값(getA) 을 적어준다.

- 예시

  ```java
  List<Student> sList = new List<Student>();
  sList.add(...);
  sList.add(...);
  sList.add(...);
  
  // 리스트 정렬 및 필터
  List<Student> resultList = sList.stream()
      .filter( s -> s.getName().equals("안원영")) // 중간연산 1
      .sorted(Comparator.comparing(Student::getNo)) // Student의 No를 기준으로 정렬한다. ==> 중간연산2
      .collect(Collectors.toList()); // 최종연산
  ```
  
  
