package com.potevio.xacp.api.admin.response;

import lombok.Data;

@Data
public class Reservation {

    private Integer id;

    private String userAccountId;

    private String name;

    private String idcardNumber;

    private String mobile;
    // 参观时间
    private Integer visitDatetime;

    private Integer status;

    private String code;

    private String bandId;
    // 进入时间
    private Integer enterDatetime;
}