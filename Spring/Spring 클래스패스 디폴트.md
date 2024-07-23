# Spring boot 의 클래스패스 디폴트

---

>

## 클래스패스 

1. 기본적으로 프로젝트에 설정되어있는 경로로, 파일을 불러올 때 해당 경로부터 시작한다. 
2. 즉, 기본적으로 애플리케이션의 클래스패스를 기준으로 자원을 찾는다. 

### 기본 클래스 패스 

1. Spring boot 의 기본 클래스 패스는  `src/main/resources` 이다. 

2. 즉, 프로젝트내에서 파일 url 을 입력해서 불러올때, 경로 앞에가 `src/main/resource`  로 시작하는 것과 같다. 

   ```js
   // 예를들어, src -> main -> resources -> email 폴더 안에 있는 파일을 읽어온다면
   // 경로를 다음과같이 사용할 수 있다.
   ClassPathResource resource = new ClassPathResource("email/authMailTemplate.html");
   // src/main/resources/email/authMailTemplate.html 을 뜻한다. 
   ```

### 클래스패스 루트 변경 방법

#### 1. gradle 인경우

```js
sourceSets {
    main {
        resources {
            srcDirs = ['src/main/myresources']
        }
    }
}
```

#### 2. Maven인 경우 

```html
<build>
    <resources>
        <resource>
            <directory>src/main/myresources</directory>
        </resource>
    </resources>
</build>
```

#### 3. 스프링의 ResourceLoader 클래스 사용 

```java
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceService {

    @Autowired
    private ResourceLoader resourceLoader;

    public String loadResource(String path) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + path);
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
```

