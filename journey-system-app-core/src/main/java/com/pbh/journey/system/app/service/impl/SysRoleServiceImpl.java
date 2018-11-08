package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.SysRoleMapper;
import com.pbh.journey.system.app.service.SysRoleService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.common.utils.util.CompareSceneException;
import com.pbh.journey.system.common.utils.util.RedisUtils;
import com.pbh.journey.system.pojo.domain.SysRole;
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
        addSysRoleCheckout(sysRole);
        super.insert(sysRole);
    }

    /**
     * 批量增加角色
     */
    @Override
    @CacheEvict(value = "SysRoleServiceImpl", allEntries = true)
    public void insertBatch(List<SysRole> list) {
        roleNameWeight(list);
        for (SysRole sysRole : list) {
            addSysRoleCheckout(sysRole);
        }
        super.insertBatch(list);
    }

    /**
     * 修改角色
     */
    @Override
    @CachePut(value = "SysRoleServiceImpl", key = "#SysRole.id")
    public void update(SysRole sysRole) {
        updateSysRoleCheckout(sysRole);
        super.update(sysRole);
    }

    /**
     * 批量修改角色
     */
    @Override
    @CacheEvict(value = "SysRoleServiceImpl", allEntries = true)
    public void updateBatch(List<SysRole> list) {
        roleNameWeight(list);
        for (SysRole sysRole : list) {
            updateSysRoleCheckout(sysRole);
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

    /**
     * 根据角色名字获取部门
     */
    @Override
    public SysRole nameGetRole(String roleName) {
        roleNameNotNull(roleName);
        return sysRoleMapper.nameGetRole(roleName);
    }

    /**
     * 启用禁用角色
     */
    @Override
    public void switchRole(SysRole sysRole) {
        sysRoleMapper.switchRole(sysRole);
    }

    /**
     * 角色名不能为空
     */
    private void roleNameNotNull(String roleName) {
        CompareSceneException.customStringIsNull(roleName, ErrorInfoConstants.PLEASE_ENTER_ROLE_NAME);
    }

    /**
     * 批量判断list中是否包含相同的角色名
     */
    private void roleNameWeight(List<SysRole> list) {
        //判断批量list当中知否含有重复的部门名字
        int listSize = list.size();
        Set<String> set = new HashSet<>(listSize);
        for (SysRole sysRole : list) {
            set.add(sysRole.getRoleName());
        }
        CompareSceneException.customNumericalNotEquality(set.size(), listSize, ErrorInfoConstants.ROLE_NAME_REPETITION);
    }

    /**
     * 增加角色时对象的判断
     */
    private void addSysRoleCheckout(SysRole sysRole) {
        //判断角色的名字是否已存在
        CompareSceneException.customObjectIsNotNull(nameGetRole(sysRole.getRoleName()), ErrorInfoConstants.ROLE_NAME_REPETITION);
        //通过redis递增数字+1获取唯一roleCode
        String roleCode = CommonConstants.ROLE_LOG + RedisUtils.incr(CommonConstants.ROLE_LOG, CommonConstants.ROLE_CODE_PROGRESSIVE);
        sysRole.setRoleCode(roleCode);
        sysRole.setSystemCode(CommonConstants.SYSTEM_CODE);
    }

    /**
     * 修改角色时对象的判断
     */
    private void updateSysRoleCheckout(SysRole sysRole) {
        String roleName = sysRole.getRoleName();
        if (!StringUtils.isEmpty(roleName)) {
            //看看这个角色名是否存在，数据库唯一性索引
            SysRole role = nameGetRole(roleName);
            //如果存在的情况下判断两个角色ID是否一致，如果不一致证明角色名重复
            if (role != null && !role.getId().equals(sysRole.getId())) {
                throw new BussinessException(ErrorInfoConstants.ROLE_NAME_REPETITION);
            }
        }
    }
}
