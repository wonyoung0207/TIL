# MCP(Model Context Protocol) 개념정리 

---

>[MCP 정의 Docs](https://modelcontextprotocol.io/docs/getting-started/intro)

## MCP란? 

- LLM(예: Claude)이 외부 도구/데이터/서비스에 표준 방식으로 접근할 수 있게 해주는 프로토콜

## 구조

- **MCP 클라이언트**
  - Claude Desktop, Claude Code, VSCode 플러그인 같은 쪽
  - 사용자의 요청 ↔ MCP 서버 호출을 중간에서 연결해줌
- **MCP 서버**
  - 네가 만드는 서버 (Node, Python 등)
  - DB, 파일시스템, 사내 API 같은 실제 자원에 접근
  - 클라이언트에 **tools / resources / prompts**를 제공

## 핵심 개념

- Tools
  - LLM이 호출할 수 있는 “함수”
  - 예: `run_sql`, `get_user(id)`, `send_slack_message` 등
- Resources
  - 읽기 전용 데이터/파일/문서
  - 예: 설정 파일, README, 문서, 로그 등
- Prompts
  - 미리 정의된 프롬프트 템플릿
  - 자주 쓰는 작업을 버튼처럼 재사용

## 사용 이유 

- LLM에게 **직접 비밀번호나 DB 주소를 주지 않고도** 실제 시스템에 안전하게 접근시키기 위해
- 플러그인/툴 시스템을 **표준화**해서 한 번 만든 MCP 서버를 여러 클라이언트에서 재사용 가능하게 하려고

## 정리

1. MCP = “LLM용 플러그인 표준 프로토콜”
2. Claude는 MCP **클라이언트 역할**
3. DB나 사내 시스템을 감싸는 **MCP 서버를 구현**해서 연결
   - 보안·권한·로그 관리를 MCP 서버에서 통제 가능