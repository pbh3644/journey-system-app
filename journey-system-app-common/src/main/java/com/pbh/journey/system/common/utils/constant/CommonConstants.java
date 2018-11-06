package com.pbh.journey.system.common.utils.constant;

/**
 * @author pangbohuan
 * @description 常量池
 * @date 2018-08-20 12:08
 **/
public class CommonConstants {

    /**
     * 系统环境变量
     */
    public static final String SYSTEM_CODE = "journey-system-app";

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
     * 用户冻结、正常状态
     */
    public static final byte USER_NORMAL = 1;
    public static final byte USER_FREEZE = 2;


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
     * 默认初始登录账号是密码为lx123456
     */
    public static final String DEFAULT_PWD = "lx123456";

    /**
     * 部门code递增数字
     */
    public static final String DEPT_LOG = "DEPT-";
    public static final byte DEPT_CODE_PROGRESSIVE = 1;

    /**
     * 角色code递增数字
     */
    public static final String ROLE_LOG = "ROLE-";
    public static final byte ROLE_CODE_PROGRESSIVE = 1;
}
