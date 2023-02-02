# CoAP 개념정리

---

> CoAP 프로토콜에 대해 정리한다. 

## CoAP 

### 정의

- 전송지연과 **패킷 손실률이 높은 네트워크 환경**에서 **저사양의 하드웨어**로 동작되는 센서 디바이스의 **RESTful 웹 서비스를 지원**하기 위한 **경량 프로토콜**

### REST

- Resource (자원)
  - HTTP의 URL이 자원( 센서에서 생성된 데이터) 의 상태이다.
- Method (표현 또는 행위)
  - Post, Get, Put. Delete 로 자원을 생성,조회,수정,삭제 하는 것 
- Message (정보)
- 따라서, **URL을 통해 센서의 데이터를 주고받는 웹 서비스 아키텍쳐**

### 통신 구조

- **Server / Client 구조**로, HTTP와 동일한 구조를 가진다. 
- Client 는 **User(사용자)** 부분을 뜻한다. 
- Server는 Iot device 나 센서같은 **Resource(자원) 을 생성하는 쪽**이다. 

### 계층구조

1. Message 계층
   - **메시지 형태**를 뜻하며, CON, ACK, NON, RST 의 타입을 가진다. 
   - **CON (확인형)** : 잘 받았는지 확인하고 싶을때 사용
   - **ACK (승인)** : 잘 받았다고 OK 사인 보낼때 사용
   - **NON (비확인형)** : 잘 받았는지 확인 안될때 사용
   - **RST (재설정)** : 특수목적일 경우 사용. 
   - 예를들어, CON 메시지를 보내면 응답으로 ACK 메시지가 와야한다. 
2. Request / Response 계층
   - **CoAP의 모델**을 뜻하며, 요청과 응답의 형태를 가진다. 
   - Request 단계
     - CON 형태의 메시지로 리소스를 요청한다.
   - Response 단계
     - ACK 형태의 메시지와 함께 요청한 리소스(Data)를 전달한다. 

### Option

- CoAP 프로토콜에서 사용할 수 있도록 만들어진 프로토콜이다. 
  - 각각의 기능과 사용법을 가지고 있다.

1. Observe
   - 서버(Device나 Senser) 의 리소스(data)가 변화할 때마다 클라이언트(사용자) 에게 메시지를 전송하는 기능 
   - 한번만 Request 로 Observe 등록해주면 리소스 변화할 때마다 Response를 보내줌 
2. Group Communication
   - Client가 한번에 여러 Server(device) 로 메시지를 보내기 위해 만들어진 프로토콜
   - Client와 Server **중간에 RD(Resource Directory)를 두어서 한번에 여러 기기를 제어하는 방법**이다. 
     - RD는 Server들의 정보를 가지고 있다. 
   - 한번의 요청으로 여러 기기에서 응답을 받을 수 있다.
3. Block-wise Transfer
   - 대용량 리소스를 전달하기 위해 만들어진 프로토콜
   - 방법
     - 큰 리소스를 block 형태로 나눈다.
     - 메시지 뒤에 블록에 대한 정보가 추가적으로 달린다.
     - 받는곳에서 블록의 순서를 조합해 리소스에 대한 정보를 읽을 수 있다.  