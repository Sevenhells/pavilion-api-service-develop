package com.potevio.xacp.api.user.mappers;

import com.potevio.xacp.api.common.utils.MyMapper;
import com.potevio.xacp.api.user.reponse.ResCertification;
import com.potevio.xacp.api.user.reponse.ResInfo;
import org.apache.ibatis.annotations.Select;

public interface ResInfoMapper extends MyMapper<ResInfo> {

    ResInfo getInfoByUserId(String userId);
}
