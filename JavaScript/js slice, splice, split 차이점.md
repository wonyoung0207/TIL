# JS 의 slice, splice, split 차이점 

---

>[참고 사이트1](https://devinserengeti.tistory.com/16)
>
>[참고 사이트2](https://velog.io/@gagaeun/JavaScript-slice-splice-split-%EC%B0%A8%EC%9D%B4)

## 요약

1. slice
   1. **배열에 사용**된다. 
   2. 원본 건들지 않고 복사된 배열을 자르고 리턴 
   3. slice( 시작위치, 끝나는위치 )
   4. **원본 놔두고 새로운 배열**로 리턴 
2. splice
   1. **배열에 사용**된다. 
   2. 원본 배열의 내용을 삭제(잘라내기)하거나 추가
   3. splice( 시작위치, 제거할 갯수, 제거 위치 삽입 요소 )
   4. **원본 배열 변경** 후 **삭제 요소 리턴** 
3. split
   1. **String에 사용**된다 .
   2. 특정 문자를 이용해 문자열을 자른 후 배열로 반환 
   3. split( 구분문자, 반환갯수 )
   4. 반환 갯수 만큼의 **새 배열 만들어 반환** 

## slice

1. 형태 

   1. slice( startingIndex, endIndex)
      1. startingIndex : 시작 index 
      2. endIndex : 끝 index (끝 index 는 포함 X)

2. 반환값

   1. **새로운 배열** 

3. 예시

   ```js
   var origArray = [1, 2, 3, 4, Apple, Mac];
   var newArray = array.slice(0,4);
   //결과값
   //origArray = [1, 2, 3, 4, Apple, Mac];   //원본은 변하지 않음
   //newArray = [1, 2, 3, 4];
   ```

## splice

1. 형태

   1. splice(startingIndex, removeIndex, item)
      1. startingIndex : 시작 index
      2. removeIndex : 시작 index 부터 몇개나 자를지 
      3. item : 제거 위치에 추가할 요소
         1. 배열도 가능 

2. 반환값

   1. **제거한 요소** 

3. 예시

   ```js
   // 예시 1
   var origArray = [1, 2, 3, 4, Apple, Mac];
   var newArray1 = array.splice(2);
   // 결과값 
   //origArray = [1, 2];   //index(2) 이후로는 사라짐
   //newArray1 = [3, 4, Apple, Mac];
   
   // 예시 2
   var origArray = [1, 2, 3, 4, Apple, Mac];
   var newArray2 = array.splice(2, 1);
   // 결과값
   //origArray = [1, 2, 4, Apple, Mac];   //원본에서 3이 사라짐
   //newArray2 = [3];  //잘려져 나온 3
   
   // 예시 3
   var origArray = [1, 2, 3, 4, Apple, Mac];
   var newArray3 = array.splice(0, 0, 'Samsung', 'Galaxy');
   // 결과값
   //origArray = ['Samsung', 'Galaxy', 1, 2, 3, 4, Apple, Mac];
   //newArray3 = [ ];
   ```

## split

1. 형태

   1. split(separator, limit)
      1. separator : 시작 index
      2. limit : 시작 index부터 몇개 반환할지에 대한 수  

2. 반환값

   1. 배열 (시작위치부터 limit 수만큼의 위치까지 배열로 반환 )

3. 예시

   ```js
   // 예시 1 
   var string = "하나, 둘, 셋, 넷, 다섯여섯일곱";
   var newArray = string.split(",", 3);
   //결과값
   //newArray = ["하나", "둘", "셋"];
   
   // 예시 2
   let myString = "the quick brown fox jumps over the lazy dog";
   // 1 
   myString.split(" ");
   //output: ["the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"]
   
   // 2
   myString.split(""); // 인수로 빈 문자열을 전달하면 각 문자를 모두 분리한다. 
   //output: ["t", "h", "e", " ", "q", "u", "i", "c", "k", " ", "b", "r", "o", "w", "n", " ", "f", "o", "x", " ", "j", "u", "m", "p", "s", " ", "o", "v", "e", "r", " ", "t", "h", "e", " ", "l", "a", "z", "y", " ", "d", "o", "g"]
   ```

## 정리 

1. `slice`: (얇게) 썰다 → 배열의 원하는 부분만 똑 떼어내서 복사하여 새 배열로 반환
2. `splice`: RNA 스플라이싱 떠올리기 → 배열 요소 추가 / 삭제 / 교체, 원 배열도 변경
3. `split`: (작은 부분들로) 나누다 → 문자열 나누기