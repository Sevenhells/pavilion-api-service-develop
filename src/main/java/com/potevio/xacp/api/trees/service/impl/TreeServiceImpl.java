package com.potevio.xacp.api.trees.service.impl;

import com.potevio.xacp.api.trees.mappers.TreesAdoptMapper;
import com.potevio.xacp.api.trees.mappers.TreesMyTreeOrderMapper;
import com.potevio.xacp.api.trees.mappers.TreesSpeciesMapper;
import com.potevio.xacp.api.trees.reponse.MyTreeOrder;
import com.potevio.xacp.api.trees.reponse.TreeSpecies;
import com.potevio.xacp.api.trees.service.TreesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desc 树木结缘业务实现类
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
@Service
public class TreeServiceImpl implements TreesService{

    @Autowired
    private TreesMyTreeOrderMapper treesMapper;

    @Autowired
    private TreesSpeciesMapper treesSpeciesMapper;

    @Autowired
    private TreesAdoptMapper treesAdoptMapper;

    @Override
    public List<MyTreeOrder> selectAllOrder(String userId,Integer firstIndex,Integer pageSize) throws Exception {
        return treesMapper.selectAllOrder(userId,firstIndex,pageSize);
    }

    @Override
    public TreeSpecies findOne(String serialNumber) {
        return treesSpeciesMapper.findOne(serialNumber);
    }

    @Override
    public List<MyTreeOrder> selectMyTrees(String userId, int firstIndex, Integer pageSize) {
        return treesMapper.selectMyTrees(userId,firstIndex,pageSize);
    }
}
