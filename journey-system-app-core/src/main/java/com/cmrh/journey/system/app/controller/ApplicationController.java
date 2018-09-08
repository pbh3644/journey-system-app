package com.cmrh.journey.system.app.controller;

import com.cmrh.journey.system.app.controller.web.WebContoller;
import com.cmrh.journey.system.app.service.ApplicationService;
import com.cmrh.journey.system.common.base.pojo.Page;
import com.cmrh.journey.system.common.result.JourneySystemAppResult;
import com.cmrh.journey.system.pojo.domain.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author：pbh
 * @Date：2018-09-08 19:45
 * @Description：微服务信息控制器
 */
@RestController
@RequestMapping("/app/")
public class ApplicationController extends WebContoller {

    @Resource
    ApplicationService applicationService;

    @GetMapping("list")
    public JourneySystemAppResult list() {
        Application application = new Application();
        application.setPage(new Page<>());
        List<Application> list = applicationService.findList(application);
        return JourneySystemAppResult.ok(list);
    }
}
