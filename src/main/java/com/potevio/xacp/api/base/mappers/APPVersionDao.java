package com.potevio.xacp.api.base.mappers;

import com.potevio.xacp.api.base.model.APPVersion;
import com.potevio.xacp.api.common.utils.MyMapper;


public interface APPVersionDao extends MyMapper<APPVersion> {

//    @Select("select url,version_code,version_name,update_log,source from app_version ")
    APPVersion selectNew();
}
