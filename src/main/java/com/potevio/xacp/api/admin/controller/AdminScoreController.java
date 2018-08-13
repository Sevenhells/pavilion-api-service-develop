package com.potevio.xacp.api.admin.controller;
/**
 * @program: backend
 * @description: 后台管理api
 * @author: Mr.lv
 * @create: 2018-08-06 10:54
 **/

import com.potevio.xacp.api.admin.model.AdminPointGoods;
import com.potevio.xacp.api.admin.response.Goods;
import com.potevio.xacp.api.admin.response.Orders;
import com.potevio.xacp.api.admin.service.AdminUsersService;
import com.potevio.xacp.api.common.LocalErrorCode;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/admin/score")
@Api(value = "AdminController", description = "后台积分服务管理")
public class AdminScoreController {
    private static final Logger logger = LoggerFactory.getLogger(AdminScoreController.class);
    @Autowired
    private AdminUsersService adminUsersService;
    /**
    * @Description: 积分商品列表
    * @Param: [page, pageSize]
    * @return: java.lang.String
    * @Author: Mr.Lv
    * @Date: 2018/8/10
    */
    @ApiOperation(value = "积分商品列表", httpMethod = "GET")
    @GetMapping(value = "goods")
    public String goods(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        try {
            int firstIndex = (page - 1) * pageSize;
            List<Goods> goods = adminUsersService.selectAllGoods(firstIndex, pageSize);
            // 查询所有的数量
            int count = adminUsersService.selectCountGoods();
            Map<String, Object> map = new HashMap<>();
            map.put("data", goods);
            map.put("count", count);
            return FastJsonUtils.resultSuccess(200, "数据获取成功", map);
        } catch (Exception e) {
            logger.error("AdminController : score/goods" + e.getMessage());
            e.printStackTrace();
        }
        return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
    }

    /** 
    * @Description: 增加积分商品
    * @Param: [goods] 
    * @return: java.lang.String 
    * @Author: Mr.Lv 
    * @Date: 2018/8/10 
    */ 
    @ApiOperation(value = "增加积分商品", httpMethod = "POST")
    @PostMapping(value = "goods")
    public String save(@RequestBody AdminPointGoods goods) {
        try {
            Boolean res = adminUsersService.insertGood(goods);
            if (res){
                return FastJsonUtils.resultSuccess(200, "商品添加成功", goods);
            }else {
                return FastJsonUtils.resultError(400, "商品添加失败", null);
            }
        } catch (Exception e) {
            logger.error("AdminController : score/goods" + e.getMessage());
            e.printStackTrace();
        }
        return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-商品新增失败", null);
    }

    /** 
    * @Description: 修改积分商品 
    * @Param: [goods] 
    * @return: java.lang.String 
    * @Author: Mr.Lv 
    * @Date: 2018/8/11 
    */ 
    @ApiOperation(value = "修改积分商品", httpMethod = "PUT")
    @PutMapping(value = "goods")
    public String updateGood(@RequestBody AdminPointGoods goods) {
        try {
            Boolean res = adminUsersService.updateGood(goods);
            if (res){
                return FastJsonUtils.resultSuccess(200, "商品更新成功", goods);
            }else {
                return FastJsonUtils.resultError(400, "商品更新失败", null);
            }
        } catch (Exception e) {
            logger.error("AdminController : score/goods" + e.getMessage());
            e.printStackTrace();
        }
        return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-商品更新失败", null);
    }

    /**
     * @Description: 积分订单列表
     * @Param: [page, pageSize]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/10
     */
    @ApiOperation(value = "积分订单列表", httpMethod = "GET")
    @GetMapping(value = "orders")
    public String point(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        try {
            int firstIndex = (page - 1) * pageSize;
            List<Orders> orders = adminUsersService.selectAllOrders(firstIndex, pageSize);
            // 查询所有的数量
            int count = adminUsersService.selectCountOrders();
            Map<String, Object> map = new HashMap<>();
            map.put("data", orders);
            map.put("count", count);
            return FastJsonUtils.resultSuccess(200, "数据获取成功", map);
        } catch (Exception e) {
            logger.error("AdminController : orders" + e.getMessage());
            e.printStackTrace();
        }
        return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常-数据获取失败", null);
    }
}
