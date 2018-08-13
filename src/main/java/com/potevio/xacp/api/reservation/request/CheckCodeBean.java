package com.potevio.xacp.api.reservation.request;

/**
 * Description: 验证预约码实体
 * Author: peng.zhang
 * Date: 2018/8/11  0:55
 */
public class CheckCodeBean {

    /**
     * 预约码
     */
    private String code;

    /**
     * 手环ID
     */
    private String bandId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }
}
