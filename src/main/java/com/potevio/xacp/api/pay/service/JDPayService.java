package com.potevio.xacp.api.pay.service;

import com.potevio.xacp.api.pay.model.AsynNotifyResponse;
import com.potevio.xacp.api.pay.model.BasePayOrderInfo;
import com.potevio.xacp.api.pay.model.CreateOrderResponse;

import javax.servlet.http.HttpServletRequest;

public interface JDPayService {

    public CreateOrderResponse unifiedOrder(BasePayOrderInfo basePayOrderInfo) throws InstantiationException, IllegalAccessException;

    public AsynNotifyResponse asynNotify(HttpServletRequest httpServletRequest);

    void updateStatus(String tradeNum);

    Boolean checkTradeNum(String tradeNum);
}
