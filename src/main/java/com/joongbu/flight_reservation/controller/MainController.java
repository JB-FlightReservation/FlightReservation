package com.joongbu.flight_reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class MainController {
	
	@GetMapping("/airplaneStatus.do")
	public void airplaneStatus() {}
}
