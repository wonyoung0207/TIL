# css BEM 아키텍처(클래스 이름 설정 규칙)

---

>[드림코딩-웹개발 로드멥](https://www.youtube.com/watch?v=TTLHd3IyErM&t=133s)

## BEM의 기본 구조

1. BEM은 **Block, Element, Modifier**라는 세 가지 개념으로 구성된다. 

## (1) Block (블록)

1. **독립적인 재사용 가능한 컴포넌트**를 나타낸다. 
2. 의미상 가장 큰 단위를 구성한다. 
3. 예: `menu`, `header`, `button`, `form`

```html
<div class="menu">...</div>
```

## (2) Element (요소)

1. **Block 내의 구성 요소**를 의미
2. **Block에 종속**되어 있으며 독립적으로 존재할 수 없다. 
3. 예: `menu__item`, `menu__link`

```html
<div class="menu">
  <div class="menu__item">Item 1</div>
  <div class="menu__item">Item 2</div>
</div>
```

## (3) Modifier (수정자)

1. **Block 또는 Element의 상태, 스타일 또는 동작을 변경**하는 용도로 사용된다. 
2. 색상, 크기, 활성화 여부 등을 나타낼 수 있다. 
3. 예: `menu__item--active`, `menu--large`

```html
<div class="menu menu--large">
  <div class="menu__item menu__item--active">Item 1</div>
</div>
```

------

## 2. BEM 명명 규칙

1. BEM에서는 다음과 같은 명명 규칙을 따른다. 
2. **Block 이름:** 단어를 하이픈(`-`)으로 구분합니다.
   1. 예: `header`, `search-bar`
3. **Element 이름:** 블록 이름 뒤에 `__` (두 개의 밑줄)로 구분하여 작성합니다.
   1. 예: `header__logo`, `search-bar__input`
4. **Modifier 이름:** 블록 또는 요소 이름 뒤에 `--` (두 개의 하이픈)으로 구분하여 작성합니다.
   1. 예: `header--fixed`, `button__icon--small`

------

## 3. 예제 코드

```html
<div class="card card--primary">
  <div class="card__header">
    <h1 class="card__title">Card Title</h1>
  </div>
  <div class="card__body">
    <p class="card__text">This is a card component.</p>
  </div>
  <div class="card__footer">
    <button class="card__button card__button--disabled">Submit</button>
  </div>
</div>
```