package com.pbh.journey.system.app.mapper;

import com.pbh.journey.system.common.base.mapper.BaseMapper;
import com.pbh.journey.system.pojo.domain.SysDeptPermission;
import com.pbh.journey.system.pojo.domain.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Dao Interface:SysDeptPermissionMapper
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-9
 */
@Mapper
public interface SysDeptPermissionMapper extends BaseMapper<SysDeptPermission> {

    /**
     * 根据部门ID查询这个部门所有的权限
     *
     * @param deptId 部门ID
     * @return 权限集合
     */
    List<SysPermission> deptOfPermission(long deptId);
}
