  drop TABLE application;
  CREATE TABLE application(
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
    delete_flag tinyint(1) DEFAULT 0 COMMENT '状态0:正常,1:删除',
    remark varchar(30) DEFAULT NULL COMMENT '备注'
  )COMMENT='服务系统的注册中心' ENGINE=InnoDB DEFAULT CHARSET=utf8;

  INSERT into application(application_name_english,application_name_chinese,application_ip,add_time,add_user_id,remark)
  VALUES('journey-system-app','后台管理系统','127.0.0.1',NOW(),123456789,'后台管理系统,拥有权限配置,系统配置等功能');

  select * from application;



  drop TABLE organization;
  CREATE TABLE organization(
    id BIGINT(32) not null COMMENT '设置主键ID',
    application_id BIGINT(20) not null COMMENT '服务系统的机构ID',
    organization_data_name varchar(30) not null COMMENT '表名',
    add_time datetime not null COMMENT '增加时间',
    update_time datetime DEFAULT NULL  COMMENT '修改时间',
    delete_time datetime DEFAULT NULL  COMMENT '删除时间',
    add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
    update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
    delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
    delete_flag tinyint(1) DEFAULT 0 COMMENT '状态0:正常,1:删除',
    remark varchar(30) DEFAULT NULL COMMENT '备注',
    primary key (application_id,id)
  )COMMENT='数据中心的注册中心' ENGINE=InnoDB DEFAULT CHARSET=utf8;
  INSERT into organization(id,application_id,organization_data_name,add_time,add_user_id,remark)
  VALUES(1,1,'数据中心表',NOW(),123456789,'数据库表信息');
  INSERT into organization(id,application_id,organization_data_name,add_time,add_user_id,remark)
  VALUES(2,1,'Application',NOW(),123456789,'微服务信息');

  select * from organization;
