package com.potevio.xacp.api.score.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.score.model.PointGoods;
import com.potevio.xacp.api.score.model.PointOrder;
import com.potevio.xacp.api.score.reponse.MyPointCount;
import com.potevio.xacp.api.score.reponse.MyPointGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Desc 认领树木订单 mapper
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
public interface ScoreOrderMapper extends MyMapper<PointOrder> {

}
