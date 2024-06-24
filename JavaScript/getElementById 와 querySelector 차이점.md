# getElementById 와 querySelector 차이점

---

>

## getElementById 

1. ID 속성 값으로 요소를 선택한다. 

2. 문법

   ```js
   document.getElementById(id);
   ```

3. 특징

   1. ID는 문서에서 유일해야 하므로 항상 단일 요소를 반환한다.
   2. 사용 시 ID 문자열에 `#`을 포함하지 않는다.
   3. **지정한 ID를 가진 단일 요소를 반환**

## querySelector 

1. CSS 선택자를 사용하여 요소를 선택한다. 

2. 문법

   ```js
   document.querySelector(selectors);
   ```

3. 특징

   1. CSS 선택자를 사용하므로 ID뿐만 아니라 클래스, 태그, 속성 등을 기준으로 요소를 선택할 수 있다. 
   2. 선택자 문자열에 `# or .`을 포함해야 한다. 
   3. **일치하는 첫 번째 요소를 반환**

## 공통점

1. DOM에서 요소를 선택하는 방법이다. 
2.  요소가 없으면 `null`을 반환한다. 

## 차이점

1. 선택자 사용 기준 
   1. `getElementById("myDiv")`
   2. `querySelector("#myDiv")`
2. 범위 
   1. getElementById 는 태그의 id 만 검색 가능 
   2. querySelector 는 여러가지 태그의 속성 검색 가능 
      1. `.myClass or #myId 등등...`
3. 성능
   1. `getElementById`는 특정 ID를 빠르게 찾는 데 최적화되어 있어 더 빠르다. 
   2. `querySelector`는 더 일반적인 선택기 분석이 필요하기 때문에 약간 느릴 수 있다. 

## 정리

1. 범용적으로 사용할 경우 querySelector를 사용하면 편하게 사용할 수 있다. 
   1. id, class 요소를 구분하지 않아도 됨
   2. 하지만 **일치하는 첫 요소를 반환** 하기 때문에, class나 id에 같은 이름을 사용하고 있다면 잘못 가져올 수 있다. 
2. **더 빠른 속도를 원한다면 getElementById** 를 사용하는것이 좋다. 
3. 둘 다 값을 찾지 못하면 **null 을 반환**한다. 