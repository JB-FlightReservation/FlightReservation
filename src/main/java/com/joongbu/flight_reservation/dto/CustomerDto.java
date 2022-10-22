package com.joongbu.flight_reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
@Getter@Setter
public class CustomerDto {
    private int ctNo;   //  고객번호
    private String ctId;    //  고객아이디
    private String ctPw;    //  고객비밀번호;
    private String ctName;  //  고객이름
    private String ctEmail; //  고객이메일
    private String ctPhone; //  고객휴대전화
    private Date ctBirth;   //  고객생일
    private String ctGender;    //  고객성별
    private Date ctCreate;  //  회원가입일
    private int ocNo;   //  쿠폰번호?
}
