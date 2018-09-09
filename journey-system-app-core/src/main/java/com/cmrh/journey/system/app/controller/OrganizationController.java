package com.cmrh.journey.system.app.controller;

import com.cmrh.journey.system.app.controller.web.WebContoller;
import com.cmrh.journey.system.app.service.OrganizationService;
import com.cmrh.journey.system.common.base.pojo.Page;
import com.cmrh.journey.system.common.result.JourneySystemAppResult;
import com.cmrh.journey.system.pojo.domain.Organization;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author：pbh
 * @Date：2018-09-09 11:32
 * @Description：表信息控制器
 */
@RestController
@RequestMapping("/org/")
public class OrganizationController extends WebContoller {

    @Resource
    OrganizationService organizationService;

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
    @PostMapping("findPage")
    public JourneySystemAppResult findPage(@RequestBody Organization organization) {
        Page<Organization> page = organizationService.findPage(organization);
        return JourneySystemAppResult.ok(page);
    }

    /**
     * 增加数据库表信息
     */
    @PostMapping("add")
    public JourneySystemAppResult add(@RequestBody Organization organization) {
        organization.setSAddTime(organization.currentTime());
        organization.setSAddUserId(123456L);
        organizationService.insert(organization);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改数据库表信息
     */
    @PostMapping("updata")
    public JourneySystemAppResult updata(@RequestBody Organization organization) {
        organization.setSUpdateTime(organization.currentTime());
        organization.setSUpdateUserId(123456L);
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
    @GetMapping("get")
    public JourneySystemAppResult get(long organizationId) {
        Organization organization = organizationService.get(organizationId);
        return JourneySystemAppResult.ok(organization);
    }
}
