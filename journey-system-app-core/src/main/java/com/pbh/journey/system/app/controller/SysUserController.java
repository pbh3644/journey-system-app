package com.pbh.journey.system.app.controller;

import com.pbh.journey.system.app.controller.web.WebContoller;
import com.pbh.journey.system.app.service.SysUserService;
import com.pbh.journey.system.common.result.JourneySystemAppResult;
import com.pbh.journey.system.pojo.domain.SysUser;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author pangbohuan
 * @description 系统用户控制器
 * @date 2018-11-01 15:44
 **/
@RestController
@RequestMapping("/sys_user/")
@Api("系统管理员相关的api")
@Slf4j
public class SysUserController extends WebContoller {

    @Resource
    private SysUserService sysUserService;

    /**
     * 查询系统管理员所有列表忽视是否被逻辑删除
     */
    @GetMapping("find_all")
    public JourneySystemAppResult findAll() {
        return JourneySystemAppResult.ok(sysUserService.findAll());
    }


    /**
     * 分页查询系统管理员列表
     */
    @PostMapping("find_Page")
    public JourneySystemAppResult findPage(@RequestBody SysUser sysUser) {
        return JourneySystemAppResult.ok(sysUserService.findPage(sysUser));
    }

    /**
     * 增加系统管理员
     */
    @PutMapping("add")
    public JourneySystemAppResult add(@RequestBody SysUser sysUser) {
        sysUserService.insert(sysUser);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改系统管理员
     */
    @PostMapping("change")
    public JourneySystemAppResult change(@RequestBody SysUser sysUser) {
        sysUserService.update(sysUser);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除系统管理员
     */
    @DeleteMapping("delete_logic")
    public JourneySystemAppResult deleteLogic(long id) {
        sysUserService.deleteLogic(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量逻辑删除系统管理员
     */
    @DeleteMapping("delete_batch")
    public JourneySystemAppResult deleteBatch(long[] ids) {
        sysUserService.deleteBatch(ids);
        return JourneySystemAppResult.ok();
    }

    /**
     * 物理删除系统管理员
     */
    @DeleteMapping("delete")
    public JourneySystemAppResult delete(long id) {
        sysUserService.delete(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 根据ID获取系统管理员信息
     */
    @GetMapping("get")
    public JourneySystemAppResult get(long id) {
        return JourneySystemAppResult.ok(sysUserService.get(id));
    }

    /**
     * 根据手机号码获取系统管理员信息
     */
    @GetMapping("get_mobile")
    public JourneySystemAppResult mobileGetSysUser(String mobile) {
        return JourneySystemAppResult.ok(sysUserService.mobileGetSysUser(mobile));
    }

    /**
     * 根据身份证号码获取系统管理员信息
     */
    @GetMapping("get_idCard")
    public JourneySystemAppResult idCardGetSysUser(String idCard) {
        return JourneySystemAppResult.ok(sysUserService.idCardGetSysUser(idCard));
    }

    /**
     * 根据邮箱号码获取系统管理员信息
     */
    @GetMapping("get_mailbox")
    public JourneySystemAppResult mailboxGetSysUser(String mailbox) {
        return JourneySystemAppResult.ok(sysUserService.mailboxGetSysUser(mailbox));
    }
}
