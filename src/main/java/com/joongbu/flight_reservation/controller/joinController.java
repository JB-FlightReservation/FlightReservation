package com.joongbu.flight_reservation.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joongbu.flight_reservation.dto.SignupDto;
import com.joongbu.flight_reservation.mapper.SignupMapper;


@RequestMapping("/signup")
@Controller 
public class joinController {
	@Autowired //객체를 주입받겠다
	DataSource dataSource;
	SignupMapper signupMapper; 
	
	@GetMapping("/joinPage.do")
	public void joinPage() {
	}
	
	@GetMapping("/joinPage2.do")
	public void joinPage2() {} 
	@PostMapping("/joinPage2.do")
	public String joinPage2(SignupDto signupDto, String ctName) {
	
		return "redirect:/signup/joinPage3.do";
//		int insert=0;
//		try {
//			insert=signupMapper.insert(signupDto);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(signupDto);
//		if(insert>0) {
//			return "redirect:/signup/joinPage3.do";
//		} else {
//			return "redirect:/signup/joinPage2.do";
//		}
	} 

	
	
	@GetMapping("/joinPage3.do")
	public void joinPage3() {} 
	
	

}
