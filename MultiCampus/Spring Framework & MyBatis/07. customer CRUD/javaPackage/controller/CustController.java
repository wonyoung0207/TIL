package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@Controller
@RequestMapping("/cust")// cust 가 호출되면 실행되는 클래스로, cust 뒤에 아무것도 없으면 "" Mapping을 호출한다. 
public class CustController {
	
	@Autowired
	CustBiz biz;
	
	
	@RequestMapping("")
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");
		
		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/center");
		
		
		return mv;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(ModelAndView mv) {
		mv.setViewName("main");
		
		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/register");
		
		
		return mv;
	}
	
	@RequestMapping("/registerimpl")
	public ModelAndView registerimpl(ModelAndView mv, CustVO obj) {
		mv.setViewName("main");
		
		mv.addObject("left", "cust/left");
		try {
			biz.register(obj);
			mv.addObject("center", "cust/registerok");
			mv.addObject("rcust",obj);
		} catch (Exception e) {
			mv.addObject("center", "cust/registerfail");
		}
		
		return mv;
	}
	
	@RequestMapping("/get")
	public ModelAndView get(ModelAndView mv) {
		mv.setViewName("main");
		
		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/get");
		
		
		return mv;
	}
	
	@RequestMapping("/getok")
	public ModelAndView getok(ModelAndView mv, String id) {
		mv.setViewName("main");
		CustVO c = null;
		
		try {
			c = biz.get(id);
			if(c.equals(null)) {
				// 이거 해주지 않으면 c=null 상태로 getok 페이지로 넘어가서 데이터 오류남 
				throw new Exception();
			}
			mv.addObject("cust",c);
			
			mv.addObject("center", "cust/getok");
		} catch (Exception e) {
			mv.addObject("center", "cust/getfail");
		}
		
		
		
		mv.addObject("left", "cust/left");
		
		
		return mv;
	}
	
	@RequestMapping("/getall")
	public ModelAndView getall(ModelAndView mv) {
		mv.setViewName("main");
		List<CustVO> list = null;
		
		try {
			list = biz.get();
			mv.addObject("custall",list);
		} catch (Exception e) {
			mv.addObject("center", "cust/getallfail");
		}
		
		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/getall");
		
		
		return mv;
	}
	
	@RequestMapping("/custdetail")//Read
	public ModelAndView custdetail(ModelAndView mv, String id) {
		mv.setViewName("main");
		//thymeleaf의 href 를 통해 날아온 id를 매개변수가 자동으로 저장한다. 
		CustVO cust = null;
		
		try {
			
			cust = biz.get(id);
			System.out.println(cust);
			mv.addObject("cust",cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/custdetail");
		
		return mv;
	}
	
	
	@RequestMapping("/remove")
	public ModelAndView remove(ModelAndView mv,String id) {
		mv.setViewName("main");
		List<CustVO> list = null;

		try {
			biz.remove(id);
			list = biz.get();
			mv.addObject("custall",list);
			mv.addObject("center", "cust/getall");
			mv.addObject("left", "cust/left");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
//	@RequestMapping("/removeok")
//	public ModelAndView removeok(ModelAndView mv,String id) {
//		mv.setViewName("main");
//		List<CustVO> list = null;
//		
//		try {
//			biz.remove(id);
//			
//			// 모든 데이터 저장 
//			list = biz.get();
//			mv.addObject("custall",list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		mv.addObject("left", "cust/left");
//		mv.addObject("center", "cust/getall");
//		
//		
//		return mv;
//	}
	
	@RequestMapping("/update")
	public ModelAndView update(ModelAndView mv,String id) {
		mv.setViewName("main");
		CustVO cust = null;

		
		try {
			cust = biz.get(id);
			mv.addObject("cust",cust);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/update");
		
		
		return mv;
	}
	
	
	@RequestMapping("/updateok")
	public ModelAndView updateok(ModelAndView mv,CustVO cust) {
		mv.setViewName("main");
		List<CustVO> list = null;
		
		try {
			System.out.println(cust);
			biz.modify(cust);
			list = biz.get();
			mv.addObject("custall",list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/getall");
		
		
		return mv;
	}
	

	
	
	

}
