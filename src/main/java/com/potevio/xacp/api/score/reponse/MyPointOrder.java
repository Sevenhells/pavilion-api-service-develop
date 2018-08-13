package com.potevio.xacp.api.score.reponse;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MyPointOrder {

    private Integer id;

    private String orderNo;

    private Integer status;

    private String name;

    private String coverImage;

    private BigDecimal amount;

    private String createdAt;

}
