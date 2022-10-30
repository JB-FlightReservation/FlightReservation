package com.joongbu.flight_reservation.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joongbu.flight_reservation.dto.*;
import com.joongbu.flight_reservation.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    public PageInfo<CustomerDto> customerPaging(SearchDto search, String ctName)   {
        PageHelper.startPage(search.getPage(), search.getRows(), search.getOrderBy());
        return PageInfo.of(adminMapper.customerList(ctName), search.getNavSize());
    }

    public PageInfo<ReservationDto> reservationPaging(SearchDto search, Integer rvNo)  {
        PageHelper.startPage(search.getPage(), 20, search.getOrderBy());
        return  PageInfo.of(adminMapper.reservationList(rvNo), search.getNavSize());
    }





}
