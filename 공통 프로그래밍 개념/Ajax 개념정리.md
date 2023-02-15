# AJAX

------

> AJAX 에대해 학습한다. 

## 1. AJAX ( Asynchronous JavaScript And XML )

   1. ### AJAX 란?

      1. 자바스크립트 라이브러리 중 하나로 **비동기 방식** 으로 통신하는 방법이다.
         - 현재 화면을 유지하면서 특정 부분만 데이터를 받아와서 변경해준다. 
      2. 2005년에 만들어진 비동기 통신을 지원하는 새로운 방식이다. 
      3. 새로운 언어가 아닌 JavaScript에 추가된 통신 방식이다. 
      4. **화면 전체 (html파일) 를 재로드 하지 않고도 서버에서 특정 데이터를 송수신 할 수 있다. **
      5. ex) 네이버의 실시간 이슈 검색기능
      
   2. ### 동작 방식

      1. 클라이언트가 요청시 AJAX 통신방식으로 html파일을 요청
      2. 클라이언트와 서버가 네트워크로 연결이 됨 
      3. 서버가 HttpServletResponse 매개변수로 받아서 일을 처리하고 rep.getWriter().print() 로 응답한다. 
      4. 네트워크가 끊어짐
      5. 화면에 요청한 데이터만 바뀐 html 을 클라이언트가 본다. 
      6. 이때 실시간 정보들만 AJAX 통신을 이용해 서버로부터 요청하고 받는다. 

      - 브라우저 -> f12 -> 네트워크를 보면 ajax방식으로 통신하는 것을 알 수 있다. 

      - **AJAX 는 @controller 가 아닌 @RestController 를 사용한다.** 

        ```java
        @RestController//AJAX 통신에 적합한 애너테이션 
        public class AJAXController {
            @RequestMapping("/gettime")
            public Object gettime() {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                return "서버시간 " + sdf.format(d);
            }
        }
        ```

        ```javascript
        function display(data){
        	$('#result').text(data);
        }
        
        function getdata(txt){
        	$.ajax({
        		url:'search',
        		data:{'s':txt,},//서버에게 data 변수 s로 데이터를 보낸다. 
        		success:function(data){
        			//연결에 성공하면 실행 data에는 controller에서 리턴한 값이 들어간다. 
        			display(data);
        		},
        		error:function(){}
        	});	
        }
        
        $(document).ready(function(){
        	$('button').click(function(){
        		var txt = $('#txt').val();
        		getdata(txt);
        	});
        });
        
        ```

        ```java
        @RestController//AJAX 통신에 적합한 애너테이션 -> @Controller와 다름 
        public class AJAXController {
        	@RequestMapping("/gettime")
        	public Object gettime() {
        		Date d = new Date();
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        		return "서버시간 " + sdf.format(d);
        	}
        }
