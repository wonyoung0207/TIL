# opensearch MCP 연결

---

>

1. pip install

   - MCP 서버가 구동될 런타임의 안정성을 보장
   - `python3-full` 패키지를 설치함으로써 표준 라이브러리의 누락 방지

   ```bash
   sudo apt-get install -y python3-pip python3-venv python3-full
   ```

2. python venv install 

   1. venv는 “MCP 서버 전용 디렉터리”에서 만들고, MCP 서버는 그 디렉터리에서 실행한다

      ```bash
      # 현재 디렉터리에 venv/ 생성
      python3 -m venv venv
      ```

   2. venv 활성화

      ```bash
      source venv/bin/activate
      
      # 성공시 프롬프트 예시 (이 상태에서 mcp server install 진행 ) -> 패키지는 venv 안에만 설치됨
      (venv) user@host$
      ```

   3. 패키지 삭제

      ```bash
      pip uninstall opensearch-mcp-server-py
      ```

   4. 확인

      ```bash
      pip list | grep opensearch
      ```

   5. venv 비활성화

      ```bash
      deactivate
      ```

3. mcp 서버 로컬 다운로드

   - [공식 opensearch MCP Github](https://github.com/opensearch-project/opensearch-mcp-server-py)

   ```bash
   # venv 활성화 상태에서 실행하기 
   pip install opensearch-mcp-server-py
   opensearch-mcp-server
   ```