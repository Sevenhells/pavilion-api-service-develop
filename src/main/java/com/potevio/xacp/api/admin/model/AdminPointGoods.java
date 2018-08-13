package com.potevio.xacp.api.admin.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "point_goods")
public class AdminPointGoods{

    @Column(name = "id")
    private Integer id;

    @Column(name = "open_account_id")
    private Integer openAccountId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "images")
    private String images;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "created_at")
    private Integer createdAt;

    @Column(name = "updated_at")
    private Integer updatedAt;
}