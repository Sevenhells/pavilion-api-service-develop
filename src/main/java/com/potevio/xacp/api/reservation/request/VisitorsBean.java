package com.potevio.xacp.api.reservation.request;

/**
 * Description: 预约人信息
 * Author: peng.zhang
 * Date: 2018/8/7  23:03
 */
public class VisitorsBean {

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idcardNumber;

    /**
     * 电话号
     */
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
