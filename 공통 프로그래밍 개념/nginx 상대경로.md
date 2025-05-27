

# nginx 상대경로 

---

>

## nginx의 root 경로 기준

- **상대 경로의 기준(base path)**는 “nginx를 실행하는 디렉터리”가 아니라 **nginx의 설정 파일(nginx.conf)의 위치**이다.

1. **절대 경로**
   - `/home/user/www`처럼 `/`로 시작하면 “절대경로”로 인식
   - 언제나 해당 경로로 매핑
2. **상대 경로**
   - `/` 없이 쓰면 "상대 경로"로 인식
   - **기준(base path)**는 `nginx.conf`가 위치한 디렉터리이다. 
   - 즉, `nginx.conf`가 `C:\nginx\conf\nginx.conf`에 있다면 상대경로는 `C:\nginx\conf\`를 기준으로 한다. 

## 주의할점

- winsw 같은 걸로 설정했을 때는 상대경로 기준이 달라질 수 있다. 

- 예를들어, winsw.xml 의 설정이 다음과 같을때 `-p` 옵션으로 인해 상대경로는 ../nginx 부터 시작이다. 

  - 즉, 

  ```conf
  <service>
  	<id>TrafficDigitalTwinNginx_hamyangulsan</id>
  	<name>Traffic Digital Twin Nginx</name>
  	<description>Traffic Digital Twin Metabuild.co.</description>
  
  	<!-- 서비스 실행 (nginx.exe) -->
  	<executable>../nginx/nginx.exe</executable>
  	<!-- <arguments>-p ../nginx -g "env MODE=single;"</arguments> -->
  	<arguments>-p ../nginx</arguments>
  
  	<!-- 서비스 종료 -->
  	<stopexecutable>../nginx/nginx.exe</stopexecutable>
  	<stoparguments>-p ../nginx -s stop</stoparguments>
  
  	<!-- 로그 저장 위치 -->
  	<logpath>../../kleverTwin/logs/nginx/winsw</logpath>
  	<logmode>roll-by-size</logmode>  <!-- 로그 모드 (롤링 방식) -->
  	<log>
  		<sizeThreshold>102400</sizeThreshold> 
  		<keepFiles>3</keepFiles>
  	</log>
  
  </service>
  ```

  
