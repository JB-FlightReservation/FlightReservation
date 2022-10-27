package com.joongbu.flight_reservation.mapper;

import com.joongbu.flight_reservation.dto.AdminDto;
import com.joongbu.flight_reservation.dto.CustomerDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<CustomerDto> customerList (int startRow, int rows);
    List<AdminDto> adminList ();

    AdminDto login(String adminId,String adminPw);
    AdminDto adminDetail(String adminId);
    int Aupdate(AdminDto admin);
    
    //int update(CustomerDto customer);
    //int delete(int ctNo);
    int insert(CustomerDto customer);
}
