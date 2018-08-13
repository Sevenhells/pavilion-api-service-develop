package com.potevio.xacp.api.common.utils;

import org.junit.Test;

/**
 * Description:
 * Author: peng.zhang
 * Date: 2018/8/9  1:59
 * Version: 1.0
 */
public class ServiceUtilsTest {

    @Test
    public void testCreateReservationCode() {
        for (int i = 0; i < 20; i++) {
            System.out.println(ServiceUtils.createReservationCode());
        }
    }

}
