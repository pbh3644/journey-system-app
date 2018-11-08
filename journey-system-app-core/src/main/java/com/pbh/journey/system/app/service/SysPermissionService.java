package com.pbh.journey.system.app.service;

import com.pbh.journey.system.common.base.service.BaseService;
import com.pbh.journey.system.pojo.domain.SysPermission;

/**
 * Service Interface:SysPermissionService
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-7
 */
public interface SysPermissionService extends BaseService<SysPermission> {

    /**
     * 判断url是否有重复的
     *
     * @param url
     * @return SysPermission
     */
    SysPermission urlGetPermission(String url);
}
