package com.potevio.xacp.api.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.potevio.xacp.api.common.controller.CommonController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.potevio.xacp.api.common.utils.FastJsonUtils;
import com.potevio.xacp.api.common.utils.UploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件上传控制器
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:42
 */
@RestController
@Api(value = "文件上传接口", description = "文件上传接口")
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class UploadController extends CommonController {
	
	@Value("${spring.http.multipart.location}")
	private String multipartLocation;
	
	// 上传文件(支持批量)
	@RequestMapping("/image")
	@ApiOperation(value = "上传图片", httpMethod="POST")
	public String uploadImage(MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		 //上传文件
        String path = UploadUtils.saveMartipartFile(multipartLocation, request,file,"users","yyyyMM");
        return FastJsonUtils.resultSuccess(null, "上传成功",path);
	}
}
