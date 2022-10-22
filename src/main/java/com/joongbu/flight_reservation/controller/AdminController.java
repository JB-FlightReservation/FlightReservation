package com.joongbu.flight_reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	DataSource dataSource;

	@Autowired
	AdminMapper adminMapper;

	@GetMapping("/userManagement.do")
	public String userManagement(Model model, @RequestParam(defaultValue = "1") int page) {
		final int ROWS = 10;
		int startRow = (page - 1) * ROWS;
		List<CustomerDto> customerList = adminMapper.customerList(startRow, ROWS);
		model.addAttribute("customerList", customerList);
		return "/adminpage/userManagement";
	}

	@GetMapping("/reservationManagement.do")
	public void reservationManagement() {
	}

	@GetMapping("reservationDetailManagement.do")
	public void reservationDetailManagement() {
	}

	@GetMapping("createCoupon.do")
	public void createCoupon() {
	}

	@GetMapping("coupon.do")
	public void coupon() {
	}

	@GetMapping("counselling.do")
	public void counselling() {
	}

}
