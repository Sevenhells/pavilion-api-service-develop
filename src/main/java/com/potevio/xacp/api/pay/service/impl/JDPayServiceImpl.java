package com.potevio.xacp.api.pay.service.impl;

import com.jd.jr.pay.gate.signature.util.JdPayUtil;
import com.potevio.xacp.api.pay.mappers.PayStatusMapper;
import com.potevio.xacp.api.pay.model.AsynNotifyResponse;
import com.potevio.xacp.api.pay.model.BasePayOrderInfo;
import com.potevio.xacp.api.pay.model.CreateOrderResponse;
import com.potevio.xacp.api.pay.service.JDPayService;
import com.potevio.xacp.api.pay.util.CertUtil;
import com.potevio.xacp.api.pay.util.HttpsClientUtil;
import com.potevio.xacp.api.pay.util.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class JDPayServiceImpl implements JDPayService{

    private static final Logger logger = Logger.getLogger(String.valueOf(JDPayServiceImpl.class));

    @Autowired
    private PayStatusMapper payStatusMapper;

    @Override
    public CreateOrderResponse unifiedOrder(BasePayOrderInfo basePayOrderInfo) throws InstantiationException, IllegalAccessException {
        String oriUrl = PropertyUtils.getProperty("wepay.server.uniorder.url");
        String priKey = PropertyUtils.getProperty("wepay.merchant.rsaPrivateKey");
        String desKey = PropertyUtils.getProperty("wepay.merchant.desKey");
        String pubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");

        String cert = CertUtil.getCert();
        // 有证书则证书验证模式、无则配置模式
        if (cert != null && !cert.equals("")) {
            basePayOrderInfo.setCert(cert);
        }
        String tradeXml = JdPayUtil.genReqXml(basePayOrderInfo, priKey, desKey);
        logger.info("request xml:" + tradeXml);
        String resultJsonData = HttpsClientUtil.sendRequest(oriUrl, tradeXml, "application/xml");
        logger.info("resultJsonData:" + resultJsonData);

        CreateOrderResponse createOrderResponse = JdPayUtil.parseResp(pubKey, desKey, resultJsonData, CreateOrderResponse.class);

        logger.info(createOrderResponse);

//        if("000000".equals(createOrderResponse.getResult().getCode())){
//            request.setAttribute("resultData", createOrderResponse);
//        }else{
//            request.setAttribute("errorMsg", createOrderResponse.getResult().getCode()+","+createOrderResponse.getResult().getDesc());
//        }
        return createOrderResponse;
    }

    @Override
    public AsynNotifyResponse asynNotify(HttpServletRequest httpServletRequest) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) httpServletRequest.getInputStream()));
            String line = null;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            logger.info("异步通知原始数据:" + sb);
        } catch (IOException e) {
            logger.error("异步通知原始数据异常:" + e);
            return null;
        }

        String deskey = PropertyUtils.getProperty("wepay.merchant.desKey");
        String pubKey = PropertyUtils.getProperty("wepay.jd.rsaPublicKey");
        try {
            AsynNotifyResponse anRes = JdPayUtil.parseResp(pubKey, deskey, sb.toString(), AsynNotifyResponse.class);
            logger.info("异步通知解析数据:" + anRes);
            logger.info("异步通知订单号：" + anRes.getTradeNum() + ",状态：" + anRes.getStatus() + "成功!!!!");

            return anRes;

        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public void updateStatus(String tradeNum) {
        payStatusMapper.updateStatus(tradeNum);
    }

    @Override
    public Boolean checkTradeNum(String tradeNum) {
        return payStatusMapper.checkTradeNum(tradeNum);
    }
}
