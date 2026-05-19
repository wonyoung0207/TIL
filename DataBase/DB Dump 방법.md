## DB

- 특정 IP 의 특정 DB 를 Dump 파일로 변경 ( 로컬 특정 경로에 dump 파일 생성)

```cmd
& "C:\app\infra\db\postgreSQL\bin\pg_dump.exe" -U meta -h 172.28.7.160 -p 5432 -Fc -d Gangwon -f "C:\DB_backup\Gangwon.dump"
```

- dump 파일 확인

```cmd
& "C:\app\infra\db\postgreSQL\bin\pg_restore.exe" -l "C:\DB_backup\Gangwon.dump"
```

- DB 생성 

```cmd
& "C:\app\infra\db\postgreSQL\bin\createdb.exe" -U meta -h localhost -p 5432 Gangwon
```

- DB 삭제 

```cmd
& "C:\app\infra\db\postgreSQL\bin\dropdb.exe" -U meta -h 127.0.0.1 -p 5432 Gangwon
```

- DB Dump 데이터 삽입

```cmd
& "C:\app\infra\db\postgreSQL\bin\pg_restore.exe" --no-owner -U meta -h 127.0.0.1 -p 5432 -d Gangwon "C:\DB_backup\Gangwon.dump"
```

## Dump 파일 생성 Shell 

```sh
$pgDump = "C:\app\infra\db\postgreSQL\bin\pg_dump.exe"
$backupDir = "C:\DB_backup"
$sourceHost = "127.0.0.1"
$port = "5432"
$user = "admin"

New-Item -ItemType Directory -Path $backupDir -Force

$dbList = @(
    "DBList1",
    "DBList2",
    ...
)

foreach ($db in $dbList) {
    Write-Host "Dump start: $db"

    & $pgDump `
        -U $user `
        -h $sourceHost `
        -p $port `
        -Fc `
        -d $db `
        -f "$backupDir\$db.dump"

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Dump success: $db" -ForegroundColor Green
    } else {
        Write-Host "Dump failed: $db" -ForegroundColor Red
    }
}
```

```sh
$pgBin = "C:\app\infra\db\postgreSQL\bin"
$createdb = "$pgBin\createdb.exe"
$pgRestore = "$pgBin\pg_restore.exe"
$backupDir = "C:\DB_backup"

# 대상 DB 서버 정보
$targetHost = "127.0.0.1"
$port = "5432"
$user = "admin"

$dbList = @(
    "DBList1",
    "DBList2",
    ...
)

foreach ($db in $dbList) {
    $dumpFile = "$backupDir\$db.dump"

    Write-Host "========================================"
    Write-Host "Start restore DB: $db"
    Write-Host "Dump file: $dumpFile"

    if (!(Test-Path $dumpFile)) {
        Write-Host "Dump file not found: $dumpFile" -ForegroundColor Red
        continue
    }

    Write-Host "Creating database: $db"

    & $createdb `
        -U $user `
        -h $targetHost `
        -p $port `
        $db

    if ($LASTEXITCODE -ne 0) {
        Write-Host "createdb failed or DB already exists: $db" -ForegroundColor Yellow
        Write-Host "Skip restore for safety. If you want overwrite, drop DB first." -ForegroundColor Yellow
        continue
    }

    Write-Host "Restoring database: $db"

    & $pgRestore `
        --no-owner `
        --verbose `
        -U $user `
        -h $targetHost `
        -p $port `
        -d $db `
        $dumpFile

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Restore success: $db" -ForegroundColor Green
    } else {
        Write-Host "Restore failed or completed with warnings: $db" -ForegroundColor Red
    }
}
```

