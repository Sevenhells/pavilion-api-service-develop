package com.potevio.xacp.api.trees.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.trees.model.TreeAdoptOrder;
import com.potevio.xacp.api.trees.reponse.TreeOrderInfo;
import org.apache.ibatis.annotations.Select;

/**
 * @Desc 认领树木订单 mapper
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
public interface TreesAdoptMapper extends MyMapper<TreeAdoptOrder> {
    @Select("select * from tree_adopt_order where id = #{orderId}")
    TreeAdoptOrder findOneByOrderId(Integer orderId);

    TreeOrderInfo findOneById(Integer orderId);
}
