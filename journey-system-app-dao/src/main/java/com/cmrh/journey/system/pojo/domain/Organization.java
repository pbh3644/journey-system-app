package com.cmrh.journey.system.pojo.domain;

import com.cmrh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:Organization
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-8
 */
@Data
public class Organization extends BaseEntity<Organization> {
    private static final long serialVersionUID = 1L;

    /**
     * 设置主键ID
     */
    private Long organizationId;
    /**
     * 服务系统的机构ID
     */
    private Long applicationId;

    /**
     * 数据中心ID
     */
    private Long organizationDataId;

    /**
     * 数据中心名字
     */
    private String organizationDataName;

}
