package com.potevio.xacp.api.base.request;

public class SendMsgRequest {
    private String mobile;
    private String source;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
