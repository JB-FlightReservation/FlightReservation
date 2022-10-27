package com.joongbu.flight_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.joongbu.flight_reservation.mapper.ReservationMapper;

@RequestMapping("/reservation")
@Controller
public class ReservationController {
	@Autowired
	ReservationMapper reservationMapper;
	
	@GetMapping("/booking.do")
	public String booking() {
		return "reservation/reservationSearch";
	}


	/*@GetMapping("/booking.do")
	@ResponseBody
	public String booking(
			@RequestParam int depart_ap_no,
			@RequestParam int landingAriport){
		return "reservation/reservationSearch"+"?";
	}*/


	@GetMapping("/passenger_list.do")
	public void passenger() {

	}
	
	@PostMapping("/pList.do")
	public void pList() {
		
	}
	
	@GetMapping("/baggage.do")
	public void baggage() {

	}

	@GetMapping("/terms.do")
	public String terms() {
		return "reservation/reservationTerms";
	}

	@GetMapping("/pay.do")
	public String pay() {
		return "reservation/reservationPay";
	}

	@GetMapping("/payComplete.do")
	public String payComplete() {
		return "reservation/reservationPayComplete";
	}

}