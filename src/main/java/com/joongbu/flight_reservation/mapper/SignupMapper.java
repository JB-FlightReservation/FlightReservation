package com.joongbu.flight_reservation.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joongbu.flight_reservation.dto.SignupDto;
//com.joongbu.flight_reservation.mapper.SignupMapper
@Mapper
public interface SignupMapper {
	int insert(SignupDto signupDto);
	SignupDto detail(String ctId);
	SignupDto login(String ctName, String ctId);
}
