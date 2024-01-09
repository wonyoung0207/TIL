## shift() 와 Unshift() 

---

>[참고 사이트1](https://webisfree.com/2021-01-06/%5B%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%5D-%EB%B0%B0%EC%97%B4%EC%9D%98-%EB%A7%A8-%EC%95%9E%EC%97%90-%EC%B6%94%EA%B0%80%ED%95%98%EA%B1%B0%EB%82%98-%EC%82%AD%EC%A0%9C%ED%95%98%EA%B8%B0-shift-unshift)
>
>[참고 사이트2](https://psychoria.tistory.com/789)

### 사용이유

- 배열의 맨 앞 또는 맨 뒤에 값을 추가할때 사용한다. 
- pop, push와 함께 사용
  - pop : 마지막 값을 꺼내고 배열에서 삭제 
  - push : 배열 끝에 하나 이상의 요소를 추가하고 배열의 새 길이를 반환
- 배열 값을 다룰때는  slice(), splice() 를 많이 사용함 

### 비트의 Shift 연산 (<< , >> , <<< )과는 다르다

1. 비트의 shift 연산
   1. 비트의 표현 자릿수를 옮기는 연산
2. 배열의 shift 
   1. 배열의 첫번째 요소를 제거 하는 메소드

### shift 와 Unshift

1. shift() 

   1. 배열에서 맨 앞에 값 삭제 후 요소 반환 

   ```js
   let mySite = ['web', 'is', 'free'];
   
   mySite.shift();
   // 삭제한 'web'을 출력함
   
   // 실행결과 mySite 값
   ['is', 'free']
   ```

2. Unshift()

   1. 배열 맨 앞의 값 추가 후 새 배열 길이 반환 

   ```js
   let mySite = ['is', 'free'];
   
   mySite.unshift('web');
   // 3을 출력함
   
   // 실행결과 mySite 값
   ['web', 'is', 'free']
   ```

