package com.pbh.journey.system.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pbh.journey.system.app.mapper.LoginNoMapper;
import com.pbh.journey.system.app.service.LoginLogService;
import com.pbh.journey.system.app.service.LoginNoService;
import com.pbh.journey.system.app.service.SysUserService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.common.utils.util.CompareSceneException;
import com.pbh.journey.system.common.utils.util.CurrentUserUtils;
import com.pbh.journey.system.common.utils.util.JwtTokenUtils;
import com.pbh.journey.system.pojo.domain.LoginLog;
import com.pbh.journey.system.pojo.domain.LoginNo;
import com.pbh.journey.system.pojo.domain.SysUser;
import com.pbh.journey.system.pojo.dto.LoginNoDTO;
import com.pbh.journey.system.pojo.dto.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ServiceImpl:LoginNoServiceImpl
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
@Slf4j
@Service("loginNoService")
public class LoginNoServiceImpl extends BaseServiceImpl<LoginNoMapper, LoginNo> implements LoginNoService {

    /**
     * security加密对象
     */
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private LoginNoMapper loginNoMapper;

    @Resource
    private LoginLogService loginLogService;

    @Resource
    private SysUserService sysUserService;

    /**
     * 查询全部登录账号列表
     */
    @Override
    @Cacheable("LoginNoServiceImpl")
    public List<LoginNo> findAll() {
        return super.findAll();
    }


    /**
     * 根据查询条件查询登录账号列表分页
     */
    @Override
    @Cacheable(value = "LoginNoServiceImpl", key = "#LoginNo")
    public Page<LoginNo> findPage(LoginNo loginNo) {
        return super.findPage(loginNo);
    }


    /**
     * 新增登录账号
     */
    @Override
    public void insert(LoginNo entity) {
        //增加前校验
        addCheckout(entity);
        super.insert(entity);
        log.warn("新用户注册成功:登录{账号为:" + entity.getUserAccount() + "类型为:" + entity.getType() + "}");
    }

    /**
     * 修改登录账号信息
     */
    @Override
    public void update(LoginNo entity) {
        LoginNo loginNo = loginNoExist(entity);
        if (loginNo != null && !loginNo.getId().equals(entity.getId())) {
            throw new BussinessException(ErrorInfoConstants.USER_ACCOUNT_REPETITION);
        }
        super.update(entity);
        log.warn("用户修改登录账号信息成功:登录{账号为:" + entity.getUserAccount() + "类型为:" + entity.getType() + "}");
    }

    /**
     * 单个物理删除
     */
    @Override
    public void delete(long id) {
        super.delete(id);
        log.warn("物理删除登录信息成功：id为：" + id);
    }

    /**
     * 单个逻辑删除
     */
    @Override
    public void deleteLogic(long id) {
        super.deleteLogic(id);
        log.warn("逻辑删除登录信息成功：id为：" + id);
    }

    /**
     * 根据批量ID逻辑删除
     */
    @Override
    public void deleteBatch(long[] ids) {
        super.deleteBatch(ids);
        log.warn("批量逻辑删除登录信息成功：这批登录信息的id为：" + ids);
    }

    @Override
    public LoginNo get(long id) {
        return super.get(id);
    }

    /**
     * 用户登录
     */
    @CacheEvict(value = "LoginLogServiceImpl", allEntries = true)
    @Override
    public String login(LoginNo loginNo) {
        loginNoAndTypeNotNull(loginNo);

        //校验登录合法性-返回该登录账号隶属用户
        SysUserDTO sysUserDTO = loginNoCheckout(loginNo);

        //把登录账号(key)和用户信息(val)转成JSON串放入redis作缓存
        String userAccount = loginNo.getUserAccount();
        if (!CurrentUserUtils.saveLoginUser(userAccount, JSONObject.toJSONString(sysUserDTO), false)) {
            throw new BussinessException(ErrorInfoConstants.SYSTEM_ERROR);
        }

        //根据登录账号生成token返回给用户,每次用户请求到后台通过token串获取登录账号,方可获取登录账号,通过登录账号可以从redis获取用户信息
        String token = JwtTokenUtils.createToken(userAccount, false);

        //登录成功加入日志
        log.warn("用户登录成功:登录{账号为:" + userAccount + "类型为:" + loginNo.getType() + "}");
        loginSucceed(sysUserDTO.getId(), userAccount);
        return token;
    }

    /**
     * 用户登出
     */
    @Override
    public void logout(HttpServletRequest request) {
        String accountToken = request.getHeader(CommonConstants.TOKEN);
        CompareSceneException.customStringIsNull(accountToken, ErrorInfoConstants.NO_TOKEN);

        //根据token获取登录账号
        String userAccount = JwtTokenUtils.getUserAccount(accountToken);
        CompareSceneException.customStringIsNull(userAccount, ErrorInfoConstants.NO_LOGIN);

        //从redis中删除登录账号
        CurrentUserUtils.delUserAccount(userAccount);
        log.warn("用户退出登录成功:登录{账号为:" + userAccount + "}");
    }

    /**
     * 根据登录账号的类型获取登录账号信息
     */
    @Override
    public LoginNo loginNoExist(LoginNo loginNo) {
        loginNoAndTypeNotNull(loginNo);
        return loginNoMapper.loginNoExist(loginNo);
    }

    /**
     * 修改密码
     */
    @Override
    public void updatePwd(LoginNoDTO loginNoDTO) {
        LoginNo loginNo = updatePwdCheckout(loginNoDTO);
        loginNoMapper.updatePwd(loginNoDTO);

        //更新系统管理员最近修改密码时间
        SysUser sysUser = new SysUser();
        sysUser.setId(loginNo.getUserId());
        sysUser.setLastPwdmodTime(sysUser.currentTime());
        sysUserService.update(sysUser);

        log.warn("用户修改密码成功:用户ID为" + loginNo.getUserId() + ",登录账号为" + loginNo.getUserAccount());
    }

    /**
     * 通过登录账号修改登录账号
     * 目前只有修改系统管理员的手机号码时才会调用这个方法同步把登录账号的手机号码更改掉
     */
    @Override
    public void updateUserAccount(LoginNoDTO loginNoDTO) {
        loginNoAndTypeNotNull(loginNoDTO);
        loginNoMapper.updateUserAccount(loginNoDTO);
        log.warn("用户修改登录账号成功:登录{账号为:" + loginNoDTO.getUserAccount() + "}");
    }

    /**
     * 登录账号和登录类型不能为空
     */
    private void loginNoAndTypeNotNull(LoginNo entity) {
        CompareSceneException.customStringIsNull(entity.getUserAccount(), ErrorInfoConstants.PLEASE_ENTER_USER_ACCOUNT);
        CompareSceneException.customNumericalEquality(CommonConstants.ZERO, entity.getType(), ErrorInfoConstants.PLEASE_SELECT_LOGIN_TYPE);
    }

    /**
     * 新增登录账号判断
     */
    private void addCheckout(LoginNo entity) {
        //判断登录账号是否已存在
        CompareSceneException.customObjectIsNotNull(loginNoExist(entity), ErrorInfoConstants.USER_ACCOUNT_REPETITION);
        //必须输入登录密码
        String userPwd = entity.getUserPwd();
        CompareSceneException.customStringIsNull(userPwd, ErrorInfoConstants.PLEASE_ENTER_PASSWORD);

        //增加登录账号时如果没有关联用户ID给登录账号关联当前用户ID
        if (CommonConstants.ZERO == entity.getUserId()) {
            entity.setUserId(CurrentUserUtils.getCurrentUserId());
        }
        //校验通过后给密码加密
        entity.setUserPwd(passwordEncoder.encode(userPwd));
    }

    /**
     * 用户登录前需要的判断
     */
    private SysUserDTO loginNoCheckout(LoginNo loginNo) {
        //用户输入的密码
        String importUserPwd = loginNo.getUserPwd();
        CompareSceneException.customStringIsNull(importUserPwd, ErrorInfoConstants.PLEASE_ENTER_PASSWORD);

        //根据账号和登录类型查询该用户
        SysUserDTO sysUserDTO = loginNoMapper.login(loginNo);
        CompareSceneException.customObjectIsNull(sysUserDTO, ErrorInfoConstants.ACCOUNT_NO_REPETITION);
        //判断输入的用户密码是否正确
        importLoginUserPwdCorrect(importUserPwd, sysUserDTO.getUserPwd());

        //判断用户是否被逻辑删除
        CompareSceneException.customNumericalEquality(CommonConstants.DELETE_FLAG_FREAK, sysUserDTO.getDeleteFlag(), ErrorInfoConstants.USER_FREEZE_DEL);

        //判断用户状态是否被冻结
        CompareSceneException.customNumericalEquality(CommonConstants.USER_FREEZE, sysUserDTO.getState(), ErrorInfoConstants.USER_FREEZE);

        //判断用户的登录账号是否被逻辑删除
        CompareSceneException.customNumericalEquality(CommonConstants.DELETE_FLAG_FREAK, sysUserDTO.getLoginNoDeleteFlag(), ErrorInfoConstants.ACCOUNT_FREEZE_DEL);

        //验证通过后判断该用户是否是登录状态
        userAccountIsNotLogin(loginNo.getUserAccount());

        //登录校验成功后为了安全把用户密码置空
        sysUserDTO.setUserPwd("");
        return sysUserDTO;
    }

    /**
     * 校验输入的登录密码是否输入正确
     */
    private void importLoginUserPwdCorrect(String importUserPwd, String userPwd) {
        if (!passwordEncoder.matches(importUserPwd, userPwd)) {
            throw new BussinessException(ErrorInfoConstants.PASSWORD_INCORRECTNESS);
        }
    }

    /**
     * 判断这个用户是否正在登录
     */
    private void userAccountIsNotLogin(String userAccount) {
        if (CurrentUserUtils.isExpiration(userAccount)) {
            throw new BussinessException(ErrorInfoConstants.LOGIN_CONFLICT);
        }
    }

    /**
     * 登录成功后往数据库加入日志
     */
    private void loginSucceed(long userId, String userAccount) {
        //登录成功增加登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(userId);
        loginLog.setSystemCode(CommonConstants.SYSTEM_CODE);
        loginLog.setUserAccount(userAccount);
        loginLogService.insert(loginLog);
    }

    /**
     * 修改密码时候需要的判断
     */
    private LoginNo updatePwdCheckout(LoginNoDTO loginNoDTO) {
        //原密码
        String userPwd = loginNoDTO.getUserPwd();
        CompareSceneException.customStringIsNull(userPwd, ErrorInfoConstants.PLEASE_ENTER_ORIGINAL_PASSWORD);

        LoginNo loginNo = get(loginNoDTO.getId());
        //比较输入的登录旧密码是否正确
        importLoginUserPwdCorrect(userPwd, loginNo.getUserPwd());

        //新密码
        String newUserPwd = loginNoDTO.getNewUserPwd();
        CompareSceneException.customStringIsNull(newUserPwd, ErrorInfoConstants.PLEASE_ENTER_NEW_PASSWORD);

        //再次确认新密码
        String newAffirmUserPwd = loginNoDTO.getNewAffirmUserPwd();
        CompareSceneException.customStringIsNull(newAffirmUserPwd, ErrorInfoConstants.PLEASE_ENTER_AGAIN_NEW_PASSWORD);

        //输入的两次新密码是否一致
        CompareSceneException.customStringNotEquality(newUserPwd, newAffirmUserPwd, ErrorInfoConstants.NEW_AND_AGAIN_PASSWORD_DIFFER);

        //校验这个账号是否被逻辑删除
        CompareSceneException.customNumericalEquality(CommonConstants.DELETE_FLAG_FREAK, loginNo.getDeleteFlag(), ErrorInfoConstants.ACCOUNT_FREEZE_DEL);

        //校验通过后给密码加密
        loginNoDTO.setNewUserPwd(passwordEncoder.encode(newUserPwd));
        return loginNo;
    }
}
