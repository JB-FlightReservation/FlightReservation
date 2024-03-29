package com.joongbu.flight_reservation.dto;

/*
  +---------+--------------+------+-----+---------+-------+
  | Field   | Type         | Null | Key | Default | Extra |
  +---------+--------------+------+-----+---------+-------+
  | ap_no   | int unsigned | NO   | PRI | NULL    |       |
  | af_no   | int unsigned | NO   | MUL | NULL    |       |
  | ap_name | varchar(45)  | NO   |     | NULL    |       |
  +---------+--------------+------+-----+---------+-------+
*/

import lombok.Data;

@Data
public class AirportDto {
    private int apNo;
    private int afNo;
    private String apName;
}
