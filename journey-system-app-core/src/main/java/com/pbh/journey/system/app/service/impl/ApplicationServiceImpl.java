package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.ApplicationMapper;
import com.pbh.journey.system.app.service.ApplicationService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.common.utils.util.CompareSceneException;
import com.pbh.journey.system.pojo.domain.Application;
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
        addApplicationCheckout(application);
        super.insert(application);
        log.warn("增加微服务成功！微服务的名字为:" + application.getApplicationNameEnglish());
    }

    /**
     * 批量增加微服务
     */
    @Override
    @CacheEvict(value = "ApplicationServiceImpl", allEntries = true)
    public void insertBatch(List<Application> list) {
        applicationNameWeight(list);
        for (Application application : list) {
            addApplicationCheckout(application);
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
        updateApplicationCheckout(application);
        super.update(application);
        log.warn("修改微服务成功：微服务的名字为:" + application.getApplicationNameEnglish());
    }

    /**
     * 批量修改微服务
     */
    @Override
    @CacheEvict(value = "ApplicationServiceImpl", allEntries = true)
    public void updateBatch(List<Application> list) {
        applicationNameWeight(list);
        for (Application application : list) {
            updateApplicationCheckout(application);
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

    /**
     * 根据微服务的中文名字精准查询是否有相同的微服务
     */
    @Override
    public Application uniquenessApplicationNameChinese(String applicationNameChinese) {
        applicationNameChineseCheckout(applicationNameChinese);
        return applicationMapper.uniquenessApplicationNameChinese(applicationNameChinese);
    }

    /**
     * 根据微服务的英文名字精准查询是否有相同的微服务
     */
    @Override
    public Application uniquenessApplicationNameEnglish(String applicationNameEnglish) {
        applicationNameChineseEnglish(applicationNameEnglish);
        return applicationMapper.uniquenessApplicationNameEnglish(applicationNameEnglish);
    }

    /**
     * 判断微服务的中文名字是否合法
     */
    private void applicationNameChineseCheckout(String applicationNameChinese) {
        CompareSceneException.customStringIsNull(applicationNameChinese, ErrorInfoConstants.APPLICATION_NAME_CHINESE_NULL);
    }

    /**
     * 判断微服务的英文名字是否合法
     */
    private void applicationNameChineseEnglish(String applicationNameEnglish) {
        CompareSceneException.customStringIsNull(applicationNameEnglish, ErrorInfoConstants.APPLICATION_NAME_ENGLISH_NULL);
    }

    /**
     * 批量判断list中是否包含相同的微服务名
     */
    private void applicationNameWeight(List<Application> list) {
        int listSize = list.size();
        //判断批量list当中知否含有重复的微服务名字
        Set<String> setChinese = new HashSet<>(listSize);
        Set<String> setEnglish = new HashSet<>(listSize);
        for (Application application : list) {
            setChinese.add(application.getApplicationNameChinese());
            setEnglish.add(application.getApplicationNameEnglish());
        }
        CompareSceneException.customNumericalNotEquality(setChinese.size(), listSize, ErrorInfoConstants.APPLICATION_NAME_CHINESE_REPETITION);
        CompareSceneException.customNumericalNotEquality(setEnglish.size(), listSize, ErrorInfoConstants.APPLICATION_NAME_ENGLISH_REPETITION);
    }

    /**
     * 增加微服务时对象的判断
     */
    private void addApplicationCheckout(Application application) {
        //判断微服务的中文名字是否重复
        Application applicationChinese = uniquenessApplicationNameChinese(application.getApplicationNameChinese());
        CompareSceneException.customObjectIsNotNull(applicationChinese, ErrorInfoConstants.APPLICATION_NAME_CHINESE_REPETITION);
        //判断微服务的英文名字是否重复
        Application applicationEnglish = uniquenessApplicationNameEnglish(application.getApplicationNameEnglish());
        CompareSceneException.customObjectIsNotNull(applicationEnglish, ErrorInfoConstants.APPLICATION_NAME_ENGLISH_REPETITION);

    }

    /**
     * 修改微服务时对象的判断
     */
    private void updateApplicationCheckout(Application application) {
        updateApplicationChineseCheckout(application);
        updateApplicationEnglishCheckout(application);
    }

    /**
     * 修改微服务时检测微服务的中文名字
     */
    private void updateApplicationChineseCheckout(Application application) {
        String applicationNameChinese = application.getApplicationNameChinese();
        //当检测到微服务的中文名字不为空时
        if (!StringUtils.isEmpty(applicationNameChinese)) {
            //根据微服务的中文名字查询对象
            Application oldApplication = uniquenessApplicationNameChinese(applicationNameChinese);
            //如果查到重复的并且两个微服务的ID不一致
            if (oldApplication != null && oldApplication.getId().equals(application.getId())) {
                throw new BussinessException(ErrorInfoConstants.APPLICATION_NAME_CHINESE_REPETITION);
            }
        }
    }

    /**
     * 修改微服务时检测微服务的英文名字
     */
    private void updateApplicationEnglishCheckout(Application application) {
        String applicationNameEnglish = application.getApplicationNameEnglish();
        //当检测到微服务的英文名字不为空时
        if (!StringUtils.isEmpty(applicationNameEnglish)) {
            //根据微服务的英文名字查询对象
            Application oldApplication = uniquenessApplicationNameEnglish(applicationNameEnglish);
            //如果查到重复的并且两个微服务的ID不一致
            if (oldApplication != null && oldApplication.getId().equals(application.getId())) {
                throw new BussinessException(ErrorInfoConstants.APPLICATION_NAME_ENGLISH_REPETITION);
            }
        }
    }
}
