## 

## 문제 발생

- Swagger 2.9.x 버전에서 `ApiParam` 어노테이션을 int 형 변수에 사용했는데 Swagger html 접속시 에러가 발생했다. 
- ApiParam 의 값은 String인데, 변수는 Int 여서 NumberFormatException 에러를 발생시키고 있었다. 
- 하지만 `@Min(0)` 어노테이션으로 값을 int 로 변경해줘도, `int` 를 wrapper 방식인 `Integer` 로 변경해도 에러가 발생했다. 

## 문제 원인 

1. @ApiParam
   - 찾아보니 swagger 2 의 고질적인 버그라는것을 알게되었다. 
   - DTO에 붙으면 defaultValue 파싱하면서 `null → "" → NumberFormatException` 발생하는 버그 존재
   - swagger3 버전부터는 DTO = `@Schema` 를, 메소드 파라미터 = `@parameter` 를 사용하도록 변경되었다. 
   - **즉, string 타입은 괜찮지만 int 타입인 경우에는 `@ApiParam` 에 버그가 있어 Error 를 리턴할 수 있다.** 
2. @NotBlank
   - int 타입에는 NotBlank 사용 못함 

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultSearchParam {
	
	@ApiParam(value="선택한 페이지 숫자(시작 인덱스 0)")
	@NotBlank	
	protected int pageNum;
	
	@ApiParam(value="한 페이지 출력 레코드 숫자")
	@NotBlank
	protected int rowNum;
	
	@ApiParam(value="검색어")
	protected String searchWord;
	
	private int getPageNum() {
		return pageNum * rowNum;
	}
	
}

```

###### 버전별 ApiParam

| 상황                          | 어노테이션          | 사용 위치         |
| ----------------------------- | ------------------- | ----------------- |
| Swagger 2 (springfox 2.9.2)   | `@ApiParam`         | 메소드 파라미터만 |
| Swagger 2 (springfox 2.9.2)   | `@ApiModelProperty` | DTO 필드용        |
| Swagger 3 (springdoc-openapi) | `@Schema`           | DTO 필드용        |
| Swagger 3 (springdoc-openapi) | `@Parameter`        | 메소드 파라미터용 |

###### @ApiParam 이란? 

- 이 어노테이션은 전부 **Swagger API 문서 생성용 메타데이터** 로만 활용됨.
  - 즉, 실행 시 서버 로직에 아무 영향이 없음
- 문서에 파라미터 설명, 기본값, 필수 여부 등을 설명하기 위해 사용

###### ApiParam 사용 매개변수 

| 속성           | 의미                 | Swagger 문서에서 반영             |
| -------------- | -------------------- | --------------------------------- |
| `value`        | 설명문 (description) | 파라미터 옆 설명                  |
| `defaultValue` | 기본값               | Swagger UI에 기본으로 채워지는 값 |
| `example`      | 예제값               | (springfox 2.9.2에서는 불안정)    |
| `required`     | 필수여부             | 필수 표시 (UI에서 * 표시됨)       |

```java
@ApiParam(
    value = "선택한 페이지 숫자 (시작 인덱스 0)", 
    defaultValue = "0", 
    required = true
)
@Min(0)
protected Integer pageNum;
```

## ApiParam   vs   ApiModelProperty

| 구분              | `@ApiParam`                                     | `@ApiModelProperty`                                    |
| ----------------- | ----------------------------------------------- | ------------------------------------------------------ |
| 제공 라이브러리   | `springfox-swagger2` (Swagger 2)                | `springfox-swagger2` (Swagger 2)                       |
| 용도              | **메소드 파라미터 설명**                        | **DTO (모델) 필드 설명**                               |
| 사용 위치         | Controller 메소드의 직접 파라미터               | DTO 클래스의 필드                                      |
| Swagger UI 반영   | 메소드 파라미터 설명에 출력                     | Model schema 설명에 출력                               |
| 내부 파싱 시점    | request parameter 매핑할 때 사용                | model schema 생성할 때 사용                            |
| defaultValue 지원 | 있음 (`defaultValue`, `example` 등 일부 불안정) | 있음 (`example` 비교적 안정적)                         |
| required 지원     | 있음                                            | 있음 (Swagger 문서용 표시, 실질 검증은 아님)           |
| 실질 유효성 검증  | 안 함                                           | 안 함 (유효성 검증은 Bean Validation (@Min 등)이 담당) |

## 해결방법

1. DTO 에서는 api 설명시 `@ApiModelProperty` 로 선언 해야한다. 
   - 파라미터에서는 `@ApiParam` 으로 가능 
2. DTO를 받을 때 `@ModelAttribute` 를 명시적으로 선언
   - Swagger에서 DTO 내부의 `@ApiModelProperty` 들을 읽고 문서화하기 위해서 필요 

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultSearchParam {
    @ApiModelProperty(value = "선택한 페이지 숫자 (시작 인덱스 0)", example = "0", required = true)
    @Min(0)
    private int pageNum;

    @ApiModelProperty(value = "한 페이지 출력 레코드 숫자", example = "10", required = true)
    @Min(1)
    private int rowNum;

	@ApiParam(value="검색어")
	protected String searchWord;
	
	private int getPageNum() {
		return pageNum * rowNum;
	}
}
```