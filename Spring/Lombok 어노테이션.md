# Lombok 어노테이션 

---

>

## Lombok 

### Lombok이란?

1. Java 애플리케이션 개발에서 반복적인 코드를 줄여주기 위해 사용되는 라이브러리이다. 
2. 어노테이션을 사용하여 자동으로 getter, setter, 생성자, toString, equals, hashCode 메서드 등을 생성해준다. 
3. 코드가 더 간결해지고, 유지보수가 용이해지는 장점을 가진다. 

### Gradle 설정

```gradle
implementation 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'
```



## Lombok 어노테이션 종류

### `@Data`

1. **자동으로 getter, setter, 생성자 등을 생성**

   ```java
   // 생성되는 어노테이션 
   @Getter`와 `@Setter
   @ToString
   @EqualsAndHashCode
   @RequiredArgsConstructor
   ```

2. 사용법

   ```java
   import lombok.Data;
   
   @Data
   public class MyClass {
       private final String name;
       private int age;
   }
   ```

### @Builder

1. **빌더 패턴을 구현하는 코드를 자동으로 생성**

2. 빌더 패턴이란, 객체 생성 패턴 중 하나로, 객체의 생성 과정에서 복잡한 초기화 로직을 단순하고 유연하게 처리할 수 있도록 도와준다. 

3. 주로 여러 필드를 가진 객체를 생성할 때 유용하며, 특히 생성자의 인자 개수가 많거나 복잡한 초기화 로직이 필요할 때 사용된다. 

4. 장점

   1. **가독성**: 빌더 패턴을 사용하면 객체 생성 코드가 더 읽기 쉬워진다. 
   2. **유연성**: 필요한 필드만 설정할 수 있으며, 순서에 상관없이 필드를 설정할 수 있다. 
   3. **불변성**: 생성되는 객체를 불변 객체로 만들기 쉽다. 

5. @builder 이용하지 않은 경우 

   ```java
   public class Person {
       private final String firstName;
       private final String lastName;
       private final int age;
       private final String email;
   
       private Person(Builder builder) {
           this.firstName = builder.firstName;
           this.lastName = builder.lastName;
           this.age = builder.age;
           this.email = builder.email;
       }
   
       public static class Builder {
           private String firstName;
           private String lastName;
           private int age;
           private String email;
   
           public Builder firstName(String firstName) {
               this.firstName = firstName;
               return this;
           }
   
           public Builder lastName(String lastName) {
               this.lastName = lastName;
               return this;
           }
   
           public Builder age(int age) {
               this.age = age;
               return this;
           }
   
           public Builder email(String email) {
               this.email = email;
               return this;
           }
   
           public Person build() {
               return new Person(this);
           }
       }
   
       // getters, toString, etc.
   }
   
   // 객체 생성
   Person person = new Person.Builder()
       .firstName("John")
       .lastName("Doe")
       .age(30)
       .email("john.doe@example.com")
       .build();
   ```

6. @builder 이용한 경우

   ```java
   import lombok.Builder;
   import lombok.Getter;
   import lombok.ToString;
   
   @Getter
   @ToString
   @Builder
   public class Person {
       private final String firstName;
       private final String lastName;
       private final int age;
       private final String email;
   }
   
   // 객체 생성
   Person person = Person.builder()
       .firstName("John")
       .lastName("Doe")
       .age(30)
       .email("john.doe@example.com")
       .build();
   ```

### @NoArgsConstructor

1. 파라미터가 없는 기본 **생성자를 생성**


### @AllArgsConstructor

1. 모든 필드를 파라미터로 받는 **생성자를 생성**


### @RequiredArgsConstructor

1. `final` 또는 `@NonNull` 필드를 파라미터로 받는 **생성자를 생성**