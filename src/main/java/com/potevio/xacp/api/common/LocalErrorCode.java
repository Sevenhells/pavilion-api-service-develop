package com.potevio.xacp.api.common;

/**
 * Description: 错误码
 * Author: peng.zhang
 * Date: 2018/8/11  1:14
 */
public class LocalErrorCode {

    // common: 通用
    public static final String COMMON_EXCEPTION = "common_1001";
    public static final String COMMON_USER_NULL = "common_1002";
    public static final String COMMON_PARAMS_ERROR = "common_1003";

    // reservation: 预约码
    public static final String RESERVATION_REIDS_NOT_EXIST = "reservation_1001";
    public static final String RESERVATION_MYSQL_NOT_EXIST = "reservation_1002";

    // admin: 管理员
    public static final String ADMIN_ACCOUNT_PASSWORD_ERROR = "admin_1001";
    public static final String ADMIN_ACCOUNT_CANNOT_USE = "admin_1002";
    public static final String ADMIN_ACCOUNT_NOT_EXIST = "admin_1003";
    public static final String ADMIN_GROUP_HANDLE_ERROR = "admin_1004";
    public static final String ADMIN_GROUP_HANDLE_IDS_NULL = "admin_1005";
    public static final String ADMIN_MENUS_HANDLE_ERROR = "admin_1006";
    public static final String ADMIN_MENUS_HANDLE_IDS_NULL = "admin_1007";
    public static final String ADMIN_POSTS_HANDLE_ERROR = "admin_1008";
    public static final String ADMIN_POSTS_HANDLE_IDS_NULL = "admin_1009";
    public static final String ADMIN_RULES_HANDLE_ERROR = "admin_1010";
    public static final String ADMIN_RULES_HANDLE_IDS_NULL = "admin_1011";
    public static final String ADMIN_STRUCTURES_HANDLE_ERROR = "admin_1012";
    public static final String ADMIN_STRUCTURES_HANDLE_IDS_NULL = "admin_1013";
    public static final String ADMIN_USERS_HANDLE_ERROR = "admin_1014";
    public static final String ADMIN_USERS_HANDLE_IDS_NULL = "admin_1015";
    public static final String ADMIN_USER_NOT_LOGIN = "admin_1016";
    public static final String ADMIN_USER_ORIGINAL_PASSWORD_ERROR = "admin_1017";

    // interceptor: 拦截器
    public static final String INTERCEPTOR_AUTHKEY_SESSIONID_NULL = "interceptor_1001";
    public static final String INTERCEPTOR_ACCOUNT_CANNOT_USE = "interceptor_1002";

    // device
    public static final String DEVICE_HANDLE_ERROR = "device_1001";

    // score
    public static final String SCODE_HANDLE_ERROR = "score_1001";
    public static final String SCODE_IS_NOT_ENOUGH = "score_1002";

    // trees
    public static final String TREES_TREE_NOT_FOUND = "trees_1001";
    public static final String TREES_CREATE_ORDER_ERROR = "trees_1002";

    // user
    public static final String USER_FREQUENT_CONTACTS_OVER = "user_1001";
    public static final String USER_FREQUENT_CONTACTS_REPEAT = "user_1002";
    public static final String USER_HANDLE_ERROR= "user_1003";

}
