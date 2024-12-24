# [Switch ]

#### 개념

1. 현실의 **교차로**라고 생각하면 된다. 
2. 차량(Packet)이 출발지를 떠나 목적지로 향할때, 여러 교차로를 지나게 된다. 교차로마다 가야할 방향을 정해야 하는데, 이때 사용되는 개념이 Switch 와 Switching이다. 
3. 따라서 각 Layer 마다 Switch 라는 의미가 존재하며 해당 Switch의 역할은 `Interface 선택` 이다. 
4. 이때 Switch는 각 계층마다 `Interface`가 의미하는게 다르다. 
   1. L3 Switch는 Router 의 역할로, 경로 선택을 의미하며 
   2. L2 Switch는 Mac주소 선택의 의미를 가진다. 
5. 비용 (Matric)
   1. 라우팅할 때 Matric 값을 사용하게 되는데, 이때 Matric 이 낮을 수록 좋은 성능을 뜻한다. 

# [L2 Access Switch] 

<img src="./images/허브 스위치.jpg" width="800">

#### 개념

1. End-Point 와 직접 연결되는 스위치이다. **(Network Access계층**에서 사용)
2. L2 스위치는 MAC 주소를 가지고 스위칭 한다. 

#### 연결 확인

1. 주황 LED
   1. 연결에 이상있음 (**LINK-DOWN**)이라고함 
2. 녹색 LED
   1. 연결 정상 (**LINK-UP**) 이라고함 

#### L2 Distribution Switch 

<img src="./images/스위치 동작구조.jpg" width="600">

1. L2 Switch를 위한 "고성능 스위치" 를 뜻한다. 
2. 대부분 VLAN 기능을 제공한다. 