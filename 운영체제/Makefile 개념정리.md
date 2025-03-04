# Makefile 개념정리

---

>

## 사용목적

1. frontend 와 backend 를 모노레포로 구성하고 makefile을 이용해 손쉽게 install, run, build 를 하기위해 만들었다. 
2. 한 터미널에서 `make all` 해서 front, back 을 한꺼번에 실행하고 싶었다. 

## Makefile이란?

- `Makefile`은 **자동화된 빌드 시스템**을 정의하는 파일이다. 
- **"make" 명령어를 사용하여 반복적인 작업을 자동화**할 수 있도록 도와준다. 
- 즉,  `make <타겟>` 명령어 하나로 손쉽게 실행 가능

## 사용목적

1. 코드 빌드 자동화 (예: C, C++, Java, Gradle 등)
2. 프로젝트 실행 자동화 (예: `npm run serve`, `./gradlew bootRun`)
3. 파일 관리 자동화 (예: `clean`, `install`, `deploy`)

## 구조 

1. "타겟(target): 의존성(dependency) → 실행 명령어(command)"  구조를 가진다.

| 개념               | 설명                                                         |
| ------------------ | ------------------------------------------------------------ |
| 타겟(Target)       | 실행할 작업 이름 (예: `frontend`, `backend`, `clean`)        |
| 의존성(Dependency) | 특정 작업을 실행하기 전에 먼저 실행할 작업 (없으면 생략 가능) |
| 명령어(Command)    | 해당 작업을 실행할 때 실제 실행되는 명령어 (탭으로 들여쓰기 필수) |
| `.PHONY`           | `make`가 파일과 구분할 수 있도록, 실제 파일이 아닌 "가상 명령어"라는 걸 알려줌 |

## Makefile 실행 (기본)

```bash
make <타겟>

make frontend   # frontend 실행
make backend    # backend 실행
make start      # frontend + backend 병렬 실행
make clean      # 빌드 파일 삭제
```

## if 문 

- Bash 스크립트에서 `if` 문을 사용할 때, 반드시 `fi`로 종료해야 한다. 
- `fi`는 **C, Java 같은 언어에서 `{}`로 블록을 닫는 것과 같은 역할**을 함.

```makefile
if [ 조건 ]; then
    실행할 명령어
fi

# 에제
# /home/user 디렉터리가 존재하면 echo 실행, 존재하지 않으면 아무것도 실행되지 않음.
if [ -d "/home/user" ]; then 
    echo "✅ 디렉터리가 존재합니다!"
fi
```

## 예제

1. 한 터미널에서 명령어 한번으로 front, back 을 각각 다른 터미널에서 실행되게 하려고 했지만 실패했다. 
   - 한 터미널에서 실행하면 빽그라운드로? 돌아가거나 로그 중첩되서 재대로 실행되는걸 확인 못함 
   - 결국 하다가 못해서 터미널 2개 띄워 `make frontend` 와 `make backend` 하는걸로 ... 
2. Frontend
   1. Vuejs 로  node 로 관리한다.
3. Backend
   1. SpringBoot 로, Gradle로 관리한다. 

```makefile
.PHONY: all frontend backend start install_modules clean

# 프론트엔드 및 백엔드 의존성 설치
install_modules:
	@echo "🚀 Installing frontend and backend dependencies..."
	cd frontend && npm install --legacy-peer-deps
	cd backend && gradle build

# Gradle 설치 
install_gradle:
	sudo apt remove gradle
	curl -s "https://get.sdkman.io" | bash
	source "$HOME/.sdkman/bin/sdkman-init.sh"
	sdk version
	sdk install gradle 7.6.1
	sdk list gradle
	gradle -v

# 자바 17 설치 
install_java: 
	sudo apt install openjdk-17-jdk -y
	java -version

# 프론트엔드 실행
run_frontend:
	@echo "Starting frontend..."
	cd frontend && npm run serve

# 백엔드 실행
run_backend:
	@echo "Starting backend..."
	cd backend && gradle bootRun

# 프론트 빌드	
build_frontend : 
	@echo "Build frontend..."
	cd frontend && npm run build

# 백엔드 빌드
build_backend : 
	@echo "Build Backend..."
	cd backend && gradle build

# frontend와 backend를 동시에 실행
# start:
# 	@echo "Opening new WSL terminals for frontend & backend..."
# 	wsl.exe -e bash -c "make frontend" 
# 	wsl.exe -e bash -c "make backend" 



# 프로젝트 정리 (빌드 결과물 삭제)
clean:
	@echo "🧹 Cleaning project..."
	cd frontend && rm -rf node_modules && rm -rf dist
	cd backend && gradle clean


```

