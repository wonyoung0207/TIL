# Day70

---

> OCR 이용 영수증 이미지 인식 

# Final Project

>사진 저장
>
>OCR 연결

## 사진 저장해서 OCR이용

- 서버로 사진을 전송하여 OCR로 사진을 판별하고 결과를 전송받는다. 

- 순서 
  1. 업로드된 파일을 서버에 저장한다. 
  2. 저장된 사진을 통해 OCR 판독을 진행한다. 
  3. OCR판독 진행결과를 이용해 html 파일에 뿌려준다. 


### MultipartFile 사용 

- 사용자가 업로드한 File을 핸들러에서 손쉽게 다룰 수 있게 도와주는 매개변수 중 하나이다. 
- 매개변수를 사용하기 위해서는 MultipartResolver Bean이 등록되어 있어야 한다. SpringBoot에서는 자동 등록을 지원하지만, SpringMVC에서는 기본적으로 등록해주지 않는다. 

### File 

- 하드디스크에 존재하는 파일에 대한 경로 또는 참조를 추상화한 객체로, new File() 을 통해 디렉토리를 생성할 수 있다. 
- 파일객체 사용후 반드시 Close() 해야한다. 

### 1. 파일 업로드 

- input type="file" 을 이용해 파일을 업로드 한다. 
- 업로드 버튼을 누르면 Ajax를 이용해 파일을 업로드, OCR 판별한다. 

```java
@Autowired
OCRAPI OCRapi;

@RequestMapping("/OCRresult")//OCR 영수증 인식 이용 
public Object OCRresult(MultipartHttpServletRequest filelist) {
    Object obj = null;
    String fieldName = "";
    MultipartFile mfile = null; 
    System.out.println("OCRresult 실행 ... ");

    //filelist --> 넘어온 파일 리스트
    //MultipartFile로 이름을 주어서 따로 받을 수 있다. ajax로 file1로 보냈으니 받을 때 MultipartFile file1 이런식으로도 가능하다.
    //MultipartHttpServletRequest로 받으면 한번에 객체를 통째로 받아서 나름 편하다.
    Iterator<String> iter = filelist.getFileNames(); 
    while (iter.hasNext()) { 
        fieldName = (String) iter.next(); //파일이름, 위에서 file1과 file2로 보냈으니 file1, file2로 나온다.
        mfile = filelist.getFile(fieldName);  //저장된 파일 객체

        Util.saveFile(mfile);//서버에 파일을 저장하는 모듈 

    }
    obj = OCRapi.OCRresult(mfile.getOriginalFilename());// OCR 판독 진행 
    return obj;
}
```



### 1. 파일을 dir 에 저장 

1. multipartFile 객체를 이용해 이미지를 받아온다. 
2. 저장할 Path와 이미지 이름을 합한 dir 변수를 만들어 준다. 
3. MultipartFile의 getBytes() 함수를 이용해 이미지를 byte로 변환한다. 
4. dir 변수의 경로를 FileOutputStream에 셋팅해준다. ( 저장경로 + 저장file이름)
5. FileOutputStream.write() 함수를 이용해 byte 로 변환한 이미지를 파일로 쓴다. 

```java
public static void saveFile(MultipartFile mf) {
    System.out.println("saveFile 시작 ... ");
    String filename = mf.getOriginalFilename();// 파일 이름 출력 
    String dir = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static","images","receipt",filename).toString();//이미지 저장 경로
    System.out.println("이미지 저장할 path : " + dir);

    byte [] data;

    try {
        System.out.println("이미지 저장 시작 ...");
        data = mf.getBytes();
        FileOutputStream fo = 
            new FileOutputStream(dir);//receipt 파일밑에 이미지 저장 
        fo.write(data);
        fo.close();
    }catch(Exception e) {

    }
}
```

### 2. OCR 판독 진행 

- 판독에 필요한 이미지 이름을 매개변수로 받는다. 
- 서버에 저장되어있는 사진의 경로를 가져와 OCR 판독기를 실행한다. 

```java

@Component
public class OCRAPI {
	String apiURL = "https://xtwbhr7su2.apigw.ntruss.com/custom/v1/17243/fb64d737a4729e5285737905a977c2703f72e819a7824771b610a8f612e9f6c4/infer";
	String secretKey = "d2NVcFRPemZUYnloZkVxekpPSExyYnVxSXZwQmJHY3A=";// 

	public Object OCRresult(String imgname) {
		System.out.println("OCRresult API 실행 ...");
		StringBuffer response = null;
		Object obj = null;
		System.out.println("imgname : " + imgname);
		
		
		String imgpath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static","images","receipt").toString();//이미지 저장 경로 
		String imageFile = imgpath +"\\" + imgname;//이미지 이름 
		// server 주소 : /root/apache-tomcat-8.5.27/webapps/ROOT/WEB-INF/classes/static/img

		
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
			File file = new File(imageFile);// 서버에 사진 저장되었을 경우 사용
			
			System.out.println("1 success..");
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
			response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println("REsult:");
			System.out.println("imgae result JSON : " + response);
			
			JSONParser parser = new JSONParser();
			obj = parser.parse(response.toString());
			file.delete();// 파일 삭제 


		} catch (Exception e) {
			System.out.println("OCR API Error 발생 ... ");
			System.out.println(e);
		}
		
		System.out.println(" ent success..");
		return obj;
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
			System.out.println("file isFile True... ");
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

}
```



### MultipartFile을 FIle로 변환하는 방법

- 2가지 방법이 존재한다. 

1. FileOutputStream 이용

   ```java
   public File convert(MultipartFile file)
   {    
       File convFile = new File(file.getOriginalFilename());
       convFile.createNewFile(); 
       FileOutputStream fos = new FileOutputStream(convFile); 
       fos.write(file.getBytes());
       fos.close(); 
       return convFile;
   }
   ```

2. multipartFile에 있는 transferTo() 함수 이용

   ```java
   public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
   {
       File convFile = new File( multipart.getOriginalFilename());
       multipart.transferTo(convFile);
       return convFile;
   }
   ```

## 해당 Text로 화면이동 

- 이동하는 방법은 크게 3가지가 있다. 

  1. 하이퍼링크 이용

  2. focus() 함수 이용

     - 이 방법은 input 테그에서만 동작한다. 

     - ```javascript
       $('#id').focus();
       ```

  3. scrollIntoView() 함수 이용

     - 이 방법은 모든 곳에서 사용가능하다. 하지만 jquery에서는 뒤에 [0] 을 꼭 붙여줘야한다. 

     - ```javascript
       // jquery 이용 
       $('#UploadResult')[0].scrollIntoView();
       // javascript 이용 
       document.getElementById('ID').scrollIntoView();
       ```

## 업로드한 이미지 미리보기 

- 사용방법

  1. input type="file" 을 사용한다. 
  2. 해당 input 에서 onchange="함수명(event)" 를 추가한다. 
  3. 호출한 함수에서 FileReader() 함수를 사용한다. 

- ```javascript
  function previewimg(event) {// 이미지 미리 보여주는 함수 
      var reader = new FileReader();
      reader.onload = function(event) {
          $('#uploadeimg').attr('src',event.target.result);//이미지 파일을 인식해서 브라우저에서 이미지 띄워줌 
      };
      reader.readAsDataURL(event.target.files[0]);//파일을 인식함 
  }
  
  // html
  <form id="uploadForm" enctype="multipart/form-data">
      <input type="file" name="mf" id="mf" placeholder="Enter imgname" onchange="previewimg(event);">
  </form>
  ```
