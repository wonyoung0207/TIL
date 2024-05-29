# Photogrammetry 개념정리 

---

>[참고 사이트1](https://merror.tistory.com/22)

## 정의 

- 사진으로부터 공간적 정보를 추출하여 피사체를 모델링 하는 기술이다. 
- 즉, 여러 장의 사진을 찍어서 그 사진들의 공통된 지점을 찾아 3차원 모델을 만들어 내는 것

```js
// Photogrammetry로 생성된 3D 타일셋 로드
var tileset = new Cesium.Cesium3DTileset({
    url: 'https://assets.cesium.com/4309/tileset.json'
});
viewer.scene.primitives.add(tileset);

// 타일셋 스타일 설정 (예: 색상, 높이 등)
tileset.style = new Cesium.Cesium3DTileStyle({
    color: "color('white')"
    // 필요한 스타일 속성 추가
});
```

