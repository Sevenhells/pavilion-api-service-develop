package com.potevio.xacp.api.score.service.impl;

import com.potevio.xacp.api.score.mappers.*;
import com.potevio.xacp.api.score.model.PointCount;
import com.potevio.xacp.api.score.model.PointCountLog;
import com.potevio.xacp.api.score.model.PointGoods;
import com.potevio.xacp.api.score.model.PointOrder;
import com.potevio.xacp.api.score.reponse.*;
import com.potevio.xacp.api.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: backend
 * @description: 积分服务实现
 * @author: Mr.lv
 * @create: 2018-08-07 17:44
 **/
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private ScoreOrderMapper scoreOrderMapper;
    @Autowired
    private PointAccountMapper pointAccountMapper;
    @Autowired
    private PointAccountLogMapper pointAccountLogMapper;
    @Autowired
    private PointGoodsDescMapper pointGoodsDescMapper;
    @Autowired
    private PointOrderMapper pointOrderMapper;
    @Autowired
    private PointOrderDescMapper pointOrderDescMapper;

    @Override
    public List<MyPointGoods> selectAll(int firstIndex, Integer pageSize) {
        return scoreMapper.selectAllGoods(firstIndex, pageSize);
    }

    @Override
    public MyPointCount findOne(String userId) {
        return scoreMapper.findOne(userId);
    }

    @Override
    public PointGoods findOneByGoodId(Integer goodsId) {
        return scoreMapper.findOneByGoodId(goodsId);
    }

    @Override
    public PointCount getScoreByUserId(String userId) {
        return scoreMapper.getScoreByUserId(userId);
    }

    // 兑换商品并且扣除积分
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean exchange(PointOrder pointOrder, PointCount pointCount) {
        // 新增订单
        try {
            String timestamp = String.valueOf(new Date().getTime() / 1000);
            int time = Integer.valueOf(timestamp);
            int insert = scoreOrderMapper.insert(pointOrder);
            int i = pointAccountMapper.updatePointAccount(pointCount);
            PointCountLog pointCountLog = new PointCountLog();
            pointCountLog.setPointAccountId(pointCount.getId());
            pointCountLog.setAmount(pointOrder.getAmount());
            pointCountLog.setType(20);
            pointCountLog.setCreatedAt(time);
            pointAccountLogMapper.insert(pointCountLog);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public PointGoodsDesc findOneByGoodIdDesc(String id) {
        return pointGoodsDescMapper.findOneByGoodIdDesc(id);
    }

    @Override
    public List<MyPointOrder> selectAllByUserId(int firstIndex, Integer pageSize, String userId) {
        return pointOrderMapper.selectAllByUserId(firstIndex, pageSize, userId);
    }

    @Override
    public MyPointOrderDesc getOrderDescById(Integer id, String userId) {
        return pointOrderDescMapper.getOrderDescById(id, userId);
    }
}