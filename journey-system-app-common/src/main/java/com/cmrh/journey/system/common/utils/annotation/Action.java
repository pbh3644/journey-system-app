package com.cmrh.journey.system.common.utils.annotation;

import com.cmrh.journey.system.common.utils.annotation.dto.OperationLogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: Action
 * @date: 2017-03-24 13:18
 * @author: pangbohuan
 * @description: (用户操作日志注解类)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String value() default "未标注";

    OperationLogType type() default OperationLogType.SELECT;
}
