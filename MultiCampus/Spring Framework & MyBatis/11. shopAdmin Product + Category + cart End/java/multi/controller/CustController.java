package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@Controller
@RequestMapping("/cust")// "/cust" 라고 들어오는 모든 것들을 처리한다. 
public class CustController {
	
	@Autowired
	CustBiz cbiz;
	
	
	// +++++++++++++++ SELECT +++++++++++++++
	
	@RequestMapping("/select")
	public String select(Model m) {
		List<CustVO> list = null;
		
		try {
			list = cbiz.get();
			m.addAttribute("custlist", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		m.addAttribute("center","cust/select");
		
		return "index";
	}
	
	
	@RequestMapping("/detail")
	public String detail(Model m,String id) {
//		System.out.println("id : "+id);
		CustVO cust = null;
		
		try {
			cust = cbiz.get(id);
			
			m.addAttribute("cust", cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		m.addAttribute("center","cust/detail");
		
		return "index";
	}

	
	
	@RequestMapping("/update")
	public String custupdate(Model m,CustVO obj) {
//		System.out.println("update obj : " + obj);
		try {
			cbiz.modify(obj);
			
//			m.addAttribute("cust", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		m.addAttribute("center","cust/select");
		return "redirect:detail?id=" + obj.getId();
//		return "index";
	}
	
	
	// +++++++++++++++ ADD +++++++++++++++
	
	@RequestMapping("/add")
	public String add(Model m) {
		m.addAttribute("center", "cust/add");
		
		return "index";
	}
	
	@RequestMapping("/addimpl")
	public String addimpl(Model m,CustVO obj) {
		
		try {
			cbiz.register(obj);
			System.out.println("cust : " + obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:detail?id=" + obj.getId();
	}



}
