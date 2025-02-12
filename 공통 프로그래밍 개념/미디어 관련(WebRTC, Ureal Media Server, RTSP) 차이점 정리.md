## 

## Unreal Media Server

1. 영상 및 오디오 스트리밍 서버 소프트웨어

## RTSP

1.  RTSP는 미디어 스트리밍을 위한 프로토콜 (Real-Time Streaming Protocol)

## WebRTC

1. WebRTC는 브라우저 및 애플리케이션 간 실시간 미디어 통신 기술

##  **차이점 비교표**

1. **Unreal Media Server** → 멀티 프로토콜 미디어 서버 (RTSP/WebRTC 지원)
2. **WebRTC** → 브라우저 & 앱 기반 실시간 P2P 통신 (초저지연)
3. **RTSP** → 미디어 스트리밍 프로토콜 (CCTV, 감시 시스템에서 많이 사용됨)

| 항목              | **Unreal Media Server**          | **WebRTC**                               | **RTSP**                       |
| ----------------- | -------------------------------- | ---------------------------------------- | ------------------------------ |
| **기능**          | 미디어 서버                      | P2P 미디어 전송                          | 스트리밍 프로토콜              |
| **주요 사용처**   | 라이브 스트리밍, IP 카메라       | 화상회의, 실시간 통신                    | CCTV, IP 카메라                |
| **지연시간**      | 1초 이하 (최적화 가능)           | <500ms (초저지연)                        | 1~2초                          |
| **브라우저 지원** | WebRTC 지원 시 가능              | 네이티브 지원 (Chrome, Firefox, Edge 등) | 직접 지원 안됨 (플레이어 필요) |
| **P2P 지원**      | ❌ (서버 기반)                    | ✅ (P2P 가능)                             | ❌ (서버 필요)                  |
| **주요 프로토콜** | RTSP, WebRTC, HTTP(S), RTMP, UMS | WebRTC (ICE, DTLS, RTP)                  | RTSP (TCP/UDP)                 |
| **코덱 지원**     | H.264, AAC, VP8, VP9             | H.264, VP8, Opus, G.711                  | H.264, AAC                     |
| **응용 프로그램** | Windows 기반 서버                | 웹RTC 브라우저 & 앱                      | CCTV, 감시 시스템              |

## WebRTC + Unreal Media Server + RTSP 조합

| **기능**                 | **사용 기술**                       | **설명**                                                    |
| ------------------------ | ----------------------------------- | ----------------------------------------------------------- |
| **실시간 영상 통신**     | **WebRTC**                          | 초저지연 (P2P, 500ms 미만) 화상회의, 원격 제어              |
| **라이브 스트리밍**      | **Unreal Media Server**             | HTTP/WebRTC/RTSP/RTMP 등을 통해 다수의 시청자에게 영상 제공 |
| **카메라 스트리밍**      | **RTSP (Unreal Media Server 사용)** | IP 카메라, CCTV, RTSP 지원 장비에서 영상 수신               |
| **VOD (녹화 영상 제공)** | **Unreal Media Server**             | HTTP/RTSP/RTMP로 녹화된 영상 제공                           |