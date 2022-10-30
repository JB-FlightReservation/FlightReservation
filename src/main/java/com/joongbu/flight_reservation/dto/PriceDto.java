package com.joongbu.flight_reservation.dto;

import lombok.Data;

/*
 +----------------------+--------------+------+-----+---------+----------------+
| Field                | Type         | Null | Key | Default | Extra          |
+----------------------+--------------+------+-----+---------+----------------+
| pr_no                | int unsigned | NO   | PRI | NULL    | auto_increment |
| pg_no                | int unsigned | NO   | MUL | NULL    |                |
| pr_airfare           | int unsigned | NO   |     | NULL    |                |
| pr_fuel_surcharge    | int unsigned | NO   |     | NULL    |                |
| pr_airport_usage_fee | int unsigned | NO   |     | NULL    |                |
| pr_discount          | int unsigned | NO   |     | NULL    |                |
| pr_total             | int unsigned | NO   |     | NULL    |                |
+----------------------+--------------+------+-----+---------+----------------+
 * */
@Data
public class PriceDto {

	private int prNo;
	private int pgNo;
	private int prAirfare;
	private int prFuelSurcharge;
	private int prAirportUsageFee;
	private int prDiscount;
	private int prTotal;
	
}
