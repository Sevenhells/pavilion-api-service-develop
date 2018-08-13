package com.potevio.xacp.api.admin.controller;

import com.potevio.xacp.api.common.controller.CommonController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开放平台APP管理
 * @author 张志明
 */

@RestController
@RequestMapping("/api/v1/admin/open/app")
@Api(value = "SysAdminOpenAppController", description = "开放平台APP管理接口")
public class SysAdminOpenAppController extends CommonController {
    private static final Logger logger = LoggerFactory.getLogger(SysAdminOpenAppController.class);

    // TODO 张志明 开放平台APP管理


}
