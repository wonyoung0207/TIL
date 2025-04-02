### 문제점

1. 클라이언트 의 PeerConnection Close 누락 



### 이슈사항

1. ws과 PeerConnection 의 라이프 사이클이 다름
   1. ws close 시점 : pc 로 streaming 시작할때
   2. pc close 시점 : 클라이언트가 close 할때
2. 돌발팝업과 CCTV 가 사용하는 mediaserver.js 코드가 다름 
   1. 돌발팝업: public 경로의 미디어서버 js
   2. CCTV : components 경로의 미디어서버 js
3. 비디오 play() 시점 2번호출
   1. 클라이언트에서 play
   2. pc 커넥하고 streaming 연결되면 play 
   3. state와 시간에 따라 Stop 호출될 여지 있음 
4. terminate() 
   1. 정상동작 하는 경우 : pc 생성후 terminate() → ws 죽음 & pc 죽음 
   2. 비정상 동작 하는 경우 : pc 생성전 terminate() →  ws 죽음 & pc 후에 연결됨 



### 해결

1. mediaserver.js
   1. 2개 사용중이던 언리얼 미디어 파일 export 버전 1개로 통합 
2. 클라이언트에 stop 로직 추가 
   1. 새 이벤트 발생 & 팝업 닫히는 경우 pc 에 대해 Stop 실행 



### 참고

1. chrome://webrtc-internals/ 
   1. 크롬에서 webrtc 모든 연결 목록 확인가능 