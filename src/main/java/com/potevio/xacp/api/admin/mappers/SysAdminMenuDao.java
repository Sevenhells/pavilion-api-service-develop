package com.potevio.xacp.api.admin.mappers;

import java.util.List;

import com.potevio.xacp.api.admin.model.SysAdminMenu;
import com.potevio.xacp.api.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface SysAdminMenuDao extends MyMapper<SysAdminMenu> {
	/**
	 * 根据ruleIds查询菜单信息
	 * @param ruleIds 权限id
	 * @param status 状态值
	 * @return List<SysAdminMenu>
	 */
	List<SysAdminMenu> selectInRuleIds(@Param("ruleIds") String ruleIds, @Param("status") int status);
}