# Day89

---

# Final Project

>admin page 의 center 꾸미기
>
>chart 이용 방문자수 표시 

## chart 이용 방문자수 표시 

#### mysql int로 형변환 

```sql
-- CAST(expr AS type) 
-- CONVERT(변환하고싶은 데이터, 데이터형식[(길이)])

-- a를 Integer 타입으로 형변환 
SELECT CAST(a AS signed) FROM A;
-- signed 가 integer 이다. 
SELECT CONVERT(AVG(count),SIGNED) FROM visitList;
```

#### chart 그리는 방법 

```html
<!-- chart를 그릴 곳 : figure 로 감싸주고 div 에 id값을 설정해 넣어준다. -->
<figure class="highcharts-figure">
    <div id="visitchart"></div>
</figure>
```

```java
@RequestMapping("/visitChartimpl")
public Object visitChartimpl() {
    System.out.println("visitChartimpl 시작 ");
    JSONObject jo = new JSONObject();
    JSONArray ja1 = new JSONArray();
    JSONArray ja2 = new JSONArray();
    JSONArray ja3 = new JSONArray();
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
    List<VisitListVO> list = null;
    String date = "";
    int avg = 0;

    try {
        list = vlbiz.VisitList7Days();
        avg = vlbiz.VisitListAvg();
        for(VisitListVO vl : list) {// 리스트 값을 각각 x, y축 에 들어갈 값으로 나누기 
            ja1.add(vl.getCount()); // 방문자수 
            date = sdf.format(vl.getDate());// 가져온 데이트 값을 String 형으로 변경 
            ja2.add(date); // 날짜 
            ja3.add(avg); // 7일간 방문자수 합 평균 
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    jo.put("visitcnt", ja1);
    jo.put("date", ja2);
    jo.put("avg", ja3);// 평균 방문자 수 

    return jo;

}
```

```javascript
$(document).ready(function() {
    getData(); // chart에 필요한 데이터 ajax로 가져오기 
});

function getData(){
    $.ajax({
        url:'visitChartimpl',
        success:function(data){//JsonObject 형태에 JsonArray형식으로 visitcnt, data 가 들어가있음 
            visitChart(data);
        }
    });
}

function visitChart(data){
    Highcharts.chart('visitchart', { // 차트를 그릴 div 명칭 
        chart: {
            type: 'spline',
            scrollablePlotArea: {
                minWidth: 300,
                scrollPositionX: 1
            }
        },
        title: {
            text: '최근 7일간 방문자수 통계 ',
            align: 'center'
        },
        subtitle: {
            text: '',
            align: 'left'
        },
        xAxis: {// x축값
            type: 'date',
            labels: {
                overflow: 'justify'
            },
            categories: data.date
        },
        yAxis: { //y축값
            title: {
                text: '방문자 수 '
            },
            minorGridLineWidth: 0,
            gridLineWidth: 0,
            alternateGridColor: null,
            plotBands: [{ // Light air
                from: 0,// y축 나눌 범위. 0 ~ 30 까지는 "방문자수 낮음" 으로 표시 
                to: 30,
                color: 'rgba(68, 170, 213, 0.1)',
                label: {
                    text: '방문자수 낮음 ',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // Light breeze
                from: 30,
                to: 60,
                color: 'rgba(0, 0, 0, 0)',
                label: {
                    text: '방문자수 약간 낮음 ',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // Gentle breeze
                from: 60,
                to: 90,
                color: 'rgba(68, 170, 213, 0.1)',
                label: {
                    text: '방문자수 평균',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // Moderate breeze
                from: 90,
                to: 140,
                color: 'rgba(0, 0, 0, 0)',
                label: {
                    text: '방문자수 많음',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // Fresh breeze
                from: 140,
                to: 170,
                color: 'rgba(68, 170, 213, 0.1)',
                label: {
                    text: '방문자수 아주 많음 ',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // Strong breeze
                from: 170,
                to: 1000,
                color: 'rgba(0, 0, 0, 0)',
                label: {
                    text: '방문자수 포화 많음 ',
                    style: {
                        color: '#606060'
                    }
                }
            }]
        },
        tooltip: {
            valueSuffix: ' 명'
        },
        plotOptions: {
            spline: {
                lineWidth: 4,// 그래프 굵기  
                states: {
                    hover: {
                        lineWidth: 5
                    }
                },
                marker: {
                    enabled: false
                },
                pointInterval: 1, // one hour -> x축 증가 범위 
                pointStart: 0// 시작값 
            }
        },
        series: [{// 그래프에 사용될 data 들어가는 곳 
            name: '전체 평균 방문자 수 ',
            data: data.avg

        }, {// 그래프에 사용될 data 들어가는 곳 
            name: '지난 7일간 날짜별 방문자 수 ',
            data: data.visitcnt
        }],
        navigation: {
            menuItemStyle: {
                fontSize: '10px'
            }
        }
    });
}
```
