# Typescript 이용 vue3 예시

---

>

## script

1. setup 명시 
   1. vue3에서는 composition API를 사용하는데, 이때 script 태그 자체에 setup 을 적용시킬 수 있다. 
2. lang 명시
   1. js 가 아닌 ts 를 사용하려면 script 태그에 lang="ts" 를 명시해주어야 한다. 
3. import 명시 
   1. type을 import 할때는 명시적으로 `import type` 명령어를 사용하는 것이 좋다. 

```vue
<!-- vue 파일 -->
<script setup lang="ts">
import { onMounted, ref } from 'vue';
import type { Event } from './EventOption';
import { loadEventWidget } from './EventOption';

const eventList = ref<Array<Event> | null>(null);
    
onMounted(() => {
  let loadList = loadEventWidget();
  eventList.value = loadList.value
})

</script>

<template>
  <div>
    <VBtn 
      v-for="item in eventList"
      :key="item.title"
      :color="item.color"
      rounded="pill" 
      size="small"
    >
      <VIcon
        start
        :icon="item.icon"
      />
      <span>{{ item.title }}</span> 
    </VBtn>
  </div>
</template>

```

## Function

1. vue 파일에서 사용하는 function 들의 집합
2. type 생성
   1. Event 라는 Object의 타입을 생성할 수 있다. 

```ts
// EventOption.ts

import { useEventWidgetStore } from '@/stores/widgets/event-store';
  // 타입 정의
export type Event = {
  title: string;
  icon: string;
  color: string;
};

export const loadEventWidget = () =>{
  const eventWidgetStore = useEventWidgetStore();
  const { eventList } = storeToRefs(eventWidgetStore);

  return eventList;
}
```

## Store

1. vue 파일에서 사용하는 데이터(상태) 저장소 
2. pinia
   1. vue3에서는 pinia store를 사용하는것이 권장된다. 

```ts
// event-store.ts
import { defineStore } from 'pinia'

export const useEventWidgetStore = defineStore('eventWidget', {
  state: () => ({
    eventList : [
      {title : "전체", icon : "tabler-checkbox", color : "primary"},
      {title : "보행자", icon : "tabler-fall", color : "success"},
      {title : "역주행", icon : "tabler-arrow-back", color : "secondary"},
      {title : "화제", icon : "tabler-campfire", color : "error"},
      {title : "급감속", icon : "tabler-car-crash", color : "warning"},
      {title : "지정체", icon : "tabler-circle-minus", color : "info"},
      {title : "정지차", icon : "tabler-circle-off", color : "error"},
    ]
  }),
  actions: {
    
  },
})
```

