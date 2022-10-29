package com.joongbu.flight_reservation.vo;

import lombok.Data;

@Data
public class AirportVO {
    String airportId;
    String airportNm;

    public AirportVO(String airportId, String airportNm) {
        this.airportId = airportId;
        this.airportNm = airportNm;
    }
}
