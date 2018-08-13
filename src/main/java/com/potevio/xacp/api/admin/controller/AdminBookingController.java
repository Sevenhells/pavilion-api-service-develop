package com.potevio.xacp.api.admin.controller;
/**
 * @program: backend
 * @description: 用户账户管理api
 * @author: Mr.lv
 * @create: 2018-08-06 10:54
 **/

import com.potevio.xacp.api.admin.response.Reservation;
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
public class AdminBookingController {
    private static final Logger logger = LoggerFactory.getLogger(AdminBookingController.class);

    @Autowired
    private AdminUsersService adminUsersService;

    // TODO 吕寒冰 预约规则管理：通用规则管理、特殊规则管理

    @ApiOperation(value = "预约管理", httpMethod = "GET")
    @GetMapping(value = "booking/setting")
    public String bookingSetting(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
       /* try {
            //
            int firstIndex = (page - 1) * pageSize;
            List<BookingSetting> bookingSettings = adminUsersService.selectAllBooking(firstIndex, pageSize);
            // 查询所有的数量
            int count = adminUsersService.selectCountBooking();
            Map<String, Object> map = new HashMap<>();
            map.put("data", bookingSettings);
            map.put("count", count);
            return FastJsonUtils.resultSuccess(200, "数据获取成功", map);
        } catch (Exception e) {
            logger.error("AdminController : booking/setting" + e.getMessage());
            e.printStackTrace();
        }*/
        return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
    }

    /** 
    * @Description:  预约信息
    * @Param: [page, pageSize] 
    * @return: java.lang.String 
    * @Author: Mr.Lv 
    * @Date: 2018/8/10 
    */ 
    @ApiOperation(value = "预约信息", httpMethod = "GET")
    @GetMapping(value = "booking/orders")
    public String booking(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        try {
            //
            int firstIndex = (page - 1) * pageSize;
            List<Reservation> reservations = adminUsersService.selectAllReservation(firstIndex, pageSize);
            // 查询所有的数量
            int count = adminUsersService.selectCountReservation();
            Map<String, Object> map = new HashMap<>();
            map.put("data", reservations);
            map.put("count", count);
            return FastJsonUtils.resultSuccess(200, "数据获取成功", map);
        } catch (Exception e) {
            logger.error("AdminBookingController : booking/orders" + e.getMessage());
            e.printStackTrace();
        }
        return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
    }
}
