package com.joongbu.flight_reservation.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.joongbu.flight_reservation.dto.*;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface AdminMapper {
	/* 회원 관리 */
	Page<CustomerDto> customerList(String ctName);

	int customerUpdate(CustomerDto customer);

	int customerDelete(int ctNo);

	CustomerDto customerCheckId(String ctId);
	CustomerDto customerCheckName(String ctName);
	CustomerDto custerCheckSearch(String ctName);

	CustomerDto customerDetail(int ctNo);
	/* 예약 관리 */
	Page<ReservationDto> reservationList(Integer rvNo);



	/* 관리자 */
	List<AdminDto> adminList();

	AdminDto login(String adminId, String adminPw);

	AdminDto adminDetail(String adminId);

	int Aupdate(AdminDto admin);


	/* 쿠폰 관리 */
	int cpInsert(CouponDto couponDto);

	List<CouponDto> cpList (int startRow, int rows);
	
	int cpDelete(int cpNo);
	
	int cpUpdate(CouponDto couponDto);


	int cpGive(List<OwnedCouponDto> studyMaterials);
}