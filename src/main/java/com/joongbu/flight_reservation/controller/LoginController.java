package com.joongbu.flight_reservation.controller;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.mapper.CustomerMapper;
@RequestMapping("/login")
@Controller
public class LoginController {
	@Autowired
	private DataSource dataSource;
	@Autowired
	CustomerMapper customerMapper;
	
	//로그인
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
		if(loginCt!=null) {
			session.setAttribute("loginCt", loginCt);
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
	
	
	//아이디 찾기
	@GetMapping("/findId.do")
	public String findId() {
		return "/login/findId";
	}
	//아이디 찾기완료
	@GetMapping("/findUserId.do")
	public String findUserId() {
		return "/login/findUserId";
	}
	
	//비번 찾기
	@GetMapping("/findPassword.do")
	public String findPassword() {
		return "/login/findPassword";
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