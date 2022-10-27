package com.joongbu.flight_reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.flight_reservation.dto.AdminDto;
import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.dto.ReservationDto;

@Mapper
public interface AdminMapper {

	List<CustomerDto> customerList(int startRow, int rows, String ctName);

	List<CustomerDto> customerList(int startRow, int rows);
	// List<AdminDto> adminList (@Param(value = "start")int startRow, int rows);

	int customerUpdate(CustomerDto customer);

	int customerDelete(int ctNo);

    List<ReservationDto> reservationList(int startRow, int rows, Integer ctNo);


	int update(CustomerDto customer);

	int delete(int ctNo);

	int insert(CustomerDto customer);


    List<AdminDto> adminList ();

    AdminDto login(String adminId,String adminPw);
    AdminDto adminDetail(String adminId);
    int Aupdate(AdminDto admin);


}
