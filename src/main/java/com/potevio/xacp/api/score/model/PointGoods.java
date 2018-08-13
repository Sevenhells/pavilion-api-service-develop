package com.potevio.xacp.api.score.model;

import com.potevio.xacp.api.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "point_goods")
public class PointGoods extends BaseEntity {

    /*
    * 开放平台账户id
    * */
    @Column(name = "open_account_id")
    private Integer openAccountId;
    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 商品描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 商品内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 所需积分数量
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 商品图片
     */
    @Column(name = "images")
    private Object images;

    /**
     * 封面图片
     */
    @Column(name = "cover_image")
    private String coverImage;

}
