package com.multi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CartBiz;
import com.multi.biz.MainBiz;
import com.multi.biz.ProductBiz;
import com.multi.vo.AdminVO;
import com.multi.vo.ProductVO;

@Controller
public class MainController {
	
	@Autowired
	MainBiz mbiz;
	
	@Autowired
	ProductBiz pbiz;
	
	@Autowired
	CartBiz cabiz;
	
	
	@RequestMapping("/")
	public String main(Model m) {
		int count = 0;
		
		try {
			count = mbiz.getCustCnt();
//			System.out.println("CustCnt : " + count);
			m.addAttribute("custcnt", count);
			
			count = mbiz.getProductCnt();
//			System.out.println("ProductCnt : " + count);
			m.addAttribute("productcnt", count);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return "index";
	}
	
	
//	@RequestMapping("/search2")//동일한 이름만 서치 가능 
//	public String search2(Model m,String txt) {
////		System.out.println(txt);
//		List<ProductVO> list = null;
//		List<CartVO> calist = null;
//		ProductVO product = null;
//		CartVO cart = null;
//		
//		try {
//			list = pbiz.get();
//			calist = cabiz.get();//cart 정보를 모두 가져온다. .
//			
//			for(ProductVO p : list) {
//				if(p.getName().equals(txt)) {
//					product = p;
////					System.out.println("productVO : " + p);
//					for(CartVO c : calist) {
//						if(c.getPid() == p.getId()) {// cart에 담겨있는 productId 와 같은 정보를 가져옴
//							m.addAttribute("cart", c);// 해당 product가 몇개 있는지 설정 
//						}
//					}
//					m.addAttribute("product", product);
//				}
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		if(product == null) {// 찾고자 하는 product가 없을경우 실행 
//			m.addAttribute("center", "/searchError");
//		}else {
//			m.addAttribute("center", "/search");
//		}
//		
//		return "index";
//	}
//	
	@RequestMapping("/search")//동일한 이름만 서치 가능 
	public String search(Model m,String txt) {
		System.out.println("search Start : ");
		List<ProductVO> list = null;
		ProductVO product = null;
		
		try {
			list = mbiz.searchproduct(txt);
			m.addAttribute("productlist", list);
			System.out.println("Product Search list : " + list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "search");
		
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Model m) {
		m.addAttribute("center", "login");
		
		
		return "index";
	}
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m,AdminVO ad, HttpSession session) {
		AdminVO admin = null;
		String next = "";
//		System.out.println("inner Admin : " + ad);
		
		try {
			admin = mbiz.getadmin(ad.getId());
			
			//입력된 user 정보 저장 
			m.addAttribute("userid", ad.getId());
			m.addAttribute("userpwd", ad.getPwd());
			
			if(admin == null) {//null 일경우 해당 ID가 없는것임 
				m.addAttribute("msg", "존재하지 않는 ID 입니다.");
				next = "/login";
				m.addAttribute("center", next);
				return "index"; 
			}
			
			//해당 id 존재시 실행 
			// pwd가 다를경우 실행 
			if(!admin.getPwd().equals(ad.getPwd())) {
				m.addAttribute("msg", "Password 가 다릅니다. ");
				next = "/login";
				m.addAttribute("center", next);
				return "index";
			}
			
			//로그인 성공시 실행
			session.setAttribute("loginadmin", admin);
			System.out.println("session : " + session.getAttribute("loginadmin"));
			m.addAttribute("user", admin);
			next = "/loginok";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", next);
		
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(Model m, HttpSession session) {
		if(session != null) {
			session.invalidate();//서버에서 session을 제거한다. 
		}
		
		m.addAttribute("center", "/");
		
		return "index";
	}
	
	
	


}
