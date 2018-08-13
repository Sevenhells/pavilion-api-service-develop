package com.potevio.xacp.api.reservation.request;

import java.util.List;

/**
 * Description: 创建预约信息请求参数
 * Author: peng.zhang
 * Date: 2018/8/7  22:57
 */
public class AddReservationBean {

    /**
     * 参观日期
     */
    private Integer visitDate;

    /**
     * 预约人列表
     */
    private List<VisitorsBean> visitors;

    public Integer getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Integer visitDate) {
        this.visitDate = visitDate;
    }

    public List<VisitorsBean> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<VisitorsBean> visitors) {
        this.visitors = visitors;
    }
}
