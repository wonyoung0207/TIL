# Vue Error : [vue/no-multiple-template-root]

---

>[참고 사이트1](https://velog.io/@essential193/Vue3-%EC%97%B4-%EB%B0%9B%EB%8A%94-%EC%A0%90)
>
>[비슷한 유형1](https://wouldyou.tistory.com/66#The%20template%20root%20requires%20exactly%20one%20element.eslint-plugin-vue-1)
>
>[비슷한 유형2](https://velog.io/@nammm/vue3-vueno-multiple-template-root-%EC%98%A4%EB%A5%98)

## 문제 

1. `[vue/no-multiple-template-root] The template root requires exactly one element`

## 원인 

1. vue2의 root template 에는 하나의 태그만 존재해야 한다. 즉, 가장 상위 태그는 1개여야 함. 
   1. 해당 문제는 vue3에서는 해결됨 

### 해결

1. root 에 있는 여러 태그들을 div 태그로 감싸줌 

   ```vue
   <template>
     <div>
       <img alt="Vue logo" src="./assets/logo.png">
       <HelloWorld msg="Welcome to Your Vue.js App"/>
     </div>
   </template>
   ```

   

