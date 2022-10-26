package com.joongbu.flight_reservation.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupDto {
	private int ctNo;
	private String ctId;
	private String ctPw;
	private String ctName;
	private String ctEmail;
	private String ctPhone;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ctBirth;
	private String ctGender;
	@DateTimeFormat(pattern = "HH:mm:ss yyyy-MM-dd")
	private Date ctCreate;
	private int ocNo; 
	
}