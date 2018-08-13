package com.potevio.xacp.api.reservation.service;

import java.sql.Date;
import java.util.List;

public interface ReservationRulesService {
    List<Date> getNotReservateDates();
}
