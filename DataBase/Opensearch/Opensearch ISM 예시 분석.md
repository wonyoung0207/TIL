## 정책 예시

1. 데이터가 들어오면 HOT 단계에서 쓰기·롤오버를 수행하고,
2. 롤오버된 이전 인덱스는 10분 후 Ultrawarm 노드로 이동시켜
3. 읽기 전용 + 최적화 상태로 장기 보관하는 ISM 정책

```json
{
    "policy_id": "idx_t.vehicle-rcv-wireless-asn-pvd-policy",
    "description": "idx_t.vehicle-rcv-wireless-asn-pvd-policy 자동 Rollover 및 Warm 단계 이동",
    "last_updated_time": 1746147636730,
    "schema_version": 21,
    "error_notification": null,
    "default_state": "starting_line",
    "states": [
        {
            "name": "starting_line",
            "actions": [],
            "transitions": [
                {
                    "state_name": "hot",
                    "conditions": {
                        "min_doc_count": 1
                    }
                }
            ]
        },
        {
            "name": "hot",
            "actions": [
                {
                    "retry": {
                        "count": 3,
                        "backoff": "exponential",
                        "delay": "1m"
                    },
                    "rollover": {
                        "min_size": "40gb",
                        "min_doc_count": 50000000,
                        "copy_alias": false
                    }
                },
                {
                    "retry": {
                        "count": 3,
                        "backoff": "exponential",
                        "delay": "1m"
                    },
                    "allocation": {
                        "require": {
                            "temp": "hot"
                        },
                        "include": {},
                        "exclude": {},
                        "wait_for": true
                    }
                }
            ],
            "transitions": [
                {
                    "state_name": "ultrawarm",
                    "conditions": {
                        "min_rollover_age": "10m"
                    }
                }
            ]
        },
        {
            "name": "ultrawarm",
            "actions": [
                {
                    "retry": {
                        "count": 3,
                        "backoff": "exponential",
                        "delay": "1m"
                    },
                    "replica_count": {
                        "number_of_replicas": 0
                    }
                },
                {
                    "retry": {
                        "count": 3,
                        "backoff": "exponential",
                        "delay": "1m"
                    },
                    "force_merge": {
                        "max_num_segments": 1
                    }
                },
                {
                    "retry": {
                        "count": 3,
                        "backoff": "exponential",
                        "delay": "1m"
                    },
                    "read_only": {}
                },
                {
                    "retry": {
                        "count": 3,
                        "backoff": "exponential",
                        "delay": "1m"
                    },
                    "allocation": {
                        "require": {
                            "temp": "ultrawarm"
                        },
                        "include": {},
                        "exclude": {},
                        "wait_for": true
                    }
                }
            ],
            "transitions": []
        }
    ],
    "ism_template": [
        {
            "index_patterns": [
                "idx_t.vehicle-rcv-wireless-asn-pvd-*"
            ],
            "priority": 200,
            "last_updated_time": 1746147636730
        }
    ]
}
```

## 상태별 의미 정리

##### 1. starting_line (초기 상태)

- 인덱스 생성 직후 상태
- 아직 데이터가 없으면 아무 작업도 하지 않음
- **문서 1건 이상 들어오면 → hot으로 전환**

##### 2. hot (실시간 쓰기 단계)

- 역할
  - 데이터 수집
  - 실시간 검색
  - 롤오버 감시
- 정책
  - Rollover 조건
    - 인덱스 크기 ≥ 40GB
    - 또는 문서 수 ≥ 5천만 건
  - Hot 노드에만 배치
    - `temp=hot` 속성 노드
- 전환 조건
  - Rollover 발생 후 10분 경과 → ultrawarm 상태로 이동

##### 3. ultrawarm (보관 단계)

- 역할
  - 과거 데이터 장기 보관
  - 읽기 전용
- 정책
  - Replica 제거 (디스크 절약)
  - Segment 1개로 병합 (force merge)
  - 쓰기 차단 (read-only)
  - Ultrawarm 노드로 이동
    - `temp=ultrawarm`
- 전환
  - 없음 (최종 상태)

---

## 용어 정리 

1. `backoff: exponential`
   - 재시도할수록 대기 시간이 지수적으로 증가하는 재시도 전략