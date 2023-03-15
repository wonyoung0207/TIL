# Signal 개념정리 

---

>[참고 사이트1](https://minholee93.tistory.com/entry/Linux-Signal)
>
>[참고 사이트2](https://shaeod.tistory.com/463)
>
>[참고 사이트3](https://jhnyang.tistory.com/143)
>
>[참고 사이트4](https://veneas.tistory.com/entry/Linux-%EB%A6%AC%EB%88%85%EC%8A%A4-%EC%8B%9C%EA%B7%B8%EB%84%90-%EB%AA%85%EB%A0%B9%EC%96%B4%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4-%EC%A2%85%EB%A3%8C-kill)

## Signal 

### 정의

- Linux에서 process 를 컨트롤하기 위해 사용한다. 
  - 따라서 특정 이벤트가 발생했을 때 프로세스에게 **전달하는 신호**(메시지) 이다.
- Linux 에서 사용하는 소프트웨어 인터럽트로, 비동기적인 이벤트를 헨들링하기 위해 사용된다. 

### 비동기적인 이벤트

- 예측할 수 없는 이벤트를 뜻한다. 

### 사용이유

- Unix/Linux에서 커널이나 프로세스가 다른 프로세스에게 비동기적인 **사건**을 알려주거나 시간을 동기화 시키기 위해 사용한다. 
- signal을 받은 프로세스는 보통 3가지 작업을 할 수 있다. 
  1. 프로세스를 종료
  2. signal 무시
  3. 받은 signal에 따른 적절한 행동

### 종류

- 종류가 많지만 여기서는 대표적인 SIGNAL 4가지만 살펴본다. 

1. SIGINT
   - 키보드로 입력받는 시그널로, 일반적으로 **CTRL + C** 입력시 보내지는 시그널이다.
   - 현재 작동중인 프로그램의 동작을 멈출때 사용된다. 
2. SIGKILL
   - 프로세스 강제로 종료의 기능을 한다. 
     - 아예 죽여버리는걸 뜻함 
3. SIGHUP
   - hangup, 로그아웃등의 접속이 끊을 때 발생하는 신호(Signal)로 특정 실행 중인 프로그램이 사용하는 설정 파일을 변경시키고 변화된 내용을 적용할때 사용된다. 
4. SIGSEGV
   - 잘못된 메모리 관리시 생기는 신호(Signal)

### 명령어 kill vs 함수 kill

1. kill() 함수 

   - 프로세스에 시그널을 전송하는 역할을 한다. 

     - signal 을 받은 프로세스의 기본 동작이 종료이기때문에 이렇게 명명. 기본 동작이 종료가 아니므로 *kill* 보다는 *send_signal* 이 더 적합한 이름이다.

   - 특정 프로세스 뿐만 아니라 같은 그룹 ID가 같은 모든 프로세스에게 동시에 시그널을 전송할 수 있으며, 권한 안에 있는 모든 프로세스에게도 시그널을 전송할 수 있다.

   - kill 함수 형태 

     ```shell
     // int kill(pid_t pid, int sig); 
     $ kill -9 123 
     // -9 : 명령어는 각각 번호가 있는데 SIGKILL이 -9를 의미
     // 123 : PID ( 신호를 보낼 프로세스의 ID 값이다.) 
     ```

2. kill 명령어 

   - 프로세스를 죽이는 것으로, -9 번이 대표적인 kill명령어이다. 
     - 물론, 프로세스에 SIGKILL을 보내면 쉘 명령의 kill과 같은 역활을 한다. 

3. 사용할 수 있는 SIGNAL 

   - 앞에 있는 번호로 해당 시그널들을 kill() 함수와 결합해 사용할 수 있다. <img src="./images/kill명령어.png" width="500">



