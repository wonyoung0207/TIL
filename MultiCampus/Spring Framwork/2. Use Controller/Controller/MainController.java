package com.multi.controller;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@RestController
public class MainController {

		@RequestMapping("/")//root로 들어오면 실행되는 곳 
		public ModelAndView main(ModelAndView mv) {
			mv.addObject("w","Welcome!!");//w 라는 객체를 생성하여 welcome 을 넣는다. 
			mv.setViewName("main");//main이라는 이름을 가진 view로 보낸다. 
			return mv;//mv에 설정되어있는 view를 호출한다. 
		}
		
		
		
		@RequestMapping("/product_view") 
		public ModelAndView product_view(ModelAndView mv) {
			mv.setViewName("product_view");
			return mv;
		}
		
		@RequestMapping("/product") 
		public ModelAndView product(ModelAndView mv,
				@RequestParam("name") String name,
				@RequestParam("price") int price,
				@RequestParam("pro_status") String pro_status,
				@RequestParam("rank") String rank) {
			
//			int price_int = Integer.parseInt(price);
			Product product = new Product(name, price, pro_status, rank);
			
			mv.addObject("product",product);
			mv.setViewName("product");
			return mv;
		}
		
		
		
		@RequestMapping("/login_view") 
		public ModelAndView login_view(ModelAndView mv) {
			mv.setViewName("login_view");
			return mv;
		}
		
		@RequestMapping("/login") 
		public ModelAndView login(ModelAndView mv,@RequestParam("id") String id,@RequestParam("pwd") String pwd) {
			User user = new User(id, pwd);
			mv.addObject("user",user);
			mv.setViewName("login");
			return mv;
		}
		
}
