package com.cmrh.journey.system.common.utils.constant;

/**
 * @author pangbohuan
 * @description 常量池
 * @date 2018-08-20 12:08
 **/
public class CommonConstants {
    /**
     * 用户token异常
     */
    public static final Integer EX_USER_INVALID_CODE = 40101;
    public static final Integer EX_USER_PASS_INVALID_CODE = 40001;
    /**
     * 客户端token异常
     */
    public static final Integer EX_CLIENT_INVALID_CODE = 40301;
    public static final Integer EX_CLIENT_FORBIDDEN_CODE = 40331;
    public static final Integer EX_OTHER_CODE = 500;


    /**
     * Application和Organizations类ID自增长
     * 不需要雪花ID生成，方便后期查看
     */
    public static final String APPLICATION_CLASS_NAME = "Application";
    public static final String ORGANIZATION_CLASS_NAME = "Organizations";
}
