package com.joongbu.flight_reservation.dto;

import lombok.Data;

/*
+---------------+------------------+------+-----+---------+----------------+
| Field         | Type             | Null | Key | Default | Extra          |
+---------------+------------------+------+-----+---------+----------------+
| pg_no         | int unsigned     | NO   | PRI | NULL    | auto_increment |
| pg_first_name | varchar(45)      | NO   |     | NULL    |                |
| pg_last_name  | varchar(45)      | NO   |     | NULL    |                |
| pg_gender     | tinyint unsigned | NO   |     | NULL    |                |
| pg_birth      | varchar(45)      | NO   |     | NULL    |                |
| pg_country    | varchar(45)      | NO   |     | NULL    |                |
| pg_baggage    | int unsigned     | NO   |     | NULL    |                |
+---------------+------------------+------+-----+---------+----------------+
 */
@Data
public class PassengerInfoDto {
	
	private int pgNo;
	private String pgFirstName;
	private String pgLastName;
	private int pgGender;
	private String pgBirth;  // Date로 바꿀 예정
	private String pgCountry;
	private int pgBaggage;
}
