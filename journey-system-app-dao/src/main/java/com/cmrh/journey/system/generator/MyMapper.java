package com.cmrh.journey.system.generator;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author pangbohuan
 * @description: 继承自己的MyMapper
 * @date 2018-07-25 17:40
 **/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
