package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.OrganizationMapper;
import com.pbh.journey.system.app.service.OrganizationService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.pojo.domain.Organization;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * 查询全部数据库表列表
     */
    @Override
    @Cacheable("OrganizationServiceImpl")
    public List<Organization> findAll() {
        return super.findAll();
    }

    /**
     * 根据查询条件查询数据库表列表分页
     */
    @Override
    @Cacheable(value = "OrganizationServiceImpl", key = "#organization")
    public Page<Organization> findPage(Organization organization) {
        return super.findPage(organization);
    }

    /**
     * 增加数据库表
     */
    @Override
    @CachePut(value = "OrganizationServiceImpl", key = "#organization.id")
    public void insert(Organization organization) {
        addOrganizationCheckout(organization);
        super.insert(organization);
    }

    /**
     * 批量增加数据库表
     */
    @Override
    @CacheEvict(value = "OrganizationServiceImpl", allEntries = true)
    public void insertBatch(List<Organization> list) {
        organizationNameWeight(list);
        for (Organization organization : list) {
            addOrganizationCheckout(organization);
        }
        super.insertBatch(list);
    }

    /**
     * 修改数据库表
     */
    @Override
    @CachePut(value = "OrganizationServiceImpl", key = "#organization.id")
    public void update(Organization organization) {
        updateOrganizationCheckout(organization);
        super.update(organization);
        log.warn("修改表成功！微服务的ID为" + organization.getApplicationId() + "表名为:" + organization.getOrganizationDataName());
    }

    /**
     * 批量修改数据库表
     */
    @Override
    @CacheEvict(value = "OrganizationServiceImpl", allEntries = true)
    public void updateBatch(List<Organization> list) {
        organizationNameWeight(list);
        for (Organization organization : list) {
            updateOrganizationCheckout(organization);
        }
        super.updateBatch(list);
        log.warn("批量修改表成功！这批表的信息为：" + list.toString());
    }

    /**
     * 根据单个ID物理删除
     */
    @Override
    @CacheEvict(value = "OrganizationServiceImpl", key = "#id")
    public void delete(long id) {
        super.delete(id);
        log.warn("物理删除表成功：这个表的id为：" + id);
    }

    /**
     * 根据单个ID逻辑删除
     */
    @Override
    @CacheEvict(value = "OrganizationServiceImpl", key = "#id")
    public void deleteLogic(long id) {
        super.deleteLogic(id);
        log.warn("逻辑删除表成功：这个表的id为：" + id);
    }

    /**
     * 根据批量ID逻辑删除
     */
    @Override
    @CacheEvict(value = "OrganizationServiceImpl", allEntries = true)
    public void deleteBatch(long[] ids) {
        super.deleteBatch(ids);
        log.warn("批量逻辑删除表成功：这批表的id为：" + ids);
    }


    /**
     * 根据ID获取对象
     */
    @Override
    @Cacheable(value = "OrganizationServiceImpl", key = "#id")
    public Organization get(long id) {
        return super.get(id);
    }

    /**
     * 判断数据库表和微服务ID是否合法
     */
    private void appIdAndTableNameCheckout(Organization organization) {
        if (CommonConstants.ZERO == organization.getApplicationId()) {
            throw new BussinessException(ErrorInfoConstants.APPLICATION_ID_NULL);
        }
        if (StringUtils.isEmpty(organization.getOrganizationDataName())) {
            throw new BussinessException(ErrorInfoConstants.ORGANIZATION_DATA_NAME_NULL);
        }
    }

    /**
     * 根据数据中心的表名和微服务的ID精准查询是否有相同的表名字
     */
    @Override
    public Organization uniquenessOrganizationName(Organization organization) {
        appIdAndTableNameCheckout(organization);
        return organizationMapper.uniquenessOrganizationName(organization);
    }

    /**
     * 批量判断list中微服务下是否包含相同的数据库名
     */
    private void organizationNameWeight(List<Organization> list) {
        //判断批量list当中知否含有重复的部门名字
        Set<String> set = new HashSet<>(list.size());
        for (Organization organization : list) {
            set.add(organization.getApplicationId() + organization.getOrganizationDataName());
        }
        if (set.size() != list.size()) {
            throw new BussinessException(ErrorInfoConstants.APPLICATION_AND_TABLE_NAME_REPETITION);
        }
    }

    /**
     * 增加数据库表对象的判断
     */
    private void addOrganizationCheckout(Organization organization) {
        if (uniquenessOrganizationName(organization) != null) {
            throw new BussinessException(ErrorInfoConstants.APPLICATION_AND_TABLE_NAME_REPETITION);
        }
    }

    /**
     * 修改数据库表对象的判断
     */
    private void updateOrganizationCheckout(Organization organization) {
        String organizationDataName = organization.getOrganizationDataName();
        //当检测到数据库表名字不为空的时候
        if (StringUtils.isEmpty(organizationDataName)) {
            Organization table = uniquenessOrganizationName(organization);
            if (table != null && !table.getId().equals(organization.getId())) {
                throw new BussinessException(ErrorInfoConstants.APPLICATION_AND_TABLE_NAME_REPETITION);
            }
        }
    }
}
