package com.joongbu.flight_reservation.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class CouponDto {

private int cpNo;
private int adminNo;
private String cpName;
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date cpStart;
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date cpEnd;
private int cpDiscount;


}
