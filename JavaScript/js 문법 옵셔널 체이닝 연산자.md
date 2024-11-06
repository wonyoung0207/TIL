# js 문법 옵셔널 체이닝 연산자

---

>

## 옵셔널 체이닝 연산자

### 개념

1. 객체의 프로퍼티에 접근할 때 해당 프로퍼티가 **존재하지 않거나 `null`, `undefined`인 경우** 에러 없이 `undefined`를 반환하도록 한다. 
2. 즉, `null`이나 `undefined`로 인한 에러를 방지하기 위해 사용된다. 

### 예시

1. `userData` 객체가 `null`이거나 `undefined`일 때, `userData.role`에 접근하려 하면 에러가 발생하는 대신, `userData?.role`로 작성하면 `undefined`를 반환해 `Null` 로 인한 에러를 방지할 수 있다. 
2. 동작
   1. `userData`가 `null`이거나 `undefined`라면 `userData?.role`은 `undefined`를 반환한다. 
      1. 따라서 `undefined === 'ROLE_ADMIN'`은 `false`이므로 `||` 연산자를 통해 `false`가 반환되어 `isAdmin`이 `false`로 설정된다. 
   2. 반대로 `userData`가 존재하고 `userData.role`이 `'ROLE_ADMIN'`인 경우 `isAdmin`은 `true` 로 설정된다. 

```js
const userData = null;
const isAdmin = userData?.role === 'ROLE_ADMIN' || false;
console.log(isAdmin); // 출력: false

const userData2 = { role: 'ROLE_ADMIN' };
const isAdmin2 = userData2?.role === 'ROLE_ADMIN' || false;
console.log(isAdmin2); // 출력: true
```

