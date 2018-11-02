package com.pbh.journey.system.app.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.pbh.journey.system.common.utils.util.CurrentUserUtils;
import com.pbh.journey.system.pojo.dto.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author pangbohuan
 * @description:所有的Contoller的父类
 * @date 2018-07-25 17:30
 **/
@Slf4j
@RestController
public class WebContoller {


    @Resource
    protected HttpServletRequest request;


    @Resource
    protected HttpServletResponse response;


    /**
     * 获取当前登录用户
     */
    protected SysUserDTO getUser() {
        SysUserDTO user = (SysUserDTO) JSONObject.parse(CurrentUserUtils.getCurrentUserJson());
        return user;
    }


    /**
     * 获取当前登录用户ID
     */
    protected long getUserId() {
        return getUser().getId();
    }

    /**
     * 获取当前登录用户真实姓名
     */
    protected String getUserName() {
        return getUser().getRealName();
    }
}
