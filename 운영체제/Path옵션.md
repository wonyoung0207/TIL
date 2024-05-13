# Path 옵션 

---

>

## --path

1. `--path` 옵션을 사용할 때 기본 경로는 명령을 실행하는 시스템 또는 프로그램의 현재 작업 디렉토리이다. 

2. 예를 들어, 터미널에서 명령을 실행할 때, `--path`를 사용하면 기본 경로는 터미널이 열려 있는 디렉토리가 된다. 

   ```js
   // 경로로 이동
   cd /home/user/documents
   
   // --path 는 /home/user/documents 가 된다. 
   // 따라서 file.txt 는 /home/user/documents/file.txt 를 뜻하게 된다. 
   command --path file.txt
   ```

   