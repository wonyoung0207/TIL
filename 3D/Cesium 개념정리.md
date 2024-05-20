# Cesium ion 개념정리 

---

>[세슘 시작해보기 예제](https://cesium.com/learn/cesiumjs-learn/cesiumjs-quickstart/)
>
>[세슘 3D Tiles](https://github.com/CesiumGS/3d-tiles)
>
>[세슘js 예제 모음](https://cesium.com/learn/cesiumjs-sandcastle/)
>
>[세슘 ion](https://ion.cesium.com/stories)
>
>

## Cesium 이란

<img src="./images/Cesium이란.avif" width="600">

1. [공식 홈페이지의 Cesium 소개](https://cesium.com/learn/cesium-foundations/)
2. Cesium은 WebGL을 기반으로 한 오픈 소스 플랫폼으로, 브라우저에서 고성능 3D 지리 정보 시각화를 가능하게 한다. 
   1. 가상 지구, 3D 지도, 실시간 데이터 시각화, 공간 분석 등 다양한 **지리 공간 애플리케이션**을 구현할 수 있다. 
3. **크로스 플랫폼** 지원으로 모바일 및 데스크톱 브라우저에서 동작 가능하다. 
4. CesiumJS는 Cesium 이온과 함께 상용 옵션으로 무료 오픈 소스 소프트웨어로 제공된다. 
5. Cesium은 2015년 3D Tiles의 최초 창시자였다. 

## Cesium 좌표계

1. EPSG:4326 - WGS84 
   1. TileSet 의 좌표계는 다르게 적용함. ( 이유는 Tileset 은 EPSG:4979 - WGS 84 이 권장사항이기 때문이다. )
2. **WGS84 (World Geodetic System 1984)**
   - WGS84는 전 세계에서 사용되는 지구의 좌표 시스템으로, 위도(Longitude)와 경도(Latitude)를 사용하여 지구의 표면을 정확하게 표현한다. 
   - CesiumJS에서는 기본적으로 WGS84 좌표계를 사용하여 지구의 위치를 표시하고 지도 상의 위치를 정의한다.
3. **EPSG:4326 (WGS 84)**
   - EPSG:4326은 WGS84 좌표계의 공간 참조 코드로, 위도(Longitude)와 경도(Latitude)를 사용하여 지구의 위치를 나타낸다. 
   - CesiumJS에서도 지구의 위도와 경도를 EPSG:4326 좌표계로 사용하여 지도에 위치를 표시하고 데이터를 처리한다. 
4. ECEF 좌표
   1. CZML 파일 구조 와 연관이 있음. 
   2. 일반적으로 CZML에서 위치 정보는 [x, y, z] 형식으로 주어지는데, 이는 지구 중심을 기준으로 하는 ECEF(Earth-Centered, Earth-Fixed) 좌표계를 사용한다. 
   3. ECEF 좌표계를 위,경도로 변경하기 위해서 Cesium 의 Cartesian3 객체를 이용할 수 있다. 


## CesiumJs 

- [공식 홈페이지의 CesiumJS 소개](https://cesium.com/learn/cesiumjs-fundamentals/)

## Viewer 

1. CesiumJS에서 3D 지도를 렌더링하고 상호작용할 수 있는 주요 객체이다. 

2. 카메라 관련 Viewer

   1. zoomTo()

      ```js
      // 형태 
      viewer.zoomTo(target, offset);
      
      // 예시 
      viewer.zoomTo(vehicleEntity);
      ```

   2. flyTo() 

      ```js
      viewer.camera.flyTo({
          destination: Cesium.Cartesian3.fromDegrees(longitude, latitude, height), // 이동할 목표 지점의 경도, 위도, 높이
          orientation: {
              heading: Cesium.Math.toRadians(0.0), // 카메라의 수평 각도 (동쪽을 기준으로 시계 방향)
              pitch: Cesium.Math.toRadians(-15.0), // 카메라의 수직 각도 (수평을 기준으로 위쪽 또는 아래쪽)
              roll: Cesium.Math.toRadians(roll) // 카메라의 롤 각도 (수평을 기준으로 좌우 기울기)
          },
          duration: duration // 애니메이션 소요 시간 (밀리초 단위)
      });
      ```

## 시간 ( JulianDate )

1. 율리우스 날짜를 나타내는 객체이다. 

2. 율리우스 통상 연도를 사용하여 날짜를 계산하는 방식으로, 특정 시점부터의 일수로 날짜를 표현한다. 

3. 예시

   ```js
   // 현재 시간을 기준으로 JulianDate 생성
   const currentTime = Cesium.JulianDate.now();
   
   const start = Cesium.JulianDate.now(); // 현재 시간을 기준으로 시작
   const totalSeconds = 1; // 1초
   const endTime = Cesium.JulianDate.addSeconds(
       start, // 시작 기준 시간
       totalSeconds, // 추가할 시간 
       new Cesium.JulianDate() // 결과 저장 객체 
   );
   ```

   

## CZML

1. CZML은 CesiumJS에서 사용하는 JSON 기반 포맷으로, 시간에 따라 변하는 3D 지리적 데이터를 표현하고 시각화하는 데 사용된다. 
2. 각 CZML 문서는 시간순으로 이벤트와 엔티티를 정의하며, 위치, 모델, 속성 등 다양한 요소를 포함할 수 있다. 
3. CesiumJS를 통해 CZML 데이터를 로드하여 웹에서 3D 지도나 다양한 시뮬레이션을 구현한다. 

## 3D Tileset

1. [세슘 3DTileset 예제](https://sandcastle.cesium.com/?src=3D%20Tiles%20Adjust%20Height.html&label=3D%20Tiles)

2. 대규모의 이기종 3D 지리공간 데이터 세트를 스트리밍하기 위한 [개방형 사양](https://github.com/CesiumGS/3d-tiles) 이다. 

3. 3D 타일을 사용하면 HLOD(계층적 세부 수준)에 따라 로드되는 데이터세트 부분을 제한하여 사용자가 대규모 3D 데이터세트를 스트리밍할 수 있다. 

4. 3D 타일의 주요 초점은 스트리밍 성능이다. 

5. CesiumJs 의 3DTileset 이용 건물, 도로 표출 

   1. 3DTileset의 모든 정보를 가지고 있는것이 tileset.json이다. 
   2. 따라서 tileset.json 을 cesium.Cesium3DTilese() 과 연결함 
   3. 건물 및 도로 Tileset 을 불러오지 못하는 문제 발생 
      1. 불러오지 못하는 이유는 tileset.json 에 설정되어있는 각각의 b3dm 파일들의 위치가 맞지 않아서였다 .
      2. b3dm 을 인식할 수 있도록 tileset.json에서 `"content":{"uri":"tiles/1.b3dm"}, ` 를 수정했다. 

6. tileset.json 내용 

   ```js
   {"boundingVolume":{"box":[54.645725461188704, -39.963195816613734, -125.22299620858394, 532.6513901297003, 0.0, 
   0.0, 0.0, 476.68711100146174, 0.0, 0.0, 0.0, 703.9743218023796]}, 
   "geometricError":1, 
   "refine":"REPLACE", 
   "content":{"uri":"tiles/2.b3dm"}, 
   "transform":[1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -3058690.224, 4046848.742, 3853315.122, 1.0]
   }, 
       
   {"boundingVolume":{"box":[-169.42882675933652, 222.98127742065117, -332.64401790942065, 575.5099140286911, 0.0, 
   0.0, 0.0, 637.1756564960815, 0.0, 0.0, 0.0, 972.7312093048822]}, 
   "geometricError":1, 
   "refine":"REPLACE", 
   "content":{"uri":"tiles/3.b3dm"}, 
   "transform":[1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -3059044.734, 4046609.843,
   3853284.71, 1.0]
   }, 
   ```


### Cesium3DTileset 과 Cesium3DTile

1. 요약
   1. `Cesium3DTileset`은 여러 개의 `Cesium3DTile`을 관리하고 이를 한 번에 로드하고 제어하는 데 사용되는 상위 수준의 클래스이다. 
   2. 일반적으로 작업을 수행할 때는 `Cesium3DTileset`을 사용하여 전체적인 3D 타일셋을 관리하고 로드하고, 필요에 따라 개별 `Cesium3DTile` 객체를 직접 조작하여 세부적인 설정을 수행한다. 
2. **Cesium3DTileset**:
   - `Cesium3DTileset`은 하나 이상의 3D 타일을 나타내는 컨테이너 역할을 합니다.
   - 일반적으로 복수의 3D 타일로 구성된 큰 데이터 세트를 로드하고 표시할 때 사용됩니다.
   - 3D 타일셋에는 여러 개의 `Cesium3DTile` 객체가 포함될 수 있습니다.
   - 전체적인 3D 타일셋의 위치, 회전, 크기 등을 제어할 수 있는 메소드와 속성을 제공합니다.
3. **Cesium3DTile**:
   - `Cesium3DTile`은 개별적인 3D 타일을 나타내는 객체입니다.
   - 일반적으로 하나의 건물, 지형 조각 또는 다른 개별 요소를 나타냅니다.
   - `Cesium3DTileset`에 속하는 각 타일은 `Cesium3DTile` 객체로 표현됩니다.
   - 개별 타일의 위치, 회전, 크기, 스타일 등을 제어할 수 있는 메소드와 속성을 제공합니다.

### 차량 움직임 표출 

> 1. [세슘 차량 움직임 표출 예제 ](https://sandcastle.cesium.com/index.html?src=Time%20Dynamic%20Wheels.html)
> 2. [GPX 이용 움직이는 예제](https://sandcastle.cesium.com/?src=GPX.html&label=DataSources)
> 3. [차량 이동경로 GPS따는곳](https://maps.openrouteservice.org/#/place/@8.76709,49.510944,7)

1. 시간에 따른 차량 움직임 부드럽게 표출 
   1. Cesium.SampledPositionProperty(); 를 이용
2. 차량 이동시 필요한 정보 
   1. 여러 position 이라는 값이 합쳐져 차량이 이동하게 된다. 
   2. Position은 구간별 이동이라고 생각하면 된다. 
      1. 즉, 특정 point에서 얼마의 시간동안 이동할것인지 결정한다. 
   3. point
      1. 시작 위경도
   4. 시간 
      1. 시작 위경도에서의 출발시간 (특정 포인트에서 출발한 시간)
      2. 끝 위경도에 도달한 시간 (포인트 끝에 도달한 시간)
   5. 최종적으로, 구간별 **Position** 이 합쳐져 차량이동이 만들어진다. 
3. 실시간 차량 표출 
   1. 데이터 들어왔을때 데이터에 대한 차량 Entity 있는지 체크 
      1. Entity 없는경우 생성 
      2. Entity 있는경우 무조건 position 있는거임 . -> 이전 position 값 가져오기 
   2. 차량 Entity의 position이 있는지 체크
      1. Position 없는경우
         1. (디폴트 경위도 + (들어온 차량 데이터 시간 - 2초))
         2. 엔티티 생성시 들어온 차량 데이터 ( 발생 시간 + 경위도 ) 
      2. Position 있는경우
         1. 이전 데이터 시간과 경위도 
         2. 현재 들어온 시간과 차량 경위도 
   3. 해당 엔티티 찾아서 position에 추가 
      1. position.addSample(시간, location)
4. QGIS 이용 차량 이동 경로 데이터 추출
   1. `qgis -> 공간 처리 박스 툴 -> random 검색 -> 선을따라 랜덤한 점 생성` 



## [사용가능 파일 타입 ](https://cesium.com/learn/3d-tiling/tiler-data-formats/)

1. [Photogrammetry or LiDAR-derived mesh](https://cesium.com/learn/3d-tiling/ion-tile-photogrammetry/)
2. [BIM, CAD, or other 3D model](https://cesium.com/learn/3d-tiling/ion-tile-3d-models/)
3. [Point Clouds](https://cesium.com/learn/3d-tiling/ion-tile-point-clouds/)
4. [3D Buildings](https://cesium.com/learn/3d-tiling/ion-tile-3d-buildings/)
5. [Terrain](https://cesium.com/learn/3d-tiling/ion-tile-terrain/)
6. [Satellite or Drone Imagery](https://cesium.com/learn/3d-tiling/ion-tile-imagery/)

