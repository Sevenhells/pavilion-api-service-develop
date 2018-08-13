package com.potevio.xacp.api.common.model;

public class User {

    private String id;
    private String mobile;
    private String code;
    private String avatar;
    private String token;
    private int certificationStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCertificationStatus() {
        return certificationStatus;
    }

    public void setCertificationStatus(int certificationStatus) {
        this.certificationStatus = certificationStatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
