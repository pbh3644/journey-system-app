package com.pbh.journey.system.common.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: Create
 * @author pangbohuan
 * @description:(使用此注解, 根据返回值验证是否添加成功)
 * @date 2018-07-25 17:30
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Insert {
    String value() default "";
}
