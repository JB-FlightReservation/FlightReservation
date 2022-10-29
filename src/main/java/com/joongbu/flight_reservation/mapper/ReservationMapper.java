package com.joongbu.flight_reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.flight_reservation.dto.ReservationDto;

@Mapper
public interface ReservationMapper {

	List<ReservationDto> reservationList();
	ReservationDto detail(int rvNo);
	int insert(ReservationDto reservation);
	int update(ReservationDto reservation);
	int delete(int afNo);
}
