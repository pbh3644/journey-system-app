package com.pbh.journey.system.app.controller;

import com.pbh.journey.system.app.controller.web.WebContoller;
import com.pbh.journey.system.app.service.OrganizationService;
import com.pbh.journey.system.common.base.pojo.Page;
import com.pbh.journey.system.common.result.JourneySystemAppResult;
import com.pbh.journey.system.pojo.domain.Organization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author：pbh
 * @Date：2018-09-09 11:32
 * @Description：表信息控制器
 */
@RestController
@RequestMapping("/org/")
@Api("表信息相关的api")
public class OrganizationController extends WebContoller {

    @Resource
    private OrganizationService organizationService;

    /**
     * 查询所有数据库表信息列表忽视是否被逻辑删除
     */
    @GetMapping("find_all")
    public JourneySystemAppResult findAll() {
        return JourneySystemAppResult.ok(organizationService.findAll());
    }

    /**
     * 根据数据库表查询条件分页列表
     */
    @PostMapping("find_page")
    public JourneySystemAppResult findPage(@RequestBody Organization organization) {
        Page<Organization> page = organizationService.findPage(organization);
        return JourneySystemAppResult.ok(page);
    }

    /**
     * 增加数据库表信息
     */
    @PutMapping("add")
    public JourneySystemAppResult add(@RequestBody Organization organization) {
        organizationService.insert(organization);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量增加数据库表信息
     */
    @PutMapping("add_batch")
    public JourneySystemAppResult addBatch(@RequestBody List<Organization> list) {
        organizationService.insertBatch(list);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改数据库表信息
     */
    @PutMapping("change")
    public JourneySystemAppResult change(@RequestBody Organization organization) {
        organizationService.update(organization);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量修改数据库表信息
     */
    @PutMapping("change_batch")
    public JourneySystemAppResult changeBatch(@RequestBody List<Organization> list) {
        organizationService.updateBatch(list);
        return JourneySystemAppResult.ok();
    }


    /**
     * 物理删除数据库表信息
     */
    @DeleteMapping("delete")
    public JourneySystemAppResult delete(long id) {
        organizationService.delete(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除数据库表信息
     */
    @DeleteMapping("delete_logic")
    public JourneySystemAppResult deleteLogic(long id) {
        organizationService.deleteLogic(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除数据库表信息
     */
    @DeleteMapping("delete_batch")
    public JourneySystemAppResult deleteBatch(long[] ids) {
        organizationService.deleteBatch(ids);
        return JourneySystemAppResult.ok();
    }


    /**
     * 根据数据库表ID获取数据库表信息
     */
    @ApiOperation(value = "根据organizationId查询表信息", notes = "查询数据库中某个表的信息")
    @GetMapping("get")
    public JourneySystemAppResult get(long id) {
        Organization organization = organizationService.get(id);
        return JourneySystemAppResult.ok(organization);
    }
}
