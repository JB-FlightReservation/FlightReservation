package com.joongbu.flight_reservation.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

// 예약할 때 데이터를 넘겨주기 위한 임시 예약 객체

@Data
public class ReservationProgress {

	// 예약번호는 자동 index해놔서 자동으로 들어가짐

	private List<PassengerRP> pList; // 탑승자 리스트 (PassengerRP)

	private String rvPhone; // 예약 대표자 전화번호
	private String rvEmail; // 예약 대표자 이메일
	private int rvIsTicketed; // 예약여부 (0이면 예약 실패, 1이면 예약 성공)
	private Date rvDate; // 예약날짜 (현재시간으로)
	private int rvMatureAdult; // 성인 수
	private int rvMatureTeen; // 청소년 수
	private int rvMatureBaby; // 유아 수
	private String afDepartAp; // 출발 공항
	private String afLandAp; // 도착 공항
	private String afDepart; // 출발 항공사
	private String afLand; // 도착 항공사
	private String afName; // 항공편명 (항공기명)
	private int rvSeatGrade; // 좌석등급
	private int nextRvNo; // 편도면 null, 왕복이면
	private int prAirfare; // 항공 운임
	private int prFuelSurcharge; // 유류할증료
	private int prAirportUsageFee; // 공항료
	private int prDiscount; // 할인금액
	private int prTotal; // 총 금액
}
