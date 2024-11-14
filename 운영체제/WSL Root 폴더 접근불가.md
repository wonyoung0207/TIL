# WSL Root 폴더 접근 불가 해결방법

---

>[참고 사이트1](https://velog.io/@gidskql6671/VSCode-WSL%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-%EB%94%94%EB%A0%89%ED%86%A0%EB%A6%AC-%EB%B3%80%EA%B2%BD%EC%8B%9CPermission-denied-%ED%95%B4%EA%B2%B0)

## 발생문제

1. WSL 이용해 Root 폴더 접근시 에러 메시지 표출.

<img src="./images/wsl 파일접근 문제해결.png" width="600">

### 실패한 사용방법

1. WSL 껏다 켜기 
   1. cmd 에서 `wsl --shutdown` 입력 후 다시 가동 
      1. [WSL 껏다 켜기](https://www.sysnet.pe.kr/2/0/13432?pageno=9)
   2. 문제 해결 X
2. 레지스트리 변경 방법
   1. `P9NP,RDPNP,LanmanWorkstation,webclient` 의 순서로 가도록 해야함 
      1. 즉, `P9NP` 가 맨앞으로 와야함 
      2. [레지스트리 변경방법1](https://shilvister.net/it/wsl-%ED%83%90%EC%83%89%EA%B8%B0-%EB%A6%AC%EB%88%85%EC%8A%A4-%EC%95%84%EC%9D%B4%EC%BD%98-%EC%98%A4%EB%A5%98-%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0/)
      3. [레지스트리 변경방법2](https://m.blog.naver.com/collect-data/222930959801)
   2. 문제 해결 X
3. 네트워크 변경 
   1. [네트워크 고급설정 변경1](https://gigglehd.com/gg/soft/14965069)
   2. [네트워크 고급설정 변경2](https://m.blog.naver.com/collect-data/222930959801)
   3. 문제 해결 X

## 성공방법

1. 파일 권한 문제 

   1. 어이없게도 내가 접속하고 사용하고 있는 계정이 wsl 의 Ubuntu 에 있는 `root` 파일에 접근권한이 없었던거 같다. 
   2. 해당 폴더에 권한을 부여했더니 해결되었다. 

2. 권한 설정 

   ```powershell
   sudo chown -R <계정명> <작업폴더>
   ```

   