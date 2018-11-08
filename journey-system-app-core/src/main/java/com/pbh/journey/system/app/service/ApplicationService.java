package com.pbh.journey.system.app.service;

import com.pbh.journey.system.common.base.service.BaseService;
import com.pbh.journey.system.pojo.domain.Application;

/**
 * Service Interface:ApplicationService
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-9
 */
public interface ApplicationService extends BaseService<Application> {
    /**
     * 根据微服务的中文名字精准查询是否有相同的微服务
     *
     * @param applicationNameChinese 微服务的中文名字
     * @return Application 微服务对象
     */
    Application uniquenessApplicationNameChinese(String applicationNameChinese);

    /**
     * 根据微服务的英文名字精准查询是否有相同的微服务
     *
     * @param applicationNameEnglish 微服务的英文名字
     * @return Application 微服务对象
     */
    Application uniquenessApplicationNameEnglish(String applicationNameEnglish);
}
