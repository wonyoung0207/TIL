# Displacement와 Repeat 관계 문제 정리

---

>

## 1. DisplacementMap의 기본 동작

- `displacementMap`은 **geometry 정점(vertex)** 을 흑백 텍스처 값에 따라 위/아래로 실제 이동시킴.
- 정점 수(세그먼트)가 충분히 많을수록 텍스처의 굴곡을 더 세밀하게 표현 가능.

## 2. Repeat의 역할

- `repeat: (x, y)` 는 텍스처가 geometry 표면에 몇 번 반복되는지 결정.
- 값이 클수록 텍스처 타일이 더 촘촘해지고, 타일당 크기가 작아짐.

예시 (PlaneGeometry(20, 20, 100, 100)):

- `repeat: (10, 10)` → 한 타일 = 20/10 = 2 단위 크기
- `repeat: (100, 100)` → 한 타일 = 20/100 = 0.2 단위 크기

## 문제 발생

- 굴곡이 나타나야 하는데 아무리 scale 과 Bias 조절해도 평평하게 나옴. 
- 이유를 못찾아 displacement 이미지 여러개 변경해봤지만 다 똑같음
- 그러다 Reat 과 displacement 간의 관계에 대해 알게되었음 .
- 즉, **정점 밀도 부족** 발생이 원인
  - displacement는 정점 단위 변형이므로, 타일이 너무 작아지면 한 타일 안에 충분한 정점이 배치되지 않음.
  - **결과: 굴곡이 표현되지 않고 “평평하게 들려 올라가는” 현상 발생.**

## 추가로 발생할 수 있는 문제 

1. **해상도 대비 문제**
   - heightmap의 픽셀 정보를 geometry 정점이 샘플링해야 하는데,
   - repeat 값이 크면 픽셀당 차이가 geometry에서 무시될 정도로 작아짐.
2. **NormalMap과의 불균형**
   - repeat을 크게 하면 normalMap은 잘게 반복되어 디테일이 살아나지만,
   - displacement는 geometry 제약으로 따라가지 못해 어색한 결과가 발생.

## 문제 해결 

- **Displacement**
  - repeat을 작게 유지 (`1~2` 정도 권장)
  - geometry subdivision(세그먼트 수)을 높여야 울퉁불퉁함이 잘 보임
  - displacementScale 값으로 기복 강도 조절
- **NormalMap**
  - repeat을 크게 (`10~20` 이상 가능)
  - 디테일은 normalMap으로 표현하고 displacement는 큰 지형 변화만 담당하게 하는 게 일반적임

## 정리

-  **DisplacementMap은 geometry 해상도 의존적이므로 repeat을 크게 주면 굴곡 표현이 무너진다.**
  - Displacement는 repeat 작게 + subdivision 크게,
  - 디테일은 NormalMap으로 표현하는 것이 안정적인 전략.