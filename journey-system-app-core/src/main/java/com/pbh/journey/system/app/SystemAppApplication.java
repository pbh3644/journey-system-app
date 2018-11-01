package com.pbh.journey.system.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * 1.内置容器启动服务
 * 2.扫描 mybatis mapper 包路径
 * 3.扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
 * 4.开启定时任务
 * 5.开启异步调用方法
 * 6.支持Spring缓存
 */
@SpringBootApplication
@MapperScan(basePackages = "com.pbh.journey.system.app.mapper")
@ComponentScan(basePackages = "com.pbh.journey.system.app")
@EnableScheduling
@EnableAsync
@EnableCaching
/**
 * @author pangbohuan
 * @description 旅行后台管理系统
 */

@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class SystemAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemAppApplication.class, args);
    }
}
