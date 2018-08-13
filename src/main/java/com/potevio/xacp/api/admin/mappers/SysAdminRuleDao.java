package com.potevio.xacp.api.admin.mappers;

import java.util.List;

import com.potevio.xacp.api.admin.model.SysAdminRule;
import com.potevio.xacp.api.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface SysAdminRuleDao extends MyMapper<SysAdminRule> {

	List<SysAdminRule> selectInIds(@Param("ruleIds") String ruleIds, @Param("status") Integer status);

	List<SysAdminRule> selectByStatus(@Param("status") Integer status);
}