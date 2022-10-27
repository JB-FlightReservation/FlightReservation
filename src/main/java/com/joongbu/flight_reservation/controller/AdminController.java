
package com.joongbu.flight_reservation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.github.pagehelper.PageInfo;
import com.joongbu.flight_reservation.dto.SearchDto;
import com.joongbu.flight_reservation.service.AdminService;
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

	@Autowired
	AdminService adminService;

	/* 회원 관리 시작 */
	@GetMapping("/userManagement.do")
	public String userManagement(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String ctName, SearchDto search) {
		final int ROWS = 10;

		PageInfo<CustomerDto> paging = null;
		try {
			if (search.getOrderBy() == null)
				search.setOrderBy("ct_no ASC");
			paging = adminService.customerPaging(search, ctName);
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
	public String reservationManagement(Model model, @RequestParam(required = false) Integer ctNo, SearchDto search) {

		final int ROWS = 10;
		PageInfo<ReservationDto> reservation = null;
		try {
			if (search.getOrderBy() == null)
				search.setOrderBy("ct_no ASC");
			reservation = adminService.reservationPaging(search, ctNo);
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

	@GetMapping("createCoupon.do")
	public void createCoupon() {
	}

	@GetMapping("coupon.do")
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
		System.out.println(adminId + adminPw);
		try {
			loginAdmin = adminMapper.login(adminId, adminPw);
			System.out.println(loginAdmin);
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

//	@GetMapping("/management.do")
//	public String management() {
//
//		return "/adminpage/management";
//	}

	@GetMapping("/adminDetail.do")
	public AdminDto adminDetail(String adminId) {
		AdminDto adminDto = adminMapper.adminDetail(adminId);

		return adminDto;
	}

	@PostMapping("/update.do")
	public String Aupdate(AdminDto admin) {
		int update = 0;
		try {
			update = adminMapper.Aupdate(admin);
			System.out.println(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (update > 0) {
			return "redirect:/adminpage/management.do";
		} else {
			return "redirect:/adminpage/detail.do?adminId=" + admin.getAdminId();
		}
	}

//	@Getter
//	@Setter
//	class CheckAdmin {
//
//	@GetMapping("/findPassword.do")
//	public void findPassword() {}
//	@PostMapping("/findPassword.do")
//	public String findPassword(AdminDto admin) {
//		int findPassword = 0;
//		try {
//			findPassword = adminMapper.Aupdate(admin);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(findPassword>0) {
//			return "redirect:/adminpage/management.do";
//		}else {
//			return "redirect:/adminpage/findPassword.do";
//		}
//	}
//
//	@Getter
//	@Setter
//	class CheckAdmin{
//		private int check;
//		private AdminDto admin;
//	}
//
//	@GetMapping("/checkAdminId.do")
//	public @ResponseBody CheckAdmin checkAdminId(@RequestParam(required = true) String adminId) {
//		CheckAdmin checkAdmin = new CheckAdmin();
//		AdminDto admin = null;
//		try {
//			admin = adminMapper.adminDetail(adminId);
//			if(admin!=null) {
//				checkAdmin.setCheck(0);
//				checkAdmin.setAdmin(admin);
//			}else {
//				checkAdmin.setCheck(1);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			checkAdmin.setCheck(-1);
//		}
//		return checkAdmin;
//	}

}
