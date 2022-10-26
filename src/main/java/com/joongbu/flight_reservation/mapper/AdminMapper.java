package com.joongbu.flight_reservation.mapper;

import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.dto.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<CustomerDto> customerList(int startRow, int rows, String ctName);


    int customerUpdate(CustomerDto customer);

    int customerDelete(int ctNo);

    List<ReservationDto> reservationList(int startRow, int rows, Integer ctNo);
}
