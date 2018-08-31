package com.cmrh.journey.system.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
 */
@SpringBootApplication
@MapperScan(basePackages = "com.cmrh.journey.system.app.mapper")
@ComponentScan(basePackages = "com.cmrh.journey.system.app")
@EnableScheduling
@EnableAsync
/**
 * @author pangbohuan
 * @description 后台管理系统
 */
public class SystemAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemAppApplication.class, args);
    }
}
