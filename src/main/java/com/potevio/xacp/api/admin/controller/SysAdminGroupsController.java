package com.potevio.xacp.api.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.potevio.xacp.api.common.LocalErrorCode;
import com.potevio.xacp.api.common.controller.CommonController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.potevio.xacp.api.admin.service.SysAdminGroupService;
import com.potevio.xacp.api.admin.model.SysAdminGroup;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统分组 控制层
 * @author leo.aqing
 */
@RestController
@RequestMapping("/api/v1/auth/admin/groups")
@Api(value = "SysAdminGroupsController", description="系统分组接口")
public class SysAdminGroupsController extends CommonController {
	@Autowired
	private SysAdminGroupService sysAdminGroupService;
	
	/**
	 * 列表
	 */
	@ApiOperation(value = "列表", httpMethod="GET")
	@GetMapping(value = "", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String index(HttpServletRequest request) {
		List<Map<String, Object>> goups = sysAdminGroupService.getDataList();
		return FastJsonUtils.resultSuccess(200, "成功", goups);
	}
	
	/**
	 * 读取
	 */
	@ApiOperation(value = "编辑", httpMethod="GET")
	@GetMapping(value = "edit/{id}", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String read(@PathVariable Integer id, HttpServletRequest request) {
		SysAdminGroup goup = sysAdminGroupService.selectByPrimaryKey(id);
		return FastJsonUtils.resultSuccess(200, "成功", goup);
	}
	
	/**
	 * 保存
	 */
	@ApiOperation(value = "保存", httpMethod="POST")
	@PostMapping(value = "save", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String save(@RequestBody(required=false) SysAdminGroup record,HttpServletRequest request) {
		if(record.getPid() == null) {
			record.setPid(0);
		} 
		int row = sysAdminGroupService.save(record);
		if(row == 0) {
			return FastJsonUtils.resultError(LocalErrorCode.ADMIN_GROUP_HANDLE_ERROR, "保存失败", null);
		}
		return FastJsonUtils.resultSuccess(200, "成功", null);
	}
	
	
	/**
	 * 更新
	 */
	@ApiOperation(value = "更新")
	@PostMapping(value = "update", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String update(@RequestBody(required=false) SysAdminGroup record,HttpServletRequest request) {
		int row = sysAdminGroupService.save(record);
		if(row == 0) {
			return FastJsonUtils.resultError(LocalErrorCode.ADMIN_GROUP_HANDLE_ERROR, "更新失败", null);
		}
		return FastJsonUtils.resultSuccess(200, "更新成功", null);
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "删除")
	@DeleteMapping(value = "delete/{id}", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delete(@PathVariable Integer id) {
		int row = sysAdminGroupService.deleteByPrimaryKey(id);
		if(row == 0) {
			return FastJsonUtils.resultError(LocalErrorCode.ADMIN_GROUP_HANDLE_ERROR, "删除失败", null);
		}
		return FastJsonUtils.resultSuccess(200, "删除成功", null);
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = "根据ids批量删除")
	@PostMapping(value = "deletes", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String deletes(@RequestBody Map<String, Object> params) {
		@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>)params.get("ids");
		if (CollectionUtils.isEmpty(ids)) {
			return FastJsonUtils.resultError(LocalErrorCode.ADMIN_GROUP_HANDLE_IDS_NULL, "操作失败", null);
		}
		try {
			for (int i = 0; i < ids.size(); i++) {
				sysAdminGroupService.deleteByPrimaryKey(ids.get(i));
			}
		} catch (Exception e) {
			return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "服务器发生未知错误", null);
		}
		return FastJsonUtils.resultSuccess(200, "成功", null);
	}
	
	/**
	 * 启用或禁用
	 */
	@ApiOperation(value = "根据ids批量启用或禁用")
	@PostMapping(value = "enables", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String enables(@RequestBody Map<String, Object> params) {
		@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>)params.get("ids");
		byte status = Byte.valueOf(params.get("status").toString());
		if (CollectionUtils.isEmpty(ids)) {
			return FastJsonUtils.resultError(LocalErrorCode.ADMIN_GROUP_HANDLE_IDS_NULL, "操作失败", null);
		}
		try {
			for (int i = 0; i < ids.size(); i++) {
				SysAdminGroup record = new SysAdminGroup();
				record.setId(Integer.valueOf(ids.get(0)));
				record.setStatus(status);
				sysAdminGroupService.updateByPrimaryKeySelective(record);
			}
		} catch (Exception e) {
			return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "保存失败", null);
		}
		return FastJsonUtils.resultSuccess(200, "成功", null);
	}
}
