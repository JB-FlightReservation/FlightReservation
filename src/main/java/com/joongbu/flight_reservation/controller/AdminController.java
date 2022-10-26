package com.joongbu.flight_reservation.controller;

import com.joongbu.flight_reservation.dto.ReservationDto;
import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.List;

@RequestMapping("/adminpage")
@Controller
public class AdminController {
    @Autowired
    DataSource dataSource;

    @Autowired
    AdminMapper adminMapper;

    /*                          회원 관리 시작                         */
    @GetMapping("/userManagement.do")
    public String userManagement(Model model, @RequestParam(defaultValue = "1") int page,@RequestParam(required = false) String ctName) {
        final int ROWS = 10;
        int startRow = (page - 1) * ROWS;
        List<CustomerDto> customerList = null;
        try {
            customerList = adminMapper.customerList(startRow, ROWS,ctName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("customerList", customerList);
        return "/adminpage/userManagement";
    }
    

    @GetMapping("/customerDelete.do")
    public String customerDelete(int ctNo) {
        int delete = 0;
        try {
            delete = adminMapper.customerDelete(ctNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (delete > 0) {
            return "redirect:/adminpage/userManagement.do";
        } else {
            return "redirect:/adminpage/management.do";
        }
    }
    @PostMapping("/customerUpdate.do")
    public String customerUpdate(CustomerDto customerDto)    {
        int update = 0;
        try {
            update = adminMapper.customerUpdate(customerDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(update);
        if (update > 0) {
            return "redirect:/adminpage/userManagement.do";
        } else {
            return "redirect:/adminpage/management.do";
        }
    }
    /*                          회원 관리 끝                           */

    /*                          예약 관리 시작                         */
    @GetMapping("/reservationManagement.do")
    public String reservationManagement(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(required = false) Integer ctNo) {

        final int ROWS = 10;
        int startRow = (page - 1) * ROWS;
        List<ReservationDto> reservationList = null;
        try {
            reservationList = adminMapper.reservationList(startRow, ROWS, ctNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("reservationList", reservationList);
        return "/adminpage/reservationManagement";
    }


    @GetMapping("reservationDetailManagement.do")
    public void reservationDetailManagement() {

    }
    /*                          예약 관리 끝                         */
    @GetMapping("management.do")
    public void management(){}


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
