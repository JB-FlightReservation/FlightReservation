package com.joongbu.flight_reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/event")
@Controller
public class EventController {
	@GetMapping("/eventPage.do")
	public String eventPage() {
		return "/event/eventPage";
	}
}
