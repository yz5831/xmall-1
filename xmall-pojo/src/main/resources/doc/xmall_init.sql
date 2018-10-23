/*************************************************************/
/**********			XMALL商城数据库初始化脚本			**********/
/********** Database: MySQL 5.7						**********/
/********** Author: Captwill						**********/
/********** Version: v1.0 2018-10-19				**********/
/*************************************************************/
-- ============================================================
-- 创建并且使用数据库xmall
-- create database xmall character set 'utf8mb4';
-- use xmall;
-- ============================================================

-- ============================================================
-- 系统状态表：sys_status
-- ============================================================
drop table if exists sys_status;
create table sys_status(
	status_id int(10) not null auto_increment comment '状态主键',
	parent_id int(10) comment '父级状态',
	status_name varchar(200) comment '状态名称',
	status_code varchar(100) comment '状态编码',
	sort_order int(10) comment '排序字段',
	create_time timestamp comment '创建时间',
	update_time timestamp comment '更新时间',
	primary key(status_id)
)Engine=InnoDB charset=utf8mb4 comment='系统状态表';

insert into sys_status(parent_id, status_name, status_code, sort_order, create_time, update_time) values(null, '是否启用', 'ISABLE', 1, current_timestamp, current_timestamp);
insert into sys_status(parent_id, status_name, status_code, sort_order, create_time, update_time) values(1, '启用', 'ENABLE', 11, current_timestamp, current_timestamp);
insert into sys_status(parent_id, status_name, status_code, sort_order, create_time, update_time) values(1, '禁用', 'DISABLE', 12, current_timestamp, current_timestamp);

-- ============================================================
-- 角色信息表：sys_role
-- ============================================================
drop table if exists sys_role;
create table sys_role(
	role_id int(10) not null auto_increment comment '角色主键',
	role_name varchar(200) comment '角色名称',
	role_code varchar(100) comment '角色编码',
	status_id int(10) comment '状态',
	create_user int(10) comment '创建人',
	create_time timestamp comment '创建时间',
	update_user int(10) comment '更新人',
	update_time timestamp comment '更新时间',
	primary key(role_id)
)Engine=InnoDB charset=utf8mb4 comment='角色信息表';

insert into sys_role(role_name, role_code, status_id, create_time, update_time) values('超级管理员', 'SUPERADMIN', 2, current_timestamp, current_timestamp);

-- ============================================================
-- 管理系统菜单信息表：sys_menu
-- ============================================================
drop table if exists sys_menu;
create table sys_menu(
	menu_id int(10) not null auto_increment comment '菜单主键',
	parent_id int(10) comment '父级菜单',
	text varchar(200) comment '菜单名称',
	url varchar(500) comment '链接地址',
	icon varchar(200) comment '图标',
	sort_order int(10) comment '排序字段',
	status_id int(10) comment '菜单状态',
	create_user int(10) comment '创建人',
	create_time timestamp comment '创建时间',
	update_user int(10) comment '更新人',
	update_time timestamp comment '更新时间',
	primary key(menu_id)
)Engine=InnoDB charset=utf8mb4 comment='管理系统菜单信息表';

insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(null, '系统管理', '', 'fa fa-info-circle', 1, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(null, '基础管理', '', '', 2, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(null, '商品管理', '', '', 3, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(null, '交易管理', '', '', 4, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(null, '会员管理', '', '', 5, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(null, '物流管理', '', '', 6, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(1, '后台用户', '/user/index', 'fa fa-user', 11, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(1, '后台角色', '', '', 12, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(1, '个人信息', '', '', 13, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(2, '地址管理', '', '', 21, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(2, '支付方式', '', '', 22, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(2, '积分等级', '', '', 23, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(3, '商品类别', '/category/index', 'fa fa-bars', 31, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(3, '商品属性', '', '', 32, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(3, '商品属性值', '', '', 33, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(3, '商品信息', '', '', 34, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(4, '订单管理', '', '', 41, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(5, '会员管理', '', '', 51, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(6, '物流公司', '', '', 61, 2, current_timestamp, current_timestamp);
insert into sys_menu(parent_id, text, url, icon, sort_order, status_id, create_time, update_time) values(6, '发货管理', '', '', 62, 2, current_timestamp, current_timestamp);

-- ============================================================
-- 角色菜单中间表：sys_role_menu
-- ============================================================
drop table if exists sys_role_menu;
create table sys_role_menu(
	role_id int(10) comment '角色主键',
	menu_id int(10) comment '菜单主键'
)Engine=InnoDB charset=utf8mb4 comment='角色菜单中间表';

insert into sys_role_menu(role_id, menu_id) values(1, 1);
insert into sys_role_menu(role_id, menu_id) values(1, 2);
insert into sys_role_menu(role_id, menu_id) values(1, 3);
insert into sys_role_menu(role_id, menu_id) values(1, 4);
insert into sys_role_menu(role_id, menu_id) values(1, 5);
insert into sys_role_menu(role_id, menu_id) values(1, 6);
insert into sys_role_menu(role_id, menu_id) values(1, 7);
insert into sys_role_menu(role_id, menu_id) values(1, 8);
insert into sys_role_menu(role_id, menu_id) values(1, 9);
insert into sys_role_menu(role_id, menu_id) values(1, 10);
insert into sys_role_menu(role_id, menu_id) values(1, 11);
insert into sys_role_menu(role_id, menu_id) values(1, 12);
insert into sys_role_menu(role_id, menu_id) values(1, 13);
insert into sys_role_menu(role_id, menu_id) values(1, 14);
insert into sys_role_menu(role_id, menu_id) values(1, 15);
insert into sys_role_menu(role_id, menu_id) values(1, 16);
insert into sys_role_menu(role_id, menu_id) values(1, 17);
insert into sys_role_menu(role_id, menu_id) values(1, 18);
insert into sys_role_menu(role_id, menu_id) values(1, 19);
insert into sys_role_menu(role_id, menu_id) values(1, 20);

-- ============================================================
-- 后台用户信息表：sys_user
-- ============================================================
drop table if exists sys_user;
create table sys_user(
	user_id int(10) not null auto_increment comment '用户主键',
	user_no varchar(100) comment '用户编号',
	username varchar(200) comment '用户姓名',
	login_name varchar(500) comment '登录名',
	password varchar(500) comment '登录密码：MD5加密',
	gender varchar(10) comment '性别：M-男，F-女',
	role_id int(10) comment '用户角色',
	id_card varchar(100) comment '身份证号码',
	birthday date comment '出生日期',
	cellphone varchar(100) comment '联系电话',
	email varchar(500) comment '电子邮件',
	status_id int(10) comment '用户状态',
	create_user int(10) comment '创建人',
	create_time timestamp comment '创建时间',
	update_user int(10) comment '更新人',
	update_time timestamp comment '更新时间',
	primary key(user_id)
)Engine=InnoDB charset=utf8mb4 comment='后台用户信息表';

insert into sys_user(username, login_name, password, role_id, status_id, create_time, update_time) values('王富贵', 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, 2, current_timestamp, current_timestamp);

-- ============================================================
-- 商品属性表：sys_category
-- ============================================================
drop table if exists sys_category;
create table sys_category(
	category_id int(10) not null auto_increment comment '商品属性主键',
	parent_id int(10) comment '父级属性',
	text varchar(200) comment '属性名',
	status_id int(10) comment '属性状态',
	sort_order int(10) comment '排序字段',
	create_user int(10) comment '创建人',
	create_time timestamp comment '创建时间',
	update_user int(10) comment '更新人',
	update_time timestamp comment '更新时间',
	primary key(category_id)
)Engine=InnoDB charset=utf8mb4 comment='商品属性表';