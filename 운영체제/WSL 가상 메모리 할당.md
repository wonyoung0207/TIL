# WSL 가상 메모리 할당

---

>[MS WSL 설정 문서](https://learn.microsoft.com/ko-kr/windows/wsl/wsl-config)

## 문제 발생

1. 로컬 개발도중 메모리가 부족해 계속해서 프로그램이 죽는 현상이 발생했다. 
2. wsl 의 메모리에 제한이 없어 주는데로 먹고있어 노트북 RAM 16GB 을 넘어서고 있어 제대로 동작을 하지 않는것이였다. 
3. 그래서 wsl 의 메모리 제한과 swap 을 통한 가상 메모리 공간 할당을 해주었다. 

## WSL 설정

- wsl 의 전역 설정인 `.wslconfig` 파일을 통해 여러 설정들을 먹일 수 있다. 
  - 해당 파일은 기본생성이 아니라 직접 만들어줘야한다. 
  - **Windows 사용자 디렉토리에 위치**하며, WSL 전체 설정을 제어한다. 
  - 경로 : `C:\Users\<사용자명>\.wslconfig`

```bash
[wsl2]
memory=4GB
processors=2
swap=100GB # 지정한 4GB를 넘기면 사용하는 가상 RAM으로, wsl 을 종료하면 자동으로 삭제된다. 
swapFile=F:\\wsl\\image\\wsl-swap.vhdx # swap 으로 사용할 가상 공간 디스크 
```

| 항목         | 설명                                                         |
| ------------ | ------------------------------------------------------------ |
| `memory`     | WSL2 인스턴스가 사용할 수 있는 **최대 메모리 양**. 예: `4GB`, `2GB` |
| `processors` | WSL2에서 사용할 **CPU 코어 수** 제한. 예: `2`                |
| `swap`       | **스왑(가상 메모리)의 크기** 지정. 예: `100GB`, `0` (사용 안 함) |
| `swapFile`   | **스왑 파일의 위치 및 이름**을 직접 지정. 기본값은 `%USERPROFILE%\AppData\Local\Packages\...\ext4.vhdx` 근처에 자동 생성 |

## 주의 사항

- `.wslconfig` 수정 후에는 반드시 **WSL 전체를 재시작**해야 적용된다

  ```bash
  wsl --shutdown
  ```

- swap 크기를 너무 크게 하면 디스크 공간을 많이 차지하고, I/O 병목이 생길 수 있다. 