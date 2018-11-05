package com.pbh.journey.system.app.mapper;

import com.pbh.journey.system.common.base.mapper.BaseMapper;
import com.pbh.journey.system.pojo.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * Dao Interface:SysRoleMapper
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-5
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据角色名获取角色对象
     *
     * @param roleName 角色名字
     * @return SysDept 角色对象
     */
    SysRole nameGetRole(String roleName);
}
