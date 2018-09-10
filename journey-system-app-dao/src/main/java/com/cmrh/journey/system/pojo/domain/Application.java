package com.cmrh.journey.system.pojo.domain;

import com.cmrh.journey.system.common.base.pojo.BaseEntity;
import lombok.Data;

/**
 * entity:Application
 *
 * @author pbh
 * @version 1.0
 * @since 2018-9-9
 */
@Data
public class Application extends BaseEntity<Application> {
    private static final long serialVersionUID = 1L;

    /**
     * 微服务的名字(英文)
     */
    private String applicationNameEnglish;

    /**
     * 微服务的名字(中文)
     */
    private String applicationNameChinese;

    /**
     * 服务的ip地址
     */
    private String applicationIp;

}
