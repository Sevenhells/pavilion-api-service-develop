package com.potevio.xacp.api.trees.reponse;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: backend
 * @description: 返回json对象
 * @author: Mr.lv
 * @create: 2018-08-06 13:57
 **/
@Data
public class MyTreeOrder implements Serializable {

    private Integer id;

    private String tradeNum;

    private String serialNumber;

    private String name;

    private String location;

    private String quality;

    private BigDecimal price;

    private String desc;

    private String content;

    private String image;

    private String image2;

    private Integer payStatus;
}