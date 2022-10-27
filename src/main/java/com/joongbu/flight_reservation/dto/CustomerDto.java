package com.joongbu.flight_reservation.dto;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/*
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| ct_no     | int unsigned | NO   | PRI | NULL    | auto_increment |
| ct_id     | varchar(45)  | NO   | UNI | NULL    |                |
| ct_pw     | varchar(45)  | NO   |     | NULL    |                |
| ct_name   | varchar(45)  | NO   |     | NULL    |                |
| ct_email  | varchar(45)  | NO   |     | NULL    |                |
| ct_phone  | varchar(45)  | NO   |     | NULL    |                |
| ct_birth  | date         | NO   |     | NULL    |                |
| ct_gender | char(1)      | NO   |     | NULL    |                |
| ct_create | date         | NO   |     | NULL    |                |
| oc_no     | int unsigned | NO   | UNI | NULL    |                |
+-----------+--------------+------+-----+---------+----------------+
 */
@Data
public class CustomerDto {
	private int ctNo;
	private String ctId;
	private String ctPw;
	private String ctName;
	private String ctEmail;
	private String ctPhone;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ctBirth;
	private char ctGender;
	private Date ctCreate;
	private int ocNo;
}
