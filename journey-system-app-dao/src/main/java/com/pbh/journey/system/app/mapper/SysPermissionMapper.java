package com.pbh.journey.system.app.mapper;

import com.pbh.journey.system.common.base.mapper.BaseMapper;
import com.pbh.journey.system.pojo.domain.SysPermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * Dao Interface:SysPermissionMapper
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-7
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 判断url是否有重复的
     *
     * @param url
     * @return SysPermission
     */
    SysPermission urlGetPermission(String url);
}
