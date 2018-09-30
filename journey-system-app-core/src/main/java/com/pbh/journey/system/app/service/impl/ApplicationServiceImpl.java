package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.ApplicationMapper;
import com.pbh.journey.system.app.service.ApplicationService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.pojo.domain.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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
@EnableCaching
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
            log.error("增加微服务失败！ApplicationServiceImpl，微服务的名字已经存在不允许重复添加，微服务的名字为：" + applicationNameEnglish);
            throw new BussinessException("增加微服务失败！含微服务的名字重复，不允许增加。批量微服务的名字为：" + applicationNameEnglish);
        }
        application.setAddTime(application.currentTime());
        application.setAddUserId(123456L);
        super.insert(application);
        log.info("增加微服务成功！微服务的名字为:" + applicationNameEnglish);
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
                log.error("批量增加微服务失败！其中包含微服务的名字重复,ApplicationServiceImpl.微服务的名字为：" + applicationNameEnglish);
                throw new BussinessException("批量增加微服务失败！其中包含微服务的名字重复，不允许批量增加。重复的微服务的名字为:" + applicationNameEnglish);
            }
            application.setAddTime(application.currentTime());
            application.setAddUserId(123456L);
        }
        super.insertBatch(list);
        log.info("批量增加微服务成功：这批微服务的信息为：" + list.toString());
    }

    /**
     * 修改微服务
     */
    @Override
    @CachePut(value = "ApplicationServiceImpl", key = "#Application.id")
    public void update(Application application) {
        String applicationNameEnglish = application.getApplicationNameEnglish();
        if (applicationMapper.uniquenessApplicationName(applicationNameEnglish) != null) {
            log.error("修改微服务的名字已经存在不允许修改重复的微服务名字,ApplicationServiceImpl.微服务的名字为：" + applicationNameEnglish);
            throw new BussinessException("修改微服务失败！这个微服务名字已经存在，不允许修改。重复的微服务名字为：" + applicationNameEnglish);
        }
        application.setUpdateTime(application.currentTime());
        application.setUpdateUserId(123456L);
        super.update(application);
        log.info("修改微服务成功：微服务的名字为:" + applicationNameEnglish);
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
                log.error("批量修改微服务的名字已经存在不允许修改重复的微服务名字,ApplicationServiceImpl.微服务的名字为：" + applicationNameEnglish);
                throw new BussinessException("批量修改微服务的名字失败！其中包含微服务的名字重复，不允许批量修改。重复的微服务的名字为：" + applicationNameEnglish);
            }
            application.setUpdateTime(application.currentTime());
            application.setUpdateUserId(123456L);
        }
        super.updateBatch(list);
        log.info("修改微服务成功：这批微服务的信息为" + list.toString());
    }

    /**
     * 根据单个ID物理删除
     */
    @Override
    @CacheEvict(value = "ApplicationServiceImpl", key = "#id")
    public void delete(long id) {
        super.delete(id);
        log.info("物理删除微服务成功：微服务的id为：" + id);
    }

    /**
     * 根据单个ID逻辑删除
     */
    @Override
    @CacheEvict(value = "ApplicationServiceImpl", key = "#id")
    public void deleteLogic(long id) {
        super.deleteLogic(id);
        log.info("逻辑删除微服务成功：微服务的id为：" + id);
    }

    /**
     * 根据批量ID逻辑删除
     */
    @Override
    @CacheEvict(value = "ApplicationServiceImpl", allEntries = true)
    public void deleteBatch(long[] ids) {
        super.deleteBatch(ids);
        log.info("批量逻辑删除微服务成功：这批微服务的id为：" + ids);
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
