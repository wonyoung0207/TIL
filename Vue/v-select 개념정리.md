# \<v-select>

### 정의

- Vue.js와 Vuetify 라이브러리에서 제공되는 컴포넌트로,  선택 목록을 표시하고 사용자가 값을 선택할 수 있도록 하는 역할을 한다. 

### 옵션

1. `v-model`: 선택한 값을 바인딩할 데이터 속성을 지정
   - `cameraInfo.instHeight`와 양방향 데이터 바인딩한다. 
2. `:options`: 선택할 수 있는 옵션들의 배열을 지정
   - `optionsInstHeight` 데이터 속성으로부터 옵션 목록을 가져온다.
3. `label`: 옵션 객체에서 표시될 값을 지정하는 속성의 이름을 지정
   -  `text`로 설정되어 있으므로 옵션 객체의 `text` 속성 값이 표시된다. 
4. `:reduce`: 옵션 객체에서 실제 값을 추출하기 위한 함수를 지정
   - (tt) => tt.data`로 설정되어 있으므로 옵션 객체의 `data` 속성 값이 사용된다. 
5. `:clearable`: 선택한 값을 지울 수 있는지 여부를 설정
   - false`로 설정되어 있으므로 선택한 값이 지워지지 않는다. 