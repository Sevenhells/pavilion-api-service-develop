package com.potevio.xacp.api.pay.mappers;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PayStatusMapper {


    @Update("update tree_adopt_order set pay_status = 10 where trade_num = #{tradeNum}")
    void updateStatus(String tradeNum);

    @Select("select * from tree_adopt_order where trade_num = #{tradeNum}")
    Boolean checkTradeNum(String tradeNum);
}
