package com.pbh.journey.system.app.controller;

import com.pbh.journey.system.app.controller.web.WebContoller;
import com.pbh.journey.system.app.service.LoginLogService;
import com.pbh.journey.system.common.result.JourneySystemAppResult;
import com.pbh.journey.system.pojo.domain.LoginLog;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author pangbohuan
 * @description 登录日志控制器
 * @date 2018-11-01 15:41
 **/
@RestController
@RequestMapping("/login_log/")
@Api("登录日志相关的api")
@Slf4j
public class LoginLogController extends WebContoller {

    @Resource
    private LoginLogService loginLogService;

    /**
     * 查询登录日志所有列表忽视是否被逻辑删除
     */
    @GetMapping("find_all")
    public JourneySystemAppResult findAll() {
        return JourneySystemAppResult.ok(loginLogService.findAll());
    }

    /**
     * 分页列表查询
     */
    @PostMapping("find_Page")
    public JourneySystemAppResult findPage(@RequestBody LoginLog loginLog) {
        return JourneySystemAppResult.ok(loginLogService.findPage(loginLog));
    }

}
