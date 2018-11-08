package com.pbh.journey.system.pojo.domain;

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:SysPermission
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-7
 */
@Data
public class SysPermission extends BaseEntity<SysPermission> {
    private static final long serialVersionUID = 1L;


    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 是否菜单1是 2不是
     */
    private Integer isMenu;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 不可匿名访问的地址
     */
    private String permissionUrl;
    /**
     * 权限描述
     */
    private String permissionDesc;
    /**
     * 系统code
     */
    private String systemCode;
    /**
     * 是否校验csrf
     */
    private Integer needCsrf;
    /**
     * 优先级，同level大的在上
     */
    private Integer order;
    /**
     * 权限类型
     */
    private String permissionType;

    public SysPermission() {
    }


}
