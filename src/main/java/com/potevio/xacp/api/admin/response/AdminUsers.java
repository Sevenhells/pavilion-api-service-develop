package com.potevio.xacp.api.admin.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdminUsers{

    private String id;

    private String mobile;

    private Integer status;

    private String name;

    private String createdAt;

    private String idCardNo;

    private BigDecimal balance;

}