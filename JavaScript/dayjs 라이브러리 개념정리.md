# dayjs 라이브러리 개념정리 

---

>

## dayjs

### 정의 

1. 가벼운 JavaScript 날짜 및 시간 라이브러리로, Moment.js의 대안으로 사용된다. 
2.  **날짜 및 시간을 쉽게 다루기 위한 다양한 기능과 메서드**를 제공하며, 특히 파일 크기가 작고 성능이 우수하다는 장점을 가진다. 

### 장점

1. 날짜 및 시간 다루기
   1. 날짜 및 시간을 편리하게 생성, 파싱, 포맷팅할 수 있다. 
2. 날짜 계산
   1. 날짜 간의 차이를 계산하거나, 특정 시간을 추가/빼는 등의 연산을 수행할 수 있다. 
3. 다국어 지원
   1. 날짜 및 시간 형식을 다양한 언어로 지원한다. 
4. 경량화
   1. Moment.js에 비해 작은 파일 크기를 가지고 있어서 웹 애플리케이션에서도 쉽게 사용할 수 있다. 

### 설치 

```nginx
npm install dayjs
```

### 사용법

- 시간 표현 
  - `YYYY`: 연도 (4자리)
  - `MM`: 월 (01부터 12까지)
  - `DD`: 일 (01부터 31까지)
  - `HH`: 시간 (00부터 23까지, 24시간 형식)
  - `mm`: 분 (00부터 59까지)
- subtract(숫자, 타입) 
  - 이전 시간을 구하는 메소드 
  - 타입에는 month, day 등등이 올 수 있다. 
  - 주의할점은 subtract으로 설정하지 않은 값은 자동으로 00 으로 설정된다. 
    - 즉, `dayjs().subtract(7, "day")` 의 경우 시간, 분 부분은 자동으로 00:00 으로 설정된다. 

```js
// Day.js 라이브러리 로드
const dayjs = require('dayjs');

// 현재 시간 가져오기
const now = dayjs();

// 포맷팅된 날짜 문자열 생성
const formattedDate = now.format('YYYY-MM-DD HH:mm:ss');
```

### 예제 

```js
// 특정 날짜 생성
const date1 = dayjs('2024-04-20');

// 날짜 포맷팅
console.log(date1.format('YYYY/MM/DD')); // "2024/04/20"

// 현재 시간 표시 
console.log(dayjs().format("YYYY-MM-DD HH:mm") );

// 현재 시간으로부터 7일전 Date
dayjs().subtract(7, "day");

// 현재 시간으로부터 7일전의 자정(00:00:00)의 Date
dayjs().subtract(7, "day").startOf('day').format("YYYY-MM-DD HH:mm");

// 현재 시간에서 오늘 마지막 시간의 Date
dayjs().endOf('day').format("YYYY-MM-DD HH:mm");

// 현재 시간으로부터 7일전 12시 30분의 Date
dayjs().subtract(7, "day").set("hour", 12).set("minute", 30);

// 날짜 계산 (1주 후)
const nextWeek = date1.add(1, 'week');
console.log(nextWeek.format('YYYY/MM/DD')); // "2024/04/27"

// 날짜 비교
const date2 = dayjs('2024-04-25');
console.log(date1.isBefore(date2)); // true

// 다국어 지원 (한국어)
const localizedDate = date1.locale('ko');
console.log(localizedDate.format('YYYY년 MM월 DD일')); // "2024년 04월 20일"
```



