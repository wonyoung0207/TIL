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

### 예시 

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

