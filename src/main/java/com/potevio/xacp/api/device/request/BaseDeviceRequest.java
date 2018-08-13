package com.potevio.xacp.api.device.request;

public class BaseDeviceRequest {

    private String sign;

    private String mac;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
