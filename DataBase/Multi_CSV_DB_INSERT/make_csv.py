import csv
import time
import os

input_file = r"..\result.log"
output_prefix = r"..\TB_ED_HT_EVENT"  # 파일 이름 앞부분만 지정 (자동으로 번호 붙음)

# CSV 헤더
header = [
    "EVENT_ID",
    "EVENT_DT",
    "OCCUR_POSITION",
    "LATITUDE",
    "LONGITUDE",
    "PROC_STATUS",
    "OCCUR_DT",
    "END_DT",
    "EVENT_TYPE_CODE",
    "INFRA_NAME",
    "EVENT_MV_URL",
    "INFRA_ID",
    "EVENT_IMG",
]

# 진행상황 변수
match_count = 0
line_count = 0
file_count = 1
rows_per_file = 100_000  # ✅ CSV 하나당 최대 50만행 (필요시 변경)
start_time = time.time()
last_log_time = start_time

# 새 CSV 파일 여는 함수
def open_new_csv(file_index):
    file_name = f"{output_prefix}_{file_index:03d}.csv"
    fout = open(file_name, "w", newline="", encoding="utf-8-sig")
    writer = csv.writer(fout)
    writer.writerow(header)
    print(f"\n📁 새 CSV 파일 생성: {file_name}")
    return fout, writer

# 첫 파일 열기
fout, writer = open_new_csv(file_count)

with open(input_file, "r", encoding="utf-8", errors="ignore") as fin:
    for line in fin:
        line_count += 1

        # "VALUES" 줄 감지 → 다음 줄에 데이터 있음
        if line.strip().upper().startswith("VALUES"):
            next_line = next(fin, "").strip()  # 다음 줄
            if next_line.startswith("(") and next_line.endswith(")"):
                values_raw = next_line.strip()[1:-1]  # 괄호 제거
                values = [
                    v.strip().strip("'").strip('"')
                    if v.strip().lower() != "null"
                    else ""
                    for v in values_raw.split(",")
                ]
                writer.writerow(values)
                match_count += 1

                # ✅ 일정 개수마다 새로운 CSV로 분할
                if match_count % rows_per_file == 0:
                    fout.close()
                    file_count += 1
                    fout, writer = open_new_csv(file_count)

        # 진행상황 로그
        if line_count % 100_000 == 0:
            elapsed = time.time() - last_log_time
            total_elapsed = time.time() - start_time
            print(
                f"[Progress] {line_count:,} lines read | "
                f"{match_count:,} rows written | "
                f"{elapsed:.2f}s since last log | total {total_elapsed:.1f}s"
            )
            last_log_time = time.time()

# 마지막 파일 닫기
fout.close()

# 완료 로그
total_elapsed = time.time() - start_time
print(f"\n✅ 완료! 총 {match_count:,}개의 행을 {file_count}개의 CSV로 저장했습니다. ({total_elapsed:.1f}s 소요)")
