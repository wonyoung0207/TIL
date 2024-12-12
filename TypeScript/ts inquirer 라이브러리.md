# ts inquirer 라이브러리

---

>

## inquirer

- 사용자로부터 화살표(방향키)로 움직여 선택항목을 선택해  입력을 받는다. 

### 사용방법

1. 'npm install inquirer @types/inquirer  '  을 통해 2가지 모듈을 받는다. ( inquirer 의 기본모듈과 typescript에서 사용할수 있는 모듈 )

2. 모드지정 ( prompt 함수에 리터럴 타입으로 값을 넣어서 모드를 만든다. )

   ```typescript
   // Commends.ts
   export enum Commans{
       Quit = "Quit",
       Add = "Add"
   }
   
   // TodoConsole.ts
   inquirer.prompt({
       type : 'list', // list형태로 항목을 뿌려줌 
       name : 'command',
       message : 'Choose Option',  
       choice : Object.values(Commands)// 선택할 수있는 항목들 ( 여기서는 열거형 enum 을 사용한다. )
   }).then((answers) => { // 사용자가 화살표로 선택한 항목 
       if(answers['command'] !== Commands.Quit ){ // 항목들중 Quit을 화살표로 선택하면 실행
           this.promptUser();
       }
   })
   ```

<img src="../../../../requirer.png">

### 발생 오류

- inquirer 은 npm으로 실행시키면 사용자 방향키 입력에 에러가 난다.

  - 따라서 node로 실행한다. 

    ```shell
    node build/index.js
    ```
