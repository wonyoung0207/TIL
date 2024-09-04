## Vue에서의 동적 스타일링

---

>

## 동적 스타일링

### 사용법

- vue에서 제공하는 v-on과 v-bind 를 이용해 태그의 스타일을 동적으로 변경할 수 있다. 

### 종류

1. v-bind 와 class 이용
   - ` class="demo" :class="boxAClass"`
2. v-bind 에 배열을 적용해서 class 를 적용 
   - `:class="['demo',boxAClass]"`
3. 변수와 직접적인 바인딩 이용 
   - `:class="{ activeBox : boxCSelected }"`
4. 동적 Style 이용
   - class가 아닌 style 도 동적으로 css 를 적용할 수 있다. 
   - `:style="['',{height : computedMapHeight}]"`
5. **style 과 class 이용** 
   - 동적 class 를 적용하는 것으로는 여러 상황에서 스타일을 적용시키기가 쉽지 않다. 
     - **또한 사용하다보니 `:style=""` 의 방법에 디폴트 옵션과 같이 사용시 동적 옵션만 적용되는 문제있다는 것을 발견**했다. 
     - 즉, `:style="['width : 100px; ',{height : computedMapHeight}]"`  사용시 width 스타일이 적용되지 않는 문제가 발생함. 
     - 이때 사용할 수 있는것이, 동적으로 style 과 class 를 이용하는 것이다. 
   - `:style="['',{height : computedMapHeight}]" :class="{activeBox : boxSelectd}"`

### 동적 class 사용 예시 

```html
<section id="styling">
  <div class="demo" :class="boxAClass" @click = "toggleBox('A')"></div>
  <div :class="['demo',boxAClass]" @click = "toggleBox('A')"></div>
  <div class="demo" :class="{ activeBox : boxBSelected }" @click = "toggleBox('B')"></div>
  <div class="demo" :class="{ activeBox : boxCSelected }" @click = "toggleBox('C')"></div>
</section>
<script>
const app = Vue.createApp({
    data (){
        return {
            boxASelected : false,
            boxBSelected : false,
            boxCSelected : false,
        }
    },
    computed : {
        boxAClass(){
            return {
              activeBox: this.boxASelected, // border 스타일 적용되도록 
            };
        },
        boxBClass(){
            return { width : this.boxBSelected + '%'  }; // 넓이 조절 % 로 
        },
    },
    methods : {
        toggleBox(box){
            if(box === 'A'){
                this.boxASelected = !this.boxASelected;
            }else if(box === 'B'){
                this.boxBSelected = !this.boxBSelected;
            }else{
                this.boxCSelected = !this.boxCSelected;
            }
        }
    },
});
app.mount('#styling');
</script>
```

### 동적 style 과 class 사용 예시 

```vue
<l-map ref="map" 
  :zoom="zoom" 
  :center="[lat, long]" 
  :options="{ attributionControl: false }" 
  :minZoom="13" 
  :style="['',{height : computedMapHeight}]"
  class="defaultMapClass"
  @ready="onResize"
  @click="addMarker"
>
</l-map>

<script>
computed : {
    // map 높이 동적 변경 
    computedMapHeight(){
      if(this.activeMapClass == "CarCall"){
        return "700px";
      }else if(this.activeMapClass == "CarRide"){
        return "600px";
      }else{
        return "400px";
      }
    }
  },
</script>
    
<style lang="scss" scoped>
.defaultMapClass{
  z-index: 2; 
  border-radius : 1.428rem;
}    
</style>
```



## Class 바인딩

### 정의

- vuejs 에서 제공하는 v-bind 를 사용하여 class 의 이름을 바인딩한다. 
- 따라서 바인딩을 통해 클래스 이름이 설정되고 안되고를 변경할 수 있게 된다. 

### 방법

1. data 값을 직접적으로 이용
2. computed() 를 이용해 함수로 처리 
   - 이 방법이 더 권장된다. 
   - 왜냐하면 가독성이 좋아지기 때문에 -> 나중에는 html코드를 많이 보는게 아닌 javascript 코드를 많이 봄 
3. **다중 클래스 적용** 
   1. `col-12 mt-2 mb-2 p-2` 클래스는 계속 적용
   2. `bcardHeightClass` 는 `bcardHeightTF`값에 따라 적용 


### 사용법

```html
<body>
    <div id="app">
        <!-- 방법 1 -->
        <p v-bind:class="{ MyClassName : isError }"></p>
        <!-- 방법 2 -->
        <p v-bind:class="{ errorTextColor }"></p>
    </div>
    
    <!-- 방법 3 -->
	<b-card v-if="showRideState" :class="['col-12 mt-2 mb-2 p-2',{bcardHeightClass : bcardHeightTF}]" no-body>
    
</body>
<script>
new vue({
    el : #app,
    data : {
        isError : false, 
    }
    computed : {
        errorTextColor : function(){
            return this.isError ? 'MyClassName' : null;
        }
    }
})
</script>



```

