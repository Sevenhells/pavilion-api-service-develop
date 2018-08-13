package com.potevio.xacp.api.trees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.potevio.xacp.api.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * @Desc 树木领养订单实体类
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
@Entity
@Data
@Table(name = "tree_adopt_order")
public class TreeAdoptOrder extends BaseEntity {

    /**
     * 定单号
     */
    @Column(name = "trade_num")
    private String tradeNum;

    /**
     * 树木序列号
     */
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * 用户ID
     */
    @JsonIgnore
    @Column(name = "user_account_id")
    private String userAccountId;

    /**
     * 领养人
     */
    @JsonIgnore
    @Column(name = "name")
    private String name;

    /**
     * 支付状态
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 应付金额
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Integer createAt;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Integer updatedAt;

    public Integer getCreateAt() {
        return createAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }
}
