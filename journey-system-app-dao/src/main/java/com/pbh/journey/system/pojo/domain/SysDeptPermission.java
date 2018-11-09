package com.pbh.journey.system.pojo.domain;

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:SysDeptPermission
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-9
 */
@Data
public class SysDeptPermission extends BaseEntity<SysDeptPermission> {
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 权限ID
     */
    private Long permissionId;

    public SysDeptPermission() {
    }


}
