# Day73

---

> 쿠폰기능 
>
> 로그인 후 이전 페이지 기억 

# Final Project

>쿠폰기능 
>
>로그인 후 그 전 페이지로 이동

## 로그인 전 페이지로 이동 

- 특정 페이지에서 로그인이 필요한 경우 로그인 페이지로 이동하고, 로그인 완료시 그 전 페이지로 자동 이동하는 기능 구현 

- 주요 기능은 request.getHeader("Referer") 이다. 

  - 이 기능은 Controller 에서 사용되며 그 전의 페이지 URL을 담고있다.
  - 따라서 해당 URL정보를 Session에 prevPage라는 이름으로 저장한다. 

- 저장한 URL을 이용헤 split 해서 이전화면을 redirect 하는 방식으로 진행했다. 

  - redirect 를 사용하면 뒤에오는 url을 controller 에서 호출해 띄워준다. 

- 코드

  ```java
  @RequestMapping("/signin")
  public String signin(Model m, HttpServletRequest request) {
      String uri = request.getHeader("Referer");
      System.out.println("이전 url : " + uri);
  
      if (uri != null && !uri.contains("/signinimpl")){// 이전페이지가 있으면 실행 
          request.getSession().setAttribute("prevPage", uri);// session에 prevPage 변수에 이전페이지 url 기억 
      }
      m.addAttribute("center", "signin");
      return "index";
  }
  
  
  @RequestMapping("/signinimpl")
  public String signinimpl(Model m, String id, String pwd, HttpSession session) {
      try{
              if(session.getAttribute("prevPage") != null) {
          String prevPage;
          String pageArr[];
          System.out.println("signinimpl prevPage : " + session.getAttribute("prevPage"));
          prevPage = (String) session.getAttribute("prevPage");
          pageArr = prevPage.split("/",4);// / 4개까지만 나눈다.  
          for(int i=0 ; i<pageArr.length ; i++)
          {
              System.out.println("pageArr["+i+"] : " + pageArr[i]);
          }
      }else{
          throw new Exception();
      }
      }catch(Exception e){
          return "redirect:/loginfail";
      }
  
      return "redirect:/"+pageArr[pageArr.length-1];
  }
  ```


## Controller 에서 다른 Controller 호출

- 컨트롤러에서 다른 컨트롤러를 호출하기 위해서 "redirect: " 를 이용할 수 있다. 

- redirect 는 새로고침 기능으로도 쓰이지만 url을 변경하여 해당 페이지를 호출하는데에도 쓰인다. 

  ```java
  @RequestMapping("/signinimpl")
  public String signinimpl(Model m) {
      
      // retrun "index"; // 이렇게 하면 springboot가 자동으로 resource/templates 경로에 있는 index.html 파일을 호출한다. 
      return "redirect:/siginok"; // return 으로 redirect 를 사용하면 url 을 새로고침하는 것이기 때문에 Controller에서 /siginok 을 Mapping 하고있는 곳을 호출한다. 
  }
  
  ```

  
