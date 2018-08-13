package com.potevio.xacp.api.score.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.score.reponse.MyPointOrder;
import com.potevio.xacp.api.score.reponse.MyPointOrderDesc;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Desc 积分商品 mapper
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
public interface PointOrderDescMapper extends MyMapper<MyPointOrderDesc> {
    @Select("select * from point_order where id = #{id} and user_account_id = #{userId}")
    MyPointOrderDesc getOrderDescById(@Param("id") Integer id,@Param("userId") String userId);
}
