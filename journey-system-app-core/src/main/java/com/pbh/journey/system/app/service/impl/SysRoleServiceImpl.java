package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.SysRoleMapper;
import com.pbh.journey.system.app.service.SysRoleService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.pojo.domain.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ServiceImpl:SysRoleServiceImpl
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-5
 */
@Service("sysRoleService")
@Slf4j
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询全部角色列表
     */
    @Override
    @Cacheable("SysRoleServiceImpl")
    public List<SysRole> findAll() {
        return super.findAll();
    }

    /**
     * 根据查询条件查询角色列表分页
     */
    @Override
    @Cacheable(value = "SysRoleServiceImpl", key = "#sysDept")
    public Page<SysRole> findPage(SysRole sysRole) {
        return super.findPage(sysRole);
    }

    /**
     * 增加角色
     */
    @Override
    @CachePut(value = "SysRoleServiceImpl", key = "#sysRole.id")
    public void insert(SysRole sysRole) {
        if (nameGetRole(sysRole.getRoleName()) != null) {
            throw new BussinessException(ErrorInfoConstants.ROLE_NAME_REPETITION);
        }
        super.insert(sysRole);
    }

    /**
     * 批量增加角色
     */
    @Override
    @CacheEvict(value = "SysRoleServiceImpl", allEntries = true)
    public void insertBatch(List<SysRole> list) {
        for (SysRole sysRole : list) {
            if (nameGetRole(sysRole.getRoleName()) != null) {
                throw new BussinessException(ErrorInfoConstants.ROLE_NAME_REPETITION);
            }
        }
        super.insertBatch(list);
    }

    /**
     * 修改角色
     */
    @Override
    @CachePut(value = "SysRoleServiceImpl", key = "#SysRole.id")
    public void update(SysRole sysRole) {
        SysRole role = nameGetRole(sysRole.getRoleName());
        if (role != null && !role.getId().equals(sysRole.getId())) {
            throw new BussinessException(ErrorInfoConstants.ROLE_NAME_REPETITION);
        }
        super.update(sysRole);
    }

    /**
     * 批量修改角色
     */
    @Override
    @CacheEvict(value = "SysRoleServiceImpl", allEntries = true)
    public void updateBatch(List<SysRole> list) {
        for (SysRole sysRole : list) {
            SysRole role = nameGetRole(sysRole.getRoleName());
            if (role != null && !role.getId().equals(sysRole.getId())) {
                throw new BussinessException(ErrorInfoConstants.ROLE_NAME_REPETITION);
            }
        }
        super.updateBatch(list);
    }

    /**
     * 根据单个ID物理删除
     */
    @Override
    @CacheEvict(value = "SysRoleServiceImpl", key = "#id")
    public void delete(long id) {
        super.delete(id);
        log.warn("物理删除角色成功：角色的id为：" + id);
    }

    /**
     * 根据单个ID逻辑删除
     */
    @Override
    @CacheEvict(value = "SysRoleServiceImpl", key = "#id")
    public void deleteLogic(long id) {
        super.deleteLogic(id);
        log.warn("逻辑删除角色成功：角色的id为：" + id);
    }

    /**
     * 根据批量ID逻辑删除
     */
    @Override
    @CacheEvict(value = "SysRoleServiceImpl", allEntries = true)
    public void deleteBatch(long[] ids) {
        super.deleteBatch(ids);
        log.warn("批量逻辑删除角色成功：这批角色的id为：" + ids);
    }


    /**
     * 根据ID获取对象
     */
    @Override
    @Cacheable(value = "SysRoleServiceImpl", key = "#id")
    public SysRole get(long id) {
        return super.get(id);
    }

    @Override
    public SysRole nameGetRole(String roleName) {
        return sysRoleMapper.nameGetRole(roleName);
    }
}