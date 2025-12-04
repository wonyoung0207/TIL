# MCP 서버 연결 방법

---

>[갓대희 - MCP 연결방법](https://goddaehee.tistory.com/391)

## 설치 방법

1. MCP 서버 설치
2. 클로드 코드 설정에 MCP 설정 추가 
   1. 리눅스
      1. `~/.config/claude/code.json` 경로에서 서버 등록
      2. `~/.claude/settings.json` 또는 `~/.claude.json`

   2. window 
      1. `%USERPROFILE%\.config\claude\code.json`

3. claude code 실행 → 자동 감지

- MCP 서버는 **독립 실행형 작은 프로그램(서버)** 형태임
- Claude Code(CLI)는  **이 서버에 연결만** 함

---

## 1. Context7

- 개발 문서를 가져와 ai 가 더 이해를 잘 할 수있도록 해주는 mcp 

##### 서버 설치 

```cmd
# 원격 서버 추가
claude mcp add --transport http context7 https://mcp.context7.com/mcp
```

##### 서버연결

```json
{
  "mcpServers": {
    "context7": {
      "command": "npx",
      "args": [
        "-y",
        "@smithery/cli@latest",
        "run",
        "@upstash/context7-mcp",
        "--key",
        "YOUR_CONTEXT7_KEY"
      ]
    }
  }
}
```

---

## 2. postgreSQL

- 접근 제어(권한 제어) 설정 (DB 보안 위해 다음처럼 계정 분리 권장)
  1. Postgres에서 **읽기 전용 계정** 생성
  2. 해당 계정으로 MCP connectionString 구성

##### 서버 설치 

```cmd
npm install -g mcp-server-postgres

# or @smithery 이용 
npx -y @smithery/cli@latest run mcp-server-postgres
```

##### 서버연결

```json
// Claude Code가 MCP 서버를 자동 실행
{
  "mcpServers": {
    "postgres-db": {
      "command": "mcp-server-postgres",
      "args": [
        "--connectionString",
        "postgresql://user:password@localhost:5432/mydb"
      ]
    }
  }
}
```

---

## 3. github

##### 서버 설치 

```cmd
npm install -g @modelcontextprotocol/server-github
```

##### 서버연결

```json
{
  "mcpServers": {
    "github": {
      "command": "mcp-github",
      "args": [
        "--token", "GITHUB_PERSONAL_ACCESS_TOKEN"
      ]
    }
  }
}
```

---

## 4. notion

##### 서버설치

```cmd
npm install -g mcp-server-notion
```

##### 서버연결

```json
{
  "mcpServers": {
    "notion": {
      "command": "mcp-server-notion",
      "args": [
        "--token",
        "NOTION_SECRET"
      ]
    }
  }
}
```

