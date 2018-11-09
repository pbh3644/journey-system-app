package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.SysDeptPermissionMapper;
import com.pbh.journey.system.app.service.SysDeptPermissionService;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.pojo.domain.SysDeptPermission;
import com.pbh.journey.system.pojo.domain.SysPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ServiceImpl:SysDeptPermissionServiceImpl
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-9
 */
@Service("sysDeptPermissionService")
public class SysDeptPermissionServiceImpl extends BaseServiceImpl<SysDeptPermissionMapper, SysDeptPermission> implements SysDeptPermissionService {

    @Resource
    private SysDeptPermissionMapper sysDeptPermissionMapper;

    /**
     * 根据部门ID查看所有权限
     */
    @Override
    public List<SysPermission> deptOfPermission(long deptId) {
        // TODO: 2018/11/9 后期要做对数组菜单的排序
        return sysDeptPermissionMapper.deptOfPermission(deptId);
    }
}
