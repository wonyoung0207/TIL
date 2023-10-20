# Vue의 Filters

---

## Filters

### 정의

- 데이터를 변환하거나 포맷팅하는 데 사용되는 함수이다.
  - 파이프 연산자 ( | ) 와 함께 쓰이며 호출 시 특정 처리를 진행 후 값을 반환한다. 
  - 따라서 다른 형태로 데이터를 처리할 때 많이 사용한다. ( 날짜 포멧 변경 )

- 매개변수 
  - 필터 함수는 첫 번째 매개변수로 필터링할 데이터(`value`)를 전달받는다. 
  - 함수 내부에서는 `moment` 라이브러리를 사용하여 날짜를 지정한 형식으로 포맷팅한 후 반환한다.


### 사용법

```vue
<template>
  <div>
    <p>{{ dateValue }}</p> <!-- data 자체 출력 -->
    <p>{{ dateValue | formatDate }}</p> <!-- filters에 구현되어있는 함수 호출 후 설정한 데이터 포맷 으로 출력 -->
  </div>
</template>

<script>
import moment from 'moment'; // 날짜를 원하는 포멧으로 변경하기 위한 라이브러리 

export default {
  data() {
    return {
      dateValue: '2023-05-18T10:30:00',
    };
  },
  filters: {
    formatDate(value) {
      if (!value) return '';

      return moment(value).format('YYYY-MM-DD HH:mm');
    },
  },
};
</script>
```

### 파이프 연산자 ( | )

- 데이터를 템플릿에서 필터링하거나 변환할때 사용한다. 
- `|`를 사용하여 **데이터와 필터를 연결**하면 **데이터가 필터로 전달**되고, **필터는 데이터를 변환하여 반환**한다. 
  - 이렇게 변환된 데이터는 템플릿에서 출력된다. 
  - 따라서 파이프 연산자를 통해 데이터의 형태를 변형 후 비교한다. 
