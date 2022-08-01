# Day79

---

# Final Project

>어노테이션 GetMapping 과 RequestMapping 차이점

## OAuth 로그인 

#### 어노테이션 GetMapping 과 RequestMapping 차이점

-  @RequestMapping은 method를 생략하면 GET 방식과 POST 방식을 모두 처리해준다. 어찌 보면 @RequestMapping만 쓰는 게 젤 편한 거다. 결국 @GetMapping과 @PostMapping을 사용하는 이유는 @RequestMapping에 굳이 method를 명시하는 이유와 같다고 볼 수 있다.

- `@GetMapping`과 `@PostMapping` 어노테이션은 `@PutMapping`, `@DeleteMapping`, `@PatchMapping`과 함께 스프링 4.3부터 등장

  ```java
  // requestMapping
  @RequestMapping(value="경로", method=RequestMethod.GET)
  @RequestMapping(value="경로", method=RequestMethod.POST)
  
  //get, post Mapping
  @GetMapping("경로")
  @PostMapping("경로")
  ```

- 정리하자면, RequestMapping을 사용하지 않고 GetMapping과 PostMapping을 사용하는 이유는 url 요청을 통해 메서드를 요청할 때 전송 방식을 하나의 url로 두개 이상의 매핑을 처리할 수 있기 때문이다. 

  ```java
  //만약 @RequestMapping("/insertBoard")으로 GET 방식의 요청을 받았다면 POST 방식의 요청은 다른 url을 써야한다
  @getMapping("/insertBoard")
  @PostMapping("/insertBoard")
  ```

  
