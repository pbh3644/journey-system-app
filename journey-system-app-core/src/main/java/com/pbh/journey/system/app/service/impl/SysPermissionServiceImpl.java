package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.SysPermissionMapper;
import com.pbh.journey.system.app.service.SysPermissionService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.common.utils.util.RedisUtils;
import com.pbh.journey.system.pojo.domain.SysPermission;
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
 * ServiceImpl:SysPermissionServiceImpl
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-7
 */
@Service("sysPermissionService")
@Slf4j
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 查询全部权限资源
     */
    @Override
    @Cacheable("SysPermissionServiceImpl")
    public List<SysPermission> findAll() {
        return super.findAll();
    }

    /**
     * 根据查询条件查询权限资源列表分页
     */
    @Override
    @Cacheable(value = "SysPermissionServiceImpl", key = "#sysDept")
    public Page<SysPermission> findPage(SysPermission sysPermission) {
        return super.findPage(sysPermission);
    }

    /**
     * 增加权限资源
     */
    @Override
    @CachePut(value = "SysPermissionServiceImpl", key = "#sysDept.id")
    public void insert(SysPermission sysPermission) {
        addSysPermissionCheckout(sysPermission);
        super.insert(sysPermission);
    }

    /**
     * 批量增加权限资源
     */
    @Override
    @CacheEvict(value = "SysPermissionServiceImpl", allEntries = true)
    public void insertBatch(List<SysPermission> list) {
        permissionUrlWeight(list);
        for (SysPermission sysPermission : list) {
            addSysPermissionCheckout(sysPermission);
        }
        super.insertBatch(list);
    }

    /**
     * 修改权限资源
     */
    @Override
    @CachePut(value = "SysPermissionServiceImpl", key = "#sysDept.id")
    public void update(SysPermission sysPermission) {
        updateSysPermissionCheckout(sysPermission);
        super.update(sysPermission);
    }

    /**
     * 批量修改权限资源
     */
    @Override
    @CacheEvict(value = "SysPermissionServiceImpl", allEntries = true)
    public void updateBatch(List<SysPermission> list) {
        permissionUrlWeight(list);
        for (SysPermission sysPermission : list) {
            updateSysPermissionCheckout(sysPermission);
        }
        super.updateBatch(list);
    }

    /**
     * 根据单个ID物理删除权限资源
     */
    @Override
    @CacheEvict(value = "SysPermissionServiceImpl", key = "#id")
    public void delete(long id) {
        super.delete(id);
        log.warn("物理删除权限资源成功：权限资源的id为：" + id);
    }

    /**
     * 根据单个ID逻辑删除权限资源
     */
    @Override
    @CacheEvict(value = "SysPermissionServiceImpl", key = "#id")
    public void deleteLogic(long id) {
        super.deleteLogic(id);
        log.warn("逻辑删除权限资源成功：权限资源的id为：" + id);
    }

    /**
     * 根据批量ID逻辑删除权限资源
     */
    @Override
    @CacheEvict(value = "SysPermissionServiceImpl", allEntries = true)
    public void deleteBatch(long[] ids) {
        super.deleteBatch(ids);
        log.warn("批量逻辑删除权限资源成功：这批权限资源的id为：" + ids);
    }


    /**
     * 根据ID获取权限资源对象
     */
    @Override
    @Cacheable(value = "SysPermissionServiceImpl", key = "#id")
    public SysPermission get(long id) {
        return super.get(id);
    }

    /**
     * 通过url获取权限资源对象
     */
    @Override
    public SysPermission urlGetPermission(String url) {
        urlNotNull(url);
        return sysPermissionMapper.urlGetPermission(url);
    }

    /**
     * url不能为空
     */
    private void urlNotNull(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new BussinessException(ErrorInfoConstants.PERMISSION_URL_NULL);
        }
    }

    /**
     * 批量判断list中是否包含相同的url
     */
    private void permissionUrlWeight(List<SysPermission> list) {
        //判断批量list当中是否含有重复的url
        Set<String> set = new HashSet<>(list.size());
        for (SysPermission sysPermission : list) {
            set.add(sysPermission.getPermissionUrl());
        }
        if (set.size() != list.size()) {
            throw new BussinessException(ErrorInfoConstants.PERMISSION_URL_REPETITION);
        }
    }

    /**
     * 增加权限资源时对象的判断
     */
    private void addSysPermissionCheckout(SysPermission sysPermission) {
        //父级ID必填
        if (CommonConstants.ZERO == sysPermission.getParentId()) {
            throw new BussinessException(ErrorInfoConstants.PERMISSION_PARENT_ID_NULL);
        }
        //是否是菜单必填
        if (CommonConstants.ZERO == sysPermission.getIsMenu()) {
            throw new BussinessException(ErrorInfoConstants.PERMISSION_IS_MENU_NULL);
        }
        //层级必填
        Integer level = sysPermission.getLevel();
        if (CommonConstants.ZERO == level) {
            throw new BussinessException(ErrorInfoConstants.PERMISSION_LEVEL_NULL);
        }

        //访问URL不可重复
        if (urlGetPermission(sysPermission.getPermissionUrl()) != null) {
            throw new BussinessException(ErrorInfoConstants.PERMISSION_URL_REPETITION);
        }
        //权限类型必填
        if (StringUtils.isEmpty(sysPermission.getPermissionType())) {
            throw new BussinessException(ErrorInfoConstants.PERMISSION_TYPE_NULL);
        }

        //设置优先级，同level大的在上
        int order = (int) RedisUtils.incr(CommonConstants.PERMISSION_LOG + level, CommonConstants.PERMISSION_LEVEL_ORDER_PROGRESSIVE);
        sysPermission.setOrder(order);
        //当前系统code
        sysPermission.setSystemCode(CommonConstants.SYSTEM_CODE);
    }


    /**
     * 修改权限资源时对象的判断
     */
    private void updateSysPermissionCheckout(SysPermission sysPermission) {
        String permissionUrl = sysPermission.getPermissionUrl();
        //当检测到url要修改时
        if (!StringUtils.isEmpty(permissionUrl)) {
            SysPermission permission = urlGetPermission(permissionUrl);
            //如果存在的情况下判断两个权限ID是否一致，如果不一致证明权限URL重复
            if (permission != null && !permission.getId().equals(sysPermission.getId())) {
                throw new BussinessException(ErrorInfoConstants.PERMISSION_URL_REPETITION);
            }
        }

        //如果检测到要修改了权限资源层级-同步修改该资源层级下的优先级
        int level = sysPermission.getLevel();
        if (CommonConstants.ZERO != level && level != get(sysPermission.getId()).getLevel()) {
            int order = (int) RedisUtils.incr(CommonConstants.PERMISSION_LOG + level, CommonConstants.PERMISSION_LEVEL_ORDER_PROGRESSIVE);
            sysPermission.setOrder(order);
        }
    }
}
