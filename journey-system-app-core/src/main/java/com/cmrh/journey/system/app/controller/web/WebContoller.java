package com.cmrh.journey.system.app.controller.web;

import com.cmrh.journey.system.app.cachetools.UserCache;
import com.cmrh.journey.system.app.pojo.SysUser;
import com.cmrh.journey.system.common.org.n3r.idworker.Sid;
import com.cmrh.journey.system.common.result.JourneySystemAppResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author pangbohuan
 * @description:所有的Contoller的父类
 * @date 2018-07-25 17:30
 **/
@RestController
public class WebContoller {

    /**
     * 列表分页
     */
    protected static Integer pageSize = 10;


    @Resource
    protected HttpServletRequest request;


    @Resource
    protected HttpServletResponse response;


    @Resource
    UserCache userCache;

    /**
     * 获取唯一ID
     */
    protected String getId() {
        String soleId = Sid.nextShort();
        return soleId;
    }


    /**
     * 获取session
     */
    protected HttpSession getSession() {
        return request.getSession();
    }

    /**
     * 获取当前登录用户
     */
    protected SysUser getUser() {
        SysUser user = (SysUser) getSession().getAttribute("sysUser");
        return user;
    }


    /**
     * 获取当前登录用户ID
     */
    protected String getUserId() {
        return getUser().getId();
    }

    /**
     * 获取当前登录用户姓名
     */
    protected String getUserName() {
        return getUser().getSysUserName();
    }


    /**
     * 登录判断
     */
    @PostMapping("/login")
    public JourneySystemAppResult login(SysUser sysUser) {
        SysUser user = userCache.isUser(sysUser);
        boolean index = false;
        if (user != null) {
            index = true;
            getSession().setAttribute("sysUser", user);
        }
        return JourneySystemAppResult.ok(index);
    }
}
