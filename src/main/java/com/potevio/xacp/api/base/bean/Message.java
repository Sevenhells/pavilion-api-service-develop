package com.potevio.xacp.api.base.bean;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "message")
public class Message {

    private String msgSign="张志明";

    private String registerTemplateCode="SMS_141430035";

    private String loginTemplateCode="SMS_142000138";

    public String getMsgSign() {
        return msgSign;
    }

    public void setMsgSign(String msgSign) {
        this.msgSign = msgSign;
    }

    public String getRegisterTemplateCode() {
        return registerTemplateCode;
    }

    public void setRegisterTemplateCode(String registerTemplateCode) {
        this.registerTemplateCode = registerTemplateCode;
    }

    public String getLoginTemplateCode() {
        return loginTemplateCode;
    }

    public void setLoginTemplateCode(String loginTemplateCode) {
        this.loginTemplateCode = loginTemplateCode;
    }
}

