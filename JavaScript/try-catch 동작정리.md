# try-catch 동작정리

---

>

##  예외 전파 기본 동작

1. **`try-catch`를 명시하지 않으면, JavaScript는 에러를 자동으로 위로 throw**한다. 
2. 하지만 **`try-catch`를 명시했는데 `throw`하지 않으면 에러가 "소멸"**되므로 상위에서 catch 하지 못한다.


| 항목                                | 설명                            |
| ----------------------------------- | ------------------------------- |
| `try-catch` 없음                    | 에러는 위로 자동 `throw`        |
| `try-catch` 있음 (but `throw` 안함) | 에러 먹어버림. 상위에서 못 받음 |
| `try-catch` + `throw err`           | 에러 처리 후 위로 다시 전파     |

```js
async function downLoadFunction() {
  try {
    await downloadVideo();
  } catch (err) {
    console.log('에러 발생했음'); // ❗ 여기서 끝나면, 상위는 에러를 모름
    // throw err; ← 이게 없으면 상위에서 못 잡음!
  }
}
```





## cancle 시 try-catch 안잡힘 문제발생

##### 문제상황

1. axios 이용해서 cancle 했는데 try-catch 문 호출 안됨 

2. `AbortController.abort()` → Axios 요청 취소됨

3.  Axios는 `Promise`를 reject(실패) 처리 (**이때 async , await 함수여야함**)

4. cancle 되면 자동으로 try-catch 문에 걸려야 하는데 안걸림 

   ```js
   import eventInterface from "../eventInterface";
   
   setup() {
       const {
         downLoadFunction,
       } = eventInterface();
   
       return {
         downLoadFunction,
       };
   },
   ```

##### 원인

1. vue 의 api call 메소드를 setup() 으로부터 가져와 사용함

2. 이때 setup() 에서 선언한 메소드에 대한 api call 은 `Function` 으로 랩핑되어 async 가 풀려버림

3. 즉, async 풀린 함수로 인식해 에러 전파가 제대로 이루어지지 않아 try-catch 에 걸리지 않음 

   ```js
   // async 함수인지 판단 방법
   console.log(this.downLoadFunction.constructor.name); // "Function" -> 이러면 NOT "AsyncFunction" 이라 에러전파 안됨
   ```

##### 해결방법

1. setup() 안에서 함수 구현 후 내부에서 api 호출 

   ```js
   setup() {
     const { downLoadFunction } = eventInterface();
   
     const downLoadF = async (event) => {
       try {
         const controller = new AbortController();
         const signal = controller.signal;
   
         const response = await downLoadFunction(event, signal);
         console.log("✅ 응답:", response);
       } catch (err) {
         console.log("❌ catch 됨:", err);
       }
     };
   
     return {
       downLoadFunction,
       downLoadF, // UI에서 쓸 수 있게 return
     };
   }
   ```

2. `response === undefined` 체크 후 `throw err` (내가 사용한 방법)

   ```js
   async downLoadF(event) {
         try{
           // api 요청 여부 확인 
           if (this.downloadController) {
             this.downloadController.abort() // 이전 요청 cancle 
           };
   
           // api 요청 캔슬
           this.downloadController = new AbortController();
           let signal = this.downloadController.signal; 
             
           const response = await this.downLoadFunction(event, signal);
           if(!response){ // cancle 시 response 체크 
             const err = new Error("cancled download");
             err.name = "CanceledError";
             err.code = "ERR_CANCELED"; 
             throw err
           }
           else if(response.data?.success) {
             var data = response.data.data;
             ...
           } else {
             this.fileUrl = null; 
             this.videoDownloadFail(response.data.error.message)
           }
         }
         catch(err){
           if (err.name === 'CanceledError' || err.code === 'ERR_CANCELED') {
             console.log('video download Canceled: The request was canceled');
           } else{
             console.log("🚀 ~ downLoadF ~ err:", err)
           }
         }
       },
   ```

   



