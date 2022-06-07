package com.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@Controller
@RequestMapping("/product")//이 클래스에 있는 모든 것들은 앞에 product로 처리한다. 
public class ProductController {
	
	@Autowired
	ProductBiz pbiz;
	
	@RequestMapping("/")
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left", "product/left");
		mv.addObject("center", "product/center");
		return mv;
	}
	
	@RequestMapping("/register")
	public String register(Model m) {
		
		m.addAttribute("left", "product/left");
		m.addAttribute("center", "product/register");
		
		return "main";
	}
	
	@RequestMapping("/registerimpl")
	public ModelAndView registerimpl(ModelAndView mv, ProductVO obj) {
		mv.setViewName("main");
		ProductVO product = null; 
				
		mv.addObject("left", "product/left");
		try {
			System.out.println("register error");
			System.out.println(obj);
			pbiz.register(obj);
			
			System.out.println("register error2");
			int cnt = pbiz.getcnt();
			product = pbiz.get(cnt);
			System.out.println(product);
			
			mv.addObject("center", "product/registerok");
			mv.addObject("product",product);
		} catch (Exception e) {
			e.getMessage();
			mv.addObject("center", "product/registerfail");
		}
		
			
			
		
		return mv;
	}
	
	

}
