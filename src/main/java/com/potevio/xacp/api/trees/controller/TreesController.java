package com.potevio.xacp.api.trees.controller;
/**
 * @program: backend
 * @description: 树木结缘服务api
 * @author: Mr.lv
 * @create: 2018-08-06 10:54
 **/

import com.potevio.xacp.api.common.LocalErrorCode;
import com.potevio.xacp.api.common.utils.CreateOrder;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import com.potevio.xacp.api.pay.model.BasePayOrderInfo;
import com.potevio.xacp.api.pay.model.CreateOrderResponse;
import com.potevio.xacp.api.pay.service.JDPayService;
import com.potevio.xacp.api.trees.model.TreeAdoptOrder;
import com.potevio.xacp.api.trees.reponse.MyTreeOrder;
import com.potevio.xacp.api.trees.reponse.TreeOrderInfo;
import com.potevio.xacp.api.trees.reponse.TreeSpecies;
import com.potevio.xacp.api.trees.request.OrderId;
import com.potevio.xacp.api.trees.request.TreeInfoRequest;
import com.potevio.xacp.api.trees.service.TreesAdoptService;
import com.potevio.xacp.api.trees.service.TreesService;
import com.potevio.xacp.api.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/trees")
@Api(value = "TreesController", description = "树木结缘接口")
public class TreesController {
    private static final Logger logger = LoggerFactory.getLogger(TreesController.class);
    @Autowired
    private TreesService treesService;
    @Autowired
    private TreesAdoptService treesAdoptService;
    @Autowired
    private UserService userService;
    @Autowired
    private JDPayService jdPayService;

    /**
     * @Description: 获取我的认领记录
     * @Param: [page, pageSize]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/7
     */
    @ApiOperation(value = "认领记录", httpMethod = "GET")
    @GetMapping(value = "my-orders")
    public String index(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            int firstIndex = (page - 1) * pageSize;
            List<MyTreeOrder> orders = treesService.selectAllOrder(userId, firstIndex, pageSize);
            return FastJsonUtils.resultSuccess(200, "成功", orders);
        } catch (Exception e) {
            logger.error("TreesController : my-orders" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultSuccess(LocalErrorCode.COMMON_EXCEPTION, "获取认领记录失败", null);
        }
    }

    /**
     * @Description: 创建认领树木订单
     * @Param: [treeAdoptOrder]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/7
     */
    @ApiOperation(value = "创建认领树木订单", httpMethod = "POST")
    @PostMapping(value = "create-order")
    public String createOrder(@RequestBody TreeAdoptOrder treeAdoptOrder, HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            String username = userService.findUsernameById(userId);
            TreeSpecies treeSpecies = treesService.findOne(treeAdoptOrder.getSerialNumber());
            if (treeSpecies == null) {
                return FastJsonUtils.resultError(LocalErrorCode.TREES_TREE_NOT_FOUND, "没有该树木到信息", null);
            }
            // 生成自定义订单号
            String tradeNum = CreateOrder.getOrderNo();
            String timestamp = String.valueOf(new Date().getTime() / 1000);
            int time = Integer.valueOf(timestamp);
            treeAdoptOrder.setName(username);
            treeAdoptOrder.setUserAccountId(userId);
            // 赋值总金额
            treeAdoptOrder.setAmount(treeSpecies.getPrice());
            treeAdoptOrder.setPayStatus(20);
            treeAdoptOrder.setTradeNum(tradeNum);
            treeAdoptOrder.setCreateAt(time);
            // 创建订单
            boolean create = treesAdoptService.createdOrder(treeAdoptOrder);
            // 创建完毕后将树木的信息查询返回回去
            if (create) {
                treeSpecies.setTradeNum(tradeNum);
                return FastJsonUtils.resultSuccess(200, "订单创建成功，请完成支付", treeSpecies);
            } else {
                return FastJsonUtils.resultError(LocalErrorCode.TREES_CREATE_ORDER_ERROR, "创建订单失败", null);
            }
        } catch (Exception e) {
            logger.error("TreesController : create-order" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "创建订单失败", null);
        }
    }

    /**
     * @Description: 创建认领树木支付订单(发起京东支付)
     * @Param: [pay, map, request, response]
     * @return: org.springframework.http.ResponseEntity<com.potevio.xacp.api.trees.model.BasePayOrderInfo>
     * @Author: Mr.Lv
     * @Date: 2018/8/9
     */
    @ApiOperation(value = "创建认领树木支付订单(发起京东支付) ", httpMethod = "POST")
    @PostMapping(value = "create-order-pay")
    public String createOrderPay(@RequestBody OrderId orderId) {
        // 用户点击支付后
        //根据订单id查询订单信息
        TreeOrderInfo treeOrderInfo = treesAdoptService.findOneById(orderId.getOrderId());
        BasePayOrderInfo basePayOrderInfo = new BasePayOrderInfo();
        // 处理时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        long lt = new Long(treeOrderInfo.getCreatedAt()*1000L);
        Date date = new Date(lt);
        // TODO 吕寒冰 组合参数
        basePayOrderInfo.setVersion("V2.0");
        basePayOrderInfo.setMerchant("22294531");
        basePayOrderInfo.setTradeNum(treeOrderInfo.getTradeNum());
        basePayOrderInfo.setTradeName(treeOrderInfo.getName());
        basePayOrderInfo.setTradeDesc(treeOrderInfo.getDesc());
        basePayOrderInfo.setTradeTime(simpleDateFormat.format(date));
        basePayOrderInfo.setAmount(treeOrderInfo.getPrice().toString());
        basePayOrderInfo.setCurrency("CNY");
//        basePayOrderInfo.setNotifyUrl("http://api.develop.xacp.workerhub.cn/pay/notify/jd");
        basePayOrderInfo.setNotifyUrl("http://localhost:8085/pay/notify/jd");
        basePayOrderInfo.setOrderType("1");

        try {
            CreateOrderResponse createOrderResponse = jdPayService.unifiedOrder(basePayOrderInfo);
            if (createOrderResponse != null) {
                if ("000000".equals(createOrderResponse.getResult().getCode())) {
//                    request.setAttribute("resultData", createOrderResponse);
                    //  CreateOrderResponse [orderId=1033153410161420989603, merchantName=张三, amount=10, tradeNum=2018081222221111, qrCode=null, expireTime=604800, toString()=JdPayBaseResponse [version=V2.0, merchant=22294531, device=null, sign=Az02g/RTXelca+kdpWeEb0U/XlTiZAlFUH4BdGBkciCmx0SHm5FsdKkvqEf7zNC4nEAyaotQHYUECGP3bWOTJPOaoxnO60rbFKRX+X45B+O09jmP5UNAnpSJU6NEqFI9M19lLr6Z4W49wLxg0eSVlHrmTN2uJw9TV0J3ljg8D3M=, result=Result [code=000000, desc=成功]]]
                    // TODO 吕寒冰 创建支付订单成功处理，返回京东支付 orderID
                    Map<String,Object> map = new HashMap<>();
                    map.put("jdOrderId",createOrderResponse.getOrderId());
                    map.put("merchant",createOrderResponse.getMerchant());
                    map.put("tradeNum",createOrderResponse.getTradeNum());
                    map.put("amount",createOrderResponse.getAmount());
                    map.put("expireTime",createOrderResponse.getExpireTime());
                    return FastJsonUtils.resultSuccess(200,"支付订单创建成功",map);
                } else {
                    // TODO 吕寒冰 失败处理
//                    request.setAttribute("errorMsg", createOrderResponse.getResult().getCode() + "," + createOrderResponse.getResult().getDesc());
                    return FastJsonUtils.resultError(400,"支付订单创建失败",null);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @Description: 获取我的树木(已支付)
     * @Param: [page, pageSize]
     * @return: java.lang.String
     * @Author: Mr.Lv
     * @Date: 2018/8/7
     */
    @ApiOperation(value = "获取我的树木", httpMethod = "GET")
    @GetMapping(value = "my-trees")
    public String getMyTrees(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request) {
        try {
            String userId = request.getHeader("username");
            int firstIndex = (page - 1) * pageSize;
            List<MyTreeOrder> orders = treesService.selectMyTrees(userId, firstIndex, pageSize);
            return FastJsonUtils.resultSuccess(200, "成功", orders);
        } catch (Exception e) {
            logger.error("TreesController : my-trees" + e.getMessage());
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "获取我的树木失败", null);
        }
    }



    @ApiOperation(value = "获取树木信息", httpMethod = "GET")
    @GetMapping(value = "info")
    public String getTreeInfo(@RequestBody TreeInfoRequest treeInfoRequest, HttpServletRequest request) {
        // TODO 吕寒冰 获取树木信息，待完善
        treeInfoRequest.getSerialNumber();
        return "";
    }
}
