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
+-------------+--------------+------+-----+---------+----------------+
 * */
@Data
public class AdminDto {
	private int admin_no;
	private String adminId;
	private String admin_pw;
	private String admin_name;
	private String admin_email;
}
