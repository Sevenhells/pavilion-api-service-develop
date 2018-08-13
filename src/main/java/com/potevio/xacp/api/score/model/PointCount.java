package com.potevio.xacp.api.score.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "point_account")
public class PointCount{

    @Column(name = "id")
    private Integer id;
    /**
    * 用户账户id
    * */
    @Column(name = "user_account_id")
    private String userAccountId;
    /**
     * 积分余额
     */
    @Column(name = "balance")
    private BigDecimal balance;

    /**
     * 账户状态
     */
    @Column(name = "status")
    private Integer status;

}
