package com.joongbu.flight_reservation.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import com.joongbu.flight_reservation.dto.AdminDto;
import com.joongbu.flight_reservation.dto.CouponDto;
import com.joongbu.flight_reservation.dto.CustomerDto;
import com.joongbu.flight_reservation.dto.ReservationDto;

@Mapper
public interface AdminMapper {
	/* 회원 관리 */
	Page<CustomerDto> customerList(String ctName);

	int customerUpdate(CustomerDto customer);

	int customerDelete(int ctNo);

	CustomerDto customerCheckId(String ctId);
	CustomerDto customerCheckName(String ctName);

	CustomerDto customerDetail(int ctNo);
	/* 예약 관리 */
	Page<ReservationDto> reservationList(Integer ctNo);


	/* 쿠폰 관리 */
	Page<CouponDto> couponList();

	/* 관리자 */
	List<AdminDto> adminList();

	AdminDto login(String adminId, String adminPw);

	AdminDto adminDetail(String adminId);

	int Aupdate(AdminDto admin);

	int update(CustomerDto customer);

	int delete(int ctNo);

	int insert(CustomerDto customer);
	
	int cpInsert(CouponDto couponDto);
	
	Page<CouponDto> cpList ();
	
	int cpDelete(int cpNo);
	
	int cpUpdate(CouponDto couponDto);

}
