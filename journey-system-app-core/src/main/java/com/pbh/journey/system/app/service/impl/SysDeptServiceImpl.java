package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.SysDeptMapper;
import com.pbh.journey.system.app.service.SysDeptService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.pojo.domain.SysDept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        if (nameGetDept(sysDept.getDeptName()) != null) {
            throw new BussinessException(ErrorInfoConstants.DEPT_NAME_REPETITION);
        }
        super.insert(sysDept);
    }

    /**
     * 批量增加部门
     */
    @Override
    @CacheEvict(value = "SysDeptServiceImpl", allEntries = true)
    public void insertBatch(List<SysDept> list) {
        for (SysDept sysDept : list) {
            if (nameGetDept(sysDept.getDeptName()) != null) {
                throw new BussinessException(ErrorInfoConstants.DEPT_NAME_REPETITION);
            }
        }
        super.insertBatch(list);
    }

    /**
     * 修改部门
     */
    @Override
    @CachePut(value = "SysDeptServiceImpl", key = "#sysDept.id")
    public void update(SysDept sysDept) {
        SysDept dept = nameGetDept(sysDept.getDeptName());
        if (dept != null && !dept.getId().equals(sysDept.getId())) {
            throw new BussinessException(ErrorInfoConstants.DEPT_NAME_REPETITION);
        }
        super.update(sysDept);
    }

    /**
     * 批量修改部门
     */
    @Override
    @CacheEvict(value = "SysDeptServiceImpl", allEntries = true)
    public void updateBatch(List<SysDept> list) {
        for (SysDept sysDept : list) {
            SysDept dept = nameGetDept(sysDept.getDeptName());
            if (dept != null && !dept.getId().equals(sysDept.getId())) {
                throw new BussinessException(ErrorInfoConstants.DEPT_NAME_REPETITION);
            }
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

    @Override
    public SysDept nameGetDept(String deptName) {
        return sysDeptMapper.nameGetDept(deptName);
    }
}
