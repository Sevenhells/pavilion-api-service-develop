package com.potevio.xacp.api.score.service;

import com.potevio.xacp.api.score.model.PointCount;
import com.potevio.xacp.api.score.model.PointGoods;
import com.potevio.xacp.api.score.model.PointOrder;
import com.potevio.xacp.api.score.reponse.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Desc 积分业务
 * @Version Mr.lv
 * @Date 2018-08-06 11:54
 */
public interface ScoreService {

    List<MyPointGoods> selectAll(int firstIndex, Integer pageSize);

    MyPointCount findOne(String userId);

    PointGoods findOneByGoodId(Integer goodsId);

    PointCount getScoreByUserId(String userId);

    Boolean exchange(PointOrder pointOrder, PointCount pointCount);

    PointGoodsDesc findOneByGoodIdDesc(String id);

    List<MyPointOrder> selectAllByUserId(int firstIndex, Integer pageSize,String userId);

    MyPointOrderDesc getOrderDescById(Integer id,String userId);
}
