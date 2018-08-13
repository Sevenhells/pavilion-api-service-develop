package com.potevio.xacp.api.admin.service;

import com.potevio.xacp.api.admin.model.SysAdminAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.potevio.xacp.api.common.base.BaseServiceImpl;
import com.potevio.xacp.api.admin.mappers.SysAdminAccessDao;
import tk.mybatis.mapper.common.Mapper;

@Service
public class SysAdminAccessService extends BaseServiceImpl<SysAdminAccess>{
	
	@Autowired
	private SysAdminAccessDao sysAdminAccessDao;
	
	@Override
	public Mapper<SysAdminAccess> getMapper() {
		return sysAdminAccessDao;
	}

}
