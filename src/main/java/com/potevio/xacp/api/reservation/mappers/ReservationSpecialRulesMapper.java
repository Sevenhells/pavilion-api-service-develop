package com.potevio.xacp.api.reservation.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.reservation.model.ReservationGeneralRules;
import com.potevio.xacp.api.reservation.model.ReservationSpecialRules;

import java.util.List;

public interface ReservationSpecialRulesMapper extends MyMapper<ReservationGeneralRules> {

    List<ReservationSpecialRules> selectSpecia();
}
