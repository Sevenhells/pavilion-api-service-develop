package com.potevio.xacp.api.user.service;

import com.potevio.xacp.api.score.model.PointCount;
import com.potevio.xacp.api.score.model.PointGoods;
import com.potevio.xacp.api.score.model.PointOrder;
import com.potevio.xacp.api.score.reponse.*;
import com.potevio.xacp.api.user.model.UserContact;
import com.potevio.xacp.api.user.reponse.*;

import java.util.List;

/**
 * @Desc 用户基础业务
 * @Version Mr.lv
 * @Date 2018-08-06 11:54
 */
public interface UserService {
    ResCitizencard getCitizencardByUserId(String userId);

    ResCertification getCertificationByUserId(String userId);

    ResInfo getInfoByUserId(String userId);

    List<ResContacts> getContacts(String userId);

    List<ResContacts> getContactsInfo(String userId, String id);

    Boolean saveContactsId(ResSaveContacts resContacts, String userId);

    Boolean updateContactsId(UserContact userContact, String userId);

    String findUsernameById(String userId);

    int checkContacts(String userId);

    UserContact checkContactsOne(String userId, String idCardNumber);
}
