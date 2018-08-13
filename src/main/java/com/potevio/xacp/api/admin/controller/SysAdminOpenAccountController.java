package com.potevio.xacp.api.admin.controller;

import com.potevio.xacp.api.admin.model.Device;
import com.potevio.xacp.api.admin.service.SysAdminDeviceService;
import com.potevio.xacp.api.common.controller.CommonController;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开放平台账号管理
 * @author 张志明
 */

@RestController
@RequestMapping("/api/v1/admin/open/accounts")
@Api(value = "SysAdminOpenAccountController", description = "开放平台账号管理接口")
public class SysAdminOpenAccountController extends CommonController {
    private static final Logger logger = LoggerFactory.getLogger(SysAdminOpenAccountController.class);

    // TODO 张志明 开放平台账号管理


}
