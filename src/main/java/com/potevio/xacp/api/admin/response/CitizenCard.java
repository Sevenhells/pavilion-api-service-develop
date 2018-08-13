package com.potevio.xacp.api.admin.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CitizenCard {

    private Integer id;

    private String serialNumber;

    private String userAccountId;

    private String name;

    private String mobile;

    private String avatar;

    private String idCardNo;

    private Integer type;

    private Integer createdAt;

}