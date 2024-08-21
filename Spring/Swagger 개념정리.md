# Swagger 개념정리 

---

>[참고 유튜브](https://www.youtube.com/watch?v=Q27PGBYmHNA)

## Swagger 

### 정의 

- REST api를 기반으로 하는 api 명세를 위한 도구이다. 
- 특정 프로그램에 존재하는 api(서비스 기능 ) **기능을 명세**하고 **테스트** 할 수 있도록 도와주는 도구이다. 

  - 즉, **백엔드와 연결**하여 **수정**시 해당 **서비스 기능에 필요한 파라미터**가 즉각적으로 변화하여 어떤 형태의 파라미터가 필요한지를 알수 있고, 파라미터로 **서버에 전송해 정상작동하는지 테스트**할 수 있는 도구라고 생각하면 된다. 
- `스웨거 허브` 나 local 에 모듈을 받아 사용할 수 있다. 

### 사용 조건 

1. 사용하고 있는 Server(백엔드) 에 어노테이션으로 Swagger 를 이용해야 한다. 
2. Swagger 어노테이션을 이용해 어려가지 정보를 기입해야한다. 
   1. 따라서 소스 코드에 관리해야할게 많아진다는 단점이 있다. 

### Swagger 어노테이션 적용 예시 

1. **Model** 

   ```java
   import io.swagger.annotations.ApiModelProperty;
   
   @Data
   @Builder
   @NoArgsConstructor
   @AllArgsConstructor
   public class TbCmCall {
   
       @NotNull
       @ApiModelProperty(value = "실시간 호출 ID")
       private String CallId;
   
       @NotBlank
       @Length(max = 12)
       @ApiModelProperty(value = "차량 ID")
       private String vehicleId;
   
       @ApiModelProperty(value = "도착 위도")
       private Double endLatitude;
   
       @ApiModelProperty(value = "도착 경도")
       private Double endLongitude;
   }
   ```

2. **Controller**

   ```java
   import io.swagger.annotations.Api;
   import io.swagger.annotations.ApiOperation;
   import io.swagger.annotations.ApiParam;
   import io.swagger.annotations.ApiResponse;
   import io.swagger.annotations.ApiResponses;
   
   @Slf4j
   @Api(tags = "실시간 호출")
   @RestController
   @RequestMapping("/call")
   public class Controller {
   	
   	@Autowired
   	TbcallServiceImpl tbcallService;
   	
       // GET
       @ApiOperation(httpMethod = "GET", value = "실시간 상세 조회", notes = "실시간 상세정보를 가져옴")
       @ApiResponses({ @ApiResponse(code = 200, message = "API 정상 작동"), @ApiResponse(code = 500, message = "서버 에러") })
       @PermitAll
       @GetMapping("/{callId}")
       public TbCmcall findTbcallById(@PathVariable("callId") @ApiParam(value = "실시간 호출 ID") String callId) {    	
           return tbCmcallService.findTbcallById(callId);
       }
       
      	// POST
       @ApiOperation(httpMethod = "POST", value = "불안감 등록",  notes = "{ headers: { 'Content-Type': 'multipart/form-data' } } 사용")
   	@ApiResponses({ @ApiResponse(code = 200, message = "API 정상 작동"), @ApiResponse(code = 500, message = "서버 에러") })
       @PostMapping("/anxiety")
   	public void requestAnxiety(@ApiParam(value = "불안감 등록", required = true) @RequestBody @Valid TbInsecurityInfo tbInsecurityInfo){
   		tbcallService.insertInsecurity(tbInsecurityInfo);
   	}   
   }
   ```

### 스웨거 허브 ( 웹 사이트 )  접속 방법

1. 웹 사이트를 이용해 swagger 를 사용할 수 있다. 

   1. 백엔드에 어노테이션으로 지정해 놓으면 Swagger 웹사이트가 해당 IP와 Port 에 설정되어있는 Swagger 어노테이션을 자동인식한다.  

2. 웹 사이트 주소 format

   ```html
   <!-- 구조 -->
   http://[사용자 서비스IP]:[Server Port]/swagger-ui/index.html#/
   
   <!-- 사용 예시 -->
   http://127.0.0.1:8080/swagger-ui/index.html#/
   ```

### 스웨거 허브 웹사이트

<img src="./images/swagger_web1.png" width="700">

<img src="./images/swagger_web2.png" width="700">

<img src="./images/swagger_web3.png" width="700">

<img src="./images/swagger_web4.png" width="700">
