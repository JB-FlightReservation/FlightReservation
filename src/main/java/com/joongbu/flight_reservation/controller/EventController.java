package com.joongbu.flight_reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.joongbu.flight_reservation.dto.CouponDto;
import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.dto.SearchDto;
import com.joongbu.flight_reservation.mapper.AdminMapper;
import com.joongbu.flight_reservation.service.AdminService;

@RequestMapping("/event")
@Controller
public class EventController {
	
	@Autowired
	AdminMapper adminMapper;

	@Autowired
	AdminService adminService;
	
	@GetMapping("/eventPage.do")
	public String eventPage() {
		return "/event/eventPage";
	}
	
	@GetMapping("/couponList.do")
	public String couponList(Model model, @RequestParam(defaultValue = "1") int page,
			SearchDto search) {
		final int ROWS = 10;
		int startRow = (page - 1) * ROWS;
		List<CouponDto> cpList = null;
		try {
			cpList = adminMapper.cpList(startRow, ROWS);
			if (search.getOrderBy() == null)
				search.setOrderBy("ct_no ASC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cpList", cpList);
		return "/event/eventPage";
	}
}
