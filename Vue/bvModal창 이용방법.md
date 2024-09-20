# $bvModal

---

> [bootstrap vue 공식문서 - modal](https://bootstrap-vue.org/docs/reference/size-propsl)
>
> [모달너비 조절 참고1](https://dgkim5360.tistory.com/entry/Bootstrap-modal-custom-size-and-location)
>
> [모달 너비 조절 참고2](https://github.com/bootstrap-vue/bootstrap-vue/issues/2112)

## bvModal

- Bootstrap.vue 에서 제공하는 기능으로 모달창을 띄우기 위해 사용된다. 

## size

1. 기본 제공 크기 
   1. `sm`
   2. `lg`
   3.  `xl`
2. 커스텀 사이즈 
   1. **커스텀하려면 modal-dialog 의 max-width조절해야함**

### 사용법

```vue
<script>
openVideoModal: function (i_camera_info) {
  let me = this;
  let camera_info = i_camera_info;
  this.data_obj = camera_info;
  this.$bvModal.show("event_modal"); // 1. 모달 호출 
},


// 2. 모달 표시 
<b-modal id="event_modal" hide-footer size="sm" content-class="shadow" no-close-on-backdrop no-fade scrollable class="event_modal" @hidden="closeModal">
</b-modal>


// 3. 모달 표시 후 'bv::modal::shown' 이벤트 발생으로 인해 실행 
mounted() {
    this.$root.$on('bv::modal::shown', (bvEvent, modalId) => {
      if ('event_modal' === modalId) {
        EventBus.$emit('eventModalShow'); // 4. 이벤트 버스로 인해 eventModalShow 이벤트 구독중인 부분 실행 
      } else {}
    })
}
    
</script>
```

1. this.$bvModal.show("event_modal"); 로 모달 표시 
2. \<b-modal id="event_modal">  의 모달이 표시 
3. 모달이 다 표시되면 `bv::modal::shown` 에의해 EventBus.$emit('eventModalShow'); 로 이벤트 발생 
   - `'bv::modal::shown'`은 Bootstrap Vue 모달 컴포넌트에서 발생하는 이벤트의 이름
4. mounted에 있는 EventBus가 해당 이벤트 받아서 실행 

## 모달 커스텀

1. 해당 방법은  `Bootstrap v4` 버전의 CSS에서 지원하는 기능으로, v4버전에서만 사용할 수 있다. (이전 버전 사용 불가 )

```vue
// 모달 예시 
<b-modal
    :id="propRef"
    ref="Popup"
    cancel-variant="outline-secondary"
    ok-title="저장"
    cancel-title="닫기"
    centered
    :title="propTitle"
    @ok="onBtnOk"
    @cancel="onBtnCancel"
    size="customSize" <!-- 이렇게 prop 이용해 모달 사이즈 커스텀할 수 있음 -->
    hideHeaderClose
    no-close-on-backdrop
    no-close-on-esc
  	:header-bg-variant="headerBgVariant"
  	:header-text-variant="headerTextVariant"
    :body-bg-variant="bodyBgVariant"
    :body-text-variant="bodyTextVariant"
    :footer-bg-variant="footerBgVariant"
    :footer-text-variant="footerTextVariant"
>
        
<script>
  export default {
    data() {
      return {
        show: false,
        variants: ['primary', 'secondary', 'success', 'warning', 'danger', 'info', 'light', 'dark'],
        headerBgVariant: 'dark',
        headerTextVariant: 'light',
        bodyBgVariant: 'light',
        bodyTextVariant: 'dark',
        footerBgVariant: 'warning',
        footerTextVariant: 'dark'
      }
    }
  }
</script>
<style>
    /* 즉, modal 의 크기는 .modal-<size> 의 형태로 커스텀해서 사용할 수 있다.  */ 
    .modal-customSize{
        max-width : 1200px;
    }    
</style>
```

