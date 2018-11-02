package com.pbh.journey.system.pojo.domain;

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:LoginNo
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
@Data
public class LoginNo extends BaseEntity<LoginNo> {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录账号
     */
    private String userAccount;

    /**
     * 登录密码
     */
    private String userPwd;

    /**
     * 登录类型，1手机号,2微信小程序，3微博，4账号OOS,5系统登录
     */
    private Byte type;

    /**
     * 微信unionId
     */
    private String wechatUnionId;

    public LoginNo() {
    }


}
