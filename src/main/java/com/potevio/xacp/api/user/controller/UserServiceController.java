package com.potevio.xacp.api.user.controller;
/**
 * @program: backend
 * @description: 用户基础信息服务api
 * @author: Mr.lv
 * @create: 2018-08-06 10:54
 **/

import com.potevio.xacp.api.common.LocalErrorCode;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import com.potevio.xacp.api.user.model.UserContact;
import com.potevio.xacp.api.user.reponse.*;
import com.potevio.xacp.api.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@Api(value = "UserServiceController", description = "用户基础信息接口")
public class UserServiceController {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ResponseContacts responseContacts1;
    @Autowired
    private ResContacts resContacts;

    /**
     * @Description: 获取市民卡信息
     * @Param: [page, pageSize]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/7
     */
    @ApiOperation(value = "获取市民卡信息", httpMethod = "GET")
    @GetMapping(value = "citizencard")
    public String index(HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            ResCitizencard resCitizencard = userService.getCitizencardByUserId(userId);
            return FastJsonUtils.resultSuccess(200, "数据获取成功", resCitizencard);
        } catch (Exception e) {
            logger.error("UserServiceController : citizencard" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
        }
    }

    /**
     * @Description: 获取实名认证信息
     * @Param: []
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/8
     */
    @ApiOperation(value = "获取实名认证信息", httpMethod = "GET")
    @GetMapping(value = "certification")
    public String getCertification(HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            ResCertification resCertification = userService.getCertificationByUserId(userId);
            return FastJsonUtils.resultSuccess(200, "数据获取成功", resCertification);
        } catch (Exception e) {
            logger.error("UserServiceController : citizencard" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
        }
    }

    /**
     * @Description: 获取用户基本信息
     * @Param: []
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/8
     */
    @ApiOperation(value = "获取用户基本信息", httpMethod = "GET")
    @GetMapping(value = "info")
    public String getInfo(HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            ResInfo resInfo = userService.getInfoByUserId(userId);
            return FastJsonUtils.resultSuccess(200, "查询成功", resInfo);
        } catch (Exception e) {
            logger.error("UserServiceController : info" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
        }
    }

    /**
     * @Description: 获取常用联系人列表
     * @Param: []
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/8
     */
    @ApiOperation(value = "获取常用联系人列表", httpMethod = "GET")
    @GetMapping(value = "contacts")
    public String getContacts(HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            List<ResContacts> resContacts = userService.getContacts(userId);
            return FastJsonUtils.resultSuccess(200, "查询成功", resContacts);
        } catch (Exception e) {
            logger.error("UserServiceController : contacts-GET" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
        }
    }

    /**
     * @Description: 获取常用联系人信息
     * @Param: [id]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/8
     */
    @ApiOperation(value = "获取常用联系人信息", httpMethod = "GET")
    @GetMapping(value = "contacts/{id}")
    public String getContactsId(@RequestBody @PathVariable("id") String id, HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            List<ResContacts> resContacts = userService.getContactsInfo(userId, id);
            return FastJsonUtils.resultSuccess(200, "查询成功", resContacts);
        } catch (Exception e) {
            logger.error("UserServiceController : contacts/{id}" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
        }
    }

    /**
     * @Description: 添加/保存常用联系人
     * @Param: [resContacts]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/9
     */
    @ApiOperation(value = "添加/保存常用联系人", httpMethod = "POST")
    @PostMapping(value = "contacts")
    public String saveContactsId(@RequestBody ResSaveContacts resContacts, HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            // 数据校验，判断数量，查重
            // 查询是否超过15个人用户了
            int count = userService.checkContacts(userId);
            UserContact userContact = userService.checkContactsOne(userId,resContacts.getIdCardNumber());
            if (count > 15){
                return FastJsonUtils.resultError(LocalErrorCode.USER_FREQUENT_CONTACTS_OVER, "保存失败,常用联系人不能超过15人", null);
            }else if (userContact != null){
                return FastJsonUtils.resultError(LocalErrorCode.USER_FREQUENT_CONTACTS_REPEAT, "保存失败,该联系人已经保存过了", null);
            }
            Boolean res = userService.saveContactsId(resContacts, userId);
            if (res) {
                responseContacts1.setName(resContacts.getName());
                responseContacts1.setIdCardNumber(resContacts.getIdCardNumber());
                responseContacts1.setMobile(resContacts.getMobile());
                return FastJsonUtils.resultSuccess(200, "保存成功", responseContacts1);
            } else {
                return FastJsonUtils.resultError(LocalErrorCode.USER_HANDLE_ERROR, "保存失败", null);
            }
        } catch (Exception e) {
            logger.error("UserServiceController : contacts-POST" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-保存失败", null);
        }

    }

    /**
     * @Description: 修改常用联系人
     * @Param: [userContact]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/9
     */
    @ApiOperation(value = "修改常用联系人", httpMethod = "PUT")
    @PutMapping(value = "contacts", produces = {"application/json;charset=UTF-8"})
    public String updateContactsId(@RequestBody UserContact userContact, HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            Boolean res = userService.updateContactsId(userContact, userId);
            if (res) {
                resContacts.setId(userContact.getId());
                resContacts.setName(userContact.getName());
                resContacts.setIdCardNumber(userContact.getIdCardNumver());
                resContacts.setMobile(userContact.getMobile());
                return FastJsonUtils.resultSuccess(200, "修改成功", resContacts);
            } else {
                return FastJsonUtils.resultError(LocalErrorCode.USER_HANDLE_ERROR, "修改失败", null);
            }
        } catch (Exception e) {
            logger.error("UserServiceController : contacts-PUT" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-修改失败", null);
        }

    }
}
