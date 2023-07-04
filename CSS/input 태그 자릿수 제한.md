# input 태그 자릿수 제한

---

>

## input type="number" 에서의 자릿수 제한

### 문제

- type="text" 인경우에는 태그 옵션 중 **maxlength** 를 이용해 손쉽게 자릿수를 제한할 수 있다. 
- 하지만 type="number" 인 경우에는 이러한 **옵션이 동작하지 않는다.** 
  - 따라서 별도로 처리를 해줘야 한다. 

### 방법

1. **정규표현식(regex)을 pattern 어트리뷰트에 maxlength와 함께 사용하기**

   -  pattern 어트리뷰트는 input태그에 정규표현식을 사용하여 입력 받을 수 있는 값을 제한하는 것이 가능하다. 

   - 단, 모바일 환경에서 지원되지 않을 수 있다는 단점이 존재. 

     ```html
     <input type="text" pattern="\d*" maxlength="8" />
     ```

2. oninput 이벤트(vue의 경우 @input )를 사용하여 별도의 스크립트로 처리 

   1. switch 문 사용

      ```javascript
      <input type="number"@input="maxLengthCheck('a',10)"  >
          
      maxLengthCheck : function(name, maxLength){
              switch(name){
                case 'a' : 
                  if(this.val.a.length > maxLength){
                    this.val.a = this.val.a.slice(0,-1);
                  }
                case 'b' :
                  if(this.val.b.length > maxLength){
                      this.val.b = this.val.b.slice(0,-1);
                  }
                case 'c' : 
                  if(this.val.c.length > maxLength){
                    this.val.c = this.val.c.slice(0,-1);
                  }
                case 'd' :
                  if(this.val.d.length > maxLength){
                      this.val.d = this.val.d.slice(0,-1);
                  }
                default : 
                  console.log('default ... ') ;
              }
      }
      ```

   2. $event 이용

      ```javascript
      <input type="number"@input="maxLengthCheck($event,'a',10)"  >
          
      maxLengthCheck : function(e, name, maxLength){ // 자릿수 체크 메소드 
          if(e.target.value.length > maxLength){
              let val = e.target.value;
              switch(name){
                  case 'a':
                    this.val.a = e.target.value.slice(0,-1);
                    break;
                  case 'b':
                    this.val.b = e.target.value.slice(0,-1);
                    break;
                  case 'c':
                    this.val.c = e.target.value.slice(0,-1);
                    break;
                  case 'd':
                    this.val.d = e.target.value.slice(0,-1);
                    break;
              }
          }
      }
      ```

3. max, min 속성을 이용 

   - 만약 4자리 까지 가능하게 하려면

     ```html
     <input type="number" min="0" max="9999">
     ```

