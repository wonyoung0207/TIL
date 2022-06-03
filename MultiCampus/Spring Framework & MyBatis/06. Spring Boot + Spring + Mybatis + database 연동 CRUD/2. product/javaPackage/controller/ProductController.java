package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@Controller
public class ProductController {
	@Autowired
	ProductBiz pbiz;
	
	@RequestMapping("/productadd")
	public String productadd(Model m) {
		
		m.addAttribute("ccenter", "productadd");
		
		return "main";
	}
	
	@RequestMapping("/productaddimpl")//Create
	public ModelAndView productaddimpl(ModelAndView mv,ProductVO product) {
		//자동으로 id, pwd, name이 productVO 클래스에 매칭되어 저장된다. 
		
		String page = "productaddok";
		System.out.println(product);
		
		try {
			pbiz.register(product);
			mv.addObject("rproduct",product);
		} catch (Exception e) {
			page = "productaddfail";
			e.printStackTrace();
		}
		
		mv.setViewName(page);
		
		return mv;
	}
	
	@RequestMapping("/productselect")//ReadAll
	public ModelAndView productselect(ModelAndView mv) {
		List<ProductVO> list = null;
		
		try {
			list = pbiz.getAll();
			mv.addObject("allproduct",list);
		} catch (Exception e) {
			
		}
		
		mv.setViewName("productselect");
		return mv;
	}
	
	
	@RequestMapping("/productdetail")//Read
	public ModelAndView productdetail(ModelAndView mv, int id) {
		//thymeleaf의 href 를 통해 날아온 id를 매개변수가 자동으로 저장한다. 
		ProductVO product = null;
		
		try {
			product = pbiz.get(id);
			mv.addObject("dproduct",product);
		} catch (Exception e) {
			
		}
		
		mv.setViewName("productdetail");
		return mv;
	}
	
	
	@RequestMapping("/productDelete")//Delete
	public String productdelete(int id) {
		try {
			pbiz.remove(id);
		} catch (Exception e) {
		}
		
		return "redirect:productselect";//productselect 를 새롭게 다시 요청한다. 
	}
	
	@RequestMapping("/productUpdate")//Read
	public ModelAndView productUpdate(ModelAndView mv,int id) {
		ProductVO product = null;
		try {
			product = pbiz.get(id);
			mv.addObject("uproduct",product);
		} catch (Exception e) {
		}
		mv.setViewName("productUpdate");
		
		return mv; 
	}
	
	@RequestMapping("/productUpdateimpl")//Update
	public String productUpdateimpl(ProductVO product) {
		try {
			pbiz.modify(product);
			
		} catch (Exception e) {
		}
		
		return "redirect:productdetail?id=" + product.getId(); 
	}
	
}
