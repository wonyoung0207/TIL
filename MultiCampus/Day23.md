# 23일차

------

> html 태그의 종류와 구조에 대해 학습한다. 
>
> 셀렉터의 종류와 속에 대해 학습한다. 

## 1. 태그 종류

   1. 공간 분할 태그

      1. div
      2. span

   2. 시맨틱 태그

      - 의미론적인 이라는 의미로, 태그를 쉽게 구분할 수 있게 만든다. 
      - html5의 가장 큰 변화이다. 
      - 즉, 시맨틱 웹은 컴퓨터 프로그램이 코드를 읽고 의미를 인식할 수 있는 **지능형 웹을 의미**한다. 

      1. header
         - 머리말
      2. nav
         - 하이퍼링크들을 모아둔 내비게이션
      3. aside
         - 본문의 흐름에 벗어나는 노트나 팁
      4. section
         - 문서의 장이나 절에 해당하는 내용
      5. article
         - 본문과 독립적인 콘텐츠 영역
      6. footer
         - 꼬리말 

## 2. 셀렉터

   - HTML 태그의 모양을 꾸밀 스타일 시트를 선택하는 기능

   1. class

      - 태그를 그룹화 할 때 사용한다. 
      - 범위가 넓을경우 사용
      - style 태그에서 ' . ' 으로 사용된다 

   2. id

      - 태그를 지칭할 때 유일한 값으로 사용
      - 범위가 좁거나 유일할때 사용
      - style태그에서 ' # ' 으로 사용된다. 

   3. *

      - 전체 셀렉터로, 모든 태그를 대상으로 한다. 

   4. 자손, 후손

      1. 자손선택자
         - 직계 자신만 포함한다. 
         - ' > ' 를 사용한다. 
         - header > h1
      2. 후손선택자
         - 자식, 후손 모두를 포함한다. 
         - '  ' 를 사용한다. 
         - header h1

      ```css
      #tb1 > tbody > tr:nth-child(2n+1){
          /* tr의 홀수 번째에 적용됨 */
      background : gray;
      color: black;
      
      }
      
      #tb1 > tbody > tr > td:nth-child(3){
      /* td 3번쨰에만 적용됨  */
      text-align: right;
      
      }
      ```

      

   5. 반응 선택자

      1. active
         - 마우스가 클릭될 때 실행
      2. hover
         - 마우스 커서가 올라갔을 때 실행 

      ```css
      input[type="text"]{
          background:lightblue;
      }
      
      input[name="pwd"]{
          background:lightyellow;
      }
      
      input[type="submit"]:hover{
          color: white;
          background:black;
      }
      
      input:focus{
          background:blue;
      
      }
      ```

      

   6. 위치 속성

      1. position속성을 사용하여 요소의 위치를 지정할 수 있다. 

      ```css
      .box{
      	width:100px;
      	height: 100px;
      	/* float:left; */
      	position:absolute;
          /* 위치를 절대적으로 0.0 을 기준으로 설정한다.  */
      	opacity: 0.5;
          /* 투명도를 나타낸다. 0~1 사이값 설정가능  */
      }
      
      .box:nth-child(1){/* class=box 중에서 1번째에 적용  */
      	background:red;
      	left:10px;
      	top:10px;
      	z-index:100 
           /* 큰 숫자가 먼저 위로올라온다.  */
      	
      }
      .box:nth-child(2){
      	background:blue;
      	left:50px;
      	top:50px;
      	z-index:10 
      	
      }
      ```

   7. 박스 속성

      1. margin
         - 테두리의 바깥쪽 여백
      2. border
         - 테두리
      3. padding
         - 테두리와 콘텐츠 사이의 여백
      4. width
         - 콘텐츠 영역의 가로 크기
      5. height
         - 콘텐츠 영역의 세로 크기

      - ### **인라인 vs block**

        1. #### inline 
        
           1. 영역이 아닌 하나의 콘텐츠로 만들어져서 **가로,세로 길이를 사용하지 못한다. **
           2. 속성
              1. 새 라인에서 시작 못함
              2. 모든 박스 내부에 위치 가능
              3. 옆에 다른 요소 배치 가능
              4. width, height로 크기 조절 불가능
              5. margin 조절 불가 
        
        2. #### block
        
           1. 영역으로 잡혀 콘텐츠와 콘텐츠를 구분할 수 있다. 
           2. **_block 으로 설정해도 글자의 높이를 따라간다. 따라서 line-height 라는 것으로 글자가 차이하는 공간을 늘려줘야한다._**
           3. 속성
              1. 항상 새 라인에서 시작
              2. 블록박스 안에서만 배치 가능
              3. 옆에 다른 요소 배치 불가능
              4. width, height 으로 크기 조절 가능 
              5. padding, margin,border 조절가능
        
        3. #### inline-block
        
           1. inline의 속성과 block의 속성을 합쳐놓은 것
           2. 속성
              1. inline속성
                 1. 새 라인 시작 못함
                 2. 모든 박스 내에 배치 가능
                 3. 옆에 다른 요소 배치 가능 
        
              2. block 속성
                 1. width, height 사용가능
                 2. padding, margin,border 조절 가능 
        
        
        ```CSS
        div{
            width:200px;
            height: 200px;
            border: 3px solid red;
            border-radius : 10px 50px;
            margin:150px;
            /* 상하좌우 모두 margin으로 250을 지정한다.  */
            padding:10px;
            box-shadow: 5px 5px 5px #A9A9A9;
            /* 가로, 세로 5씩, 그림자를 5px, 색상을 #A9로  */
        }
        
        p{
            display: none;
            /* 영역까지 없애서 화면에서 안보이게 만든다.  */
            visiblity: hidden;
            /* 영역은 유지하면서 안보이게 만든다.  */
            display:inline;
            /* 가로 세로를 설정할 수 없다. */
            display:inline-block;
            /* 가로 세로를 설정할 수 있다.  */
            display:block;
            
            line-height: 70px;
        	/* block 형으로 바꿔도 높이는 글자높이 만큼만 된다. 따라서 따로 높이를 조절해 줘야한다.  */
        
        }
        ```
        
        

## 3. 폰트 추가하기

   1. [google font 이용](fonts.google.com)

      1. 사용할 폰트 누르고 들어가서 select font style
      2. 오른쪽 위 눌르고 link 복사 후 사용할 파일에 넣어주기 
      3. font-family 속성으로 적용해주기 . 

      ```css
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Koulen&family=Oleo+Script+Swash+Caps&display=swap" rel="stylesheet">
      
      span{
          font-family: 'Oleo Script Swash Caps', cursive;
          /* 만약 앞에적힌 폰트가 없으면 디폴트로 cursive를 적용한다.  */
      	}
      ```
      
      

   ## tip.콘텐츠 무조건 정 중앙부터 배치하고 싶다면 사용

   ## 		margin : 0 auto;

   ## tip. margin으로는 글씨의 높이가 조절되지 않는다. 따라서 높이를 조절하려면 다음을 사용
   ##		line-height

   

