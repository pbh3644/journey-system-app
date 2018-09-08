package com.cmrh.journey.system.app.service.impl;

import com.cmrh.journey.system.app.mapper.ApplicationMapper;
import com.cmrh.journey.system.app.service.ApplicationService;
import com.cmrh.journey.system.common.base.service.serviceimpl.BaseServiceImpl;
import com.cmrh.journey.system.pojo.domain.Application;
import org.springframework.stereotype.Service;

/**
 * ServiceImpl:ApplicationServiceImpl
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-8
 */
@Service("applicationService")
public class ApplicationServiceImpl extends BaseServiceImpl<ApplicationMapper, Application> implements ApplicationService {
    //@Resource
    //private ApplicationMapper applicationMapper;

}
