# cursor 개념정리

---

> [참고 사이트1](https://ofcourse.kr/css-course/cursor-%EC%86%8D%EC%84%B1)

## Cursor

### 정의

- 해당 태그 위에 위치하는 마우스 커서의 모양을 변경할 수 있다. 

### 종류

1. `auto`: 자동
2. `default`: 기본값 (화살표)
3. `pointer`: 손가락 모양 (클릭 가능한 버튼)
4. `wait`: 로딩
5. 등등...

### 사용법

```html
<style>
.cursors span{ cursor: pointer }
</style>

<div class="cursors">
	<span style="cursor: auto">Auto</span>
	<span style="cursor: crosshair">Crosshair</span>
	<span style="cursor: default">Default</span>
	<span style="cursor: pointer">Pointer</span>
	<span style="cursor: move">Move</span>
	<span style="cursor: e-resize">e-resize</span>
	<span style="cursor: ne-resize">ne-resize</span>
	<span style="cursor: nw-resize">nw-resize</span>
	<span style="cursor: n-resize">n-resize</span>
	<span style="cursor: se-resize">se-resize</span>
	<span style="cursor: sw-resize">sw-resize</span>
	<span style="cursor: s-resize">s-resize</span>
	<span style="cursor: w-resize">w-resize</span>
	<span style="cursor: text">Text</span>
	<span style="cursor: wait">Wait</span>
	<span style="cursor: help">Help</span>
</div
```



