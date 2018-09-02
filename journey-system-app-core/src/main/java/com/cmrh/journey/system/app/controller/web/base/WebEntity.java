package com.cmrh.journey.system.app.controller.web.base;


import com.cmrh.journey.system.app.controller.SysUserContoller;
import com.cmrh.journey.system.common.utils.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.ParameterizedType;

/**
 * @className: WebContoller的逻辑处理类
 * @date: 2018-03-24 13:15
 * @author: pangbohuan
 * @description:
 */
@Slf4j
public class WebEntity<T> {


    @Value("${worker.id}")
    protected int workerId;

    @Value("${sysUser.id}")
    private int sysUserId;


    /**
     * 擦除泛型判断调用者信息
     * 给出相应的机构类型id
     */
    protected int genericityErasure() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class entity = (Class) pt.getActualTypeArguments()[0];
        Object obj = null;
        try {
            obj = entity.newInstance();
        } catch (InstantiationException e) {
            log.error("擦除泛型后强转失败");
        } catch (IllegalAccessException e) {
            log.error("擦除泛型后强转失败");
        } finally {
            int dataCenterId = 1;
            if (obj != null) {
                if (obj instanceof SysUserContoller) {
                    dataCenterId = sysUserId;
                }
            }
            return dataCenterId;
        }
    }

    protected long getId() {
        return new SnowflakeIdWorker(workerId, genericityErasure()).nextId();
    }
}
