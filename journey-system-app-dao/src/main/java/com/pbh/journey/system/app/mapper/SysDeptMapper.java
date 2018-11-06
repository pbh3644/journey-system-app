package com.pbh.journey.system.app.mapper;

import com.pbh.journey.system.common.base.mapper.BaseMapper;
import com.pbh.journey.system.pojo.domain.SysDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * Dao Interface:SysDeptMapper
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-5
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
    /**
     * 根据部门名获取部门对象
     *
     * @param deptName 部门名字
     * @return SysDept 部门对象
     */
    SysDept nameGetDept(String deptName);

    /**
     * 启用禁用部门
     *
     * @param sysDept 部门ID,启用禁用状态
     */
    void switchDept(SysDept sysDept);

}
