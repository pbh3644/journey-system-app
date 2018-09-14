package com.pbh.journey.system.common.utils.exception.auth;


import com.pbh.journey.system.common.utils.constant.CommonConstants;
import com.pbh.journey.system.common.utils.exception.BussinessException;

/**
 * @author pangbohuan
 * @description ClientForbiddenException
 * @date 2018-08-20 14:08
 **/
public class ClientForbiddenException extends BussinessException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }
}
