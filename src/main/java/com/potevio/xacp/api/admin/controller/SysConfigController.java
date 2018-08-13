package com.potevio.xacp.api.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.potevio.xacp.api.common.controller.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.potevio.xacp.api.common.service.SysSystemConfigService;
import com.potevio.xacp.api.common.model.SysSystemConfig;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统配置 控制层
 * @author leo.aqing
 */
@RestController
@RequestMapping("/api/v1/auth/admin")
@Api(value = "SysConfigController", description = "系统配置接口")
public class SysConfigController extends CommonController {
	@Autowired
	private SysSystemConfigService sysSystemConfigService;
	
	@ApiOperation(value = "获取配置", httpMethod="POST")
	@PostMapping(value = "/configs", produces = {"application/json;charset=UTF-8"})
	public String configs(@RequestBody(required=false) SysSystemConfig record,HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<SysSystemConfig> configs = sysSystemConfigService.select(record);
		for (SysSystemConfig c : configs) {
			data.put(c.getName(), c.getValue());
		}
		return FastJsonUtils.resultSuccess(200, "查询配置成功", data);
	}
}
