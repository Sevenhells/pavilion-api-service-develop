package com.potevio.xacp.api.score.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.score.model.PointGoods;
import com.potevio.xacp.api.score.reponse.MyPointOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Desc 积分商品 mapper
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
public interface PointOrderMapper extends MyMapper<MyPointOrder> {

    List<MyPointOrder> selectAllByUserId(@Param("firstIndex") int firstIndex,@Param("pageSize") Integer pageSize, @Param("userId") String userId);
}
