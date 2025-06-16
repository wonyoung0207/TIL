## Light 종류

---

>[Threejs Light Doc](https://threejs.org/docs/#api/en/lights/SpotLight)

## ambientLight

1. 모든 Object 에다가 빛을 비춘다. 
2. 하지만 그림자가 제공되지 않는다. 

## DirectionalLight

1. 햇빛같은 빛이다. 

   - 빛의 새기와 색상 지정 가능 

2. 가장 많이 사용할듯? 

   ```js
   // DirectionalLight( color : Integer, intensity : Float)
   const directionalLight = new THREE.DirectionalLight( 0xffffff, 0.5 );
   scene.add( directionalLight );
   ```

## HemisphereLight

1. 하늘색상과 지상 색을 나눠 설정 가능하다. 

2. 오브젝트의 색상은 하늘색, 지상색을 이용해 표현된다. 

   ```js
   // HemisphereLight( skyColor : Integer, groundColor : Integer, intensity : Float )
   const light = new THREE.HemisphereLight( 0xffffbb, 0x080820, 1 );
   scene.add( light );
   ```

## PointLight

1. 하나의 지점에 빛을 표시할 때 사용 

2. 즉, 전등, 전구 같은것들 사용할 때 사용함 

   ```js
   // PointLight( color : Integer, intensity : Float, distance : Number, decay : Float )
   // intensity : 빛의 세기 (처음에 1로 했다가 안보여서 없는줄... 50 정도 해야 전구같음 )
   // distanc : 빛의 최대거리, 디폴트 : 0(No Limit)
   // decay : 감쇠율, 거리에 따른 세기 조정 , 디폴트 :  2
   
   const light = new THREE.PointLight( 0xff0000, 1, 100 );
   light.position.set( 50, 50, 50 );
   scene.add( light );
   ```

## SpotLight

1. 무대 조명같은 빛 

2. **SpotLight는 방향성 조명이기 때문에** `target` 속성이 필요하다. 

   - 어떤 오브젝트를 조명할건지 오브젝트의 위치를 지정해줄 필요가 있음 

   ```js
   // 빛 기준점 
   const target = new THREE.Object3D()
   target.position.set(0, 0, 0)
   scene.add(target)
   
   // SpotLight( color : Integer, intensity : Float, distance : Float, angle : Radians, penumbra : Float, decay : Float )
   // spotLight.angle = Math.PI / 5       // 30도 퍼짐 각도
   // spotLight.penumbra = 0.3            // 가장자리 부드러움
   // spotLight.distance = 0             // 최대 거리 제한
   // spotLight.decay = 1                 // 빛의 감쇠 (거리 멀어질수록 어두워짐)
   spotLight = new THREE.SpotLight(0xffffff, 100, 0, Math.PI / 4, 0, 0);
   spotLight.position.set( 10, 10, 3 );
   spotLight.castShadow = true; // default false
   spotLight.angle = Math.PI / 5       // 30도 퍼짐 각도
   spotLight.penumbra = 0.3            // 가장자리 부드러움
   spotLight.distance = 10             // 최대 거리 제한
   spotLight.decay = 1                 // 빛의 감쇠 (거리 멀어질수록 어두워짐)
   spotLight.target = target
   spotHelper = new THREE.SpotLightHelper( spotLight );
   
   scene.add( spotHelper );
   scene.add( spotLight );
   ```

   