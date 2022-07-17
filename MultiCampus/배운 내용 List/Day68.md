# Day68

---

> NCP의 ORC 기능 복습 

## ORC

- [내가 정리한 내용](https://github.com/wonyoung0207/TIL/blob/master/MultiCampus/%EB%B0%B0%EC%9A%B4%20%EB%82%B4%EC%9A%A9%20List/Day63.md)

- 이용 방법 및 순서

  1. NCP 클라우드 접속 후 콘솔 - Service - AI Service - CLOVA ORC 클릭

  2. 도메인 생성 

     1. General / Template 생성
     2. 도메인 이름, 도메인 코드 같은값으로 입력 
     3. 한국어 - Template - Basic - Free 선택 후 도메인 생성 

  3. 템플릿 빌더 클릭 

     1. 왼쪽 목록의 설정 클릭 
     2. 외부연동 - 연동설정 - APIGW 자동연동 수정 클릭 - Secret Key 와 APIGW URL 복사 

  4. 템플릿 생성 

     1. 왼쪽 메뉴에서 템플릿 목록 클릭 
     2. 템플릿 명 입력 
        - 템플릿 명은 범위가 넓음 . 사용할 기능 명 적어주면 된다. 
        - ex ) 영수증 인식기 
     3. 이미지 업로드 
        - 처음 나오는 영역설정은 '로고' 로 일단 지정 후 '대표 샘플명' 설정
        - 대표 샘플명 설정 후 필드 추가를 눌러 인식할 텍스트 영역을 설정한다. 
        - 영역 설정 후 체크 버튼을 누르면 필드 이름 설정이 나온다. 이때 필드 이름을 설정한다. 
          - 필드 영역은 나중에 JSON 데이터로 받아올 때 필요함 
        - 인식 필드 지정 후 템플릿 저장 

  5. 서비스 배포

     - 서비스를 이용하기 위해서는 배포를 해야한다. 
     - 베포관리 - 만든 템플릿 선택 - 베타 배포 and 서비스 배포 클릭 
     - 정식으로 이용하기 위해서는 서비스 배포를 해야한다.  

  6. 테스트 진행 

     - 왼쪽 목록에 있는 '테스트' 항목으로 
     - 파일 찾기로 파일 올리고 확인 누르면 영역을 인식한다. 

  7. Eclipse 에서 ORC 이용 

     1. pom.xml 설정

        ```xml
        <!-- json request -->
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20211205</version>
        </dependency>
        ```

     2. java 코드 및 결과 

     ```java
     @Test
     void contextLoads() {
         String apiURL = "https://xtwbhr7su2.apigw.ntruss.com/custom/v1/17243/fb64d737a4729e5285737905a977c2703f72e819a7824771b610a8f612e9f6c4/infer";//템플릿 빌더에서 복사한 APIGW URL 입력 
         String secretKey = "d2NVcFRPemZUYnloZkVxekpPSExyYnVxSXZwQmJHY3A=";//템플릿 빌더에서 복사한 secret Key 입력
         String imgpath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static","img").toString();//이미지 저장 경로 
         String imageFile = imgpath +"\\영화영수증_오즈의 마법사.png";//이미지 이름 
         try {
             URL url = new URL(apiURL);
             HttpURLConnection con = (HttpURLConnection)url.openConnection();
             con.setUseCaches(false);
             con.setDoInput(true);
             con.setDoOutput(true);
             con.setReadTimeout(30000);
             con.setRequestMethod("POST");
             String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
             con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
             con.setRequestProperty("X-OCR-SECRET", secretKey);
     
             JSONObject json = new JSONObject();
             json.put("version", "V2");
             json.put("requestId", UUID.randomUUID().toString());
             json.put("timestamp", System.currentTimeMillis());
             JSONObject image = new JSONObject();
             image.put("format", "jpg");
             image.put("name", "demo");
             JSONArray images = new JSONArray();
             images.put(image);
             json.put("images", images);
             String postParams = json.toString();
     
             con.connect();
             DataOutputStream wr = new DataOutputStream(con.getOutputStream());
             long start = System.currentTimeMillis();
             File file = new File(imageFile);
             System.out.println("----------"+file.getName());
             System.out.println("----------"+file.getPath());
             System.out.println("----------"+file.getAbsolutePath());
             writeMultiPart(wr, postParams, file, boundary);
             wr.close();
     
             int responseCode = con.getResponseCode();
             BufferedReader br;
             if (responseCode == 200) {
                 br = new BufferedReader(new InputStreamReader(con.getInputStream()));
             } else {
                 br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
             }
             String inputLine;
             StringBuffer response = new StringBuffer();
             while ((inputLine = br.readLine()) != null) {
                 response.append(inputLine);
             }
             br.close();
             System.out.println("REsult:");
             System.out.println(response);
         } catch (Exception e) {
             System.out.println(e);
         }
     }
     private void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
         IOException {
         StringBuilder sb = new StringBuilder();
         sb.append("--").append(boundary).append("\r\n");
         sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
         sb.append(jsonMessage);
         sb.append("\r\n");
     
         out.write(sb.toString().getBytes("UTF-8"));
         out.flush();
     
         if (file != null && file.isFile()) {
             out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
             StringBuilder fileString = new StringBuilder();
             fileString
                 .append("Content-Disposition:form-data; name=\"file\"; filename=");
             fileString.append("\"" + file.getName() + "\"\r\n");
             fileString.append("Content-Type: application/octet-stream\r\n\r\n");
             out.write(fileString.toString().getBytes("UTF-8"));
             out.flush();
     
             try (FileInputStream fis = new FileInputStream(file)) {
                 byte[] buffer = new byte[8192];
                 int count;
                 while ((count = fis.read(buffer)) != -1) {
                     out.write(buffer, 0, count);
                 }
                 out.write("\r\n".getBytes());
             }
     
             out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
         }
         out.flush();
     }	
     ```

     ```json
     	//JSNO 형태로 영화이름 출력 : data.images[0].fields[0].inferText
     	//JSNO 형태로 날짜정보 출력 : data.images[0].fields[1].inferText
     	//JSNO 형태로 좌석정보 출력 : data.images[0].fields[2].inferText
     	//JSNO 형태로 가격정보 출력 : data.images[0].fields[3].inferText
     	//JSNO 형태로 프로젝트명 출력 : data.images[0].fields[4].inferText
     	// 영수증 ORC 출력 JSON 형태 
     	{"version":"V2",
     	"requestId":"40569b6c-2547-40e9-962a-88098bdfa862",
     	"timestamp":1658045876594,
     	"images":[{
     		"uid":"bfcf733471d84ef8b648e363b34e9f76",
     		"name":"demo",
     		"inferResult":"SUCCESS",
     		"message":"SUCCESS","matchedTemplate":{"id":18762,"name":"영수증"},
     		"validationResult":{"result":"NO_REQUESTED"},
     		"fields":[
     		          {"name":"영화이름",
     		        	  "valueType":"ALL",
     		        	  "boundingPoly":{"vertices":[{"x":18.0,"y":136.0},{"x":217.0,"y":136.0},{"x":217.0,"y":176.0},{"x":18.0,"y":176.0}]},
     		        	  "inferText":"오즈의 마법사",
     		        	  "inferConfidence":0.99985003,
     		        	  "type":"NORMAL","subFields":[{"boundingPoly":{"vertices":[{"x":18.0,"y":137.0},{"x":115.0,"y":137.0},{"x":115.0,"y":173.0},{"x":18.0,"y":173.0}]},"inferText":"오즈의","inferConfidence":0.9997,"lineBreak":false},{"boundingPoly":{"vertices":[{"x":118.0,"y":136.0},{"x":217.0,"y":136.0},{"x":217.0,"y":176.0},{"x":118.0,"y":176.0}]},"inferText":"마법사","inferConfidence":1.0,"lineBreak":true}]
         			  },
     		          {"name":"날짜정보",
         				  "valueType":"ALL",
         				  "boundingPoly":{"vertices":[{"x":13.0,"y":214.0},{"x":232.0,"y":214.0},{"x":232.0,"y":248.0},{"x":13.0,"y":248.0}]},
         				  "inferText":"2022/07/30(일) 1회",
         				  "inferConfidence":0.99965,
         				  "type":"NORMAL",
         				  "subFields":[{"boundingPoly":{"vertices":[{"x":13.0,"y":214.0},{"x":188.0,"y":214.0},{"x":188.0,"y":248.0},{"x":13.0,"y":248.0}]},"inferText":"2022/07/30(일)","inferConfidence":0.9993,"lineBreak":false},{"boundingPoly":{"vertices":[{"x":187.0,"y":214.0},{"x":232.0,"y":214.0},{"x":232.0,"y":246.0},{"x":187.0,"y":246.0}]},"inferText":"1회","inferConfidence":1.0,"lineBreak":true}]
     				  },
     		          {"name":"좌석정보",
     					  "valueType":"ALL",
     					  "boundingPoly":{"vertices":[{"x":9.0,"y":262.0},{"x":148.0,"y":262.0},{"x":148.0,"y":294.0},{"x":9.0,"y":294.0}]},
     					  "inferText":"3관,B열 7번",
     					  "inferConfidence":0.99864995,
     					  "type":"NORMAL","subFields":[{"boundingPoly":{"vertices":[{"x":9.0,"y":262.0},{"x":104.0,"y":262.0},{"x":104.0,"y":294.0},{"x":9.0,"y":294.0}]},"inferText":"3관,B열","inferConfidence":0.9974,"lineBreak":false},{"boundingPoly":{"vertices":[{"x":103.0,"y":262.0},{"x":148.0,"y":262.0},{"x":148.0,"y":293.0},{"x":103.0,"y":293.0}]},"inferText":"7번","inferConfidence":0.9999,"lineBreak":true}]
     				  },
     		          {"name":"가격정보",
     					  "valueType":"ALL",
     					  "boundingPoly":{"vertices":[{"x":40.0,"y":323.0},{"x":137.0,"y":323.0},{"x":137.0,"y":341.0},{"x":40.0,"y":341.0}]},
     					  "inferText":"10,000 원 (1 명)",
     					  "inferConfidence":0.9997,"type":"NORMAL",
     					  "subFields":[{"boundingPoly":{"vertices":[{"x":40.0,"y":325.0},{"x":84.0,"y":325.0},{"x":84.0,"y":340.0},{"x":40.0,"y":340.0}]},"inferText":"10,000","inferConfidence":0.9995,"lineBreak":false},{"boundingPoly":{"vertices":[{"x":83.0,"y":323.0},{"x":100.0,"y":323.0},{"x":100.0,"y":341.0},{"x":83.0,"y":341.0}]},"inferText":"원","inferConfidence":1.0,"lineBreak":false},{"boundingPoly":{"vertices":[{"x":99.0,"y":324.0},{"x":116.0,"y":324.0},{"x":116.0,"y":340.0},{"x":99.0,"y":340.0}]},"inferText":"(1","inferConfidence":1.0,"lineBreak":false},{"boundingPoly":{"vertices":[{"x":115.0,"y":323.0},{"x":137.0,"y":323.0},{"x":137.0,"y":341.0},{"x":115.0,"y":341.0}]},"inferText":"명)","inferConfidence":0.9993,"lineBreak":false}]
     					  },
     		          {"name":"프로젝트명",
     						  "valueType":"ALL",
     						  "boundingPoly":{"vertices":[{"x":57.0,"y":507.0},{"x":218.0,"y":507.0},{"x":218.0,"y":536.0},{"x":57.0,"y":536.0}]},
     						  "inferText":"Ticket SaJo",
     						  "inferConfidence":0.999,
     						  "type":"NORMAL","subFields":[{"boundingPoly":{"vertices":[{"x":57.0,"y":507.0},{"x":143.0,"y":507.0},{"x":143.0,"y":536.0},{"x":57.0,"y":536.0}]},"inferText":"Ticket","inferConfidence":0.9997,"lineBreak":false},{"boundingPoly":{"vertices":[{"x":145.0,"y":507.0},{"x":218.0,"y":507.0},{"x":218.0,"y":536.0},{"x":145.0,"y":536.0}]},"inferText":"SaJo","inferConfidence":0.9983,"lineBreak":true}]
     				  }
     		          ],
     		"title":{"name":"영수증_쇼생크탈출","boundingPoly":{"vertices":[{"x":12.0,"y":16.0},{"x":141.0,"y":16.0},{"x":141.0,"y":39.0},{"x":12.0,"y":39.0}]},"inferText":"Ticket SaJo","inferConfidence":0.9994,"subFields":[{"boundingPoly":{"vertices":[{"x":12.0,"y":16.0},{"x":80.0,"y":16.0},{"x":80.0,"y":38.0},{"x":12.0,"y":38.0}]},"inferText":"Ticket","inferConfidence":0.9997,"lineBreak":false},{"boundingPoly":{"vertices":[{"x":81.0,"y":16.0},{"x":141.0,"y":16.0},{"x":141.0,"y":39.0},{"x":81.0,"y":39.0}]},"inferText":"SaJo","inferConfidence":0.9991,"lineBreak":true}]}
     	}]}
     ```

     
