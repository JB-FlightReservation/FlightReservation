package com.joongbu.flight_reservation.controller;

import com.joongbu.flight_reservation.dto.AirflightDto;
import com.joongbu.flight_reservation.mapper.AirflightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.joongbu.flight_reservation.mapper.ReservationMapper;

import javax.sql.DataSource;
import java.util.List;

@RequestMapping("/reservation")
@Controller
public class ReservationController {

	@Autowired
	AirflightMapper afMapper;
	ReservationMapper rMapper;

	// ------------- 예매 1 ------------------
	@GetMapping("/booking.do")
	public String booking(Model model) {
		List<AirflightDto> airflightList = null;
		try {
			airflightList = afMapper.airflightList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("airflightList", airflightList);
		return "reservation/reservationSearch";
	}

	// ------------- 예매 2 ------------------
	@GetMapping("/passenger_list.do")
	public void passenger() {

	}

	@PostMapping("/pList.do")
	public void pList() {

	}

	
	
	
	
	
	
	
	
	
	// ------------- 예매 3 ------------------
	@GetMapping("/baggage.do")
	public void baggage() {

	}
	
	
	
	
	
	
	
	
	
	
	
	

	// ------------- 예매 4 ------------------
	@GetMapping("/terms.do")
	public String terms() {
		return "reservation/reservationTerms";
	}

	// ------------- 예매 5 ------------------
	@GetMapping("/pay.do")
	public String pay() {
		return "reservation/reservationPay";
	}

	@GetMapping("/payComplete.do")
	public String payComplete() {
		return "reservation/reservationPayComplete";
	}

}
