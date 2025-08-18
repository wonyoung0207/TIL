# 다중 CSV파일 DB import 방법 정리

---



## 학습 이유 



## 사용 방법

- 로컬에서 `LOAD DATA [LOCAL] INFILE` 사용 
  - `LOAD DATA [LOCAL] INFILE` :  **MySQL에서 CSV 같은 텍스트 파일을 초고속으로 테이블에 적재하기 위한 명령어**

## `LOAD DATA [LOCAL] INFILE` 

- 파일을 **클라이언트(DBeaver, Python 등)** 가 읽어서 DB 서버로 전송
  - 클라이언트 PC에 있는 CSV도 바로 올릴 수 있음
  - 단, 서버 `local_infile=ON`, 클라이언트 드라이버(`allowLocalInfile=true`) 설정 필요
- 즉, `LOAD DATA [LOCAL] INFILE` 는 CSV 파일을 SQL INSERT로 바꾸지 않고, MySQL이 직접 읽어서 테이블에 넣는 “대량 적재 전용 명령” 이다. 

##### 성능

- `INSERT` 문 수백만 번 실행하는 것보다 수십~수백 배 빠름
- CSV → SQL 변환 필요 없음 (DB엔진이 직접 읽음)
- 수백 MB~수 GB CSV도 수 분 내 적재 가능

##### 보안 설정

- MySQL 8.0 이상은 `local_infile=OFF`가 기본 → 켜야 함

```bash
SHOW VARIABLES LIKE 'local_infile';
SET GLOBAL local_infile = ON;
```

##### 구조

```bash
LOAD DATA [LOCAL] INFILE '파일경로'
[REPLACE | IGNORE]
INTO TABLE 테이블명
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ',' ENCLOSED BY '"' ESCAPED BY '\\'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(컬럼목록 또는 @변수목록)
SET 컬럼명 = 표현식, ...
```

##### 주요 옵션 

1. `FIELDS TERMINATED BY ','`
    → 구분자 지정 (CSV는 보통 `,`)
2. `ENCLOSED BY '"'`
    → 값이 `"텍스트"`로 감싸져 있을 때 제거
3. `ESCAPED BY '\\'`
    → 따옴표 안에서 `\"` 같은 이스케이프 처리
4. `LINES TERMINATED BY '\n'`
    → 줄바꿈 문자 지정 (Linux/Unix `\n`, Windows `\r\n`)
5. `IGNORE 1 LINES`
    → CSV 헤더 스킵

## 예시 코드

##### python 설치 

```
python -m ensurepip --default-pip
python -m pip --version
python -m ensurepip --upgrade
python -m pip install pymysql
python -m pip install "cryptography"
```



```py
#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
폴더 안의 CSV들을 MySQL 테이블로 'LOAD DATA LOCAL INFILE'로 일괄 적재.
- 성공 파일: log/success.log (append)
- 실패 파일: log/error.log   (append)
- 줄바꿈 자동 감지(\r\n / \n)
- 중복키 정책: --on-duplicate ignore|replace|error
- 첫 실패 시 즉시 중단: --stop-on-error
- 특정 파일 이전은 스킵: --start-after <파일명 또는 와일드카드>
"""

import argparse, glob, os, sys
import re  
from getpass import getpass
from pathlib import Path
from fnmatch import fnmatch


def detect_line_terminator(path: str) -> str:
    with open(path, "rb") as f:
        chunk = f.read(2048)
    return r"\r\n" if b"\r\n" in chunk else r"\n"

def natural_key(p):
    name = Path(p).name
    return [int(s) if s.isdigit() else s.lower() for s in re.split(r'(\d+)', name)]

def build_sql(file_path: str, db: str, table: str, line_term: str, on_dup: str) -> str:
    infile = file_path.replace("\\", "\\\\")
    dup_kw = ""
    if on_dup == "ignore":
        dup_kw = "IGNORE"
    elif on_dup == "replace":
        dup_kw = "REPLACE"

    return f"""LOAD DATA LOCAL INFILE '{infile}' {dup_kw}
INTO TABLE {db}.{table}
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '{line_term}'
IGNORE 1 LINES
(@EVENT_ID,@EVENT_DT,@OCCUR_POSITION,@LATITUDE,@LONGITUDE,@PROC_STATUS,
 @OCCUR_DT,@END_DT,@EVENT_TYPE_CODE,@INFRA_NAME,@EVENT_MV_URL,@INFRA_ID,@EVENT_IMG)
SET
  EVENT_ID        = @EVENT_ID,
  EVENT_DT        = @EVENT_DT,
  OCCUR_POSITION  = @OCCUR_POSITION,
  LATITUDE        = NULLIF(@LATITUDE,''),
  LONGITUDE       = NULLIF(@LONGITUDE,''),
  PROC_STATUS     = NULLIF(@PROC_STATUS,''),
  OCCUR_DT        = @OCCUR_DT,
  END_DT          = NULLIF(@END_DT,''),
  EVENT_TYPE_CODE = @EVENT_TYPE_CODE,
  INFRA_NAME      = @INFRA_NAME,
  EVENT_MV_URL    = NULLIF(@EVENT_MV_URL,''),
  INFRA_ID        = NULLIF(@INFRA_ID,''),
  EVENT_IMG       = NULLIF(@EVENT_IMG,'');"""

def main():
    ap = argparse.ArgumentParser(description="Bulk import CSVs into MySQL with LOAD DATA LOCAL INFILE")
    ap.add_argument("--dir", required=True, help="CSV 폴더")
    ap.add_argument("--pattern", default="*.csv", help="기본: *.csv")
    ap.add_argument("--log-dir", default="./log", help="로그 폴더 (기본: ./log)")
    ap.add_argument("--host", required=True)
    ap.add_argument("--port", type=int, default=3306)
    ap.add_argument("--user", required=True)
    ap.add_argument("--password", help="비밀번호 (지정 안 하면 실행 중 입력)")
    ap.add_argument("--database", required=True)
    ap.add_argument("--table", required=True)
    ap.add_argument("--on-duplicate", choices=["error","ignore","replace"], default="error")
    ap.add_argument("--stop-on-error", action="store_true", help="첫 실패에서 즉시 중단")
    ap.add_argument("--start-after", help="지정한 파일(이름 또는 와일드카드) 이전은 스킵하고 이후부터 처리")
    args = ap.parse_args()

    if args.password is None:
        args.password = getpass("MySQL password: ")

    # 로그 준비 (append)
    run_name = Path(args.dir).name
    log_dir = Path(args.log_dir)
    log_dir.mkdir(parents=True, exist_ok=True)
    # success_log = log_dir / "success.log"
    # error_log   = log_dir / "error.log"
    success_log = log_dir / f"success_{run_name}.log"
    error_log   = log_dir / f"error_{run_name}.log"

    # 파일 수집 + 정렬
    files = sorted(glob.glob(str(Path(args.dir) / args.pattern)), key=natural_key)
    if not files:
        print(f"[ERR] No CSVs found: {Path(args.dir)}\\{args.pattern}", file=sys.stderr)
        sys.exit(2)

    # --start-after 처리 (이름은 basename 기준, 대소문자 무시, 와일드카드 지원)
    if args.start_after:
        target = args.start_after
        last_match_index = -1
        for i, f in enumerate(files):
            name = Path(f).name
            if ("*" in target or "?" in target):
                if fnmatch(name.lower(), target.lower()):
                    last_match_index = i
            else:
                if name.lower() == target.lower():
                    last_match_index = i
        if last_match_index >= 0:
            files = files[last_match_index + 1:]  # 해당 파일 '이후'부터
            if not files:
                print(f"[INFO] --start-after={target} 이후에 처리할 파일이 없습니다.", file=sys.stderr)
        else:
            print(f"[WARN] --start-after 대상({target})을 목록에서 찾지 못했습니다. 전체 파일을 처리합니다.", file=sys.stderr)

    try:
        import pymysql
    except ImportError:
        print("[ERR] PyMySQL 미설치. 먼저: pip install pymysql", file=sys.stderr)
        sys.exit(3)

    conn = pymysql.connect(
        host=args.host, port=args.port, user=args.user, password=args.password,
        database=args.database, charset="utf8mb4", autocommit=True, local_infile=1
    )

    ok = fail = 0
    try:
        with conn.cursor() as cur:
            # 실행 배치 헤더 기록
            with success_log.open("a", encoding="utf-8") as sf, error_log.open("a", encoding="utf-8") as ef:
                sf.write(f"\n=== 실행 시작: dir={args.dir}"
                         f"{' | start-after='+args.start_after if args.start_after else ''} ===\n")
                ef.write(f"\n=== 실행 시작: dir={args.dir}"
                         f"{' | start-after='+args.start_after if args.start_after else ''} ===\n")

            for f in files:
                abs_path = str(Path(f).resolve())
                try:
                    lt = detect_line_terminator(abs_path)
                    sql = build_sql(abs_path, args.database, args.table, lt, args.on_duplicate)
                    cur.execute(sql)
                    print(f"[OK] {os.path.basename(f)} ({lt})")
                    with success_log.open("a", encoding="utf-8") as sf:
                        sf.write(f"{abs_path} 성공\n")
                    ok += 1

                except Exception as e:
                    print(f"[FAIL] {os.path.basename(f)} :: {e}", file=sys.stderr)
                    with error_log.open("a", encoding="utf-8") as ef:
                        ef.write(f"{abs_path} :: {e}\n")
                    fail += 1

                    if args.stop_on_error:
                        print(f"중단: 실패 발생 → {abs_path}", file=sys.stderr)
                        print(f"Done. OK={ok}, FAIL={fail}")
                        sys.exit(1)
    finally:
        conn.close()

    print(f"Done. OK={ok}, FAIL={fail}")
    sys.exit(0 if fail == 0 else 1)

if __name__ == "__main__":
    main()

```

```py
# 실행 명령어 (끝까지 돌기)
python ./multi_csv_import.py \
  --dir "./202306" \
  --log-dir "./log" \
  --host [Server IP] --port 3306 \
  --user user5g \
  --password "[UserPWD]" \
  --database daegu5g_dev --table TB_ED_HT_EVENT_ALL_PART \
  --on-duplicate ignore \
  --start-after "TB_ED_HT_EVENT_202508061754_400.csv"


# 에러나면 멈추기 
python ./multi_csv_import.py \
  --dir "./202306" --log-dir "./log" \
  --host [Server IP] --port 3306 \
  --user user5g --password "[UserPWD]" \
  --database daegu5g_dev --table TB_ED_HT_EVENT_ALL_PART \
  --on-duplicate ignore \
  --stop-on-error \
  # --start-after "TB_ED_HT_EVENT_202508061754_401.csv" # 시작파일 설정 (sorted 되어있어서 가능)

```

