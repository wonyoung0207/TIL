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

      2. 

2. 유용한 사이트

   1. [highcharts](https://www.highcharts.com/)
      1. 차트 그리는 곳 
      2. 사용방법 
         1. Demos -> Highcharts JS Demos -> 원하는 차트 선택 -> EDIT IN JSFIDDLE -> \<script> 태그 복사 
         2. 
   2. 
