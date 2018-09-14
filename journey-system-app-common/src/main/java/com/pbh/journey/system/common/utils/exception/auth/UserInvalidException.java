package com.pbh.journey.system.common.utils.exception.auth;


import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;

/**
 * @author pangbohuan
 * @description UserInvalidException
 * @date 2018-08-20 14:06
 **/
public class UserInvalidException extends BussinessException {

    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
