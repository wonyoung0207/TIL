# vue 라우터 Navigation Cancelled Error

---

>

## Error 발생 내용 

```js
Uncaught (in promise) Error: Navigation cancelled from "/event" to "/dashboard" with a new navigation.
```

1. 발생이유
   1. 메뉴버튼에 router 가 지정되어있어 클릭시 페이지 이동. 
   2. 이때 router 실행전 `this.$router.go(-1);` 를 이용해 페이지를 이전 페이지로 이동
   3. 즉, 라우터에 의해 이동이 되어야 하는데, 그전에 강제로 라우터의 이동을 실행해 이전 라우터의 동작이 취소됨

## 해결 방법

1. 버튼에 걸려있는 라우터 정보 삭제 

   1. 라우터의 이동을 삭제하고, 동작하지 않도록 함

2. 기존 로직

   ```js
   // menu.js 
   {
       title: "대시보드",
       i18nCode: "label.L0001",
       icon: "GridIcon",
       route: "dashboard", // route 설정으로 인해 메뉴 클릭시 router 가드에 의해 페이지 이동 
       pageAuth: ["A", "U"],
   },
   ```

   ```js
   // verticleNameMenuItem.vue => vuexy 템플릿의 verticle 메뉴 이용 
   clickMenu(item){
     let i18nMenuText = this.$t(`${item.i18nCode}`); // 메뉴 이동 router Text 
     let dashboardLocaleText = this.$t("label.L0001"); // 대시보드 Locale Text
   
     // 대시보드 페이지만 새창 열기 
     if( i18nMenuText == dashboardLocaleText){
       window.open(this.$router.resolve({ name: 'dashboard' }).href, '_blank');
       this.$router.go(-1); // 여기서 에러가 발생. (라우터가드가 dashboard 로 이동하려고 했는데, go() 로인해 취소되어 발생하는 문제 )
     }
   },
   ```

3. 해결 로직 

   ```js
   // menu.js 
   {
       title: "대시보드",
       i18nCode: "label.L0001",
       icon: "GridIcon",
       pageAuth: ["A", "U"],
   },
   ```

   ```js
   // verticleNameMenuItem.vue => vuexy 템플릿의 verticle 메뉴 이용 
   clickMenu(item){
     let i18nMenuText = this.$t(`${item.i18nCode}`); // 메뉴 이동 router Text 
     let dashboardLocaleText = this.$t("label.L0001"); // 대시보드 Locale Text
   
     // 대시보드 페이지만 새창 열기 
     if( i18nMenuText == dashboardLocaleText){
       window.open(this.$router.resolve({ name: 'dashboard' }).href, '_blank');
     }
   },
   ```

   