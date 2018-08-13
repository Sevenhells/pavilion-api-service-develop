package com.potevio.xacp.api.trees.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.trees.reponse.MyTreeOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Desc 树木结缘服务 mapper
 * @Version Mr.Lv
 * @Date 2018-08-06 11:56
 */
public interface TreesMyTreeOrderMapper extends MyMapper<MyTreeOrder> {

    List<MyTreeOrder> selectAllOrder(@Param("userId") String userId, @Param("firstIndex") Integer firstIndex,@Param("pageSize") Integer pageSize);

    List<MyTreeOrder> selectMyTrees(@Param("userId") String userId, @Param("firstIndex") Integer firstIndex,@Param("pageSize") Integer pageSize);
}
