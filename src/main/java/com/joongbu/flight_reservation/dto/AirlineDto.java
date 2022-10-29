package com.joongbu.flight_reservation.dto;

import lombok.Data;


/*
  +---------+--------------+------+-----+---------+-------+
  | Field   | Type         | Null | Key | Default | Extra |
  +---------+--------------+------+-----+---------+-------+
  | al_no   | int unsigned | NO   | PRI | NULL    |       |
  | al_name | varchar(45)  | NO   |     | NULL    |       |
  +---------+--------------+------+-----+---------+-------+
*/
@Data
public class AirlineDto {
    private int alNo;
    private String alName;
}
