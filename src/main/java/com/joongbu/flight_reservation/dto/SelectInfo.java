package com.joongbu.flight_reservation.dto;

import lombok.Data;

@Data
public class SelectInfo {
    private String depAprt;
    private String landAprt;
    private String depDate;
    private String vihicleId;
    private String airlineNm;
    private String depTime;
    private String arrTime;
    private String seatGrade;
    private Integer seatGradeCharge;
}
