package com.pbh.journey.system.common.utils.exception.auth;


import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.exception.BaseException;

/**
 * @author pangbohuan
 * @description ClientInvalidException
 * @date 2018-08-20 14:08
 **/
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
