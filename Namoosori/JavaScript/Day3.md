# Day3

---

> 5-1 ~ 5-2

## 객체 ( Object )

### 정의

- **이름(key) 과 값(value)**으로 구성된 프로퍼티의 집합이다. 
- 문자열, 숫자, boolean, null, undefined 를 제외한 모든 값은 객체이다. 
- **프로퍼티(property)** : 객체의 상태를 나타내는 값
  - var a = { num : 5 } 에서 num : 5가 프로퍼티이다. 
- **메서드(method)** : 프로퍼티를 참조하고 조작할 수 있는 동작

### 선언 및 사용방법 

- 객체 리터럴
  - 리터럴이란 사람이 이해할 수 있는 문자나 약속된 기호를 사용해 값을 생성하는 표기법을 말한다. 
  - 자바스크립트에서 객체를 생성하는 가장 일반적으로 사용하는 방법 = 리터럴

```javascript
// 객체 리터럴 이용해서 객체 생성 
let a = {}; // 객체 선언 및 생성  

let b = { // 객체는 key와 value로 구성된다. 
    "key1" : "value1",
    "key2" : "value2"
}

// 접근 
console.log(b["key1"])
console.log(b.key1)

// 조회
let c = b["key3"] || "none"; // key값중 key3가 없다면 none으로 초기화 

// 객체에 새로운 함수 넣기 
b.key4 = function(){
    return this.key1;
}// 이렇게하면 객체 b에 key4라는 객체가 추가되고, 함수의 형태로 들어가게 된다. .

// 객체 삭제 
delete b.key4;

// 객체의 모든 프로퍼티 ( 값 ) 을 출력하는 방법 => for .. in 반복문 
for( const key in b ){
    console.log(key + ' : ' + b[key]);
}// b 객체에 저장된 key값을 모두 가져옴 
```

### 생성자 함수 이용 객체 생성 

- 리터럴은 객체 생성시 하나만 만들 수 있는 방법이다. 
- **리터럴 생성과 생성자 함수 차이점**
  - 생성자 함수를 이용하면 같은 형태의 객체를 여러개 만들 수 있다는 장점이 있다. 

```javascript
let Person = function(name, id) {
    this.name = name;
    this.id = id;
}

let person01 = new Person('kim', 'A1234');
```

### 구조분해할당

- 객체에 있는 값을 쉽게 사용할 수있는 방법 

```javascript
let {name, id} = person01;
// person01이라는 객체에서 name, id 라는 key 값을 찾아 value를 리턴한다. 
```


