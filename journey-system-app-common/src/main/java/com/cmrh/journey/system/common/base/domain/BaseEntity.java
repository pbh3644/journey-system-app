package com.cmrh.journey.system.common.base.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: 所有bean的基类
 * @date: 2018-03-24 13:15
 * @author: pangbohuan
 * @description:
 */
@Data
public class BaseEntity<T> implements Serializable {

    /**
     * 创建人ID
     */
    private Integer createdSysUserId;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date createdTime;

    /**
     * 修改人ID
     */
    private Integer modifiedSysUserId;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date modifiedTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 第几页
     */
    private Integer page;

    /**
     * 每页显示多少数据
     */
    private Integer pageSize = 15;
}
