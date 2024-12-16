# js Map과 Object 사용 차이점

---

>

## Map과 Object 차이점 

##### **Map을 사용할 때**:

- 키의 타입이 다양해야 할 때 (숫자, 객체, 불리언 등).
- 키의 순서를 유지해야 할 때.
- **빈번한 삽입, 삭제, 검색**이 필요한 경우.

##### **Object를 사용할 때**:

- JSON 형태로 데이터를 다루어야 할 때.
- **간단한 키-값 쌍** 데이터가 필요할 때.

##### 정리 

1. **Map**은 더 **유연하고 기능이 많으며**, 키 타입에 제한이 없다. 특히 키 순서가 중요한 경우와 다양한 타입의 키를 다룰 때 유리하다. 
2. **Object**는 간단하고 직렬화가 필요할 때 유리하며 JSON과 호환이 필요한 경우 적합하다. 

## Map 예시

```js
// Map으로 변환
const facilityMap = new Map([
  ["down", new URL(`/src/assets/3D/geojson/down_facilityPoint_testPart.geojson`, import.meta.url).href],
  ["up", new URL(`/src/assets/3D/geojson/up_facilityPoint_testPart.geojson`, import.meta.url).href],
]);

// Map 값 확인
console.log(facilityMap.get("down")); // 'down'에 해당하는 값 출력
console.log(facilityMap.get("up"));   // 'up'에 해당하는 값 출력
```

## Object 예시 

```js
// Object로 변환
const facilityObject = {
  down: new URL(`/src/assets/3D/geojson/down_facilityPoint_testPart.geojson`, import.meta.url).href],
  up: new URL(`/src/assets/3D/geojson/up_facilityPoint_testPart.geojson`, import.meta.url).href],
};

// Object 값 확인
console.log(facilityObject.down); // 'down'에 해당하는 값 출력
console.log(facilityObject.up);   // 'up'에 해당하는 값 출력
```

