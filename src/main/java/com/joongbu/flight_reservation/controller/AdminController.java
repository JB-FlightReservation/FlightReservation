
package com.joongbu.flight_reservation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.joongbu.flight_reservation.dto.*;
import com.joongbu.flight_reservation.service.AdminService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.joongbu.flight_reservation.mapper.AdminMapper;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@RequestMapping("/adminpage")
@Controller
public class AdminController {

	@Autowired
	AdminMapper adminMapper;

	@Autowired
	AdminService adminService;

	/* 회원 관리 시작 */
	@GetMapping("/userManagement.do")
	public String userManagement(Model model, @RequestParam(required = false) String ctName, SearchDto search) {

		PageInfo<CustomerDto> paging = null;
		CheckCustomer checkCustomer = new CheckCustomer();
		try {
			if (search.getOrderBy() == null)
				search.setOrderBy("ct_no ASC");
			paging = adminService.customerPaging(search, ctName);
			if (paging.getTotal() == 0) {
				checkCustomer.setCheck(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("paging", paging);
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
		if (update > 0) {
			return "redirect:/adminpage/userManagement.do";
		} else {
			return "redirect:/adminpage/management.do";
		}
	}

	@Data
	class CheckCustomer {
		private int check; // 0:없음, 1:존재함, -1:통신오류
		private CustomerDto customer;
	}

	@GetMapping("/checkSearchName.do")
	public @ResponseBody CheckCustomer checkSearchName(CustomerDto customer,
			@RequestParam(required = true) String ctName) {
		CheckCustomer checkCustomer = new CheckCustomer();
		try {
			customer = adminMapper.custerCheckSearch(ctName);
			if (customer != null) {
				checkCustomer.setCheck(0);
			} else if (customer == null) {
				checkCustomer.setCheck(-2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkCustomer;
	}

	@GetMapping("/checkCustomerId.do")
	public @ResponseBody CheckCustomer checkCustomerId(CustomerDto customer,
			@RequestParam(required = true) String ctId) {
		CheckCustomer checkCustomer = new CheckCustomer();
		try {
			customer = adminMapper.customerCheckId(ctId);
			if (customer != null) {
				checkCustomer.setCheck(1);
				checkCustomer.setCustomer(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkCustomer.setCheck(-1);
		}
		return checkCustomer;
	}

	@GetMapping("/checkCustomerName.do")
	public @ResponseBody CheckCustomer checkCustomerName(CustomerDto customer,
			@RequestParam(required = true) String ctName) {
		CheckCustomer checkCustomer = new CheckCustomer();
		try {
			customer = adminMapper.customerCheckName(ctName);
			if (customer != null) {
				checkCustomer.setCheck(1);
				checkCustomer.setCustomer(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkCustomer.setCheck(-1);
		}
		return checkCustomer;
	}
	/* 회원 관리 끝 */


	/* 예약 관리 시작 */
	@GetMapping("/reservationManagement.do")
	public String reservationManagement(Model model, @RequestParam(required = false) Integer rvNo, SearchDto search) {

		PageInfo<ReservationDto> reservation = null;
		try {
			if (search.getOrderBy() == null)
				search.setOrderBy("ct_no ASC");
			reservation = adminService.reservationPaging(search, rvNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("reservation", reservation);

		return "/adminpage/reservationManagement";
	}

	@GetMapping("reservationDetailManagement.do")
	public void reservationDetailManagement() {

	}

	/* 예약 관리 끝 */
	@GetMapping("management.do")
	public void managemen() {
	}

	/* 쿠폰 */
	// 쿠폰 페이지 - 발급
	@GetMapping("/createCoupon.do")
	public void createCoupon() {
	}

	@PostMapping("/createCoupon.do")
	public String createCoupon(CouponDto couponDto) {
		int insert = 0;
		try {
			insert = adminMapper.cpInsert(couponDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("CouponDto: " + couponDto);
		if (insert > 0) {
			return "redirect:/adminpage/couponList.do";
		} else {
			return "redirect:/adminpage/createCoupon.do";
		}
	}

	// 쿠폰페이지- 리스트

	@GetMapping("/couponList.do")
	public String couponList(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String ctName, SearchDto search) {
		final int ROWS = 10;
		int startRow = (page - 1) * ROWS;
		List<CouponDto> cpList = null;
		PageInfo<CustomerDto> paging = null;
		try {
			cpList = adminMapper.cpList(startRow, ROWS);
			if (search.getOrderBy() == null)
				search.setOrderBy("ct_no ASC");
			paging = adminService.customerPaging(search, ctName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cpList", cpList);
		model.addAttribute("customer", paging);
		return "/adminpage/couponList";
	}

	// 쿠폰페이지- 삭제
	@GetMapping("/cpDelete.do")
	public String couponDelete(int cpNo) {
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
	public String cpUpdate(CouponDto coupon) {
		int update = 0;
		try {
			update = adminMapper.cpUpdate(coupon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (update > 0) {
			return "redirect:/adminpage/couponList.do";
		} else {
			return "redirect:/adminpage/couponList.do";
		}
	}

	/* 쿠폰 끝 */

	/* 관리자 */
	@GetMapping("/loginpage.do") // 관리자 로그인
	public void loginpage(HttpServletRequest req, HttpSession session) {
		String refererPage = req.getHeader("Referer");
		session.setAttribute("redirectPage", refererPage);
	}

	@PostMapping("/loginpage.do") // 관리자 로그인
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

	@GetMapping("/logout.do") // 관리자 로그아웃
	public String logout(HttpSession session) {
		session.removeAttribute("loginAdmin");
		return "redirect:/adminpage/management.do";
	}

	@GetMapping("/adminDetail.do") // 관리자 아이디 상세정보
	public AdminDto adminDetail(String adminId) {
		AdminDto adminDto = adminMapper.adminDetail(adminId);

		return adminDto;
	}

	@PostMapping("/update.do") // 관지라 정보 수정 (비밀번호, 이름, 이메일)
	public String Aupdate(AdminDto admin) {
		int update = 0;
		try {
			update = adminMapper.Aupdate(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (update > 0) {
			return "redirect:/adminpage/management.do";
		} else {
			return "redirect:/adminpage/detail.do?adminId=" + admin.getAdminId();
		}
	}

	@GetMapping("/findPassword.do") // 관리자비밀번호 찾기
	public void findPassword() {
	}

	@PostMapping("/findPassword.do") // 관리자비밀번호 찾기
	public String findPassword(AdminDto admin) {
		int findPassword = 0;
		try {
			findPassword = adminMapper.Aupdate(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (findPassword > 0) {
			return "redirect:/adminpage/loginpage.do";
		} else {
			return "redirect:/adminpage/findPassword.do";
		}
	}

	@Getter
	@Setter
	class CheckAdmin { // 관리자 정보 체크
		private int check;
		private AdminDto admin;
	}

	@GetMapping("/checkAdminId.do") // 관리자 아이디 체크
	public @ResponseBody CheckAdmin checkAdminId(@RequestParam(required = true) String adminId) {
		CheckAdmin checkAdmin = new CheckAdmin();
		AdminDto admin = null;
		try {
			admin = adminMapper.adminDetail(adminId);
			if (admin != null) {
				checkAdmin.setCheck(0);
				checkAdmin.setAdmin(admin);
			} else {
				checkAdmin.setCheck(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkAdmin.setCheck(-1);
		}
		return checkAdmin;
	}

	@GetMapping("/checkAdminName.do") // 관리자 이름 체크
	public @ResponseBody CheckAdmin checkAdminName(@RequestParam(required = true) String admin_name) {
		CheckAdmin checkAdmin = new CheckAdmin();
		AdminDto admin = null;
		try {
			admin = adminMapper.adminDetail(admin_name);
			if (admin != null) {
				checkAdmin.setCheck(0);
				checkAdmin.setAdmin(admin);
			} else {
				checkAdmin.setCheck(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkAdmin.setCheck(-1);
		}
		return checkAdmin;
	}

}

/* 관리자 끝 */
