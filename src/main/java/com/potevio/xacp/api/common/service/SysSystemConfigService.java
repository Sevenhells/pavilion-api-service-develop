package com.potevio.xacp.api.common.service;

import com.potevio.xacp.api.common.model.SysSystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.potevio.xacp.api.common.base.BaseServiceImpl;
import com.potevio.xacp.api.common.mappers.SysSystemConfigDao;
import tk.mybatis.mapper.common.Mapper;
@Service
public class SysSystemConfigService extends BaseServiceImpl<SysSystemConfig>{
	@Autowired
	private SysSystemConfigDao sysSystemConfigDao;
	
	@Override
	public Mapper<SysSystemConfig> getMapper() {
		return sysSystemConfigDao;
	}

}
