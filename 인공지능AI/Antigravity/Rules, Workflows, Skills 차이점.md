- Antigravity에는 Skills 외에도 Rules와 Workflows가 있다. 
- 혼동하기 쉬우니 차이를 명확히 해야함

| 구분      | 로드 시점                  | 용도                          | 저장 위치                                         |
| --------- | -------------------------- | ----------------------------- | ------------------------------------------------- |
| Rules     | 항상 (시스템 프롬프트처럼) | 코드 스타일, 필수 컨벤션 강제 | `.agent/rules/`, `~/.gemini/GEMINI.md`            |
| Workflows | 사용자가 `/명령어`로 호출  | 저장된 프롬프트 시퀀스        | `.agent/workflows/`                               |
| Skills    | 에이전트가 관련성 판단 시  | 재사용 가능한 전문 지식       | `.agent/skills/`, `~/.gemini/antigravity/skills/` |

- Rules는 "항상 TypeScript 사용", "docstring 필수"처럼 예외 없이 적용되어야 하는 규칙에 적합하다.
- Workflows는 "새 기능 개발" 같은 멀티스텝 작업을 `/new-feature`로 한 번에 실행할 때 유용하다. Skills는 에이전트가 스스로 판단해서 적용하는 '주문형 전문성'이다.