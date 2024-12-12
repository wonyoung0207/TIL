## Map

- 순서가 없고 key와 value로 구성되어 있는 Collection

- 사용법

  ```typescript
  class TodoCollection{
      private itemMap : Map<number, TodoItem>; // Map은 선언 후 무조건 초기화되어야한다. 
      constructor(public userName : string, todoItems : TodoItem[] ){
          this.itemMap = new Map<number , TodoItem>();
          todoItems.forEach( (item) => itemMap.set(item.id, item)); // Map은 set으로 값을 넣고 get으로 읽는다. 
          
      }
      
      getTodoItems(includeComplete : boolean) : TodoItem[] {
      	return [...this.itemMap.values()].filter(
              (item) => includeComplete || !item.complete
          );// 전개연산자를 이용해 Map의 value들을 배열로 변환한다.  
  	}
  }
  
  ```

- 전개연산자 ( Spread Operator ) => ' ... ' 을 사용 

  - 배열 혹은 객체요소를 전개하기위해 사용되는 연산자.  

  - 배열에 있는 값들을 다른 배열로 옮길 때 사용 

    ```typescript
    let arr: number[] = [1,2];
    let arr2: number[] = [...arr, 3, 4];
    ```

### 내장함수 

- hasNext() : 다음 객체가 존재하는지 확인하는 메소드 => true , false 로 리턴

- **next()** : 다음 요소를 선택하도록 반환  => 읽어올 요소가 남아있는지 확인 

  - 리턴값은 두개의 파라미터로(value,done) 이루어진 객체이다. 
  - **value : iterator의 다음번째 값** 
  - **done : 마지막 값이 사용된 경우 true로 변경됨 . 그 전까지는 계속 false**

- ketset() :  저장된 모든 키를 객체형태로 반환한다.

  - iterator : collection 에 있는 요소들을 순회하는 인터페이스

- **keys()**  : map에 있는 key값들을 iterator 객체로 반환 

  - 리턴형식은 A new Map iterator object. 

  ```java
  map.put("A", 1);
  map.put("B", 2);
  map.put("C", 3);
  Set keyset = map.keySet();
  System.out.println("Key set values are" + keyset);// (result) Key set values are [A,B,C]
  ```

- 질문

  1. map.keys() 가 반환하는 데이터 형식은?? 

     - 리턴형식은 A new Map iterator object.

  2. iterator.next() 가 반환하는 형태는 어떻게 되나요??

     - value 와 done으로 이루어진 객체 반환 

       - value 에는 iterator 의 다음번째 인덱스 값. done은 마지막 

     - 다음값이 있으면 done 이 true됨 

       ```typescript
       console.log(iterator.next()); // { value: 0, done: false }
       console.log(iterator.next()); // { value: 1, done: false }
       console.log(iterator.next()); // { value: 2, done: false }
       console.log(iterator.next()); // { value: undefined, done: true }
       ```

  3. map 에 내장되어있는 함수( keys, next 등 ) 에 대한 공식 문서를 찾아보고 싶습니다. 어느 사이트를 들어가야 map에 대한 공식 자료를 찾을 수 있을까요??

     - https://developer.mozilla.org/  : 해당 사이트에서 찾을 수 있다. 

- 자바 방법

  ```java
  // 방법1
  Iterator<String> keys = map.keySet().iterator();
  while( keys.hasNext() ){
      String key = keys.next();
      System.out.println( String.format("키 : %s, 값 : %s", key, map.get(key)) );
  }
  
  // 방법2
  for( Map.Entry<String, String> elem : map.entrySet() ){
      System.out.println( String.format("키 : %s, 값 : %s", elem.getKey(), elem.getValue()) );
  }
  
  // 방법3
  for( String key : map.keySet() ){
      System.out.println( String.format("키 : %s, 값 : %s", key, map.get(key)) );
  }
  ```

  

---



## 제네릭

### 정의

- 어떤타입의 객체를  넣고 어떤타입으로 꺼낼지 명시적으로 넣을 수 있는 형태
- 재사용 가능한 클래스 , 함수를 만들기 위해 다양한 타입에서 사용 가능 하게 하기위해 사용하는 것 
- 모든 타입의 객체를 다루면서 객체 타입의 무결성을 지킬 수 있다. 

### 사용법

```typescript
class Box<T>{
    constructor(private fruit : T) {}
    getFruit() : T {
        return this.fruit;
    }
}

const box : Box<Orange> = new Box(new Orange(5));
console.log(box.getFruit().getName());

// Compile Error 발생 
const strBox = new Box('Banana');
console.log(strBox.getFruit().getName()); // 여기서 에러발생 : 이유는 box안의 값이 fruit 객체가 아닌 string 이기 때문에 fruit 객체 안에있는 getName() 을 호출할 수 없다. 
```

---

## 