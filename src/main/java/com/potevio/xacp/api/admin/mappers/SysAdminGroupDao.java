package com.potevio.xacp.api.admin.mappers;

import java.util.List;

import com.potevio.xacp.api.admin.model.SysAdminGroup;
import com.potevio.xacp.api.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface SysAdminGroupDao extends MyMapper<SysAdminGroup> {
	/**
	 * 查询分组信息
	 * @param userId 用户ID
	 * @param status 状态
	 * @return
	 */
	List<SysAdminGroup> selectByUserId(@Param("userId") Integer userId, @Param("status") Byte status);
}