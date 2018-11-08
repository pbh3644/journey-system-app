package com.pbh.journey.system.app.controller;

import com.pbh.journey.system.app.controller.web.WebContoller;
import com.pbh.journey.system.app.service.ApplicationService;
import com.pbh.journey.system.common.result.JourneySystemAppResult;
import com.pbh.journey.system.pojo.domain.Application;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author：pbh
 * @Date：2018-09-09 11:04
 * @Description：微服务信息控制器
 */
@RestController
@RequestMapping("/app/")
@Api("微服务信息相关的api")
@Slf4j
public class ApplicationController extends WebContoller {

    @Resource
    private ApplicationService applicationService;

    /**
     * 查询所有微服务列表忽视是否被逻辑删除
     */
    @GetMapping("find_all")
    public JourneySystemAppResult findAll() {
        return JourneySystemAppResult.ok(applicationService.findAll());
    }

    /**
     * 根据查询微服务条件分页列表
     */
    @PostMapping("find_Page")
    public JourneySystemAppResult findPage(@RequestBody Application application) {
        return JourneySystemAppResult.ok(applicationService.findPage(application));
    }

    /**
     * 增加微服务信息
     */
    @PutMapping("add")
    public JourneySystemAppResult add(@RequestBody Application application) {
        applicationService.insert(application);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量增加微服务信息
     */
    @PutMapping("add_batch")
    public JourneySystemAppResult addBatch(@RequestBody List<Application> list) {
        applicationService.insertBatch(list);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改微服务信息
     */
    @PostMapping("change")
    public JourneySystemAppResult change(@RequestBody Application application) {
        applicationService.update(application);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量修改微服务信息
     */
    @PostMapping("change_batch")
    public JourneySystemAppResult changeBatch(@RequestBody List<Application> list) {
        applicationService.updateBatch(list);
        return JourneySystemAppResult.ok();
    }

    /**
     * 物理删除服务信息
     */
    @DeleteMapping("delete")
    public JourneySystemAppResult delete(long id) {
        applicationService.delete(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除服务信息
     */
    @DeleteMapping("delete_logic")
    public JourneySystemAppResult deleteLogic(long id) {
        applicationService.deleteLogic(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除服务信息
     */
    @DeleteMapping("delete_batch")
    public JourneySystemAppResult deleteBatch(long[] ids) {
        applicationService.deleteBatch(ids);
        return JourneySystemAppResult.ok();
    }

    /**
     * 根据微服务ID获取服务信息
     */
    @GetMapping("get")
    @ApiOperation(value = "根据applicationId查询微服务信息", notes = "查询数据库中某个微服务的信息")
    public JourneySystemAppResult get(long id) {
        return JourneySystemAppResult.ok(applicationService.get(id));
    }

    /**
     * 根据微服务的中文名字精准查询微服务
     */
    @GetMapping("get_app_name_chinese")
    public JourneySystemAppResult uniquenessApplicationNameChinese(String applicationNameChinese) {
        return JourneySystemAppResult.ok(applicationService.uniquenessApplicationNameChinese(applicationNameChinese));
    }

    /**
     * 根据微服务的英文名字精准查询微服务
     */
    @GetMapping("get_app_name_english")
    public JourneySystemAppResult uniquenessApplicationNameEnglish(String applicationNameEnglish) {
        return JourneySystemAppResult.ok(applicationService.uniquenessApplicationNameEnglish(applicationNameEnglish));
    }

}
