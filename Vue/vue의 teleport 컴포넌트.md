#  vue의 teleport 태그 

---

>

## teleport

### 정의

- UI를 DOM 트리의 다른 위치에 렌더링할 수 있도록 한다. 
  - UI 요소를 **현재 컴포넌트의 DOM 구조와 분리**시켜 더 유연하고 **효율적인 UI 디자인을 구현**
  - 따라서 원하는 태그의 내용을 다른 컴포넌트(상위 또는 하위) 로 보내 표시할 수있다. 

### 사용이유

- Dom 구조로 봤을 때 , alert 창 같은 경우 사용한 위치가 아닌, 해당 컴포넌트의 가장 상위에 표시되는게 Dom 구조로써 좋다. 
  - 따라서 Dom의 원하는 위치로 태그 안의 내용을 옮길 때 사용한다. 
- 해당 컴포넌트의 data와 method를 모두 이용할 수 있지만 Dom트리로 봤을 때는 다른 컴포넌트 처럼 다른 곳에서 렌더링 된다. 

### 사용법

1. 이동할 태그 내용을 `teleport`로 감싼다. 
2. **to 옵션**에 **이동할 태그 이름**을 적는다. 
   - to는 태그 옵션으로, 해당 태그안에 있는 내용을 Dom트리의 어디로 이동시킬지 정한다. 
   - 태그 이름인 `body` 로 써도 되고, 태그의 id나 class 이름인 `#tagId` `.className` 으로 사용할 수도 있다. 

### 예시

```html
<!-- 컴포넌트 1 -->
<body>
    <div>
        div 1 
    </div>
    <div>
        div 2
    </div>
</body>

<!-- 컴포넌트 2 -->
<template>
	<div>
        <teleport to="body"> <!--다른 컴포넌트로 보내 Dom 렌더링이 진행된다.  -->
            <error-alert v-if="inputIsInvalid">
                <h2>
                    Input is invalid!
                </h2>
                <button @click="confirmError">
                    Okey
                </button>
            </error-alert>
        </teleport>
	</div>
</template>

```

