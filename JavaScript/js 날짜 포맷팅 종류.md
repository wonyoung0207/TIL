# js 날짜 포맷팅 종류 

---

## Intl.DateTimeFormat 이용

```js
// 현재 시간 
let date = new Date(); 

// 서부 인도네시아 시간대로 변경
const options = {
    timeZone: 'Asia/Jakarta', // Jakarta는 WIB 시간대에 속함
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    weekday: 'long',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    hour12: true // 24시간 형식
};

// Intl.DateTimeFormat을 사용하여 포맷팅
const intlFormatter = new Intl.DateTimeFormat('id-ID', options);
const formattedParts = intlFormatter.formatToParts(date);

// 요일만 영어로 포맷팅
const weekdayFormatter = new Intl.DateTimeFormat('en-US', options);
const weekdayFormattedParts = weekdayFormatter.formatToParts(date);
const weekdayPart = weekdayFormattedParts.find(part => part.type === 'weekday');
const enWeekday = weekdayPart.value;

// 시, 분, 초를 개별적으로 추출
let year, month, day, weekday, hours, minutes, seconds, dayPeriod ;
formattedParts.forEach(part => {
    if (part.type === 'year') {
        year = part.value;
    } else if (part.type === 'month') {
        month = part.value;
    } else if (part.type === 'day') {
        day = part.value;
    } else if (part.type === 'weekday') {
        weekday = part.value;

    } else if (part.type === 'hour') {
      hours = part.value;
    } else if (part.type === 'minute') {
      minutes = part.value;
    } else if (part.type === 'second') {
      seconds = part.value;
    } else if (part.type === 'dayPeriod'){
      dayPeriod = part.value; // AM 또는 PM
    }
});


this.currentDate = `${year}-${month}-${day} (${enWeekday})`;

// Intl.DateTimeFormat을 사용하여 포맷팅
this.currentTime = hours + ":" + minutes + ":" + seconds + " " + dayPeriod;
this.timer = setTimeout(this.setCurrentTime, 1000);
```



## toLocalTimeString 이용

```js
this.timerVersion = 'ko-KR'
if(this.$i18n.locale == 'ko'){
  this.timerVersion = 'ko-KR';
}else if(this.$i18n.locale == 'id'){
  this.timerVersion = 'id-ID';
}else{
  this.timerVersion = 'en-US';
}

let now = new Date();
this.currentTime = now.toLocaleTimeString(this.timerVersion);
let addDate = null;


if(this.regDate == null && this.regDate == ''){
    this.regDate = '9999-99-99'
    this.expireDate = this.regDate 
}
// 현재에 7일을 더한 날짜 계산
addDate = new Date(this.regDate);
addDate.setDate(addDate.getDate() + 7);
this.expireDate = this.formatDate(addDate);

formatDate(date) {
  const year = date.getFullYear();
  const month = ('0' + (date.getMonth() + 1)).slice(-2);
  const day = ('0' + date.getDate()).slice(-2);
  return `${year}-${month}-${day}`;
},
```



## Date 이용 

```js
getCurrentDate() {
  var date = new Date();
    
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  var hour = date.getHours();
  var minute = date.getMinutes();
  var second = date.getSeconds();

	// 앞에 0 추가 
  month = month < 10 ? "0" + month : month;
  day = day > 9 ? day : "0" + day;
  hour = hour > 9 ? hour : "0" + hour;
  minute = minute > 9 ? minute : "0" + minute;
  second = second > 9 ? second : "0" + second;

  return (
    year.toString() +
    month.toString() +
    day.toString() +
    hour.toString() +
    minute.toString() +
    second.toString()
  );
},

// 날짜 표출 
changeOcrnDt(value) {
  const year = value.slice(0, 4);
  const month = value.slice(4, 6);
  const day = value.slice(6, 8);
  const hour = value.slice(8, 10);
  return `${year}-${month}-${day} ${hour}`;
},
```



