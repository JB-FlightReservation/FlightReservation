package com.joongbu.flight_reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reservation")
@Controller
public class ReservationController {
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