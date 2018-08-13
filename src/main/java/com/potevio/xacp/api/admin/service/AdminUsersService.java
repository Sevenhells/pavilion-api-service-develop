package com.potevio.xacp.api.admin.service;

import com.potevio.xacp.api.admin.model.AdminPointGoods;
import com.potevio.xacp.api.admin.response.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminUsersService {

    List<AdminUsers> selectAll(int firstIndex, Integer pageSize);

    int selectCount();

    List<CitizenCard> selectAllCitizenCard(int firstIndex, Integer pageSize);

    int selectCountCitizenCard();

    List<Reservation> selectAllReservation(int firstIndex, Integer pageSize);

    int selectCountReservation();

    List<Goods> selectAllGoods(int firstIndex, Integer pageSize);

    int selectCountGoods();

    Boolean insertGood(AdminPointGoods goods);

    Boolean updateGood(AdminPointGoods goods);

    List<Orders> selectAllOrders(int firstIndex, Integer pageSize);

    int selectCountOrders();
}
