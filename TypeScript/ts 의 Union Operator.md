# ts 의 Union Operator(`|`)

---

>

## Union

### 정의

- TypeScript는 타입들의 조합을 통해 새로운 타입을 정의할 수 있다. 이때 사용하는 것이 union이다.
- 내가 원하는 타입만을 지정하고 싶을 때 사용 => any 타입쓰면 어떤 타입이든지 들어올 수 있기 때문 

### 특징

- 유니온 타입을 사용하면 사용된 두 클래스에 공통인 멤버에만 접근이 가능하다. 
- 따라서 각 타입이 가지는 **고유멤버( 타입이 가지는 내장함수)는 사용할 수 없다**.  => **TypeGuard** 를 사용해 해결할 수 있다. 
- typeguard 방법 3가지 
  1. **typeof 연산자** : number, string, boolean 같은 기본 타입만 체크 가능 
  2. 사용자 정의함수
  3. **instanceof**  : Array 같은 객체 타입 체크 가능 

### 사용방법

- Union Operator( | ) 이용 

```typescript
// 하나의 타입 지정
a(shape : any) { } // 다양한 타입을 받고자 할 떄 any를 쓰고 any는 데이터 타입이든지 들어올 수 있다. 

// 유니온 타입 지정 
 let numOrstr : number | string; // 문자열과 숫자만 담을 수 있다. 

numOrstr = 'abc';
numOrstr.split() // 유니온타입을 사용하면 string 에 있는 고유멤버인 split() 함수를 사용할 수 없게된다
// 따라서 typeguard 를 이용해 해결할 수 있다. 

// typegard 적용 => typeof
if(typeof numOrstr === 'string'){
    numOrstr.split();
}
// typeguard 적용 => instanceof 
if(numOrstr instanceof Array){
    
}

```

### 