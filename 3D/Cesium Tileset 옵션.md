```js
/**
 * 지도에 타일셋 붙이기 - 간단한 옵션을 사용한 예
 * 방향 : 타일셋 등록 값 사용
 * 스케일 : 타일셋 등록 값 사용
 * 좌표 : 타일셋 등록 값 사용
 * 고도 : 타일셋 등록 값 사용
 */
function loadTileset() {
    var tileset = viewer.scene.primitives.add(new Cesium.Cesium3DTileset({
        url: '/data/tileset.json'
    }));
    tileset.readyPromise.then(function(tileset) {
        viewer.zoomTo(tileset, new Cesium.HeadingPitchRange(0.5, -0.2, tileset.boundingSphere.radius * 4.0));
    })
    .otherwise(function(error) {
        console.error('tileset 읽기 오류', error);
    });
    tileset.initialTilesLoaded.addEventListener(function() {
        console.log('초기 타일(tileset)이 로드됨');
    });
    tileset.allTilesLoaded.addEventListener(function() {
        console.log('모든 타일이 로드됨');
    });
}

/**
 * 지도에 타일셋 붙이기 - 많은 옵션을 사용한 예
 * 방향 : 타일셋 등록 값 사용
 * 스케일 : 타일셋 등록 값 사용
 * 좌표 : 타일셋 등록 값 사용
 * 고도 : 타일셋 등록 값 사용
 */
function loadTileset() {
    var tileset = viewer.scene.primitives.add(new Cesium.Cesium3DTileset({
        url: '/data/tileset.json',
        shadows : Cesium.ShadowMode.ENABLED, //타일 표면에 그림자를 드리울지 여부. 로딩시 ENABLED가 아니면 속성 수정반영이 잘 안되었음
        maximumScreenSpaceError : 1, //Default 16. 세분화 수준 향상 값 : 작을수록 디테일함
        maximumMemoryUsage : 2048, //Default 512. 개발PC기준 1024 설정시 더 불안정 했음
        cullWithChildrenBounds : true, //Default true. 최적화 옵션. 자식을 묶는 볼륨을 결합하여 타일을 제거할지 여부.
        baseScreenSpaceError : 1024, //Default 1024. skipLevelOfDetail이 true 일 때, 디테일 수준을 건너 뛰기 전에 도달해야하는 화면 공간 오류
        skipScreenSpaceErrorFactor : 16,
        skipLevelOfDetail : true,
        skipLevels : 1, //Default 1. skipLevelOfDetail이 true 인 경우 타일을로드 할 때 건너 뛸 최소 레벨 수를 정의하는 상수. 0이면 아무 레벨도 건너 뜀. 로드 할 타일을 결정하기 위해 skipScreenSpaceErrorFactor와 함께 사용.
        immediatelyLoadDesiredLevelOfDetail : false, //Default false. skipLevelOfDetail이 true이면 최대 화면 공간 오류를 충족시키는 타일 만 다운로드. 건너 뛰는 요인은 무시되고 원하는 타일 만로드.
        loadSiblings : false, //Default false. skipLevelOfDetail이 true 일 때 보이는 타일의 형제가 순회 중에 항상 다운로드되는지 여부를 결정
    }));
    tileset.readyPromise.then(function(ts) {
        var boundingSphere = ts.boundingSphere;
        var cartographic = Cesium.Cartographic.fromCartesian(boundingSphere.center);
        console.log(cartographic.longitude, cartographic.latitude, cartographic.height);
        var surface = Cesium.Cartesian3.fromRadians(cartographic.longitude, cartographic.latitude, 0.0);
        var offset = Cesium.Cartesian3.fromRadians(cartographic.longitude, cartographic.latitude, cartographic.height);
//      var offset = Cesium.Cartesian3.fromRadians(cartographic.longitude, cartographic.latitude, 0);
        var translation = Cesium.Cartesian3.subtract(offset, surface, new Cesium.Cartesian3());
        ts.modelMatrix = Cesium.Matrix4.fromTranslation(translation);
        viewer.zoomTo(tileset, new Cesium.HeadingPitchRange(0.0, -0.5, boundingSphere.radius * 2.0));
    })
    .otherwise(function(error) {
        console.error('tileset 읽기 오류', error);
    });
    tileset.initialTilesLoaded.addEventListener(function() {
        console.log('초기 타일(tileset)이 로드됨');
    });
    tileset.allTilesLoaded.addEventListener(function() {
        console.log('모든 타일이 로드됨');
    });
}

/**
 * 지도에 타일셋 붙이기
 * 방향 : 타일셋 등록 값 사용
 * 스케일 : 타일셋 등록 값 사용
 * 좌표 : 타일셋 등록 값 사용
 * 고도 : 직접 지정
 */
function loadTileset() {
    var tileset = viewer.scene.primitives.add(new Cesium.Cesium3DTileset({
        url: '/data/tileset.json',
        shadows : Cesium.ShadowMode.ENABLED, //타일 표면에 그림자를 드리울지 여부. 로딩시 ENABLED가 아니면 속성 수정반영이 잘 안되었음
        maximumScreenSpaceError : 1, //Default 16. 세분화 수준 향상 값 : 작을수록 디테일함
        maximumMemoryUsage : 2048, //Default 512. 개발PC기준 1024 설정시 더 불안정 했음
        cullWithChildrenBounds : true, //Default true. 최적화 옵션. 자식을 묶는 볼륨을 결합하여 타일을 제거할지 여부.
//      skipLevelOfDetail : false, //Default true. 최적화 옵션. 순회 중에 디테일 스킵의 레벨을 적용할지 어떨지를 판정
        baseScreenSpaceError : 1024, //Default 1024. skipLevelOfDetail이 true 일 때, 디테일 수준을 건너 뛰기 전에 도달해야하는 화면 공간 오류
//      skipScreenSpaceErrorFactor : 1, //Default 16. skipLevelOfDetail이 true 인 경우 건너 뛸 최소 화면 공간 오류를 정의하는 배율. 로드 할 타일을 결정하기 위해 skipLevels와 함께 사용.
        skipLevels : 1, //Default 1. skipLevelOfDetail이 true 인 경우 타일을로드 할 때 건너 뛸 최소 레벨 수를 정의하는 상수. 0이면 아무 레벨도 건너 뜀. 로드 할 타일을 결정하기 위해 skipScreenSpaceErrorFactor와 함께 사용.
        immediatelyLoadDesiredLevelOfDetail : false, //Default false. skipLevelOfDetail이 true이면 최대 화면 공간 오류를 충족시키는 타일 만 다운로드. 건너 뛰는 요인은 무시되고 원하는 타일 만로드.
        loadSiblings : false, //Default false. skipLevelOfDetail이 true 일 때 보이는 타일의 형제가 순회 중에 항상 다운로드되는지 여부를 결정
    }));
    tileset.readyPromise.then(function(ts) {
        var heightOffset = 0; //타일 고도 - 28
        var boundingSphere = ts.boundingSphere;
        var cartographic = Cesium.Cartographic.fromCartesian(boundingSphere.center);
        console.log(cartographic.longitude, cartographic.latitude);
        var surface = Cesium.Cartesian3.fromRadians(cartographic.longitude, cartographic.latitude, 0.0);
        var offset = Cesium.Cartesian3.fromRadians(cartographic.longitude, cartographic.latitude, heightOffset);
        var translation = Cesium.Cartesian3.subtract(offset, surface, new Cesium.Cartesian3());
        ts.modelMatrix = Cesium.Matrix4.fromTranslation(translation);
        viewer.zoomTo(tileset, new Cesium.HeadingPitchRange(0.0, -0.5, boundingSphere.radius * 2.0));
    })
    .otherwise(function(error) {
        console.error('tileset 읽기 오류', error);
    });
    tileset.initialTilesLoaded.addEventListener(function() {
        console.log('초기 타일(tileset)이 로드됨');
    });
    tileset.allTilesLoaded.addEventListener(function() {
        console.log('모든 타일이 로드됨');
    });
}

/**
 * 지도에 타일셋 붙이기
 * 방향 : 직접 지정
 * 스케일 : 타일셋 등록 값 사용
 * 좌표 : 직접 지정
 * 고도 : 직접 지정
 */
function loadTileset() {
    var tileset = viewer.scene.primitives.add(new Cesium.Cesium3DTileset({
        url: '/data/tileset.json'
    }));
    tileset.readyPromise.then(function(ts) {
        //tileset 배치
        var position = Cesium.Cartesian3.fromDegrees(126.5187319888, 33.5117640085, 39.724);
        var hpr = new Cesium.HeadingPitchRoll(Cesium.Math.toRadians(-90), Cesium.Math.toRadians(0), Cesium.Math.toRadians(-90)); //heading/pitch/roll : 타일셋이 지면에 바로 놓이도록 방향을 돌림
        ts._root.transform = Cesium.Matrix4.IDENTITY;
//      ts.modelMatrix = Cesium.Transforms.headingPitchRollToFixedFrame(position, new Cesium.HeadingPitchRoll());
        ts.modelMatrix = Cesium.Transforms.headingPitchRollToFixedFrame(position, hpr); //타일의 방향을 지정함

//      viewer.zoomTo(ts, new Cesium.HeadingPitchRange(-90, -0.5, 30)); //타일셋 위치로 이동함
    })
    .otherwise(function(error) {
        console.error('tileset 읽기 오류', error);
    });
    tileset.initialTilesLoaded.addEventListener(function() {
        console.log('초기 타일(tileset)이 로드됨');
    });
    tileset.allTilesLoaded.addEventListener(function() {
        console.log('모든 타일이 로드됨');
    });

    viewer.zoomTo(tileset, new Cesium.HeadingPitchRange(-90, -0.5, 30)); //타일셋 위치로 이동함
}

/**
 * 지도에 타일셋 붙이기
 * 방향 : 직접 지정
 * 스케일 : 직접 지정
 * 좌표 : 직접 지정
 * 고도 : 직접 지정
 */
function loadTileset() {
    //조회할 3d tileset을 불러옴
    var tileset = viewer.scene.primitives.add(new Cesium.Cesium3DTileset({
        url: '/data/tileset.json',
        show: false,
        preloadWhenHidden: true, //show가 true인 것처럼 타일을 로드함. 이거 없이 타일 생성시 나중에 show:true 작동 안됨
        skipLevelOfDetail: true, //최적화 옵션. 순회 중에 세부 수준 건너 뛰기를 적용해야하는지 여부
        shadows: Cesium.ShadowMode.DISABLED, //Default : ENABLED.
//      maximumMemoryUsage : 768 //Default 512. 개발PC기준 1024 설정시 더 불안정 했음
//      foveatedTimeDelay: 1,
//      progressiveResolutionHeightFraction: 0.5,
        maximumScreenSpaceError : isMobile() ? 8 : 1, //기본값 16. 세분화 수준 향상 값 : 작을수록 디테일함
//      maximumScreenSpaceError : isMobile() ? 8 : 1, //낮은 메모리 모바일 장치에 대한 임시 해결 방법 - 최대 오류를 8로 늘림.
        maximumNumberOfLoadedTiles : isMobile() ? 10 : 1000 //낮은 메모리 모바일 장치에 대한 임시 해결 방법 - 타일 캐시를 감소.
    }));
    tileset.readyPromise.then(function(ts) {
        mapContents.zoomScale = ts.boundingSphere.radius;
        var position = Cesium.Cartesian3.fromDegrees(coordinatex, coordinatey, coordinatez + height);
        var hpr = new Cesium.HeadingPitchRoll(Cesium.Math.toRadians(heading), Cesium.Math.toRadians(pitch), Cesium.Math.toRadians(roll)); //heading/pitch/roll
        var orientation = Cesium.Transforms.headingPitchRollQuaternion(position, hpr);
        mapContents.orientation = orientation;
        var matrix = Cesium.Transforms.headingPitchRollToFixedFrame(position, hpr); //타일의 방향을 지정함
//      ts.modelMatrix = Cesium.Transforms.headingPitchRollToFixedFrame(position, hpr); //타일의 방향을 지정함
        ts.modelMatrix = Cesium.Matrix4.multiplyByUniformScale(matrix, scale, matrix); //타일셋에 스케일 적용
        ts._root.transform = Cesium.Matrix4.IDENTITY; //이게 있어야 지면에 붙임

        ts.colorBlendMode = Cesium.Cesium3DTileColorBlendMode.REPLACE;
    })
    .otherwise(function (error) {
        console.error('tileset 읽기 오류', error);
    });
    tileset.initialTilesLoaded.addEventListener(function() {
        console.log('초기 타일(tileset)이 로드됨');
    });
    tileset.allTilesLoaded.addEventListener(function() {
        console.log('모든 타일이 로드됨');
    });
}
```

