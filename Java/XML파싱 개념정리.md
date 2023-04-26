# XML 파일 Java로 파싱하는 방법

---

>[참고 사이트1](https://scshim.tistory.com/462)
>
>[참고 사이트２](https://sangwoo0727.github.io/java/JAVA-29_SAXParser/)

## XML 문서 파싱

### 방법

1. DOM
   1.  XML을 **트리 형태의 데이터**로 만든 후, 해당 데이터를 가공하는 방식으로 파싱을 진행한다.
   2.  XML 문서를 **메모리에 모두 로드**한 후 파싱한다.
      - 단점: 메모리를 많이 사용한다.
      - 장점: 구현과 구조변경이 쉽다.
2. SAX (Simple API for XML Parser )
   1. · XML 문서를 **라인단위로 체크**하면서 파싱하는 방법이다.
      1. 자바 API에서 제공
   2. 이러한 이유로 XML **데이터를 모두 메모리에 올려 놓지 않고 파싱**을 진행한다.
      - 장점: 메모리를 사용하는 공간이 DOM에 비하여 작다.
      - 단점: 구현과 구조 변경이 어렵다.
   3. 핸들러를 따로 구현하여 발생한 이벤트를 변수에 저장 활용하는 방식을 사용한다.  

- 따라서 한번에 Dom으로 묶어 처리하고 싶다면 Dom을 사용하고, 라인 단위로 내려가며 처리하고 싶다면 SAX 방식을 사용한다. 

---

## SAX

### 동작방법

- 기본적으로 세가지 이벤트가 발생하고, 각각의 메소드는 아래와 같다.
  1. **startElement()** : 태그를 처음 만나면, 발생하는 이벤트
  2. **endElement()** : 닫힌 태그를 만나면 발생하는 이벤트
  3. **characters()** : 태그와 태그 사이의 text(내용)을 처리하기 위한 이벤트
     - 파서가 XML 문서의 element 내부의 문자 데이터를 발견할 때 호출
     - 따라서 element (태그) 안에 값이 있을경우 실행되는 메소드이다. 

### 메서드의 매개변수 

- startElement()와 endElement() 메서드에서의 매개변수 중 localName, qName, attributes에 대해 알아본다. 

1. **localName**
   - localName은 태그의 이름을 문자열로 반환
   - 예를 들어, \<book> 태그에서 localName은 "book"(태그이름)이 된다. 
2. **qName**
   - qName은 네임스페이스를 포함한 태그의 이름을 문자열로 반환
   - 예를 들어, \<book xmlns="http://example.com"> 태그에서 qName은 "book"이 된다. 
   - qName은 XML **문서의 네임스페이스**를 식별하는 데 사용된다.
3. **attributes**
   - attributes는 **태그의 속성**을 포함하는 Attributes 객체
   - Attributes 객체는 이름-값 쌍의 집합으로, 각각의 이름과 값을 문자열로 반환한다.
   - 예를 들어, \<book **id**="1234" **author**="John Doe"> 태그에서 attributes는 **id와 author 속성의 값을 포함**하게 됩니다.

### 예시

1. xml 파일 형태

```xml
<?xml version="1.0" encoding="UTF-8"?>
<TABLE>
    <METADATA>
        <NAME>T_BASEFILE_TB</NAME>
        <COL>FILE_ID</COL>
        <COL>FILE_NAME</COL>
    </METADATA>
    <ROWS>
        <ROW>
            <FILE_ID>0</FILE_ID>
            <FILE_NAME/>
        </ROW>
        <ROW>
            <FILE_ID>1</FILE_ID>
            <FILE_NAME/>
        </ROW>
    </ROWS>
</TABLE>
```

2. File 데이터 담을 객체 

```java
public class BaseFile{ // FIleID 가지고 있는 기본제공 파일 
	private String fileId;
	private String fileName;
    
	public BaseFile() {
	}

	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
}
```

3. Handler 클래스 

```java
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class BaseFileHandler extends DefaultHandler{ // 특정 XML 파일을 열어서 태그별로 어떻게 처리할 것인지를 결정하는 클래스 
	
	//파싱한 객체를 넣을 리스트
	private List<BaseFile> list;
	
	//파싱한 객체
	private BaseFile obj;
	
	//character 메소드에서 저장할 문자열 변수
	private String str;
	
	public BaseFileHandler() {
		list = new ArrayList<>();
	}
	
	public void startElement(String uri, String localName, String name, Attributes att) {
		//시작 태그를 만났을 때 발생하는 이벤트
		if(name.equals("ROW")) { // ROW라는 태그를 만나면 실행된다. -> ROW 태그단위로 객체 생성
			obj = new BaseFile(); 
			list.add(obj);
		}
	}
	public void endElement(String uri, String localName, String name) {
		//끝 태그를 만났을 때 발생하는 이벤트 
		if(name.equals("FILE_ID")) { // ROW태그 단위로 생성한 객체안에 FILE_ID 라는 프로퍼티에 값을 넣는다. 
			obj.setFileId(str); //  str에 받은 text를 넣어준다. 
		}

	}
	public void characters(char[] ch, int start, int length) { 
		//태그와 태그 사이의 내용을 처리
		str = new String(ch,start,length); // str 를 이용해 해당 태그 안의 text를 받아온다. 
	}
    
    public List<BaseFile> getList(){
		return list;
	}
	public void setList(List<BaseFile> list) {
		this.list = list;
	}

}
```

4. File 객체 생성 후 파일 읽기 

```java
public class StartAnalysis {
	public static void main(String[] args) {
		String targetFile = "T_BASEFILE_TB.xml";
		String filePath = "./src/XML_FileAnalysis/FileAllFolder/"; // Paths.get("").toAbsolutePath()
		File file = new File(filePath + targetFile);
        
		SAXParserFactory factory = SAXParserFactory.newInstance(); // SAX를 이용하기 위해 필요 
		try {
			SAXParser parser = factory.newSAXParser(); // SAX를 이용하기 위해 필요 
			BaseFileHandler handler = new BaseFileHandler(); // handler객체 생성 
            
			parser.parse(file, handler); // 해당 파일과 파일안에 있는 태그들을 handler를 통해 제어 
			List<BaseFile> list = handler.getList(); // startElement 에 설정한 단위로 생성된 객체들이 리스트 형태로 들어가있다.
			for(BaseFile b : list) { 
				System.out.println(b);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
```

---

## Error

- org.w3c.dom.DOMException: HIERARCHY_REQUEST_ERR: 삽입이 허용되지 않는 노드를 삽입하려고 시도했습니다.

- 오류 코드

  ```java
  FileOutputStream fos = new FileOutputStream( resultFilePath + "T_" + fildId + "_TB.xml");
  DataOutputStream dos = new DataOutputStream(fos);
  
  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
  Document doc = dBuilder.newDocument();
  
  Element rootElement = doc.createElement("ROWS");// 
  doc.appendChild(rootElement); // 
  
  // rMap 에 있는 값 
  for (HashMap.Entry<String, F> entry : rMap.entrySet()) { // Map의 value에 있는 F 파일 객체 이용하기 위해
      // 가장 상위의 row 엘리먼트(태그) 생성 
      Element rowElement = doc.createElement("ROW");// fildId 만큼 ROW 가 생성되야 함 
      doc.appendChild(rootElement); // row 태그 추가  -> Error 남 
  
      Element element = doc.createElement("ROWID");// rowId로 하위 태그셍성 
      if (entry.getValue() != null) {
          element.appendChild(doc.createTextNode(entry.getValue().getRowId()+"")); // 태그 안에 들어갈 내용 추가 
      }
      rootElement.appendChild(element); // ROW 태그 밑에 추가 
  }
  
  byte[] bytes = docToString(doc).getBytes();
  fos.write(bytes);
  fos.close();
  
  ```

- 해결코드

  - 문제점:  rootElement에 rowElement를 추가해야 하는데 doc에 추가함. 

    - `doc.appendChild(rootElement)`에서 `rootElement`가 문서의 루트 요소가 되기 때문에 이후에 추가하는 모든 요소들은 이 루트 요소의 자식 요소가 되어야 한다. 
    - 이렇게되면 가장 상위 Element로 rootElement와 rowElement가 추가되는데, 이때 충돌이 발생한다. 

    ```java
    for (HashMap.Entry<String, F> entry : rMap.entrySet()) { // Map의 value에 있는 F 파일 객체 이용하기 위해
        // 가장 상위의 row 엘리먼트(태그) 생성 
        Element rowElement = doc.createElement("ROW");// fildId 만큼 ROW 가 생성되야 함 
        rootElement.appendChild(rootElement); // row 태그 추가  
    
        Element element = doc.createElement("ROWID");// rowId로 하위 태그셍성 
        if (entry.getValue() != null) {
            element.appendChild(doc.createTextNode(entry.getValue().getRowId()+"")); // 태그 안에 들어갈 내용 추가 
        }
        rootElement.appendChild(element); // ROW 태그 밑에 추가 
    }
    ```

