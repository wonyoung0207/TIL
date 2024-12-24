# Proxy 구조 (Socket Stream의 파일수준 필터링)

---

>

## 형태

1. Proxy(Server) + Socket + Stream

## 데이터 단위 

1. Socket Stream

## 개념

1. Application 수준에서 동작
2. Socket + Stream 의 파일 데이터 필터링 
3. **즉, User Mode , App, Proxy, Socket Stream 을 하나로 묶어 외우면 좋다.** 

## 우회(외부 Proxy)

1. 외부 Proxy
   1. Proxy 서버를 이용해 우회하여 Web Server에 접속할 때 사용한다. 
2. 예시
   1. Proxy 서버를 이용해 **우회**하여 Web Server 에 요청을 보낸다. 
   2. 예를들어, 미국의 Web Server는 한국에서는 허용되지 않지만 독일은 허용한다. 
   3. 이때 미국 Web Server에 접속하기 위해선 한국에서 독일 Proxy를 이용해 우회한다면 접속이 가능해진다. 
3. 즉, IP 를 감추고 싶을때 Proxy가 많이 이용된다. 

<img src="./images/Proxy 우회.jpg" width="700">

<img src="./images/Proxy 우회 구조.jpg" width="700">

## 보호와 감시 (내부 Proxy)

1. 내부 Proxy 
   1. **내부 및 외부의 요청을 감시**할 때 사용한다. 
2. Proxy 와 IPS 관계
   1. **IPS의 역할은 단편화된 Packet 단위의 내용을 보고 Client를 보호**하는 것이다. 
      1. Proxy 서버또한 IPS와 같은 기능을 할 수 있다. 
   2. Proxy 서버는 **HTTP통신을 통해 감시**하기 때문에 File을 감시하는 측면에서 IPS보다 좋은 성능을 낼 수 있다. 

<img src="./images/Proxy의 보호와 감시.jpg" width="700">

## 서버 보호 (Reverse Proxy)

1. Reverse Proxy
   1. **서버 보호를 위해 여러계층의  Proxy 서버가 존재**하는 구조 
2. 예시 
   1. 클라이언트가 하나의 서버에 접속할 때 
   2. 클라이언트 요청은 Pakcet수준에서 IPS 를 이용해 암호화한다. 
   3. 이때 Server IP에 바로 요청하는 것이 아닌 Server 앞에 방화벽인 Proxy 서버에 요청을 보내게 된다. 
   4. Proxy는 겹겹이 Web Server를 보호하여 각각 SSL 역할, WAF 역할로써 동작한다. 

<img src="./images/Reverse Proxy 구조.jpg" width="700">

## 로컬 암호화 분석 

1. Fiddler 
   1. **로컬단위에서 HTTPS를 분석**하는 도구 
2. 예시
   1. 암호화 되어있는 HTTPS를 분석하기 위해 로컬 단위에 Fidder 라는 도구를 설치한다.
   2. Fiddler 는 Proccess로써 단편화가 일어나는 Packet(4계층)까지 내려가지 않고 Socket (Stream)단위에서 HTTPS 를 분석하게 된다. 
   3. 즉, Proxy Server 를 127.0.0.1로 설정하면 Fiddler 가 해당 HTTPS 신호를 검지하고 Fiddler 를 이용해 외부로 접속한다. 
   4. 이때 **Fiddler 는 HTTPS 를 Packet 단위가 아닌 Stream 단위에서 복호화해서 분석**하기 때문에 암호화 통신 분석에 매우 용이해진다. 

<img src="./images/Fiddler Proxy 구조.jpg" width="700">