package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.ApplicationMapper;
import com.pbh.journey.system.app.service.ApplicationService;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.pojo.domain.Application;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void insert(Application application) {
        String applicationNameEnglish = application.getApplicationNameEnglish();
        if (applicationMapper.uniquenessApplicationName(application.getApplicationNameEnglish()) != null) {
            log.error("增加微服务失败！ApplicationServiceImpl.32行，微服务的名字已经存在不允许重复添加，微服务的名字为：" + applicationNameEnglish);
            throw new BussinessException("增加微服务失败！含微服务的名字重复，不允许增加。批量微服务的名字为：" + applicationNameEnglish);
        }
        super.insert(application);
        log.info("增加微服务成功！微服务的名字为:" + applicationNameEnglish);
    }

    @Override
    public void insertBatch(List<Application> list) {
        for (Application application : list) {
            String applicationNameEnglish = application.getApplicationNameEnglish();
            if (applicationMapper.uniquenessApplicationName(applicationNameEnglish) != null) {
                log.error("批量增加微服务失败！其中包含微服务的名字重复,ApplicationServiceImpl.44行.微服务的名字为：" + applicationNameEnglish);
                throw new BussinessException("批量增加微服务失败！其中包含微服务的名字重复，不允许批量增加。重复的微服务的名字为:" + applicationNameEnglish);
            }
        }
        super.insertBatch(list);
        log.info("批量增加微服务成功：这批微服务的信息为：" + list.toString());
    }

    @Override
    public void update(Application application) {
        String applicationNameEnglish = application.getApplicationNameEnglish();
        if (applicationMapper.uniquenessApplicationName(applicationNameEnglish) != null) {
            log.error("修改微服务的名字已经存在不允许修改重复的微服务名字,ApplicationServiceImpl.56行.微服务的名字为：" + applicationNameEnglish);
            throw new BussinessException("修改微服务失败！这个微服务名字已经存在，不允许修改。重复的微服务名字为：" + applicationNameEnglish);
        }
        super.update(application);
        log.info("修改微服务成功：微服务的名字为:" + applicationNameEnglish);
    }

    @Override
    public void updateBatch(List<Application> list) {
        for (Application application : list) {
            String applicationNameEnglish = application.getApplicationNameEnglish();
            if (applicationMapper.uniquenessApplicationName(applicationNameEnglish) != null) {
                log.error("批量修改微服务的名字已经存在不允许修改重复的微服务名字,ApplicationServiceImpl.68行.微服务的名字为：" + applicationNameEnglish);
                throw new BussinessException("批量修改微服务的名字失败！其中包含微服务的名字重复，不允许批量修改。重复的微服务的名字为：" + applicationNameEnglish);
            }
        }
        super.updateBatch(list);
        log.info("修改微服务成功：这批微服务的信息为" + list.toString());
    }
}
