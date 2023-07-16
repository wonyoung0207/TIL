# Class 바인딩 

---

>

## Class 바인딩

### 정의

- vuejs 에서 제공하는 v-bind 를 사용하여 class 의 이름을 바인딩한다. 
- 따라서 바인딩을 통해 클래스 이름이 설정되고 안되고를 변경할 수 있게 된다. 

### 방법

1. data 값을 직접적으로 이용
2. computed() 를 이용해 함수로 처리 
   - 이 방법이 더 권장된다. 
   - 왜냐하면 가독성이 좋아지기 때문에 -> 나중에는 html코드를 많이 보는게 아닌 javascript 코드를 많이 봄 

### 사용법

```html
<body>
    <div id="app">
        <!-- 방법 1 -->
        <p v-bind:class="{ MyClassName : isError }"></p>
        <!-- 방법 2 -->
        <p v-bind:class="{ errorTextColor }"></p>
    </div>
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

