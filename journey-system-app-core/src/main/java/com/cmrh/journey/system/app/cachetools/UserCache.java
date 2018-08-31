package com.cmrh.journey.system.app.cachetools;


import com.cmrh.journey.system.app.pojo.SysUser;
import com.cmrh.journey.system.app.service.SysUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pangbohuan
 * @description 用户账号密码的缓存
 */
@Configuration
public class UserCache implements InitializingBean {

    private final static int PAST_TIME = 1000 * 10;

    List<SysUser> list = new ArrayList();

    @Resource
    SysUserService sysUserService;

    long time = System.currentTimeMillis();

    /**
     * 提供最新的用户列表
     */
    public List<SysUser> getSysUserList() {
        updateList();
        return list;
    }

    /**
     * 实现Springd的InitializingBean接口
     * 初始化调用afterPropertiesSet方法
     */
    @Override
    public void afterPropertiesSet() {
        updateList();
    }

    /**
     * 更新LIST数据
     */
    public void updateList() {
        list = sysUserService.selectAll();
    }

    /**
     * 判断用户账号密码是否正确
     */
    public SysUser isUser(SysUser user) {
        refresh();
        int sysUserNumber = user.getSysUserNumber();
        for (SysUser users : list) {
            if (users.getSysUserNumber() == sysUserNumber) {
                String decode = sysUserService.userPasswordEncrypt(user.getSysUserPassword());
                if (users.getSysUserPassword().equals(decode)) {
                    return users;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 判断10分钟到了刷新缓存
     */
    public void refresh() {
        if (System.currentTimeMillis() - time > PAST_TIME) {
            time = System.currentTimeMillis();
            updateList();
        }
    }

}
