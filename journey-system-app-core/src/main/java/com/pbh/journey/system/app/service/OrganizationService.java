package com.pbh.journey.system.app.service;

import com.pbh.journey.system.common.base.service.BaseService;
import com.pbh.journey.system.pojo.domain.Organization;

/**
 * Service Interface:OrganizationService
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-9
 */
public interface OrganizationService extends BaseService<Organization> {

    /**
     * 根据数据中心的表名和微服务的ID精准查询是否有相同的表名字
     *
     * @param organization 包含表名和微服务ID
     * @return Organization 数据中心对象
     */
    Organization uniquenessOrganizationName(Organization organization);
}
