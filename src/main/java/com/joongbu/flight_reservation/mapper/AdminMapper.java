package com.joongbu.flight_reservation.mapper;

import com.joongbu.flight_reservation.dto.CustomerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<CustomerDto> customerList (int startRow, int rows);

    int update(CustomerDto customer);
    int delete(int ctNo);
    int insert(CustomerDto customer);
}
