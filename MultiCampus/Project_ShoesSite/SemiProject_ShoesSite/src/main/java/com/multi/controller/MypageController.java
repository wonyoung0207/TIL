package com.multi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.Addr_listBiz;
import com.multi.biz.BuyBiz;
import com.multi.biz.Buy_detailBiz;
import com.multi.biz.CartBiz;
import com.multi.biz.CustBiz;
import com.multi.biz.ReviewBiz;
import com.multi.vo.Addr_listVO;
import com.multi.vo.BuyVO;
import com.multi.vo.Buy_detailVO;
import com.multi.vo.CartVO;
import com.multi.vo.CustVO;
import com.multi.vo.ReviewVO;


@Controller
@RequestMapping("/mypage")
public class MypageController {

	@Autowired
	CustBiz cust_biz;

	@Autowired
	CartBiz cart_biz;

	@Autowired
	BuyBiz buy_biz;
	
	@Autowired
	Buy_detailBiz buy_detail_biz;

	@Autowired
	ReviewBiz review_biz;

	@Autowired
	Addr_listBiz addr_biz;

	@RequestMapping("")
	public String mypage(Model m) {

		m.addAttribute("left", "mypage/left");
		m.addAttribute("mypage_center", "mypage/center");

		m.addAttribute("center", "mypage/mypage");

		return "index";
	}

	@RequestMapping("buy")//쇼핑정보 - 주문/배송현황 클릭시 실행 
	public String buy(Model m, String id) {
		CustVO cust = null;
		List<BuyVO> list = null;

		System.out.println(id);

		try {
			cust = cust_biz.get(id);
			list = buy_biz.selectUserBuyGroup(id);
			m.addAttribute("buylist", list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("mypage_center", "mypage/buy");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}
	
	@RequestMapping("buy_remove")//쇼핑정보 - 주문/배송현황애서 주문취소 클릭시 실행 
	public String buy_remove(Model m, int id, HttpSession session) {
		CustVO cust = (CustVO) session.getAttribute("user");
		List<BuyVO> list = null;
		System.out.println("buy id : " + id);

		try {
			//주문 상세 삭제 
			buy_detail_biz.deletebuyid(id);

			// 주문 삭제 
			buy_biz.remove(id);
			
			cust = cust_biz.get(cust.getId());
			list = buy_biz.selectUserBuy(cust.getId());
			System.out.println("buy list : " + list);
			m.addAttribute("buylist", list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("mypage_center", "mypage/buy");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}
	
	@RequestMapping("buy_detail")//쇼핑정보 - 주문/배송현황 - 상세주문내역 실행 
	public String buy_detail(Model m, int id, HttpSession session) {
		CustVO cust = (CustVO) session.getAttribute("user");
		List<Buy_detailVO> list = null;

		System.out.println(id);

		try {
			
			list = buy_detail_biz.selectdetailproduct(id);
			System.out.println("oid가 " + id + " 인 buy_detailList : " + list);

			m.addAttribute("buy_detailList", list);

		} catch (Exception e) {
			e.printStackTrace();
		}


		m.addAttribute("mypage_center", "mypage/buy_detail");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}
	@RequestMapping("review")//활동정보 - 상품리뷰 클릭시 실행  
	public String review(Model m, String id) {//user id 매개변수로 들어옴 
		CustVO cust = null;
		List<BuyVO> list = null;
		List<ReviewVO> review_list = null;
		List<BuyVO> month_list = new ArrayList<BuyVO>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1 = Calendar.getInstance();
		String strcal1="";
		Date before_one_month = new Date();


		cal1.setTime(before_one_month);// 시간 설정 
		cal1.add(Calendar.MONTH, -1);// 현재시간부터 1달 뺴기 
		strcal1 = sdf.format(cal1.getTime());


		try {
			// 나의 상품리뷰 쓰기 구간 시작 
			before_one_month = sdf.parse(strcal1);// 뺀걸 date형으로 변환 

			cust = cust_biz.get(id);
			list = buy_biz.selectUserBuy(id);
			for(BuyVO buy : list) {
				if(buy.getRegdate().after(before_one_month)) {//현재시점 -1달 이후의 물건만 통과
					month_list.add(buy);
					//					System.out.println("추가된 buy : " + buy);
				}
			}
			// 나의 Review 모아보기 구간 시작 
			review_list = review_biz.selectuserall(id);
			System.out.println("review list : " + review_list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("buylist", month_list);
		m.addAttribute("reviewlist", review_list);
		m.addAttribute("mypage_center", "mypage/review");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}


	@RequestMapping("myproduct_reviewAll")//활동정보 - 나의상품리뷰 쓰기의 전체보기 클릭시 실행 
	public String myproduct_reviewAll(Model m, HttpSession session) { 
		CustVO cust = (CustVO) session.getAttribute("user");
		System.out.println("CustVo : " + cust);
		List<BuyVO> list = null;


		try {
			list = buy_biz.selectUserBuy(cust.getId());
			System.out.println("list : " + list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("buylist", list);
		m.addAttribute("mypage_center", "mypage/myproduct_reviewAll");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}

	@RequestMapping("user_info")//나의정보 - 회원정보관리 클릭시 실행
	public String user_info(Model m, String id, HttpSession session) {//cust id를 받아옴 
		CustVO cust = (CustVO) session.getAttribute("user");



		m.addAttribute("mypage_center", "mypage/user_info");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}

	@RequestMapping("user_info_modify")//나의정보 - 회원정보 변경시 실행 
	public String user_info_modify(Model m, String name, String telphone, HttpSession session) {//cust id를 받아옴 
		CustVO cust = (CustVO) session.getAttribute("user");
		System.out.println("기존 cust : " + cust);
		cust.setName(name);
		cust.setTelphone(telphone);
		System.out.println("변경된 cust : " + cust);

		try {
			cust_biz.modify(cust);
			session.setAttribute("user", cust);// session에 저장된 user정보를 변경된 정보로 셋팅

		} catch (Exception e) {
			e.printStackTrace();
		}


		m.addAttribute("left", "mypage/left");
		m.addAttribute("mypage_center", "mypage/center");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}


	@RequestMapping("user_changePwd")//나의정보 - 주문/배송현황 클릭시 실행 
	public String user_changePwd(Model m,String id) {//cust id를 받아옴


		m.addAttribute("left", "mypage/left");
		m.addAttribute("mypage_center", "mypage/user_changePwd");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}

	@RequestMapping("user_changePwdimpl")//나의정보 - 주문/배송현황 클릭시 실행 
	public String user_changePwdimpl(Model m,String pwd,HttpSession session) {//변경 가능한 pwd 를 받아옴
		CustVO cust = (CustVO) session.getAttribute("user");
		System.out.println("기존 cust 비밀번호 : " + cust);
		cust.setPwd(pwd);// 비밀번호 변경 

		try {
			cust_biz.modify(cust);// 데이터베이스의 비밀번호를 변경
			session.setAttribute("user", cust);// session에 저장된 user정보를 변경된 정보로 셋팅

			System.out.println("변경된 cust 비밀번호 : " + cust);
		} catch (Exception e) {
			e.printStackTrace();
		}


		m.addAttribute("left", "mypage/left");
		m.addAttribute("mypage_center", "mypage/center");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}
	@RequestMapping("user_addr")//나의정보 - 배송지관리 클릭
	public String user_addr(Model m,String pwd,HttpSession session) {
		CustVO cust = (CustVO) session.getAttribute("user");
		List<Addr_listVO> list = null;


		try {
			list = addr_biz.getcustinfoAll(cust.getId());
			System.out.println("addr_list All: " + list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("addr_list", list);
		m.addAttribute("mypage_center", "mypage/user_addr");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}

	@RequestMapping("addr_modify")//나의정보 - 배송지관리 수정 클릭
	public String addr_modify(Model m,int id,HttpSession session) {
		Addr_listVO addr = null;

		try {
			addr = addr_biz.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("addr", addr);
		m.addAttribute("mypage_center", "mypage/addr_modify");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}


	@RequestMapping("addr_modifyimpl")//나의정보 - 배송지관리 수정 완료 클릭
	public String addr_modifyimpl(Model m,int addr_id, String add_addr, String add_addr_detail,HttpSession session) {
		Addr_listVO addr = null;
		CustVO cust = (CustVO) session.getAttribute("user");
		List<Addr_listVO> list = null;



		try {
			addr = addr_biz.get(addr_id);
			addr.setAddr(add_addr);
			addr.setAddr_detail(add_addr_detail);

			addr_biz.modify(addr);// 주소 수정 

			list = addr_biz.getcustinfoAll(cust.getId());
			System.out.println("addr_list All: " + list);


		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("addr_list", list);
		m.addAttribute("mypage_center", "mypage/user_addr");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}

	@RequestMapping("addr_remove")//나의정보 - 배송지관리 삭제 클릭  
	public String addr_remove(Model m,int id,HttpSession session) {
		CustVO cust = (CustVO) session.getAttribute("user");
		Addr_listVO addr = null;
		List<Addr_listVO> list = null;

		System.out.println("삭제할 addr ID : " + id);

		try {
			addr_biz.remove(id);// 해당 주소록 삭제 


			list = addr_biz.getcustinfoAll(cust.getId());// 해당 아이디 주소록 모두 불러오기 
			System.out.println("addr_list All: " + list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("addr_list", list);
		m.addAttribute("mypage_center", "mypage/user_addr");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}

	@RequestMapping("addr_new")//나의정보 - 주문/배송현황 클릭시 실행 
	public String addr_new(Model m,HttpSession session) {

		m.addAttribute("mypage_center", "mypage/addr_new");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}

	@RequestMapping("addr_newimpl")//나의정보 - 주문/배송현황 클릭시 실행 
	public String addr_newimpl(Model m,Addr_listVO addr,HttpSession session) {
		System.out.println("새로 추가할 addr : " + addr);
		CustVO cust = (CustVO) session.getAttribute("user");
		List<Addr_listVO> list = null;

		try {
			addr_biz.register(addr);//주소록 추가 

			list = addr_biz.getcustinfoAll(cust.getId());
			System.out.println("addr_list All: " + list);

		} catch (Exception e) {
			e.printStackTrace();
		}



		m.addAttribute("addr_list", list);

		m.addAttribute("mypage_center", "mypage/user_addr");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}



	@RequestMapping("user_remove")//나의정보 - 회원탈퇴 클릭시 
	public String user_remove(Model m,HttpSession session) {

		m.addAttribute("mypage_center", "mypage/user_remove");
		m.addAttribute("left", "mypage/left");
		m.addAttribute("center", "mypage/mypage");
		return "index";
	}


	@RequestMapping("user_removeimpl")//나의정보 - 회원탈퇴 완료 클릭시 
	public String user_removeimpl(Model m,String current_pwd,HttpSession session) {
		CustVO cust = (CustVO) session.getAttribute("user");
		String move_page = "";
		List<CartVO> cart_list = null;
		List<BuyVO> buy_list = null;

		// 주소목록, 카트, 회원 테이블만 지우기 -> 주문과 리뷰 테이블에서는 삭제 안함 
		if(!cust.getPwd().equals(current_pwd)) {// 만약 비밀번호가 다르다면 실행 
			System.out.println("비밀번호 다름 ");
			move_page = "mypage/mypage"; 
			m.addAttribute("msg", "비밀번호가 다릅니다. ");
			m.addAttribute("mypage_center", "mypage/user_remove");
			m.addAttribute("left", "mypage/left");

		}else {
			System.out.println("해당 id 삭제 : " + cust);
			try {
				buy_list = buy_biz.selectUserBuy(cust.getId());
				System.out.println("가져온 buy_list : " + buy_list);


				// 해당 id로된 정보를 cart에서 삭제
				cart_biz.deleteuserid(cust.getId());
				System.out.println("user cart 삭제 ");

				// 해당 id로된 정보를 add_list에서 삭제 
				addr_biz.deleteuserid(cust.getId());
				System.out.println("user addr_list 삭제 ");
				
				// 해당 id로된 정보를 buy에서 삭제 
				for(BuyVO buy : buy_list) {
					// 해당 id로된 정보를 주문 상세에서 삭제 
					buy_detail_biz.deletebuyid(buy.getId());
					System.out.println("user buy_detail 삭제 ");
					
					buy_biz.remove(buy.getId());
					System.out.println("user buy 삭제 ");
				}
				
				// 해당 id로된 정보를 review에서 삭제 
				review_biz.deleteuserid(cust.getId());
				System.out.println("user review 삭제 ");
				
				
				// session 삭제 
				session.removeAttribute("user");
				System.out.println("user session 삭제 ");

				//
				
				// 유저 삭제
				cust_biz.remove(cust.getId());
				System.out.println("user 삭제 ");



				System.out.println("모든 데이터 삭제 완료 ");

				move_page = "/login/custremove";
				// 메인 페이지로 이동 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		m.addAttribute("center", move_page);// 다시 user_remove 페이지로 이동

		return "index";
	}

}
