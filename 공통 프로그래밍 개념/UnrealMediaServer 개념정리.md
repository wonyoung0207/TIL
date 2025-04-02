# UnrealMediaServer 개념정리

---

>

## 최종 정리 (라이프사이클)

1. UnrealWebRTCPlayer 객체 생성 
   1. 내부 설정 초기화 진행
2. 사용자 play 호출 
   1. 비디오 재생
3. ws 생성
   1. 미디어 서버와 핸드쉐이크 (연결관리)
   2. SDP/ICE 정보를 Unreal Media Server와 교환하는 통신 채널 (signaling)
4. ws 연결 성공
   1. 미디어서버가 ws.onmessage() 통해 코덱정보, ICE 주소, 트랙 정보 등 Streaming에 필요한 값들을 ws 통해 넘겨줌
   2. 내부 state 값 1로 업데이트 
   3. 중요한건 미디어 서버가 준다는것임 
5. peer-connection 생성
   1. UnrealMediaServer의 streaming 을 받을 수 있도록 연결통로역할인 pc(RTCPeerConnection) 객체 생성 
   2. 미디어서버는 WebRTC 스트리밍 서버 (미디어 송출자)
   3. pc는 실제 미디어 데이터 송수신 주체(P2P 연결의 본체)
6. ws 으로 정보전송 (`createOffer`)
   1. `createOffer()` 로 생성된 SDP를 미디어 서버로 ws.send() 통해 전송 

7. 서버로부터 SDP, ICE 정보 수신
   1. state값 1로 인해 호출
   2. pc에 미디어서버가 준 정보 토대로 SDP생성
      1. server의 SDP, ICEcandidate 를 pc에 설정 
8. WebRTC 연결 
   1. SDP 이용해 "PC ----- MediaServer" 가 webRTC로 연결 

9. ws close
   1. PC 커넥트 완료되면 ws.close() 호출 

10. Streaming시작 
    1. pc에 세팅된 SDP, ICE 이용해 미디어 서버와 Streaming 통신 시작
    2. `ontrack` (미디어서버로부터 스트리밍 수신되면 호출) 에 의해 스트리밍 시작




##### websocket    VS   peer-connect

1. ws : 연결관리 ( 핸드쉐이크 )
2. pc : stream ( 데이터 전송 )



## 1. ICE Candidate란?

1. **브라우저가 연결 가능한 IP 후보 주소**

   1. 인터넷에서는 컴퓨터들이 서로 직접 연결되기 어렵고, 대부분 NAT(공유기 뒤)나 방화벽에 숨어있음
   2. 그래서 브라우저끼리 직접 연결하려면, **서로가 실제로 연결 가능한 주소(IP + 포트)** 를 찾아야 함
   3. 이걸 자동으로 찾아주는 게 **ICE**라는 기술이다.

2. 예시

   1. 브라우저가 **"나 여기서 연결할 수 있어!"** 라고 알려주는 후보 IP 주소

3. 종류

   - 하나의 브라우저는 여러 개의 candidate를 만들어낼 수 있음

   1. 로컬 IP (`192.168.x.x`)
   2. 공용 IP (STUN 통해 얻음)
   3. 중계 서버 IP (TURN 서버)

##### `iceServers`란?

1. ICE Candidate를 하기위해 필요한 서버 
   1. 즉, 브라우저가 **"내가 어디서 연결될 수 있는지 주소 후보(ICE candidate)를 수집"** 하려면, STUN 또는 TURN 서버가 필요
2. STUN 서버란?
   1. 상대방에게 알려줄 **내 공인 IP 주소**를 찾아주는 서버
   2. 대부분 무료
3. TURN 서버란?
   1. 브라우저끼리 직접 연결이 안 될 경우 **중계해주는 서버**
   2. 유료 서비스인 경우가 많음



## 2. RTCPeerConnection

##### 정의

1. `RTCPeerConnection`은 WebRTC의 핵심 구성요소 중 하나로, 브라우저 간 **실시간 미디어/데이터 통신**을 가능하게 해주는 객체

##### 역할 

1. 오디오 / 비디오 스트림 전송
2. 데이터 채널을 통한 실시간 데이터 송수신
3. ICE (Interactive Connectivity Establishment) 기반 연결 관리

##### `new RTCPeerConnection();`   빈 설정 하는이유

1. Unreal 서버가 WebSocket을 통해 직접 ICE candidate를 줘버리기 때문

##### PeerConnection에 트랙을 추가하는 이유

1. 내가 상대방에게 어떤 미디어(비디오/오디오)를 보내고 싶은지를 알려주기 위해 

##### 연결 상태 관리 (`pc.onconnectionstatechange`)

```js
function onConnStateChange(event) {
    if (pc.connectionState === "failed") {
        Terminate();
        log.error("Connection failed; playback stopped");
    }
}
```

| 상태           | 의미                             |
| -------------- | -------------------------------- |
| `new`          | 연결 초기화 상태                 |
| `connecting`   | ICE 후보 교환 중                 |
| `connected`    | 연결 성공 (스트림 송수신 가능) ✅ |
| `disconnected` | 일시적인 연결 끊김               |
| `failed`       | 연결 실패 ❌                      |
| `closed`       | 연결 종료됨                      |

##### 미디어 수신 (`gotRemoteStream`)

1. Unreal Media Server가 보낸 비디오 스트림을 비디오 태그에 연결할때 사용 
2. `pc.ontrack` 이벤트는 상대방(서버)이 보내는 **비디오/오디오 스트림을 수신**하면 호출됨 

```js
function gotRemoteStream(e) {
    remoteVideo.srcObject = e.streams[0]; // 화면 출력
    if (videoCodec == "")
        remoteVideo.setAttribute('style', 'background-color:black');

    if (showControls)
        remoteVideo.controls = true;
}
```

##### WebRTC 연결 (`pc.createOffer([options])`)

1. 미디어서버와의 Streaming 위해 **연결 제안서를 만듬**
2. `createOffer()` 이용해 미디어서버랑 연결 후 Streaming 시작 
   1. WebRTC 는 **SDP (Session Description Protocol)** 를 통해 연결 정보를 교환
   2. 브라우저가 사용 가능한 코덱, 미디어 트랙, 네트워크 정보 등을 담은 **SDP Offer**를 생성
   3. 이걸 상대방에게 보내야 WebRTC 연결이 시작
3. **즉, SDP 정보를 토대로 Offer 생성 후 미디어서버로 보내면 WebRTC 연결되서 Streaming 시작** 
   1. `pc.createOffer()`만으로는 스트리밍이 시작되지 않음 
   2. `onCreateOfferSuccess()` 에서 ws.send() 발생 

4. `setLocalDescription()`
   1.  내 연결 계획(SDP)을 로컬 pc에 등록하는 작업
   2. 즉, webRTC로 스트리밍 하기전 pc에 준비동작 하는거로 이해하면됨 

```scss
[브라우저 클라이언트]
   ↓
createOffer()
   ↓
setLocalDescription()
   ↓
WebSocket으로 서버에 Offer 전송
   ↓
[Unreal Media Server]
   ↓
SDP Answer + ICE Candidate 전송
   ↓
[브라우저]
   ↓
setRemoteDescription(), addIceCandidate()
   ↓
연결 성립
```



##### websocket URL 예시

```js
URL: ws://127.0.0.1:5119/webrtc_playnow/singleport/tcp/camera
```

##### codec

1. strArr 배열에 저장 
2. videoCodec = strArr[0];
3. audioCodec = strArr[1];



## 3. unrealWebRTCPlayer 

##### 사용변수 

| 변수                       | 설명                                        |
| -------------------------- | ------------------------------------------- |
| `pc`                       | RTCPeerConnection 객체                      |
| `ws`                       | WebSocket 객체                              |
| `state`                    | 연결 상태 (-1: 중지, 0: 초기화, 1: 연결 중) |
| `connOK`                   | WebSocket 연결 성공 여부                    |
| `remoteVideo`              | video DOM 요소                              |
| `audioCodec`, `videoCodec` | 서버에서 지정한 코덱                        |
| `firstAttempt`             | Android 연결 재시도 시도 여부               |
| `latestStopTime`           | 마지막 정지 시간 (중복 연결 방지)           |

##### state 

- 사용자가 스트리밍을 재생하거나 중지할 때의 진행 상황을 추적하기 위해 사용되는 변수
- -1 : 초기화 상태
- 0 : ws 연결하는 중 
- 1 : 연결해서 스트리밍중 

| 값   | 의미                             |
| ---- | -------------------------------- |
| `-1` | 중지됨 (idle 상태) , 초기화 상태 |
| `0`  | 재생 시도 중 (signaling 중)      |
| `1`  | 재생 중 (WebRTC 연결 중/완료)    |