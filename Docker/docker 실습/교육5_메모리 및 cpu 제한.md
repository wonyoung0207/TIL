# 교육5_ 메모리 및 cpu 제한 

---

>[참고 사이트1](https://junstar92.tistory.com/165)
>
>[참고 사이트2](https://cumulus.tistory.com/35)
>
>[참고 사이트3](https://dejavuhyo.github.io/posts/setting-memory-and-cpu-limits-in-docker/)
>
>[postgresql DB 셋팅](https://shyvana.tistory.com/8)

### 문제

1. Ubuntu 20.04 컨테이너 생성
   - Memory 3.2 GB 로 제한 
   - CPU 110% 로 사용제한 
2. hello 를 출력하는 무한 루프를 Shell Script 형태로 저장 
3. 2번 진행의 결과물을 실행 
4. Dokcer 의 자원 확인 명령어를 통해 컨테이너 자원 사용량 확인 

### 사용할 개념

1. 이미 생성한 **컨테이너의 속성을 수정** 

   - 이미 생성된 컨테이너의 리소스 제한 설정을 변경하려면 **update 명령어**를 사용

     ```cmd
     docker update 변경속성 <container>
     ```

2. 컨테이너 **메모리 제한** 

   - docker run 커맨드에 **--memory 옵션**을 지정하여 제한할 수 있다. 

   - 입력할 수 있는 단위는 m(megabyte), g(gigabyte)이며, 제한할 수 있는 최소 메모리는 4MB 이다. 

     - **컨테이너** 내에서 동작하는 프로세스가 컨테이너에 **할당된 메모리를 초과**하면 **컨테이너는 자동으로 종료**된다. 

     ```cmd
     docker run --name m1 -it -m 512m
     docker run --name m2 -it --memory 3.2G 
     ```

3. 컨테이너 **CPU 자원 할당 비율** 

   - `--cpu-shares 또는 -c`

   - 컨테이너에 CPU를 한 개씩 할당하는 방법이 아니라, 시스템에 존재하는 CPU를 어느 비중만큼 나누어 사용할 것인지**(가중치)**  명시하는 옵션

   - 설정하지 않으면 기본적으로 1024 이다. => 1024 는 **cpu 할당에서**의 `--cpu 1 ` 과 같은 값이다. 

     - 예를들어, 1024를 할당한 컨테이너가 512가 할당된 컨테이너에 비해 2배 더 많은 CPU 자원이 할당된다는 뜻이다. 

       ```cmd
       docker run --name c1 -c 1024 컨테이너명
       docker run --name c2 --cpu-shares 512 컨테이너명
       ```

4. **CPU** 코어 개수 지정 

   - `--cpus`

   - **10진수**로 지정하면 **코어의 개수**를 의미하고, **소수**로 지정하면 **비율**을 의미

     ```cmd
     '로컬 컴퓨터의 CPU 코어 중 2개의 비중을 할당 받는다. '
     docker run --name c1 --cpus=2 컨테이너명
     docker run --name c1 --cpus=1.1 컨테이너명
     
     ```

5. 컨테이너 자원 확인 

   - `docker stats 컨테이너명`
   - 컨테이너의 cpu ,메모리 사용량등을 볼 수 있다.



```java
// 1. ubuntu 를 메모리 3.2g, cpu 110% 로 할당 
docker run -it --name ubu -m 3.2g --cpus=1.1 ubuntu:20.04
    
// 2. hello 출력하는 무한루프 shell script 형태 저장 
apt-get update 
apt-get install vim 
touch script.sh
vi script.sh
    
// ******* shell start *******
#!/bin/bash

while :
do
	echo "Press [CTRL+C] to stop.."
done
// ******* shell end*******
    
    
// 3. 결과물 실행 
./script.sh
    
// 4. docker container 자원 사용량 확인 
exit 
docker stats ubu
    

// docker 컨테이너 접속 
docker exec -it ubu /bin/bash

```

<img src="./컨테이너 자원 확인.png" width=700>
