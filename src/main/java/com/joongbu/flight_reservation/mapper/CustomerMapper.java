package com.joongbu.flight_reservation.mapper;

import org.apache.ibatis.annotations.Mapper;
//com.joongbu.flight_reservation.mapper.CustomerMapper

import com.joongbu.flight_reservation.dto.CustomerDto;
@Mapper
public interface CustomerMapper {
	CustomerDto login(String ctId, String ctPw);
	CustomerDto find(String ctName, String ctEmail);
	CustomerDto loginCheck(String ctId, String ctPw);
}
