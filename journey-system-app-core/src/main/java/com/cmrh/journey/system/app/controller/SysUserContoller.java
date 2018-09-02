package com.cmrh.journey.system.app.controller;

import com.cmrh.journey.system.app.controller.web.WebContoller;
import com.cmrh.journey.system.app.pojo.SysUser;
import com.cmrh.journey.system.app.service.SysUserService;
import com.cmrh.journey.system.common.result.JourneySystemAppResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pangbohuan
 * @description:SysUserContoller
 * @date 2018-07-25 17:30
 **/
@RestController
@RequestMapping("/sys_user/")
public class SysUserContoller extends WebContoller<SysUserContoller> {

    @Resource
    SysUserService sysUserService;

    @GetMapping("")
    public JourneySystemAppResult queryUserListPaged() {
        return JourneySystemAppResult.ok(getId());
    }

    /**
     * 系统用户分页查询列表
     */
    @PostMapping("queryUserListPaged")
    public JourneySystemAppResult queryUserListPaged(SysUser sysUser) {
        List<SysUser> userList = sysUserService.queryUserListPaged(sysUser);
        int count = sysUserService.selectCount(sysUser);
        return JourneySystemAppResult.queryList(userList, count);
    }

    /**
     * 增加系统用户
     */
    @PostMapping("/saveUser")
    public JourneySystemAppResult saveUser(SysUser user) {
        user.setId(getId());
        sysUserService.saveUser(user);
        return JourneySystemAppResult.ok("保存成功");
    }

    /**
     * 修改系统用户
     */
    @PostMapping("/updateUser")
    public JourneySystemAppResult updateUser(SysUser user) {
        sysUserService.updateUser(user);
        return JourneySystemAppResult.ok("保存成功");
    }

    /**
     * 删除系统用户
     */
    @PostMapping("/deleteUser")
    public JourneySystemAppResult deleteUser(String userId) {
        sysUserService.deleteUser(userId);
        return JourneySystemAppResult.ok("删除成功");
    }

    /**
     * 根据ID获取用户信息
     */
    @GetMapping("/queryUserById")
    public JourneySystemAppResult queryUserById(String userId) {
        SysUser sysUser = sysUserService.queryUserById(userId);
        return JourneySystemAppResult.ok(sysUser);
    }
}
