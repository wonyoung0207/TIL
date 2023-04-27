# Docker 이용해서 MS SQL 설치 

---

> [설치 방법 참고 사이트](https://oingdaddy.tistory.com/285)

### 1. docker 로 이미지 다운 

   ```commend
   docker pull mcr.microsoft.com/mssql/server:2019-latest
   ```

### 2. 다운받은 docker 이미지 실행  

   ```commend
   // 1. 형태
   docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=<사용자설정 PW>" -p 1433:1433 --name <사용자 지정 이름> -h <컨테이너 호스트 이름> -d mcr.microsoft.com/mssql/server:2019-latest
   
   // 2. 예시 
   docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=test1234" -p 1433:1433 --name mssql -h mssql -d mcr.microsoft.com/mssql/server:2019-latest
   ```

   | 매개변수                                      | Description                                                  |
   | --------------------------------------------- | ------------------------------------------------------------ |
   | -e "ACCEPT_EULA=Y"                            | 최종 사용자 사용권 계약 수락을 확인하기 위해 ACCEPT_EULA 변수를 어떤 값에 설정. SQL Server 이미지에 대한 설정을 해야 한다. |
   | -e "SA_PASSWORD=<사용자설정 PW>"              | **8자 이상이고 SQL Server 암호 요구사항을 충족하는 암호를 필수로 한다.** |
   | -p 1433:1433                                  | 호스트 환경의 TCP 포트(첫 번째 값)를 컨테이너의 TCP 포트(두 번째 값)로 매핑한다. |
   | --name <사용자 지정 이름>                     | 컨테이너에 대해 임의로 생성된 이름보다는 사용자 지정 이름을 지정해준다. 둘 이상의 컨테이너를 실행하는 경우 이 동일한 이름을 다시 사용할 수 없다. |
   | -h <컨테이너 호스트 이름>                     | 컨테이너 호스트 이름을 명시적으로 설정하는 데 사용. 지정하지 않으면 임의로 생성된 시스템 GUID인 컨테이너 ID가 기본값으로 사용 |
   | -d mcr.microsoft.com/mssql/server:2019-latest | SQL Server 2019 Ubuntu Linux 컨테이너 이미지                 |

### 3. cmd에서 docker에 다운받은 Container( 여기선 mssql ) 에 접속

   ```coomend
   1. 형태
   docker exec -it <mssql-server> "bash"
   
   2. 예시 
   docker exec -it mssql "bash"
   // cmd에서 docker에 있는 bash 로 접속한다. 
   ```

### 4. sqlcmd 라는 파일을 이용해 MSSQL에 접속

   ```commend
   /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "<your password>"
   // /opt/mssql-tools/bin/sqlcmd => sqlcmd 있는 절대경로 똑같이 써줘야함 
   ```

### 5. MSSQL 에 접속하면 cmd 앞부분이 mssql@mssql 의 형태로 바뀐다.

### 6. 이제 Ms SQL을 사용할 수 있으므로 명령어를 입력한다. 

   - 이때 주의할점은 명령한다음 무조건 go 명령어를 입력해줘야한다. 
   - go 명령어를 실행해야 지정한 명령어가 실행됨 

   ```sql
   > create database wonyDB
   > go
   > use wonyDB
   ```

### 7. 해당 DB를 쉽게 사용하기 위해 데이터베이스 관리 도구를 사용해야한다. 

   - 이때 **툴과 Ms SQL Database를 연결**해주기 위한 '**사용자 계정 생성** ' 과 ' **계정 권한 할당** ' 을 해줘야한다. 

   ```
   // 사용자 계정 생성
   1> CREATE LOGIN <user> WITH PASSWORD='<user password>'
   2> GO
   1> CREATE USER <user> FOR LOGIN <user>;
   2> GO
   
   // 계정 권한 할당 
   1> exec sp_addrolemember 'db_owner', <user>;
   ```

---

   ### 에러

- 문제

  - cmd 에서 docker 사용시 에러 
  - 비밀번호설정 중 test34!@#$ 중 '!@#$' 부분을 cmd가 지원하지 않음

- 해결 

  - docker desktop 에서 실행 

  - 또는 비밀번호 변경을 통해 '!@#$' 부분 변경 

    ```
    > docker exec -it mssql-server /opt/mssql-tools/bin/sqlcmd -S > localhost -U SA -P '<yourNewPAssword1!>' -Q 'ALTER LOGIN SA WITH PASSWORD="<yourNewPAssword1!>"'
    ```

    



