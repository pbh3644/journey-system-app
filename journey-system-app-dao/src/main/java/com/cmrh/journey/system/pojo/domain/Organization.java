package com.cmrh.journey.system.pojo.domain;

import com.cmrh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:Organization
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-9
 */
@Data
public class Organization extends BaseEntity<Organization> {
    private static final long serialVersionUID = 1L;

    /**
     * 服务系统的机构ID
     */
    private Long applicationId;
    /**
     * 表名
     */
    private String organizationDataName;
}
