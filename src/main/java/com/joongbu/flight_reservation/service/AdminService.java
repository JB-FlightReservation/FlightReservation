package com.joongbu.flight_reservation.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joongbu.flight_reservation.dto.CouponDto;
import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.dto.ReservationDto;
import com.joongbu.flight_reservation.dto.SearchDto;
import com.joongbu.flight_reservation.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    public PageInfo<CustomerDto> customerPaging(SearchDto search, String ctName)   {
        PageHelper.startPage(search.getPage(), search.getRows(), search.getOrderBy());
        return PageInfo.of(adminMapper.customerList(ctName), search.getNavSize());
    }

    public PageInfo<ReservationDto> reservationPaging(SearchDto search, Integer ctNo)  {
        PageHelper.startPage(search.getPage(), 20, search.getOrderBy());
        return  PageInfo.of(adminMapper.reservationList(ctNo), search.getNavSize());
    }

    public PageInfo<CouponDto> couponPaging(SearchDto search)  {
        PageHelper.startPage(search.getPage(), search.getRows(), search.getOrderBy());
        return  PageInfo.of(adminMapper.couponList(), search.getNavSize());
    }


}
