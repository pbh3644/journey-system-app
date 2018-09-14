package com.pbh.journey.system.app.config.redis.init;

import com.pbh.journey.system.app.service.ApplicationService;
import com.pbh.journey.system.app.service.OrganizationService;
import com.pbh.journey.system.common.utils.util.RedisUtils;
import com.pbh.journey.system.common.utils.util.StringUtil;
import com.pbh.journey.system.pojo.domain.Application;
import com.pbh.journey.system.pojo.domain.Organization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：pbh
 * @Date：2018-09-09 11:16
 * @Description：初始化redis缓存
 */
@Order(1)
@Configuration
@Slf4j
public class RedisInit implements ApplicationRunner {


    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * redisTemplate
     */
    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 所有微服务
     */
    @Resource
    private ApplicationService applicationService;

    /**
     * 数据库表业务
     */
    @Resource
    private OrganizationService organizationService;

    @Override
    public void run(ApplicationArguments args) {
        ValueOperations<String, Map<String, Object>> operations = redisTemplate.opsForValue();
        initApplication(operations);
        log.info("初始化成功RedisUtils中的组件成功");
    }

    /**
     * 初始化微服务以及每个微服务包含表
     * 放入redis缓存中方便获取机构ID和微服务ID
     */
    public void initApplication(ValueOperations<String, Map<String, Object>> operations) {

        List<Application> appList = applicationService.findAll();
        List<Organization> orgList = organizationService.findAll();
        log.info("查询所有微服务和所有数据库表工作准备就绪......");
        int index = 0;
        for (Application application : appList) {
            long id = application.getId();
            long maxTableId = 0;
            Map<String, Object> applicationInfo = new HashMap<>(16);
            for (int i = index; i < orgList.size(); i++) {
                Organization organization = orgList.get(i);
                long applicationId = organization.getApplicationId();
                if (id == applicationId) {
                    //数据库表ID可以说是该微服务下的数据库表编号
                    maxTableId = organization.getId();
                    //数据库表名字对应着实体类的驼峰命名，方便用擦除泛型的特效在POJO基类获取数据中心ID，便于达到雪花算法的特征(机构ID+数据中心ID)
                    String organizationDataName = organization.getOrganizationDataName();

                    String pojoName = StringUtil.className(organizationDataName);
                    applicationInfo.put(pojoName, maxTableId);
                } else {
                    //因为是排序查询出的一旦不相等表明这个微服务已经没有对应的表了,为了提高性能快速进行下一个微服务的数据分析
                    index = i + 1;
                    break;
                }
            }
            String applicationIp = application.getApplicationIp();
            String applicationNameEnglish = application.getApplicationNameEnglish();
            String applicationNameChinese = application.getApplicationNameChinese();

            //微服务的ID
            applicationInfo.put("applicationId", id);

            //微服务的IP
            applicationInfo.put("applicationIp", applicationIp);

            //微服务的中文名字
            applicationInfo.put("applicationNameChinese", applicationNameChinese);

            //微服务下最大的数据库ID+1
            applicationInfo.put("maxTableId", maxTableId + 1);

            //通过微服务的名字加入redis缓存中
            operations.set(applicationNameEnglish, applicationInfo);
            log.info(applicationNameEnglish + ":微服务相关信息加入redis成功:" + applicationInfo.toString());
        }
        //并把redisTemplate注入到RedisUtils中目的为了微服务共享
        RedisUtils.applicationName = applicationName;
        RedisUtils.operations = operations;
        RedisUtils.redisTemplate = redisTemplate;
    }
}
