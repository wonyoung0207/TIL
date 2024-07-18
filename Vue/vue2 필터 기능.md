# vue2 필터 기능

---

>

## filter 란? 

1. Vue 2에서 `|` 연산자는 필터를 나타낸다. 
2. 즉, `|` 를 이용해 동적으로 변경된 값을 나타낼 수 있다. 

### 사용 예시

```vue
<template>
    <span>{{ downAvgspeed | speedColor }}</span>
    <div>
        <span class="number"  v-bind:class="upAvgSpeed | speedColor">{{upAvgSpeed.toFixed(1)}}</span>km/h
    </div>
</template>
<script>
export default {
	data(){ ... }
	created(){ ... }
	mounted(){ ... }
    filters: {
        speedColor(speed) {
          if (speed > 60)
            return "speedColor1";
          else if (speed > 40)
            return "speedColor1";
          else if (speed > 20)
            return "speedColor2";
          else
            return "speedColor2";
        }
	}
}
</script>
```



## vue3 에서의 filter 기능

1. Vue 3에서는 필터 기능이 제거되었기 때문에, 동등한 기능을 구현하기 위해 **메서드**나 계산된 속성을 사용해야 한다. 
   1. toFixed(1) : 소수 첫자리 반올림 

```vue
<template>
  <div>
    <span class="number" :class="speedColor(downAvgspeed)">
      {{ downAvgspeed.toFixed(1) }} 
    </span> km/h 
  </div>
</template>

<script>
export default {
  data() {
    return {
      downAvgspeed: 25.5 // 예시 데이터
    };
  },
  methods: {
    speedColor(speed) {
      if (speed > 30) {
        return 'fast';
      } else if (speed < 10) {
        return 'slow';
      } else {
        return 'moderate';
      }
    }
  }
};
</script>

<style>
.fast {
  color: red;
}
.slow {
  color: blue;
}
.moderate {
  color: green;
}
</style>

```

