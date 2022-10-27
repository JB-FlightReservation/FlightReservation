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
    | af_depart_ap   | varchar(45)  | NO   |     | NULL    |       |
    | af_land_ap     | varchar(45)  | NO   |     | NULL    |       |
    +----------------+--------------+------+-----+---------+-------+
*/

@Data
public class AirflightDto {
    int af_no;
    String af_name;
    int al_no;
    String af_depart;
    String af_land;
    Integer af_economy;
    Integer af_business;
    Timestamp af_depart_ap;
    Timestamp af_land_ap;
}
