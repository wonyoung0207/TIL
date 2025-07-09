# vue2의 자식과 부모 데이터 양방향 바인딩

>

##### Vue 2 Options API 에서 .sync`를 이용해 자식 컴포넌트에서 부모의 데이터를 양방향 바인딩하는 방법

---

## 구조 요약

- **부모 → 자식**: prop으로 값 전달 (`:selected-tunnel.sync="..."`)
- **자식 → 부모**: `$emit('update:selectedTunnel', 값)` 으로 값 변경 알림
- `.sync`는 자동으로 setter 역할을 해줌

---

## 1. 부모 컴포넌트 (`ParentComponent.vue`)

```vue
<template>
  <div>
    <equipment-filter-form
      :selected-tunnel.sync="SB_tunnelCodeData"
      :tunnel-options="tunnelCodeOptions"
    />
    <p>선택된 터널: {{ SB_tunnelCodeData }}</p>
  </div>
</template>

<script>
import EquipmentFilterForm from './components/EquipmentFilterForm.vue'

export default {
  components: { EquipmentFilterForm },
  data() {
    return {
      SB_tunnelCodeData: null,
      tunnelCodeOptions: [
        { text: '터널 A', data: 'A' },
        { text: '터널 B', data: 'B' },
      ]
    }
  }
}
</script>
```

## 2. 자식 컴포넌트 (`EquipmentFilterForm.vue`)

```vue
<template>
  <v-select
    label="text"	
    :value="selectedTunnel"
    :clearable="false"
    :reduce="(d) => d.data"
    :options="tunnelOptions"
    @input="$emit('update:selectedTunnel', $event)"
  />
</template>

<script>
export default {
  props: {
    selectedTunnel: [String, Object],
    tunnelOptions: Array
  }
}
</script>
```

## 핵심 

- `.sync`는 Vue에서 **부모 → 자식으로 값을 전달하면서, 자식 → 부모로 변경사항을 반영할 수 있게** 만들어주는 **양방향 바인딩 sugar syntax** 이다. 

  ```vue
  <!-- 부모 -->
  
  <!-- 외부 형태 -->
  <my-component :some-prop.sync="value" />
  
  <!-- 내부 동작 구조 -->
  <my-component
    :some-prop="value"
    @update:someProp="value = $event"
  />
  ```

  1. 부모가 `value`를 `some-prop`으로 자식에게 전달
  2. 자식이 `$emit('update:someProp', newValue)` 하면
  3. 부모가 그 값을 받아서 `value = newValue` 로 자동 반영

- `$emit('update:propName', value)`

  - 자식 컴포넌트가 **`update:propName` 이벤트를 emit하면 부모의 값을 갱신**
  - `$event`는 DOM 이벤트 핸들러에서 전달되는 **입력값 또는 이벤트 객체** 이다. (즉, 입력 Text)

  ```vue
  <!-- 자식 -->
  
  <b-form-input :value="someProp" @input="$emit('update:someProp', $event)" />
  <script>
  export default {
      props : {
          someProp : String, 
      }
  }
  </script>
  <!-- 만약 hello 검색시, $emit('update:searchText', 'hello')로 부모 전달 -->
  ```

##### Modal 과 .sync 차이점 

- `v-model`은 내부적으로 `:value` + `@input`을 사용하는 양방향 바인딩
- `.sync`는 이걸 **prop 이름을 원하는 대로 커스터마이징**해서 쓸 수 있게 해준다. 

## 주의할점

- 이 패턴은 Vue 2.3 이상에서 사용가능하며
- `v-model`을 커스텀으로 사용하고 싶다면 `model` 옵션을 활용할 수 있다. (`model: { prop: 'value', event: 'input' }`).