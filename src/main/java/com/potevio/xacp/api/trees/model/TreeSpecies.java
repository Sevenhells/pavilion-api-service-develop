package com.potevio.xacp.api.trees.model;

import com.potevio.xacp.api.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Desc 树木种类表实体类
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
@Data
@Table(name = "tree_species")
public class TreeSpecies extends BaseEntity {
    /**
     * 树木名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 树木编号
     */
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * 位置
     */
    @Column(name = "location")
    private String location;


    /**
     * 品质
     */
    @Column(name = "quality")
    private String quality;

    /**
     * 价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 描述
     */
    @Column(name = "desc")
    private String desc;


    /**
     * 树种介绍
     */
    @Column(name = "content")
    private String content;

    /**
     * 树种介绍图片
     */
    @Column(name = "image")
    private String image;

    /**
     * 树种介绍图片2
     */
    @Column(name = "image2")
    private String image2;

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
}
