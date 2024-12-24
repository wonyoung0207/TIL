## ARP ( Address Resoultion Protocol )

<img src="./images/ARP 동작.jpg" width="700">

## 개념

1. IP 주소를 이용해 Mac 주소를 알아낼때 활용되는 프로토콜  
   1. **통신은 L3 수준에서 Internet을 이용해 서버의 L3 로 이어진다. 이때 L2 인 Mac 주소는 서버와 통신시 알필요가 없다.** 
   2. L2 수준은 Local 망으로, GW , 라우터 등의 주소에만 사용된다. 

2. 보통 PC 를 부팅하면 Gateway(공유기) 의 Mac 주소를 찾는다. 
   1. **왜냐하면 네트워크를 이용하기 위해선 무조건 Gateway를 거쳐야하기 때문이다.** 
   2. 이때 Mac 주소를 찾는 프로토콜이 ARP 이며 Request 인 IP 를 통해  Gateway의 Mac 주소를 Reply 받는다. 