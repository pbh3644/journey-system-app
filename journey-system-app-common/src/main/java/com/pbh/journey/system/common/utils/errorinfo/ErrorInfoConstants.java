package com.pbh.journey.system.common.utils.errorinfo;

/**
 * @author pangbohuan
 * @description 所有错误信息的提示
 * @date 2018-11-05 15:06
 **/
public class ErrorInfoConstants {

    /**
     * 系统错误
     */
    public static final String SYSTEM_ERROR = "系统错误请稍后重试";
    public static final String ID_NOT_NULL = "ID不能为空";

    /**
     * BaseServiceImpl 的错误信息
     */
    public static final String SAVE_ERROR = "操作失败";
    public static final String UPDATE_ERROR = "修改失败";
    public static final String DELETE_ERROR = "删除失败";

    /**
     * SysUserServiceImpl 的错误信息
     */
    public static final String PLEASE_ENTER_SYSTEM_NICKNAME = "请填写系统昵称";
    public static final String PLEASE_ENTER_REAL_NAME = "请填写真实姓名";
    public static final String PLEASE_ENTER_ID_CARD = "请输入正确的身份证号码";
    public static final String PLEASE_ENTER_MAILBOX = "请输入正确的邮箱号";
    public static final String PLEASE_ENTER_MOBILE = "请输入正确的手机号";
    public static final String MOBILE_REPETITION = "这个号码已存在不允许填写重复的";

    /**
     * LoginNoServiceImpl 的错误信息
     */
    public static final String PLEASE_ENTER_PASSWORD = "请输入登录密码";
    public static final String PLEASE_RELEVANCE_USER_ID = "请先关联用户ID";
    public static final String MOBILE_NO_REPETITION = "输入的账号不存在";
    public static final String ACCOUNT_FREEZE = "这个账号已经被冻结,如需解冻请联系管理员";
    public static final String PASSWORD_INCORRECTNESS = "输入的密码错误";
    public static final String LOGIN_CONFLICT = "这个账号正在登录,不允许再重复登录";
    public static final String NO_TOKEN = "用户token不能为空";
    public static final String NO_LOGIN = "该用户并没有登录";
    public static final String PLEASE_ENTER_USER_ACCOUNT = "请输入账号";
    public static final String PLEASE_SELECT_LOGIN_TYPE = "请选择账号登录类型";
    public static final String PLEASE_ENTER_ORIGINAL_PASSWORD = "请输入原密码";
    public static final String PLEASE_ENTER_NEW_PASSWORD = "请输入新密码";
    public static final String PLEASE_ENTER_AGAIN_NEW_PASSWORD = "请再次输入新密码";
    public static final String NEW_AND_AGAIN_PASSWORD_DIFFER = "新密码输入的两次不一致,请重新输入";

    /**
     * ApplicationServiceImpl 的错误信息
     */
    public static final String APPLICATION_NAME_REPETITION = "微服务的名字重复，不允许填写重复的";

    /**
     * OrganizationServiceImpl 的错误信息
     */
    public static final String APPLICATION_AND_TABLE_NAME_REPETITION = "这个微服务已存在相同的表名,不允许填写重复的";

    /**
     * SysDeptServiceImpl 的错误信息
     */
    public static final String DEPT_NAME_REPETITION = "部门的名字重复，不允许填写重复的";

    /**
     * SysRoleServiceImpl 的错误信息
     */
    public static final String ROLE_NAME_REPETITION = "角色的名字重复，不允许填写重复的";

}
