package com.potevio.xacp.api.user.service.impl;

import com.potevio.xacp.api.user.mappers.*;
import com.potevio.xacp.api.user.model.UserContact;
import com.potevio.xacp.api.user.reponse.*;
import com.potevio.xacp.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: backend
 * @description: 积分服务实现
 * @author: Mr.lv
 * @create: 2018-08-07 17:44
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ResCitizencardMapper resCitizencardMapper;
    @Autowired
    private ResCertificationMapper resCertificationMapper;
    @Autowired
    private ResInfoMapper resInfoMapper;
    @Autowired
    private ResContactsMapper resContactsMapper;
    @Autowired
    private ResSaveContactsMapper resSaveContactsMapper;
    @Autowired
    private UserContactMapper userContactMapper;

    @Override
    public ResCitizencard getCitizencardByUserId(String userId) {
        return resCitizencardMapper.getCitizencardByUserId(userId);
    }

    @Override
    public ResCertification getCertificationByUserId(String userId) {
        return resCertificationMapper.getCertificationByUserId(userId);
    }

    @Override
    public ResInfo getInfoByUserId(String userId) {
        return resInfoMapper.getInfoByUserId(userId);
    }

    @Override
    public List<ResContacts> getContacts(String userId) {
        return resContactsMapper.getContacts(userId);
    }

    @Override
    public List<ResContacts> getContactsInfo(String userId, String id) {
        return resContactsMapper.getContactsInfo(userId, id);
    }

    @Override
    public Boolean saveContactsId(ResSaveContacts resContacts, String userId) {
        String timestamp = String.valueOf(new Date().getTime() / 1000);
        int time = Integer.valueOf(timestamp);
        resContacts.setUserAccountId(userId);
        resContacts.setCreatedAt(time);
        int insert = resSaveContactsMapper.insert(resContacts);
        if (insert == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean updateContactsId(UserContact userContact, String userId) {
        userContact.setUserAccountId(userId);
        int i = userContactMapper.updateByPrimaryKeySelective(userContact);
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String findUsernameById(String userId) {
        return userContactMapper.findUsernameById(userId);
    }

    @Override
    public int checkContacts(String userId) {
        return userContactMapper.checkContacts(userId);
    }

    @Override
    public UserContact checkContactsOne(String userId, String idCardNumber) {
        UserContact userContact =userContactMapper.checkContactsOne(userId,idCardNumber);
        System.out.println(userContact);
        return userContact;
    }
}