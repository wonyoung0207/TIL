# Netty 에서의 ProtoBuf 

---

>

## ProtoBuf

##### 정의

1. **Google**이 개발한 **이진 데이터 직렬화 포맷**이다. 
2. 데이터를 빠르고 효율적으로 직렬화(Serialize)하고 역직렬화(Deserialize)하기 위해 설계되었다. 
3. 텍스트 기반 포맷(JSON, XML)에 비해 **메시지 크기가 작고 처리 속도가 빠르다.**
4. Netty는 Protobuf와 통합할 수 있는 **인코더와 디코더를 제공**한다. 

##### 특징

| **특징**             | **설명**                                      |
| -------------------- | --------------------------------------------- |
| **이진 데이터 포맷** | JSON이나 XML보다 메시지 크기가 작음           |
| **다양한 언어 지원** | Java, C++, Python, Go 등 대부분 언어 지원     |
| **스키마 기반**      | 데이터 구조를 `.proto` 파일로 정의            |
| **타입 안전성**      | 필드 타입이 명확히 정의됨                     |
| **호환성**           | 새로운 필드 추가 시에도 하위 호환성 유지 가능 |

##### ProtoBuf 와 JSON 비교

| **항목**        | **Protobuf**       | **JSON** |
| --------------- | ------------------ | -------- |
| **데이터 크기** | 작음               | 큼       |
| **속도**        | 빠름               | 느림     |
| **가독성**      | 없음 (이진 데이터) | 있음     |
| **타입 안전성** | 있음               | 없음     |
| **호환성**      | 우수               | 보통     |

##### 언제 사용해야 할까?

- 데이터 크기가 중요한 경우 (모바일, IoT)
- 고성능 통신이 필요한 네트워크 애플리케이션
- 언어 간 데이터 교환이 필요한 분산 시스템

## ProtoBuf 파일 구조 

```protobuf
syntax = "proto3";

package example;

option java_package = "com.example.protobuf";
option java_outer_classname = "PersonProto";

// 주소 메시지 정의
message Address {
  string street = 1;
  string city = 2;
  int32 zip_code = 3;
  reserved 2, 4 to 5; // 번호 예약
  reserved "nickname"; // 이름 예약
}

// 상태 Enum 정의
enum Status {
  UNKNOWN = 0;
  ACTIVE = 1;
  INACTIVE = 2;
}

// 주요 메시지 정의
message Person {
  string name = 1;                    // 이름
  int32 age = 2;                      // 나이
  repeated string hobbies = 3;        // 취미 리스트
  Address address = 4;                // 다른 message의 값을 사용 
  Status status = 5;                  // 다른 Enum 필드값 사용 
  map<string, string> metadata = 6;   // 메타데이터 (키-값 쌍)
  
  oneof contact_info {                // Oneof 필드
    string email = 7;
    string phone = 8;
  }
}

message Section {
  int32 y = 1;
  int32 totalCount = 2;
  int32 lane = 3;
  int32 avgSpeed = 4;
}

```

- syntax 
  - protobuf의 **버전**을 작성(proto2 or proto3)
- package 
  - 데이터 구조의 충돌 방지를 위해서 작성
- option 
  - .proto 파일 및 내부의 **메세지나 필드 등에 대한 옵션**을 설정
- import 
  - 다른 .proto 파일을 현재 파일로 가져오는데 사용. 
  - 이를 통해 다른 파일에 정의된 메세지나 서비스를 현재 파일에서 사용 가능 
- service 
  - 네트워크를 통해 호출되는 메서드들의 집합
- message 
  - **해당 이름의 데이터 구조**를 나타냄.
  - required : 필수 필드
  - repeated : 반복해서 등장하는 필드. 순서가 보장
  - optional : 선택적 필드 
  - enum : enum 타입
  - oneof : oneof 안에 있는 데이터는 안에 있는 데이터 중 하나만 request

## Protobuf & ByteBuf

- **Protobuf:** 구조화된 데이터를 다룰 때 적합하며 개발자 생산성이 높습니다.
- **ByteBuf:** 커스텀 프로토콜 처리나 성능 최적화가 필요할 때 강력합니다.
- 즉, **일반적인 API나 데이터 통신**에서는 **Protobuf**를 추천하며, **고성능 네트워크 처리**가 필요할 때 **ByteBuf**를 활용하는 것이 좋다. 

| **항목** | **Protobuf**                                  | **ByteBuf**                                |
| -------- | --------------------------------------------- | ------------------------------------------ |
| **정의** | Google에서 개발한 **이진 데이터 직렬화 포맷** | Netty가 제공하는 **이진 데이터 버퍼**      |
| **목적** | 객체 데이터를 효율적으로 인코딩/디코딩        | 네트워크 I/O 성능을 최적화하는 데이터 버퍼 |
| **용도** | 데이터 교환 포맷으로 구조적 데이터를 직렬화   | 직접 데이터 읽기/쓰기 제어                 |
| **장점** | 작은 메시지 크기, 플랫폼 독립성               | Zero-copy 메모리 처리, 효율적인 버퍼 관리  |

##### 차이점

| **비교 항목**       | **Protobuf**                   | **ByteBuf**                      |
| ------------------- | ------------------------------ | -------------------------------- |
| **데이터 구조**     | 명확한 필드 정의 (스키마 기반) | 단순한 바이트 배열               |
| **직렬화/역직렬화** | 자동 지원                      | **직접 바이트** 처리             |
| **타입 안전성**     | 타입 안전성 보장               | 수동 데이터 관리                 |
| **성능**            | 고성능 직렬화                  | 고성능 버퍼 관리                 |
| **사용 난이도**     | 쉬움 (스키마 정의)             | 비교적 복잡함 (바이트 수동 처리) |