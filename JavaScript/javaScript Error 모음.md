# TypeError: Failed to execute 'appendChild' on 'Node': parameter 1 is not of type 'Node'."

---

>[참고 사이트1](https://bobbyhadz.com/blog/failed-to-execute-appendchild-on-node-in-javascript)

## 발생 코드

```js
showTootip:function(event){
    if(!!event['target']['dataset']){
        this.startConstruction=event['target']['dataset']['start'];
        this.endConstruction=event['target']['dataset']['end'];
    }

    const box=event.target.id?event.target:event.target.parentNode;
    const tooltip=document.getElementById('tooltip');
 	box.appendChild(tooltip);
    if(!!tooltip){
        tooltip.style.visibility='visible';
    }
 },
```

## 발생 원인 

- `appendChild` 함수의 첫 번째 매개변수로 전달된 값이 'Node' 타입이 아니라서 발생
  - `appendChild` 함수는 노드를 추가하는데 사용되며, 첫 번째 매개변수는 추가하려는 노드여야 한다. 

## 해결 코드 

```js
showTootip:function(event){
 if(!!event['target']['dataset']){
     this.startConstruction=event['target']['dataset']['start'];
     this.endConstruction=event['target']['dataset']['end'];
 }

 const box=event.target.id?event.target:event.target.parentNode;
 const tooltip=document.getElementById('tooltip');

 if(!!tooltip){ // tooptip이 빈값인지 체크 
     box.appendChild(tooltip);
     tooltip.style.visibility='visible';
 }
},
```

