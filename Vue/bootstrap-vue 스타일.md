# bootstrap-vue 스타일

---

> [bootstrap vue 공식문서 - modal](https://bootstrap-vue.org/docs/reference/size-propsl)

## 조절방법

1. 태그나 컴포넌트의 속성값으로 간단하게 그리드를 조절할 수 있다. 
2. 단, bootstrap v4 버전에서 지원하기 떄문에 하위에서는 사용할 수 없다. 
   1. 하위 버전에서는 sm, lg 같은 옵션을 사용해야한다. 

### size

1. 버튼 크기 조절
   1. `.btn-<size>`
2. 모달의 크기 조절 
   1. `.modal-<size>` 
3. form 요소 크기 조절
   1. `.form-control-<size>`
4. 페이징 처리 크기 조절
   1. `.pagination-<size>` 

### width

```html
<div class="text-center">
  <div class="w-25 p-3 mb-1 bg-secondary text-light">Width 25%</div>
  <div class="w-50 p-3 mb-1 bg-secondary text-light">Width 50%</div>
  <div class="w-75 p-3 mb-1 bg-secondary text-light">Width 75%</div>
  <div class="w-100 p-3 bg-secondary text-light">Width 100%</div>
</div>
```

### height

```html
<div style="height: 100px; background-color: rgba(255,0,0,0.1);">
  <div class="h-25 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,.1)">
    Height 25%
  </div>
  <div class="h-50 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,.1)">
    Height 50%
  </div>
  <div class="h-75 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,.1)">
    Height 75%
  </div>
  <div class="h-100 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,.1)">
    Height 100%
  </div>
</div>
```

### max & min 

```js
// (max-width: 100%;)
mw-100 
// (max-height: 100%;) 
mh-100
```

