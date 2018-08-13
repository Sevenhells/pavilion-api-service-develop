package com.potevio.xacp.api.user.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.user.reponse.ResContacts;
import com.potevio.xacp.api.user.reponse.ResInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResContactsMapper extends MyMapper<ResContacts> {

    @Select("select id,name,id_card_number,mobile from user_contacts where user_account_id = #{userId}")
    List<ResContacts> getContacts(String userId);

    @Select("select id,name,id_card_number,mobile from user_contacts where user_account_id = #{userId} and id = #{id}")
    List<ResContacts> getContactsInfo(@Param("userId") String userId,@Param("id") String id);
}
