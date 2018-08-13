package com.potevio.xacp.api.reservation.model;

import com.potevio.xacp.api.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

@Data
@Table(name = "reservation_special_rules")
public class ReservationSpecialRules extends BaseEntity {

    @Column(name = "rule_date")
    private Date ruleDate;

    @Column(name = "is_open")
    private Byte isOpen;

    @Column(name = "limit_vistor")
    private Integer limitVistor;

    @Column(name = "open_time")
    private Time openTime;

    @Column(name = "close_time")
    private Time closeTime;

    @Column(name = "created_at")
    private Integer createdAt;

    @Column(name = "updated_at")
    private Integer updatedAt;
}
