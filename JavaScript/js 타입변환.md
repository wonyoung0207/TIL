# js에서의 타입변환

---

>[참고 사이트1](https://blog.outsider.ne.kr/361)
>
>[참고 사이트2](https://www.freecodecamp.org/korean/news/how-to-convert-a-string-to-a-number-in-javascript/)

## 숫자 타입변환 방법

### 1. Number()

- 어떤 값이든 숫자로 강제 변환한다. 
- 문자열을 숫자로 변환할 때는 문자열이 숫자로 완벽하게 변환될 수 있는 경우에만 변환된다.

```js
a = Number('123');    // 결과: 123
Number('hello');  // 결과: NaN (숫자로 완벽하게 변환할 수 없음) -> NaN = Not a Number
Number(true);     // 결과: 1
Number(false);    // 결과: 0
Number(null);     // 결과: 0

console.log(typeof a);    // Result : string
```

### 2. parseInt()

- 주어진 문자열에서 정수를 해석하여 반환한다. 
- 문자열의 **시작부분에서 숫자만 추출**하고, 그 이후의 문자는 무시한다. 
  - 즉, 처음부터 문자가 오면 숫자 보지 않고 문자라고 인식하며 뒤에 문자가 있어도 Number() 와는 다르게 숫자 부분만 가져와 숫자로 변환한다. 
- 또한 정수 부분만을 반환하며 소수점 이하를 무시한다. 

```js
parseInt('123');      // 결과: 123
parseInt('123hello'); // 결과: 123
parseInt('hello123'); // 결과: NaN (첫 번째 문자가 숫자가 아니므로 변환 실패)
parseInt('12.34');    // 결과: 12 (소수점 이하를 무시)
parseInt('0xf');      // 결과: 15 (16진수로 해석)
```

