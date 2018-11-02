package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.SysUserMapper;
import com.pbh.journey.system.app.service.SysUserService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.common.utils.util.IpUtils;
import com.pbh.journey.system.common.utils.util.RegexUtils;
import com.pbh.journey.system.pojo.domain.SysUser;
import com.pbh.journey.system.pojo.dto.LoginNoDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ServiceImpl:SysUserServiceImpl
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private LoginNoServiceImpl loginNoService;

    /**
     * 查询全部系统管理员列表
     */
    @Override
    @Cacheable("SysUserServiceImpl")
    public List<SysUser> findAll() {
        return super.findAll();
    }


    /**
     * 根据查询条件查询系统管理员列表分页
     */
    @Override
    @Cacheable(value = "SysUserServiceImpl", key = "#sysUser")
    public Page<SysUser> findPage(SysUser sysUser) {
        return super.findPage(sysUser);
    }


    /**
     * 增加系统管理员
     * 并且增加默认登录账号 登录类型:手机号 登录账号:手机号码 密码:123456s
     */
    @Override
    public void insert(SysUser entity) {
        String nickName = entity.getNickName();
        if (StringUtils.isEmpty(nickName)) {
            throw new BussinessException("请填写系统昵称");
        }
        String realName = entity.getRealName();
        if (StringUtils.isEmpty(realName)) {
            throw new BussinessException("请填写真实姓名");
        }
        String idCard = entity.getIdCard();
        if (!RegexUtils.checkIdCard(idCard)) {
            throw new BussinessException("请输入正确的身份证号码");
        }
        String mailbox = entity.getMailbox();
        if (!RegexUtils.checkEmail(mailbox)) {
            throw new BussinessException("请输入正确的邮箱号");
        }
        String mobile = entity.getMobile();
        if (!RegexUtils.checkMobile(mobile)) {
            throw new BussinessException("请输入正确的手机号");
        }
        
        if (loginNoService.loginNoExist(mobile) != null) {
            throw new BussinessException("这个号码已存在不允许填写重复的");
        }

        //获取用户的IP地址
        entity.setDeviceId(IpUtils.getRealIP());
        super.insert(entity);

        //获取初始化用户账号密码
        LoginNoDTO loginNo = new LoginNoDTO();
        loginNo.setUserId(entity.getId());
        loginNo.setType(CommonConstants.LOGIN_MOBILE);
        loginNo.setUserAccount(mobile);
        loginNo.setUserPwd(CommonConstants.DEFAULT_PWD);
        loginNoService.insert(loginNo);
    }

    /**
     * 修改系统管理员信息
     */
    @Override
    public void update(SysUser entity) {
        String newMobile = entity.getMobile();
        //当检测到管理员修改了手机号码时
        if (!StringUtils.isEmpty(newMobile)) {
            if (RegexUtils.checkMobile(newMobile)) {
                throw new BussinessException("请输入正确的手机号");
            }
            if (loginNoService.loginNoExist(newMobile) != null) {
                throw new BussinessException("这个号码已存在不允许填写重复的");
            }
            SysUser sysUser = get(entity.getId());
            String mobile = sysUser.getMobile();
            //看看是否一致，不一致同时把登录账号表关联改了
            if (!newMobile.equals(mobile)) {
                LoginNoDTO loginNoDTO = new LoginNoDTO();
                loginNoDTO.setType(CommonConstants.LOGIN_MOBILE);
                loginNoDTO.setNewUserAccount(newMobile);
                loginNoDTO.setUserAccount(mobile);
                loginNoService.updateUserAccount(loginNoDTO);
            }
        }
        super.update(entity);
    }

    /**
     * 物理删除系统管理员
     */
    @Override
    public void delete(long id) {
        super.delete(id);
    }

    /**
     * 批量逻辑删除系统管理员
     */
    @Override
    public void deleteBatch(long[] ids) {
        super.deleteBatch(ids);
    }

    /**
     * 逻辑删除系统管理员
     */
    @Override
    public void deleteLogic(long id) {
        super.deleteLogic(id);
    }

    @Override
    public SysUser get(long id) {
        return super.get(id);
    }
}
