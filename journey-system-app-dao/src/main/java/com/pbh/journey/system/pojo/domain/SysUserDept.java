package com.pbh.journey.system.pojo.domain;

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:SysUserDept
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-9
 */
@Data
public class SysUserDept extends BaseEntity<SysUserDept> {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 部门ID
     */
    private Long deptId;

    public SysUserDept() {
    }


}
