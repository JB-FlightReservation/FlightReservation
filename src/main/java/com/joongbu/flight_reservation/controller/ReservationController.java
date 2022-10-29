package com.joongbu.flight_reservation.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.flight_reservation.dto.AirflightDto;
import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.dto.PassengerInfoDto;
import com.joongbu.flight_reservation.dto.ReservationDto;
import com.joongbu.flight_reservation.mapper.AirflightMapper;
import com.joongbu.flight_reservation.mapper.ReservationMapper;

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

	@PostMapping("/book")
	public String firstBook(ReservationDto rDto, AirflightDto aDto, HttpSession session) {
		
		session.setAttribute("rSession", rDto); // ReservationDto session
		session.setAttribute("aSession", aDto); // AirflightDto session

		return "redirect:/reservation/passenger.do";
	}

	// ------------- 예매 2 ------------------
	@GetMapping("/passenger.do")
	public String passenger() {
		return "reservation/passenger";
	}

	@PostMapping("/passenger")
	public String passengerInput(PassengerInfoDto pDto, ReservationDto rDto, HttpSession session,
			@SessionAttribute(required = false) CustomerDto loginCt,
			@SessionAttribute ReservationDto rSession) {
		
		rDto.setRvEmail(rDto.getRvEmail());
		rDto.setRvPhone(rDto.getRvPhone());
		
		session.setAttribute("pSession", pDto);
		
		return "redirect:/reservation/baggage.do";
	}

	// ------------- 예매 3 ------------------
	@GetMapping("/baggage.do")
	public String baggage() {
		
//		System.out.println(pDto);
		
		// pDto.setPgBaggage(pSession.getPgBaggage());
		
//		System.out.println(pSession);
		return "reservation/baggage";
	}
	
	@PostMapping("/baggage")
	public void baggageInput(PassengerInfoDto pDto, @SessionAttribute ReservationDto rSession, @SessionAttribute PassengerInfoDto pSession) {
		
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
