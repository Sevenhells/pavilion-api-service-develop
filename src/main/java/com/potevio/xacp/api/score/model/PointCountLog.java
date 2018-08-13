package com.potevio.xacp.api.score.model;

import com.potevio.xacp.api.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "point_account_log")
public class PointCountLog extends BaseEntity {

    /**
    * 用户账户id
    * */
    @Column(name = "point_account_id")
    private Integer pointAccountId;
    /**
     * 积分余额
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 账户状态
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Integer createdAt;
}
