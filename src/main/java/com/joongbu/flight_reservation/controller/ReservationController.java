package com.joongbu.flight_reservation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joongbu.flight_reservation.dto.AirflightDto;
import com.joongbu.flight_reservation.dto.PassengerRP;
import com.joongbu.flight_reservation.dto.ReservationProgress;
import com.joongbu.flight_reservation.mapper.AirflightMapper;
import com.joongbu.flight_reservation.mapper.ReservationMapper;

@RequestMapping("/reservation")
@Controller
public class ReservationController {

	@Autowired
	AirflightMapper afMapper;
	ReservationMapper rMapper;

	ReservationProgress rp = new ReservationProgress();
	PassengerRP prp = new PassengerRP();
	List<PassengerRP> pList = new ArrayList<PassengerRP>();

	// ------------- 예매 1 ------------------
	@GetMapping("/booking.do")
	public String booking(@RequestBody JSONObject getAirlineInfo, Model model) {
		List<AirflightDto> airflightList = null;
		try {
			airflightList = afMapper.airflightList();
			System.out.println(getAirlineInfo.get("item"));
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

	@PostMapping("/passenger_list.do")
	public void passenger(HttpServletRequest req) {

//		System.out.println(req.getParameter("pgCountry"));
		
		prp.setPgFirstName(req.getParameter("pgFirstName"));
		prp.setPgLastName(req.getParameter("pgLastName"));
		pList.add(prp);
		
		System.out.println(pList);

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
