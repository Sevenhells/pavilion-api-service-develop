package com.potevio.xacp.api.user.model;

import com.potevio.xacp.api.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "user_contacts")
public class UserContact extends BaseEntity {

    /**
    * 用户账户id
    * */
    @Column(name = "user_account_id")
    private String userAccountId;
    /**
     * 积分余额
     */
    @Column(name = "name")
    private String name;

    /**
     * 账户状态
     */
    @Column(name = "id_card_number")
    private String idCardNumver;

    /**
     * 用户手机
     */
    @Column(name = "mobile")
    private String mobile;

}
