package com.joongbu.flight_reservation.dto;

import lombok.Data;

//예약할 때 데이터를 넘겨주기 위한 임시 예약 객체 - 탑승자 리스트
@Data
public class PassengerRP {
	
	private String pgFirstName; // 탑승객 이름
	private String pgLastName; // 탑승객 성
	private int pgGender; // 탑승객 성별
	private String pgBirth; // 탑승객 생년월일 (Date로 바꿀 예정)
	private String pgCountry; // 탑승객 국적
	private int pgBaggage; // 탑승객 수화물
}
