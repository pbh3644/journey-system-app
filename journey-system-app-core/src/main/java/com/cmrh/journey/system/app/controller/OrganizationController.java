package com.cmrh.journey.system.app.controller;

import com.cmrh.journey.system.app.controller.web.WebContoller;
import com.cmrh.journey.system.app.service.OrganizationService;
import com.cmrh.journey.system.common.base.pojo.Page;
import com.cmrh.journey.system.common.result.JourneySystemAppResult;
import com.cmrh.journey.system.pojo.domain.Organization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author：pbh
 * @Date：2018-09-08 19:49
 * @Description：数据中心控制器
 */
@RestController
@RequestMapping("/org/")
public class OrganizationController extends WebContoller {

    @Resource
    OrganizationService organizationService;


    @GetMapping("list")
    public JourneySystemAppResult list() {
        Organization organization = new Organization();
        organization.setPage(new Page<>());
        List<Organization> list = organizationService.findList(organization);
        System.out.println(list + "organizationService");
        return JourneySystemAppResult.ok(list);
    }
}
