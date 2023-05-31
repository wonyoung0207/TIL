# exceljs 개념정리 

---

## exceljs

### 정의

- Node.js와 브라우저 환경에서 Excel 파일을 생성하고 편집할 수 있는 JavaScript 라이브러리이다. 
  -  프로그래밍 방식으로 Excel 파일을 생성하고, 특정 데이터를 읽거나 쓸 수 있으며, 스타일, 서식, 차트 등 다양한 Excel 기능을 조작할 수 있다.

### 사용법

```javascript
const ExcelJS = require('exceljs'); // module 을 불러와서 변수로 이용 

// 새로운 워크북 생성
const workbook = new ExcelJS.Workbook();

// 새로운 워크시트 추가
const worksheet = workbook.addWorksheet('Sheet 1');

// 데이터 입력
worksheet.getCell('A1').value = 'Hello'; // 엑셀시트 A1에 값을 입력 
worksheet.getCell('B1').value = 'World!';

// 스타일 적용
const cellA1 = worksheet.getCell('A1');
cellA1.font = { bold: true, size: 12 };
cellA1.fill = { type: 'pattern', pattern: 'solid', fgColor: { argb: 'FFFF0000' } };

// 데이터 읽기
const valueA1 = worksheet.getCell('A1').value;
const valueB1 = worksheet.getCell('B1').value;
console.log(valueA1); // 출력: 'Hello'
console.log(valueB1); // 출력: 'World!'

// 특정 셀 가져오기
const cellA1 = worksheet.getCell('A1');
console.log(cellA1.value); // 출력: 'Hello'

// 특정 행 가져오기
const row2 = worksheet.getRow(2);
console.log(row2.getCell('A').value); // 출력: 'Apple'

// Excel 파일로 저장
workbook.xlsx.writeFile('example.xlsx')
  .then(() => {
    console.log('Excel 파일이 생성되었습니다.');
  })
  .catch((error) => {
    console.log('파일 저장 중 오류가 발생했습니다.', error);
  });

```

