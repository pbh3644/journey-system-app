  drop TABLE IF EXISTS application;
  CREATE TABLE IF NOT EXISTS application(
    id BIGINT(32) not null primary key AUTO_INCREMENT COMMENT '设置主键ID',
    application_name_english varchar(30) not null COMMENT '微服务的名字(英文)',
    application_name_chinese varchar(30) not null COMMENT '微服务的名字(中文)',
    application_ip varchar(30) not null COMMENT '服务的ip地址',
    add_time datetime not null COMMENT '增加时间',
    update_time datetime DEFAULT NULL  COMMENT '修改时间',
    delete_time datetime DEFAULT NULL  COMMENT '删除时间',
    add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
    update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
    delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
    delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
    remark varchar(30) DEFAULT NULL COMMENT '备注'
  )COMMENT='服务系统的注册中心' ENGINE=InnoDB DEFAULT CHARSET=utf8;

  INSERT into application(application_name_english,application_name_chinese,application_ip,add_time,add_user_id,remark)
  VALUES('journey-system-app','后台管理系统','127.0.0.1',NOW(),123456789,'后台管理系统,拥有权限配置,系统配置等功能');


  select * from application;



  drop TABLE IF EXISTS organization;
  CREATE TABLE IF NOT EXISTS organization(
    id BIGINT(32) not null COMMENT '设置主键ID',
    application_id BIGINT(20) not null COMMENT '服务系统的机构ID',
    organization_data_name varchar(30) not null COMMENT '表名',
    add_time datetime not null COMMENT '增加时间',
    update_time datetime DEFAULT NULL  COMMENT '修改时间',
    delete_time datetime DEFAULT NULL  COMMENT '删除时间',
    add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
    update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
    delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
    delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
    remark varchar(30) DEFAULT NULL COMMENT '备注',
    primary key (application_id,id)
  )COMMENT='数据中心的注册中心' ENGINE=InnoDB DEFAULT CHARSET=utf8;
  INSERT into organization(id,application_id,organization_data_name,add_time,add_user_id,remark)
  VALUES(1,1,'数据中心表',NOW(),123456789,'数据库表信息');
  INSERT into organization(id,application_id,organization_data_name,add_time,add_user_id,remark)
  VALUES(2,1,'Application',NOW(),123456789,'微服务信息');

  select * from organization;

drop TABLE IF EXISTS sys_user;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint(32) NOT NULL COMMENT 'userId',
  `nick_name` varchar(100) NOT NULL COMMENT '昵称',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
	 mailbox  varchar(50) DEFAULT NULL COMMENT '邮箱号',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '当前状态，1正常，2禁止登录',
  `last_pwdmod_time` datetime DEFAULT NULL COMMENT '最近修改密码时间',
  `device_id` varchar(255) DEFAULT NULL COMMENT '设备Id',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';
select * from sys_user;

DROP TABLE IF EXISTS `login_no`;
CREATE TABLE IF NOT EXISTS `login_no` (
  `id` bigint(32) NOT NULL COMMENT 'id',
  `user_id` bigint(32) NOT NULL COMMENT 'userId',
  `user_account` varchar(100) NOT NULL COMMENT '登录账号',
  `user_pwd` varchar(100) NOT NULL COMMENT '登录密码',
  `type` tinyint(4) NOT NULL COMMENT '登录类型，1手机号，2微信小程序，3微博，4账号OOS',
  `wechat_union_id` VARCHAR(100) NULL DEFAULT NULL COMMENT '微信unionId',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录账号表';
select * from login_no;

DROP TABLE IF EXISTS `login_log`;
CREATE TABLE IF NOT EXISTS `login_log` (
  `id` bigint(32) NOT NULL COMMENT 'id',
  `user_id` bigint(32) NOT NULL COMMENT 'userId',
  `user_account` varchar(100) NOT NULL COMMENT '登录账号',
  `system_code` VARCHAR(100) NOT NULL COMMENT '系统code',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志表';
select * from login_log;

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint(32) NOT NULL COMMENT 'roleId',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_code` varchar(100) NOT NULL COMMENT '角色代码',
  `role_desc` varchar(100) NOT NULL COMMENT '角色描述',
  `system_code` varchar(32) NOT NULL COMMENT '系统code',
  `role_state` tinyint(4) NOT NULL COMMENT '状态，1启用，2禁用',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';
select * from sys_role;


