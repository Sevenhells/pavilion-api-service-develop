package com.potevio.xacp.api.certification.request;

public class IdCardVerifyRequest {
    private String name;
    private String idCardNumber;

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
}
