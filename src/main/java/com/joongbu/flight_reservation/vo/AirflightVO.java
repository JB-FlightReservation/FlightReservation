package com.joongbu.flight_reservation.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AirflightVO {
    private String vihicleId;
    private String airlineNm;
    private String depPlandTime;
    private String arrPlandTime;
    private String economyCharge;
    private String prestigeCharge;
    private String depAirportNm;
    private String arrAirportNm;

    public AirflightVO(String vihicleId, String airlineNm, String depPlandTime, String arrPlandTime, String economyCharge, String prestigeCharge, String depAirportNm, String arrAirportNm) {
        this.vihicleId = vihicleId;
        this.airlineNm = airlineNm;
        this.depPlandTime = depPlandTime;
        this.arrPlandTime = arrPlandTime;
        this.economyCharge = economyCharge;
        this.prestigeCharge = prestigeCharge;
        this.depAirportNm = depAirportNm;
        this.arrAirportNm = arrAirportNm;
    }
}
