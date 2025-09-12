# Threejs 기능 정리 

---

>

## 도형 개선 

- 3D 그래픽(혹은 2D 그래픽)에서 경계선이나 대각선처럼 계단 현상이 도드라져 보이는 것을 부드럽게 처리해주는 기법
- 경계선(edge) 인접 픽셀을 여러 번 샘플링하기 때문에, 경계가 더 부드럽게 보이게 한다. 

```js
const renderer = new Three.WebGLRenderer({
    alpha : true // 캔버스 배경 투명 여부
    antialias : true // 안티얼리어싱 (MSAA 활성화(Multisample Anti-Aliasing))
})
```

## 페이지 반응형 처리 

```js
function onWindowResize() {
	camera.aspect = window.innerWidth / window.innerHeight; // 종횡비 ( 설정 안하면 Scene은 조절되지만 도형은 찌그러짐)
    camera.updateProjectionMatrix();
    renderer.serSize(window.innerWidth, window.innerHeight); // webGL 자체 사이즈 조절 
}

// 웹 이벤트 발생
window.addEventListener('resize', onWindowResize)
```

