package com.potevio.xacp.api.common.utils;

/**
 * Description: API服务工具类
 * Author: peng.zhang
 * Date: 2018/8/7  23:11
 */
public class ServiceUtils {

    /**
     * 创建长度为6的随机数字字符串
     * @return 6位长度的随机数字字符串
     */
    public static String createReservationCode() {
        int codeTail = (int) (Math.random() * 100000);
        codeTail = codeTail > 10000 ? codeTail : codeTail + 10000;
        return "" + (int) (Math.random() * 10) + codeTail;
    }

}
