package com.pbh.journey.system.pojo.domain;

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * entity:SysUser
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
@Data
public class SysUser extends BaseEntity<SysUser> {
    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 邮箱号
     */
    private String mailbox;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 当前状态，1正常，2禁止登录
     */
    private Integer state;
    /**
     * 最近修改密码时间
     */
    private Date lastPwdmodTime;
    /**
     * 设备Id
     */
    private String deviceId;

    public SysUser() {
    }


}
