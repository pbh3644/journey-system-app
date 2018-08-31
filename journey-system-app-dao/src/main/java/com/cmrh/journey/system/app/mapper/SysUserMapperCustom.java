package com.cmrh.journey.system.app.mapper;

/**
 * @author pangbohuan
 * @description:SysUserMapperCustom
 * @date 2018-07-25 17:30
 **/
public interface SysUserMapperCustom {

    /**
     * MD5加密
     *
     * @param password
     * @return String
     */
    String userPasswordEncrypt(String password);
}