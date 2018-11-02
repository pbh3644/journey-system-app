package com.pbh.journey.system.pojo.dto;

import com.pbh.journey.system.pojo.domain.SysUser;
import lombok.Data;

/**
 * DTO:SysUserDTO
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
@Data
public class SysUserDTO extends SysUser {

    /**
     * 登录账号
     */
    private String userAccount;

    /**
     * 登录类型，1手机号,2微信小程序，3微博，4账号OOS,5系统登录
     */
    private Byte type;

    /**
     * 登录账号是否被冻结
     */
    private Byte loginNoDeleteFlag;

    /**
     * 用户密码
     */
    private String userPwd;

    public SysUserDTO() {
    }
}
