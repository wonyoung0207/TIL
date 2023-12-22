# for in 과 for of 의 차이점

---

>

## for...of

1. 배열 또는 이터러블(iterable) 객체의 각 요소에 대해 반복한다.
2. 값을 가져오기 위해 사용된다. 
3. 주로 배열의 요소, 문자열의 문자 등에 대해 순회할 때 사용된다. 
4. 배열의 값 순회에는 `for...of`를 사용하고, 객체의 속성을 열거할 때는 `for...in`을 사용

```js
const array = ['a', 'b', 'c'];

for (const value of array) {
  console.log(value);
}

// 결과 : a -> b -> c
```

## for...in

1. 객체의 열거 가능한 속성을 반복합니다.
2. 객체의 키(key)를 가져오기 위해 사용된다. 
3. 주로 객체의 속성을 열거할 때 사용된다. 
   1. 객체의 속성을 열거할 때 사용하는데, 배열의 경우에는 배열 요소가 **객체의 속성으로 취급됩니다.** 
   2. 배열도 사실 객체이기 때문에 `for...in`을 사용하여 배열의 인덱스를 열거할 수 있지만, 이는 권장되는 방법은 아니다. 
4. 따라서 for in 에서 **배열을 사용**하면 요소가 아닌 **index**가 나온다. 

```js
const obj = { a: 1, b: 2, c: 3 };

for (const key in obj) {
  console.log(key + ': ' + obj[key]);
}

// 결과 
// a: 1
// b: 2
// c: 3

const array = ['a', 'b', 'c'];

for (const index in array) {
  console.log(index);
}
// 결과 : 0 -> 1 -> 2
```

