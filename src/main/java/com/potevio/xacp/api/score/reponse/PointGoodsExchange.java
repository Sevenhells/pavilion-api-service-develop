package com.potevio.xacp.api.score.reponse;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Component
public class PointGoodsExchange {

    private Integer id;

    private String name;

}
