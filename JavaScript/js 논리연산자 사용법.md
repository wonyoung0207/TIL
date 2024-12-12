### 논리연산자의 특이한 사용 

- or 연산자 ( || ) : 첫번째 값이 존재하면 첫번째 값을 리턴함. 그렇지 않다면 2번쨰 값 리턴 
- and 연산자 ( && ) : 두개의 값이 모두 true이면 가장 마지막값을 리턴함 .
  - 둘 중 하나라도 false면 false를 리턴한다. 

```typescript
// OR 연산자 
newValueMap.set('phoneNumber', newPhoneNumber || foundMember.phoneNumber); // => newPhoneNumber에 값이 있으면 해당값이 map의 value로 들어가고, 만약 없다면 foundMember.phoneNumber 가 value로 들어간다. 

// And 연산자 
// 조건문 사용 
if(person.age > 20 ){ 
	console.log('운전가능 ')
} else{
    console.log('운전불가 ')
}
// 논리연산자 사용 
console.log( person.age > 19 && '운전가능 ') // person.age >19 와 '운전가능' 이 참이기 때문에 마지막 값인 '운전가능' 을 리턴함 
```

### 논리 연산자 !! 두개 쓰는 이유 

- 한개인 경우 = 부정

- 두개인 경우 = 긍정 ( 부정의 부정 )

  - Boolean형으로 형변환을 위해 사용됨
  - 예를들어 0을 false로, 1을 true로 만들어줄 때에 사용한다. 

- 자바스크립트의 기본 데이터 타입

  - **다음 6가지만 false가 출력되고 이외는 모두 true이다.** 

  | ””        | 빈 문자열          |
  | --------- | ------------------ |
  | false     | 기본 boolean false |
  | NaN       | Not a Number       |
  | undefined | 정의되지 않은 값   |
  | null      | Null값             |
  | 0         | 숫자 기본값        |

### 