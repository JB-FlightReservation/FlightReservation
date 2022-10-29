package com.joongbu.flight_reservation.dto;

import java.util.Date;

import lombok.Data;

/*
+-----------------+--------------+------+-----+---------+-------+
| Field           | Type         | Null | Key | Default | Extra |
+-----------------+--------------+------+-----+---------+-------+
| rv_no           | int unsigned | NO   | PRI | NULL    |       |
| ct_no           | int unsigned | NO   | MUL | NULL    |       |
| rv_phone        | varchar(45)  | NO   |     | NULL    |       |
| rv_email        | varchar(45)  | NO   |     | NULL    |       |
| rv_is_ticketed  | tinyint(1)   | NO   |     | NULL    |       |
| rv_date         | date         | NO   |     | NULL    |       |
| rv_mature_adult | int unsigned | NO   |     | NULL    |       |
| rv_mature_teen  | int unsigned | NO   |     | NULL    |       |
| rv_mature_baby  | int unsigned | NO   |     | NULL    |       |
| rv_depart_ap    | varchar(45)  | NO   |     | NULL    |       |
| rv_land_ap      | varchar(45)  | NO   |     | NULL    |       |
| next_rv_no      | int unsigned | YES  |     | NULL    |       |
| rv_seat_grade   | varchar(45)  | NO   |     | NULL    |       |
+-----------------+--------------+------+-----+---------+-------+ 
 */

@Data
public class ReservationDto implements DTO{
	private int rvNo;
	private int ctNo;
	private int rvPetAccompanying;
	private String rvPhone;
	private String rvEmail;
	private int rvIsTicketed;
	private Date rvDate;
	private int rvMatureAdult;
	private int rvMatureTeen;
	private int rvMatureBaby;
	private String rvDepartAp;
	private String rvLandAp;
	private int nextRvNo;
	private String rvSeatGrade;
	
	
}
