package com.joongbu.flight_reservation.dto;

import lombok.Data;

import java.sql.Timestamp;

/*
  [Table: airflight_info]
    +----------------+--------------+------+-----+---------+-------+
    | Field          | Type         | Null | Key | Default | Extra |
    +----------------+--------------+------+-----+---------+-------+
    | af_no          | int unsigned | NO   | PRI | NULL    |       |
    | af_name        | varchar(45)  | NO   |     | NULL    |       |
    | al_no          | int unsigned | NO   | MUL | NULL    |       |
    | af_depart_time | datetime     | NO   |     | NULL    |       |
    | af_land_time   | datetime     | NO   |     | NULL    |       |
    | af_economy     | int unsigned | YES  |     | NULL    |       |
    | af_business    | int unsigned | YES  |     | NULL    |       |
    | af_depart_ap   | int unsigned | NO   |     | NULL    |       |
    | af_land_ap     | int unsigned | NO   |     | NULL    |       |
    +----------------+--------------+------+-----+---------+-------+
*/

@Data
public class AirflightDto {
    private int afNo;
    private String afName;
    private Timestamp afDepartTime;
    private Timestamp afLandTime;
    private Integer afEconomy;
    private Integer afBusiness;
    private String afDepartAp;
    private String afLandAp;
    private String rvSeatGrade;
}
