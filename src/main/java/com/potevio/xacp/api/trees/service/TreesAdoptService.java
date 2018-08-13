package com.potevio.xacp.api.trees.service;

import com.potevio.xacp.api.trees.model.TreeAdoptOrder;
import com.potevio.xacp.api.trees.reponse.MyTreeOrder;
import com.potevio.xacp.api.trees.reponse.TreeOrderInfo;

/**
 * @Desc 树木结缘订单业务
 * @Version Mr.lv
 * @Date 2018-08-06 11:54
 */
public interface TreesAdoptService {

    boolean createdOrder(TreeAdoptOrder treeAdoptOrder);

    TreeAdoptOrder findOneByOrderId(Integer orderId);

    TreeOrderInfo findOneById(Integer orderId);
}
