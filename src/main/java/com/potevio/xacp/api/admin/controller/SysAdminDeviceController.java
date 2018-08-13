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
 * 设备控制层
 * @author 张志明
 */
@RestController
@RequestMapping("/api/v1/admin/devices")
@Api(value = "DeviceController", description = "设备接口")
public class SysAdminDeviceController extends CommonController {
    private static final Logger logger = LoggerFactory.getLogger(SysAdminDeviceController.class);

    @Autowired
    private SysAdminDeviceService deviceService;

    /**
     * 列表
     */
    @ApiOperation(value = "设备列表", httpMethod="GET")
    @GetMapping(value = "")
    @ResponseBody
    public String List(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        try{
            int start = (page-1)*pageSize;
            Map<String, Object> paramMap = new HashMap<String,Object>();
            paramMap.put("start", start);
            paramMap.put("size", pageSize);
            List<Device> devicelist = deviceService.selectAll(paramMap);
            return FastJsonUtils.resultSuccess(200, "成功",devicelist);
        }catch (Exception e){
            logger.error("DeviceController : getDrvice error");
            e.printStackTrace();
        }
        return FastJsonUtils.resultSuccess(500, "查询失败", null);
    }

    /**
     * 读取设备信息
     */
    @ApiOperation(value = "设备编辑", httpMethod="GET")
    @GetMapping(value = "/edit")
    @ResponseBody
    public String read(@RequestParam("id") Integer id , HttpServletRequest request) {
        try{
            Device goup = deviceService.selectById(id);
            return FastJsonUtils.resultSuccess(200, "成功", goup);
        }catch (Exception e){
            logger.error("DeviceController : editDevice error");
            e.printStackTrace();
        }
        return FastJsonUtils.resultSuccess(500, "查询失败", null);
    }
    /**
     * 添加
     * 暂时有问题
     */
    @ApiOperation(value = "添加设备", httpMethod="POST")
    @PostMapping(value = "insert")
    @ResponseBody
    public String insert(@RequestBody Device record,HttpServletRequest request) throws Exception {
        int row = deviceService.insert(record,request);
        if(row == 0) {
            return FastJsonUtils.resultError(-200, "保存失败", null);
        }
        return FastJsonUtils.resultSuccess(200, "成功", null);
    }

    /**
     * 编辑修改设备
     * 暂时有问题
     */
    @ApiOperation(value = "修改设备", httpMethod="PUT")
    @PutMapping(value = "/update")
    @ResponseBody
    public String update(@RequestBody Device record) {
        int row = deviceService.update(record);
        if(row == 0) {
            return FastJsonUtils.resultError(-200, "保存失败", null);
        }
        return FastJsonUtils.resultSuccess(200, "成功", null);
    }

    /**
     * 删除
     *
     */
    @ApiOperation(value = "设备删除",httpMethod = "DELETE")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam("id") Integer id) {
        int row = deviceService.deleteById(id);
        if(row == 0) {
            return FastJsonUtils.resultError(-200, "删除失败", null);
        }
        return FastJsonUtils.resultSuccess(200, "删除成功", null);
    }

}
