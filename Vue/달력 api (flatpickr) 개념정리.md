# flatpickr

> [공식 사이트](https://flatpickr.js.org/examples/)

## 정의

- 달력 기능을 제공하는 api이다. 
- 여러 옵션을 제공해 쉽게 달력을 구현할 수 있다. 

## 옵션 ( config )

1. 날짜 속성 

| 번호 |    속성     | 내용                                                         |
| :--: | :---------: | ------------------------------------------------------------ |
|  1   |   minDate   | 선택할 수 있는 가장 빠른 날짜                                |
|  2   |   maxDate   | 선택할 수 있는 가장 마지막 날짜                              |
|  3   | dateFormat  | 달력 입력 포맷 (Y:년, m:월, d:일, H:24시간, i:분)            |
|  4   |  altFormat  | 보여지는 입력 포맷 (Y:년, F:월(FullName), j:일)              |
|  5   | defaultDate | 기본 입력 날짜                                               |
|  6   |   disable   | 해당 날짜 비활성화(문자열, 배열) .    ex) 2021-01 선택 불가 > disable: [ { from : "2021-01-01", to: "2021-01-31" } ] |
|  7   |   enable    | 해당 날짜 비활성화(문자열, 배열).    ex) 2021-01 만 선택 가능 > disable: [ { from : "2021-01-01", to: "2021-01-31" } ] |
|  8   |    mode     | 날짜 입력 모드 설정(다중, from to) ("multiple", "range")     |
|  9   | conjunction | 날짜 구분 문자(문자열).   ex) ", " > 2021-01-01, 2021-01-02  |
|  10  |   inline    | 항상 달력이 열려있음(true/false) 기본값 false                |

2. 시간 속성

| 번호 |    속성     | 내용                                                 |
| :--: | :---------: | ---------------------------------------------------- |
|  1   | enableTime  | 시간 속성 사용 여부(true/false)                      |
|  2   | noCalendar  | 달력 제거(true/false)                                |
|  3   | dateFormat  | 달력 입력 포맷 (Y:년, m:월, d:일, H:24시간, i:분)    |
|  4   |  time_24hr  | 24시간 사용 여부(true/false) 기본값 false로 12시간제 |
|  5   |   minTime   | 선택할 수 있는 가장 빠른 시간(문자열) "12:00"        |
|  6   |   maxTime   | 선택할 수 있는 가장 늦은 시간(문자열) "24:00"        |
|  7   | defaultDate | 기본 입력 시간(문자열) "12:00"                       |

3. 날짜 Fomat

| 번호 | 속성 | 내용                                          |
| :--: | :--: | --------------------------------------------- |
|  1   |  d   | 01 ~ 31                                       |
|  2   |  D   | Mon ~ Sun (요일의 짧은 텍스트 표현)           |
|  3   |  l   | Sunday ~ Saturday (요일의 전체 텍스트 표현)   |
|  4   |  j   | 1 ~ 31                                        |
|  5   |  w   | 0(일요일) ~ 6(토요일)                         |
|  6   |  W   | 0(매년 첫째 주) ~ 52(매년 마지막 주)          |
|  7   |  F   | January ~ December (한 달의 전체 텍스트 표현) |
|  8   |  m   | 01 ~ 12                                       |
|  9   |  n   | 1 ~ 12                                        |
|  10  |  M   | Jan ~ Dec (한 달의 짧은 텍스트 표현)          |
|  11  |  y   | 연도 두 자리 숫자 표현(21)                    |
|  12  |  Y   | 년도 전체 숫자 표현 (2021)                    |
|  13  |  Z   | 2021-01-16T12:30:00.000Z                      |
|  14  |  H   | 시간을 24시로 나타냄. 00 to 23                |
|  15  |  h   | 시간을 12로 나타냄. 1 to 12                   |
|  16  |  i   | 분을 나타냄                                   |
|  17  |  S   | 두자리로 초를 나타냄 . 00 to 59               |
|  18  |  s   | 한자리로 초를 나타냄. 0, 1 to 59              |
|  19  |  K   | AM or PM                                      |

### 예시

```javascript
flatPickrConfig: {
    mode: "range",
    locale: Korean,
    enableTime: true,
    // time_24hr : true, // 오전, 오후 없어짐 
    allowInput: false, // 사용자 직접 입력 막기 
    // allowInput: true,
    // time_24hr: true,
    dateFormat: "Y-m-d H:i",
    maxDate: new Date(),
    defaultHour: [5, 10],
    defaultDate: new dayjs().format("YYYY-MM-DD HH:mm"),
    onchange : {

    },
    plugins: [
      new ConfirmDatePlugin({
        confirmIcon: "",
        confirmText: "OK", // 달력 버튼 
        showAlways: true,
        theme: "light", // or "dark"
      }),
    ],
},
```



## 이벤트  ( Events )

| 번호 |      속성      | 내용                           |
| :--: | :------------: | ------------------------------ |
|  1   |    onChange    | 날짜가 선택되었을 때           |
|  2   |     onOpen     | 달력이 열릴 때                 |
|  3   |    onClose     | 달력이 닫힐 때                 |
|  4   | onMonthChange  | 월이 변경되었을 때             |
|  5   |  onYearChange  | 년도가 변경되었을 때           |
|  6   |    onReady     | 달력이 준비 상태가 되었을 때   |
|  7   | onValueUpadate | 입력값이 새 날짜로 바뀌었을 때 |





---

## 다국어 처리 

```js
import { Korean } from "flatpickr/dist/l10n/ko.js"; // locale 에 설정하기 위해서 flatpickr 파일에서 ko 를 가져와야 한다. 


flatPickrConfig: {
    mode: "range",
    locale: this.$i18n.locale == "ko" ? Korean : null, // 영문 버전이 default 이기 때문에 null 로 설정해주면 된다. 
    enableTime: true,
    // time_24hr : true, // 오전, 오후 없어짐
    allowInput: false, // 사용자 직접 입력 막기
    // allowInput: true,
    // time_24hr: true,
    dateFormat: "Y-m-d H:i",
    maxDate: new Date(),
    defaultHour: [5, 10],
    defaultDate: new dayjs().format("YYYY-MM-DD HH:mm"),
    plugins: [
      new ConfirmDatePlugin({
        confirmIcon: "",
        confirmText: "OK", // 달력 버튼
        showAlways: true,
        theme: "light", // or "dark"
      }),
    ],
},
    
// locale 을 변경할 경우 실행 
setUpdateDate() {
    if (this.$i18n.locale == 'ko') {
        this.flatPickrConfig.locale = Korean
    this.dateSeparator = ' ~ '
    } else {
    	this.flatPickrConfig.locale = null
    	this.dateSeparator = ' to '
    }
    this.mergeDate = `${this.searchStartDate}${this.dateSeparator}${this.searchEndDate}`; // 기간
},
```

