package com.potevio.xacp.api.reservation.model;

import com.potevio.xacp.api.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @Desc 预约实体类
 * @Version peng.zhang
 * @Date 2018/8/5 下午4:22
 */
@Data
@Table(name = "reservation")
public class Reservation extends BaseEntity {

    /**
     * 用户ID
     */
    @Column(name = "user_account_id")
    private String userAccountId;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 身份证号
     */
    @Column(name = "idcard_number")
    private String idcardNumber;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 参观时间
     */
    @Column(name = "visit_datetime")
    private Integer visitDateTime;

    /**
     * 预约状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 预约码
     */
    @Column(name = "code")
    private String code;

    /**
     * 手环ID
     */
    @Column(name = "band_id")
    private String bandId;

    /**
     * 进入时间
     */
    @Column(name = "enter_datetime")
    private Integer enterDatetime;

    /**
     * 出场时间
     */
    @Column(name = "exit_datetime")
    private Integer exitDatetime;

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

    /**
     * 预约地址
     */
    @Transient
    private String address;

    /**
     * 预约位置
     */
    @Transient
    private String location;

}
