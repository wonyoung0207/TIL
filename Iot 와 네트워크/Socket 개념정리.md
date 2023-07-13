# Socket 개념정리 

---

>[참고 사이트1](https://helloworld-88.tistory.com/215)
>
>[참고 사이트2](https://medium.com/@su_bak/term-socket%EC%9D%B4%EB%9E%80-7ca7963617ff)
>
>[참고 사이트3](https://popbox.tistory.com/66)

## Socket

### 정의

- 두 프로그램이 서로 데이터를 주고 받을 수 있도록 양쪽 모두에 생성되는 통신 단자 
  - 따라서 실시간 데이터를 주고받는 스트리밍에 적합하다. 
  - 클라이언트와 서버 간의 **데이터 전송을 담당**
- **TCP / IP 기반** 네트워크 통신에서 데이터 송수신의 마지막 접점을 말한다.  
  - Server와 Client가 특정 Port를 통해 실시간으로 양방향 통신을 하는 방식
- 떨어져 있는 두 호스트를 **연결해주는 도구**로써 인터페이스의 역할을 하는데 데이터를 주고 받을 수 있는 구조체로 **소켓을 통해 데이터 통로**가 만들어 진다. 

### 소켓통신에 필요한 것

- 소켓간 통신을 위해서는 네트워크상에서 클라이언트와 서버에 해당되는 컴퓨터를 식별하기 위한 **IP주소**와 해당 컴퓨터내에서 현재 통신에 사용되는 응용프로그램을 식별하기 위한 **포트번호**가 사용된다. 

### 사용처

1. 소켓은 일반적으로 **클라이언트와 서버 간의 통신**에서 사용된다. 
   - **클라이언트 소켓**은 **서버로 연결**을 시도하고, **서버 소켓**은 클라이언트의 **연결 요청을 수락**한다. 
2. TCP(Socket Transmission Control Protocol) 또는 UDP(User Datagram Protocol) 프로토콜을 사용하여 네트워크 통신을 수행한다. 
   - **TCP**
     - TCP 소켓은 **신뢰성이 있는 연결** 기반의 통신을 제공하며, 데이터 전송의 정확성과 순서를 보장한다. 
   - **UDP**
     - UDP 소켓은 **비연결성**이며, 데이터의 신속한 전송을 위해 사용한다. 
3. 네트워크 데이터를 읽고 쓰는 인터페이스를 제공하며, 데이터의 전송 및 수신을 위한 메서드를 제공한다. 

### Endpoint ( 종착점 )

- IP Address와 port 번호의 조합을 뜻하며 **최종 목적지**를 나타낸다.
  - 모든 TCP 연결은 2개의 앤드 포인트로 유일하게 식별될 수 있다. 
- 예시로 최종목적지는 사용자의 디바이스(PC, 스마트폰 등) 또는 Server가 될 수 있다. 

### Socket의 역할 

- 데이터를 통신할 수 있도록 해주는 연결부이기 때문에 통신할 두 프로그램(Client, Server) 모두에 소켓이 생성되야 한다. 
- **클라이언트 ( Client )**
  - 접촉하는 측을 Client 라고 한다. 
  - 실제로 데이터 송수신이 일어나는 곳
  
- **서버 ( Server )** 
  - 접촉을 기다리는 측을 Server라고 한다. 
  - 클라이언트 소켓의 연결 요청을 대기하고, 연결 요청이 오면 클라이언트 소켓을 생성하여 통신이 가능하게 한다.

### 소켓의 동작과정 

1. 소켓 생성
   - 클라이언트는 서버에 연결하기 위해 소켓을 생성하고, 서버는 연결 요청을 수락하기 위한 소켓을 생성
2. 연결 설정
   - 클라이언트는 서버의 IP 주소와 포트 번호를 사용하여 서버에 연결을 시도
3. 데이터 송수신
   - 연결이 수립된 후, 클라이언트와 서버는 소켓을 통해 데이터를 송수신
4. 소켓 닫기 
   -  통신이 완료되면 클라이언트와 서버는 소켓을 닫아 연결을 종료

### 사용방법

1. Client와 Server 두군데에서 socket 을 생성한다. 
2. Server 측에서 
   1. bind() 함수로 **ip와 port 번호를 설정**한다.
   2. listen() 함수로 클라이언트의 접근 요청에 수신 대기열을 만들어 몇 개의 클라이언트를 대기 시킬지 결정한다. 
   3. accept() 함수를 사용하여 **클라이언트와의 연결을 기다린다.** 
      - 클라이언트가 연결해 올 때마다 요청은 요청 큐(Request Queue)에 쌓이고, 각각의 클라이언트 연결에 accept() 함으로써 요청을 **요청 큐**에서 꺼내고 **Socket 객체가 리턴**된다. 
      - 서버에는 ServerSocket과 Socket 2가지 종류의 소켓이 존재한다. 
3. Client 측에서 
   1. connect() 함수를 이용하여 통신 할 **서버**의 설정된 **ip와 port 번호**에 통신을 시도
   2. 통신을 시도 시, 서버가 accept() 함수를 이용하여 클라이언트의 socket descriptor를 반환
   3. 데이터는 Byte 형태로 변환해 전달하고 수신시 다시 변환과정을 거쳐 사람이 읽을 수 있게 한다. 
4. 이를 통해 클라이언트와 서버가 서로 read(), write() 를 하며 통신 (이 과정이 반복)

- 서버 소켓 구현 

  ```java
  public class MyServer {
  	public static void main(String[] args) {
  		BufferedReader in = null;
  		PrintWriter out = null;
  		
  		ServerSocket serverSocket = null; // 서버 프로그램에서 사용하는 소켓으로 ServerSocket 객체를 생성하여 클라이언트가 연결해오는 것을 기다린다. 
  		Socket socket = null; // 클라이언트와 데이터를 주고받기 위해 사용되는 소켓 
  		Scanner scanner = new Scanner(System.in);
  		
  		try {
  			serverSocket = new ServerSocket(8000);// 포트번호를 이용해 서버 소켓 생성 
  			
  			System.out.println("[Server실행] Client연결대기중...");
  			socket = serverSocket.accept();			// 연결대기 -> socket 객체에 데이터 넣어 client 와 통신함 
  
  			System.out.println("Client 연결됨.");
              
              // 데이터 송수신을 위한 input/output 스트림 생성 
  			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 데이터 수신에 사용
  			out = new PrintWriter(socket.getOutputStream()); // 데이터 송신에 사용 
  						
  			while(true) {
  				String inputMessage = in.readLine();	// 수신데이터 한줄씩 읽기	
  				if ("quit".equalsIgnoreCase(inputMessage)) break;
  				
  				System.out.println("From Client: " + inputMessage);
  				System.out.print("전송하기>>> ");
  				
  				String outputMessage = scanner.nextLine();
  				out.println(outputMessage);
  				out.flush();
  				if ("quit".equalsIgnoreCase(outputMessage)) break;
  			}
  		} catch (IOException e) {
  			e.printStackTrace();
  		} finally {
  			try {
  				scanner.close();		// Scanner 닫기
  				socket.close();			// Socket 닫기
  				serverSocket.close();		// ServerSocket 닫기
  				System.out.println("연결종료");
  			} catch (IOException e) {
  				System.out.println("소켓통신에러");
  			}
  		}
  	}
  }
  ```

- 클라이언트 소켓 구현 

  ```java
  public class MyClient {
  	public static void main(String[] args) {
  		BufferedReader in = null;
  		PrintWriter out = null;
  		
  		Socket socket = null;
  		Scanner scanner = new Scanner(System.in);
  		
  		try {
  			socket = new Socket("127.0.0.1", 8000); // 서버에 접속하기 위한 IP와 Port 번호 
  			
  			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  			out = new PrintWriter(socket.getOutputStream());
  			
  			while(true) {
  				System.out.print("전송하기>>> ");
  				String outputMessage = scanner.nextLine();
  				out.println(outputMessage);
  				out.flush();
  				if ("quit".equalsIgnoreCase(outputMessage)) break;
  								
  				String inputMessage = in.readLine();
  				System.out.println("From Server: " + inputMessage);
  				if ("quit".equalsIgnoreCase(inputMessage)) break;
  			}
  		} catch (IOException e) {
  			System.out.println(e.getMessage());
  		} finally {
  			try {
  				scanner.close();
  				if (socket != null) socket.close();
  				System.out.println("서버연결종료");
  			} catch (IOException e) {
  				System.out.println("소켓통신에러");
  			}
  		}
  	}
  }
  ```

### 종류

1. 스트림 socket
   - TCP 를 따르는 소켓으로, 양방향 연결을 지향한다. 
   - 송신된 순서에 따라 중복되지 않게 데이터를 수신한다.
   - 소량의 데이터보다 대량의 데이터 전송에 적합하다. 
2. 데이터그램 socket 
   - UDP 를 따르는 소켓으로, 비연결성을 지향한다. 
   - 데이터의 크기에 제한이 있다. 
   - 확실한 전달이 보장되지 않아 데이터 손실되도 오류가 발생하지 않음
   - 실시간 멀티미디어 정보 처리에 주로 사용됨 ( 전화, 챗봇 )

---

## HTTP 와 Socket 통신의 차이점 

1. HTTP 통신 
   - Client의 **요청(Request)**이 있을 때만 서버가 **응답(Response)**하여 해당 정보를 전송하고 **곧바로 연결을 종료**하는 방식
   - **단방향 통신**으로 실시간 연결이 아닌 필요한 경우만 Server에 접속할 때 사용된다. 
2. Socket 통신 
   -  Server와 Client가 **특정 Port를 통해 실시간으로 양방향 통신**을 하는 방식
   - 양방향 통신으로 실시간 데이터를 주고받는 상황이 필요한 경우 사용한다. 
   - 동영상, Streaming , 온라인 게임 