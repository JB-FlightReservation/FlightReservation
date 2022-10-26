package com.joongbu.flight_reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/search") //이 파일안에 예매가 있음
@Controller
public class SearchController {
	@GetMapping("/flightsearch.do")
	public String flightsearch() {
		return "/search/flightsearch";
	}
}
