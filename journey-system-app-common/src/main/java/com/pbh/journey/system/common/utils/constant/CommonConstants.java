package com.pbh.journey.system.common.utils.constant;

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


    /**
     * 逻辑删除状态
     */
    public static final byte DELETE_FLAG_NORMAL = 1;
    public static final byte DELETE_FLAG_FREAK = 2;

    /**
     * 登录类型 1手机号,2微信小程序，3微博，4账号OOS,5系统登录
     */
    public static final byte LOGIN_MOBILE = 1;
    public static final byte LOGIN_WEIXIN = 2;
    public static final byte LOGIN_WEIBO = 3;
    public static final byte LOGIN_OOS = 4;
    public static final byte LOGIN_SYSTEM = 5;

    /**
     * 整数类型默认为0状态
     */
    public static final byte ZERO = 0;

    /**
     * 报文头 token 名字
     */
    public static final String TOKEN = "accountToken";

    /**
     * 身份证号码18位数
     */
    public static final byte ID_CARD_DIGIT = 18;

    /**
     * 默认初始密码为lx123456
     */
    public static final String DEFAULT_PWD = "lx123456";
}
