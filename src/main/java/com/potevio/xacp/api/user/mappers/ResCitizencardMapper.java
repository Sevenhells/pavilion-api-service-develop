package com.potevio.xacp.api.user.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.user.reponse.ResCitizencard;
import org.apache.ibatis.annotations.Select;

public interface ResCitizencardMapper extends MyMapper<ResCitizencard> {

    @Select("select serial_number as citizenCardNumber,theme,created_at from citizen_card_account where user_account_id = #{userId}")
    ResCitizencard getCitizencardByUserId(String userId);
}
