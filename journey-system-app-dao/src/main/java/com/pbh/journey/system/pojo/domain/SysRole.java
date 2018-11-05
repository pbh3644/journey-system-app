package com.pbh.journey.system.pojo.domain;

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:SysRole
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-5
 */
@Data
public class SysRole extends BaseEntity<SysRole> {
    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色代码
     */
    private String roleCode;
    /**
     * 角色描述
     */
    private String roleDesc;
    /**
     * 系统code
     */
    private String systemCode;
    /**
     * 状态，1启用，2禁用
     */
    private Integer roleState;

    public SysRole() {
    }


}
