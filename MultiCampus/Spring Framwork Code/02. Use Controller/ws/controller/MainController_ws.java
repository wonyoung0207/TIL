package com.multi.controller;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@RestController
public class MainController_ws {

		@RequestMapping("/ws")//root로 들어오면 실행되는 곳 
		public ModelAndView main(ModelAndView mv) {
			mv.addObject("w","Welcome!!");//w 라는 객체를 생성하여 welcome 을 넣는다. 
			mv.setViewName("ws_main");//main이라는 이름을 가진 view로 보낸다. 
			return mv;//mv에 설정되어있는 view를 호출한다. 
		}

		@RequestMapping("/wslogin_view") 
		public ModelAndView wslogin_view(ModelAndView mv) {
			mv.setViewName("wslogin_view");
			
			return mv;
		}
		
		@RequestMapping("/wslogin") 
		public ModelAndView login(ModelAndView mv,@RequestParam("id") String id,@RequestParam("pwd") String pwd) {
			if(id.equals("id01") && pwd.equals("1234")) {//정상 로그인이면 실행
				mv.addObject("user",new User(id,pwd));
				mv.setViewName("wslogin_success");
			}else {				
				mv.addObject("user",new User());
				mv.setViewName("wslogin_fail");
			}
			return mv;
		}
		
		
		@RequestMapping("/wsProductList_view") 
		public ModelAndView wsProductList_view(ModelAndView mv) {
			mv.setViewName("wsProductList_view");
			
			return mv;
		}
		
		
		@RequestMapping("/wsproduct")
		public ModelAndView wsproduct(ModelAndView mv,
				@RequestParam("name1") String name1,
				@RequestParam("price1") int price1,
				@RequestParam("status1") String status1,
				@RequestParam("rank1") String rank1,
				@RequestParam("name2") String name2,
				@RequestParam("price2") int price2,
				@RequestParam("status2") String status2,
				@RequestParam("rank2") String rank2){
			
			Product product1 = new Product(name1, price1, status1, rank1);
			mv.addObject("product1",product1);
			
			Product product2 = new Product(name2, price2, status2, rank2);
			mv.addObject("product2",product2);
			
			mv.setViewName("wsproduct");
			return mv;
			
		}
		
		
}
