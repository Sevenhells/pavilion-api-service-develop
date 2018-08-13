package com.potevio.xacp.api.score.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.score.model.PointCount;
import com.potevio.xacp.api.score.model.PointOrder;
import org.apache.ibatis.annotations.Update;

/**
 * @Desc 认领树木订单 mapper
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
public interface PointAccountMapper extends MyMapper<PointCount> {

    @Update("update point_account set balance = #{balance} where id = #{id} and  status = 10")
    int updatePointAccount(PointCount pointCount);
}
