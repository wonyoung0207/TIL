# cursor CSS  정리

---

> [참고 사이트1](https://ofcourse.kr/css-course/cursor-%EC%86%8D%EC%84%B1)

## cursor란?

- 마우스가 **요소 위에 있을 때 포인터 모양을 지정**하는 CSS 속성
- **기능을 만들지 않음**
- 오직 **사용자에게 가능한 행동을 알려주는 UI 힌트**

------

## cursor가 하는 것 / 안 하는 것

##### 하는 것

- 클릭 가능 여부 표현
- 드래그 가능 상태 표현
- 리사이즈 방향 안내
- 비활성 상태 표시

#####  안 하는 것

- 드래그 이동 구현
- 클릭 이벤트 처리
- 마우스 좌표 계산
- 터치 동작 처리

------

## 핵심 요소

##### 기본 UI

- `default` : 기본 화살표
- `pointer` : 클릭 가능
- `text` : 텍스트 선택
- `not-allowed` : 사용 불가

##### 드래그 UI

- `grab` : 잡을 수 있음
- `grabbing` : 잡고 이동 중
- `move` : 이동 가능 (구형)

##### 리사이즈

- `ew-resize`, `ns-resize`, `nwse-resize`, `nesw-resize`

##### 사용법

```vue
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

------

## 상태에 따른 사용 패턴

```css
.draggable {
  cursor: grab;
}

.draggable:active {
  cursor: grabbing;
}
```

- 기본 상태: 드래그 가능
- 클릭(누름) 상태: 드래그 중

------

## 적용 규칙

- **가장 가까운 요소의 cursor가 적용**
- 같은 속성 여러 번 쓰면 **마지막 값만 적용**
- 모바일 환경에서는 **무시됨**

------

## 요약

> cursor는 “동작을 만드는 속성”이 아니라 “가능한 동작을 알려주는 시각적 표시”다.
