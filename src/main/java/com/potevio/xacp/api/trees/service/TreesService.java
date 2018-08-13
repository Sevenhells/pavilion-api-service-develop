package com.potevio.xacp.api.trees.service;

import com.potevio.xacp.api.trees.reponse.MyTreeOrder;
import com.potevio.xacp.api.trees.reponse.TreeSpecies;

import java.util.List;

/**
 * @Desc 树木结缘业务
 * @Version Mr.lv
 * @Date 2018-08-06 11:54
 */
public interface TreesService{

    /**
     * 查询用户认领树木订单表
     * @return 认领记录
     * @throws Exception
     */
    public List<MyTreeOrder> selectAllOrder(String userId,Integer firstIndex,Integer pageSize) throws Exception;

    /** 
    * @Description: 查询树木信息
    * @Param: [serialNumber] 
    * @return: com.potevio.xacp.api.trees.model.TreeSpecies 
    * @Author: Mr.Lv 
    * @Date: 2018/8/7 
    */ 
    TreeSpecies findOne(String serialNumber);

    List<MyTreeOrder> selectMyTrees(String userId, int firstIndex, Integer pageSize);
}
