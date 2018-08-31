package com.cmrh.journey.system.common.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pangbohuan
 * @description:SysUser
 * @date 2018-07-25 17:30
 **/
@Data
@Table(name = "sys_user")
public class SysUser {
    /**
     * 系统用户ID
     */
    @Id
    private String id;

    /**
     * 系统用户名
     */
    @Column(name = "sys_user_name")
    private String sysUserName;

    /**
     * 系统用户性别
     */
    @Column(name = "sys_user_sex")
    private String sysUserSex;

    /**
     * 系统用户年龄
     */
    @Column(name = "sys_user_age")
    private Integer sysUserAge;

    /**
     * 系统用户登录账号
     */
    @Column(name = "sys_user_number")
    private Integer sysUserNumber;

    /**
     * 系统用户登录密码
     */
    @Column(name = "sys_user_password")
    private String sysUserPassword;

    /**
     * 系统用户邮箱
     */
    @Column(name = "sys_user_mailbox")
    private String sysUserMailbox;
}