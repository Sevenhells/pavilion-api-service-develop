package com.potevio.xacp.api.score.reponse;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MyPointGoods {

    private Integer id;

    private String name;

    private String coverImage;

    private BigDecimal amount;

}
