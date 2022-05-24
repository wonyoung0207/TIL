# 30일차

------

> Project_AppleSite 를 완성한다. 
>
> KaKAo Map API를 다뤄본다. 

## 1. KaKao 지도 API 이용

   1. [KaKaoMap API 사이트](https://apis.map.kakao.com/)

   2. [API사용방법](https://apis.map.kakao.com/web/guide/)

   3. 방법

      1. 해당 사이트에서 App Key발급

      2. 애플리케이션 추가 

      3. 플랫폼 만들기 -> web 도메인 추가 

         - 플랫폼 : 사용할 서버를 등록해준다. 

         - 등록된 서버에서만 지도 API를 사용할 수있다. 

   4. 코드

      1. ```html
         <!-- API를 사용하기 위해서는 지정된 도메인에서 사용할 수 있는 appkey 를 넣어줘야 한다. -->
         <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fe437edd3e9bc5edc04dc452af1cba1d"></script>
         <meta charset="UTF-8">
         <script>
         
         $(document).ready(function(){
         	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
             mapOption = { 
                 center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                 level: 3 // 지도의 확대 레벨 -> 숫자가 작아지면 확대 
             };
         
         	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
         	
         
         	//++++++++++++++++++++++ 지도에 컨트롤 추가 ++++++++++++++++++++++
         	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
         	var mapTypeControl = new kakao.maps.MapTypeControl();
         	// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
         	// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
         	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
         	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
         	var zoomControl = new kakao.maps.ZoomControl();
         	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
         	
         
         	//++++++++++++++++++++++ 지도에 마커 추가 ++++++++++++++++++++++
         	// 마커가 표시될 위치입니다 -> 기역할 장소에 깃발 꼽는 것 
         	var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 
         	// 마커를 생성합니다
         	var marker = new kakao.maps.Marker({
         	    position: markerPosition
         	});
         	// 마커가 지도 위에 표시되도록 설정합니다
         	marker.setMap(map);
         	
         	
         	//++++++++++++++++++++++ 지도에 마커 인포윈도우 추가 ++++++++++++++++++++++
         	var iwContent = '<div style="padding:5px;">Hello World! <br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
             iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다
         	// 인포윈도우를 생성합니다
         	var infowindow = new kakao.maps.InfoWindow({
         	    position : iwPosition, 
         	    content : iwContent 
         	});
         	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
         	infowindow.open(map, marker); 
         	
         	
         	$('#go1').click(function(){
         	    // 이동할 위도 경도 위치를 생성합니다 
         	    var moveLatLon = new kakao.maps.LatLng(37.5176084,126.905008);
         	    
         	    // 지도 중심을 이동 시킵니다
         	    map.setCenter(moveLatLon);
         	});
         	
         	$('#go2').click(function(){
         	    // 이동할 위도 경도 위치를 생성합니다 
         	    var moveLatLon = new kakao.maps.LatLng(37.514082,126.941687);
         	    
         	    // 지도 중심을 이동 시킵니다
         	    map.setCenter(moveLatLon);
         	});
         });
         
         </script>
         <button id="go1">영등포 타임스퀘어</button>
         <button id="go2">노량진역</button>
         
         <div id="map"></div>
         
         ```

      2. 
