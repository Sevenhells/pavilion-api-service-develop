package com.potevio.xacp.api.reservation.service.impl;

import com.potevio.xacp.api.common.base.BaseServiceImpl;
import com.potevio.xacp.api.common.utils.RedisUtil;
import com.potevio.xacp.api.reservation.mappers.ReservationGeneralMapper;
import com.potevio.xacp.api.reservation.mappers.ReservationSpecialRulesMapper;
import com.potevio.xacp.api.reservation.model.ReservationGeneralRules;
import com.potevio.xacp.api.reservation.model.ReservationSpecialRules;
import com.potevio.xacp.api.reservation.service.ReservationRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationRulesServiceImpl extends BaseServiceImpl<ReservationGeneralRules> implements ReservationRulesService {
    @Autowired
    ReservationSpecialRulesMapper reservationRulesMapper;

    @Autowired
    ReservationGeneralMapper reservationGeneralMapper;

    @Override
    public Mapper<ReservationGeneralRules> getMapper() {
        return reservationRulesMapper;
    }

    @Override
    public List<Date> getNotReservateDates() {

        List<Date> list=new ArrayList<>();
        //查询所有的特殊规则日期
        List<ReservationSpecialRules> specialList=reservationRulesMapper.selectSpecia();
        //将开馆，闭馆两种分别存于两个新的列表
        List<ReservationSpecialRules> openSpecialList=new ArrayList<>();
        List<ReservationSpecialRules> closeSpecialList=new ArrayList<>();
        for (ReservationSpecialRules reservationSpecialRules:specialList){
            if (reservationSpecialRules.getIsOpen()==1){
                openSpecialList.add(reservationSpecialRules);
            }else {
                closeSpecialList.add(reservationSpecialRules);
            }
        }
        //查询通用闭馆日期
        List<ReservationGeneralRules> closeGeneralList=reservationGeneralMapper.selectCloseDates();

        //查询通用开馆日期
        List<ReservationGeneralRules> openGeneralList=reservationGeneralMapper.selectOpenDates();

        //当通用闭馆日期等于特殊开馆时，将这条记录从closeGeneralList中移除
        for(ReservationGeneralRules reservationGeneralRules:closeGeneralList){
            for (ReservationSpecialRules reservationSpecialRules:openSpecialList){
                //当特殊开馆中预约人数超出限制时，即该日不可预约
                if (reservationSpecialRules.getLimitVistor()<=5000){
                    list.add(reservationSpecialRules.getRuleDate());
                }
                if(reservationGeneralRules.getRuleDate()==reservationSpecialRules.getRuleDate()){
                    closeGeneralList.remove(reservationGeneralRules);
                }
            }
        }

        for(ReservationSpecialRules reservationSpecialRules:closeSpecialList){
           list.add(reservationSpecialRules.getRuleDate());
        }
        for (ReservationGeneralRules reservationGeneralRules:closeGeneralList){
            list.add(reservationGeneralRules.getRuleDate());
        }
        //判断通用开馆日期中预约人数已达限制人数
        for (ReservationGeneralRules reservationGeneralRules:openGeneralList){
            if (reservationGeneralRules.getLimitVisitors()>=5000){
                //预约人数已超上限
                list.add(reservationGeneralRules.getRuleDate());
            }
        }
        //将结果放到缓存中
        RedisUtil.set("unreachableDate",list);
        return list;
    }
}
