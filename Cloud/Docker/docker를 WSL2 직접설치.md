## Wls2 에 Docker 설치 

```bash
# 1. 패키지 업데이트
sudo apt update
sudo apt upgrade -y

# 2. 필수 패키지 설치
sudo apt install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release -y

# 3. Docker GPG 키 등록
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | \
  sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

# 4. Docker 저장소 등록
echo \
  "deb [arch=$(dpkg --print-architecture) \
  signed-by=/etc/apt/keyrings/docker.gpg] \
  https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

# 5. Docker 설치
sudo apt update
sudo apt install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y

# 6. Docker 데몬 실행 (WSL은 systemd 없음 → 직접 실행 필요)
sudo dockerd &

# 7. (선택) 현재 유저를 docker 그룹에 추가 -> 해야 sudo 안써도 docker 이용할 수 있음 
sudo usermod -aG docker $USER
```

