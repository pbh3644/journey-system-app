package com.cmrh.journey.system.app.service.impl;

import com.cmrh.journey.system.app.mapper.OrganizationMapper;
import com.cmrh.journey.system.app.service.OrganizationService;
import com.cmrh.journey.system.common.base.service.serviceimpl.BaseServiceImpl;
import com.cmrh.journey.system.pojo.domain.Organization;
import org.springframework.stereotype.Service;

/**
 * ServiceImpl:OrganizationServiceImpl
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-8
 */
@Service("organizationService")
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
    //@Resource
    //private OrganizationMapper organizationMapper;

}
