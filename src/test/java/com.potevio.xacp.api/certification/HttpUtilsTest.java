package com.potevio.xacp.api.certification;

import com.jd.jr.risk.faceverify.export.vo.HttpIdAuthReq;
import com.jd.jr.risk.faceverify.export.vo.VerifyBusinessType;
import com.potevio.xacp.api.common.utils.ServiceUtils;
import org.junit.Test;

import java.util.ArrayList;

public class HttpUtilsTest {

    @Test
    public void testHttpPostWithJSON() {
        HttpIdAuthReq httpIdAuthReq = new HttpIdAuthReq();
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
    }
}
