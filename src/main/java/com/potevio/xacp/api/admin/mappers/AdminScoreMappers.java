package com.potevio.xacp.api.admin.mappers;

import com.potevio.xacp.api.admin.model.AdminPointGoods;
import com.potevio.xacp.api.admin.response.Orders;
import com.potevio.xacp.api.common.utils.MyMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminScoreMappers extends MyMapper<AdminPointGoods> {

    @Insert("insert into point_goods values (#{id},#{openAccountId},#{name},#{description},#{content},#{stock},#{amount},#{images},#{coverImage},#{createdAt},#{updatedAt})")
    int insertGoods(AdminPointGoods goods);

    int updateGoods(AdminPointGoods goods);

    List<Orders> selectAllOrders(@Param("firstIndex") int firstIndex, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from point_order")
    int selectCountOrders();
}