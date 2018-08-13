package com.potevio.xacp.api.base.Controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.potevio.xacp.api.base.bean.Message;
import com.potevio.xacp.api.base.model.*;
import com.potevio.xacp.api.base.request.SendMsgRequest;
import com.potevio.xacp.api.base.service.APPVersionService;
import com.potevio.xacp.api.base.service.MapService;
import com.potevio.xacp.api.base.service.PavilionMapAreaService;
import com.potevio.xacp.api.common.controller.CommonController;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import com.potevio.xacp.api.common.utils.MessageUtil;
import com.potevio.xacp.api.common.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/base")
@Api(value = "BaseController", description = "基础服务接口")
public class BaseController extends CommonController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private MapService mapService;

    @Autowired
    private PavilionMapAreaService pavilionMapAreaService;

    @Autowired
    private APPVersionService appVersionService;

    /**
     * 发送短信
     *
     */
    @ApiOperation(value = "发送短信", httpMethod = "POST")
    @PostMapping(value = "app/code", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String sendMsg(@RequestBody(required = true) SendMsgRequest sendMsgRequest,Message message) throws ClientException {

        String phoneNumber = sendMsgRequest.getMobile();
        String randomNum = createRandomNum(6);
        String jsonContent = "{\"code\":\"" + randomNum + "\"}";

        Map<String, String> paramMap = new HashMap<>();
        Jedis jedis=new Jedis();

        // TODO 张志明 逻辑优化，配置文件优化（已完成除redis部分）
        if (sendMsgRequest.getSource().equals("register")) {
            paramMap.put("phoneNumber", phoneNumber);
            paramMap.put("msgSign", message.getMsgSign());
            paramMap.put("templateCode", message.getRegisterTemplateCode());
            paramMap.put("jsonContent", jsonContent);
            // TODO 张志明 把mobile 、code 、过期时间 5分钟  存入 redis  主键为   register_mobile
            RedisUtil.set("register_"+phoneNumber,randomNum);
            jedis.expire("register_"+phoneNumber,300);
        } else if(sendMsgRequest.getSource().equals("login")) {
            paramMap.put("phoneNumber", phoneNumber);
            paramMap.put("msgSign", message.getMsgSign());
            paramMap.put("templateCode", message.getLoginTemplateCode());
            paramMap.put("jsonContent", jsonContent);
            // TODO 把mobile 、code 、过期时间 5分钟  存入 redis  主键为   login_mobile
            RedisUtil.set("login_"+phoneNumber,randomNum);
            jedis.expire("login_"+phoneNumber,300);
        } else {
            return FastJsonUtils.resultError(400, "系统错误",null);
        }

        SendSmsResponse sendSmsResponse = MessageUtil.sendSms(paramMap);

        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            return FastJsonUtils.resultSuccess(200, "短信发送成功",null);
        }else{
            return FastJsonUtils.resultError(200, "短信发送失败",null);
        }
    }

    @ApiOperation(value = "获取地图", httpMethod = "GET")
    @GetMapping(value = "map", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getMap() {
        List<PavilionMap> maps = mapService.selectAll();
        return FastJsonUtils.resultSuccess(200, "成功",maps);
    }

    @ApiOperation(value = "获取区域数据", httpMethod = "GET")
    @GetMapping(value = "map/areas", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getMapAreas() {
        List<ResultMapAreas> resultMapAreas=mapService.selectAllAreas();
        //        List<PavilionMap> maps = mapService.selectAll();
        for(ResultMapAreas pavilionMap : resultMapAreas) {
            List<PavilionMapArea> areaList=pavilionMapAreaService.selectByMapId(pavilionMap.getId());
            pavilionMap.setAreas(areaList);
        }
        return FastJsonUtils.resultSuccess(200, "成功",resultMapAreas);
    }


    /**
     * APP检查更新
     *
     */
    @ApiOperation(value = "APP检查更新", httpMethod = "POST")
    @PostMapping(value = "app/checkupdate")
    @ResponseBody
    public String checkUpdate(@RequestParam ("versionCode") Integer versionCode){
        try {
            APPVersion appVersion=appVersionService.selectNew();
            if(versionCode < appVersion.getVersionCode()){
                Map<String,Object> map=new HashMap<>();
                map.put("downloadUrl",appVersion.getUrl());
                map.put("versionCode",appVersion.getVersionCode());
                map.put("versionName",appVersion.getVersionName());
                map.put("updateLog",appVersion.getUpdateLog());
                return FastJsonUtils.resultSuccess(200,"发现新版本",map);
            }else {
                return FastJsonUtils.resultSuccess(200,"您已升级为最新版本",null);
            }
        }catch (Exception e){
            logger.error("DeviceController : editDevice error");
            e.printStackTrace();
        }
        return FastJsonUtils.resultError(-200,"查询失败",null);
    }
    /**
     * 生成随机数
     * @param num 位数
     * @return
     */
    public static String createRandomNum(int num){
        String randomNumStr = "";
        for(int i = 0; i < num;i ++){
            int randomNum = (int)(Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }

}
