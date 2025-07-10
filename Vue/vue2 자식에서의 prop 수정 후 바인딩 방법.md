# vue2 자식에서의 prop 수정 후 바인딩 방법

---

>Vue 2에서 자식 컴포넌트의 `prop` 수정 후 상위로 바인딩하는 방법 (`.sync` 기반)

## 개요

- 개발도중 prop으로 받은 데이터를 자식 컴포넌트에서 재가공해야하는 일이 있었다. ( 테이블 Filtering 인데, 하위 컴포넌트에서 totalCount 값을 Filtering한 Data로 변경해야했음 )
- Vue 2에서는 `props`는 **읽기 전용**이기 때문에, 자식 컴포넌트에서 직접 수정할 수 없었다. 
- 하지만 `.sync`를 사용하면 자식에서 `@update:propName` 이벤트를 발생시켜 **상위 데이터를 업데이트**를 할 수 있다고하여 적용해 보았다. 

## 방법

1. 자식 컴포넌트에서 Prop으로 값을 받아온다. 
2. 특정 이벤트 상황에서 Prop의 데이터를 직접 변경하지 않고 상위로 변경 사항을 전파한다. (`$emit` 이용)
3. 상위는 변경된 값을 다시 하위로 전파해준다. 

### 사용 조건

- Vue 2.x
- Composition API를 쓰지 않아도 가능 ( Options API 사용중이였기 때문에 )
- 상위에서 `.sync` 문법으로 바인딩할 때, 해당 값은 **반응형(예: data, computed, ref 등)** 이어야 함

### 상위 컴포넌트 예시

```vue
<template>
	<!-- 방법1. sync 문법 이용(더 좋은듯?) -->
	<equipment-table :total-rows.sync="totalRows" />	

	<!-- 방법2. 이벤트로 직접 수정 -->
    <equipment-table
        :total-rows="totalRows"
        @update-total-rows="totalRows = $event"
      />
</template>

<script>
export default {
  data() {
    return {
      totalRows: 0,
    }
  }
}
</script>
```

### 자식 컴포넌트 예시 (`equipment-table.vue`)

```vue
<tamplate>
    	<b-table>
            ...
    	</b-table>
    
    	<!-- equipment-table 자식인 pageing처리 컴포넌트로도 전파해야함 -->
        <equipment-pagenation
            :total-rows="totalRows"
            :row-count.sync="rowCount"
            :current-page-num.sync="currentPageNum"
        />
</tamplate>
<script>
export default {
    props: {
      totalRows: Number
    },
    methods: {
      onFiltered(filteredItems) {
        // props 직접 수정은 ❌
        // 방법1. update 이벤트를 통해 부모의 값을 수정
        this.$emit('update:totalRows', filteredItems.length);

        // 방법2. 직접 props를 수정하는 대신 이벤트로 부모에게 전달
        this.$emit('update-total-rows', filteredItems.length);
      }
    }
}
</script>
```

------

### 핵심 포인트

- `.sync`는 내부적으로 `v-bind:prop` + `@update:prop` 를 sugar syntax로 줄인 것
- 자식 컴포넌트에서는 **`$emit('update:xxx', newVal)`** 을 통해 상위 값을 반영
- 상위에서는 자동으로 해당 prop을 업데이트