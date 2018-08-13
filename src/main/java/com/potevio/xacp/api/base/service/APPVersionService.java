package com.potevio.xacp.api.base.service;

import com.potevio.xacp.api.base.mappers.APPVersionDao;
import com.potevio.xacp.api.base.model.APPVersion;
import com.potevio.xacp.api.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class APPVersionService extends BaseServiceImpl<APPVersion> {
    @Autowired
    private APPVersionDao appDao;

    @Override
    public Mapper<APPVersion> getMapper() {
        return appDao;
    }

    public APPVersion selectNew() {
        return appDao.selectNew();
    }
}
