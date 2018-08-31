package com.cmrh.journey.system.app.controller;

import com.cmrh.journey.system.app.controller.web.WebContoller;
import com.cmrh.journey.system.app.pojo.SysUser;
import com.cmrh.journey.system.app.service.SysUserService;
import com.cmrh.journey.system.utils.result.JourneySystemAppResult;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pangbohuan
 * @description:SysUserContoller
 * @date 2018-07-25 17:30
 **/
@RestController
@RequestMapping("/sys_user/")
public class SysUserContoller extends WebContoller {

    @Resource
    SysUserService sysUserService;

    /**
     * 系统用户分页查询列表
     */
    @PostMapping("queryUserListPaged")
    public JourneySystemAppResult queryUserListPaged(SysUser sysUser, Integer page) {
        if (page == null) {
            page = 1;
        }
        List<SysUser> userList = sysUserService.queryUserListPaged(sysUser, page, pageSize);

        int count = sysUserService.selectCount(sysUser);
        int totalPages = (double) count / 10 > count / 10 ? (count / 10) + 1 : count / 10;

        Map result = new HashMap<>(4);
        result.put("count", count);
        result.put("totalPages", totalPages);
        result.put("list", userList);
        result.put("page", page);

        return JourneySystemAppResult.ok(result);
    }


    /**
     * 增加系统用户
     */
    @PostMapping("/saveUser")
    public JourneySystemAppResult saveUser(SysUser user) throws Exception {
        user.setId(getId());
        sysUserService.saveUser(user);
        return JourneySystemAppResult.ok("保存成功");
    }

    /**
     * 修改系统用户
     */
    @PostMapping("/updateUser")
    public JourneySystemAppResult updateUser(SysUser user) {
        sysUserService.updateUser(user);
        return JourneySystemAppResult.ok("保存成功");
    }

    /**
     * 删除系统用户
     */
    @Delete("/deleteUser")
    public JourneySystemAppResult deleteUser(String userId) {
        sysUserService.deleteUser(userId);
        return JourneySystemAppResult.ok("删除成功");
    }

    /**
     * 根据ID获取用户信息
     */
    @GetMapping("/queryUserById")
    public JourneySystemAppResult queryUserById(String userId) {
        SysUser sysUser = sysUserService.queryUserById(userId);
        return JourneySystemAppResult.ok(sysUser);
    }
}
