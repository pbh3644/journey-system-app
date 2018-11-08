package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.SysUserMapper;
import com.pbh.journey.system.app.service.SysUserService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.common.utils.util.CompareSceneException;
import com.pbh.journey.system.common.utils.util.IpUtils;
import com.pbh.journey.system.pojo.domain.LoginNo;
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
     * 判断新增管理员是否合法
     * 增加系统管理员
     * 增加默认登录账号 登录类型:手机号 登录账号:手机号码 密码:lx123456
     */
    @Override
    public void insert(SysUser sysUser) {
        addSysUserCheckout(sysUser);
        super.insert(sysUser);
        addInitLogin(sysUser);
    }

    /**
     * 修改系统管理员信息
     */
    @Override
    public void update(SysUser sysUser) {
        updateSysUserCheckout(sysUser);
        super.update(sysUser);
    }

    /**
     * 物理删除系统管理员
     */
    @Override
    public void delete(long id) {
        super.delete(id);
        log.warn("物理删除系统管理员成功：这个系统管理员的id为：" + id);
    }

    /**
     * 逻辑删除系统管理员
     */
    @Override
    public void deleteLogic(long id) {
        super.deleteLogic(id);
        log.warn("逻辑删除系统管理员成功：这个系统管理员的id为：" + id);
    }

    /**
     * 批量逻辑删除系统管理员
     */
    @Override
    public void deleteBatch(long[] ids) {
        super.deleteBatch(ids);
        log.warn("批量逻辑系统管理员成功：这批系统管理员的id为：" + ids);
    }

    /**
     * 通过ID获取系统管理员
     */
    @Override
    public SysUser get(long id) {
        return super.get(id);
    }

    /**
     * 通过手机号码获取系统管理员
     */
    @Override
    public SysUser mobileGetSysUser(String mobile) {
        mobileCheckout(mobile);
        return sysUserMapper.mobileGetSysUser(mobile);
    }

    /**
     * 通过身份证号码获取系统管理员
     */
    @Override
    public SysUser idCardGetSysUser(String idCard) {
        idCardCheckout(idCard);
        return sysUserMapper.idCardGetSysUser(idCard);
    }

    /**
     * 通过邮箱号码获取系统管理员
     */
    @Override
    public SysUser mailboxGetSysUser(String mailbox) {
        mailboxCheckout(mailbox);
        return sysUserMapper.mailboxGetSysUser(mailbox);
    }

    /**
     * 检验手机号码的合法性
     */
    private void mobileCheckout(String mobile) {
        CompareSceneException.mobileCheckout(mobile, ErrorInfoConstants.PLEASE_ENTER_MOBILE);
    }

    /**
     * 检验身份证号码的合法性
     */
    private void idCardCheckout(String idCard) {
        CompareSceneException.idCardCheckout(idCard, ErrorInfoConstants.PLEASE_ENTER_ID_CARD);
    }

    /**
     * 检验邮箱号码的合法性
     */
    private void mailboxCheckout(String mailbox) {
        CompareSceneException.mailboxCheckout(mailbox, ErrorInfoConstants.PLEASE_ENTER_MAILBOX);
    }

    /**
     * 增加系统管理员时对象的判断
     */
    private void addSysUserCheckout(SysUser sysUser) {
        //请填写系统昵称
        CompareSceneException.customStringIsNull(sysUser.getNickName(), ErrorInfoConstants.PLEASE_ENTER_SYSTEM_NICKNAME);
        //请填写真实姓名
        CompareSceneException.customStringIsNull(sysUser.getRealName(), ErrorInfoConstants.PLEASE_ENTER_REAL_NAME);
        //身份证号码唯一
        CompareSceneException.customObjectIsNotNull(idCardGetSysUser(sysUser.getIdCard()), ErrorInfoConstants.ID_CARD_REPETITION);
        //邮箱号码唯一
        CompareSceneException.customObjectIsNotNull(mailboxGetSysUser(sysUser.getMailbox()), ErrorInfoConstants.MAILBOX_REPETITION);
        //手机号码唯一
        CompareSceneException.customObjectIsNotNull(mobileGetSysUser(sysUser.getMobile()), ErrorInfoConstants.MAILBOX_REPETITION);

        //获取用户的IP地址
        sysUser.setDeviceId(IpUtils.getRealIP());
    }

    /**
     * 初始化新增系统管理员登录账号
     */
    private void addInitLogin(SysUser sysUser) {
        //获取初始化用户账号密码
        LoginNoDTO loginNo = new LoginNoDTO();
        loginNo.setUserId(sysUser.getId());
        loginNo.setType(CommonConstants.LOGIN_MOBILE);
        loginNo.setUserAccount(sysUser.getMobile());
        loginNo.setUserPwd(CommonConstants.DEFAULT_PWD);
        loginNoService.insert(loginNo);
    }

    /**
     * 修改系统管理员时对象的判断
     */
    private void updateSysUserCheckout(SysUser sysUser) {
        updaterCheckoutMobile(sysUser);
        updaterCheckoutIdCard(sysUser);
        updaterCheckoutMailbox(sysUser);
    }

    /**
     * 通过管理员登录账号修改登录账号
     */
    private void updateUserAccount(String newMobile, String oldMobile) {
        LoginNoDTO loginNoDTO = new LoginNoDTO();
        loginNoDTO.setType(CommonConstants.LOGIN_MOBILE);
        loginNoDTO.setNewUserAccount(newMobile);
        loginNoDTO.setUserAccount(oldMobile);
        loginNoService.updateUserAccount(loginNoDTO);
    }


    /**
     * 修改系统管理员校验手机号码合法性和唯一性
     *
     * @param sysUser 系统管理员
     */
    private void updaterCheckoutMobile(SysUser sysUser) {
        String newMobile = sysUser.getMobile();
        //当检测到管理员手机号码不为空时
        if (!StringUtils.isEmpty(newMobile)) {
            //通过新手机号查询用户
            SysUser user = mobileGetSysUser(newMobile);
            //如果用户存在的情况下判断两个用户ID是否一致，如果不一致证明用户修过的新手机号重复，如果一致证明用户没有修改过手机号
            if (user != null && !user.getId().equals(sysUser.getId())) {
                throw new BussinessException(ErrorInfoConstants.MOBILE_REPETITION);
            }
            //如果用户不存在的情况下证明手机号唯一且还被用户修改过
            if (user == null) {
                loginNoExist(CommonConstants.LOGIN_MOBILE, newMobile, get(sysUser.getId()).getMobile());
            }
        }
    }

    /**
     * 通过登录类型和账号判断登录账号表是否存在
     */
    private void loginNoExist(byte type, String newMobile, String oldMobile) {
        LoginNo loginNo = new LoginNo();
        loginNo.setUserAccount(newMobile);
        loginNo.setType(type);
        //登录账号唯一
        CompareSceneException.customObjectIsNotNull(loginNoService.loginNoExist(loginNo), ErrorInfoConstants.USER_ACCOUNT_REPETITION);
        updateUserAccount(newMobile, oldMobile);
    }

    /**
     * 修改系统管理员校验身份证合法性和唯一性
     */
    private void updaterCheckoutIdCard(SysUser sysUser) {
        String idCard = sysUser.getIdCard();
        //当检测到管理员身份证号码不为空时
        if (!StringUtils.isEmpty(idCard)) {
            //通过新身份证号查询用户
            SysUser user = idCardGetSysUser(idCard);
            //如果存在的情况下判断两个用户ID是否一致，如果不一致证明身份证号码重复
            if (user != null && !user.getId().equals(sysUser.getId())) {
                throw new BussinessException(ErrorInfoConstants.ID_CARD_REPETITION);
            }
        }
    }

    /**
     * 修改系统管理员校验邮箱合法性和唯一性
     */
    private void updaterCheckoutMailbox(SysUser sysUser) {
        String mailbox = sysUser.getMailbox();
        //当检测到管理员邮箱号码不为空时
        if (!StringUtils.isEmpty(mailbox)) {
            //通过新邮箱号查询用户
            SysUser user = mailboxGetSysUser(mailbox);
            //如果存在的情况下判断两个用户ID是否一致，如果不一致证明邮箱号码重复
            if (user != null && !user.getId().equals(sysUser.getId())) {
                throw new BussinessException(ErrorInfoConstants.MAILBOX_REPETITION);
            }
        }
    }
}
