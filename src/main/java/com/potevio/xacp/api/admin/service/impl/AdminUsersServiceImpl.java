package com.potevio.xacp.api.admin.service.impl;

import com.potevio.xacp.api.admin.mappers.AdminScoreMappers;
import com.potevio.xacp.api.admin.mappers.AdminUsersMappers;
import com.potevio.xacp.api.admin.model.AdminPointGoods;
import com.potevio.xacp.api.admin.response.*;
import com.potevio.xacp.api.admin.service.AdminUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: backend
 * @description: 注册用户
 * @author: Mr.lv
 * @create: 2018-08-10 10:34
 **/
@Service
public class AdminUsersServiceImpl implements AdminUsersService {
    @Autowired
    private AdminUsersMappers adminUsersMappers;
    @Autowired
    private AdminScoreMappers adminScoreMappers;
    @Override
    public List<AdminUsers> selectAll(int firstIndex, Integer pageSize) {
        return adminUsersMappers.selectAllUser(firstIndex,pageSize);
    }

    @Override
    public int selectCount() {
        return adminUsersMappers.selectUserCount();
    }

    @Override
    public List<CitizenCard> selectAllCitizenCard(int firstIndex, Integer pageSize) {
        return adminUsersMappers.selectAllCitizenCard(firstIndex,pageSize);
    }

    @Override
    public int selectCountCitizenCard() {
        return adminUsersMappers.selectCountCitizenCard();
    }

    @Override
    public List<Reservation> selectAllReservation(int firstIndex, Integer pageSize) {
        return adminUsersMappers.selectAllReservation(firstIndex,pageSize);
    }

    @Override
    public int selectCountReservation() {
        return adminUsersMappers.selectCountReservation();
    }

    @Override
    public List<Goods> selectAllGoods(int firstIndex, Integer pageSize) {
        return adminUsersMappers.selectAllGoods(firstIndex,pageSize);
    }

    @Override
    public int selectCountGoods() {
        return adminUsersMappers.selectCountGoods();
    }

    @Override
    public Boolean insertGood(AdminPointGoods goods) {
        String timestamp = String.valueOf(new Date().getTime() / 1000);
        int time = Integer.valueOf(timestamp);
        goods.setCreatedAt(time);
        goods.setUpdatedAt(goods.getCreatedAt());
        int insert = adminScoreMappers.insertGoods(goods);
        if (insert == 0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Boolean updateGood(AdminPointGoods goods) {
        // 创建更新时间
        String timestamp = String.valueOf(new Date().getTime() / 1000);
        int time = Integer.valueOf(timestamp);
        goods.setUpdatedAt(time);
        int i = adminScoreMappers.updateGoods(goods);
        if (i == 0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<Orders> selectAllOrders(int firstIndex, Integer pageSize) {
        return adminScoreMappers.selectAllOrders(firstIndex,pageSize);
    }

    @Override
    public int selectCountOrders() {
        return adminScoreMappers.selectCountOrders();
    }
}