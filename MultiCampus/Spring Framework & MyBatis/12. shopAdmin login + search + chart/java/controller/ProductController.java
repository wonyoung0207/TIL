package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CateBiz;
import com.multi.biz.ProductBiz;
import com.multi.frame.Util;
import com.multi.vo.CateVO;
import com.multi.vo.ProductVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductBiz pbiz;
	
	@Autowired
	CateBiz cbiz;
	
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
	
	
	@RequestMapping("/add")
	public String add(Model m) {
		List<CateVO> list = null;
		
		try {
			list = cbiz.get();
			m.addAttribute("catelist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		m.addAttribute("center", "product/add");
		
		
		return "index";
	}
	
	@RequestMapping("/addimpl")
	public String addimpl(Model m,ProductVO obj) {
		// productVO로 name, price, cid, mf(이걸 이용해 파일의 이름을 도출) 가 날라온다.
		System.out.println("받은 productVO : " + obj);
		String imgname = obj.getMf().getOriginalFilename();//파일 이름을 도출 
		obj.setImgname(imgname);// 비어있던 필드에 이름 셋팅 
		System.out.println("setImgname : " + imgname);
		
		try {
			pbiz.register(obj);// 셋팅한 imgname으로 데이터를 추가 
			System.out.println("product : " + obj);
			//파일을 저장 
			Util.saveFile(obj.getMf());//서버에 파일을 저장하는 모듈 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:select";//저장후 select 화면으로 가라 
	}
	
	@RequestMapping("/detail")
	public String detail(Model m, int id) {
		ProductVO product = null;
		List<String> list = null;
		List<CateVO> clist = null;
		
		try {
			// 서버에 저장된 이미지 이름만 중복제거해서 가져온다. 
			list = pbiz.getSelectAllImgName();
			m.addAttribute("imglist", list);
			
			// 넘어온 id 값에 해당하는 product 정보를 가져온다. 
			product = pbiz.get(id);
			m.addAttribute("product", product);
			
			// cid 의 정보는 category에 존재하는 정보여야 한다. 
			clist = cbiz.getlow();
			m.addAttribute("catelist", clist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		m.addAttribute("center", "product/detail");
		
		return "index";
	}
	
	
	@RequestMapping("/update")
	public String update(ProductVO p, Model m) {
//		System.out.println("ProductVO : " + p);
		
		if(p.getImgname().equals("기타")) {
			String imgname = p.getMf().getOriginalFilename();
			p.setImgname(imgname);
					
			Util.saveFile(p.getMf());
		}

		try {
			pbiz.modify(p);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:detail?id=" + p.getId();
	}
	
	@RequestMapping("/delete")
	public String delete(ProductVO p, Model m) {
//		System.out.println("ProductVO : " + p);
		
		try {
			pbiz.remove(p.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:select";
	}



}
