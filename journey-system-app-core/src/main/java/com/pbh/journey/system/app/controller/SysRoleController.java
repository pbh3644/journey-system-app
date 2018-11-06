package com.pbh.journey.system.app.controller;

import com.pbh.journey.system.app.service.SysRoleService;
import com.pbh.journey.system.common.result.JourneySystemAppResult;
import com.pbh.journey.system.pojo.domain.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pangbohuan
 * @description 系统角色控制器
 * @date 2018-11-05 17:10
 **/
@RestController
@RequestMapping("/sys_role/")
@Api("系统角色相关的api")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 查询所有角色列表忽视是否被逻辑删除
     */
    @GetMapping("find_all")
    public JourneySystemAppResult findAll() {
        return JourneySystemAppResult.ok(sysRoleService.findAll());
    }

    /**
     * 根据查询角色条件分页列表
     */
    @PostMapping("find_Page")
    public JourneySystemAppResult findPage(@RequestBody SysRole sysRole) {
        return JourneySystemAppResult.ok(sysRoleService.findPage(sysRole));
    }

    /**
     * 增加角色信息
     */
    @PutMapping("add")
    public JourneySystemAppResult add(@RequestBody SysRole sysRole) {
        sysRoleService.insert(sysRole);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量增加角色信息
     */
    @PutMapping("add_batch")
    public JourneySystemAppResult addBatch(@RequestBody List<SysRole> list) {
        sysRoleService.insertBatch(list);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改角色信信息
     */
    @PostMapping("change")
    public JourneySystemAppResult change(@RequestBody SysRole sysRole) {
        sysRoleService.update(sysRole);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量修改角色信息
     */
    @PostMapping("change_batch")
    public JourneySystemAppResult changeBatch(@RequestBody List<SysRole> list) {
        sysRoleService.updateBatch(list);
        return JourneySystemAppResult.ok();
    }

    /**
     * 物理删除角色信息
     */
    @DeleteMapping("delete")
    public JourneySystemAppResult delete(long id) {
        sysRoleService.delete(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除角色信息
     */
    @DeleteMapping("delete_logic")
    public JourneySystemAppResult deleteLogic(long id) {
        sysRoleService.deleteLogic(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除角色信息
     */
    @DeleteMapping("delete_batch")
    public JourneySystemAppResult deleteBatch(long[] ids) {
        sysRoleService.deleteBatch(ids);
        return JourneySystemAppResult.ok();
    }

    /**
     * 根据角色ID获取角色信息
     */
    @GetMapping("get")
    @ApiOperation(value = "根据id查询角色信息", notes = "查询数据库中某个角色的信息")
    public JourneySystemAppResult get(long id) {
        return JourneySystemAppResult.ok(sysRoleService.get(id));
    }

    /**
     * 修改角色禁启用状态
     */
    @PostMapping("use")
    public JourneySystemAppResult roleUse(@RequestBody SysRole sysRole) {
        sysRoleService.switchRole(sysRole);
        return JourneySystemAppResult.ok();
    }
}
