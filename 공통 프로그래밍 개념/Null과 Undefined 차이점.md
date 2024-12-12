## Null 과 Undefined 차이점

### 공통점

- 둘다 **없음**을 나타내는 값이다. 

### null

- 변수는 존재하나, **null 로 (값이) 할당된 상태.** 즉 null은 **자료형이 정해진(defined) 상태**
- typeof 연산자로 타입 확인시 **Object** 타입이 나온다. 

### undefined

- 변수는 존재하나, **어떠한 값으로도 할당되지 않아** **자료형이 정해지지(undefined) 않은 상태**
- typeof 연산자로 타입 확인시 **undefined** 타입이 나온다. 

```javascript
var var1; //undefined (어떤 값도 할당되지 않아서 자료형을 알 수 없음)
var var2 = null; //null (null로 (값이) 할당되어서 자료형을 알 수 있음 - null의 자료형은 object)

undefined == null //true
undefined === null //false
```

