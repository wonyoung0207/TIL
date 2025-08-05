

# Telegram Bot 이용 Noti 주기

---

>[텔레그램 봇 API Docs](https://core.telegram.org/bots/api)
>
>

## 사용 이유 

- 회사 메신저로 Telegram 을 사용하고 있었다. 
- 그래서 GitLab Runner 와 함께 CI/CD 과정을 Telegram 을 통해 Noti 받고자 구현하였다. 

## 사용방법

##### 1. **Telegram Bot 생성 및 Token 발급**

1. Telegram 검색창 에서 `@BotFather` 검색 → Start
2. `/newbot` 명령어 입력 후 Bot 이름 및 username 입력.
3. **API Token**이 발급된다. 
   - 나중에 `.gitLab-ci.yml` 에서 Telegram Bot 접근시 사용

##### 2. **Telegram Chat ID 찾기**

1. 생성한 Bot을 Telegram에서 검색 → Start 클릭.

2. 브라우저에서 아래 URL로 접속:

   ```bash
   https://api.telegram.org/bot<YOUR_BOT_TOKEN>/getUpdates
   ```

   - → 메시지를 보낸 후 조회하면, 응답에 `chat` → `id` 값이 나옵니다. → **Chat ID 저장**
   - 나중에 `.gitLab-ci.yml` 에서 Telegram Bot 접근시 사용

3. 주의할점!!!

   - Bot이랑 대화를 시작하지 않으면 getUpdates에 아무 것도 뜨지 않기때문에 Telegram에서 방금 만든 Bot을 검색하고 "Start" 버튼 클릭해야한다. 

##### 3. **GitLab CI/CD `.gitlab-ci.yml` 설정**

```yml
stages:
  - build
  - notify

build_job:
  stage: build
  script:
    - echo "빌드 작업 수행중..."
  artifacts:
    when: always

telegram_notify:
  stage: notify
  script:
    - |
      if [ "$CI_JOB_STATUS" == "success" ]; then
        STATUS="✅ SUCCESS"
      else
        STATUS="❌ FAILED"
      fi

      MESSAGE="Pipeline *$CI_PIPELINE_ID* for project *$CI_PROJECT_NAME* has *$STATUS*."
      curl -s -X POST https://api.telegram.org/bot<YOUR_BOT_TOKEN>/sendMessage \
        -d chat_id=<YOUR_CHAT_ID> \
        -d text="$MESSAGE" \
        -d parse_mode=Markdown
  when: always
```

### 주요 포인트

| 항목               | 설명                                |
| ------------------ | ----------------------------------- |
| `<YOUR_BOT_TOKEN>` | BotFather에서 받은 Token 입력       |
| `<YOUR_CHAT_ID>`   | 위에서 찾은 Chat ID 입력            |
| `when: always`     | 성공/실패 여부 상관없이 알림 보내기 |

## Telegram Bot Username 규칙

1. **반드시 `bot`(소문자)으로 끝나야 함**
   - 예: `MetaNotiTestBot`, `ci_cd_test_bot`, `MyCoolBot`
2. **알파벳, 숫자, 밑줄(_)만 사용 가능 (특수문자 불가)**
   - `/`, `-` 같은 특수문자 사용하면 안 됨.
3. **영문 대소문자 구분 없음**
   - username은 항상 소문자 취급됨.
4. **중복된 이름 사용 불가**
   - 이미 존재하는 username은 사용 불가 (ex: `notiBot`은 이미 존재