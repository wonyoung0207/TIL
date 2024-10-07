# vue 라우터 이용 브라우저 탭 명칭 변경

---

>
>
>

## 페이지명

1. 브라우저의 tab 명을 변경하기위해 사용한다. 
   1. vue 의 router 기능을 이용해 페이지별 text 를 브라우저 탭 명으로 사용할 수 있다. 

## 사용개념

1. **vue의 네비게이션가드**
   1. vue의 네비게이션가드인 beforeEach 를 이용해 페이지 이동시 명칭을 동적으로 변경할 수 있다. 
2. **document.title**
   1. vue 애플리케이션이 표출되는 파일로, vue 프로젝트 가장 기본 파일인 index.html에 있는 title 태그 에 내용이 적용된다. 

## 코드

```js
// 메뉴 코드 menuList.js
export default [
  {
    path: "/admin/main",
    name: "main",
    component: () => import("@/views/main"),
    meta: {
      pageTitle: "main페이지",
    },
    beforeEnter(to, from, next) {
      if (
        localStorage.getItem("userData") != null &&
        JSON.parse(localStorage.getItem("userData")).authorId == 30
      ) {
        next({ name: "subErrorPage" });
      } else {
        next();
      }
    },
  },
  {
    path: "/admin/error",
    name: "error-404",
    component: () => import("@/views/error"),
    meta: {
      layout: "full",
    },
  },
  {
    path: "/admin/subPage",
    name: "subPage",
    component: () =>
      import(
        "@/views/subPage.vue"
      ),
    meta: {
      pageTitle: "서브 페이지",
      breadcrumb: [
        {
          text: "서브의 서브 페이지1",
          to: "/admin/subsubPage",
        },
        {
          text: "서브의 서브 페이지2",
          active: true,
        },
      ],
    },
  },
]
```

```js
import menuList from "./routes/menuList";

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
  routes: [
    { path: "/", redirect: { name: "main" } }, 
    ...menuList,
    {
      path: "*",
      redirect: { name: "error-404" },
    },
  ],
});

router.push = function push(location) {
  return originalPush.call(this, location).catch(err => {
    if (err.name !== 'NavigationDuplicated') throw err;
  });
}

router.beforeEach((to, _, next) => {
  // 페이지 이름 설정 
  if(to.meta.breadcrumb && to.meta.breadcrumb[0]){
    let mainTitle = to.meta.breadcrumb[0].text ;  // 메인 페이지 명
    let subTitle = to.meta.pageTitle; // 서브 페이지 명
    const pageTitle = mainTitle + " > " + subTitle + " | KATECH - 5G 실증 통합운영 시스템";
    document.title = pageTitle; // index.html의 title 부분 라우터에 의해 동적 변경 
  }
}
```

