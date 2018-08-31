package com.cmrh.journey.system.app.service;

import com.cmrh.journey.system.app.pojo.SysUser;

import java.util.List;

/**
 * @author pangbohuan
 * @description:SysUserService
 * @date 2018-07-25 17:30
 **/
public interface SysUserService {

    /**
     * 增加用户
     *
     * @param user
     */
    void saveUser(SysUser user);

    /**
     * 修改用户
     *
     * @param user
     */
    void updateUser(SysUser user);

    /**
     * 删除用户
     *
     * @param userId
     */
    void deleteUser(String userId);

    /**
     * 查询单个用户
     *
     * @param userId
     * @return SysUser
     */
    SysUser queryUserById(String userId);

    /**
     * 查询用户列表分页
     *
     * @param user
     * @param page
     * @param pageSize
     * @return List<SysUser>
     */
    List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

    /**
     * MD5加密
     *
     * @param password
     * @return String
     */
    String userPasswordEncrypt(String password);

    /**
     * 查询用户列表
     *
     * @return List<SysUser>
     */
    List<SysUser> selectAll();

    /**
     * 查询用户总数量
     *
     * @param user
     * @return int
     */
    int selectCount(SysUser user);


}
