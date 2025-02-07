

# Netty의 Bootstrap 객체

---

> 

## 정의

- `ootstrap`은 **Netty 서버 & 클라이언트 네트워크 통신을 초기화 및 설정하기 위한 클래스**
- **서버**가 클라이언트 요청을 수신하기 위해 **네트워크 채널을 생성하고 설정**
  - 즉, 네티로 작성한 네트워크 프로그램이 시작할 때 가장 먼저 수행

## 역할

1. 어플리케이션이 **수행할 동작**을 지정
2. 프로그램에 대한 **각종 설정**을 지정한다

## Netty 서버 설정

- Netty 서버 설정은 크게 "서버용" 과 "클라이언트용" 으로 나뉜다. 여기서는 "서버용" Netty 설정으로 하려고 한다. 

- **서버용 (ServerBootstrap)**

  - 연결시 `bind()` 메소드 사용 
  - `ServerBootstrap`은 **서버 통신 설정 관리자**

  ```java
  ServerBootstrap bootstrap = new ServerBootstrap();
  bootstrap.group(bossGroup, workerGroup);
  bootstrap.bind(8080).sync()
  ```

- **클라이언트용 (Bootstrap)**

  - 연결시 `connect()` 메소드 사용 
  - `Bootstrap`은 **클라이언트 통신 설정 도우미**

  ```java
  Bootstrap bootstrap = new Bootstrap();
  bootstrap.group(new NioEventLoopGroup())
          .channel(NioSocketChannel.class)
          .remoteAddress(host, port)
          .handler(channelInitializer)
          .option(ChannelOption.SO_KEEPALIVE, true); // 소켓연결유지 및 비정상적인 연결감지
  ChannelFuture future = bootstrap.connect();
  ```

##### 1. 이벤트 루프 그룹(EventLoopGroup) 

- 네트워크 이벤트(데이터 읽기/쓰기, 연결 이벤트)를 처리하는 스레드 그룹

- Netty 는 이벤트 루프 그룹을 통해 비동기 적으로 네트워크 이벤트를 처리 한다. 

- 종류

  - **bossGroup** 
    - 연결이 수락되면 `workerGroup`에 작업을 위임
  - **workerGroup** 
    - 데이터 읽기, 쓰기 이벤트, 핸들러 실행 등을 담당

  | 그룹            | 역할                          |
  | --------------- | ----------------------------- |
  | **bossGroup**   | 클라이언트 연결 수락(Accept)  |
  | **workerGroup** | 데이터 읽기/쓰기, 이벤트 처리 |

```java
EventLoopGroup bossGroup = new NioEventLoopGroup(1); // 클라이언트 연결 수락 담당
EventLoopGroup workerGroup = new NioEventLoopGroup(); // 데이터 I/O 담당
```

##### 2. 채널 전송 모드

- 채널의 종류(Channel Type) 설정 
  - `NioServerSocketChannel` 등
  - 들어오는 새로운 연결을 수락하고 새로운 Channel을 생성하기 위해 NioServerSocketChannel 클래스를 사용
- 블로킹, 논블로킹, epoll(입출력다중화기법, 대신 리눅스에서만 가능) (channel메서드) 
  - `OioServerSocketChannel..`

##### 3. 채널 파이프라인 

1. 네티채널과 이벤트 핸들러 사이의 연결통로 **( 즉, 네티 서버에 핸들러를 연결)**
2. 소켓채널로 수신된 데이터를 처리할 데이터핸들러를 지정
3. `handler()`
   1. 서버 소켓(Channel)에 이벤트가 발생할 때 실행
4. `childHandler()`
   1. 클라이언트와 연결된 소켓에서 이벤트가 발생할 때 실행
   2. 데이터 수신 후 응답 메시지를 전송

```java
EventLoopGroup bossGroup = new NioEventLoopGroup(1); // 클라이언트 연결 수락 담당
EventLoopGroup workerGroup = new NioEventLoopGroup(); // 데이터 I/O 담당

ServerBootstrap bootstrap = new ServerBootstrap();
// Server 설정
bootstrap.group(bossGroup, workerGroup)
         .channel(NioServerSocketChannel.class) // 서버 소켓 채널 타입
         .handler(new LoggingHandler()) // 서버 소켓에 대한 핸들러
         .childHandler(new ChannelInitializer<SocketChannel>() { // 클라이언트 채널에 대한 핸들러
             @Override
             protected void initChannel(SocketChannel ch) {
                 ChannelPipeline pipeline = ch.pipeline();
                 pipeline.addLast(new SimpleServerHandler());
             }
         });
        .option(ChannelOption.SO_BACKLOG, 128) // 서버 소켓 옵션 설정 -> 연결 대기 큐 크기 설정
        .childOption(ChannelOption.TCP_NODELAY, true) // 클라이언트 소켓 옵션 설정 -> 지연 없이 패킷 전송
        .childOption(ChannelOption.SO_KEEPALIVE, true) // 연결 유지 활성화
```

## 서버 소켓 이벤트

- `ServerBootstrap` 설정시 `handler()` 에 등록하는 이벤트

1. **`channelActive()`**
   - 클라이언트가 연결되었을 때 호출
   - 연결 완료 후 초기 설정이나 환영 메시지를 보낼 수 있습니다.
2. **`channelInactive()`**
   - 클라이언트가 연결을 끊었을 때 호출
   - 리소스 해제나 로그 기록에 사용합니다.
3. **`channelRead()`**
   - 클라이언트로부터 데이터가 들어왔을 때 호출
   - 수신된 데이터를 처리하거나 응답 메시지를 보낼 수 있습니다.
4. **`channelReadComplete()`**
   - 모든 데이터 읽기가 완료되었을 때 호출
5. **`exceptionCaught()`**
   - 네트워크 통신 중 예외가 발생했을 때 호출
   - 연결 종료 전에 오류 로그를 남기고 리소스를 정리하는 데 사용합니다.

## 객체 이용 핸들러 등록 간소화

##### 방법

1. `ChannelInitializer<SocketChannel>` 를 Extend 받아 구현한 객체를 이용하면 쉽고 간결하게 등록할 수 있다. 
2. 정확히는 `ChannelInitializer` 를 상속받고 `initChannel()` 메소드를 `@override` 한다. 

##### 동작 원리

1. 객체 생성
   1. `ChannelInitializer<SocketChannel>` 클래스를 상속한 `channelInitializer`는 Netty 파이프라인 설정을 담당한다. 
2. Netty 서버 생성
   1. Netty 서버 생성시 핸들러 객체를 등록한다. 
   2. `handler(channelInitializer)`를 호출하면 Netty는 **연결이 생성될 때마다 `initChannel()` 메서드를 호출**한다. 
   3. Netty는 `initChannel()` 메서드 내부에 정의된 파이프라인 설정 코드(`pipeline.addLast(...)`)를 자동으로 적용한다. 

```java
public class RelayChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();

        // 데이터 인코더, 디코더 및 비즈니스 로직 핸들러 추가
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("businessHandler", new BusinessLogicHandler());

        System.out.println("Pipeline initialized!");
    }
}
```

```java
RelayChannelInitializer channelInitializer = new RelayChannelInitializer();

// 클라이언트측 Netty 서버 생성
Bootstrap bootstrap = new Bootstrap();
bootstrap.group(new NioEventLoopGroup())
         .channel(NioSocketChannel.class)
         .handler(channelInitializer) // initChannel() 자동 호출
         .option(ChannelOption.SO_KEEPALIVE, true);
```

## Channel 과 Netty Server관계

1. `Bootstrap`과 `ServerBootstrap`은 `Channel`을 생성하고 관리하는 역할을 한다.
2. 클라이언트 역할 : `Bootstrap`
   1. 클라이언트는 `NioSocketChannel`(하나의 채널)을 통해 서버와 연결합니다.
   2. 연결 채널인 `channel` 만 사용
3. 서버 역할 : `ServerBootstrap`
   1. 서버는 `NioServerSocketChannel`(메인 채널)을 통해 연결 요청을 수락하고, 각 요청에 대해 `SocketChannel`(자식 채널)을 생성합니다.
   2. 연결 채널과 데이터 처리 채널 사용
   3. `channel` & `workerchannel` 
4. 핵심
   1. **서버나 클라이언트 모두 `Channel`을 통해 통신**
   2. `Bootstrap` 및 `ServerBootstrap`은 이러한 `Channel`을 설정하고 초기화하는 역할을 합니다.

| 개념                | 설명                                                         |
| ------------------- | ------------------------------------------------------------ |
| **Channel**         | 네트워크 소켓 연결에 대한 **추상화 객체**로 데이터 송수신, 읽기/쓰기 작업을 수행합니다. |
| **Bootstrap**       | **클라이언트용** 설정 클래스입니다. 클라이언트 `Channel`을 설정하고 연결을 관리합니다. |
| **ServerBootstrap** | **서버용** 설정 클래스입니다. 서버가 수락할 `Channel`과 워커 채널(`workerChannel`)을 설정하고 연결 요청을 관리합니다. |