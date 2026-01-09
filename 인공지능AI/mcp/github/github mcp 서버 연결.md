# Github MCP 서버 연결

---

>[공식 Github mcp server 깃주소](https://github.com/github/github-mcp-server)

## 방법

- antigravity agent 이용 mcp 접근 

- Github Token 만 있으면 쉽게 접근 가능

- MCP Store 에서 Github 찾아서 Github Token 넣고 install 하면 연결 끝

  <img src="./images/local_github_mcp_server_이용개인레포접근2.png" width="500">

## mcp server 설정 

- antigravity 에 `mcp_config.json` 에 mcp 설정이 가능하다. 
- 다음 설정은 docker image를 이용해 mcp server container 를 실행해 접근하는 방식이다. 
- 접근시 내 개인 GIThub 레포지토리에 접근해서 데이터 가져와 달라고 하면 Token을 사용해 접근이 되는것을 확인할 수 있다. 

```json
 "github-mcp-server": {
      "command": "docker",
      "args": [
        "run",
        "-i",
        "--rm",
        "-e",
        "GITHUB_PERSONAL_ACCESS_TOKEN",
        "ghcr.io/github/github-mcp-server"
      ],
      "env": {
        "GITHUB_PERSONAL_ACCESS_TOKEN": "token"
      }
    }
```

<img src="./images/local_github_mcp_server_이용개인레포접근.png" width="300">

