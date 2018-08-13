package com.potevio.xacp.api.certification.mappers;

import com.potevio.xacp.api.certification.request.CizendCardAccount;
import com.potevio.xacp.api.certification.request.FaceVerifyRequest;
import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.user.reponse.ResCertification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface CertificationMapper{

    @Insert("insert into user_certification values (null,#{name},#{idCardNo},#{idCardImgFront},#{idCardImgBack},#{status},#{createdAt},#{updatedAt},#{userAccountId})")
    Boolean saveCertification(FaceVerifyRequest faceVerifyRequest);

    @Insert("insert into citizen_card_account values (#{id},#{serialNumber},#{userAccountId},#{theme},#{bindStatus},#{getStatus},#{printStatus},#{createdAt},#{updatedAt})")
    Boolean initCitizenCard(CizendCardAccount cizendCardAccount);

    @Insert("insert into user_certification values (null,#{name},#{idCardNo},#{idCardImgFront},#{idCardImgBack},#{status},#{createdAt},#{updatedAt},#{userId})")
    void saveCertificationFail(FaceVerifyRequest faceVerifyRequest);

    @Select("select * from user_certification where id_card_no = #{idCardNo}")
    boolean checkCertification(String idCardNo);
}
