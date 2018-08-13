package com.potevio.xacp.api.reservation.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.reservation.model.ReservationGeneralRules;

import java.util.List;

public interface ReservationGeneralMapper extends MyMapper<ReservationGeneralRules> {

    List<ReservationGeneralRules> selectCloseDates();

    List<ReservationGeneralRules> selectOpenDates();
}
