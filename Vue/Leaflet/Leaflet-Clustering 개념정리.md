# Leaflet-clustering 개념정리

---

>[깃허브 - leaflet marker Cluster](https://github.com/Leaflet/Leaflet.markercluster#using-the-plugin)
>
>[Leaflet 공식 Docs ](https://leafletjs.com/reference.html)
>
>[Leaflet Cluster 예제 사이트](https://leaflet.github.io/Leaflet.markercluster/example/marker-clustering-realworld.10000.html)

##  leaflet.markercluster 동작 방식 (원리)

> **줌 레벨 + 거리 기반으로 마커를 그룹핑**해서 “여러 마커 → 하나의 클러스터 마커”로 표현

### 동작 흐름

1. 마커들을 **grid(격자)** 로 나눔
2. 같은 grid 안에 있으면 하나의 클러스터로 묶음
3. 줌 인 → grid가 작아짐 → 클러스터 분해
4. 줌 아웃 → grid가 커짐 → 다시 병합

**중요**

- 서버 아님  (전부 클라이언트)
- 실제 마커는 사라지지 않고 **표현만 묶임**
- DOM 마커 수를 **극적으로 줄여서 성능 확보**

| 상황                | 추천                      |
| ------------------- | ------------------------- |
| 마커 300~3000개     | ✅ 필수                    |
| 마커 1만 개 이상    | ⚠️ Canvas / 서버 타일 고민 |
| 마커 클릭/팝업 많음 | ✅ 매우 적합               |
| 실시간 추가/삭제    | ✅ 지원                    |

------

## 사용 방법 (Vue2 기준)

##### ① 라이브러리 설치

```js
npm install leaflet.markercluster
import 'leaflet.markercluster'
import 'leaflet.markercluster/dist/MarkerCluster.css'
import 'leaflet.markercluster/dist/MarkerCluster.Default.css'
```

------

##### ② ClusterGroup 생성

```js
mounted() {
  this._map = L.map(this.$refs.map).setView([37.5, 127], 13)

  this._cluster = L.markerClusterGroup({
    showCoverageOnHover: false,
    maxClusterRadius: 80
  })

  this._map.addLayer(this._cluster)
}
```

- **ClusterGroup = LayerGroup의 확장판**

------

##### ③ 마커 추가

```js
addMarkers(data) {
  this._cluster.clearLayers()

  data.forEach(item => {
    const marker = L.marker([item.lat, item.lng])
      .bindPopup(item.name)

    this._cluster.addLayer(marker)
  })
}
```

- `marker.addTo(map)` 하지 말고,  **반드시 cluster에 addLayer**

------

## 옵션

```js
L.markerClusterGroup({
  maxClusterRadius: 80,        // 클러스터 반경(px)
  disableClusteringAtZoom: 17,// 특정 줌부터 개별 마커
  spiderfyOnMaxZoom: true,    // 겹칠 때 거미줄 형태
  showCoverageOnHover: false, // 영역 표시 제거
  zoomToBoundsOnClick: true   // 클릭 시 확대
})
```

### 옵션 설명 핵심

- `disableClusteringAtZoom`
  - **“이 줌 이상에서는 클러스터 해제”**
- `spiderfyOnMaxZoom`
  - 동일 좌표 마커 필수 옵션

------

## 이벤트 처리 

##### 클러스터 클릭

```js
this._cluster.on('clusterclick', e => {
  console.log(e.layer.getAllChildMarkers())
})
```

##### 개별 마커 클릭

```js
marker.on('click', () => {
  this.selectedId = item.id
})
```

- Vue 상태 변경은 **marker 이벤트 안에서만**

------

## 성능 최적화 

##### 1. 마커는 한 번에 추가하라

❌

```js
data.forEach(() => cluster.addLayer(marker))
```

⭕ (대량일 때)

```js
const markers = data.map(d => L.marker(...))
cluster.addLayers(markers)
```

#####  2. Vue watcher로 cluster 조작 하지 않기

- 데이터 바뀔 때만 `clear → add`
- 지도 이동 이벤트와 섞지 말 것

### 3. HTML icon 남용 금지

```js
L.divIcon({ html: '...' }) // 많으면 렉걸림
```

- 클러스터 많으면 **SVG / 기본 아이콘 권장**

------

## 안정적 구조 

```
Map
 └─ MarkerClusterLayer
     ├─ clear()
     ├─ setData(data)
     └─ onMarkerClick()
```

------

## 한 줄 요약

> **markercluster는 “마커를 줄이는 게 아니라 그려야 할 DOM 수를 줄이는 기술”이다.**