package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductBiz pbiz;
	
	@RequestMapping("/select")
	public String select(Model m) {
		List<ProductVO> list = null;
		
		try {
			list = pbiz.get();
			m.addAttribute("productlist", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("center","product/select");
		return "index";
	}

}
