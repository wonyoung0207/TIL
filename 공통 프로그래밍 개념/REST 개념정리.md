# REST 개념정리

---

> representational : 표현 
>
> REST == RESTful, 레스트풀 이라고도 한다.
>
> [참고 사이트1](https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html)
>
> [참고 사이트2](https://aws.amazon.com/ko/what-is/restful-api/)
>
> [참고 사이트3](https://www.redhat.com/ko/topics/api/what-is-a-rest-api)
>
> [참고 사이트4](https://khj93.tistory.com/entry/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-REST-API%EB%9E%80-REST-RESTful%EC%9D%B4%EB%9E%80)

## REST(Representational state transfer)

### 정의

- API 작동 방식에 대한 조건을 부과하는 **소프트웨어 아키텍처**이다.
- 자원을 이름(자원의 표현)으로 구분하여 해당 자원의 상태(정보)를 주고 받는 모든 것을 의미
  - REST는 처음에 인터넷과 같은 복잡한 **네트워크에서 통신을 관리하기 위한 지침**으로 만들어졌다.
- 따라서 **URL을 통해 센서의 데이터를 주고받는 웹 서비스 아키텍쳐**이다. 

#### 즉, REST란

1. HTTP **URI(Uniform Resource Identifier)**를 통해 **자원(Resource)을 명시**하고
2. HTTP **Method(POST, GET, PUT, DELETE)**를 통해 해당 자원에 대한 **CRUD Operation을 적용**하는 것을 의미한다.
   - Create : 데이터 생성(POST)
   - Read : 데이터 조회(GET)
   - Update : 데이터 수정(PUT, PATCH)
   - Delete : 데이터 삭제(DELETE)

### REST 구성요소

1. Resource (자원)
   - HTTP의 URL이 자원( 센서에서 생성된 데이터) 의 상태이다.
2. Method (표현 또는 행위)
   - Post, Get, Put. Delete 로 자원을 생성,조회,수정,삭제 하는 것 
3. Message (정보)

### REST API

- REST 기반으로 서비스 API를 구현한 것 
- **REST 아키텍처를 구현하는 웹 서비스**를 **RESTful 웹 서비스**라고 한다.
- API 개발자는 여러 아키텍처를 사용하여 API를 설계할 수 있다.
  - REST 아키텍처 스타일을 따르는 API를 REST API라고 한다.

### 특징

- REST는 기본적으로 **웹의 기존 기술과 HTTP 프로토콜을 그대로 활용**하기 때문에 **웹의 장점을 최대한 활용할 수 있는** 아키텍처 스타일이다.
- **REST**는 네트워크 상에서 **Client와 Server 사이의 통신 방식 중 하나**이다.
- Server-Client(서버-클라이언트 구조)
  - 자원이 있는 쪽이 Server, 자원을 요청하는 쪽이 Client가 된다.
- Stateless(무상태)
- Cacheable(캐시 처리 가능)
- Layered System(계층화)
- Uniform Interface(인터페이스 일관성)

### 장점

1. HTTP 프로토콜의 표준을 최대한 활용하여 여러 추가적인 장점을 함께 가져갈 수 있게 해준다.
2. REST API 메시지가 의도하는 바를 명확하게 나타내므로 의도하는 바를 쉽게 파악할 수 있다.
3. 서버와 클라이언트의 역할을 명확하게 분리한다. 

### 단점

1. 사용할 수 있는 메소드가 4가지밖에 없다. 
   -  GET, POST, PUT, DELETE 와 같은 메서드를 제공

### 필요한 이유 

- 최근의 서버 프로그램은 **다양한 브라우저**와 안드로이폰, 아이폰과 같은 **모바일 디바이스에서도 통신**을 할 수 있어야 한다.
- 이때 REST 아키텍처를 이용하여 **멀티 플렛폼에서 안정적으로 통신**할 수 있다. 

### 구성요소

1. 자원(Resource): **HTTP URI**
   - 모든 자원에 고유한 ID가 존재하고, 이 자원은 Server에 존재한다.
   - Client는 URI를 이용해서 자원을 지정하고 해당 자원의 상태(정보)에 대한 조작을 Server에 요청한다.
2. 행위(Verb): **HTTP Method**
   - HTTP 프로토콜의 Method를 사용한다.
   - HTTP 프로토콜은 GET, POST, PUT, DELETE 와 같은 메서드를 제공한다.
3. 표현(Representation of Resource) : **HTTP Message Pay Load**
   - Client가 자원의 **상태(정보)에 대한 조작을 요청**하면 Server는 이에 적절한 **응답(Representation)**을 보낸다.
   - REST에서 하나의 자원은 JSON, XML, TEXT, RSS 등 여러 형태의 Representation으로 나타내어 질 수 있다.
     - JSON 혹은 XML를 통해 데이터를 주고 받는 것이 일반적이다.