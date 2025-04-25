# WSL Windows IP와 포트포워딩

---

>

## 포트포워딩 하는 이유

1. WSL 내부에서 Netty 서버를 띄우고 외부 PC에서 접근하려고 했다. 
   1. wsl 내부에서는 해당 포트로 서버가 띄워진것을 확인했고, wsl IP로 외부에서 접속을 시도해봤다. 
   2. 그런데 websocket접속이 안되서 살펴보니 WSL IP는 windows 내부와 연결되어있을 뿐이라 Ping 도 안되고 직접 연결이 되지 않았다. 
2. 따라서 윈도우 IP와 특정 port  로 접속했을 때, Window에서 자체적으로 WSL 로 포트포워딩을 할 수 있도록 설정해줬다. 



## 사용 방법

1. wsl 에서 필요한 IP와 Port로 서버를 띄운다. 

2. window에서 port 가 동작하는지 확인한다. 

   ```powershell
   netstat -ano | findstr 9999
   ```

3. window 에서 wsl IP와 Port로 포워딩을 해준다. 

   ```bash
   # 형태 
   netsh interface portproxy add v4tov4 listenport=<wsl 서버포트> listenaddress=<wsl 서버IP> connectport=<window에서 포워딩할 포트> connectaddress=<window에서 포워딩할 IP>
   
   # 172.28.7.160:6900 으로 요청이 온것을 포트포워딩 해서 WSL 에서 실행중인 0.0.0.0:9999 로 포워딩해준다.
   netsh interface portproxy add v4tov4 listenport=9999 listenaddress=0.0.0.0 connectport=6900 connectaddress=172.28.7.160
   ```

4. 등록된 포트포워딩 확인

   ```bash
   netsh interface portproxy show all
   ```

   