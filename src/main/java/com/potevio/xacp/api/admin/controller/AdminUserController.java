package com.potevio.xacp.api.admin.controller;
/**
 * @program: backend
 * @description: 用户账户管理api
 * @author: Mr.lv
 * @create: 2018-08-06 10:54
 **/

import com.potevio.xacp.api.admin.response.*;
import com.potevio.xacp.api.admin.service.AdminUsersService;
import com.potevio.xacp.api.common.LocalErrorCode;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/admin")
@Api(value = "AdminController", description = "用户账户管理")
public class AdminUserController {
    private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private AdminUsersService adminUsersService;

    /**
     * @Description: 用户账户列表
     * @Param: [page, pageSize]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/7
     */
    @ApiOperation(value = "用户账户管理", httpMethod = "GET")
    @GetMapping(value = "users")
    public String index(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        try {
            int firstIndex = (page - 1) * pageSize;
            List<AdminUsers> adminUsers = adminUsersService.selectAll(firstIndex, pageSize);
            // 查询所有的数量
            int count = adminUsersService.selectCount();
            Map<String, Object> map = new HashMap<>();
            map.put("data", adminUsers);
            map.put("count", count);
            return FastJsonUtils.resultSuccess(200, "数据获取成功", map);
        } catch (Exception e) {
            logger.error("AdminController : " + e.getMessage());
            e.printStackTrace();
        }
        return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
    }

    /**
     * @Description: 市民卡领取记录
     * @Param: [page, pageSize]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/10
     */
    @ApiOperation(value = "市民卡领取记录", httpMethod = "GET")
    @GetMapping(value = "citizencard")
    public String citizencard(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        try {
            //
            int firstIndex = (page - 1) * pageSize;
            List<CitizenCard> citizenCards = adminUsersService.selectAllCitizenCard(firstIndex, pageSize);
            // 查询所有的数量
            int count = adminUsersService.selectCountCitizenCard();
            Map<String, Object> map = new HashMap<>();
            map.put("data", citizenCards);
            map.put("count", count);
            return FastJsonUtils.resultSuccess(null, "数据获取成功", map);
        } catch (Exception e) {
            logger.error("AdminController : citizencard" + e.getMessage());
            e.printStackTrace();
        }
        return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
    }
}
