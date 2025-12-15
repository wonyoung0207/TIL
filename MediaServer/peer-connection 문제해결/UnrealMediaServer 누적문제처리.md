# UnrealMediaServer 누적 문제 및 해결

## 문제점 (Problem)

- **증상**: 브라우저 탭 닫기, 페이지 이동, 또는 팝업 종료 시에도 `UnrealMediaServer` 와의 연결인 `ws` (WebSocket)와 `PeerConnection` 객체가 클라이언트 메모리에서 해제되지 않고 누적됨.
- **영향**: 시간이 지날수록 브라우저 메모리 사용량이 증가하고, 미디어 서버 포트 고갈 등의 문제 야기
- **원인**: 클라이언트 측의 `PeerConnection Close` 누락 및 `ws`와의 생명주기 불일치.

---

## 이슈 상세 (Issues)

### 1. WebSocket(ws)과 PeerConnection(pc)의 생명주기 불일치
- **상황**
  - **ws Close 시점**: `pc`로 스트리밍을 시작할 때 (Handshake 및 Signaling 완료 후) 닫힘.
  - **pc Close 시점**: 클라이언트가 명시적으로 종료(`close`)할 때.
- **문제점**: `ws`는 이미 닫혔는데 `pc`는 살아있는 상태가 지속되거나, 종료 시점이 서로 달라 한쪽만 해제되는 좀비 커넥션 발생.

### 2. 코드 파편화 (Code Fragmentation)
- **상황**: 기능별로 서로 다른 미디어 서버 스크립트(`mediaserver.js`)를 사용함.
  - **돌발팝업**: `public` 경로의 레거시 스크립트 사용.
  - **CCTV**: `components` 경로의 스크립트 사용.
- **문제점**: 로직이 이원화되어 있어, 한쪽의 버그(종료 로직 누락 등)를 수정해도 다른 쪽에 반영되지 않음. 유지보수의 어려움.

### 3. 비디오 Play 함수의 중복 호출
- **상황**
  1. 클라이언트 사용자가 `play` 버튼 클릭 시 호출.
  2. `pc` 연결 완료 후 스트리밍 데이터가 들어올 때 내부적으로 다시 호출됨.
- **문제점**: `state` 값 변경과 타이밍 이슈로 인해 `Stop`이 호출되어야 할 시점에 다시 `Play`가 트리거되거나, 불필요한 리소스 점유 발생 가능성.

### 4. Terminate() 시점의 경쟁 상태 (Race Condition)
- **정상 케이스**: `pc` 생성 → `Terminate()` 호출 → `ws` & `pc` 모두 정상 종료.
- **비정상 케이스 (Race Condition)**: `pc` 생성 전(비동기 로딩 중)에 `Terminate()` 호출
  - → `ws`는 죽음.
  - → 그러나 이후 비동기 콜백이 실행되어 `pc`가 생성되고 연결됨.
  - → 결과적으로 제어권 없는 `pc`가 누적됨.

---

## 코드 분석 (Code Analysis)

### 1. 기존 객체 해제 없는 중복 생성 (Zombie Objects)
`DoSignaling` 함수에서 `ws`가 이미 존재함에도 불구하고 `close` 없이 새로 생성합니다. 또한 `onmessage` 콜백 내부에서 `pc`를 새로 생성할 때 기존 `pc`를 확인하지 않습니다.

```javascript
// 기존 코드의 문제점: ws나 pc가 null이 아니어도 덮어씌움 (기존 연결은 누수됨)
function DoSignaling() {
    // ...URL 생성 로직...
    try {
        ws = new WebSocket(URL); // 기존 ws가 있어도 덮어씀!
    } 
    // ...
    ws.onmessage = function(evt) {
        if (state == 0) {
             // ...
             pc = new RTCPeerConnection(servers); // 기존 pc가 있어도 덮어씀!
        }
    }
}
```

### 2. 불안정한 State 처리 (Unsafe State Handling)
- `ws.onmessage`에서 `state`가 `0`이 아닌 경우를 모두 `else`로 처리
- 만약 `Terminate()`가 호출되어 `state`가 `-1`이 된 상태에서 메시지가 도착하면, 이미 `null`이 된 `pc`에 접근하거나 의도치 않은 로직이 실행될 수 있음

```javascript
ws.onmessage = function(evt) {
    // ...
    if (state == 0) {
        // ...
    }
    else {
        // 문제점: state가 -1(종료됨)이어도 이 블록이 실행됨.
        // 이때 pc가 null이면 pc.setRemoteDescription 등에서 오류 발생하거나
        // 좀비 pc가 다시 살아날 수 있음.
        var serverSDP = JSON.parse(strArr[0]);
        // ...
        pc.setRemoteDescription(new RTCSessionDescription(serverSDP));
    }
}
```

---

## 해결 (Resolution)

### 1. mediaserver.js 스크립트 통합
- 다중으로 사용되던 언리얼 미디어 스크립트를 **단일 Export 모듈**로 통합.
- 모든 기능(돌발팝업, CCTV 등)이 동일한 최신 로직을 사용하도록 변경하여 수정사항 일괄 적용.

### 2. 클라이언트 Stop 로직 강화
- **명시적 종료 처리 추가**:
  - 새로운 이벤트 발생 시(다른 카메라 보기 등) 기존 `pc` 확인 후 강제 종료.
  - 팝업 닫힘(Close) 이벤트 등 컴포넌트 파괴 시점에 반드시 `Stop` 로직이 실행되도록 보장.
- **Race Condition 방지**: `Terminate` 호출 시 플래그를 세워, 이후에 `pc` 생성 콜백이 오더라도 연결을 진행하지 않고 즉시 파괴하도록 방어 코드 작성.

---

## 참고 (Reference)

- **chrome://webrtc-internals/**
  - 크롬 브라우저에서 제공하는 WebRTC 디버깅 도구.
  - 현재 열려 있는 모든 `PeerConnection` 목록과 상태, 데이터 전송량 등을 확인하여 누수 여부를 실시간으로 모니터링 가능.

---

## 요약
WebSocket과 PeerConnection의 생명주기 불일치로 인한 누적 문제를 미디어서버 스크립트 통합 및 클라이언트 명시적 Stop 로직 추가로 해결함.