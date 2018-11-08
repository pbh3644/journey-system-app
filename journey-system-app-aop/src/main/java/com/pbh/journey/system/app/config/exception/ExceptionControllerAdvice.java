package com.pbh.journey.system.app.config.exception;

import com.pbh.journey.system.common.result.JourneySystemAppResult;
import com.pbh.journey.system.common.utils.exception.BussinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author pangbohuan
 * @date 2018/1/25
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 业务异常统一处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BussinessException.class)
    public JourneySystemAppResult businessExceptionDispose(BussinessException ex) {
        String errMessage = ex.getMessage();
        log.error(errMessage, ex);
        return JourneySystemAppResult.errorMsg(errMessage);
    }
}
