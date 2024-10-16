# JSON to DTO 변환방법

---

>[참고 사이트1](https://ydontustudy.tistory.com/151)
>
>[GSON 참고사이트1](https://hianna.tistory.com/629)

## DTO 

1. DB데이터에 대해 쿼리 조회 결과를 myBatis를 이용해 java의 데이터 객체로 변환할 때 사용

### 공부하게된 이유 

1. DB의 데이터 타입에 JSON 객체가 잇는경우에 DTO 의 어떤 데이터 형을 사용해야 편리한지 궁금증이 생겼다. 
2. String, Map, JsonNode ... 등등의 여러 방법들이 있었는데, 해당 방법들에 대해 공부해보려고 한다. 

### 해결 방법

1. 나같은 경우는 GSON 라이브러리를 사용해서 해결했다. 
2. 먼저 Mybatis의 쿼리 결과인 JSON데이터 타입을 String으로 받았고, MVC의 Service 단에서 GSON 라이브러리를 사용해 Map 형태로 변경해주었다. 



## 시도한 방법들

### 1. String 타입으로 받기 

1. 먼저 DT의 JSON 타입 데이터를 String 타입으로 받아봤다. 
2. 결과는 다음과 같이 `\` 로 인해 보기도 힘들고 다루기도 힘든String 형식이였다. 
   1. 따라서 String 방식이 아닌 다른 방식으로 사용해보기위해 알아봤다.

```js
"{\"ver\": \"1.0\", \"cam_id\": \"C0210\", \"events\": {\"numObj\": 3, \"objects\": [{\"id\": \"E01_1\", \"ph\": 200, \"pw\": 50, \"px\": 101, \"py\": 10, \"type\": \"person\", \"speed\": 3.2, \"altitude\": \"0\", \"latitude\": \"37.566380\", \"pos_area\": \"D3\", \"direction\": 2, \"longitude\": \"126.977902\", \"event_code\": \"E01\", \"image_path\": \"192.168.0.1.D.1.jpg\", \"video_path\": \"192.168.0.1.D.video.mp4\", \"event_timestamp\": \"2022-01-11 10:35:00.123\", \"object_timestamp\": \"2022-01-11 10:35:00:100\"}, {\"id\": \"E11_1\", \"ph\": 300, \"pw\": 80, \"px\": 200, \"py\": 100, \"type\": \"car\", \"speed\": 20.5, \"altitude\": \"0\", \"latitude\": \"35.179572\", \"pos_area\": \"F2\", \"direction\": 5, \"longitude\": \"129.075577\", \"event_code\": \"E11\", \"image_path\": \"192.168.0.1.D.2.jpg\", \"video_path\": \"192.168.0.1.D.video.mp4\", \"event_timestamp\": \"2022-01-11 10:35:00.123\", \"object_timestamp\": \"2022-01-11 10:35:00:100\"}]}, \"infra_id\": \"AK021CCT0131\", \"cam_width\": 1920, \"cam_height\": 1080, \"send_timestamp\": \"2022-01-11 10:35:00.123\"}"
```

### 2. GSON 라이브러리 이용 

1. Google에서 제공하는 Java 라이브러리로, Java 객체를 JSON으로 변환하거나 JSON을 Java 객체로 변환하는 데 사용된다.
   1.  Java 컬렉션(`List`, `Map` 등)이나 제네릭 타입도 쉽게 처리할 수 있다.
2. 주로 JSON을 처리하는 애플리케이션에서 사용되며, 간편하게 객체를 직렬화하거나 역직렬화할 수 있는 기능을 제공한다.
   1. 직렬화
      1. Java 객체를 JSON으로 변환 
   2. 역직렬화
      1. JSON을 Java 객체로 변환 

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

### 3. jackson 라이브러리이용

1. jackson은 JSON 관련 라이브러리이다.
2. 해당 라이브러리 이용하면 JSON 데이터를 **`JsonNode`**나 다른 객체로 변환할 수 있다.

```java
// vo
public class CallVo {
    private JsonNode filePassList;  // Jackson의 JsonNode로 받음
}
```

### 4. typehandler 사용 

1. xml 에서 resultMap 에 있는 필드에 typehandler 를 적용하는 방법
   1. typehandler 에 사용할 vo 를 만들어줘야 한다.

```xml
// xml
<resultMap id="HomeDisplayedBookVO" type="com.myproject.doseoro.packages.book.vo.HomeDisplayedBookVO">
    <result property="title" column="title"></result>
    <result property="about" column="about"></result>
    <result property="price" column="price"></result>
    <result property="images" column="img" typeHandler="com.myproject.doseoro.global.util.JsonTypeHandler"></result>
</resultMap>
<select id="findHomeDisplayedBooks" resultMap="HomeDisplayedBookVO">
    SELECT
        title,
        about,
        price,
        img
    FROM t_book
    ORDER BY createdAt
    DESC  LIMIT 5
</select>
```

```java
// vo
@MappedTypes({Object.class})
public class JsonTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> s, JdbcType jdbcType) throws
            SQLException {
        preparedStatement.setObject(i, new Gson().toJson(s));

    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return convertToList(resultSet.getString(s));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return convertToList(resultSet.getString(i));
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return convertToList(callableStatement.getString(i));
    }

    private List<String> convertToList(String myJsonDataListAsString) {
        try {
            return new ObjectMapper().readValue(myJsonDataListAsString, new TypeReference<List<String>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
```

### 5. JsonProperty 어노테이션 이용

1.  **Jackson 라이브러리**에서 사용되는 어노테이션으로, JSON 필드와 Java 클래스의 필드 이름을 매핑할 때 사용한다. 
1.  작은 json 형태라면 매핑할 수 있지만 크고 여러가지 형태라면 사용이 어렵다. 

```java
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    // JSON에서 "user_name"이라는 필드와 매핑됨
    @JsonProperty("user_name")
    private String username;

    // JSON에서 "age"라는 필드와 매핑됨
    @JsonProperty("age")
    private int age;
    
    // 생성자에 @JsonProperty 사용
    public User(@JsonProperty("user_name") String username, 
                @JsonProperty("age") int age) {
        this.username = username;
        this.age = age;
    }
}

// json형태
{
    "user_name": "John",
    "age": 30
}

```

```java
User user = objectMapper.readValue(jsonData, User.class);
System.out.println(user.getUsername());  // 출력: John
System.out.println(user.getAge());       // 출력: 30
```





