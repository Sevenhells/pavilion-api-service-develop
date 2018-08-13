package com.potevio.xacp.api.score.model;

import com.potevio.xacp.api.common.base.BaseEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Component
@Table(name = "point_order")
public class PointOrder{
    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;
    /**
     * 用户账户id
     */
    @Column(name = "user_account_id")
    private String userAccountId;

    /**
     * 商品id
     */
    @Column(name = "good_id")
    private Integer goodId;

    /**
     * 商品封面
     */
    @Column(name = "cover_image")
    private String coverImage;

    /**
     * 下单人姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 积分余额
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Integer createdAt;
}
