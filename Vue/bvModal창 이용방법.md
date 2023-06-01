# $bvModal

---

## bvModal

- Bootstrap.vue 에서 제공하는 기능으로 모달창을 띄우기 위해 사용된다. 

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

