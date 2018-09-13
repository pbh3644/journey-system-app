package com.cmrh.journey.system.app.mapper;

import com.cmrh.journey.system.common.base.mapper.BaseMapper;
import com.cmrh.journey.system.pojo.domain.Organization;
import org.apache.ibatis.annotations.Mapper;

/**
 * Dao Interface:OrganizationMapper
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-9
 */
@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {
    /**
     * 根据数据中心的表名和微服务的ID精准查询是否有相同的表名字
     *
     * @param organization 包含表名和微服务ID
     * @return Organization 数据中心对象
     */
    Organization uniquenessOrganizationName(Organization organization);
}
