# ts 의 interface

---

>

## Interface

### 정의

- typeScript 에서의 interface는 **여러 타입의 속성을 interface 하나로 나타내기 위해 사용**된다. 

### 특징

- 인터페이스에서 정의하는 메소드는 모두 추상메소드이다. 

- 클래스와 다르게 인스턴스화 (객체화 ) 할 수 없다. 

- 인터페이스를 이용해 변수, 함수, 클래스에 타입을 지정할 수 있다. 

  ```typescript
  // interface 적용 전. => 변수마다 모두 타입을 지정해줘야한다. 
  const todo : {id : string, task : string , complete : boolean } = {
      id : 'abc',
      task : 'task',
      complete : true
  }
  
  // interface 적용 후 => 여러군대서 재활용 가능 
  interface Itodo{
      id : string,
      task : string;
      complete : boolean
  }
  
  const todo : Itodo = {
      id : 'abc',
      task : 'task',
      complete : true
  }
  ```

### 사용시 만족해야할 조건

1. 인터페이스를 사용한 변수에서는 인터페이스 안에서 정의된 타입들을 모두 정의해줘야한다. 

   1. **옵셔널 프로퍼티** 
      1. 인터페이스에서 필수요소가 아닌 선택적 요소로 변경한 프로퍼티
      2. '?' 키워드를 이용하면 변경할 수 있다.  


   ```typescript
   interface Shape {
       p1 : number[],
       p2 : number[],
       area? : number // 옵셔널 프로퍼티 (필수요소에서 제외)
   }
   
   let rectangle : Shap = {
       p1 : [1,2,3],
       p2 : [4,5,6],
       area : 12
   }
   
   // 이때 area 는 '?' 를 붙인 선택적 요소이기 때문에 다음과 같이 생략할 수 있다. 
   let rectangle : Shap = {
       p1 : [1,2,3],
       p2 : [4,5,6]
   }
   ```

2. readonly 사용

   - interface 정의 시 프로퍼티 앞에 readonly를 사용하면 해당 인터페이스가 적용된 객체안에 있는 readonly 프로퍼티는 추후 값을 변경할 수 없다. 
     - 따라서 readonly는  const와 동일한 기능을 한다. 
   - const는 변수선언시 사용, interface의 readonly는 특정 프로퍼티를 상수화 시키고자 할 때 사용 

   ```typescript
   interface p {
       readonly x : number,
       y : number,
   }
   
   let test : p = {
       x : 10, 
       y : 4
   }
   test.x = 11 //  변경시 에러남. readonly이기 때문에 
   ```

3. Class와의 관계

   - 클래스 구현에 interface를 사용했다면 해당 클래스는 interface의 프로퍼티( 멤버변수 ) 와 메소드를 모두 요소로 구현해야한다. 
     - interface는 기본적으로 추상화가 적용되기 때문에  implements 한 곳에서 모두 구현해야한다. 

### 덕 타이핑 (duck typing)

- 특정 인터페이스에서 정의한 프로퍼티나 메소드를 갖고있는 클래스는 해당 인터페이스를 구현한 것으로 인정하는 것 
- 따라서 implements 를 하지 않은 클래스더라도 해당 인터페이스의 요소 ( 프로퍼티, 추상메소드 등) 를 가지고 있다면 implements한 것과 동일시 하게 본다. 

