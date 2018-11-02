package com.pbh.journey.system.pojo.dto;

import com.pbh.journey.system.pojo.domain.LoginNo;
import lombok.Data;

/**
 * DTO:LoginNoDTO
 *
 * @author pbh
 * @version 1.0
 * @since 2018-11-1
 */
@Data
public class LoginNoDTO extends LoginNo {

    //扩展自己的字段

    /**
     * 输入的新密码
     */
    private String newUserPwd;

    /**
     * 输入的第二次密码
     */
    private String newAffirmUserPwd;

    /**
     * 新的登录账号
     */
    private String newUserAccount;

    public LoginNoDTO() {
    }
}
