# Day4

---

> 6-1 ~ 6-7 강 까지의 내용 

## Class

- ES6부터 class 키워드를 통해 프로토타입을 사용하지 않고 직관적으로 부모 클래스의 생성자와 메소드에 접근할 수 있다. 

```javascript
class Person{ // 객체 
    constructor(name, id) {
        this.name = name;
        this.id = id;
    }
    toString(){
        return this.name;
	}
}

let p01 = new Person('kim', 'A1023'); // 객체 생성 

```

### 사용방법 및 조건 

1. 자바스크립트에서는 자바와 다르게 클래스의 멤버변수를 사용하지 않고 생성자만을 이용하여 멤버변수를 초기화할 수 있다. 

2. 자바스크립트에서는 프로퍼티(객체의 구성요소- 변수,함수 등 ) 자료형을 지정해주지 않아도 된다. 

3. 생성자 사용하게되면 위에있는 class **멤버변수인** name, intro, foundedDate 를 **생략**해도 된다.

4. 자바스크립트에서 **멤버변수 선언시 "_" 를 붙여주면** 관례적인 의미로 해당 변수는 외부 접근을 허용하지 않는 **private형태**라는 것을 뜻한다. 

   - 따라서 해당 변수는 get, set을 통해서만 접근가능하다. 

   - 또한 **get, set 메소드의 이름을 멤버변수와 같게**한다. 

5. 자바와는 다르게 **get,set메소드를 마치 멤버변수처럼 호출**해서 사용한다. 

```javascript
class a {
    // 프로퍼티 자료형 지정 안해도됨 
    // name;
    // intro;
    // foundedDate;
    
    constructor(name, intro){
        this._name = name;
        this._intro = intro;
        this._foundedDate = new Date();   
    }
    
    get name(){ // 자바스크립트의 get메소드는 객체처럼 접근해야해서 호출 시 함수가 아닌것처럼 () 를 생략한다. 
        return this._name;
    }
    
    set name(name){
        this._name = name;
    }
}

let newClub = new a('club 1', 'intro 1');
console.log(newClub.name); // 자바와는 다르게 get메소드를 마치 멤버변수처럼 호출해서 사용한다. 

```



---

## Collection

### 정의

- 다수의 객체를 담을 수 있는 객체

### 종류

- index로 값에 접근할 수 있는지 없는지를 구분 

1. index로 접근 가능 ( indexed Collection )
   - Arrays (배열)
   - Typed Array
2. index로 접근 불가능 ( Keyed Collection )
   - Objects
   - Map
   - Set
   - WeakMap
   - WeakSet

### Keyed Collection

- Map
  - Map 과 객체의 차이점 
    - 객체로 사용하게 되면 Key값에 문자열만 사용할 수 있지만, Map으로 사용하면 문자열, 숫자 ,문자 모두 가능하다. 
    - Map 이 제공해주는 내장메소드가 존재하여 데이터를 손쉽게 사용할 수 있다. 
    - 따라서 ES6 이후에는 거의 Map , Set 으로 데이터를 모아 사용한다. 
  - Map 과 WeakMap의 차이점 
    - 제공 메소드는 같다. 
    - WeakMap 의 Key 값으로는 객체만 올 수 있다. 
- Set
  - **중복 데이터를 허용하지 않고** 저장 데이터 자체를 Key로 데이터를 꺼내야한다. 

- TravelClubStore.mjs

```javascript
import mapStorage from './MapStorage.mjs';

class TravelClubStore{
    constructor(){
        this._clubMap = mapStorage.clubMap; // 현재 TravelClubStore는 멤버변수가 _clubMap 하나이다.
    }
    
    // 저장된 TravelClub 갯수 반환 
    count(){
        return this._clubMap.size;
    }
    
    // 해당 Club이름이 있는지 검색
    exist(name){
        // Mpa 형태  =>  Map(Key, Value) = (travelClub.name, travelClub)
        return this._clubMap.has(name);// has는 key값을 보내 찾는다. 
    }
    
    // 해당 클럽이름이 있는지 검색하고 없다면 새로운 club객체 생성 
    store(newClub){
        if(this.exist(newClub.name)){//true면 해당 클럽 이름이 이미 존재하는 것 
            return null;
        }
        this._clubMap.set(newClub.name , newClub); // 클럽Map 객체에 추가 
        return newClub.name;
    }
    
    // 원하는club객체 가져오기 
    retieve(name){
        return this._clubMap.get(name);// 해당 값을 가져옴 
    }
    
    retrieveAll(){
        return this._clubMap.values();// 모든 데이터를 객체로 리턴 
    }
    
    // 삭제
    delte(name){
        this._clubMap.delte(name);
    }
}

export default new TravelClubStore();// 이렇게 new 해서 export 하면 사용하는 부분에서 바로 사용할 수 있다. 만약 new하지 않고 그냥 넘겨주면 사용하는 곳에서 new 해서 사용해야한다. 
```

- ClubCoordinator.mjs

```javascript
// import TravelClubStore from '../store/TravelClubStore.mjs'; //=> 위에 export에서 new 하지않았다면 이렇게 사용해도 된다. 
import clubStore from '../store/TravelClubStore.mjs'; 

class ClubCoodinator{
    constructor(){
        // this._clubStore = new TravelClubStore(); => export 시 new하지 않으면 이렇게 사용하면 됨 
        this._clubStore = clubStore;
    }
    
    // 수정 
    modify(name, intro){
        if( !this._clubStore.exist(name)){// 찾고자 하는 클럽이름이 없을 경우 실행 
            return ;
        }
        let foundClub = this._clubStore.retrieve(name);
        foundClub.intro = intro;
        this._clubStore.update(foundClub);
    }
    
}
```

- for of 로 Map 출력하기 

```javascript
// for of 로 map 값 가져오기 
for( let value of allClubs ){
    console.log(value);
}
for( let [key, value] of allClubs ){// key와 value 두가지 다 가져오기 위해서는 변수로 받아야한다. 
    console.log(key + ' : ' + value);
}

```



---

## 싱글톤 

- 싱글톤(singleton) 패턴은 전체 시스템에서 하나의 인스턴스만 존재하도록 보장하는 **객체 생성패턴**을 말한다.

  - 동일 클래스로 `new` 를 해도 인스턴스 하나만 존재한다는 것

  ```javascript
  let a = new Singleton();
  let b = new Singleton();
  console.log(a === b); // true
  // 두 객체는 싱글톤으로 생성된 객체이므로 동일한 객체이다. 
  ```

- 장점

  - 싱글톤 패턴을 사용하면 고정된 메모리 영역에 인스턴스 하나만을 사용하기 때문에 **메모리 낭비를 방지**할 수 있다. 
  - 싱글톤으로 만들어진 인스턴스는 전역이기 때문에 **다른 클래스의 인스턴스들이 데이터를 공유하기 쉬워진다**.'

- 특징 

  - 외부 클래스로부터 인스턴스 생성을 차단한다.
  - 인스턴스에 직접 접근하는 것을 차단한다.
  - 인스턴스에 대한 접점을 제공한다.

