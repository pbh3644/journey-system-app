package com.pbh.journey.system.app.mapper;

import com.pbh.journey.system.common.base.mapper.BaseMapper;
import com.pbh.journey.system.pojo.domain.Application;
import org.apache.ibatis.annotations.Mapper;

/**
 * Dao Interface:ApplicationMapper
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-9
 */
@Mapper
public interface ApplicationMapper extends BaseMapper<Application> {

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
