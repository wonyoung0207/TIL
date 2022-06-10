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
		List<CateVO> list = null;
		
		
		try {
			cate = cbiz.get(id);
			list = cbiz.getmain();//high-category 만 가져옴 
			
			m.addAttribute("highlist", list);
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
		List<CateVO> list = null;
		try {
			list = cbiz.getmain();// 상위 카테고리만 가져온다. 
			m.addAttribute("highlist", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "cate/add");
		
		return "index";
	}
	
	@RequestMapping("/addimpl")
	public String addimpl(Model m,CateVO obj) {
		
		try {
			cbiz.register(obj);
			System.out.println("cate : " + obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:detail?id=" + obj.getId();
	}
	
	@RequestMapping("/delete")
	public String delete(Model m, int id) {
		try {
			cbiz.remove(id);
			m.addAttribute("msg",null);
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("msg", "삭제할 수 없습니다.");
			return "redirect:detail?id=" + id;//삭제 안되면 다시 리로드 
		}
		
		return "redirect:select";
	}
	

}
