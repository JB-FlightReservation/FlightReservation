package com.joongbu.flight_reservation.dto;

import lombok.Data;

/*
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| admin_no    | int unsigned | NO   | PRI | NULL    | auto_increment |
| admin_id    | varchar(45)  | NO   |     | NULL    |                |
| admin_pw    | varchar(45)  | NO   |     | NULL    |                |
| admin_name  | varchar(45)  | NO   |     | NULL    |                |
| admin_email | varchar(45)  | NO   |     | NULL    |                |
| admin_eno   | int unsigned | NO   | UNI | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
 * */
@Data
public class AdminDto {
	private int adminNo;
	private String adminId;
	private String adminPw;
	private String adminName;
	private String adminEmail;
	private int adminEno;
}
