package com.potevio.xacp.api.pay.controller;

import com.potevio.xacp.api.pay.model.AsynNotifyResponse;
import com.potevio.xacp.api.pay.service.JDPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("pay/notify")
@Api(value = "PayNotifyController", description = "支付异步回调通知接口")
public class PayNotifyController {
    private static final Logger logger = LoggerFactory.getLogger(PayNotifyController.class);

    @Autowired
    private JDPayService jdPayService;

    @ApiOperation(value = "京东支付回调接口", httpMethod = "POST")
    @PostMapping(value = "jd")
    public String jdPayNotify(HttpServletRequest httpServletRequest) {
        AsynNotifyResponse asynNotifyResponse = jdPayService.asynNotify(httpServletRequest);
        if (asynNotifyResponse != null) {
            String tradeNum = asynNotifyResponse.getTradeNum(); //商户订单号
            String status = asynNotifyResponse.getStatus(); //状态 0-处理中 1-成功 2-失败
            // TODO 吕寒冰 先查询是否有此订单
            Boolean isHas = jdPayService.checkTradeNum(tradeNum);
            if (!isHas || Integer.valueOf(status) != 1) {
                return "";
            }
            // TODO 吕寒冰 判断是否成功，失败不做处理，成功后改变用户订单状态
            try {
                // 根据商户订单号修改支付状态
                jdPayService.updateStatus(tradeNum);
                return "";
            } catch (Exception e) {
                logger.error("ScoreController : jd" + e.getMessage());
                e.printStackTrace();
            }
        }
        return "";
    }
}
