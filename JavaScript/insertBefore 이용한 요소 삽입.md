# insertBefore 이용한 요소 삽입

---

>[참고 사이트1](https://shinyks.com/2023/07/javascript/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8-insertbefore-%EC%82%AC%EC%9A%A9-%EB%B0%A9%EB%B2%95/)
>
>[참고 사이트2](https://powerku.tistory.com/114)

## insertBefore()

1. 하나의 노드를 특정 부모 노드의 자식 노드로 삽입하는 메소드 이다. 
2. 기준 점 노드 앞에 노드를 삽입한다. 
   1. 이방법을 이용해 내가 원하는 위치에 태그를 삽입 할 수 있다.
3. 기존 부모 노드에서도 굳이 삭제 할 필요 없이 자동으로 이동하게 한다. 
   1. 즉, 부모노드 1번에 위치하고 있을 때, 3번으로 옮기고 싶을 때 삭제하고 옮기지 않아도 된다. 
   2. 노드는 자동적으로 기존 노드에서 삭제되고, 새로운 부모 밑에 삽입된다.  
4. 기준 점 노드를 null로 하게 되면, 자식 노드의 끝에 삽입 됩니다. 
5. 반환값 
   1. 추가된 Node 개체를 반환값으로 가진다. 

### 문법

```js
// 부모노드.insertBefore(삽입 할 노드, 기준 점 노드);
const insertNode = node.insertBefore(newnode, existingnode);
```

### 예시

```html
<ul class = "parent">
  <li class="newEle">+</li>
  <li class="ele">button</li>
  <li class="ele">button</li>
  <li class="ele">button</li>
  <li class="ele">button</li>
</ul>
```

```js
const ele = document.querySelector('.newEle'); // 추가할 노드
const parent = document.querySelector('.parent'); // 부모 노드 
parent.insertBefore(ele, null); // 부모의 맨 끝에 삽입
parent.insertBefore(ele, parent.firstChild); // 부모의 맨 앞에 삽입
```



---

## 비교 메소드 

### appendChild() 

1. 하나의 노드를 특정 부모 노드의 **자식 노드 중 맨 마지막** 자식 노드로 삽입하는 메소드
2. 개체 집합(Node)만 추가 할 수 있으며, 추가된 Node 개체를 반환값으로 가진다.