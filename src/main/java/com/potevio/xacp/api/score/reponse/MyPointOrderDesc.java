package com.potevio.xacp.api.score.reponse;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MyPointOrderDesc {

    private Integer id;

    private String orderNo;

    private Integer status;

    private String name;

    private String coverImage;

    private BigDecimal amount;

    // 优惠券
    private String couponCode;

    // 优惠券过期时间
    private String couponExpiration;

    private String createdAt;

}
