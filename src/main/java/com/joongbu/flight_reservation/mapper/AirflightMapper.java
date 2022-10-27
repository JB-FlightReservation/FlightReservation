package com.joongbu.flight_reservation.mapper;

import com.joongbu.flight_reservation.dto.AirflightDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirflightMapper {
    List<AirflightDto> airflightList();
    AirflightDto detail(int afNo);
    int insert(AirflightDto airflight);
    int update(AirflightDto airflight);
    int delete(int afNo);
}
