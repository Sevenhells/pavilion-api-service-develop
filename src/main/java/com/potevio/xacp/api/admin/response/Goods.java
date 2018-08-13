package com.potevio.xacp.api.admin.response;

import lombok.Data;

import javax.persistence.Table;

@Data
public class Goods {

    private Integer id;

    private String openAccount;

    private String name;

    private String desc;

    private String content;

    private String amount;

    private Integer stock;
}