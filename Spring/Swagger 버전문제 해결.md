## Swagger 버전문제 해결 

---

>

## 문제발생 

1. Swagger 사용하려고 했는데 OpenAPI 2 & 3 버전에서 사용하는 태그 이름이 달랐다. 
   1. Swagger 3 을 사용하려고 했지만 기존 프로젝트에서 합쳐야 하는게 있어 버전을 맞춰줘야했다. 
   2. 기존 : springfox version 3 사용 
   3. **그런데 기존 프로젝트에서 Swagger 3.0 의 일부 기능을 사용중이였다. (springfox version 3는 Swagger2 만 지원하는줄 알았음...)**
2. 또한 swagger 를 사용하기 위해 OpenAPI 버전에 대해 공부하게 됐다. 

## 결론

1. Springfox version 3 은 기본 swagger 2.0 을 지원 하지만
2. `SwaggerConfig` 에서 docket 에 `DocumentationType.OAS_30` 을 하면 OpenAPI 3.x 의 기능 일부를 사용할 수 있다. 
   1. 대표적인 어노테이션 : `@Schema`  (OpenAPI 2.0 에서는 사용못하고, OpenAPI 3.x 에서만 사용할 수 있음 )

## Swagger 버전 

| 구분                    | 버전   | 설명                                                         |
| ----------------------- | ------ | ------------------------------------------------------------ |
| **스펙(Specification)** |        |                                                              |
| Swagger 2.0             | 2.0    | 기존 “Swagger” 명칭 사용, JSON/YAML 스펙에 `"swagger": "2.0"` 로 표시 |
| OpenAPI 3.0             | 3.0.x  | Swagger 3.0 → OpenAPI 3.0 으로 명칭 변경, 컴포넌트·보안·콜백 지원 강화 |
|                         |        |                                                              |
| **Springfox**           |        |                                                              |
| 2.x                     | 2.9.2  | Swagger 2.0 기반, Spring Boot 1.x/2.x 지원                   |
| 3.0.0                   | 3.0.0  | 기본은 Swagger 2.0, 일부 OpenAPI 3.0 지원 추가               |
|                         |        |                                                              |
| **springdoc-openapi**   |        |                                                              |
| 1.6.x                   | 1.6.14 | Spring Boot 2.x 용, OpenAPI 3.0 지원                         |
| 2.1.x                   | 2.1.0  | Spring Boot 3.x 용, OpenAPI 3.0/3.1 지원                     |

## swagger 라이브러리 이용 방법 

1. **Boot Starter 하나로 포함**

   ```groovy
   // springfox-boot-starter 3.x 부터는 ui 자동 imp 해줌
   // 이 한 줄로 Swagger 2, Swagger UI, 그리고 OpenAPI 3 지원이 모두 로드
   implementation 'io.springfox:springfox-boot-starter:3.0.0'
   
   // 만약 swagger 2 만 사용하려면 별도 선언 필요 
   implementation 'io.springfox:springfox-swagger2:2.9.2'
   implementation 'io.springfox:springfox-swagger-ui:2.9.2'
   ```

2. **OAS 3 사양 활성화**
    
    - 기본 `Docket` 빈을 생성할 때 `DocumentationType.OAS_30` 을 지정하면, Springfox가 OpenAPI 3 형식의 문서(`/v3/api-docs`)와 UI(`/swagger-ui/index.html`)를 자동 생성해 준다. 

   ```java
   @Configuration
   public class SwaggerConfig {
     @Bean
     public Docket api() {
       return new Docket(DocumentationType.OAS_30)  // ← OpenAPI 3.0 (Springfox 3.x 사용해도 이렇게 하면 OpenAPI 3.x 일부 지원기능 사용가능함 )
         .select()
         .apis(RequestHandlerSelectors.any())
         .paths(PathSelectors.any())
         .build();
     }
   }
   ```

3. **기존 Swagger 2 그대로도 사용 가능**
    
    - 만약 `DocumentationType.SWAGGER_2` 를 지정하면 이전처럼 Swagger 2 스펙으로도 문서를 만들 수 있으며, OAS_30과 SWAGGER_2를 동시에 한 프로젝트에서 분리해 쓸 수도 있다. 
    
    ```java
    @Configuration
    @EnableSwagger2
    public class SwaggerConfig {
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2) // OpenAPI 2.0
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
        }
    }
    ```

- 요약하자면, `springfox-boot-starter:3.x` 에서는 OpenAPI 3 사양을 사용할 수 있고, `Docket(DocumentationType.OAS_30)` 으로 활성화하면 된다. 



