package com.pbh.journey.system.app.controller;

import com.pbh.journey.system.app.controller.web.WebContoller;
import com.pbh.journey.system.app.service.SysDeptService;
import com.pbh.journey.system.common.result.JourneySystemAppResult;
import com.pbh.journey.system.pojo.domain.SysDept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pangbohuan
 * @description 系统部门控制器
 * @date 2018-11-05 17:01
 **/
@RestController
@RequestMapping("/sys_dept/")
@Api("系统部门相关的api")
public class SysDeptController extends WebContoller {


    @Resource
    private SysDeptService sysDeptService;


    /**
     * 查询所有部门列表忽视是否被逻辑删除
     */
    @GetMapping("find_all")
    public JourneySystemAppResult findAll() {
        return JourneySystemAppResult.ok(sysDeptService.findAll());
    }

    /**
     * 根据查询部门条件分页列表
     */
    @PostMapping("find_Page")
    public JourneySystemAppResult findPage(@RequestBody SysDept sysDept) {
        return JourneySystemAppResult.ok(sysDeptService.findPage(sysDept));
    }

    /**
     * 增加部门信息
     */
    @PutMapping("add")
    public JourneySystemAppResult add(@RequestBody SysDept sysDept) {
        sysDeptService.insert(sysDept);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量增加部门信息
     */
    @PutMapping("add_batch")
    public JourneySystemAppResult addBatch(@RequestBody List<SysDept> list) {
        sysDeptService.insertBatch(list);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改部门信信息
     */
    @PostMapping("change")
    public JourneySystemAppResult change(@RequestBody SysDept sysDept) {
        sysDeptService.update(sysDept);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量修改部门信息
     */
    @PostMapping("change_batch")
    public JourneySystemAppResult changeBatch(@RequestBody List<SysDept> list) {
        sysDeptService.updateBatch(list);
        return JourneySystemAppResult.ok();
    }

    /**
     * 物理删除部门信息
     */
    @DeleteMapping("delete")
    public JourneySystemAppResult delete(long id) {
        sysDeptService.delete(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除部门信息
     */
    @DeleteMapping("delete_logic")
    public JourneySystemAppResult deleteLogic(long id) {
        sysDeptService.deleteLogic(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除部门信息
     */
    @DeleteMapping("delete_batch")
    public JourneySystemAppResult deleteBatch(long[] ids) {
        sysDeptService.deleteBatch(ids);
        return JourneySystemAppResult.ok();
    }

    /**
     * 根据部门ID获取部门信息
     */
    @GetMapping("get")
    @ApiOperation(value = "根据Id查询部门信息", notes = "查询数据库中某个部门的信息")
    public JourneySystemAppResult get(long id) {
        return JourneySystemAppResult.ok(sysDeptService.get(id));
    }

    /**
     * 根据部门名获取部门信息
     */
    @GetMapping("get_dept_name")
    @ApiOperation(value = "根据部门名查询部门信息", notes = "查询数据库中某个部门的信息")
    public JourneySystemAppResult nameGetDept(String deptName) {
        return JourneySystemAppResult.ok(sysDeptService.nameGetDept(deptName));
    }

    /**
     * 修改部门禁启用状态
     */
    @PostMapping("use")
    public JourneySystemAppResult deptUse(@RequestBody SysDept sysDept) {
        sysDeptService.switchDept(sysDept);
        return JourneySystemAppResult.ok();
    }
}
