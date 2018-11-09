package com.pbh.journey.system.pojo.domain;

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:SysRolePermission
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-9
 */
@Data
public class SysRolePermission extends BaseEntity<SysRolePermission> {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 权限ID
     */
    private Long permissionId;

    public SysRolePermission() {
    }


}
