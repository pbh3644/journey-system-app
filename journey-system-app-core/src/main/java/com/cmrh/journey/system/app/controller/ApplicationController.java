package com.cmrh.journey.system.app.controller;

import com.cmrh.journey.system.app.controller.web.WebContoller;
import com.cmrh.journey.system.app.service.ApplicationService;
import com.cmrh.journey.system.common.base.pojo.Page;
import com.cmrh.journey.system.common.result.JourneySystemAppResult;
import com.cmrh.journey.system.pojo.domain.Application;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author：pbh
 * @Date：2018-09-09 11:04
 * @Description：微服务信息控制器
 */
@RestController
@RequestMapping("/app/")
public class ApplicationController extends WebContoller {

    @Resource
    private ApplicationService applicationService;


    /**
     * 查询分页列表测试
     */
    @GetMapping("listTest")
    public JourneySystemAppResult listTest() {
       /* Application application = new Application();
        application.setId(3L);
        application.setSAddTime(application.currentTime());
        application.setSAddUserId(123456L);
        application.setApplicationNameChinese("测试数据444");
        application.setApplicationNameEnglish("test44444");
        application.setApplicationIp(IpUtils.getRealIP(request));
        application.setSRemark("测试数据444....");*/
        Application application = applicationService.get(1);
        Page<Application> page = applicationService.findPage(application);
        return JourneySystemAppResult.ok(page);
    }

    /**
     * 根据查询条件分页列表
     */
    @PostMapping("find_Page")
    public JourneySystemAppResult findPage(@RequestBody Application application) {
        Page<Application> page = applicationService.findPage(application);
        return JourneySystemAppResult.ok(page);
    }

    /**
     * 增加微服务信息
     */
    @PutMapping("add")
    public JourneySystemAppResult add(@RequestBody Application application) {
        application.setAddTime(application.currentTime());
        application.setAddUserId(123456L);
        applicationService.insert(application);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改微服务信息
     */
    @PutMapping("updata")
    public JourneySystemAppResult updata(@RequestBody Application application) {
        application.setUpdateTime(application.currentTime());
        application.setUpdateUserId(123456L);
        applicationService.update(application);
        return JourneySystemAppResult.ok();
    }

    /**
     * 删除服务信息
     */
    @DeleteMapping("delete")
    public JourneySystemAppResult delete(long applicationId) {
        applicationService.delete(applicationId);
        return JourneySystemAppResult.ok();
    }

    /**
     * 根据服务ID获取服务信息
     */
    @GetMapping("get")
    public JourneySystemAppResult get(long applicationId) {
        Application application = applicationService.get(applicationId);
        return JourneySystemAppResult.ok(application);
    }

}
