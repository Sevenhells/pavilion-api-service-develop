package com.potevio.xacp.api.trees.service.impl;

import com.potevio.xacp.api.common.base.BaseServiceImpl;
import com.potevio.xacp.api.trees.mappers.TreesAdoptMapper;
import com.potevio.xacp.api.trees.model.TreeAdoptOrder;
import com.potevio.xacp.api.trees.reponse.TreeOrderInfo;
import com.potevio.xacp.api.trees.service.TreesAdoptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Desc 树木结缘订单实现类
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
@Service
public class TreeAdoptServiceImpl extends BaseServiceImpl<TreeAdoptOrder> implements TreesAdoptService{

    @Autowired
    private TreesAdoptMapper treesAdoptMapper;
    @Override
    public Mapper<TreeAdoptOrder> getMapper() {
        return treesAdoptMapper;
    }

    @Override
    public boolean createdOrder(TreeAdoptOrder treeAdoptOrder) {
        int row = treesAdoptMapper.insert(treeAdoptOrder);
        if (row == 0){
            return  false;
        }else {
            return true;
        }
    }

    @Override
    public TreeAdoptOrder findOneByOrderId(Integer orderId) {
        return treesAdoptMapper.findOneByOrderId(orderId);
    }

    @Override
    public TreeOrderInfo findOneById(Integer orderId) {
        return treesAdoptMapper.findOneById(orderId);
    }
}
