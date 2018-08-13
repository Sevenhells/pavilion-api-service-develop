package com.potevio.xacp.api.score.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.score.model.PointGoods;
import com.potevio.xacp.api.score.reponse.PointGoodsDesc;
import org.apache.ibatis.annotations.Select;

/**
 * @Desc 积分商品 mapper
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
public interface PointGoodsDescMapper extends MyMapper<PointGoods> {

    @Select("select * from point_goods where id = #{id}")
    PointGoodsDesc findOneByGoodIdDesc(String id);
}
