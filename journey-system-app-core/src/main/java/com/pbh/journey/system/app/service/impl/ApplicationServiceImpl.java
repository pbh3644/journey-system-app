package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.ApplicationMapper;
import com.pbh.journey.system.app.service.ApplicationService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.pojo.domain.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ServiceImpl:ApplicationServiceImpl
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-9
 */
@Service("applicationService")
@Slf4j
public class ApplicationServiceImpl extends BaseServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    @Resource
    private ApplicationMapper applicationMapper;

    /**
     * 查询全部微服务列表
     */
    @Override
    @Cacheable("ApplicationServiceImpl")
    public List<Application> findAll() {
        return super.findAll();
    }

    /**
     * 根据查询条件查询微服务列表分页
     */
    @Override
    @Cacheable(value = "ApplicationServiceImpl", key = "#application")
    public Page<Application> findPage(Application application) {
        return super.findPage(application);
    }

    /**
     * 增加微服务
     */
    @Override
    @CachePut(value = "ApplicationServiceImpl", key = "#Application.id")
    public void insert(Application application) {
        String applicationNameEnglish = application.getApplicationNameEnglish();
        if (applicationMapper.uniquenessApplicationName(application.getApplicationNameEnglish()) != null) {
            throw new BussinessException(ErrorInfoConstants.APPLICATION_NAME_REPETITION);
        }
        super.insert(application);
        log.warn("增加微服务成功！微服务的名字为:" + applicationNameEnglish);
    }

    /**
     * 批量增加微服务
     */
    @Override
    @CacheEvict(value = "ApplicationServiceImpl", allEntries = true)
    public void insertBatch(List<Application> list) {
        for (Application application : list) {
            String applicationNameEnglish = application.getApplicationNameEnglish();
            if (applicationMapper.uniquenessApplicationName(applicationNameEnglish) != null) {
                throw new BussinessException(ErrorInfoConstants.APPLICATION_NAME_REPETITION);
            }
        }
        super.insertBatch(list);
        log.warn("批量增加微服务成功：这批微服务的信息为：" + list.toString());
    }

    /**
     * 修改微服务
     */
    @Override
    @CachePut(value = "ApplicationServiceImpl", key = "#Application.id")
    public void update(Application application) {
        String applicationNameEnglish = application.getApplicationNameEnglish();
        if (applicationMapper.uniquenessApplicationName(applicationNameEnglish) != null) {
            throw new BussinessException(ErrorInfoConstants.APPLICATION_NAME_REPETITION);
        }
        super.update(application);
        log.warn("修改微服务成功：微服务的名字为:" + applicationNameEnglish);
    }

    /**
     * 批量修改微服务
     */
    @Override
    @CacheEvict(value = "ApplicationServiceImpl", allEntries = true)
    public void updateBatch(List<Application> list) {
        for (Application application : list) {
            String applicationNameEnglish = application.getApplicationNameEnglish();
            if (applicationMapper.uniquenessApplicationName(applicationNameEnglish) != null) {
                throw new BussinessException(ErrorInfoConstants.APPLICATION_NAME_REPETITION);
            }
        }
        super.updateBatch(list);
        log.warn("修改微服务成功：这批微服务的信息为" + list.toString());
    }

    /**
     * 根据单个ID物理删除
     */
    @Override
    @CacheEvict(value = "ApplicationServiceImpl", key = "#id")
    public void delete(long id) {
        super.delete(id);
        log.warn("物理删除微服务成功：微服务的id为：" + id);
    }

    /**
     * 根据单个ID逻辑删除
     */
    @Override
    @CacheEvict(value = "ApplicationServiceImpl", key = "#id")
    public void deleteLogic(long id) {
        super.deleteLogic(id);
        log.warn("逻辑删除微服务成功：微服务的id为：" + id);
    }

    /**
     * 根据批量ID逻辑删除
     */
    @Override
    @CacheEvict(value = "ApplicationServiceImpl", allEntries = true)
    public void deleteBatch(long[] ids) {
        super.deleteBatch(ids);
        log.warn("批量逻辑删除微服务成功：这批微服务的id为：" + ids);
    }


    /**
     * 根据ID获取对象
     */
    @Override
    @Cacheable(value = "ApplicationServiceImpl", key = "#id")
    public Application get(long id) {
        return super.get(id);
    }
}
