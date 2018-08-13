package com.potevio.xacp.api.reservation.service.impl;

import com.potevio.xacp.api.common.LocalErrorCode;
import com.potevio.xacp.api.common.base.BaseServiceImpl;
import com.potevio.xacp.api.common.utils.DateTimeUtils;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import com.potevio.xacp.api.common.utils.RedisUtil;
import com.potevio.xacp.api.common.utils.ServiceUtils;
import com.potevio.xacp.api.reservation.mappers.ReservationMapper;
import com.potevio.xacp.api.reservation.model.Reservation;
import com.potevio.xacp.api.reservation.request.AddReservationBean;
import com.potevio.xacp.api.reservation.request.CheckCodeBean;
import com.potevio.xacp.api.reservation.request.VisitorsBean;
import com.potevio.xacp.api.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @Desc 预约业务实现类
 * @Version peng.zhang
 * @Date 2018/8/5 下午4:56
 */
@Service
public class ReservationServiceImpl extends BaseServiceImpl<Reservation> implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public Mapper<Reservation> getMapper() {
        return reservationMapper;
    }

    @Override
    public List<Reservation> addMultiple(String reservationAccountId, AddReservationBean addReservationBean) throws Exception {
        Integer visitDate = addReservationBean.getVisitDate();
        List<Reservation> reservations = new ArrayList<>();
        // TODO 张志明 判断预约日期合法性
        Reservation reservation;
        for (VisitorsBean visitor : addReservationBean.getVisitors()) {
            // 在MySQL中添加预约记录
            reservation = addReservation(reservationAccountId, visitDate, visitor);
            if (reservation != null) {
                /*
                1. 获取当前日期的开始时间戳
                2. 将预约码写入Redis库缓存（为了解决一天预约码不可重复的问题）
                3. 注意：Redis数据库的规则：
                    - key: reservation + 当天开始时间戳
                    - field: 预约码
                    - value: 暂时保留空字符串
                 */
                Long startTime = DateTimeUtils.getStartTimestamp(new Date());
                RedisUtil.hset("reservation:" + startTime, reservation.getCode(), "");
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    @Override
    public List<Reservation> selectAll() throws Exception {
        List<Reservation> reservations = reservationMapper.selectAll();
        for (Reservation r : reservations) {
            r.setAddress("雄安新区市民服务中心");
            r.setLocation("115.920964,39.057175");
        }
        return reservations;
    }

    @Override
    public List<Reservation> selectByUid(String uid) throws Exception {
        Example example = new Example(Reservation.class);
        example.createCriteria().andEqualTo("userAccountId", uid);
        List<Reservation> reservations = reservationMapper.selectByExample(example);
        for (Reservation r : reservations) {
            r.setAddress("雄安新区市民服务中心");
            r.setLocation("115.920964,39.057175");
        }
        return reservations;
    }

    @Override
    public String checkCode(CheckCodeBean checkCodeBean) throws Exception {
        // 检查该预约码在redis中是否存在
        Long currentDayStart = DateTimeUtils.getStartTimestamp(new Date());
        if (!RedisUtil.hHasKey("reservation:" + currentDayStart, checkCodeBean.getCode())) {
            return FastJsonUtils.resultError(LocalErrorCode.RESERVATION_REIDS_NOT_EXIST, "不存在与信息匹配的预约码", null);
        }
        // 检查数据库是是否存在该预约码
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("code", checkCodeBean.getCode());
        paramsMap.put("band_id", checkCodeBean.getBandId());
        paramsMap.put("start_time", DateTimeUtils.getStartTime(new Date()));
        paramsMap.put("end_time", DateTimeUtils.getEndTime(new Date()));
        paramsMap.put("status", 1);
        List<Reservation> reservations = reservationMapper.checkCode(paramsMap);
        if (reservations.size() > 0) {
            return FastJsonUtils.resultSuccess(null, "预约码验证成功", null);
        } else {
            return FastJsonUtils.resultError(LocalErrorCode.RESERVATION_MYSQL_NOT_EXIST, "不存在与信息匹配的预约码", null);
        }
    }

    private Reservation addReservation(String reservationAccountId, Integer visitDate, VisitorsBean visitor) throws Exception {
        Reservation reservation = new Reservation();
        reservation.setUserAccountId(reservationAccountId);
        reservation.setName(visitor.getName());
        reservation.setIdcardNumber(visitor.getIdcardNumber());
        reservation.setMobile(visitor.getMobile());
        reservation.setVisitDateTime(visitDate);
        reservation.setStatus(1);
        reservation.setCode(getReservationCode());
        reservation.setCreateAt(DateTimeUtils.DateToTimestamp(new Date()));
        reservation.setUpdatedAt(DateTimeUtils.DateToTimestamp(new Date()));
        if (reservationMapper.insertSelective(reservation) > 0) {
            reservation.setAddress("雄安新区市民服务中心");
            reservation.setLocation("115.920964,39.057175");
            return reservation;
        }
        return null;
    }

    private String getReservationCode() throws Exception {
        Long currentDayStart = DateTimeUtils.getStartTimestamp(new Date());
        Set<Object> keys = RedisUtil.hkeys("reservation:" + currentDayStart);
        for (int i = 0; i < 10000; i++) {
            String code = ServiceUtils.createReservationCode();
            if (keys == null || !keys.contains(code)) {
                return code;
            }
        }
        return null;
    }

}
