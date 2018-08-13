package com.potevio.xacp.api.user.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.user.reponse.ResCertification;
import org.apache.ibatis.annotations.Select;

public interface ResCertificationMapper extends MyMapper<ResCertification> {

    @Select("select name,id_card_no as idCardNumber,created_at from user_certification where user_account_id = #{userId}")
    ResCertification getCertificationByUserId(String userId);
}
