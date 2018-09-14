package com.pbh.journey.system.common.utils.exception.auth;


import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;

/**
 * @author pangbohuan
 * @description UserTokenException
 * @date 2018-08-20 14:05
 **/
public class UserTokenException extends BussinessException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
