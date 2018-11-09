package com.pbh.journey.system.pojo.domain;

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:SysUserPermission
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-9
 */
@Data
public class SysUserPermission extends BaseEntity<SysUserPermission> {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 权限ID
     */
    private Long permissionId;

    public SysUserPermission() {
    }


}
