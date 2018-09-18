package com.pbh.journey.system.app.config.security.service;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author pangbohuan
 * @description 系统用户明细信息类
 * @date 2018-09-14 10:33
 **/
public interface SysUserDetails extends Serializable {

    /**
     * 返回分配给用户的角色列表
     *
     * @return Collection
     */
    Collection<? extends GrantedAuthority> getAuthorities();

    /**
     * 返回密码
     *
     * @return String
     */
    String getPassword();

    /**
     * 返回帐号
     *
     * @return String
     */
    String getUsername();

    /**
     * 账户是否未过期
     *
     * @return boolean
     */
    boolean isAccountNonExpired();

    /**
     * 账户是否未锁定
     *
     * @return boolean
     */
    boolean isAccountNonLocked();

    /**
     * 密码是否未过期
     *
     * @return boolean
     */
    boolean isCredentialsNonExpired();

    /**
     * 账户是否激活
     *
     * @return boolean
     */
    boolean isEnabled();
}
