package com.joongbu.flight_reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joongbu.flight_reservation.dto.AdminDto;
import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.mapper.AdminMapper;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.List;

@RequestMapping("/adminpage")
@Controller
public class AdminController {
	@Autowired
	DataSource dataSource;

	@Autowired
	AdminMapper adminMapper;

	@GetMapping("/userManagement.do")
	public String userManagement(Model model, @RequestParam(defaultValue = "1") int page) {
		final int ROWS = 10;
		int startRow = (page - 1) * ROWS;
		List<CustomerDto> customerList = adminMapper.customerList(startRow, ROWS);
		model.addAttribute("customerList", customerList);
		return "/adminpage/userManagement";
	}

	@GetMapping("/reservationManagement.do")
	public void reservationManagement() {
	}

	@GetMapping("reservationDetailManagement.do")
	public void reservationDetailManagement() {
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
	public void loginpage(
			HttpServletRequest req,
			HttpSession session
			) {
		String refererPage=req.getHeader("Referer");
		session.setAttribute("redirectPage", refererPage);
	}
	@PostMapping("/loginpage.do")
	public String loginpage(
			@RequestParam(required = true) String adminId, 
			@RequestParam(required = true) String adminPw,
			HttpSession session,
			@SessionAttribute(required = false) String redirectPage
			) {
		AdminDto loginAdmin = null;
		System.out.println(adminId+adminPw);
		try {
			loginAdmin = adminMapper.login(adminId, adminPw);
			System.out.println(loginAdmin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(loginAdmin!=null) {
			session.setAttribute("loginAdmin", loginAdmin);
			if(redirectPage==null) {
				return "redirect:/adminpage/management.do";				
			}else {
				return "redirect:"+redirectPage;				
			}
		}else {
			return "redirect:/adminpage/loginpage.do";
		}
		
	}
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("loginAdmin");
		return "redirect:/adminpage/management.do";
	}
	@GetMapping("/management.do")
	public String management() {
		
		return "/adminpage/management";
	}
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
		if(update>0) {
			return "redirect:/adminpage/management.do";
		}else {
			return "redirect:/adminpage/detail.do?adminId="+admin.getAdminId();
		}
	}
	@GetMapping("/findPassword.do")
	public void findPassword() {}
	@PostMapping("/findPassword.do")
	public String findPassword(AdminDto admin) {
		int findPassword = 0;
		try {
			findPassword = adminMapper.Aupdate(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(findPassword>0) {
			return "redirect:/adminpage/management.do";
		}else {
			return "redirect:/adminpage/findPassword.do";
		}
	}
	
	@Getter@Setter
	class CheckAdmin{
		private int check;
		private AdminDto admin;
	}
	@GetMapping("/checkAdminId.do")
	public @ResponseBody CheckAdmin checkAdminId(
			@RequestParam(required = true) String adminId
			) {
		CheckAdmin checkAdmin = new CheckAdmin();
		AdminDto admin = null;
		try {
			admin = adminMapper.adminDetail(adminId);
			if(admin!=null) {
				checkAdmin.setCheck(0);
				checkAdmin.setAdmin(admin);
			}else {
				checkAdmin.setCheck(1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkAdmin.setCheck(-1);
		}
		return checkAdmin;
	}
	
}
