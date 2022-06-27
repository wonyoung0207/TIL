package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.ProductBiz;
import com.multi.biz.ReviewBiz;
import com.multi.biz.Shoes_cntBiz;
import com.multi.frame.Util;
import com.multi.vo.Filter;
import com.multi.vo.ProductVO;
import com.multi.vo.ReviewVO;
import com.multi.vo.Shoes_cntVO;


@Controller
public class ProductController {
	
	@Autowired
	ProductBiz pbiz;
	
	@Autowired
	Shoes_cntBiz sbiz;
	
	@Autowired
	ReviewBiz rbiz;
	
	@RequestMapping("/product")
	public String product(Model m,String gender) {
		List<ProductVO> list3 = null;
		Filter f = new Filter("G", gender, null, 0, 0, null, null,0);
		int msg = 0;
		try {
			list3 = pbiz.getfilter(f);
			if(list3.isEmpty()) {
				m.addAttribute("msg", msg);
			}else {
				msg=1;
				m.addAttribute("plist", list3);
				m.addAttribute("msg", msg);
			}
		} catch (Exception e) {
			e.printStackTrace(); // 오류페이지 제작 필요
		}
		m.addAttribute("center", "product/center");
		m.addAttribute("footer", "footer");
		m.addAttribute("header", "header");
		return "index";
	}
	
	@RequestMapping("/productdetail")
	public String productdetail(Model m, int id) {
		ProductVO obj = null;
		List<Shoes_cntVO> list1 = null;
		List<ReviewVO> list2 = null;
		int staravg = 0;
		int reviewcount = 0;
		
		try {
			obj = pbiz.get(id);
			list1 = sbiz.getproduct(id);
			list2 = rbiz.getproduct(id);
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			staravg = rbiz.getstaravg(id);
			reviewcount = rbiz.getreviewcnt(id);
			
		} catch(Exception e) {
			staravg = 0;
			reviewcount = 0;
		}
		
		m.addAttribute("detailproduct", obj);
		m.addAttribute("slist", list1);
		m.addAttribute("rlist", list2);
		
		m.addAttribute("staravg", staravg);
		m.addAttribute("reviewcount", reviewcount);
		
		
		m.addAttribute("center", "product/detail");
		m.addAttribute("footer", "footer");
		m.addAttribute("header", "header");
		
		return "index";
	}
	
	@RequestMapping("/addfilter")
	public String addfilter(Model m, String type, String gender,
			String cid, Integer param1, Integer param2, String color, String size,Integer sortby) {
		Filter f = new Filter(type, gender, cid, param1, param2, color, size, sortby);
		int msg = 0;
		
		List<ProductVO> list = null;
		try {
			list = pbiz.getfilter(f);
			if(list.isEmpty()) {
				m.addAttribute("msg", msg);
			}else {
				msg = 1;
				m.addAttribute("plist", list);
				m.addAttribute("msg", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/product/center :: #productTable";
	}
	
	// 6.25 추가
	@RequestMapping("/addreview")
	public String addreview(ReviewVO obj, Model m) {
		int pid = obj.getPid();
		
		String filename = obj.getMf().getOriginalFilename();
		obj.setFilename(filename);
		try {
			rbiz.register(obj); 
			Util.saveFile(obj.getMf());
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		
		return "redirect:/productdetail?id="+pid;
	}

}
