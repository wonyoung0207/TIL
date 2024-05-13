# Path 옵션 

---

>

## --path

1. `--path` 옵션을 사용할 때 기본 경로는 명령을 실행하는 시스템 또는 프로그램의 현재 작업 디렉토리이다. 

   1. 예를 들어, 터미널에서 명령을 실행할 때, `--path`를 사용하면 기본 경로는 터미널이 열려 있는 디렉토리가 된다. 

2. 맨 앞에 `/`  를 사용하면 루트 디렉토리 경로를 뜻하므로, 절대 경로로 찾게 된다. 

   1. 즉, `/home/user/documents` 는 절대 경로인 `C:` 밑에 있는 /home/... 의 경로를 뜻하게 된다. 
   2. cmd에서 `cd /`  명령어를 통해 절대경로의 시작위치가 어디인지 파악할 수 있다. 

   ```js
   // 경로로 이동
   cd /home/user/documents
   
   // --path 는 /home/user/documents 가 된다. 
   // 따라서 file.txt 는 /home/user/documents/file.txt 를 뜻하게 된다. 
   command --path file.txt
   ```

   