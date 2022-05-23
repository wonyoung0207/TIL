# 29일차

------

> AJAX 에대해 학습한다. 
>
> 

1. AJAX

   1. JSON 이용

      1. [ { } , { } , { } ...] 의 형태를 가진다. 

      2. ```java
         @RequestMapping("/getdata")
         public Object getdata() {//데이터를 JSON 형식으로 내려준다. 
             JSONArray ja = new JSONArray();
             //JSON : [{},{},{}] 의 형태
             for(int i=0; i<6; i++) {
                 JSONObject jo = new JSONObject();
                 jo.put("id", "id01"+i);
                 jo.put("name", "james" + i);
                 jo.put("age", 30+i);
                 ja.add(jo);
             }
             return ja;
         }
         ```

   2. 차트 이용

      1. ```html
         function display(){
         	const chart = Highcharts.chart('container', {//container영역에 뿌린다. 
         	    title: {
         	        text: 'Chart.update'
         	    },
         	    subtitle: {
         	        text: 'Plain'
         	    },
         	    xAxis: {
         	        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
         	    },
         	    series: [{//JSON 형식 
         	        type: 'column',
         	        colorByPoint: true,
         	        data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4],
         	        // 이 데이터를 뿌린다. 
         	        showInLegend: false
         	    }]
         	});
         ```

   3. JSONArray 와 JSONObject 차이점 

      - JSONArray

        - 배열 형식으로 value를 다룰때 사용한다. 
        - ja.add(0, data) 로 추가하고  data[0] 으로 사용한다. 

      - JSONObject

        - HashMap의 형태로 Key와 value로 이루어진다. 
        - HashMap의 특징으로 순서가 없고 jo.put("id","id01") 로 추가하고 jo.get("id") 로 사용한다. 

      - ```java
        @RequestMapping("/loginCheck")
        public Object loginCheck(String id, String pwd) {//데이터를 JSON 형식으로 내려준다. 
            JSONArray ja = makeUser();
            JSONObject result = new JSONObject();//결과 전송할 변수 
            JSONObject check_user = new JSONObject();
            check_user.put("id", id);
            check_user.put("pwd", pwd);
        
            for(int i=0; i<ja.size(); i++) {
                JSONObject user = (JSONObject) ja.get(i);
                if(check_user.get("id").equals(user.get("id"))) {
                    if(check_user.get("pwd").equals(user.get("pwd"))) {
                        System.out.println("로그인 성공");
                        result.put("ok", "1");
                        result.put("message", "로그인 성공");
                        
                        return result;//아이디가 존재. 로그인 성공 
                    }
                    result.put("ok", "0");
                    result.put("message", "비밀번호가 다릅니다. ");
        
                    System.out.println("비밀번호가 다릅니다. ");
                    return result;//비밀번호 오류
                }
            }
        
            result.put("ok", "0");
            result.put("message", "아이디가 존재하지 않습니다. ");
            System.out.println("아이디 존재 안함 ");
            return result;// 아이디 존재 오류
        
        }
        ```

      - ```javascript
        function loginCheck(){
            var id = $('#id').val();
            var pwd = $('#pwd').val();
            alert(id + ' : ' + pwd)
        
            //아이디 있는지 확인 ajax 통신으로 확인 
            $.ajax({
                url: '/loginCheck',
                data : {
                    'id':id,
                    'pwd':pwd,
                },
                success : function(data){
                    //data 는 JSONObject 형식으로 받았지만 JavaScript 타입에는 Object타입밖에 없어서 사용시에는 Object 형식으로 사용해야 된다. 
        
                    $('#id_check').text("오류번호 : " + data.ok + "("+ data.message + ")");
                    $('#id_check').css('color','red');
                },
            });
        }
        ```

      - 

2. 유용한 사이트

   1. [highcharts](https://www.highcharts.com/)
      1. 차트 그리는 곳 
      2. 사용방법 
         1. Demos -> Highcharts JS Demos -> 원하는 차트 선택 -> EDIT IN JSFIDDLE -> \<script> 태그 복사 
         2. 

3. JS 파일과 JSP 파일 차이점

   1. js (JavaScript)
      - js 파일은 클라이언트쪽 ( front-end ) 파일을 의미한다. 
      - 따라서 \<script> 태그안에 포함되는 것을 의미한다. 
   2. jsp (Java Server pages)
      - jsp 파일은 서버측(Back-end) 파일을 의미한다. 
      - HTML 내에 직접 자바 코드를 삽입하여 웹 서버에서 동적으로 웹 페이지를 생성하여 웹 브라우저에게 돌려주는 서버 측 프로그래밍 
