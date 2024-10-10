# GSON 라이브러리 개념정리

---

>[참고 사이트1](https://hianna.tistory.com/629)
>
>[참고 사이트2](https://velog.io/@alsgus92/JavaKotlin-Gson-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC%EA%B0%80-%EC%A0%9C%EA%B3%B5%ED%95%98%EB%8A%94-%EC%A3%BC%EC%9A%94-API-%EA%B0%80%EC%9D%B4%EB%93%9C)
>
>[참고 사이트3](https://re-build.tistory.com/41)

## GSON 이란? 

1. Google에서 제공하는 Java 라이브러리로, Java 객체를 JSON으로 변환하거나 JSON을 Java 객체로 변환하는 데 사용된다.
2.  Java 컬렉션(`List`, `Map` 등)이나 제네릭 타입도 쉽게 처리할 수 있다.

### 특징

1. 주로 JSON을 처리하는 애플리케이션에서 사용되며, 간편하게 객체를 직렬화하거나 역직렬화할 수 있는 기능을 제공한다.
2. 직렬화
   1. 객체를 JSON으로 변환
3. 역직렬화
   1. JSON을 객체로 변환

### 주요 메소드

1. **fromJson() : 역직렬화**
   1. JSON 형식의 데이터를 **지정한 타입의 데이터로 변환**
2. **toJson() : 직렬화**
   1. 지정된 타입의 데이터를 **JSON 형식의 데이터로 변환**

### 사용방법

```xml
<!-- mybatis 쿼리 -->
<select id='formatterJsonList' resultType="FormatterJson">
    select 
        VERIFI_ID,
        JsonField ,
        SUCCESS 
</select>
```

```java
// DTO
public class  FormatterJson {
	@ApiModelProperty(value = "ID(12)")
	private String Id;

    @ApiModelProperty(value = "성공, 실패 여부")
    private boolean success;

    @ApiModelProperty(value = "문자열")
    private String jsonField;
    
    @ApiModelProperty(value = "JSON형태 맵")
    private Map<String, Object> stringToJson;
}
```

```java
// Service
public List<FormatterJson> formatterJsonList() {
		Gson gson = new Gson();
		List<FormatterJson> formatterJsonList = formatterJsonMapper.formatterJsonList();
	
		// JSON 데이터인 filePathList를 변환하여 DTO에 저장
        formatterJsonList.forEach(formatterJson -> {
            try {
                String str = formatterJson.getJsonField(); // JSON 데이터를 String으로 받음
            	Map<String, Object> map = gson.fromJson(FilePathListSting, Map.class);

                formatterJson.setStringToJson(map); // 변환된 데이터를 DTO에 설정
            } catch (Exception e) {
                e.printStackTrace(); // 오류 처리
            }
        });
		return formatterJsonList;
	}
```

### 