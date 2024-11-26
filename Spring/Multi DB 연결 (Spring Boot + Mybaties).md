# Multi DB 연결 (Spring Boot + Mybaties)

---

>[참고 사이트1](https://wildeveloperetrain.tistory.com/328)
>
>​	[코드참고](https://github.com/JianChoi-Kor/multidatabase)
>
>[참고 사이트2](https://jiurinie.tistory.com/136)
>
>[참고 사이트3](https://oingdaddy.tistory.com/378)
>
>[참고 사이트4](https://velog.io/@ghkvud2/Multiple-DataSource-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0)

## 1. Multi DB 연결하게된 이유

##### 문제 발생

1. 기존 타일멥 프로젝트를 진행하던 중 외부 DB(지도의 타일 관련 저장 DB) 에 있는 정보를 사용해야 했다. 
2. 따라서 기존 프로젝트 환경에서 `"기존 DB + 타일 DB"` 를 사용해야 했다. 

##### 기존 프로젝트

1. 기존 프로젝트는 Spring Boot 와 Mybaties 를 사용하는 환경이였고, 기존 구현되어있는 백엔드 코드들을 수정하지 않고 새 DB 를 연결하고 싶었다. 
2. `dispatch-servlet.xml` 파일을 통해 `Datasource` 를 `Bean` 으로 생성해 Spring Boot 가 자동으로 `JDBC 및 Mapper` 를 인식하고 있었다. 
3. 따라서 개발도중 `Mybaties 의 Mapper` 를 어떻게 Mapping 하는지에 대한 방법을 이해하기가 어려웠다. 



## 2. 개발목표 

1. **이번 기회를 통해 `Spring Boot` 의 `Bean` 생성 및 `Mybaties`의 동작 방법을 이해하며 `Mybaties`가 각각의 DB를 어떻게 Mapping 하고 인식하는지에 중점을 두고 개발을 시작**했다. 
2. **`패키지 구조와 @Mapping` 동작**에 집중해 다음 내용들을 작성해 보려고 한다.



## 3. 핵심개념

##### Multi 데이터베이스 구조

1. 여러 데이터베이스를 사용할 경우, 각 **데이터베이스마다 별도의 `DataSourceConfig`를 만들어서** 독립적인 DataSource, SqlSessionFactory, TransactionManager를 정의한다. 
2. 멀티 데이터베이스 환경에서는 각 데이터베이스에 맞는 설정을 별도로 정의하여 효율적으로 관리할 수 있게된다. 
3. **(실제로 만들때 패키지 구조에 대한 이해에 많은 시간이 소요됐다!!)**

##### Multi 데이터베이스 2가지 구현 방법 

1. dispatch-servlet.xml 에서 태그를 이용해 datasource `Bean` 생성
2. **DatasourceConfig.java 를 이용해 어노테이션 및 코드로 `Bean` 생성 (이 방법으로 소개할것임!!)**

##### 패키지 구조

1. basePackages를 이해하고 설정하는데 많은 시간이 걸렸다. 
   1. basePackages로 설정된 패키지의 하위는 모두다 특정 DB로 매핑되게한다. 
2. 예를들어, Primary 를 구현한 Datasource가 있으면 
   1. Datasource에서 `@MappScan` 을 이용해 **Mapper 인터페이스를 매핑**해줘야 하는데 이때 사용되는것이 basePackages 패키지 경로이다.
   2. 해당 패키지 하위에 있는 @Mapper는 모두 Primary XML과 연결된다.



## 4. 구현 파일 구조 

```
src
└── main
    ├── java
    │   └── com
    │        ├── primary
    │        │   ├── config
    │        │   │   ├── PrimaryDataSourceConfig.java
    │        │   ├── controller
    │        │   │   ├── UserController.java
    │        │   ├── mapper
    │        │   │   ├── UserMapper.java
    │        │   ├── service
    │        │   │   └── UserService.java
    │        └── second
    │            ├── config
    │            │   ├── SecondDataSourceConfig.java
    │            ├── controller
    │            │   ├── SecondController.java
    │            ├── mapper
    │            │   └── ExtraDataMapper.java
    │            ├── service
    │            │   └── ExtraDataService.java
    └── resources
        ├── mapper
        │   ├── primary
        │   │   ├── UserMapper.xml
        │   ├── second
        │   │   └── ExtraDataMapper.xml
        └── mybatis-config.xml
```

##### 1.`primary`

1. 주(기본) 데이터베이스를 뜻하는 디렉토리
2. 기본이라는 것은 추상적일 뿐 multi DB 중 하나라고 생각하면 된다. 
3. 즉, `primary`와 `second`는 구분될 **DB 를 나누는 기준**으로, **패키지**로 나뉜다.

##### 2.`second`

1. Multi DB 중 하나로, primary에 속한 DB 가 아닌 Second DB에 관련된 파일 패키지이다. 

##### 3.`config`

1. **데이터베이스 및 MyBatis 설정 클래스가 정의**된다. 
2. 다중 DB 를 설정하는 가장 중요한 파일로,  Spring Boot에서 **데이터베이스 연결 설정**을 정의하고 관리하는 역할을 하는 구성 클래스이다. 

##### 4.`java -> mapper`

1. **`@Mapper` 를 사용하는 mapper 인터페이스** ( MVC 패턴에서 사용되는 mapper 를 뜻함)
2. 데이터베이스와의 CRUD 작업을 수행한다. 

##### 5.`MVC`

1. Controller, Service이 포함된다. 

##### 6.`resource -> primary`

1. Primary 데이터베이스에 관련된 mybaties의 **쿼리 파일**
2. 쿼리가 모여있는 xml파일임

##### 7.`resource -> second`

1. Second 데이터베이스에 관련된 mybaties의 **쿼리 파일**

##### 8.`mybatis-config.xml`

1. 공통 MyBatis 설정 파일.



## 5. DatasourceConfig 

##### 개념

1. Spring Boot에서 **데이터베이스 연결 설정**을 정의하고 관리하는 역할을 하는 클래스이다. 

##### 사용 객체

1. DataSource
   1. 데이터베이스에 연결하기 위한 **URL, 사용자 이름, 비밀번호, 드라이버 클래스** 등의 정보를 바탕으로 `DataSource` 객체를 생성
2. SqlSessionFactory
   1. MyBatis에서 **SQL을 실행하기 위한 핵심 객체**인 `SqlSessionFactory`를 설정
3. TransactionManager
   1. 데이터베이스 **트랜잭션을 관리**하기 위한 `DataSourceTransactionManager` 객체를 생성

##### 정리

1. **데이터소스 설정**: `@ConfigurationProperties`로 외부 설정 파일을 읽어와 데이터소스를 초기화.
2. **MyBatis 설정**: `SqlSessionFactory`에 데이터소스와 Mapper 위치를 연결.
3. **트랜잭션 매니저 설정**: 데이터소스를 기반으로 트랜잭션을 관리.
4. **Mapper 인터페이스 스캔**: `@MapperScan`으로 Mapper 인터페이스를 스캔해 빈으로 등록.

##### DatasourceConfig 장점

1. 유연한 구성
   1. 데이터베이스 **설정을 코드로 관리**하므로, 다양한 데이터베이스 설정에 따라 **커스터마이징이 가능**하다.
2. 멀티 데이터베이스 지원
   1. 여러 데이터베이스를 사용하는 애플리케이션에서도 **독립적인 설정을 적용**할 수 있다.
3. 중앙 집중화
   1. 데이터베이스 관련 설정을 한곳에서 관리하므로, 유지보수성과 가독성이 향상된다.
4. MyBatis 연동
   1. MyBatis와 데이터를 연결하는 핵심 설정을 포함한다.



## 6. DatasourceConfig 클래스 예시

```java
@EnableConfigurationProperties
@Configuration
@MapperScan(
    basePackages = "com.primary", // Primary DB를 사용할 Mapper 들을 인식할 수 있는 기준 패키지(해당 패키지 하위에 있는 @Mapper는 모두 Primary XML과 연결된다.)
    sqlSessionFactoryRef = "sqlSessionFactoryPrimary" // 특정 SqlSessionFactory를 매핑
)
public class PrimaryDataSourceConfig {

    @Bean(name = "dataSourcePrimary") // datasourceBean생성 
    @Primary // Datasource의 기본 빈으로 설정함 
    @ConfigurationProperties(prefix = "primary.datasource") // application.properties 에서 설정한 값을 가져옴 
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactoryPrimary")
    @Primary
    public SqlSessionFactory sqlSessionFactoryPrimary(@Qualifier("dataSourcePrimary") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml")); // classpath: 는 프로젝트의 resource 폴더 하위를 가리킨다. 

        Resource[] resources1 = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/primary/*.xml");
        Resource[] combinedResources = ArrayUtils.addAll(resources1, resources2);
        
        // 결합된 리소스 배열을 setMapperLocations에 전달
        factoryBean.setMapperLocations(combinedResources);

        return factoryBean.getObject();
    }

    @Bean(name = "transactionManagerPrimary")
    @Primary
    public DataSourceTransactionManager transactionManagerPrimary(@Qualifier("dataSourcePrimary") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
```

## 7. 추가 학습 내용

##### dispatch-servlet.xml (중요!!!)

1. Spring MVC에서 **DispatcherServlet**의 설정 파일로, 클라이언트 요청을 적절한 컨트롤러에 라우팅하기 위한 설정을 정의한다.
2. 기본적으로 1개의 DB만 사용할때는 여기서 `Datasource Bean` 객체를 생성해 Spring이 자동으로 Injection 하도록 한다. 
   1. 하지만 이렇게했을경우 내부에서 어떻게 돌아가고 있는지 알기 힘들뿐만 아니라 컨트롤도 하기 어려워진다. 
3. **즉, 요즘은 `DatasourceConfig.java` 파일로 빼서 관리한다.** 

##### application.properties

1. Spring Boot의 설정 파일로, 애플리케이션에서 사용하는 설정값을 정의한다. 

   ```properties
   # 데이터베이스 관련 설정
   spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
   spring.datasource.username=myuser
   spring.datasource.password=mypass
   ```

##### prefix

1. 설정값을 특정 그룹으로 묶기 위해 사용.

2. application.properties에서 설정한 값을 사용하기 위해 사용

   ```java
   @ConfigurationProperties(prefix = "spring.datasource")
   public class DataSourceProperties {
       private String url;
       private String username;
       private String password;
   }
   ```

##### `@Qualifier("dataSource")`

1. Spring에서 동일한 타입의 빈(Bean)이 여러 개일 때 특정 빈을 선택하기 위해 사용

##### `@MapperScan`

1. MyBatis의 Mapper 인터페이스 위치를 스캔하고 SqlSessionFactory를 연결.

2. basePackages

   1. `@MapperScan`의 속성으로, 스캔할 패키지 경로를 지정

3. sqlSessionFactoryRef

   1. 특정 SqlSessionFactory를 매핑.

   ```java
   @MapperScan(
       basePackages = "com.primary",
       sqlSessionFactoryRef = "sqlSessionFactoryPrimary"
   )
   public class PrimaryDataSourceConfig {
       // DB의 Datasource 빈생성 
   }
   ```

##### `@Primary`

1. Spring에서 동일한 타입의 빈이 여러 개일 때 기본적으로 사용될 빈을 지정하는 데 사용
