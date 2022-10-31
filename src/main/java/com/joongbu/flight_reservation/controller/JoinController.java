package com.joongbu.flight_reservation.controller;

import javax.servlet.http.HttpSession;

import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.joongbu.flight_reservation.dto.SignupDto;
import com.joongbu.flight_reservation.mapper.SignupMapper;

import lombok.Getter;
import lombok.Setter;


@RequestMapping("/signup")
@Controller
public class JoinController {
	@Autowired //객체를 주입받겠다
	SignupMapper signupMapper;
	@Autowired
	CustomerMapper customerMapper;

	PasswordEncoder passwordEncoder;
	@Autowired
    public JoinController( PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	@GetMapping("/joinPage.do")
	public void joinPage() {
	}

	@GetMapping("/joinPage2.do")
	public void joinPage2() {}
	@PostMapping("/joinPage2.do")
	public String joinPage2(
			SignupDto signupDto,
			@RequestParam(required=true) String ctName,
			@RequestParam(required=true) String ctId,
			HttpSession session
			) {
		int insert=0;
		String msg="";
		try {
			passwordEncoder = new BCryptPasswordEncoder();
			signupDto.setCtPw(passwordEncoder.encode(signupDto.getCtPw()));
			insert = signupMapper.insert(signupDto);
//			String input=passwordEncoder.encode(signupDto.getCtPw());
//	        signupDto.setCtPw(input);
// 			userService.saveUserData(signupDto);
// 			insert=signupMapper.insert(signupDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return "redirect:/signup/joinPage3.do";
		if(insert>0) {
			session.setAttribute("signupDto",signupDto);
			return "redirect:/signup/joinPage3.do";
		} else {
			msg="회원가입 실패. 다시 입력해주세요";
			session.setAttribute("msg", msg);
			return "redirect:/signup/joinPage2.do";
		}
	}


	//로그인
			@GetMapping("/loginPage.do")
			public void loginPage() {

			}
			@PostMapping("/loginPage.do")
			public String loginPage(
					CustomerDto customerDto,
					@SessionAttribute SignupDto signupDto,
					@RequestParam(required = true) String ctId,
					@RequestParam(required = true) String ctPw,
					HttpSession session
					) {

				CustomerDto loginCt=null;
					// loginCt=customerMapper.login(ctId, ctPw);
				try {
					BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
					System.out.println(pe.matches(ctPw, signupDto.getCtPw()));
					if(pe.matches(ctPw, signupDto.getCtPw())){
						loginCt=customerMapper.login(ctId, signupDto.getCtPw());
					}else{
						return "redirect:/login/loginPage.do";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//boolean b =passwordEncoder.matches(ctPw, customerDto.getCtPw());
				if(loginCt!=null) {
					session.setAttribute("loginCt", loginCt);
					return "redirect:/";
				}else {
					return "redirect:/login/loginPage.do";
				}
			}




	//insert- 아이디 중복 체크 (userinsert.js)
		@Getter@Setter
		class CheckUser {
			private int check;        // 0:없음, 1:존재함, -1:통신오류
			private SignupDto user;
		}

		//@ResponseBody DTO 객체를 JSON으로 바꿔서 반환 (캡슐화된 필드만)
		@GetMapping("/checkUserId.do")
		public @ResponseBody CheckUser checkUserId(
				SignupDto user,
					@RequestParam(required =true) String ctId
				) {
			CheckUser checkUser = new CheckUser();
			try {
				user = signupMapper.detail(ctId);
				if(user!=null) {
					checkUser.setCheck(1);
					checkUser.setUser(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
				checkUser.setCheck(-1);
			}
			return checkUser;
		}

	@GetMapping("/joinPage3.do")
	public void joinPage3() {}



}
