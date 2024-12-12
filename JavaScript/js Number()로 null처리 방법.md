### Number() 함수로  null 값변경

- 자바스크립트에서는 null값을 Number()형태로 변경하면 0으로 인식한다. 

  ```typescript
  if(this.autoIdMap.get(className) === undefined){
      this.autoIdMap.set(className, Number(club.getId()));// usid = '' 을 Number형태로 변경하면 0이나온다. 
  }
  ```

- null은 0으로, undefined는 NaN으로 반환한다. 