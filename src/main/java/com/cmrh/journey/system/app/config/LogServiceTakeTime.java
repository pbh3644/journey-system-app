package com.cmrh.journey.system.app.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author pangbohuan
 * @description:统计所有的service方法处理时间
 * @date 2018-07-25 17:30
 **/
@Aspect
@Component
public class LogServiceTakeTime {

    final static Logger log = LoggerFactory.getLogger(LogServiceTakeTime.class);

    @Pointcut("execution(* com.cmrh.journey.system.app.service..*.*(..))")
    public void performance() {
    }

    @Around("performance()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //记录起始时间
        long begin = System.currentTimeMillis();
        Object result = "";
        /** 执行目标方法 */
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error("日志记录发生错误, errorMessage: {}", e.getMessage());
        } finally {
            /** 记录操作时间 */
            long took = (System.currentTimeMillis() - begin) / 1000;
            log.info("Service执行时间为: {}秒", took);
        }
        return result;
    }

    @Before("performance()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        log.info("doBefore");
    }
}