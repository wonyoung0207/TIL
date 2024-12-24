# DHCP

<img src="./images/DHCP 동작예시.jpg" width="700">

## 개념

1. IP 주소 + Subnetmask + Gateway 주소 + DNS 서버 주소 를 자동( Dynamic ) 으로 설정해준다. 

## 인터넷 설정

1. PC의 인터넷 설정에서 DHCP 가 사용된다. 
2. IP주소, SubnetMask, Gateway IP주소, DNS 를 설정할 수 있는데, 이때 DNS 만 L3가 아니다. 
3. `자동으로 IP or DNS 주소 받기` 가 DHCP를 사용하는 것이다. 

## Dynamic과 Runtime관계

1. Dynamic이란 네트워크에서는 Runtime과 같다. 
2. Runtime = Host가 작동중에 **서버가 클라이언트에게 IP 주소를 알려준다.** 

## 동작과정 

1. Runtime
   1. 특정 PC 의 전원이 켜진다. 
2. DHCP 설정 유무 파악
   1. 만약 네트워크 설정이 "자동" 으로 되어있다면 DHCP 를 이용하게 된다. 
   2. "수동" 설정이라면 설정한 IP, Subnetmask, DNS, Gateway IP주소를 사용한다. 
3. Gateway 정보
   1. Gateway 정보를 받아오는데, 이때 GW의 IP 주소만 받고 Mac 주소는 받지 않는다. 
   2. 왜냐하면 ARP 프로토콜 이용해 GW의 IP 주소를 가지고 Mac 주소 알 수 있기 때문에 
4. **Brodcast 전송**
   1. DHCP 설정시 **Broadcast 를 날려 DHCP Server를 찾게**된다. 
   2. 이때 Broadcast 의 신호는 자신의 네트워크 풀 즉, 외부 네트워크가 아닌 내부망 으로만 신호를 보낸다. 
   3. DHCP Serever만 응답하고, 나머지 PC는 신호를 받지만 응답하지 않는다. 
5. DHCP 에서 네트워크 설정 제공
   1. DHCP는 `IP Pool `이라는 저장공간을 가지고있어, 내부망의 IP 주소를 관리하고 있다. 
   2. 여기서 연결된적이 있는지 유무 파악 후 연결된 적이 있으면 동일 네트워크 설정을 제공하고, 처음 연결이라면 새로운 네트워크 설정을 제공한다. 
   3. 이때 제공되는 네트워크 정보는 "IP, Subnetmask, DNS, Gateway IP주소" 이다. 