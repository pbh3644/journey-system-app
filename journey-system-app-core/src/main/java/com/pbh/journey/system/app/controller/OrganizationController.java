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
     * 查询分页列表测试
     */
    @GetMapping("listTest")
    public JourneySystemAppResult listTest() {
        Organization organization = new Organization();
        organization.setPage(new Page<>());
        Page<Organization> page = organizationService.findPage(organization);
        return JourneySystemAppResult.ok(page);
    }

    /**
     * 根据查询条件分页列表
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
        organization.setAddTime(organization.currentTime());
        organization.setAddUserId(123456L);
        organizationService.insert(organization);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改数据库表信息
     */
    @PutMapping("updata")
    public JourneySystemAppResult updata(@RequestBody Organization organization) {
        organization.setUpdateTime(organization.currentTime());
        organization.setUpdateUserId(123456L);
        organizationService.update(organization);
        return JourneySystemAppResult.ok();
    }

    /**
     * 删除数据库表信息
     */
    @DeleteMapping("delete")
    public JourneySystemAppResult delete(long organizationId) {
        organizationService.delete(organizationId);
        return JourneySystemAppResult.ok();
    }

    /**
     * 根据数据库表ID获取数据库表信息
     */
    @ApiOperation(value = "根据organizationId查询表信息", notes = "查询数据库中某个表的信息")
    @GetMapping("get")
    public JourneySystemAppResult get(long organizationId) {
        Organization organization = organizationService.get(organizationId);
        return JourneySystemAppResult.ok(organization);
    }
}
