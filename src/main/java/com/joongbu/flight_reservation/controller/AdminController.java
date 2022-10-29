
package com.joongbu.flight_reservation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.joongbu.flight_reservation.dto.*;
import com.joongbu.flight_reservation.service.AdminService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.joongbu.flight_reservation.mapper.AdminMapper;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@RequestMapping("/adminpage")
@Controller
public class AdminController {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminService adminService;

    /* 회원 관리 시작 */
    @GetMapping("/userManagement.do")
    public String userManagement(Model model,
                                 @RequestParam(required = false) String ctName, SearchDto search) {

        PageInfo<CustomerDto> paging = null;
        try {
            if (search.getOrderBy() == null)
                search.setOrderBy("ct_no ASC");
            paging = adminService.customerPaging(search, ctName);
            System.out.println(paging);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("paging", paging);
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
    public String customerUpdate(CustomerDto customerDto) {
        int update = 0;
        try {
            update = adminMapper.customerUpdate(customerDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (update > 0) {
            return "redirect:/adminpage/userManagement.do";
        } else {
            return "redirect:/adminpage/management.do";
        }
    }
    @Data
    class CheckCustomer {
        private int check;
        private CustomerDto customer;
    }

    @GetMapping("checkCustomerId.do")
    public @ResponseBody CheckCustomer checkCustomerId  (
            CustomerDto customer,
            @RequestParam(required = true) String ctId
    )   {
        CheckCustomer checkCustomer = new CheckCustomer();
        try {
            customer = adminMapper.customerCheckId(ctId);
            if (customer != null)   {
                checkCustomer.setCheck(1);
                checkCustomer.setCustomer(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            checkCustomer.setCheck(-1);
        }
        return checkCustomer;
    }

    @GetMapping("checkCustomerName.do")
    public @ResponseBody CheckCustomer checkCustomerName    (
            CustomerDto customer,
            @RequestParam(required = true) String ctName
    )   {
        CheckCustomer checkCustomer = new CheckCustomer();
        try {
            customer = adminMapper.customerCheckName(ctName);
            if (customer != null)   {
                checkCustomer.setCheck(1);
                checkCustomer.setCustomer(customer);
            }
        } catch (Exception e) {
            checkCustomer.setCheck(-1);
            e.printStackTrace();
        }
        return checkCustomer;
    }
    @GetMapping("/customerDetail.do")
    public void customerDetail(@RequestParam(required = true) int ctNo,
                               Model model,
                               SearchDto searchDto) {
        CustomerDto customer = null;
        try {
            customer = adminMapper.customerDetail(ctNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (customer != null)   {
            model.addAttribute("customer", customer);
        }
    }

    /* 회원 관리 끝 */

    /* 예약 관리 시작 */
    @GetMapping("/reservationManagement.do")
    public String reservationManagement(Model model, @RequestParam(required = false) Integer ctNo, SearchDto search) {

        final int ROWS = 10;
        PageInfo<ReservationDto> reservation = null;
        PageInfo<ReservationDto> reservationAfter = null;
        try {
            if (search.getOrderBy() == null)
                search.setOrderBy("ct_no ASC");
            reservation = adminService.reservationPaging(search, ctNo);
            reservationAfter = adminService.reservationAfterPaging(search, ctNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("reservation", reservation);
        model.addAttribute("reservationAfter", reservationAfter);
        return "/adminpage/reservationManagement";
    }



    /* 예약 관리 끝 */

    /* 쿠폰 관리 */
    @GetMapping("couponManagement.do")
    public String couponManagement(Model model, SearchDto search) {
        PageInfo<CouponDto> coupon = null;
        try {
            if (search.getOrderBy() == null) search.setOrderBy("cp_no ASC");
            coupon = adminService.couponPaging(search);
            System.out.println(coupon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("paging", coupon);

        return "adminpage/coupon";

    }

    /* 쿠폰 관리 끝 */
    @GetMapping("management.do")
    public void managemen() {
    }


    @GetMapping("counselling.do")
    public void counselling() {
    }

    @GetMapping("/loginpage.do")
    public void loginpage(HttpServletRequest req, HttpSession session) {
        String refererPage = req.getHeader("Referer");
        session.setAttribute("redirectPage", refererPage);
    }

    @PostMapping("/loginpage.do")
    public String loginpage(@RequestParam(required = true) String adminId,
                            @RequestParam(required = true) String adminPw, HttpSession session,
                            @SessionAttribute(required = false) String redirectPage) {
        AdminDto loginAdmin = null;
        System.out.println(adminId + adminPw);
        try {
            loginAdmin = adminMapper.login(adminId, adminPw);
            System.out.println(loginAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (loginAdmin != null) {
            session.setAttribute("loginAdmin", loginAdmin);
            if (redirectPage == null) {
                return "redirect:/adminpage/management.do";
            } else {
                return "redirect:" + redirectPage;
            }
        } else {
            return "redirect:/adminpage/loginpage.do";
        }

    }

    @GetMapping("/logout.do")
    public String logout(HttpSession session) {
        session.removeAttribute("loginAdmin");
        return "redirect:/adminpage/management.do";
    }



    @GetMapping("/adminDetail.do")
    public AdminDto adminDetail(String adminId) {
        AdminDto adminDto = adminMapper.adminDetail(adminId);

        return adminDto;
    }

    @PostMapping("/update.do")
    public String Aupdate(AdminDto admin) {
        int update = 0;
        try {
            update = adminMapper.Aupdate(admin);
            System.out.println(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (update > 0) {
            return "redirect:/adminpage/management.do";
        } else {
            return "redirect:/adminpage/detail.do?adminId=" + admin.getAdminId();
        }
    }


}
