package com.pbh.journey.system.app.service;

import com.pbh.journey.system.common.base.service.BaseService;
import com.pbh.journey.system.pojo.domain.SysUser;

/**
 * Service Interface:SysUserService
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 通过用户手机号码查询用户
     *
     * @param mobile 手机号
     * @return SysUser
     */
    SysUser mobileGetSysUser(String mobile);


    /**
     * 通过用户身份证号码查询用户
     *
     * @param idCard 身份证号
     * @return SysUser
     */
    SysUser idCardGetSysUser(String idCard);


    /**
     * 通过用户邮箱查询用户
     *
     * @param mailbox 邮箱号码
     * @return SysUser
     */
    SysUser mailboxGetSysUser(String mailbox);
}
