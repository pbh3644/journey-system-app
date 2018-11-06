package com.pbh.journey.system.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pbh.journey.system.app.mapper.LoginNoMapper;
import com.pbh.journey.system.app.service.LoginLogService;
import com.pbh.journey.system.app.service.LoginNoService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.errorinfo.ErrorInfoConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import com.pbh.journey.system.common.utils.util.CurrentUserUtils;
import com.pbh.journey.system.common.utils.util.JwtTokenUtils;
import com.pbh.journey.system.pojo.domain.LoginLog;
import com.pbh.journey.system.pojo.domain.LoginNo;
import com.pbh.journey.system.pojo.dto.LoginNoDTO;
import com.pbh.journey.system.pojo.dto.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        operationLoginNoPre(entity);

        LoginNo loginNo = loginNoExist(entity.getUserAccount());
        if (loginNo != null) {
            throw new BussinessException(ErrorInfoConstants.MOBILE_REPETITION);
        }

        String userPwd = entity.getUserPwd();
        if (StringUtils.isEmpty(userPwd)) {
            throw new BussinessException(ErrorInfoConstants.PLEASE_ENTER_PASSWORD);
        }

        if (CommonConstants.ZERO == entity.getUserId()) {
            throw new BussinessException(ErrorInfoConstants.PLEASE_RELEVANCE_USER_ID);
        }

        //校验通过后给密码加密存入数据库
        entity.setUserPwd(passwordEncoder.encode(entity.getUserPwd()));
        super.insert(entity);
        log.warn("新用户注册成功:登录{账号为:" + entity.getUserAccount() + "类型为:" + entity.getType() + "}");
    }

    /**
     * 修改登录账号信息
     */
    @Override
    public void update(LoginNo entity) {
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
    @Override
    public String login(LoginNo loginNo) {
        operationLoginNoPre(loginNo);

        //用户输入的密码
        String importUserPwd = loginNo.getUserPwd();
        if (StringUtils.isEmpty(importUserPwd)) {
            throw new BussinessException(ErrorInfoConstants.PLEASE_ENTER_PASSWORD);
        }

        //根据账号和登录类型查询是否存在该用户是否被逻辑删除
        loginNo.setDeleteFlag(CommonConstants.DELETE_FLAG_NORMAL);
        SysUserDTO sysUserDTO = loginNoMapper.login(loginNo);

        if (sysUserDTO == null) {
            throw new BussinessException(ErrorInfoConstants.MOBILE_NO_REPETITION);
        }

        //比较密码是否正确
        if (!passwordEncoder.matches(importUserPwd, sysUserDTO.getUserPwd())) {
            throw new BussinessException(ErrorInfoConstants.PASSWORD_INCORRECTNESS);
        }

        //判断用户是否被逻辑删除
        if (CommonConstants.DELETE_FLAG_FREAK == sysUserDTO.getDeleteFlag()) {
            throw new BussinessException(ErrorInfoConstants.USER_FREEZE_DEL);
        }

        //判断用户状态是否被冻结
        if (CommonConstants.DELETE_FLAG_FREAK == sysUserDTO.getState()) {
            throw new BussinessException(ErrorInfoConstants.USER_FREEZE);
        }

        //判断用户的登录账号是否被逻辑删除
        if (CommonConstants.DELETE_FLAG_FREAK == sysUserDTO.getLoginNoDeleteFlag()) {
            throw new BussinessException(ErrorInfoConstants.ACCOUNT_FREEZE_DEL);
        }

        //验证通过后判断该用户是否是登录状态
        if (CurrentUserUtils.isExpiration(loginNo.getUserAccount())) {
            throw new BussinessException(ErrorInfoConstants.LOGIN_CONFLICT);
        }

        //登录校验成功后为了安全把用户密码置空
        sysUserDTO.setUserPwd("");

        //登录成功增加登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(sysUserDTO.getId());
        loginLog.setSystemCode(CommonConstants.SYSTEM_CODE);
        loginLog.setUserAccount(loginNo.getUserAccount());
        loginLogService.insert(loginLog);

        //把登录账号和用户信息放入redis
        String sysUserJson = JSONObject.toJSONString(sysUserDTO);
        if (!CurrentUserUtils.saveLoginUser(loginNo.getUserAccount(), sysUserJson, false)) {
            throw new BussinessException(ErrorInfoConstants.SYSTEM_ERROR);
        }
        //根据登录账号生成token返回给用户,每次用户请求到后台通过token串获取登录账号,方可获取登录账号,通过登录账号可以从redis获取用户信息
        String token = JwtTokenUtils.createToken(loginNo.getUserAccount(), false);
        log.warn("用户登录成功:登录{账号为:" + loginNo.getUserAccount() + "类型为:" + loginNo.getType() + "}");
        return token;
    }

    /**
     * 用户登出
     */
    @Override
    public void logout(HttpServletRequest request) {
        String accountToken = request.getHeader(CommonConstants.TOKEN);
        if (StringUtils.isEmpty(accountToken)) {
            throw new BussinessException(ErrorInfoConstants.NO_TOKEN);
        }
        //根据token获取登录账号
        String userAccount = JwtTokenUtils.getUserAccount(accountToken);
        if (CurrentUserUtils.isExpiration(userAccount)) {
            throw new BussinessException(ErrorInfoConstants.NO_LOGIN);
        }
        //从redis中删除登录账号
        CurrentUserUtils.delUserAccount(userAccount);
        log.warn("用户退出登录成功:登录{账号为:" + userAccount + "}");
    }

    /**
     * 判断登录账号是否存在
     */
    @Override
    public LoginNo loginNoExist(String loginNo) {
        if (StringUtils.isEmpty(loginNo)) {
            throw new BussinessException(ErrorInfoConstants.PLEASE_ENTER_USER_ACCOUNT);
        }
        return loginNoMapper.loginNoExist(loginNo);
    }

    /**
     * 修改密码
     */
    @Override
    public void updatePwd(LoginNoDTO loginNoDTO) {
        //原密码
        String userPwd = loginNoDTO.getUserPwd();
        if (StringUtils.isEmpty(userPwd)) {
            throw new BussinessException(ErrorInfoConstants.PLEASE_ENTER_ORIGINAL_PASSWORD);
        }

        //新密码
        String newUserPwd = loginNoDTO.getNewUserPwd();
        if (StringUtils.isEmpty(newUserPwd)) {
            throw new BussinessException(ErrorInfoConstants.PLEASE_ENTER_NEW_PASSWORD);
        }

        //再次确认新密码
        String newAffirmUserPwd = loginNoDTO.getNewAffirmUserPwd();
        if (StringUtils.isEmpty(newAffirmUserPwd)) {
            throw new BussinessException(ErrorInfoConstants.PLEASE_ENTER_AGAIN_NEW_PASSWORD);
        }

        if (!newAffirmUserPwd.equals(newUserPwd)) {
            throw new BussinessException(ErrorInfoConstants.NEW_AND_AGAIN_PASSWORD_DIFFER);
        }
        long id = loginNoDTO.getId();
        if (CommonConstants.ZERO == id) {
            throw new BussinessException(ErrorInfoConstants.ID_NOT_NULL);
        }

        LoginNo loginNo = get(id);
        byte deleteFlag = loginNo.getDeleteFlag();
        if (CommonConstants.DELETE_FLAG_FREAK == deleteFlag) {
            throw new BussinessException(ErrorInfoConstants.ACCOUNT_FREEZE_DEL);
        }

        //校验通过后给密码加密存入数据库
        loginNoDTO.setNewUserPwd(passwordEncoder.encode(newUserPwd));
        loginNoMapper.updatePwd(loginNoDTO);
        log.warn("用户修改密码成功:用户ID为" + loginNo.getUserId() + ",登录账号为" + loginNo.getUserAccount());
    }

    @Override
    public void updateUserAccount(LoginNoDTO loginNoDTO) {
        loginNoMapper.updateUserAccount(loginNoDTO);
        log.warn("用户修改登录成功:登录{账号为:" + loginNoDTO.getUserAccount() + "}");
    }

    /**
     * 登录账号通用的判断(预处理)
     */
    private void operationLoginNoPre(LoginNo entity) {
        if (StringUtils.isEmpty(entity.getUserAccount())) {
            throw new BussinessException(ErrorInfoConstants.PLEASE_ENTER_USER_ACCOUNT);
        }

        if (CommonConstants.ZERO == entity.getType()) {
            throw new BussinessException(ErrorInfoConstants.PLEASE_SELECT_LOGIN_TYPE);
        }
    }
}
