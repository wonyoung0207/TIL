# Day60

---

> NCP ( CLOVA OCR 사용 - 문자 판독기 )
>

# NCP 

- 카카오 지도 API 사용 
  - KaKaoTest.java 파일
  
    ```java
    public String kakaolocalapi(String keyword) throws Exception {//keyword에 찾을 값이 들어옴 
        String address = "https://dapi.kakao.com/v2/local/search/keyword.JSON";
    
        String param = "query=" + keyword
            //+ "&category_group_code=" + "FD6"
            + "&x=" + "37.5606326" // 현재 나의 위치 x 좌표
            + "&y=" + "126.9433486" // 현재 나의 위치 y 좌표
            + "&radius=" + "1000"; // 1km 안에서 검색 
    
        String apiKey = "bf688fae49b592a5af93829db4dd43d3";	//발급받은 restapi key
    
        URL url = new URL(address);  			//접속할 url 설정
        HttpURLConnection conn;					//httpURLConnection 객체
        conn = (HttpURLConnection) url.openConnection();	//접속할 url과 네트워크 커넥션을 연다.
        conn.setRequestMethod("POST");             
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);	//Property 설정
    
        OutputStreamWriter ds = new OutputStreamWriter(conn.getOutputStream());
        ds.write(param);
        ds.flush();
        ds.close();
    
        int responseCode = conn.getResponseCode();		//responseCode를 받아옴.
    
        InputStream inputStream = conn.getInputStream();	//데이터를 받아오기 위한 inputStream
        BufferedReader br;		//inputStream으로 들어오는 데이터를 읽기 위한 reader
        String json = null;
        Charset charset = Charset.forName("UTF-8");
        if(responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(inputStream,charset));
            json = br.readLine();
            br.close();
        }
        else {
            System.out.println(" ERROR !!! ");
        }
    
        inputStream.close();
        conn.disconnect();
        System.out.println(json);
    
        return json;
    }
    ```

# Final Project

> 사람별 페이지 나누기 
>
> 기능 구현 상의 
>
> 기획안 및 I.A 설계

- 깃허브 구축 ( 브랜치 점검 )

  - [GithubProject 소스 ](https://github.com/JangHyojoon/Ticket_SaJo)

- [기획안 완성](https://docs.google.com/document/d/1MbLavEHwsvxEfyyYkqMv0_yCHno2QccQ/edit)

- UI 설계 ( 홈페이지 계층 구조도)

  <img src="../images/FinalProject/IA설계도.png">

### 
