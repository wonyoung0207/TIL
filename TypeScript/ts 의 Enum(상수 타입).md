# ts 의 Enum(상수 타입)

---

>

## Enum ( 열거형 타입)

### 정의

- **상수들을 관리하기 위한 객체**로, 상수의 집합을 정의

### 상수와 일반상수

- 상수 : 시용자 정의 상수 => const 를 이용한 변수 
  - 속성을 변경할 수 없음 
  - const PI = 3.14    => PI라는 변수는 사용자 정의 변수가 되어 다른 곳에서 PI의 값을 변경하지 못한다. 변경하고 싶으면 초기화 부분의 값을 변경해줘야한다. 
- 일반상수 : nummber , string ...
  - 속성의 변경이 가능 

### 사용방법

1. enum 키워드로 시작
2. 클래스처럼 중괄호 { }  사용 
3. key = value 의 형태로 나열 
4. 열거형 속성은 **숫자와 문자열만 허용**한다. 

```typescript
enum Coloe{
    red = 'r',
    blue = 'b',
    green = 'g'
}
// 상수의 집합 = enum(열거형)
const color : Color = Color.red;
```

### 리버스 멥핑 

- key로 값을꺼낼 수 있고, 값으로도 key를 꺼낼 수 있는 구조 

- 열거형 앞에 **const 가 안붙는다면** 일반적으로 리버스 맵핑이 가능하다. 

  ```typescript
  console.log(color.red ) // 출력 : r
  console.log(color['r']) // 출력 : red
  ```

  