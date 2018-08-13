package com.potevio.xacp.api.certification.request;

import lombok.Data;

@Data
public class FaceVerifyRequest {
    private String token;
    private String verifyId;
    private String name;
    private String idCardNo;
    private String idCardImgFront;
    private String idCardImgBack;
    private Integer status;
    private Integer createdAt;
    private Integer updatedAt;
    private String userAccountId;
}
