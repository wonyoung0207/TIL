## vue @input

- input 태그안의 value값을 실시간으로 감지하기 위해 사용한다. 

  ```vue
  <temaplate>
  	<input type="text" @input="onChange($event)">
  </temaplate>
  
  <script>
      onChange(event){
          console.log(event.target.value)
      }
  </script>
  ```

  

