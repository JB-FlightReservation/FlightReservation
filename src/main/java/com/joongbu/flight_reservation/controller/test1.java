package com.joongbu.flight_reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class test1 {
	
	@GetMapping("/signup/joinPage.do")
	public String joinpage() {
		return "/signup/joinPage";
	}

}
