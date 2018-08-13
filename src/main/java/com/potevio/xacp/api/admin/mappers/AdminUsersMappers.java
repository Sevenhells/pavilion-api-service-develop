package com.potevio.xacp.api.admin.mappers;

import com.potevio.xacp.api.admin.response.AdminUsers;
import com.potevio.xacp.api.admin.response.CitizenCard;
import com.potevio.xacp.api.admin.response.Goods;
import com.potevio.xacp.api.admin.response.Reservation;
import com.potevio.xacp.api.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminUsersMappers extends MyMapper<AdminUsers> {
    List<AdminUsers> selectAllUser(@Param("firstIndex") int firstIndex,@Param("pageSize") Integer pageSize);

    @Select("select count(*) from user_account")
    int selectUserCount();

    List<CitizenCard> selectAllCitizenCard(@Param("firstIndex") int firstIndex,@Param("pageSize") Integer pageSize);

    @Select("select count(*) from citizen_card_get_log")
    int selectCountCitizenCard();

    @Select("select * from reservation limit #{firstIndex},#{pageSize}")
    List<Reservation> selectAllReservation(@Param("firstIndex") int firstIndex,@Param("pageSize") Integer pageSize);

    @Select("select count(*) from reservation")
    int selectCountReservation();

    @Select("select * from point_goods")
    List<Goods> selectAllGoods(@Param("firstIndex") int firstIndex,@Param("pageSize") Integer pageSize);

    @Select("select count(*) from point_goods")
    int selectCountGoods();
}