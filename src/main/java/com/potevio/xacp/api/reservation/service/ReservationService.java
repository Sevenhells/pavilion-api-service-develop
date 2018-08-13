package com.potevio.xacp.api.reservation.service;

import com.potevio.xacp.api.reservation.model.Reservation;
import com.potevio.xacp.api.reservation.request.AddReservationBean;
import com.potevio.xacp.api.reservation.request.CheckCodeBean;

import java.util.List;

/**
 * @Desc 预约业务
 * @Version peng.zhang
 * @Date 2018/8/5 下午4:56
 */
public interface ReservationService {

    /**
     * 批量添加预约
     * @return 添加的个数
     * @throws Exception 异常
     */
    List<Reservation> addMultiple(String reservationAccountId, AddReservationBean addReservationBean) throws Exception;

    /**
     * 查询预约列表
     * @return 预约信息
     * @throws Exception 异常
     */
    List<Reservation> selectAll() throws Exception;

    /**
     * 根据uid查询预约列表
     * @param uid 用户ID
     * @return 预约信息
     * @throws Exception 异常
     */
    List<Reservation> selectByUid(String uid) throws Exception;

    /**
     * 验证预约码是否有效
     * @param checkCodeBean 验证预约码信息
     * @return
     */
    String checkCode(CheckCodeBean checkCodeBean) throws Exception;

}
