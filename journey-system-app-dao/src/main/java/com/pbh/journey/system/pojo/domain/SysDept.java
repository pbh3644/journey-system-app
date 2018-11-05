package com.pbh.journey.system.pojo.domain;

import com.pbh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:SysDept
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-5
 */
@Data
public class SysDept extends BaseEntity<SysDept> {
    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门代码
     */
    private String deptCode;
    /**
     * 部门描述
     */
    private String deptDesc;
    /**
     * 系统code
     */
    private String systemCode;
    /**
     * 状态，1启用，2禁用
     */
    private Integer deptState;

    public SysDept() {
    }


}
