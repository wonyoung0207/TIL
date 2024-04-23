# JavaScript 메소드 모음

---

>

## charAt() 과 charCodeAt() 차이점

- charAt 는 index에 해당하는 문자를 리턴하고, chartCodeAt은 유니코드 값을 리턴하는 차이





# JS 의 Object.assign()

---

>[참고 사이트1](https://pro-self-studier.tistory.com/21)
>
>[참고 사이트2](https://juno-develop.tistory.com/5)

## Object.assign

### 정의 

- 객체의 값을 병합할 때 사용한다. 
- 즉, 여러개의 객체를 하나의 객체 형태로 **복사**할때 많이 사용한다. 

### 형태

1. `target`: 병합할 속성들을 복사할 **대상 객체**
2. `sources`: 복사한 속성들을 제공하는 하나 이상의 소스 **객체들**

```js
Object.assign(target, ...sources)
```

### 예시

1. 객체를 병합할때 사용된다. 
2. 복제본을 만들기 위해서는 빈 객체 `{}`를 대상으로 사용하는 것이 좋다. 

```js
const target = { a: 1, b: 2 };
const source = { b: 4, c: 5 };

// 병합 1
const merged = Object.assign(target, source); // target객체가 달라진다. 
console.log(merged); // { a: 1, b: 4, c: 5 }

// 병합 2
const merged = Object.assign({}, target, source); // target 객체를 변경하지 않고 병합된 객체를 얻을 수 있다. 
console.log(merged); // { a: 1, b: 4, c: 5 }

// 복제 
const copy = Object.assign({}, source); 
```



