package com.joongbu.flight_reservation.dto;

import lombok.Data;

@Data
public class SearchDto {
    private int page=1;
    private int rows = 10;  //  한 페이지에 출력될 rows
    private int navSize = 5;    //  페이지 네비의 수
    private String orderBy;
    private String field;   //  검샐할 필드
    private String value;   //  검색할 필드의 값


    public SearchDto() {
    }

    public SearchDto(int page, String orderBy) {
        this.page = page;
        this.orderBy = orderBy;
    }

    public SearchDto(int page, String orderBy, int rows) {
        this.page = page;
        this.orderBy = orderBy;
        this.rows = rows;
    }

    public SearchDto(int page, String orderBy, int rows, int navSize) {
        this.page = page;
        this.orderBy = orderBy;
        this.rows = rows;
        this.navSize = navSize;
    }

    public SearchDto(int page, String orderBy, String field, String value) {
        this.page = page;
        this.orderBy = orderBy;
        this.field = field;
        this.value = value;
    }
}
