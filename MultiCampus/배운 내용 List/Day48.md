# Day48

---

> 세미프로젝트 Day05
>
> script 파일 경로설정
>
> type="tel" 의 장점
>
> Login , Register 페이지 모든 조건

# 세미프로젝트 Day05

> 개인 개발 시작 
>
> 개발 내용 : login, register 

## Script 와 link 경로설정

- script로 가져온 bootstrap 이나 link 로 가져온 여러 css를 모든 페이지에서 사용하기 위해서는 경로를 절대값으로 설정해야한다. 

  ```javascript
  // 잘못사용한 예
  // 1. 절대경로 사용 x => assets 앞에 / (루트) 를 붙이지 않음
  <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
  // 2. 슬레쉬가 아닌 역슬레쉬 사용 
  <script src="\assets\vendor\bootstrap\js\bootstrap.min.js"></script>
  
  // 잘 사용한 예 
  <script src="/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
  <script src="/assets/js/custom.js"></script>
  ```

- 따라서 앞에 / (루트) 를 붙여주면 절대경로로 설정되서 루트부터 찾는다. 

  - 앞에 / (루트) 를 붙여주지 않으면 url 페이지가 변경될때마다 그 페이지를 중심으로 경로를 검색한다. 

  - 경로 : assets/vender 일경우 

    1. url : /login
       - /login/assets/vender 의 경로로 찾는다. 

    2. url : /
       - /assets/vender 의 경로로 찾는다. 

## input[type='tel'] 에서 사용할 수 있는 pattern

- 전화번호 타입일때 패턴을 설정할수 있지만 유효성 검사는 따로 진행해야한다. 
- 장점은 모바일 입력시 숫자 패드가 재공된다는 것이다. 

```xml
<input id="telNo" name="telNo" type="tel" required
          pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}">
```

## Login 페이지 조건

- 공백이면 안된다. 

  ```javascript
  $(document).ready(function() {
  	$('#id').blur(function(){
  		if($('#id').val() == ''){// id값이 공백일 경우
  			$('#id_span').text('id는 공백이 될 수 없습니다. ');
  			$('#id_span').css('color','red');
  		}else{
  			$('#id_span').text('');
  		}
  	});
  	
  	$('#pwd').blur(function(){
  		if($('#pwd').val() == ''){// id값이 공백일 경우
  			$('#pwd_span').text('pwd는 공백이 될 수 없습니다. ');
  			$('#pwd_span').css('color','red');
  		}else{
  			$('#pwd_span').text('');
  		}
  	});
  	
  	
  	$('#login_btn').click(function() {
  
  		if($('#id').val() == ''){// id값이 공백일 경우
  			$('#id_span').text('id는 공백이 될 수 없습니다. ');
  			$('#id_span').css('color','red');
  			$('#id').focus();
  		}else{
  			if ($('#pwd').val() == '') {//pwd 값이 공백일 경우 
  				$('#pwd_span').text('pwd는 공백이 될 수 없습니다.');	
  				$('#pwd_span').css('color','red');	
  				$('#pwd').focus();
  			} else {
  				$('#login_form').attr({
  					'method' : 'post',
  					'action' : '/login/loginimpl'
  				});
  
  				$('#login_form').submit();
  			}
  		}
  
  	});
  });
  ```

  

## Register

- 계정 생성시 정규표현식으로 조건 걸기 

- [패스워드 조건 참고 사이트 ](https://seill.tistory.com/774)

  ```javascript
  let number = pwd.search(/[0-9]/g);// 숫자 찾기 
  let english = pwd.search(/[a-z]/ig);//영문자 찾기 
  let spece = pwd.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);//특수문자 찾기 
  let reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;//비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함
  if (pw.length < 8 || pw.length > 20) {
      alert("8자리 ~ 20자리 이내로 입력해주세요.");
      return false;
  
  } else if (pw.search(/\s/) != -1) {
      alert("비밀번호는 공백 없이 입력해주세요.");
      return false;
  
  } else if (number < 0 || english < 0 || spece < 0) {
      alert("영문,숫자,특수문자를 혼합하여 입력해주세요.");
      return false;
  
  }else{
      alert("정상동작 중 ");
      return true;
  }
  ```

  
