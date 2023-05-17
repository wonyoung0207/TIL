# Spring 동작 방식 

1. 웹 애플리케이션이 실행되면 Tomcat(WAS)에 의해 web.xml이 loading된다.
2. web.xml에 등록되어 있는 ContextLoaderListener(Java Class)가 생성된다. ContextLoaderListener 클래스는 ServletContextListener 인터페이스를 구현하고 있으며, ApplicationContext를 생성하는 역할을 수행한다.
3. 생성된 ContextLoaderListener는 root-context.xml을 loading한다.
4. root-context.xml에 등록되어 있는 Spring Container가 구동된다. 이 때 개발자가 작성한 비즈니스 로직에 대한 부분과 DAO, VO 객체들이 생성된다. 
5. 클라이언트로부터 웹 애플리케이션이 요청이 온다.
6. DispatcherServlet(Servlet)이 생성된다. DispatcherServlet은 FrontController의 역할을 수행한다. 클라이언트로부터 요청 온 메시지를 분석하여 알맞은 PageController에게 전달하고 응답을 받아 요청에 따른 응답을 어떻게 할 지 결정만한다. 실질적은 작업은 PageController에서 이루어지기 때문이다. 이러한 클래스들을 HandlerMapping, ViewResolver 클래스라고 한다.
7. DispatcherServlet은 servlet-context.xml을 loading 한다.
8. 두번째 Spring Container가 구동되며 응답에 맞는 PageController 들이 동작한다. 이 때 첫번째 Spring Container 가 구동되면서 생성된 DAO, VO, ServiceImpl 클래스들과 협업하여 알맞은 작업을 처리하게 된다.

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE sqlMapConfig
    PUBLIC "-//ibatis.apache.org//DTD SQL Map Config 2.0//EN"
    "http://ibatis.apache.org/dtd/sql-map-config-2.dtd">

<sqlMapConfig>
	<properties resource="jdbc.properties" />

	<settings cacheModelsEnabled="true"
	          enhancementEnabled="true"
		      lazyLoadingEnabled="true"
		      errorTracingEnabled="false"
		      maxRequests="32"
		      maxSessions="10"
		      maxTransactions="5"
		      useStatementNamespaces="true" />
	
	<sqlMap resource="sqlmaps/${jdbc.db}/ids_user.xml" />
	<sqlMap resource="sqlmaps/${jdbc.db}/ids_dashboard.xml" />
</sqlMapConfig>
```

### 사용 태그 정리 

1. `<?xml version="1.0" encoding="UTF-8"?>`
   - XML 파일이 버전 1.0으로 작성되었으며, UTF-8 인코딩을 사용한다는 것을 나타낸다. 
2. `<!DOCTYPE ...">`
   - 이 부분은 XML 파일의 DTD(Document Type Definition)를 정의한다. 
   - `sqlMapConfig`라는 이름의 DTD를 사용하고, 해당 DTD의 위치는 `http://ibatis.apache.org/...` 이다. 
   - DTD는 XML 문서의 구조와 유효성을 정의하는데 사용
3. `<sqlMapConfig>`:
   -  **SQL 매핑 정보의 설정을 시작하는 태그**로 해당 태그 내에 다양한 설정 요소들을 정의할 수 있다. 
4. `<properties resource="jdbc.properties" />`
   - `jdbc.properties`라는 **파일에서 속성 값을 가져오는 설정을 정의**한다. 
   - 속성 값들은 이후 SQL 매핑 파일에서 참조하여 사용할 수 있다. 
5. `<settings>`
   - **SQL 매핑 설정에 대한 다양한 옵션을 설정**하는 태그 이다. 
   - 예를 들어, 캐시 모델 사용 여부, 지연 로딩 설정, 오류 추적 여부 등을 설정할 수 있다.
6. `<sqlMap resource="sqlmaps/${jdbc.db}/ids_user.xml" />`
   - 이 태그는 **SQL 매핑 파일의 위치와 이름을 지정**한다. 
   - `${jdbc.db}`는 속성 값으로, 프로퍼티 파일에서 가져온 값이 사용되고 이렇게 설정된 SQL 매핑 파일은 해당 위치에 있는 `ids_user.xml` 파일이다.