package com.pbh.journey.system.app.controller;

import com.pbh.journey.system.app.controller.web.WebContoller;
import com.pbh.journey.system.app.service.SysPermissionService;
import com.pbh.journey.system.common.result.JourneySystemAppResult;
import com.pbh.journey.system.pojo.domain.SysPermission;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pangbohuan
 * @description 系统权限资源控制器
 * @date 2018-11-07 10:50
 **/
@RestController
@RequestMapping("/sys_permission/")
public class SysPermissionController extends WebContoller {


    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 查询所有权限资源列表忽视是否被逻辑删除
     */
    @GetMapping("find_all")
    public JourneySystemAppResult findAll() {
        return JourneySystemAppResult.ok(sysPermissionService.findAll());
    }

    /**
     * 根据查询权限资源条件分页列表
     */
    @PostMapping("find_Page")
    public JourneySystemAppResult findPage(@RequestBody SysPermission sysPermission) {
        return JourneySystemAppResult.ok(sysPermissionService.findPage(sysPermission));
    }

    /**
     * 增加权限资源
     */
    @PutMapping("add")
    public JourneySystemAppResult add(@RequestBody SysPermission sysPermission) {
        sysPermissionService.insert(sysPermission);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量增加权限资源信息
     */
    @PutMapping("add_batch")
    public JourneySystemAppResult addBatch(@RequestBody List<SysPermission> list) {
        sysPermissionService.insertBatch(list);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改权限资源信息
     */
    @PostMapping("change")
    public JourneySystemAppResult change(@RequestBody SysPermission sysPermission) {
        sysPermissionService.update(sysPermission);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量修改权限资源信息
     */
    @PostMapping("change_batch")
    public JourneySystemAppResult changeBatch(@RequestBody List<SysPermission> list) {
        sysPermissionService.updateBatch(list);
        return JourneySystemAppResult.ok();
    }

    /**
     * 物理删除权限资源信息
     */
    @DeleteMapping("delete")
    public JourneySystemAppResult delete(long id) {
        sysPermissionService.delete(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除权限资源信息
     */
    @DeleteMapping("delete_logic")
    public JourneySystemAppResult deleteLogic(long id) {
        sysPermissionService.deleteLogic(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除权限资源信息
     */
    @DeleteMapping("delete_batch")
    public JourneySystemAppResult deleteBatch(long[] ids) {
        sysPermissionService.deleteBatch(ids);
        return JourneySystemAppResult.ok();
    }

    /**
     * 根据ID获取权限资源信息
     */
    @GetMapping("get")
    @ApiOperation(value = "根据Id查询权限资源信息", notes = "查询数据库中某个权限资源的信息")
    public JourneySystemAppResult get(long id) {
        return JourneySystemAppResult.ok(sysPermissionService.get(id));
    }

    /**
     * 根据url获取权限资源信息
     */
    @GetMapping("get_url")
    public JourneySystemAppResult get(String url) {
        return JourneySystemAppResult.ok(sysPermissionService.urlGetPermission(url));
    }
}
