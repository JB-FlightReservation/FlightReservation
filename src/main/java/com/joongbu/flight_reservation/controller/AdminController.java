
package com.joongbu.flight_reservation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.flight_reservation.dto.AdminDto;
import com.joongbu.flight_reservation.dto.CouponDto;
import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.dto.ReservationDto;
import com.joongbu.flight_reservation.mapper.AdminMapper;

import lombok.Getter;
import lombok.Setter;

@RequestMapping("/adminpage")
@Controller
public class AdminController {

	@Autowired
	AdminMapper adminMapper;

	/* 회원 관리 시작 */
	@GetMapping("/userManagement.do")
	public String userManagement(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String ctName) {
		final int ROWS = 10;
		int startRow = (page - 1) * ROWS;
		List<CustomerDto> customerList = null;
		try {
			customerList = adminMapper.customerList(startRow, ROWS, ctName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("customerList", customerList);
		return "/adminpage/userManagement";
	}

	@GetMapping("/customerDelete.do")
	public String customerDelete(int ctNo) {
		int delete = 0;
		try {
			delete = adminMapper.customerDelete(ctNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (delete > 0) {
			return "redirect:/adminpage/userManagement.do";
		} else {
			return "redirect:/adminpage/management.do";
		}
	}

	@PostMapping("/customerUpdate.do")
	public String customerUpdate(CustomerDto customerDto) {
		int update = 0;
		try {
			update = adminMapper.customerUpdate(customerDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(update);
		if (update > 0) {
			return "redirect:/adminpage/userManagement.do";
		} else {
			return "redirect:/adminpage/management.do";
		}
	}
	/* 회원 관리 끝 */

	/* 예약 관리 시작 */
	@GetMapping("/reservationManagement.do")
	public String reservationManagement(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) Integer ctNo) {

		final int ROWS = 10;
		int startRow = (page - 1) * ROWS;
		List<ReservationDto> reservationList = null;
		try {
			reservationList = adminMapper.reservationList(startRow, ROWS, ctNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("reservationList", reservationList);
		return "/adminpage/reservationManagement";
	}

	@GetMapping("reservationDetailManagement.do")
	public void reservationDetailManagement() {

	}

	/* 예약 관리 끝 */
	@GetMapping("management.do")
	public void managemen() {
	}
	//쿠폰 페이지 - 발급
	@GetMapping("/createCoupon.do")
	public void createCoupon() {
	}
	@PostMapping("/createCoupon.do")
	public String createCoupon(
		CouponDto couponDto
		) {
	int insert=0;
	try {
		insert=adminMapper.cpInsert(couponDto);
	} catch (Exception e) {
		e.printStackTrace();
	}
	System.out.println(couponDto);
	if(insert>0) {
		return "redirect:/adminpage/couponList.do";
	} else {
		return "redirect:/adminpage/createCoupon.do";
	}
	}
	
	//쿠폰페이지- 리스트
	@GetMapping("/couponList.do")
	public String couponList(
			Model model,
			@RequestParam(defaultValue="1") int page
			) {
		final int ROWS=10;
		int startRow = (page - 1) * ROWS;
		List<CouponDto> cpList = null;
		try {
			cpList = adminMapper.cpList(startRow, ROWS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cpList", cpList);
		return "/adminpage/couponList";
	}
	//쿠폰페이지- 삭제
		@GetMapping("/cpDelete.do")
		public String couponDelete(
			@RequestParam(required=true) int cpNo
				) {
			int delete = 0;
			try {
				delete = adminMapper.cpDelete(cpNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (delete > 0) {
				return "redirect:/adminpage/couponList.do";
			} else {
				return "redirect:/adminpage/couponList.do";
			}
		}
		@GetMapping("/cpUpdate.do")
		public void cpUpdate() {
			
		}
		
		@PostMapping("/cpUpdate.do")
		public String cpUpdate(
				CouponDto couponDto
				) {
			int update = 0;
			try {
				update = adminMapper.cpUpdate(couponDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (update > 0) {
				return "redirect:/adminpage/couponList.do";
			} else {
				return "redirect:/adminpage/cpUpdate.do?cpNo="+couponDto.getCpNo();
			}
		}
	

	@GetMapping("/coupon.do")
	public void coupon() {
	}

	@GetMapping("counselling.do")
	public void counselling() {
	}

	@GetMapping("/loginpage.do")
	public void loginpage(HttpServletRequest req, HttpSession session) {
		String refererPage = req.getHeader("Referer");
		session.setAttribute("redirectPage", refererPage);
	}

	@PostMapping("/loginpage.do")
	public String loginpage(@RequestParam(required = true) String adminId,
			@RequestParam(required = true) String adminPw, HttpSession session,
			@SessionAttribute(required = false) String redirectPage) {
		AdminDto loginAdmin = null;
		try {
			loginAdmin = adminMapper.login(adminId, adminPw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (loginAdmin != null) {
			session.setAttribute("loginAdmin", loginAdmin);
			if (redirectPage == null) {
				return "redirect:/adminpage/management.do";
			} else {
				return "redirect:" + redirectPage;
			}
		} else {
			return "redirect:/adminpage/loginpage.do";
		}

	}

	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("loginAdmin");
		return "redirect:/adminpage/management.do";
	}

//	@GetMapping("/management.do")
//	public String management() {
//
//		return "/adminpage/management";
//	}

	@GetMapping("/detail.do")
	public AdminDto Adetail(String adminId) {
		AdminDto adminDto = adminMapper.Adetail(adminId);
		return adminDto;
	}

	@PostMapping("/update.do")
	public String Aupdate(AdminDto admin) {
		int update = 0;
		try {
			update = adminMapper.Aupdate(admin);
			System.out.println(admin.getAdminId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (update > 0) {
			return "redirect:/adminpage/management.do";
		} else {
			return "redirect:/adminpage/detail.do?adminId=" + admin.getAdminId();
		}
	}

	@Getter
	@Setter
	class CheckAdmin {
		private int check;
		private AdminDto admin;
	}

	@GetMapping("/checkAdminId.do")
	public @ResponseBody CheckAdmin checkAdminId(@RequestParam(required = true) String adminId) {
		CheckAdmin checkAdmin = new CheckAdmin();
		AdminDto admin = null;
		try {
			admin = adminMapper.Adetail(adminId);
			if (admin != null) {
				checkAdmin.setCheck(1);
				checkAdmin.setAdmin(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkAdmin.setCheck(-1);
		}
		return checkAdmin;
	}

}
