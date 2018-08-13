package com.potevio.xacp.api.certification.service;

import com.jd.jr.risk.faceverify.export.service.TdIDAuthService;
import com.jd.jr.risk.faceverify.export.vo.*;
import com.jd.jr.risk.faceverify.export.vo.resp.RespVO;

import com.potevio.xacp.api.certification.HttpUtils;
import com.potevio.xacp.api.certification.mappers.CertificationMapper;
import com.potevio.xacp.api.certification.request.CizendCardAccount;
import com.potevio.xacp.api.certification.request.FaceVerifyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TdIDAuthServiceImpl implements TdIDAuthService {
    @Autowired
    private CizendCardAccount cizendCardAccount;
    @Autowired
    private CertificationMapper certificationMapper;
    @Override
    public RespVO<HttpIdAuthResp> idAuth(HttpIdAuthReq httpIdAuthReq) {

        // TODO 请求京东身份认证接口

//        HttpIdAuthReq httpIdAuthReq = new HttpIdAuthReq();
        httpIdAuthReq.setAppName("JD_KJCX_XA_IDAUTH");
        httpIdAuthReq.setAppAuthorityKey("LqBdR1Pj2iyyszm/R8ELNFAVJkIsEeknQX/q9otG/4M=");
        httpIdAuthReq.setBusinessId("JD_KJCX_XA_SI");
        httpIdAuthReq.setVerifyBusinessType(VerifyBusinessType.SIMPLE_VERIFY);
        httpIdAuthReq.setFaceSDK("libJDCNSDK");
        httpIdAuthReq.setFaceSDKVersion("1.5");
        httpIdAuthReq.setName("郭栋");
        httpIdAuthReq.setIdCard("142322199502267513");
        httpIdAuthReq.setVerifyToken("");
        ArrayList<String> facedata = new ArrayList<String>();
        facedata.add("MP#IDCARD_334c528ddb8c0721f18fc5b95528dbb01bc21019ed22b561ea8a91417c87901e");
        httpIdAuthReq.setFaceData(facedata);
//        httpIdAuthReq.setName("JD_KJCX_XA_IDAUTH");
        try {
            HttpUtils.httpPostWithJSON("https://identify.jd.com/f/idAuth",httpIdAuthReq);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean verifyTokenCheck(HttpIdAuthReq httpIdAuthReq) {

        // TODO 郭栋 请求京东验证接口
        httpIdAuthReq.setAppName("JD_KJCX_XA_IDAUTH");
        httpIdAuthReq.setAppAuthorityKey("LqBdR1Pj2iyyszm/R8ELNFAVJkIsEeknQX/q9otG/4M=");
        httpIdAuthReq.setBusinessId("JD_KJCX_XA_SI");
        httpIdAuthReq.setVerifyBusinessType(VerifyBusinessType.SIMPLE_VERIFY);
        httpIdAuthReq.setFaceSDK("libJDCNSDK");
        httpIdAuthReq.setFaceSDKVersion("1.5");
        httpIdAuthReq.setVerifyToken("");

        try {
            HttpUtils.httpPostWithJSON("https://identify.jd.com/f/idAuth",httpIdAuthReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public RespVO<IdCardAuthResp> idCardVerify(IdCardAuthReq idCardAuthReq) {
        return null;
    }

    @Override
    public RespVO<IdCardOcrResp> idCardOCRdetect(IdCardOcrReq idCardOcrReq) {
        return null;
    }

    public Boolean saveCertification(FaceVerifyRequest faceVerifyRequest) {
        String timestamp = String.valueOf(new Date().getTime() / 1000);
        int time = Integer.valueOf(timestamp);
        faceVerifyRequest.setStatus(10);
        faceVerifyRequest.setCreatedAt(time);
        faceVerifyRequest.setUpdatedAt(faceVerifyRequest.getCreatedAt());
        return certificationMapper.saveCertification(faceVerifyRequest);
    }

    public Boolean initCitizenCard(String userId,String idCardNo) {
        // 设置id
        String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String timestamp = String.valueOf(new Date().getTime() / 1000);
        int time = Integer.valueOf(timestamp);
        cizendCardAccount.setId(id);
        cizendCardAccount.setSerialNumber(idCardNo);
        cizendCardAccount.setUserAccountId(userId);
        cizendCardAccount.setTheme("雄安");
        cizendCardAccount.setBindStatus(20);
        cizendCardAccount.setGetStatus(20);
        cizendCardAccount.setPrintStatus(20);
        cizendCardAccount.setCreatedAt(time);
        cizendCardAccount.setUpdatedAt(cizendCardAccount.getCreatedAt());
        return certificationMapper.initCitizenCard(cizendCardAccount);
    }

    public void saveCertificationFail(FaceVerifyRequest faceVerifyRequest) {
        String timestamp = String.valueOf(new Date().getTime() / 1000);
        int time = Integer.valueOf(timestamp);
        faceVerifyRequest.setStatus(30);
        faceVerifyRequest.setCreatedAt(time);
        faceVerifyRequest.setUpdatedAt(faceVerifyRequest.getCreatedAt());
        certificationMapper.saveCertificationFail(faceVerifyRequest);
    }

    public boolean checkCertification(String idCardNo) {
        return certificationMapper.checkCertification(idCardNo);
    }
}
