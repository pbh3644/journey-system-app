package com.pbh.journey.system.app.service.impl;

import com.pbh.journey.system.app.mapper.LoginLogMapper;
import com.pbh.journey.system.app.service.LoginLogService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.base.service.impl.BaseServiceImpl;
import com.pbh.journey.system.pojo.domain.LoginLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ServiceImpl:LoginLogServiceImpl
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
@Service("loginLogService")
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    /**
     * 查询全部登录日志列表
     */
    @Override
    public List<LoginLog> findAll() {
        return super.findAll();
    }

    /**
     * 根据查询条件查询日志列表分页
     */
    @Override
    public Page<LoginLog> findPage(LoginLog loginLog) {
        return super.findPage(loginLog);
    }
}
