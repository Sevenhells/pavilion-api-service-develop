package com.potevio.xacp.api.reservation.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.reservation.model.Reservation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Desc 预约服务 mapper
 * @Version peng.zhang
 * @Date 2018/8/5 下午5:05
 */
public interface ReservationMapper extends MyMapper<Reservation> {

    public List<Reservation> checkCode(@Param("params_map") Map<String, Object> paramsMap);

}
