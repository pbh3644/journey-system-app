package com.pbh.journey.system.app.service;

import com.pbh.journey.system.common.base.service.BaseService;
import com.pbh.journey.system.pojo.domain.SysDeptPermission;
import com.pbh.journey.system.pojo.domain.SysPermission;

import java.util.List;

/**
 * Service Interface:SysDeptPermissionService
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-9
 */
public interface SysDeptPermissionService extends BaseService<SysDeptPermission> {

    /**
     * 根据部门ID查询这个部门所有的权限
     *
     * @param deptId 部门ID
     * @return 权限集合
     */
    List<SysPermission> deptOfPermission(long deptId);
}
