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
INSERT into sys_user(id,nick_name,real_name,id_card,mailbox,mobile,add_time,add_user_id,remark)
VALUES(944784114,'pbh','庞博桓','45092319980102129X','pbh3644@163.com','15277943644',NOW(),944784114,'顶级管理员');

DROP TABLE IF EXISTS `login_no`;
CREATE TABLE IF NOT EXISTS `login_no` (
  `id` bigint(32) NOT NULL COMMENT 'id',
  `user_id` bigint(32) NOT NULL COMMENT 'userId',
  `user_account` varchar(100) NOT NULL COMMENT '登录账号',
  `user_pwd` varchar(100) NOT NULL COMMENT '登录密码',
  `type` tinyint(4) NOT NULL COMMENT '登录类型，1手机号,2微信小程序，3微博，4账号OOS,5系统登录',
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
ALTER TABLE `login_no` ADD UNIQUE (user_account);
select * from login_no;
INSERT into login_no(id,user_id,user_account,user_pwd,type,wechat_union_id,add_time,add_user_id,remark)
VALUES(123456,944784114,'pbh3644','pbh.3644',5,'pbh3644',NOW(),944784114,'顶级管理员');



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
  `role_state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态，1启用，2禁用',
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
ALTER TABLE `sys_role` ADD UNIQUE (role_name);
select * from sys_role;
INSERT into sys_role(id,role_name,role_code,role_desc,system_code,add_time,add_user_id,remark)
VALUES(123456,'顶级管理员','01','拥有系统所有权限','1',NOW(),944784114,'顶级管理员');

DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `id` bigint(32) NOT NULL COMMENT 'dept_id',
  `dept_name` varchar(100) NOT NULL COMMENT '部门名称',
  `dept_code` varchar(100) NOT NULL COMMENT '部门代码',
  `dept_desc` varchar(100) NOT NULL COMMENT '部门描述',
  `system_code` varchar(32) NOT NULL COMMENT '系统code',
  `dept_state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态，1启用，2禁用',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统部门表';
ALTER TABLE `sys_dept` ADD UNIQUE (dept_name);

select * from sys_dept;

INSERT into sys_dept(id,dept_name,dept_code,dept_desc,system_code,add_time,add_user_id,remark)
VALUES(123456,'董事部','01','董事部门','1',NOW(),944784114,'顶级管理员');

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id` bigint(32) NOT NULL COMMENT 'resourceUrlId',
  `parent_id` bigint(32) DEFAULT NULL COMMENT '父级ID',
  `is_menu` tinyint(1) NOT NULL COMMENT '是否菜单1是 2不是',
  `level` TINYINT(4) NOT NULL COMMENT '层级',
  `permission_url` varchar(100) NOT NULL COMMENT '不可匿名访问的地址',
  `permission_desc` varchar(100) NOT NULL COMMENT '权限描述',
  `system_code` varchar(32) NOT NULL COMMENT '系统code',
  `need_csrf` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否校验csrf',
  `order` INT(10) NOT NULL DEFAULT '0' COMMENT '优先级，同level大的在上',
  `permission_type` varchar(30) NOT NULL  COMMENT '权限类型',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限资源表';
select * from sys_permission;

INSERT into sys_permission(id,is_menu,level,permission_url,permission_desc,system_code,permission_type,add_time,add_user_id)
VALUES(1,1,1,'根菜单','根菜单','root','root',NOW(),944784114);


DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE IF NOT EXISTS `sys_user_dept` (
	`id` bigint(32) NOT NULL COMMENT 'sys_user_dept_id',
	 user_id bigint(32) NOT NULL COMMENT '用户ID',
	 dept_id bigint(32) NOT NULL COMMENT '部门ID',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
	 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户部门表';
select * from sys_user_dept;

INSERT into sys_user_dept(id,user_id,dept_id,add_time,add_user_id,remark)
VALUES(123456,944784114,123456,NOW(),944784114,'顶级管理员');


DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
	`id` bigint(32) NOT NULL COMMENT 'sys_user_role_id',
	 user_id bigint(32) NOT NULL COMMENT '用户ID',
	 role_id bigint(32) NOT NULL COMMENT '角色ID',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
	 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';
select * from sys_user_role;
INSERT into sys_user_role(id,user_id,role_id,add_time,add_user_id,remark)
VALUES(123456,944784114,123456,NOW(),944784114,'顶级管理员');

DROP TABLE IF EXISTS `sys_user_permission`;
CREATE TABLE IF NOT EXISTS `sys_user_permission` (
	`id` bigint(32) NOT NULL COMMENT 'sys_user_permission_id',
	 user_id bigint(32) NOT NULL COMMENT '用户ID',
	 permission_id bigint(32) NOT NULL COMMENT '权限ID',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
	 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户权限表';
select * from sys_user_permission;


DROP TABLE IF EXISTS `sys_dept_permission`;
CREATE TABLE IF NOT EXISTS `sys_dept_permission` (
	`id` bigint(32) NOT NULL COMMENT 'sys_dept_permission_id',
	 dept_id bigint(32) NOT NULL COMMENT '部门ID',
	 permission_id bigint(32) NOT NULL COMMENT '权限ID',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
	 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统部门权限表';
select * from sys_dept_permission;


DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE IF NOT EXISTS `sys_role_permission` (
	`id` bigint(32) NOT NULL COMMENT 'sys_role_permission_id',
	 role_id bigint(32) NOT NULL COMMENT '角色ID',
	 permission_id bigint(32) NOT NULL COMMENT '权限ID',
   add_time datetime not null COMMENT '增加时间',
   update_time datetime DEFAULT NULL  COMMENT '修改时间',
   delete_time datetime DEFAULT NULL  COMMENT '删除时间',
   add_user_id BIGINT(32) NOT NULL  COMMENT '增加用户ID',
   update_user_id BIGINT(32) DEFAULT NULL  COMMENT '修改用户ID',
   delete_user_id BIGINT(32) DEFAULT NULL  COMMENT '删除用户ID',
   delete_flag tinyint(1) DEFAULT 1 COMMENT '状态 1:正常,2:删除',
   remark varchar(30) DEFAULT NULL COMMENT '备注',
	 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色权限表';
select * from sys_role_permission;



select *
from sys_user_dept ud
LEFT JOIN sys_user u on u.id=ud.user_id;
LEFT JOIN sys_dept d on ud.dept_id=d.id;


select * from login_no;
select * from login_log;
select * from sys_user;
select * from sys_dept;
select * from sys_role;
select * from sys_permission;




select s.*
from login_no l
LEFT JOIN sys_user s on l.user_id = s.id
WHERE l.user_account = #userAccount# and
l.user_pwd = #user_pwd#