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
public class TreeOrderInfo {

    private Integer id;

    private String tradeNum;

    private String name;

    private BigDecimal price;

    private String desc;

    private Integer createdAt;
}