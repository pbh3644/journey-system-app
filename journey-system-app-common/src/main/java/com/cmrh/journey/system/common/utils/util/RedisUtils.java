package com.cmrh.journey.system.common.utils.util;

import com.cmrh.journey.system.common.utils.constant.CommonConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;

/**
 * @Author：pbh
 * @Date：2018-09-09 19:24
 * @Description：redis缓存工具类
 */
@Data
@Slf4j
public class RedisUtils {
    /**
     * redisTemplate
     */
    public static RedisTemplate redisTemplate;

    /**
     * operations
     */
    public static ValueOperations<String, Map<String, Object>> operations;


    /**
     * 该工程服务的名字
     */
    public static String applicationName;


    /**
     * 保存起来:本服务只有一个服务ID
     */
    private static int applicationId = 1;

    /**
     * 保存起来:最大数据中心ID
     */
    private static int tableId = 1;

    /**
     * 雪花算法获取数字ID
     */
    public static Long nextId(Class cla) {
        String simpleName = cla.getSimpleName();
        //如果是Application或者是ORGANIZATION_BAEN_NAME直接返回NULL这两个表用自增长ID
        if (CommonConstants.APPLICATION_CLASS_NAME.equals(simpleName) || CommonConstants.ORGANIZATION_CLASS_NAME.equals(simpleName)) {
            return null;
        }

        Map<String, Object> map = operations.get(applicationName);
        if (map == null) {
            log.error("获取nextId的时候redis缓存失效，请查看问题排查");
            //发送邮件、短信通知开发人员排查问题

            //代码还是要继续跑,最坏的结果不过是服务ID和数据ID都为1
            return new SnowflakeIdWorker(applicationId, tableId).nextId();
        } else {
            applicationId = applicationId != 0 ? applicationId : Integer.parseInt(map.get("applicationId").toString());
            int organizationId = Integer.parseInt(map.get(simpleName).toString());
            tableId = tableId < organizationId ? organizationId : tableId;
            return new SnowflakeIdWorker(applicationId, organizationId).nextId();
        }
    }
}
