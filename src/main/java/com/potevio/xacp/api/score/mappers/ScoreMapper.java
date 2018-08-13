package com.potevio.xacp.api.score.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.score.model.PointCount;
import com.potevio.xacp.api.score.model.PointGoods;
import com.potevio.xacp.api.score.reponse.MyPointCount;
import com.potevio.xacp.api.score.reponse.MyPointGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Desc 认领树木订单 mapper
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
public interface ScoreMapper extends MyMapper<MyPointGoods> {
    List<MyPointGoods> selectAllGoods(@Param("firstIndex") int firstIndex,@Param("pageSize") Integer pageSize);

    MyPointCount findOne(String userId);

    @Select("select * from point_goods where id = #{goodsId}")
    PointGoods findOneByGoodId(Integer goodsId);

    @Select("select * from point_account where user_account_id = #{userId}")
    PointCount getScoreByUserId(String userId);
}
