# GitLab 사전 정의 변수 정리 

---

>[GitLab 사전 정의 변수 Docs](https://docs.gitlab.com/ci/variables/predefined_variables/)

| 변수명                                 | 설명                                                         |
| -------------------------------------- | ------------------------------------------------------------ |
| `$CI_PIPELINE_SOURCE`                  | 파이프라인의 트리거 원인 (예: `push`, `merge_request_event`, `web`, `schedule` 등) |
| `$CI_COMMIT_BRANCH`                    | 현재 커밋이 속한 브랜치 이름                                 |
| `$CI_COMMIT_REF_NAME`                  | 현재 리포지토리에서 체크아웃된 ref 이름                      |
| `$CI_MERGE_REQUEST_ID`                 | MR이 존재할 경우 MR ID                                       |
| `$CI_JOB_STAGE`                        | 현재 job의 stage 이름                                        |
| `$CI_COMMIT_SHA`                       | 커밋의 SHA 값                                                |
| `$CI_PROJECT_PATH`                     | `<namespace>/<project-name>` 형식의 경로                     |
| `CI_MERGE_REQUEST_ID`                  | MR ID (MR일 때만 존재)                                       |
| `$CI_MERGE_REQUEST_SOURCE_BRANCH_NAME` | MR의 소스 브랜치                                             |
| `$CI_MERGE_REQUEST_TARGET_BRANCH_NAME` | MR의 대상 브랜치                                             |

## `$CI_PIPELINE_SOURCE` 값 정리 (Merge Request 관련)

| 상황                                      | 값 (`$CI_PIPELINE_SOURCE`) | 설명                                                         |
| ----------------------------------------- | -------------------------- | ------------------------------------------------------------ |
| MR 생성 (예: feature → dev)               | `"merge_request_event"`    | Merge Request 파이프라인입니다. `CI_MERGE_REQUEST_*` 변수 사용 가능 |
| MR 승인 후 병합 (Approve + Merge 클릭 시) | `"push"`                   | 병합 결과가 `dev` 브랜치로 푸시되며 실행됨                   |
| dev 브랜치에 직접 푸시                    | `"push"`                   | 단순 푸시로 파이프라인 실행됨                                |



------

## 요약: MR 관련 파이프라인 흐름

1. **MR 생성/수정 시** → `CI_PIPELINE_SOURCE == "merge_request_event"`
   - CI 목적의 파이프라인 (빌드/테스트)
   - 아직 병합되지 않은 코드 기준
2. **MR이 Approve되고 Merge 버튼을 눌러 병합 완료 시** → `CI_PIPELINE_SOURCE == "push"`
   - 대상 브랜치(예: `dev`)로 코드가 실제로 반영되었기 때문에
   - 이 시점의 파이프라인은 CD에 해당