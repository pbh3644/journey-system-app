package com.pbh.journey.system.app.config.security.service.impl;

import com.pbh.journey.system.app.config.security.service.SysUserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author pangbohuan
 * @description 安全服务用户类
 * @date 2018-09-14 11:20
 **/
public class SysUserDetailsImpl implements SysUserDetails {

    /**
     * 用户ID
     */
    private final String id;

    /**
     * 用户账号
     */
    private final String account;

    /**
     * 用户密码
     */
    private final String password;

    /**
     * 角色集合
     */
    private final Collection<? extends GrantedAuthority> authorities;

    SysUserDetailsImpl(String id, String account, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
