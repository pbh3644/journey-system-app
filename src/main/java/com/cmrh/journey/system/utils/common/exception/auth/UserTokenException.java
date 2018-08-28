package com.cmrh.journey.system.utils.common.exception.auth;


import com.cmrh.journey.system.utils.common.constant.CommonConstants;
import com.cmrh.journey.system.utils.common.exception.BaseException;

/**
 * @author pangbohuan
 * @description UserTokenException
 * @date 2018-08-20 14:05
 **/
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
