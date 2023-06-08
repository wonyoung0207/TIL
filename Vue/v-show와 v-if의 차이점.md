# vue v-if와 v-show 차이점

## 공통점 

- 컴포넌트나 태그를 숨기거나 보이지 않게 만든다. 

## 다른점

- 실제 Dom에 그려주느냐의 여부에 차이가 있다. 

### v-if

- 값이 true이면 표시, false이면 표시x
- DOM 영역에 실제로 그려주지 않는다. 

### v-show

- css style이 display: none이 되어 보이지만 않게 한다. 
- 문제점
  - 보이지만 않아서 영역을 차지하게 된다. 
  - 이떄 css 에 문제가 생겨 버튼을 눌러도 동작하지 않는 상황이 발생한다. 

- 따라서 숨기는 액션이 반복된다면 v-show를, 아니라면 v-if를 사용하는 것이 좋다. 

### 사용법

```vue
<div id="app">
    <div v-if="show">YES</div>
    <div v-show="show">YES</div>
    <button @click="toggleShow">Toggle</button>
</div>
<script>
    new Vue ({
        el: '#app',
        data :{
            show: true
        },
        methods:{
            toggleShow(){
                this.show = !this.show;
            }
        }
    });
</script>
```



