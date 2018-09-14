package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.OrganizationMapper;
import com.pbh.journey.system.app.service.OrganizationService;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.pojo.domain.Organization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ServiceImpl:OrganizationServiceImpl
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-9
 */
@Service("organizationService")
@Slf4j
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Resource
    private OrganizationMapper organizationMapper;

    @Override
    public void insert(Organization organization) {
        Long applicationId = organization.getApplicationId();
        String organizationDataName = organization.getOrganizationDataName();
        if (organizationMapper.uniquenessOrganizationName(organization) != null) {
            log.error("增加表失败！这个微服务已存在相同的表名,OrganizationServiceImpl.33行。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
            new BussinessException("增加表失败！这个微服务已存在相同的表名,不允许增加.微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
        }
        super.insert(organization);
        log.info("增加表成功！微服务的ID为" + applicationId + "表名为:" + organizationDataName);
    }

    @Override
    public void insertBatch(List<Organization> list) {
        for (Organization organization : list) {
            if (organizationMapper.uniquenessOrganizationName(organization) != null) {
                Long applicationId = organization.getApplicationId();
                String organizationDataName = organization.getOrganizationDataName();
                log.error("批量增加表失败！这个微服务下已经存在有这个表名字。OrganizationServiceImpl.46行。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
                new BussinessException("批量增加表失败！这个微服务下已经存在有这个表名字，不允许批量增加。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
            }
        }
        super.insertBatch(list);
        log.info("批量增加表成功！这批表的信息为：" + list.toString());
    }

    @Override
    public void update(Organization organization) {
        Long applicationId = organization.getApplicationId();
        String organizationDataName = organization.getOrganizationDataName();
        if (organizationMapper.uniquenessOrganizationName(organization) != null) {
            log.error("修改表失败！这个微服务已存在相同的表名,OrganizationServiceImpl.59行。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
            new BussinessException("修改表失败！这个微服务已存在相同的表名,不允许修改.微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
        }
        super.update(organization);
        log.info("修改表成功！微服务的ID为" + applicationId + "表名为:" + organizationDataName);
    }

    @Override
    public void updateBatch(List<Organization> list) {
        for (Organization organization : list) {
            if (organizationMapper.uniquenessOrganizationName(organization) != null) {
                Long applicationId = organization.getApplicationId();
                String organizationDataName = organization.getOrganizationDataName();
                log.error("批量修改表失败！这个微服务下已经存在有这个表名字。OrganizationServiceImpl.72行。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
                new BussinessException("批量修改表失败！这个微服务下已经存在有这个表名字，不允许批量修改。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
            }
        }
        super.updateBatch(list);
        log.info("批量修改表成功！这批表的信息为：" + list.toString());
    }
}
