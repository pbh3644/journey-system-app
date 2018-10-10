package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.OrganizationMapper;
import com.pbh.journey.system.app.service.OrganizationService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.pojo.domain.Organization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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

    /**
     * 查询全部微服务列表
     */
    @Override
    @Cacheable("OrganizationServiceImpl")
    public List<Organization> findAll() {
        return super.findAll();
    }

    /**
     * 根据查询条件查询微服务列表分页
     */
    @Override
    @Cacheable(value = "OrganizationServiceImpl", key = "#organization")
    public Page<Organization> findPage(Organization organization) {
        return super.findPage(organization);
    }

    @Override
    @CachePut(value = "OrganizationServiceImpl", key = "#organization.id")
    public void insert(Organization organization) {
        Long applicationId = organization.getApplicationId();
        String organizationDataName = organization.getOrganizationDataName();
        if (organizationMapper.uniquenessOrganizationName(organization) != null) {
            log.error("增加表失败！这个微服务已存在相同的表名,OrganizationServiceImpl。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
            throw new BussinessException("增加表失败！这个微服务已存在相同的表名,不允许增加.微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
        }
        organization.setAddTime(organization.currentTime());
        organization.setAddUserId(123456L);
        super.insert(organization);
        log.info("增加表成功！微服务的ID为" + applicationId + "表名为:" + organizationDataName);
    }

    @Override
    @CacheEvict(value = "OrganizationServiceImpl", allEntries = true)
    public void insertBatch(List<Organization> list) {
        for (Organization organization : list) {
            if (organizationMapper.uniquenessOrganizationName(organization) != null) {
                Long applicationId = organization.getApplicationId();
                String organizationDataName = organization.getOrganizationDataName();
                log.error("批量增加表失败！这个微服务下已经存在有这个表名字。OrganizationServiceImpl。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
                throw new BussinessException("批量增加表失败！这个微服务下已经存在有这个表名字，不允许批量增加。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
            }
            organization.setAddTime(organization.currentTime());
            organization.setAddUserId(123456L);
        }
        super.insertBatch(list);
        log.info("批量增加表成功！这批表的信息为：" + list.toString());
    }

    @Override
    @CachePut(value = "OrganizationServiceImpl", key = "#organization.id")
    public void update(Organization organization) {
        Long applicationId = organization.getApplicationId();
        String organizationDataName = organization.getOrganizationDataName();
        if (organizationMapper.uniquenessOrganizationName(organization) != null) {
            log.error("修改表失败！这个微服务已存在相同的表名,OrganizationServiceImpl。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
            throw new BussinessException("修改表失败！这个微服务已存在相同的表名,不允许修改.微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
        }
        organization.setUpdateTime(organization.currentTime());
        organization.setUpdateUserId(123456L);
        super.update(organization);
        log.info("修改表成功！微服务的ID为" + applicationId + "表名为:" + organizationDataName);
    }

    @Override
    @CacheEvict(value = "OrganizationServiceImpl", allEntries = true)
    public void updateBatch(List<Organization> list) {
        for (Organization organization : list) {
            if (organizationMapper.uniquenessOrganizationName(organization) != null) {
                Long applicationId = organization.getApplicationId();
                String organizationDataName = organization.getOrganizationDataName();
                log.error("批量修改表失败！这个微服务下已经存在有这个表名字。OrganizationServiceImpl。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
                throw new BussinessException("批量修改表失败！这个微服务下已经存在有这个表名字，不允许批量修改。微服务的ID为：" + applicationId + "表名为:" + organizationDataName);
            }
            organization.setUpdateTime(organization.currentTime());
            organization.setUpdateUserId(123456L);
        }
        super.updateBatch(list);
        log.info("批量修改表成功！这批表的信息为：" + list.toString());
    }

    /**
     * 根据单个ID物理删除
     */
    @Override
    @CacheEvict(value = "OrganizationServiceImpl", key = "#id")
    public void delete(long id) {
        super.delete(id);
        log.info("物理删除表成功：这个表的id为：" + id);
    }

    /**
     * 根据单个ID逻辑删除
     */
    @Override
    @CacheEvict(value = "OrganizationServiceImpl", key = "#id")
    public void deleteLogic(long id) {
        super.deleteLogic(id);
        log.info("逻辑删除表成功：这个表的id为：" + id);
    }

    /**
     * 根据批量ID逻辑删除
     */
    @Override
    @CacheEvict(value = "OrganizationServiceImpl", allEntries = true)
    public void deleteBatch(long[] ids) {
        super.deleteBatch(ids);
        log.info("批量逻辑删除表成功：这批表的id为：" + ids);
    }


    /**
     * 根据ID获取对象
     */
    @Override
    @Cacheable(value = "OrganizationServiceImpl", key = "#id")
    public Organization get(long id) {
        return super.get(id);
    }
}
