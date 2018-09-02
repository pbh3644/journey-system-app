package com.cmrh.journey.system.app.service.impl;

import com.cmrh.journey.system.app.mapper.SysUserMapper;
import com.cmrh.journey.system.app.mapper.SysUserMapperCustom;
import com.cmrh.journey.system.app.pojo.SysUser;
import com.cmrh.journey.system.app.service.SysUserService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pangbohuan
 * @description:SysUserServiceImpl
 * @date 2018-07-25 17:30
 **/
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    /**
     * 生成的Mapper
     */
    @Resource
    SysUserMapper sysUserMapper;

    /**
     * 自定义SQL的Mapper
     */
    @Resource
    SysUserMapperCustom sysUserMapperCustom;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void saveUser(SysUser user) {
        sysUserMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void updateUser(SysUser user) {
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void deleteUser(String userId) {
        sysUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public SysUser queryUserById(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<SysUser> queryUserListPaged(SysUser user) {
        // 开始分页
        PageHelper.startPage(user.getPage(), user.getPageSize());

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        /**
         * 模糊查询字段筛选
         * */
        String sysUserName = user.getSysUserName();
        if (StringUtils.isNotEmpty(sysUserName)) {
            criteria.andLike("userName", "%" + sysUserName + "%");
        }
        example.orderBy("userAge").asc();
        List<SysUser> userList = sysUserMapper.selectByExample(example);

        return userList;
    }

    @Override
    public String userPasswordEncrypt(String password) {
        return sysUserMapperCustom.userPasswordEncrypt(password);
    }

    @Override
    public List<SysUser> selectAll() {
        return sysUserMapper.selectAll();
    }

    @Override
    public int selectCount(SysUser user) {
        return sysUserMapper.selectCount(user);
    }
}
