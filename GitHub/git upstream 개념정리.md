# git upstream 

---

>[원격 저장소 branch 가져오기](https://cjh5414.github.io/get-git-remote-branch/)
>
>[참고 사이트1](https://dev200ok.blogspot.com/2020/09/git-git-upstream-origin.html)
>
>[참고 사이트2](https://velog.io/@rkio/Git-upstream-origin%EC%97%90-%EB%8C%80%ED%95%98%EC%97%AC)
>
>[참고 사이트3](https://m.blog.naver.com/PostView.naver?blogId=sqlpro&logNo=222555029055&categoryNo=18&proxyReferer=)

## upstream

### 정의

1. 로컬 브랜치가 원격 브랜치를 추적하도록 설정하는 데 사용한다. 

### upstream 과 origin 의 차이점 

1. 보통은 다른 레포를 포크해서 내 레포를 만들어 놓고 내 레포를 클론, 풀 해서 작업한다. 
2. 이때 가장 근본이 되는 레포를 upstream 이라고 부른다. 
3. 내가 fork해서 가져온 레포를 origin이라고 부른다. 
4. 그러므로 내가 로컬에 클론 해 놓고 작업할 때, origin/master에서 가져올지, upstream/master에서 가져와야 할지에 따라 upstream과 origin 의 의미는 달라진다. 

### upstream과 downstream

1. 두 레포지토리간의 관계에 따라 정의되는 상대적인 개념이다. 
2. pull 로 기준을 보면 이해가 쉽다. 
   1. pull을 당하는 쪽 = upstream
   2. pull을 하는 쪽 = downstream
3. fork 로 본다면 
   1. fork해온 내 repo는 downstream 이고 
   2. fork한 repo가 upstream 이 된다. 
4. 나의 repo를 clone으로 가져온다면 
   1. origin 해온 브랜치가 remote 즉, upstream 이 된다. 
   2. 그리고 local이 downstream이 된다. 





