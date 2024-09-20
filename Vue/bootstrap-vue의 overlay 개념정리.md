# bootstrap-vue의 overlay 개념정리

---

>[bootstrap-vue 의 overlay 공식문서](https://bootstrap-vue.org/docs/components/overlay)

## overlay란? 

1. 페이지나 컴포넌트 위에 반투명한 오버레이(overlay)를 표시하는 컴포넌트
2. **사용자가 다른 UI 요소를 클릭하지 못하게 하거나, 데이터를 로드하는 동안 시각적인 표시를 추가할 때 사용한다.** 

### 주요기능

1. **Show/Hide**
   1. 오버레이를 토글할 수 있는 `show` 속성을 사용하여 오버레이를 표시하거나 숨길 수 있다.
2. **Customizable**
   1. 오버레이의 투명도, 색상, 애니메이션 등을 쉽게 커스터마이즈할 수 있다.
3. **Content Masking**
   1. 오버레이가 활성화되면 기본 콘텐츠가 비활성화되므로 상호작용이 차단된다. 
4. **`rounded`**
   1. 오버레이의 모서리를 둥글게
5. **`aria-hidden`**
   1. 오버레이가 나타날 때 접근성(Accessibility)을 위해 화면 리더에서 요소를 숨길지 여부를 설정한다. 
   2. `show`가 `true`면 해당 요소는 화면 리더에서 보이지 않도록 설정된다. 
6. **`overlay-class`** 
   1. 해당 속성을 추가하여 CSS 클래스를 커스터마이즈할 수 있다. 
7. `no-wrap` 
   1. 해당 속성을 사용하면 오버레이가 내부 콘텐츠의 크기와 무관하게 부모 요소를 완전히 덮는다. 



```vue
<template>
  <div>
    <b-overlay 
        :show="show" 
        rounded="sm"
        spinner-variant="primary"
        spinner-type="grow"
        spinner-small
   	>
      <b-card title="Card with overlay" :aria-hidden="show ? 'true' : null">
        <b-card-text>Laborum consequat non elit enim exercitation cillum.</b-card-text>
        <b-card-text>Click the button to toggle the overlay:</b-card-text>
        <b-button :disabled="show" variant="primary" @click="show = true">
          Show overlay
        </b-button>
      </b-card>
    </b-overlay>
    <b-button class="mt-3" @click="show = !show">Toggle overlay</b-button>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        show: false
      }
    }
  }
</script>
```

