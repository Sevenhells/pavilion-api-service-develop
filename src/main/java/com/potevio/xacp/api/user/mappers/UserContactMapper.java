package com.potevio.xacp.api.user.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.user.model.UserContact;
import com.potevio.xacp.api.user.reponse.ResCertification;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserContactMapper extends MyMapper<UserContact> {

    @Select("select name from user_account where id = #{userId}")
    String findUsernameById(String userId);

    @Select("select count(*) from user_contacts where user_account_id = #{userId}")
    int checkContacts(String userId);

    @Select("select * from user_contacts where user_account_id = #{userId} and id_card_number = #{idCardNumber}")
    UserContact checkContactsOne(@Param("userId") String userId,@Param("idCardNumber") String idCardNumber);
}
