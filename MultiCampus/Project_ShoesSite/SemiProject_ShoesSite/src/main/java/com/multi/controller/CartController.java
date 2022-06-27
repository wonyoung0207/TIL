package com.multi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.biz.Addr_listBiz;
import com.multi.biz.BuyBiz;
import com.multi.biz.Buy_detailBiz;
import com.multi.biz.CartBiz;
import com.multi.vo.Addr_listVO;
import com.multi.vo.BuyVO;
import com.multi.vo.Buy_detailVO;
import com.multi.vo.CartVO;
import com.multi.vo.CustVO;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartBiz cartbiz;
	
	@Autowired
	Buy_detailBiz bdbiz;
	
	@Autowired
	Addr_listBiz albiz;
	
	@Autowired
	BuyBiz bbiz;
	
	@RequestMapping("")
	public String cart(Model m,HttpSession session) {
		CustVO ncust = (CustVO) session.getAttribute("user");
		List<CartVO> list = new ArrayList<CartVO>();
			if(ncust == null) {
				m.addAttribute("center", "login/login");
			}
			else if (session != null) {
			int total =0;
			String id =ncust.getId();
			List<CartVO> clist =null;
			try {
				clist = cartbiz.uidselect(id);
				total = cartbiz.gettotal(id);
				m.addAttribute("center", "cart/cart");
				m.addAttribute("clist", clist);
				m.addAttribute("list", list);
				m.addAttribute("total", total);
				
			} catch (Exception e) {
				m.addAttribute("center", "cart/cart");
				m.addAttribute("clist", clist);

			}
			
			
		}return "index";
	}
	@RequestMapping("/delete")
	public String delete(Model m,Integer id) {
		try {
			cartbiz.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:";
	}
	@RequestMapping("/update")
	public String update(Model m,CartVO cart) {
		List<CartVO> list = new ArrayList<CartVO>();
		list = cart.getCartlist();
		try {
			for (CartVO cartVO : list) {
				cartbiz.modify(cartVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cart/checkout";
	}
	@RequestMapping("/checkout")
	public String checkout(Model m ,HttpSession session) { 
		CustVO ncust = (CustVO) session.getAttribute("user");
		String id =ncust.getId();
		List<CartVO> clist =null;
		Addr_listVO al = null;
		try {
			int bid = bbiz.selectid();
			clist = cartbiz.uidselect(id);
			int total = cartbiz.gettotal(id);
			al = albiz.getcustinfo(id);
			int cnt = 0;
			cnt = cartbiz.selectcnt(id);
			m.addAttribute("center", "cart/checkout");
			m.addAttribute("clist", clist);
			m.addAttribute("total", total);
			m.addAttribute("al", al);
			m.addAttribute("bid", bid);
			m.addAttribute("cnt", cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	@RequestMapping("buyimpl")
	public String buyimpl(Model m,BuyVO buy,HttpSession session) {
		CustVO ncust = (CustVO) session.getAttribute("user");
		String id =ncust.getId();
		List<Buy_detailVO> blist =null;
		List<Buy_detailVO> bidlist = null;
		
		try {
			bbiz.register(buy);
			int bid = bbiz.selectid();
			blist = bdbiz.getbuy_detail(id);
			for (Buy_detailVO buy_detailVO : blist) {
				bdbiz.register(buy_detailVO);
			};
			bidlist = bdbiz.selectid(bid);
			m.addAttribute("center", "cart/success");
			m.addAttribute("bidlist", bidlist);
			cartbiz.deleteall(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	

}
