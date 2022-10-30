package com.joongbu.flight_reservation.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.security.auth.message.config.AuthConfig;
import javax.servlet.http.HttpServletRequest;
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

import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.dto.SignupDto;
import com.joongbu.flight_reservation.mapper.CustomerMapper;
import com.joongbu.flight_reservation.service.SendMailService;

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
	
	
	//아이디 찾기
	@Autowired
	SendMailService emailservice;
	String EmailauthNum;
	
	@GetMapping("/findId.do")
	public void findId() {}
	@PostMapping("/findId.do")
	public String findId(
			@RequestParam("name") String ctName,
			@RequestParam("email") String ctEmail,
			Model model
			) {
		System.out.println(ctName+"/"+ctEmail); 
		CustomerDto find=null;
		try {
			find=customerMapper.find(ctName, ctEmail);
			//System.out.println(find.getCtId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(find!=null) {
			String toEmail = ctEmail;
			try {
				EmailauthNum = emailservice.sendEmail(toEmail);
				
				CheckNum aa=new CheckNum();
				aa.setAuthNum(EmailauthNum);
				System.out.println(EmailauthNum);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			return "redirect:/login/findUserId.do";
		}
		return "redirect:/login/findId.do";
	}
	
	@Getter@Setter
	class CheckNum{
		private String authNum;
	}

	
	//아이디 찾기완료
	@GetMapping("/findUserId.do")
	public void findUserId() {}
	
	
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
			String toEmail = ctEmail;
			try {
				EmailauthNum = emailservice.sendEmail(toEmail);
				System.out.println(EmailauthNum);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
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