package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.SysDeptMapper;
import com.pbh.journey.system.app.service.SysDeptService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.common.utils.util.CompareSceneException;
import com.pbh.journey.system.common.utils.util.RedisUtils;
import com.pbh.journey.system.pojo.domain.SysDept;
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
 * ServiceImpl:SysDeptServiceImpl
 *
 * @author pangbohuan
 * @version 1.0
 * @since 2018-11-5
 */
@Service("sysDeptService")
@Slf4j
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询全部部门列表
     */
    @Override
    @Cacheable("SysDeptServiceImpl")
    public List<SysDept> findAll() {
        return super.findAll();
    }

    /**
     * 根据查询条件查询部门列表分页
     */
    @Override
    @Cacheable(value = "SysDeptServiceImpl", key = "#sysDept")
    public Page<SysDept> findPage(SysDept sysDept) {
        return super.findPage(sysDept);
    }

    /**
     * 增加部门
     */
    @Override
    @CachePut(value = "SysDeptServiceImpl", key = "#sysDept.id")
    public void insert(SysDept sysDept) {
        addSysDeptCheckout(sysDept);
        super.insert(sysDept);
    }

    /**
     * 批量增加部门
     */
    @Override
    @CacheEvict(value = "SysDeptServiceImpl", allEntries = true)
    public void insertBatch(List<SysDept> list) {
        deptNameWeight(list);
        for (SysDept sysDept : list) {
            addSysDeptCheckout(sysDept);
        }
        super.insertBatch(list);
    }

    /**
     * 修改部门
     */
    @Override
    @CachePut(value = "SysDeptServiceImpl", key = "#sysDept.id")
    public void update(SysDept sysDept) {
        updateSysDeptCheckout(sysDept);
        super.update(sysDept);
    }

    /**
     * 批量修改部门
     */
    @Override
    @CacheEvict(value = "SysDeptServiceImpl", allEntries = true)
    public void updateBatch(List<SysDept> list) {
        deptNameWeight(list);
        for (SysDept sysDept : list) {
            updateSysDeptCheckout(sysDept);
        }
        super.updateBatch(list);
    }

    /**
     * 根据单个ID物理删除
     */
    @Override
    @CacheEvict(value = "SysDeptServiceImpl", key = "#id")
    public void delete(long id) {
        super.delete(id);
        log.warn("物理删除部门成功：部门的id为：" + id);
    }

    /**
     * 根据单个ID逻辑删除
     */
    @Override
    @CacheEvict(value = "SysDeptServiceImpl", key = "#id")
    public void deleteLogic(long id) {
        super.deleteLogic(id);
        log.warn("逻辑删除部门成功：部门的id为：" + id);
    }

    /**
     * 根据批量ID逻辑删除
     */
    @Override
    @CacheEvict(value = "SysDeptServiceImpl", allEntries = true)
    public void deleteBatch(long[] ids) {
        super.deleteBatch(ids);
        log.warn("批量逻辑删除部门成功：这批部门的id为：" + ids);
    }


    /**
     * 根据ID获取对象
     */
    @Override
    @Cacheable(value = "SysDeptServiceImpl", key = "#id")
    public SysDept get(long id) {
        return super.get(id);
    }

    /**
     * 判断部门名字不能为空
     */
    private void deptNameCheckout(String deptName) {
        CompareSceneException.customStringIsNull(deptName, ErrorInfoConstants.PLEASE_ENTER_DEPT_NAME);
    }

    /**
     * 根据部门名字获取部门
     */
    @Override
    public SysDept nameGetDept(String deptName) {
        deptNameCheckout(deptName);
        return sysDeptMapper.nameGetDept(deptName);
    }

    /**
     * 启用禁用部门
     */
    @Override
    public void switchDept(SysDept sysDept) {
        //部门ID不能为空
        CompareSceneException.customNumericalEquality(CommonConstants.ZERO, sysDept.getId(), ErrorInfoConstants.ID_NOT_NULL);
        //部门状态不能为空
        CompareSceneException.customNumericalEquality(CommonConstants.ZERO, sysDept.getDeptState(), ErrorInfoConstants.DEPT_STATE_REPETITION);
        sysDeptMapper.switchDept(sysDept);
    }

    /**
     * 批量判断list中是否包含相同的部门名
     */
    private void deptNameWeight(List<SysDept> list) {
        //判断批量list当中知否含有重复的部门名字
        int listSize = list.size();
        Set<String> set = new HashSet<>(listSize);
        for (SysDept sysDept : list) {
            set.add(sysDept.getDeptName());
        }
        CompareSceneException.customNumericalNotEquality(set.size(), listSize, ErrorInfoConstants.DEPT_NAME_REPETITION);
    }

    /**
     * 增加部门时对象的判断
     */
    private void addSysDeptCheckout(SysDept sysDept) {
        //判断部门名字是否重复
        CompareSceneException.customObjectIsNotNull(nameGetDept(sysDept.getDeptName()), ErrorInfoConstants.DEPT_NAME_REPETITION);
        //通过redis递增数字+1获取唯一deptCode
        String deptCode = CommonConstants.DEPT_LOG + RedisUtils.incr(CommonConstants.DEPT_LOG, CommonConstants.DEPT_CODE_PROGRESSIVE);
        sysDept.setDeptCode(deptCode);
        sysDept.setSystemCode(CommonConstants.SYSTEM_CODE);
    }

    /**
     * 修改部门时对象的判断
     */
    private void updateSysDeptCheckout(SysDept sysDept) {
        //当检测到部门名不为空的时候
        String deptName = sysDept.getDeptName();
        if (!StringUtils.isEmpty(deptName)) {
            //看看这个部门名是否存在，数据库唯一索性
            SysDept dept = nameGetDept(deptName);
            //如果存在的情况下判断两个部门ID是否一致，如果不一致证明部门名重复
            if (dept != null && !dept.getId().equals(sysDept.getId())) {
                throw new BussinessException(ErrorInfoConstants.DEPT_NAME_REPETITION);
            }
        }
    }
}
