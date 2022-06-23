# Day50

---

> 세미프로젝트 Day07
>
> 날짜 비교 및 빼기
>
> thymeleaf 숫자로 each 돌리기 

# 세미프로젝트 Day07

> 개인 개발 
>
> 개발 내용 : Mypage ( 쇼핑정보, 활동정보-Review  )
>
> 중간점검 ( 내용 합치기 )

## Java 에서 Date 비교 

- [참고사이트](https://developer-talk.tistory.com/397)

- 비교 메소드

  1. compareTo()
  2. eqauls()
  3. before()
  4. after()

- CompareTo()

  - Date객체를 인수로 전달받으며 compareTo() 메서드를 호출한 Date객체와 값을 비교한다. 
  - 값이 동일하면 0을 반환하고, 호출한 Date 객체가 이전 날짜인 경우 0보다 작은 값을 반환한다. 
  - 반대로 호출한 Date객체가 이후 날짜인 경우 0 보다 큰 값을 반환한다. 

- before()

  - Date.before() 메서드는 before() 메서드를 호출한 Date 객체가 인수로 전달된 Date 객체보다 이전 날짜인 경우 true를 반환한다. 
  - 동일한 날짜이거나 이후 날짜인 경우 false를 반환한다. 
  - 따라서 매개변수의 날짜가 이전 날짜면 true 반환 

- after()

  - Date.after() 메서드는 after() 메서드를 호출한 Date 객체가 인수로 전달된 Date 객체보다 이후 날짜인 경우 true를 반환한다. 

  - 동일한 날짜이거나 이전 날짜인 경우 false를 반환합니다.

  - 따라서 매개변수의 날짜가 이후인 경우 true를 반환. 

    ```java
    Date date1 = new Date(2022, 1, 1);
    Date date2 = new Date(2022, 1, 15);
    boolean result = date1.after(date2);
    ```

- eqauls()

  - 날짜가 동일한 경우true 반환 

## 날짜 빼기

```java
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Calendar cal = Calendar.getInstance();
Date now = new Date();

cal.setTime(now);// 시간 설정 
cal.add(Calendar.MONTH, -1);// 현재시간부터 1달 뺴기 
System.out.println("1달 빼기 : " + sdf.format(cal.getTime()));
System.out.println(cal);
```

- java에 적용 

``` java

@RequestMapping("review")//활동정보 - 상품리뷰 클릭시 실행  
public String review(Model m, String id) {//user id 매개변수로 들어옴 
    CustVO cust = null;
    List<BuyVO> list = null;
    List<BuyVO> month_list = new ArrayList<BuyVO>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal1 = Calendar.getInstance();
    String strcal1="";
    Date before_one_month = new Date();


    cal1.setTime(before_one_month);// 시간 설정 
    cal1.add(Calendar.MONTH, -1);// 현재시간부터 1달 뺴기 
    strcal1 = sdf.format(cal1.getTime());
    //cal 형태를 date형태로 바꾸기위해 먼저 string으로 변경 


    try {
        //String 형으로 변형된 것을 date형으로 변환 
        before_one_month = sdf.parse(strcal1);

        cust = cust_biz.get(id);
        list = buy_biz.selectUserBuy(id);
        for(BuyVO buy : list) {
            if(buy.getRegdate().after(before_one_month)) {//현재시점 -1달 이후의 물건만 통과
                month_list.add(buy);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }


    m.addAttribute("buylist", month_list);
    m.addAttribute("mypage_center", "mypage/review");
    m.addAttribute("left", "mypage/left");
    m.addAttribute("center", "mypage/mypage");
    return "index";
}
```

## th:each 문 숫자로 돌리는 방법

```html
<!-- 참고 사이트 : https://ifuwanna.tistory.com/200-->
<i class="fa fa-star" th:each="num : ${#numbers.sequence(1,review.star)}"></i>
```
