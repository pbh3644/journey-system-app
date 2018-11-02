package com.pbh.journey.system.pojo.domain;

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:LoginLog
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
@Data
public class LoginLog extends BaseEntity<LoginLog> {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID userId
     */
    private Long userId;

    /**
     * 登录账号
     */
    private String userAccount;

    /**
     * 系统code
     */
    private String systemCode;

    public LoginLog() {
    }

}
