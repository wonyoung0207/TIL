# git 발생 오류

---

1. 초기 branch 없어서 push 불가능 

   - 문제점

     - `git push origin master` 를 했을 때 다음 오류가 발생했다. 
       - origin과 remote 도 정상적으로 설정되어있는데 안됨 

     ```git
     remote: A default branch (e.g. main) does not yet exist for temp/newemp_temp/2023_first_quarter/wony/ex2
     remote: Ask a project Owner or Maintainer to create a default branch:
     ```

   - 발생이유 

     - owner, 또는 maintainer를 제외하고 새로운 repo를 생성하고 최초 push를 할 수 없다. (developer는 최초 push를 할 수 없음
     - 따라서 권한이 없어 push 하지 못하고, 권한없이 push 하려면 기본 branch가 존재해야한다. 

   - 해결

     - 권한 있는 분이 branch 생성 후 다시 Clone 받음

2. 