package com.pbh.journey.system.app.config.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author pangbohuan
 * @description serDetailsService实现类
 * @date 2018-09-14 10:31
 **/
@Service("userDetailsService")
public class SysUserDetailsServiceImpl implements UserDetailsService {

    /**
     * 根据用户账号返回用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String sysUserAccount) throws UsernameNotFoundException {
        return null;
    }
}
