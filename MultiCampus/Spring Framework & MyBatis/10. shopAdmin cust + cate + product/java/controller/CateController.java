package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CateBiz;
import com.multi.vo.CateVO;

@Controller
@RequestMapping("/cate")// "/Cate" 라고 들어오는 모든 것들을 처리한다. 
public class CateController {
	
	@Autowired
	CateBiz cbiz;
	
	
	// +++++++++++++++ SELECT +++++++++++++++
	
	@RequestMapping("/select")
	public String select(Model m) {
		List<CateVO> list = null;
		
		try {
			list = cbiz.get();
			m.addAttribute("catelist", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		m.addAttribute("center","cate/select");
		
		return "index";
	}
	
	
	@RequestMapping("/detail")
	public String detail(Model m,int id) {
//		System.out.println("id : "+id);
		CateVO cate = null;
		
		try {
			cate = cbiz.get(id);
			
			m.addAttribute("cate", cate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center","cate/detail");
		
		return "index";
	}

	
	
	@RequestMapping("/lowupdate")
	public String lowupdate(Model m,CateVO obj) {
		try {
			cbiz.modify(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:detail?id=" + obj.getId();
	}
	
	
	// +++++++++++++++ ADD +++++++++++++++
	
	@RequestMapping("/add")
	public String add(Model m) {
		m.addAttribute("center", "Cate/add");
		
		return "index";
	}
	
	@RequestMapping("/addimpl")
	public String addimpl(Model m,CateVO obj) {
		
		try {
			cbiz.register(obj);
			System.out.println("Cate : " + obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:detail?id=" + obj.getId();
	}



}
