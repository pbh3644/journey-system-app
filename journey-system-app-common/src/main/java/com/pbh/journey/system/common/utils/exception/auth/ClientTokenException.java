package com.pbh.journey.system.common.utils.exception.auth;


import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.exception.BaseException;

/**
 * @author pangbohuan
 * @description ClientTokenException
 * @date 2018-08-20 14:07
 **/
public class ClientTokenException extends BaseException {
    public ClientTokenException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
