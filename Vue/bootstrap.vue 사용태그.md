# bootstrap.vue 사용

---

> [템플릿 스타일](https://demos.pixinvent.com/vuexy-vuejs-admin-template-vue2/demo-1/forms/form-element/vue-select)

## b-table

- 필드에 값을 넣어주면 자동으로 row 로 표시

```java
<b-table
    ref="maintable"
    striped
    hover
    responsive
    :fields="tableColumns" // 표시할 필드명 ( ex. 사이트 아이디, 사이트명 등등... ) 
    :items="items" // 표시할 필드의 데이터 값 ( 필드명으로 지정한 값들이 맞춰저 들어감 ) 
    :per-page="rowNum" // 페이지 관련 
    :sort-by.sync="sortValue" // 어떤 필드를 기준으로 sort 할 것인지 
    sort-desc.sync="true"
    :filter="filter" // 필터 검색어 
    :filter-included-fields="filterOn" // 필터시 사용될 필드명
    @filtered="onFiltered" // 필터된 결과 후 호출되는 함수 
    @row-clicked="updateRow" // 행 클릭시 실행 함수 
	@head-clicked="toggleColumn" // Column명 클릭시 실행 함수 
    :current-page="currentPage" // 현재 페이지 
    show-empty
    empty-text="데이터가 존재하지 않습니다." // 테이블 데이터 비엇을 경우 표시 텍스트
    empty-filtered-text="검색어와 일치하는 결과가 없습니다." // 필터값 비었을 경우 실행 
    style="min-height: 55vh; margin-top: 5px;"
    class="mb-0 text-center"
>
```



## v-select 에서 다중선택

```html
<v-select
  v-model="selected" // 선택된 값 바인딩
  :dir="$store.state.appConfig.isRTL ? 'rtl' : 'ltr'" // 왼쪽 오른쪽 붙이기
  label="title"
  :options="option"
  class="select-size-sm" // select 된 요소 크기 
  multiple // 여러개 선택 가능 
/>
```



## feather-icon 사용법

- [icon 찾기](https://feathericons.com/)

```java
// 전역컴포넌트로 global-components.js 에 선언되어있음.
// 따라서 import 하지 않고 컴포넌트 바로 사용가능 
<feather-icon icon="RefreshCwIcon" /> 
```

