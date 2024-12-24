## WAS 구조

1. JVM
   1. User Mode 수준에서 S/W 형태로 CPU를 구현한 Machine 이라고 보면 된다. 
   2. 즉, SW 로 구현된 CPU이다. 
   3. Java Byte Code 로 동작한다. 
2. Servlet Container
   1. 개발자가 구현한 개별 기능(메소드)들의 집합 
   2. 필요한 기능들을 구현해 Servlet으로 관리한다. 
3. Middelware
   1. 공통 기능들인 네트워크, DB 접속 기능들을 처리하는 역할

<img src="./images/WAS 구조.jpg" width="1000">