package com.potevio.xacp.api.reservation.controller;

import com.potevio.xacp.api.common.LocalErrorCode;
import com.potevio.xacp.api.common.utils.FastJsonUtils;
import com.potevio.xacp.api.reservation.model.Reservation;
import com.potevio.xacp.api.reservation.request.AddReservationBean;
import com.potevio.xacp.api.reservation.request.CheckCodeBean;
import com.potevio.xacp.api.reservation.request.SceneCreateReservationBean;
import com.potevio.xacp.api.reservation.service.ReservationRulesService;
import com.potevio.xacp.api.reservation.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

/**
 * @Desc 预约服务控制层
 * @Version peng.zhang
 * @Date 2018/8/5 下午4:42
 */
@RestController
@RequestMapping("/api/v1/reservation")
@Api(value = "ReservationController", description = "预约服务接口")
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRulesService reservationRulesService;

    /**
     * 创建预约信息
     * @param addReservationBean 预约信息
     * @param request request请求
     * @return 已添加的预约信息
     */
    @PostMapping
    @ApiOperation(value = "创建预约信息", httpMethod = "POST")
    public String createReservation(@RequestBody AddReservationBean addReservationBean, HttpServletRequest request) {
        try {
            String reservationAccountId = request.getHeader("username");
            if (checkUserNull(reservationAccountId)) {
                return FastJsonUtils.resultError(LocalErrorCode.COMMON_USER_NULL, "用户名为空", null);
            }
            List<Reservation> addedReservation = reservationService.addMultiple(reservationAccountId, addReservationBean);
            return FastJsonUtils.resultSuccess(null, "预约成功", addedReservation);
        } catch (Exception e) {
            logger.error("ReservationController : createReservation error", e);
            return FastJsonUtils.resultSuccess(LocalErrorCode.COMMON_EXCEPTION, "服务器发生未知错误", null);
        }
    }

    /**
     * 获取预约信息
     * @return 预约信息
     */
    @GetMapping(value = "my-reservations")
    @ApiOperation(value = "获取我的预约信息", httpMethod = "GET")
    public String getMyReservation(HttpServletRequest request) {
        try {
            String reservationAccountId = request.getHeader("username");
            if (checkUserNull(reservationAccountId)) {
                return FastJsonUtils.resultError(LocalErrorCode.COMMON_USER_NULL, "用户名为空", null);
            }
            return FastJsonUtils.resultSuccess(
                    null,
                    "获取预约信息成功",
                    reservationService.selectByUid(reservationAccountId));
        } catch (Exception e) {
            logger.error("ReservationController : getMyReservation error");
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "服务器发生未知错误", null);
        }
    }

    /**
     * TODO 获取不可预约的日期（闭馆的日期）（已完成）
     * @return 不可预约的日期列表
     */
    @GetMapping(value = "unreachable-date")
    @ApiOperation(value = "获取不可预约的日期", httpMethod = "GET")
    public String getUnreachableDate(HttpServletRequest request) {
        try {
            // TODO 张志明 获取不可预约的日期 逻辑，放到Service层处理，返回不可预约日期列表（已完成）
            List<Date> dateList=reservationRulesService.getNotReservateDates();
            // 1.特殊规则 2.通用规则 3.日预约数达到上限

            return FastJsonUtils.resultSuccess(200,"数据获取成功",dateList);
        } catch (Exception e) {
            logger.error("ReservationController : getUnreachableDate error");
            e.printStackTrace();
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "服务器发生未知错误", null);
        }
    }

    /**
     * 验证预约码
     * @return
     */
    @PostMapping(value = "check-code")
    @ApiOperation(value = "验证预约码", httpMethod = "POST")
    public String checkCode(CheckCodeBean checkCodeBean) {
        try {
            return reservationService.checkCode(checkCodeBean);
        } catch (Exception e) {
            logger.error("ReservationController: checkCode error", e);
            return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "服务器发生未知错误", null);
        }
    }

    /**
     * 现场预约
     * @return
     */
    @PostMapping(value = "scene-create-reservation")
    @ApiOperation(value = "现场预约", httpMethod = "POST")
    public String sceneCreateReservation(SceneCreateReservationBean sceneCreateReservationBean) {

        return FastJsonUtils.resultError(LocalErrorCode.COMMON_EXCEPTION, "服务器发生未知错误", null);
    }

    /**
     * 检查用户名是否为空（即当前用户是否登录）
     * @param reservationAccountId 用户ID
     * @return 如果为空，返回true，否则返回false
     */
    private boolean checkUserNull(String reservationAccountId) {
        return (reservationAccountId == null || "".equals(reservationAccountId.trim()));
    }

}
