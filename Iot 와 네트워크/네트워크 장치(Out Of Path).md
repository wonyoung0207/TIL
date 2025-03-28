# Out Of Path 구조 (Packet 인식/인지)

---

>

##### 형태

1. 센서 + Packet 수집장치 
2. 수집장치
   1. L2 Port Mirroring
   2. Tab 스위치 (고성능 스위치)

##### 데이터 단위 

1. 3계층 - Packet 

##### 개념

1. **분석 및 탐지 목적**으로 사용한다. 
   1. 센서와 수집장치에 의해 스위치로 들어오는 내용들을 **Mirroring(복사) 해서 저장해놓는 구조**이다. 
2. 센서는 청진기와 비슷하다고 생각하면 된다. 
   1. **센서의 역할은 Packet의 모든 내용을 Mirroring 해서 수집하고 분석하는 것이다.** 
   2. 즉, 수집장치는 도감청으로 이어질 수 있다. 

##### 예시

1. ISP가 클라이언트의 Out Bound 를 모니터링하고 있다. 
2. 이때 클라이언트의 Packet이 접속하려는 곳이 유해 사이트라면 ISP 에서 수집(Monitoring)하고 있다가 Sensor 서버가 해당 사이트를 감지하면 접속하려는 곳보다 더 빠른 속도로 클라이언트의 요청을 유해사이트 사이트로 처리 페이지로 이동시킨다. 

3. 망중립성의 원칙 (SPI 와 DPI)
   1. 따라서 우리나라는 SPI 를 통제할 수 있는 나라이다. 
   2. 하지만 DPI 를 실시하는 순간 도감청으로 본다. 

<img src="./images/Out Of Path 구조1.jpg" width="700">

<img src="./images/Out Of Path 구조2.jpg" width="700">