package com.pbh.journey.system.app.service;

import com.pbh.journey.system.common.base.service.BaseService;
import com.pbh.journey.system.pojo.domain.SysRole;

/**
 * Service Interface:SysRoleService
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-5
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 根据角色名获取角色对象
     *
     * @param roleName 角色名字
     * @return SysDept 角色对象
     */
    SysRole nameGetRole(String roleName);


    /**
     * 启用禁用角色
     *
     * @param sysRole 角色ID,启用禁用状态
     */
    void switchRole(SysRole sysRole);
}
