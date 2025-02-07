# Netty 개념정리 

---

>[네티 유저 가이드 예제코드](https://rahs.tistory.com/200)
>
>[네티 프로젝트 코드 Github](https://github.com/netty/netty)

## 정리

1. Netty는 pipeLine을 이용해 이벤트를 처리한다. 
2. 먼저 사용자 입력이 이벤트 루프 라는 장소에 들어가게 된다. 
3. 이벤트 루프에는 PipeLine이 등록되어있다. 
   1. PipeLine은 이벤트 루프에서 이벤트를 받아 핸들러로 전달하는 역할을 한다. 
   2. 각각의 pipeLine 내부에 있는 Handler 가 사용자 입력에 대해 처리를 진행한다. 
   3. 내부 핸들러에 따라 inbound와 outbound 가 있어 입력과 출력의 순서 및 처리 결과가 달라진다.
   4. 역할마다의 PipeLine이 있어 다양한 방법으로 입력을 처리함 -> 역할마다의 Chennel이 형성됨 
4. 즉, **하나의 PipeLine에 여러 핸들러들을 등록한다. 그리고 각각의 PipeLine은 하나의 채널로 등록된다. 채널로 등록하면 이벤트 루프에서 동작한다.** 

## 배경

1. 기존의 소켓 프로그래밍은 클라이언트가 접속하게 되면 스레드를 할당해야 하는데(1:1관계), 정말 많은 클라이언트가 접속을 하게 될 경우 그 숫자만큼 스레드를 생성해야 해서 리소스의 낭비로 이루어지고, 문맥 교환과 관련된 문제와 입력이나 출력 데이터에 관련한 무한 대기 현상이 발생하는 문제가 있었습니다. 이러한 네트워크 문제 때문에 개발된 방법이 자바의 NIO 방식(Non-Blocking Input Ouput)입니다.
2. **NIO**(Non-blocking Input Ouput) ⇒ **비동기적 네트워크 통신**, 핵심으로는 네트워크 리소스 사용률을 세부적으로 제어하는 Non-Blocking 호출이 포함되어 있는 것으로, 내부적으로 시스템의 이벤트 통지 API를 이용해 논블록킹 소켓을 등록하면 해당 소켓의 정보를 확인할 수 있습니다.

![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/ccLhoR/btqHA3CmwNH/KuKYT4KCqLkn5Md0dejjA0/img.png)

- 위의 그림에서 Selector는 입출력을 하고 있는 Socket의 집합 상태를 확인하기 위해 이벤트 통지 API를 사용하기 때문에 클라이언트 당 스레드를 생성하지 않고, 필요할 때마다 스레드에게 통지를 하기 때문에 **비동기적인 통신 구조**를 갖출 수 있게 됩니다. 

## 정의

1. Netty는 프로토콜 서버 및 클라이언트와 같은 네트워크 응용 프로그램을 빠르고 쉽게 개발할 수 있는 **NIO 클라이언트 서버 프레임 워크**입니다. TCP 및 UDP 소켓 서버와 같은 네트워크 프로그래밍을 크게 단순화하고 간소화합니다.

## 특징

1. **설계** : 단일 API로 블로킹과 논블로킹 방식의 **여러 전송 유형을 지원**, 단순하지만 강력한 스레딩 모델, 비연결 데이터그램 소켓 지원, 재사용 지원을 위한 논리 컴포넌트 연결
2. **이용편이성** : JDK 1.6이상을 제외한 **추가 의존성이 없음**
3. **성능** : 코어 자바 API보다 **높은 처리량과 짧은 지연시간**, 풀링과 재사용을 통한 리소스 소비 감소, 메모리 복사 최소화
4. **견고성** : 저속, 고속 또는 과부하연결로 인한 **OutOfMemoryError가 발생하지 않음**, 고속 네트워크 상의 NIO에서 일반적인 읽기/쓰기 비율 불균형이 발생하지 않음
5. **보안** : 완벽한 **SSL/TLS 지원**

## 핵심 컴포넌트

1. **Channel** : 하나 이상의 입출력 작업을 수행할 수 있는 하드웨어 장치, 파일, 네트워크 소켓이나 프로그램 컴포넌트와 같은 Open된 Connection 을 의미. 

2. **Callback** : 다른 메서드로 자신에 대한 참조를 제공하는 메서드. 이벤트를 처리할 때 Netty 내부적으로 콜백을 이용하는데, 이때 ChannelHandler 인터페이스를 통해 이벤트를 처리.

3. **Future** : 작업이 완료가 될 경우 애플리케이션에 알림. Future 객체는 비동기 작업의 결과를 담는 Placeholder의 역할. 이때 ChannelFuture 인터페이스를 이용해 결과값을 활용.

4. **Event**와 **Handler** : Netty는 작업 상태의 변화를 알리기 위해 이벤트를 이용하고, 발생한 이벤트를 기준으로 Handler를 통해 트리거.

5. **EventLoop** : 연결의 수명주기 중 발생하는 이벤트를 처리하는 Netty의 핵심추상화를 정의. 이벤트가 발생할 때까지 대기하다가 이벤트가 발생하면 해당 이벤트를 처리할 수 있는 Handler에게 dispatch함.

   ![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/yEhK2/btqHBRuUD2o/ijCfGliB3cD7LcwZe6Tz01/img.png)

6. **PipeLine** : 이벤트 루프에서 이벤트를 받아 핸들러에 전달하는 역할

   1. **Pipeline 내부에 핸들러들이 추가되는 방식** ( PipeLine 안에 channelHandler가 있는 구조 )
   2. 내부에 핸들러에 따라 결과가 달라지므로 순서 중요. (3가지 종류 존재)
      1. Inboundhandler(들어오는 것을 처리)
         1. **InboundHandler는 Bottom-Up 방식**으로 먼저 넣은 핸들러부터 데이터를 처리

      2. OutboundHandler(값을 내보낼 때 사용)
         1. **OutboundHandler는 Top-down 방식**으로 나중에 넣은 핸들러부터 데이터를 처리

      3. DuplexHandler(양쪽 다 적용) 

   3. `fireChannelRead(msg)`
      1. **클라이언트 요청을 서버가 읽는(fire)** 상황
      2. InBound에서 **다음 핸들러로 전달**하는 메소드 
         1. 즉, 먼저 handler에 등록된 로직이 호출되고, 해당 로직 끝에 `fireChannelRead()` 를 이용해 다음 핸들러로 이벤트를 전달한다. 

   4. `write(msg)` 
      1. 서버가 데이터를 **클라이언트로 보내는(write) 상황** 이다. 
      2. OutBound에서 **다음 핸들러로 전달**하는 메소드 
         1. 즉, 나중에 등록된 outbound handler 부터 호출된다. 

      3. 주의사항
         1. `write()` 호출 후 반드시 `flush()`가 필요


![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/EN6fG/btqHLfuMOba/RY7O3U5BYp5Z9hHACgIqxK/img.png)

## PipeLine의 역순처리

- Netty는 **Pipeline의 흐름을 효율적으로 관리하기 위해 Outbound 이벤트는 역방향 처리**한다. 
- 이는 데이터 출력이 마지막 등록된 핸들러부터 처리될 때 **더 직관적이고 성능이 최적화되기 때문**입니다.
- 즉
  - Inbound 이벤트는 앞에서 뒤로 순서대로 처리
  - Outbound 이벤트는 뒤에서 앞으로 역순으로 처리

## 핵심 플로우 

![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/PWASk/btqHJpEmBF9/R3O3z2HcrDZhEDtZZQazG0/img.png)

1. Netty의 흐름을 대략적으로 표현한 그림. Netty서버를 생성하고 이벤트 루프를 통해 이벤트를 감지,
2. 개설된 채널을 통해 데이터가 넘어오고 파이프라인의 핸들러들을 거치며 로직에 따라 데이터를 처리.

### ServerBootstrap  

- `ServerBootstrap`은 **Netty 서버 초기화 및 설정을 위한 클래스**
- **서버**가 클라이언트 요청을 수신하기 위해 **네트워크 채널을 생성하고 설정**
  - 즉, 네티로 작성한 네트워크 프로그램이 시작할 때 가장 먼저 수행

```java
ServerBootstrap bootstrap = new ServerBootstrap(); // 네티 서버 초기화 
bootstrap.group(bossGroup, workerGroup) // 이벤트 그룹 
     .channel(NioServerSocketChannel.class) // 채널 타입 설정 
     .childHandler(new ChannelInitializer<SocketChannel>() { // 클라이언트 소켓 설정 
         @Override
         protected void initChannel(SocketChannel ch) { 
             ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                 @Override
                 protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                     System.out.println("Received: " + msg);
                     ctx.writeAndFlush("Hello from Netty Server!");
                 }
             });
         }
     });
```

## ChannelOption

1. 개설된 채널에 대해 소켓의 동작방식을 사전에 정의된 옵션을 통해 정의할 수 있습니다. 
   1. option()은 서버 쪽 소켓에 대한 옵션을 의미
   2. childOption()을 클라이언트 쪽 소켓에 대한 옵션을 의미


| 옵션명       | 설명                                            |
| ------------ | ----------------------------------------------- |
| TCP_NODELAY  | Nagle 알고리즘 비활성화 여부 설정               |
| SO_KEEPALIVE | 정해진 시간마다 keepalive packet을 전송         |
| SO_SNDBUF    | 커널 송신 버퍼 크기                             |
| SO_RCVBUF    | 커널 수신 버퍼 크기                             |
| SO_REUSEADDR | TIME_WAIT 걸린 포트 재사용 설정                 |
| SO_LINGER    | 소켓을 닫을 때 송신 버퍼에 남은 데이터 전송시간 |
| SO_BACKLOG   | 동시에 수용 가능한 소켓 연결 가능 수            |

## 예제서버

- 간단한 예제를 통해 어떠한 순서로 Netty서버를 구성하는지 알아보겠습니다.

![img](https://gw.metabuild.co.kr/ekp/service/file/fileView?module=img&fileUrl=/images/000171&fileName=20240221170346302_ZUB3FIE9.png)

1. 2개의 EventLoopGroup을 NioEventLoopGroup으로 생성. NioEventLoopGroup은 다중 이벤트 루프이고 서버측 애플리케이션을 구현할 때 우리는 보통 boss와 worker 두 가지로 나누어 구성.
   1. **boss는 연결을 수락**하는 역할을 하고 해당 연결을 두번째 그룹에 등록해주고, **worker는 이러한 연결에 대한 트래픽을 처리.**
2. ServerBootstrap은 서버를 구성할 수 있게 해주는 도우미 클래스. 체인패턴을 통해 서버를 구성.
3. 새로운 연결을 열 것이고 어떤 방식으로 열 것 인지를 정의. 예제에서는 NioServerSocketChannel을 사용할 것이라 정의.
4. 연결이 수락되고 채널이 생성될 때 구성되는 디코더, 핸들러 등을 어떤 순서대로 파이프라인에 채울지 정의. ChannelInitializer는 이러한 설정을 사용자가 쉽게 할 수 있도록 제공되는 핸들러이고 예제에서는 ServerHandler을 사용.
5. 소켓의 동작방식에 대한 사전에 정의된 옵션을 줄 수 있음. 예제의 SO_BACKLOG는 동시에 수용 가능한 소켓 연결 요청수에 대한 옵션.
6. childOption()은 서버에 접속한 클라이언트 소켓에 대한 채널 옵션을 설정할 때 사용. SO_KEEPALIVE는 정해진 시간마다 keepalive packet을 전송하여 연결상태를 확인하는 옵션.
7. 마지막으로 호스트와 포트를 지정해주고 서버를 구동

## 예제코드

```java
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.ChannelOption;

public class NettyServerExample {

    public static void main(String[] args) throws InterruptedException {
        // 이벤트 루프 그룹 생성
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // 연결 수락 담당
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 데이터 처리 담당

        try {
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
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 클라이언트 소켓 옵션 설정 -> 연결 유지 활성화

            // 서버 바인딩
            ChannelFuture future = bootstrap.bind(8080).sync();
            System.out.println("Server started on port 8080");

            // 채널 종료 대기
            future.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    // Server 소켓에 대한 로그 핸들러
    static class LoggingHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println("Server socket active");
            ctx.fireChannelActive();
        }
    }

    // 클라이언트 소켓 데이터 처리 핸들러
    static class SimpleServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            System.out.println("Received message: " + msg);
            ctx.writeAndFlush("Hello from Netty Server!");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
```

## 관련개념정리

1. ByteBuf

   1. Netty는 java.nio.ByteBuffer를 업그레이드시킨 ByteBuf를 사용. 데이터 컨테이너이고 2개의 포인터로 3개의 영역으로 나뉨.

      ![img](https://t1.daumcdn.net/thumb/R720x0.fpng/?fname=http://t1.daumcdn.net/brunch/service/user/2aeh/image/6NbeVQMaOkzESiI8_2mUrm9oLUM.png)

   2. DiscardableBytes : 이미 읽은 데이터. 버릴 수 있음. Reader Index 이전 영역

   3. ReadableBytes : 읽을 수 있는 데이터. Reader Index와 Writer Index 사이

   4. WriteableBytes : 쓰기가 가능한 영역. Writer Index 이후

   5. 특징

      1. 기존에 java.nio.ByteBuffer는 flip()이라는 메서드를 통해 읽기와 쓰기를 전환하였지만 위 그림과 같이 ByteBuf는 Reader index와 writer index가 따로 존재하여 별도의 전환이 필요없음.
      2. 내장 복합 버퍼 형식을 통한 투명한 제로카피가 가능함. 여러 개의 Buffer객체를 통합할 수 있는 결합 된 Buffer객체를 가지고 있음. 이러한 복합 버퍼를 통해 불필요한 복사를 할 필요가 없음.
      3. 용량에 따라 확장가능. java의 ByteBuffer는 한번 용량을 정하면 고정이였지만 ByteBuf는 가변적으로 늘어남.
      4. 사용자 정의 버퍼 형식으로 확장 가능.
      5. 참조 카운팅을 통해 사용되지 않은 버퍼를 회수하고 메모리 사용량과 성능을 최적화함.
      6. 바이트 버퍼 풀을 제공. 

2. Future(ChannelFuture)

   1. 비동기적 연산의 처리 결과를 표현하기 위해 사용. 처리완료에 대한 확인, 취소, 반환 등을 수행하는 메서드를 제공.
   2. 장점
      1. 비동기 작업의 결과를 쉽게 관리할 수 있음
      2. 작업완료를 기다릴 필요없이 결과가 필요할 때 가져올 수 있음
   3. 단점
      1. 작업 완료 알림의 부재. isDone()메서드가 있지만 이 메서드는 풀링방식으로 작동한다(계속 호출해야 함)
      2. 연산 결과를 가져올 때 블로킹 된다. 
      3. => 이러한 단점들을 보완한 Completable Future라는 것이 나옴

3. ChannelFuture

   1. Netty에서 Future에 대한 단점을 보완한 ChannelFuture를 사용.

   2. ChannelFuture는 하나 이상의 ChannelFutureListener을 등록할 수 있음.

   3. ChannelFuture는 채널까지 전달되어 작업 완료 후 클라이언트로 응답을 보내고 채널도 종료됨.

   4. 이러한 결과로 이벤트루프에는 블로킹이 걸리지 않음.

      

