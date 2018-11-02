package com.pbh.journey.system.app.controller;

import com.pbh.journey.system.app.controller.web.WebContoller;
import com.pbh.journey.system.app.service.LoginNoService;
import com.pbh.journey.system.common.result.JourneySystemAppResult;
import com.pbh.journey.system.pojo.domain.LoginNo;
import com.pbh.journey.system.pojo.dto.LoginNoDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author pangbohuan
 * @description 登录账号控制器
 * @date 2018-11-01 15:43
 **/
@RestController
@RequestMapping("/login_no/")
@Api("登录账号相关的api")
@Slf4j
public class LoginNoController extends WebContoller {

    @Resource
    private LoginNoService loginNoService;

    /**
     * 查询登录账号所有列表忽视是否被逻辑删除
     */
    @GetMapping("find_all")
    public JourneySystemAppResult findAll() {
        return JourneySystemAppResult.ok(loginNoService.findAll());
    }


    /**
     * 分页列表查询系统登录账号
     */
    @PostMapping("find_Page")
    public JourneySystemAppResult findPage(@RequestBody LoginNo loginNo) {
        return JourneySystemAppResult.ok(loginNoService.findPage(loginNo));
    }

    /**
     * 增加登录账号
     */
    @PutMapping("add")
    public JourneySystemAppResult logout(@RequestBody LoginNo loginNo) {
        loginNoService.insert(loginNo);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改登录账号
     */
    @PostMapping("change")
    public JourneySystemAppResult change(@RequestBody LoginNo loginNo) {
        loginNoService.update(loginNo);
        return JourneySystemAppResult.ok();
    }

    /**
     * 逻辑删除登录账号
     */
    @DeleteMapping("delete_logic")
    public JourneySystemAppResult deleteLogic(long id) {
        loginNoService.deleteLogic(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 批量逻辑删除登录账号
     */
    @DeleteMapping("delete_batch")
    public JourneySystemAppResult deleteBatch(long[] ids) {
        loginNoService.deleteBatch(ids);
        return JourneySystemAppResult.ok();
    }

    /**
     * 物理删除登录账号
     */
    @DeleteMapping("delete")
    public JourneySystemAppResult delete(long id) {
        loginNoService.delete(id);
        return JourneySystemAppResult.ok();
    }

    /**
     * 获取登录账号信息
     */
    @GetMapping("get")
    public JourneySystemAppResult get(long id) {
        return JourneySystemAppResult.ok(loginNoService.get(id));
    }

    /**
     * 账号登录
     */
    @PostMapping("login")
    public JourneySystemAppResult login(@RequestBody LoginNo loginNo) {
        return JourneySystemAppResult.ok(loginNoService.login(loginNo));
    }

    /**
     * 账号登出
     */
    @PostMapping("logout")
    public JourneySystemAppResult logout() {
        loginNoService.logout(request);
        return JourneySystemAppResult.ok();
    }

    /**
     * 修改密码
     */
    @PostMapping("update_pwd")
    public JourneySystemAppResult updatePwd(@RequestBody LoginNoDTO loginNoDTO) {
        loginNoService.updatePwd(loginNoDTO);
        return JourneySystemAppResult.ok();
    }

    /**
     * 查询输入的账号是否存在
     */
    @GetMapping("login_no_exist")
    public JourneySystemAppResult loginNoExist(String userAccount) {
        LoginNo loginNo = loginNoService.loginNoExist(userAccount);
        if (loginNo != null) {
            return JourneySystemAppResult.ok();
        }
        return JourneySystemAppResult.ok("该用户不存在");
    }

}
