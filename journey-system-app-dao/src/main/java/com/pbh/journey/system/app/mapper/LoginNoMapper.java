package com.pbh.journey.system.app.mapper;

import com.pbh.journey.system.common.base.mapper.BaseMapper;
import com.pbh.journey.system.pojo.domain.LoginNo;
import com.pbh.journey.system.pojo.dto.LoginNoDTO;
import com.pbh.journey.system.pojo.dto.SysUserDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Dao Interface:LoginNoMapper
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
@Mapper
public interface LoginNoMapper extends BaseMapper<LoginNo> {

    /**
     * 用户登录-通过账号-登录类型-登录
     * 查询出来了再判断用户是否已经被删除
     * 登录账号是否被删除
     *
     * @param loginNo
     * @return SysUserDTO 用户对象
     */
    SysUserDTO login(LoginNo loginNo);


    /**
     * 根据登录账号和登录类型查询账号是否存在
     *
     * @param loginNo LoginNo
     * @return LoginNo 登录账号表对象
     */
    LoginNo loginNoExist(LoginNo loginNo);


    /**
     * 修改密码
     *
     * @param loginNoDTO
     */
    void updatePwd(LoginNoDTO loginNoDTO);

    /**
     * 修改登录账号，根据手机号修改
     *
     * @param loginNoDTO 手机号和登录类型
     */
    void updateUserAccount(LoginNoDTO loginNoDTO);

}
