package com.potevio.xacp.api.certification.controller;

import com.jd.jr.risk.faceverify.export.vo.HttpIdAuthReq;
import com.potevio.xacp.api.certification.request.FaceVerifyRequest;
import com.potevio.xacp.api.certification.request.IdCardVerifyRequest;
import com.potevio.xacp.api.certification.service.TdIDAuthServiceImpl;
import com.potevio.xacp.api.common.LocalErrorCode;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/certification")
@Api(value = "CertificationController", description = "身份认证接口")
public class CertificationController {

    private static final Logger logger = LoggerFactory.getLogger(CertificationController.class);

    @Autowired
    private TdIDAuthServiceImpl tdIDAuthService;
    @ApiOperation(value = "人脸识别结果认证", httpMethod = "POST")
    @PostMapping(value = "face")
    public String face(@RequestBody FaceVerifyRequest faceVerifyRequest, HttpServletRequest request) {
        try {
            HttpIdAuthReq httpIdAuthReq = new HttpIdAuthReq();
            httpIdAuthReq.setAppName("JD_KJCX_XA_IDAUTH");
            httpIdAuthReq.setAppAuthorityKey("LqBdR1Pj2iyyszm/R8ELNFAVJkIsEeknQX/q9otG/4M=");
            httpIdAuthReq.setBusinessId("JD_KJCX_XA_SI");
            httpIdAuthReq.setVerifyToken(faceVerifyRequest.getToken());

            Boolean result = tdIDAuthService.verifyTokenCheck(httpIdAuthReq);

            if (result){
                // 根据身份证查看是否已经认证
                boolean checkCertification = tdIDAuthService.checkCertification(faceVerifyRequest.getIdCardNo());
                if (checkCertification){
                    return FastJsonUtils.resultError(400,"该用户已经认证","");
                }
                // TODO 吕寒冰 用户认证表增加记录，姓名/身份证号码，状态为10
                String userId = request.getHeader("username");
                faceVerifyRequest.setUserAccountId(userId);
                // 更新用户认证表
                Boolean resSave = tdIDAuthService.saveCertification(faceVerifyRequest);
                // 初始化市民卡账户表
                Boolean resInit = tdIDAuthService.initCitizenCard(userId,faceVerifyRequest.getIdCardNo());
                // TODO 吕寒冰 初始化市民卡账户表
                if (resSave && resInit){
                    return FastJsonUtils.resultSuccess(200, "认证成功", "");
                }else {
                    return FastJsonUtils.resultError(400,"认证失败","");
                }
            } else {
                // TODO 吕寒冰 用户认证表增加记录，姓名/身份证号码，状态为30
                tdIDAuthService.saveCertificationFail(faceVerifyRequest);
                return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "认证失败", null);
            }

        } catch (Exception e) {
            // TODO 吕寒冰 用户认证表增加记录，姓名/身份证号码，状态为 40
            logger.error("CertificationController : face" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "异常认证失败", null);
        }
    }

    /**
     * TODO 暂时不需要此接口
     * @param idCardVerifyRequest
     * @param request
     * @return
     */
    @ApiOperation(value = "实名身份认证", httpMethod = "POST")
    @PostMapping(value = "idcard")
    public String idCard(@RequestBody IdCardVerifyRequest idCardVerifyRequest, HttpServletRequest request) {
        try {

            if (true){
                // 用户认证表增加记录，姓名/身份证号码，状态为0
            }
            return FastJsonUtils.resultSuccess("200", "认证成功", "");
        } catch (Exception e) {
            logger.error("CertificationController : create-order" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "认证失败", null);
        }
    }
}
