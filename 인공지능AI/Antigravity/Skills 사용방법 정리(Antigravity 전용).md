# Skills 사용방법 정리(Antigravity 전용)

---

> [Github Anthropics Skills](https://github.com/anthropics/skills)

## Skills 실행 매커니즘

- 온디맨드 로딩
  - System Prompt처럼 항상 로딩되는 것이 아니라, 관련성이 판단될 때만 컨텍스트에 주입

## Skills 폴더 구조

- 각 스킬은 다음과 같은 구조로 된 개별 폴더에 저장됩니다.
- Skill은 본질적으로 **폴더 기반 패키지**다. **필수 파일인 SKILL.md**와 **선택적 리소스**(스크립트, 템플릿, 참조 문서)로 구성된다.

```
.agent/skills/
├── skill-name/              # Individual skill folder
│   ├── SKILL.md             # Main skill definition (required)
│   ├── scripts/             # Helper scripts (optional)
│   ├── examples/            # Usage examples (optional)
│   └── resources/           # Templates & resources (optional)
```

**핵심:** `SKILL.md`. 만 필수 항목 .나머지는 모두 선택 사항

---

## skill.md 파일 구조 

- SKILL.md 파일은 두 부분으로 나뉜다. 
  1. YAML 프론트매터(메타데이터)
  2. 마크다운 본문(상세 지시사항)
- 여기서 description 필드가 가장 중요하다. 
  - 에이전트는 대화 시작 시 모든 Skill의 name과 description만 읽는다. 
  - 현재 작업이 특정 Skill과 관련 있다고 판단하면 그때서야 전체 SKILL.md를 로드한다. 
  - 따라서 description은 AI가 이해하기 쉽게, 트리거 키워드를 명확히 포함해야 한다.

```markdown
---
name: my-skill-name
description: "What this skill does"
---

# Skill Title

## Overview
[What this skill does] 

## When to Use
- Use when [scenario]

## Instructions
[Step-by-step guide]

## Examples
[Code examples] 
```

---

## Skill 저장 위치

- Antigravity는 두 가지 스코프의 Skill을 지원한다.
  - Workspace 범위
  - global 범위

| 위치                            | 스코프    | 용도                                             |
| ------------------------------- | --------- | ------------------------------------------------ |
| `<project>/.agent/skills/`      | Workspace | 프로젝트별 워크플로우, 팀 공유용 (Git 커밋 가능) |
| `~/.gemini/antigravity/skills/` | Global    | 개인 유틸리티, 모든 프로젝트에서 사용            |

- 새 Skill은 먼저 프로젝트 레벨(.agent/skills/)에서 테스트한다. 
- 여러 프로젝트에서 유용하다고 검증되면 글로벌 폴더로 이동하거나 심볼릭 링크를 건다. 
- 이렇게 하면 실험 과정에서 다른 프로젝트를 오염시키지 않는다.

---

## Skill 사용 방법

##### 1단계: 스킬 생성

- 현재 프로젝트의 root 경로에 `.agent/skills/` 폴더를 생성한다. 

```bash
# skills 별 폴더생성 
mkdir -p .agent/skills/[skills name]

# skills 파일 생성 
touch .agent/skills/git-commit-formatter/SKILL.md
```

### 2단계: AI 채팅에서 스킬을 호출

- `@`해당 기호 뒤에 스킬 이름을 입력
- 예시 : `@brainstorming`
  - 코딩 전에 브레인스토밍과 디자인 작업을 먼저 진행하는 skills 

```
@brainstorming help me design a todo app
```

또는

```
@stripe-integration add payment processing to my app
```

