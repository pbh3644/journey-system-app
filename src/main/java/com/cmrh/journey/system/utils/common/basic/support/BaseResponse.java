package com.cmrh.journey.system.utils.common.basic.support;

import lombok.Data;

/**
 * @author pangbohuan
 * @date 2018-08-20 14:03
 **/
@Data
public class BaseResponse {
    private int status = 200;
    private String message;

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse() {
    }
}
