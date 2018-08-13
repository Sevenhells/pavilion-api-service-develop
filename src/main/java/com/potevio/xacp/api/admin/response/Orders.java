package com.potevio.xacp.api.admin.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Orders {

    // 订单id
    private Integer id;

    // 订单号
    private String orderNo;

    // 积分账户id
    private Integer pointAccountId;

    // 用户账户id
    private String userAccountId;

    private String userName;

    private String userMobile;

    // 积分商品名字
    private String name;

    // 订单状态
    private Integer status;

    private String coverImage;

    // 商品名称
    private String openAccount;

    private String description;

    private String content;

    // 所花费积分
    private BigDecimal amount;

    // 优惠券
    private String couponCode;

    // 优惠券过期时间
    private Integer couponExpiration;

    private Integer createdAt;
}