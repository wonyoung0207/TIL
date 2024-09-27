# bootstap-vue의 b-table 정리

---

>

## b-table

1. 필드에 값을 넣어주면 자동으로 row 로 표시
2. 표시할 필드의 formmat을 지정할 수 있음 

### 사용예시

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

### formatter 적용

1. b-table의 옵션 중 **tableColumns** 옵션을 이용해 변경 할 수 있다.

```js
const tableColumns = [
    { key: "numb", label: "번호" },
    { key: "startDt", label: "시작 시간", formatter: (value) => dayjs(value).format('YYYY.MM.DD HH:mm:ss') },
    { key: "endDt", label: "종료 시간", formatter: (value) => dayjs(value).format('YYYY.MM.DD HH:mm:ss') },
    { key: "Id", label: "ID" },
    { key: "Name", label: "이름" },
    { key: "cWork", label: "작업" },
    { key: "detail", label: "상세", thStyle: { width: "10%" } },
];
```



