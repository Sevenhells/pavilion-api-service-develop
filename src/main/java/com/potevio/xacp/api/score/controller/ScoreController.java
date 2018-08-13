package com.potevio.xacp.api.score.controller;
/**
 * @program: backend
 * @description: 用户积分服务api
 * @author: Mr.lv
 * @create: 2018-08-06 10:54
 **/

import com.alibaba.fastjson.JSON;
import com.potevio.xacp.api.common.LocalErrorCode;
import com.potevio.xacp.api.common.utils.CreateOrder;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import com.potevio.xacp.api.score.model.PointCount;
import com.potevio.xacp.api.score.model.PointGoods;
import com.potevio.xacp.api.score.model.PointOrder;
import com.potevio.xacp.api.score.reponse.*;
import com.potevio.xacp.api.score.request.Exchange;
import com.potevio.xacp.api.score.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/score")
@Api(value = "ScoreController", description = "积分接口")
public class ScoreController {
    private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private PointGoodsExchange pointGoodsExchange;
    @Autowired
    private PointOrder pointOrder;

    /**
     * @Description: 获取积分商品
     * @Param: [page, pageSize]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/7
     */
    @ApiOperation(value = "获取积分商品", httpMethod = "GET")
    @GetMapping(value = "goods")
    public String index(@RequestBody @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        int firstIndex = (page - 1) * pageSize;
        try {
            List<MyPointGoods> myPointGoodsList = scoreService.selectAll(firstIndex, pageSize);
            return FastJsonUtils.resultSuccess(200, "成功", myPointGoodsList);
        } catch (Exception e) {
            logger.error("ScoreController : goods" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultSuccess(LocalErrorCode.COMMON_EXCEPTION, "获取失败", null);
        }
    }

    /**
     * @Description: 获取我的积分
     * @Param: []
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/7
     */
    @ApiOperation(value = "获取我的积分", httpMethod = "GET")
    @GetMapping(value = "my-score")
    public String getMyScore(HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            MyPointCount myPointCount = scoreService.findOne(userId);
            return FastJsonUtils.resultSuccess(200, "成功", myPointCount);

        } catch (Exception e) {
            logger.error("ScoreController : my-score" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultSuccess(LocalErrorCode.COMMON_EXCEPTION, "获取失败", null);
        }
    }

    /**
     * @Description: 获取积分商品详情
     * @Param: [id]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/8
     */
    @ApiOperation(value = "获取积分商品详情", httpMethod = "GET")
    @GetMapping(value = "goods/{id}")
    public String getScoreProDesc(@PathVariable("id") String id) {
        try {
            PointGoodsDesc pointGoodsDesc = scoreService.findOneByGoodIdDesc(id);
            Object image2 = JSON.parse((String) pointGoodsDesc.getImages());
            pointGoodsDesc.setImages(image2);
            return FastJsonUtils.resultSuccess(200, "ok", pointGoodsDesc);
        } catch (Exception e) {
            logger.error("ScoreController : goods/{id}" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultSuccess(LocalErrorCode.COMMON_EXCEPTION, "获取失败", null);
        }
    }

    /**
     * @Description: 积分兑换商品
     * @Param: [request]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/8
     */
    @ApiOperation(value = "积分兑换商品", httpMethod = "POST")
    @PostMapping(value = "exchange")
    public String exchangeProduct(@RequestBody Exchange exchange, HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            // 查找对应的积分商品
            PointGoods pointGood = scoreService.findOneByGoodId(exchange.getGoodsId());
            // 得到账户信息
            PointCount pointCount = scoreService.getScoreByUserId(userId);
            int res = pointGood.getAmount().compareTo(pointCount.getBalance());
            if (res == 1) {
                return FastJsonUtils.resultError(LocalErrorCode.SCODE_IS_NOT_ENOUGH, "用户剩余积分不足，不可以兑换该商品", null);
            }
            String timestamp = String.valueOf(new Date().getTime() / 1000);
            int time = Integer.valueOf(timestamp);
            pointOrder.setUserAccountId(userId);
            pointOrder.setName(pointGood.getName());
            pointOrder.setGoodId(exchange.getGoodsId());
            pointOrder.setAmount(pointGood.getAmount());
            pointOrder.setCreatedAt(time);
            pointOrder.setOrderNo(CreateOrder.getOrderNo());
            pointOrder.setCoverImage(pointGood.getCoverImage());
            BigDecimal userScoreUpdate = pointCount.getBalance().subtract(pointGood.getAmount());
            pointCount.setBalance(userScoreUpdate);
            Boolean exRes = scoreService.exchange(pointOrder, pointCount);
            if (exRes) {
                pointGoodsExchange.setId(pointGood.getId());
                pointGoodsExchange.setName(pointGood.getName());
                return FastJsonUtils.resultSuccess(200, "兑换成功", pointGoodsExchange);
            } else {
                return FastJsonUtils.resultError(LocalErrorCode.SCODE_HANDLE_ERROR, "兑换失败", null);
            }
        } catch (Exception e) {
            logger.error("ScoreController : exchange" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "兑换失败", null);
        }
    }

    /**
     * @Description: 获取我的兑换记录
     * @Param: [page, pageSize]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/8
     */
    @ApiOperation(value = "获取我的兑换记录", httpMethod = "GET")
    @GetMapping(value = "my-orders")
    public String getMyExchange(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            int firstIndex = (page - 1) * pageSize;
            List<MyPointOrder> myPointOrders = scoreService.selectAllByUserId(firstIndex, pageSize, userId);
            return FastJsonUtils.resultSuccess(200, "成功", myPointOrders);
        } catch (Exception e) {
            logger.error("ScoreController : my-orders" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "获取兑换记录失败", null);
        }
    }

    /**
     * @Description: 获取兑换订单详情
     * @Param: [id]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/8
     */
    @ApiOperation(value = "获取兑换订单详情", httpMethod = "GET")
    @GetMapping(value = "my-orders/{id}")
    public String getExchangeDesc(@PathVariable("id") Integer id, HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            MyPointOrderDesc myPointOrderDesc = scoreService.getOrderDescById(id, userId);
            return FastJsonUtils.resultSuccess(200, "获取数据成功", myPointOrderDesc);
        } catch (Exception e) {
            logger.error("ScoreController : my-orders/{id}" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "获取兑换订单详情失败", null);
        }
    }
}
