# Dom element 가져오기 차이점 

---

>

## 가져오는 방법

1. getElementById 이용
2. getElementsByClassName 이용

- 둘다 HTML 문서에서 요소를 가져오는 방법이다. 
- 하지만 동작과 반환값이 다르다. 

## getElementById

1. 문서에서 특정 ID를 가진 요소를 하나만 가져온다. 

2. **반환값**

   1. 해당 ID를 가진 요소를 직접 반환한다. 
   2. 요소가 존재하지 않으면 `null`을 반환한다

3. 사용법

   ```js
   var element = document.getElementById("mainVideo");
   ```

## getElementsByClassName 

1. `getElementsByClassName`는 지정된 클래스를 가진 모든 요소를 가져온다. 

2. **반환값**

   1. HTMLCollection이라는 유사 배열 객체를 반환한다. 
   2. 요소가 없으면 빈 HTMLCollection을 반환한다. 

3. 사용법

   ```js
   var elements = document.getElementsByClassName("mainVideo");
   ```

## 정리 

1. 즉, 두 기능은 Dom element를 가져오는 기능을 하지만 다른 반환값과 의미를 가지기 때문에 사용시 주의가 필요하다. 