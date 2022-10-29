	package com.joongbu.flight_reservation.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/*
+---------------+------------------+------+-----+---------+----------------+
| Field         | Type             | Null | Key | Default | Extra          |
+---------------+------------------+------+-----+---------+----------------+
| pg_no         | int unsigned     | NO   | PRI | NULL    | auto_increment |
| pg_first_name | varchar(45)      | NO   |     | NULL    |                |
| pg_last_name  | varchar(45)      | NO   |     | NULL    |                |
| pg_gender     | tinyint unsigned | NO   |     | NULL    |                |
| pg_birth      | date             | NO   |     | NULL    |                |
| pg_country    | varchar(45)      | NO   |     | NULL    |                |
| pg_baggage    | int unsigned     | NO   |     | NULL    |                |
+---------------+------------------+------+-----+---------+----------------+
 */
@Data
public class PassengerInfoDto{
	
	private int pgNo;
	private String pgFirstName;
	private String pgLastName;
	private int pgGender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pgBirth;  // Date로 바꿀 예정
	private String pgCountry;
	private int pgBaggage;
	private List<PassengerInfoDto> p;
}
