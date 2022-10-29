package com.joongbu.flight_reservation.vo;

import lombok.Data;

@Data
public class AirlineVO {
    private String airlineId;
    private String airlineNm;

    public AirlineVO(String airlineId, String airlineNm){
        this.airlineId = airlineId;
        this.airlineNm = airlineNm;
    }
}
