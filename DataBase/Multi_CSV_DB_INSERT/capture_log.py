input_file = r"..\head_history_tmp.log"
# input_file = r"..\test_20000.log"
output_file = r"..\result.log"

keyword = "INSERT INTO TB_ED_HT_EVENT"

capture = False
buffer = []
line_count = 0
found_count = 0
line_number = 0
line_202510_count = 0
line_202508_count = 0
line_max_count = 1000000

with open(input_file, "r", encoding="utf-8", errors="ignore") as fin, \
     open(output_file, "w", encoding="utf-8") as fout:
    
    for line in fin:
        line_count += 1
        if line_count % 1_000_000 == 0:
            print(f"Processed {line_count:,} lines... found {found_count:,} INSERTs")

        # INSERT 문 시작 감지
        if keyword in line.strip():
            # if not line.startswith("2025-09"):
            #     continue

            capture = True
            buffer = [keyword + "\n"]  # INSERT 문만 버퍼에 추가
            # buffer = [keyword + "\n"]  # INSERT 문만 버퍼에 추가
            found_count += 1
            line_number = 0     # 새 블록 시작 시 줄 번호 초기화
            continue

        # INSERT 블록 내용 처리
        if capture:
            if "202510" in line:
                line_202510_count += 1
                capture = False
                buffer = []
                continue
            if "202508" in line:
                line_202508_count += 1
                capture = False
                buffer = []
                continue

            line_number += 1           # ✅ 줄 번호 먼저 증가
            buffer.append(line)

            # ✅ 이제 line_number == 3일 때 정확히 4번째 줄에 해당
            if line_number == 3:
                fout.writelines(buffer)
                capture = False
                buffer = []
                # if line_max_count < found_count:
                #     break    
                continue


print(f"\n✅ Done! Processed {line_count:,} lines, found {found_count:,} INSERT blocks., 202510 lines: {line_202510_count}, 202508 lines: {line_202508_count}")
