package com.joongbu.flight_reservation.controller;

//import java.io.UnsupportedEncodingException;
//
//import javax.mail.MessagingException;
//import javax.security.auth.message.config.AuthConfig;
//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.mapper.CustomerMapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequestMapping("/login")
@RequiredArgsConstructor
@Controller
public class LoginController {
	@Autowired
	private DataSource dataSource;
	@Autowired
	CustomerMapper customerMapper;
	PasswordEncoder passwordEncoder;
	//로그인
		@Autowired
		  public LoginController( PasswordEncoder passwordEncoder) {
	        this.passwordEncoder = passwordEncoder;
	    }
		
		@GetMapping("/loginPage.do")
		public void loginPage() {}
		@PostMapping("/loginPage.do")
		public String loginPage(
				@RequestParam(required = true) String ctId,
				@RequestParam(required = true) String ctPw,
				HttpSession session
				) {
			System.out.println(ctId+"/"+ctPw);
			CustomerDto loginCt=null;
			try {
				loginCt=customerMapper.login(ctId, ctPw);
			} catch (Exception e) {
				e.printStackTrace();
			}
			passwordEncoder = new BCryptPasswordEncoder();
			boolean result=passwordEncoder.matches(ctPw,ctPw);//첫번째 평문, 두번째 암호화
			if(result == true) {
				return "redirect:/";
			}else {
				return "redirect:/login/loginPage.do";
			}
		}
	//로그아웃
	@GetMapping("/logout.do")//세션삭제
	public String logout(HttpSession session) {
		session.removeAttribute("loginCt");
		return "redirect:/";
	}
	
	
	//아이디찾기
	@Getter
	@Setter
	class CheckUser {
		private int check;
		private CustomerDto customer; 
	}
	@GetMapping("/findId.do")
	public void findId() {}
	@PostMapping("/findId.do")
	public String findId(
			CustomerDto customer
			) {
		int findId = 0;
		try {
			findId = customerMapper.newPassword(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(findId >0) {
			return "redirect:/login/findUserId.do";
		}else {
			return "redirect:/login/findId.do";
		}
		
	}
	@GetMapping("/checkName.do")
	public @ResponseBody CheckUser checkUserName (
			@RequestParam(required = true) String ctName
			) {
		CheckUser checkUser = new CheckUser();
		CustomerDto customer=null;
		try {
			customer=customerMapper.detail(ctName);
			if(customer != null) {
				checkUser.setCheck(0);
				checkUser.setCustomer(customer);
			}else {
				checkUser.setCheck(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkUser.setCheck(-1);
		}
		
		return checkUser;
	}
	@GetMapping("/checkEmail.do")
	public @ResponseBody CheckUser checkUserEmail (
			
			@RequestParam(required = true) String ctEmail
			) {
		CheckUser checkUser = new CheckUser();
		CustomerDto customer=null;
		try {
			customer=customerMapper.detail2(ctEmail);
			if(customer != null) {
				checkUser.setCheck(0);
				checkUser.setCustomer(customer);
			}else {
				checkUser.setCheck(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			checkUser.setCheck(-1);
		}
		
		return checkUser;
	}
	
	//아이디 찾기완료
	@GetMapping("/findUserId.do")
	public String findUId(
			@SessionAttribute CustomerDto customer,
			Model model
			) {
		model.addAttribute("findId",customer.getCtId());
		return "/login/findUserId";
	}
	@PostMapping("/findUserId")
	public String findUserId(
			CustomerDto customer,
			HttpSession session
			) {
		CustomerDto custo = customerMapper.find(customer.getCtName(), customer.getCtEmail());
		session.setAttribute("customer", custo);
		return "redirect:/login/findUserId.do";
	}
	
	
	//비번 찾기
	@GetMapping("/findPassword.do")
	public void findPassword() {}
	@PostMapping("/findPassword.do")
	public String findPw(
			@RequestParam(required = true) String ctId,
			@RequestParam(required = true) String ctName,
			@RequestParam(required = true) String ctEmail
			) {
		System.out.println(ctId+"/"+ctName+"/"+ctEmail);
		CustomerDto findPassword=null;
		try {
			findPassword=customerMapper.findPassword(ctId, ctName, ctEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(findPassword != null) {
			
			
			return "/login/findPwAuth.do";
		}
		return "redirect:/login/findPassword.do";
	}
	
	//인증확인
	@PostMapping("/findPwAuth.do")
	public String findPwAuth() {
		return "/findPwAuth.do";
	}
	
	
	//새비번 지정 
	@GetMapping("/newPassword.do")
	public String newPassword() {
		return "/login/newPassword";
	}
	//비번 변경완료 
	@GetMapping("/finishChangePw.do")
	public String finishChangePw() {
		return "/login/finishChangePw";
	}

}