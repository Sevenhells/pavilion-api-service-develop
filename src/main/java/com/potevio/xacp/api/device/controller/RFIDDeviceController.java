package com.potevio.xacp.api.device.controller;

import com.potevio.xacp.api.common.controller.CommonController;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import com.potevio.xacp.api.device.request.BaseDeviceRequest;
import com.potevio.xacp.api.device.request.PostIdRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/device")
@Api(value = "RFIDDeviceController", description = "RFID感应设备接口")
public class RFIDDeviceController extends CommonController {

    @ApiOperation(value = "心跳接口", httpMethod="POST")
    @PostMapping(value = "ping", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String ping(@RequestBody(required=true) BaseDeviceRequest baseDeviceRequest, HttpServletRequest request) {

        // TODO 郭栋 监测上次请求时间，更新设备表状态，设备出现异常短信通知管理员

        String macAddress = baseDeviceRequest.getMac();
        // TODO 郭栋 根据MAC地址查询设备表

        return FastJsonUtils.resultSuccess(null, "成功", null);
    }


    @ApiOperation(value = "提交用户手环ID数据", httpMethod="POST")
    @PostMapping(value = "post-id", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String postID(@RequestBody(required=true) PostIdRequest postIdRequest, HttpServletRequest request) {

        // TODO 郭栋 记录交互数据，更新用户积分账户，记录积分日志，同步数据给第三方厂商服务器（需要从open_app表中读取，发送到不同服务器）

        String bandId = postIdRequest.getBandId();

        String macAddress = postIdRequest.getMac();
        // TODO 郭栋 根据MAC地址查询设备表设备关联的项目信息(open_app)（项目id、项目单次积分数额）


        return FastJsonUtils.resultSuccess(null, "成功", null);
    }

}
