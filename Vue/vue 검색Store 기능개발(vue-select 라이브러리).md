# vue 검색Store 기능개발(vue-select 라이브러리)

---

>[vue-select 라이브러리 - NPM](https://www.npmjs.com/package/vue-select)

## vue-select 

1. Vue.js를 위한 **유연하고 사용하기 쉬운 셀렉트 드롭다운 컴포넌트**를 제공하는 오픈소스 라이브러리
2. 검색 기능, 다중 선택, 비동기 데이터 로딩 등을 포함한 고급 기능을 제공한다. 

## 핵심기능

1. **vuexy Store 사용**
   1. 데이터 전역 관리 
2. **vue-select 라이브러리 사용**
   1. 검색시 미리보기 데이터 제공 
   2. 검색결과 없을시 텍스트 제공 
   3. options에서 선택한 데이터는 Object 형태로 v-model과 연결
   4. vuetify 에 있는 v-select 와는 아예 다르기 때문에 주의 필요 
3. bootstrap-vue 의 Grid 사용
   1. 화면 동적 변환 제공 
4. CSS 
   1. Bootstrap 의 SM 스타일 적용
   2. vue-select 는 vuexy Template와는 Style이 다르기 때문에 수동으로 맞춰줘야 한다. 

## 이용방법

1. select 박스를 클릭한다. 
2. 찾고싶은 도로명을 입력한다. 
   1. store에 있는 데이터들중 자동으로 검색되어 표출된다.  
3. 도로를 선택한다. 
   1. 선택한 도로정보가 Object 형태로 v-model 에 입력된다. 
4. 검색을 누르면 도로 타일이 추가된다. 

## 결과화면 

<img src="./images/검색Store1.png" width="300"><img src="../../../../%EA%B2%80%EC%83%89Store2.png" width="300"><img src="./images/검색Store3.png" width="300">

## 코드

```vue
<b-card-title class="mb-1">
  <b-row align-v="center">
    <b-col cols="2" class="pr-0">
      <h5 class="m-0">도로명</h5>
    </b-col>
    <b-col cols="10" class="pr-0">
      <b-row>
        <b-col cols="9" class="pr-0 text-right">
          <b-form-group class="input-group-merge m-0" variant="outline-primary">
            <v-select
              v-model="selectedRoadName"
              :options="storeTiles"
              label="text"
              placeholder="도로명을 입력해 주세요."
              clearable
              @input="changeRoadLevel"
              class="small-select"
            >
              <template #no-options>
                <div class="no-options-message">
                  검색 결과가 없습니다.
                </div>
              </template>
            </v-select>
          </b-form-group>
        </b-col>
        <b-col cols="3" class="pl-0">
          <b-button size="sm" variant="primary" @click="doSearchTile">검색</b-button>
        </b-col>
      </b-row>
    </b-col>
  </b-row>
</b-card-title>

<script>
import vSelect from 'vue-select'

setup(props, context) {
    // 전체 도로 타일 Store
    const storeTiles = computed(() => {
      return store.state.tile.DBTileArr
    });

    // 타일 스토어 저장 
     store.commit('tile/setDBTileArr', tiles);         
}
</script>

<style scope > /* 지역 css 설정  */
.small-select{
  height: 2.142rem;
  font-size: 0.857rem;
}
</style>

<style lang="scss"> /* vue-select 라이브러리 때문에 전역 css에 설정 */
.vs__selected-options {
  white-space: nowrap ;
  overflow: hidden ;
  display: flex; // flex container 부모 
}

.vs__selected-options span {
  flex: 1; // vs_search 제외한 나머지 공간 차지 
  white-space: nowrap ;
  overflow: hidden ;
  text-overflow: ellipsis ;
  display: block ;
}

.vs__search {
  flex: 0 ; // 요소 콘텐츠 크기만큼 공간 차지
}
</style>
```

```js
// vuexy Store - index.js
export default {
    namespaced: true,
    state: {
        DBTileArr: [],
    },
    getters: {
        getDBTileArr(state) {
            return state.DBTileArr
        },
    },
    mutations: {
        setDBTileArr(state, val) {
            state.DBTileArr = val
        },
    },
    actions: {

    }
}
```



## 문제해결

##### 문제점

1. 문제점 1
   1. 선택한 내용의 Text가 selectBox 넘어가면 글자가 줄바꿈 되서 Selectbox 높이 증가 되는문제발생
2. 문제점 2
   1. 클래스 적용해도 적용 안되는 문제 발생 
3. 문제점 3
   1. 위의 내용 해결 후에도 vs__search 로 인해 줄바꿈 되는 문제 발생 

##### 1번 해결방법

1. CSS 이용해 `text-overflow:ellipsis` 로 해결 

   ```html
   <style lang="scss" scoped>
   .vs__selected-options {
     white-space: nowrap ;
     overflow: hidden ;
   }
   
   .vs__selected-options span {
     white-space: nowrap ;
     overflow: hidden ;
     text-overflow: ellipsis ;
     display: block ;
   }
   </stlye>
   ```

##### 2번 해결방법

1. 라이브러리 사용으로 인해 CSS의 scope 적용 안되는 문제임

   1. 언제 전역 스타일을 고려해야 할까?
      - 외부 라이브러리나 플러그인을 사용하는 경우.
      - 스타일이 자식 컴포넌트 또는 외부 DOM 요소에 적용되어야 하는 경우.
      - 스타일이 재사용 가능하거나 전역적으로 적용되어야 하는 경우.

2. 전역 설정으로 해결 

   ```css
   <style lang="scss">
   .vs__selected-options {
     white-space: nowrap ;
     overflow: hidden ;
   }
   
   .vs__selected-options span {
     white-space: nowrap ;
     overflow: hidden ;
     text-overflow: ellipsis ;
     display: block ;
   }
   </stlye>
   ```

##### 3번 해결방법

1. DevTool을 보니 `vs__selected-options` 밑에는 `vs__selected`와 `vs__search` 가 있었다.

   1. 따라서 `vs__selected` 의 span 내용만 `overflow:hidden` 으로 한다고 해도 `vs__search` 로 인해 줄바꿈이 되고있었다. 

2. `display : flex` 를 이용해 `vs__selected`와 `vs__search` 의 영역을 동적으로 설정해주었다. 

   ```html
   <style>
   .vs__selected-options {
     white-space: nowrap ;
     overflow: hidden ;
     display: flex; // flex container 부모 
     // text-overflow: ellipsis ;
     // max-width: 100% ;
   }
   
   .vs__selected-options span {
     flex: 1; // vs_search 제외한 나머지 공간 차지 
     white-space: nowrap ;
     overflow: hidden ;
     text-overflow: ellipsis ;
     display: block ;
     // max-width: 75%;
   }
   
   .vs__search {
     flex: 0 ; // 요소 콘텐츠 크기만큼 공간 차지
     // flex: 0은 요소가 가용 공간을 확장하거나 축소하지 않도록 하지만, 그 크기는 콘텐츠의 크기나 지정된 width, height에 의존하도록 함
   }
   </style>
   ```

   
