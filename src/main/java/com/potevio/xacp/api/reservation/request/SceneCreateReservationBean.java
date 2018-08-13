package com.potevio.xacp.api.reservation.request;

/**
 * Description: 现场预约请求实体
 * Author: peng.zhang
 * Date: 2018/8/11  1:01
 */
public class SceneCreateReservationBean {

    /**
     * 实名认证token
     */
    private String certificationToken;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCardNumber;

    /**
     * 手环ID
     */
    private String bandId;

    public String getCertificationToken() {
        return certificationToken;
    }

    public void setCertificationToken(String certificationToken) {
        this.certificationToken = certificationToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }
}

