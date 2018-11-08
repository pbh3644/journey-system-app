package com.pbh.journey.system.app.service;

import com.pbh.journey.system.common.base.service.BaseService;
import com.pbh.journey.system.pojo.domain.LoginNo;
import com.pbh.journey.system.pojo.dto.LoginNoDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Service Interface:LoginNoService
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
public interface LoginNoService extends BaseService<LoginNo> {

    /**
     * 用户登录-通过账号密码登录
     * 登录成功生成token,把用户信息查询出来放到redis中(key=token,val=sysUser)
     * 并且返回token给用户
     *
     * @param loginNo
     * @return String token
     */
    String login(LoginNo loginNo);

    /**
     * 用户登出
     * 通过request获取报文头token
     * 删除redis中缓存的用户信息
     *
     * @param request
     */
    void logout(HttpServletRequest request);

    /**
     * 根据登录账号和登录类型查询账号是否存在
     *
     * @param loginNo LoginNo
     * @return LoginNo 登录账号表对象
     */
    LoginNo loginNoExist(LoginNo loginNo);

    /**
     * 修改密码
     *
     * @param loginNoDTO
     */
    void updatePwd(LoginNoDTO loginNoDTO);

    /**
     * 修改登录账号，根据手机号修改
     *
     * @param loginNoDTO 手机号和登录类型
     */
    void updateUserAccount(LoginNoDTO loginNoDTO);
}
