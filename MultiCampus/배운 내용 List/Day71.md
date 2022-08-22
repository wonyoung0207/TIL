# Day71

---

> 영수증 인식 후 적립 가능한지 검사 

# Final Project

>영수증 적립조건 검사 
>
>JavaScript 날짜 형식 이용 

## JavaScript 날짜

- 사용 가능 함수 
  - getFullYear()
    - 연도(네 자릿수)를 반환
  - getMonth()
    - 월을 반환합니다(**0 이상 11 이하**).
    - Month 만 0~11 이라 +1 을 해야 현재 월이 재대로 나온다. 
  - getDate()
    - 일을 반환
  - getHours(), [getMinutes(), getSeconds(), getMilliseconds()
    - 시, 분, 초, 밀리초를 반환

- 일수 더하기 

  - pre_today.setDate(pre_today.getDate() + 7)

  ```javascript
  function display(data) {
      //JSNO 형태로 영화이름 출력 : data.images[0].fields[0].inferText
      //JSNO 형태로 날짜정보 출력 : data.images[0].fields[1].inferText - 2022/07/24(일)
      //JSNO 형태로 좌석정보 출력 : data.images[0].fields[2].inferText
      //JSNO 형태로 가격정보 출력 : data.images[0].fields[3].inferText
      //JSNO 형태로 프로젝트명 출력 : data.images[0].fields[4].inferText
      var today = new Date();
      var pre_today = new Date();
      var receipt_projectName;
      var receipt_date;
      var movie_date ;
  
      if(data.images[0].inferResult != 'FAILURE'){// 적립할수 있는 영수증 
          receipt_projectName = data.images[0].fields[4].inferText;
          receipt_date = data.images[0].fields[1].inferText;
          movie_date = new Date(receipt_date.substr(0, 4),receipt_date.substr(5, 2),receipt_date.substr(8, 2));// 영수증 시간 
          pre_today.setDate(pre_today.getDate() - 7);// 현재시간에서 7일 더하기 
          pre_today.setMonth(pre_today.getMonth() + 1);// +1 을 해야 현재 월이 정확히 나온다.  
  
          if(receipt_projectName == 'Ticket SaJo' &&  movie_date > pre_today){
              $('#UploadResult').text("적립금을 받을 수 있는 영수증 입니다. ");
              $('#UploadResult').css('color','green');
              $('#UploadResult')[0].scrollIntoView();
              $('#context').show();
          }else{
              $('#UploadResult').text("해당 영화관이 아닌 영수증 이거나 날짜가 7일 이상 지난 영수증 입니다. ");
              $('#UploadResult').css('color','red');
              $('#UploadResult')[0].scrollIntoView();
              $('#context').hide();
          }
  
      }else{//적립할수 없는 영수증  
          $('#UploadResult').text("적립금을 받을 수 없는 영수증 입니다. 다른 영수증을 이용해 주세요.");
          $('#UploadResult').css('color','red');
          $('#UploadResult')[0].scrollIntoView();
          $('#context').hide();
      }
  
  }
  ```

