# nodejs를 이용한 geoJson to Json 파일변환

---

>[참고 사이트1](https://blog.naver.com/yh_park02/221838928060)

## 이용한 방법

1. nodejs의 모듈인 `fs (file system)` 을 이용함 
   1. `var fs = require("fs");`

## 파일 읽기,쓰기 방법 종류

1. 동기 vs 비동기

   1. 파일을 읽는 방법에는 크게 두 가지 방법이 있는데, **동기함수와 비동기함수**이다.
   2. 동기함수는 파일을 완전히 읽어들일 때까지 다른 작업을 수행하지 않는다.
   3. 비동기함수는 파일을 완전히 읽기 전까지 다른 작업을 수행하다가 파일이 전부 읽혔을 때 callback()함수를 통해 수행이 끝났음을 확인할 수 있다. 
   4. 동기와 비동기는 예외를 처리하는 방식이 다소 차이가 있다. 

2. 예시

   1. 옵션으로는 보통 "utf8"과 같은 인코딩 방식을 지정해준다.

   2. <동기함수> 

      1. 읽기 : 
         1. `fs.readFileSync(filename, [option]);`
      2. 쓰기 : 
         1. `fs.writeFileSync(filename, data, [option]);`

   3. <비동기함수> 

      1. 읽기 : 
         1. `fs.readFileSync(filename,[option], callback)`
      2. 쓰기 : 
         1. `fs.writeFile(filename, data,[option], callback)`

      ```js
      var fs = require('fs');
      // 1. 동기
      var text = fs.readFileSync('file.txt', 'utf8'); 
      
      // 2. 비동기
      var text = fs.readFile('file.txt', 'utf-8', function(err,text){ 
          console.log(text);
      });
      ```

## 코드

```js
/**
 * 용도 : geojson to json 
 * 내용 : javascript 로 nodejs 를 이용한 json파일 생성
 * 사용방법 : 
 *  1. cmd 를 열어 해당 파일의 폴더 위치로 이동 (파일명 : 'GeojsonToJson.js')
 *  2. 파일경로 설정 -> input과 output의 geojson파일 경로 및 파일명 설정
 *  3. `node GeojsonToJson.js` 를 입력 -> (여기서 GeojsonToJson.js는 코드가 들어있는 현재 파일명임)
 *  4. 변환 완료 
 * 
 * make 원영
 * 
 */


const fs = require('fs');
const path = require('path')

// 파일 경로 설정 down
const inputFilePath = path.join('C:', 'Users', 'user', 'Desktop','3D' ,'facility_point', 'down_facility_point','down_facilityPoint_testPart.geojson');
const outputFilePath = path.join('C:', 'Users', 'user', 'Desktop','3D' ,'facility_point','down_facility_point' ,'down_testPart_convertedToJson.json');

// 파일 경로 설정 up 
// const inputFilePath = path.join('C:', 'Users', 'user', 'Desktop','3D' ,'facility_point', 'up_facility_point','up_facilityPoint_testPart.geojson');
// const outputFilePath = path.join('C:', 'Users', 'user', 'Desktop','3D' ,'facility_point','up_facility_point' ,'up_testPart_convertedToJson.json');


// 파일 읽기
fs.readFile(inputFilePath, 'utf8', (err, data) => {
    if (err) {
        console.error('파일 읽기 오류:', err);
        return;
    }

    // GeoJSON 파싱
    const geojson = JSON.parse(data);

    // 변환된 JSON 생성
    const convertedJson = geojson.features.map(feature => {
        return {
            // 변환하고자 하는 형태에 맞게 변경 
            properties: {
                id: feature.properties.fid,
                kind: feature.properties.kind,
                text: feature.properties.text,
                bound: feature.properties.bound,
                lane_size: feature.properties.lane_size,
                degree: feature.properties.cng도,
            },
            geometry: feature.geometry,
            // geometry: {
            //     type: "MultiPoint",
            //     coordinates: [
            //         feature.geometry.coordinates
            //     ]
            // }
        }
    });

    // 변환된 JSON 문자열로 변환
    const convertedData = JSON.stringify(convertedJson, null, 2);

    // 변환된 JSON 파일 쓰기
    fs.writeFile(outputFilePath, convertedData, 'utf8', (err) => {
        if (err) {
            console.error('파일 쓰기 오류:', err);
            return;
        }
        console.log('변환된 파일이 성공적으로 저장되었습니다:', outputFilePath);
    });
});
```

---

## 내가 사용했던 geojson파일 

```json
{
"type": "FeatureCollection",
"name": "down_facilityPoint_testPart(changeLCS)",
"crs": { "type": "name", "properties": { "name": "urn:ogc:def:crs:OGC:1.3:CRS84" } },
"features": [
{ "type": "Feature", "properties": { "fid": 1, "cng도": 0.004, "kind": "cctv", "text": null, "bound": 0, "lane_size": 5 }, "geometry": { "type": "Point", "coordinates": [ 127.103399775319431, 37.299047613726472 ] } },
{ "type": "Feature", "properties": { "fid": 2, "cng도": 0.0057676539537839999, "kind": "vms", "text": null, "bound": 0, "lane_size": 5 }, "geometry": { "type": "Point", "coordinates": [ 127.103388515449666, 37.300815226371761 ] } },
{ "type": "Feature", "properties": { "fid": 3, "cng도": 7535307907567.0, "kind": "lcs", "text": null, "bound": 0, "lane_size": 5 }, "geometry": { "type": "Point", "coordinates": [ 127.103510242167701, 37.302580338841238 ] } },
]
}

```

## 변환된 json 파일 

```js
[
  {
    "properties": {
      "id": 1,
      "kind": "cctv",
      "text": null,
      "bound": 0,
      "lane_size": 5,
      "degree": 0.004
    },
    "geometry": {
      "type": "Point",
      "coordinates": [
        127.10339977531943,
        37.29904761372647
      ]
    }
  },
  {
    "properties": {
      "id": 2,
      "kind": "vms",
      "text": null,
      "bound": 0,
      "lane_size": 5,
      "degree": 0.005767653953784
    },
    "geometry": {
      "type": "Point",
      "coordinates": [
        127.10338851544967,
        37.30081522637176
      ]
    }
  },
  {
    "properties": {
      "id": 3,
      "kind": "lcs",
      "text": null,
      "bound": 0,
      "lane_size": 5,
      "degree": 7535307907567
    },
    "geometry": {
      "type": "Point",
      "coordinates": [
        127.1035102421677,
        37.30258033884124
      ]
    }
  },
]
```

