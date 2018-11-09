package com.pbh.journey.system.app.controller;

import com.pbh.journey.system.app.controller.web.WebContoller;
import com.pbh.journey.system.app.service.SysDeptPermissionService;
import com.pbh.journey.system.common.result.JourneySystemAppResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author pangbohuan
 * @description 部门权限控制器
 * @date 2018-11-09 10:28
 **/
@RestController
@RequestMapping("/dept_permission/")
@Api("部门权限相关的api")
@Slf4j
public class SysDeptPermissionController extends WebContoller {

    @Resource
    private SysDeptPermissionService sysDeptPermissionService;

    /**
     * 查看该部门拥有的权限
     */
    @GetMapping("get")
    public JourneySystemAppResult deptOfPermission(long id) {
        return JourneySystemAppResult.ok(sysDeptPermissionService.deptOfPermission(id));
    }
}
