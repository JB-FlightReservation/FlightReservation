package com.joongbu.flight_reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
+---------------------+--------------+------+-----+---------+-------+
| Field               | Type         | Null | Key | Default | Extra |
+---------------------+--------------+------+-----+---------+-------+
| rv_no               | int unsigned | NO   | PRI | NULL    |       |
| ct_no               | int unsigned | NO   | MUL | NULL    |       |
| rv_pet_accompanying | tinyint(1)   | NO   |     | NULL    |       |
| rv_phone            | varchar(45)  | NO   |     | NULL    |       |
| rv_email            | varchar(45)  | NO   |     | NULL    |       |
| rv_is_ticketed      | tinyint(1)   | NO   |     | NULL    |       |
| rv_date             | date         | NO   |     | NULL    |       |
| rv_mature_adult     | int unsigned | NO   |     | NULL    |       |
| rv_mature_teen      | int unsigned | NO   |     | NULL    |       |
| rv_mature_baby      | int unsigned | NO   |     | NULL    |       |
| rv_depart_ap        | varchar(45)  | NO   |     | NULL    |       |
| rv_land_ap          | varchar(45)  | NO   |     | NULL    |       |
| next_rv_no          | int unsigned | YES  |     | NULL    |       |
+---------------------+--------------+------+-----+---------+-------+
 */
@Getter@Setter
public class ReservationDto {
    private int rvNo;   //  예약번호
    private int ctNo;   //  고객번호
    private int rvPetAccompanying;  //  반려동물동반
    private String  rvPhone;    //  예약전화번호
    private String rvEamil; //  예약이메일
    private int rvIsTicketed;   //  발권여부?
    private Date rvDate;    //  예약날짜
    private int rvMatureAdult;  //  성인
    private int rvMatureTeen;   //  미성년자
    private int rvMatureBaby;   //  유아
    private String rvDepartAp;  //  이륙시간
    private String rvLandAP;    //  착륙시간
    private String nextRvNo;    //  왕복여부?
}
