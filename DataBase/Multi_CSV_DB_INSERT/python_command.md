```bash
# 실행 명령어 (끝까지 돌기)
python ./multi_csv_import.py \
  --dir "./202508_missing_data" \
  --log-dir "./log" \
  --host [DB IP] --port [DB port] \
  --user testUser \
  --password "[DB Password]" \
  --database dbBase --table TableName \
  --on-duplicate ignore \
#   --start-after "TB_ED_HT_EVENT_202508061754_400.csv"


# 에러나면 멈추기 
python ./multi_csv_import.py \
  --dir "./[폴더명]" --log-dir "./log" \
  --host [DB IP] --port [DB port] \
  --user [DB user Name] --password "[DB Password]" \
  --database [DB Name] --table [Table Name] \
  --on-duplicate ignore \
  --stop-on-error \
  # --start-after "TB_ED_HT_EVENT_202508061754_401.csv"

```

## 주의할점

###### 1. 서버의 `LOAD DATA LOCAL INFILE` 기능이 켜져있어야 가능함

```sql
SET GLOBAL local_infile = 1;

SHOW VARIABLES LIKE 'local_infile'; -- Value 가 ON 이면 정상적
```

##### 2. python 사용 가능해야함 
