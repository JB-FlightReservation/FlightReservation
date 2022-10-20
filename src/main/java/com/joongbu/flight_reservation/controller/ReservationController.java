package com.joongbu.flight_reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reservation")
@Controller
public class ReservationController {
	@GetMapping("/stage1.do")
	public String stage1() {
		System.out.println("Reservation page connected");
		return "reservation/reservationSearch";
	}
}
