# 创建用户表
CREATE TABLE `sys_admin`
(
    `admin_id`               BIGINT(20)  NOT NULL AUTO_INCREMENT,
    `admin_login_name`       VARCHAR(50) NOT NULL,
    `admin_login_pass`       VARCHAR(100) DEFAULT NULL,
    `admin_login_mac`        VARCHAR(50)  DEFAULT NULL,
    `admin_login_ip`         VARCHAR(50)  DEFAULT NULL,
    `admin_login_begin_time` datetime     DEFAULT NULL,
    `admin_login_end_time`   datetime     DEFAULT NULL,
    `admin_status`           INT(11)      DEFAULT NULL,
    `admin_create_date`      datetime     DEFAULT NULL,
    `admin_mobile_phone`     VARCHAR(50)  DEFAULT NULL,
    `admin_email`            VARCHAR(50)  DEFAULT NULL,
    `admin_is_default`       INT(11)      DEFAULT NULL,
    `has_authorize_right`    INT(11)      DEFAULT NULL,
    `admin_pwd_state`        INT(11)      DEFAULT NULL,
    `admin_login_fail_count` INT(11)      DEFAULT NULL,
    PRIMARY KEY (`admin_id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

# 创建角色表
CREATE TABLE sys_role
(
    role_id      bigint AUTO_INCREMENT PRIMARY KEY,
    role_name_en varchar(20),
    role_name_cn varchar(20),
    role_type    int,
    role_status  int,
    role_remark  varchar(20)
);

# 创建用户角色表
CREATE TABLE sys_admin_ref_role
(
    arr_id       bigint AUTO_INCREMENT PRIMARY KEY,
    arr_role_id  bigint,
    arr_admin_id bigint
);

# 创建模块表
CREATE TABLE sys_modules
(
    module_id      bigint AUTO_INCREMENT PRIMARY KEY,
    module_name_en varchar(20),
    module_name_cn varchar(20),
    module_url     varchar(100),
    module_fid     bigint,
    module_order   int,
    module_type    int,
    visible        int
);

# 创建角色模块表
CREATE TABLE sys_role_ref_module
(
    rrm_id    bigint AUTO_INCREMENT PRIMARY KEY,
    module_id bigint,
    role_id   bigint
);

# 创建登录token记录表
CREATE TABLE `persistent_logins`
(
    `username`  varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
    `series`    varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
    `token`     varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
    `last_used` timestamp                              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`series`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


# 添加一个用户, 用户名/密码：user/user
INSERT INTO `vhr`.`sys_admin`(`admin_id`, `admin_login_name`, `admin_login_pass`, `admin_login_mac`, `admin_login_ip`,
                              `admin_login_begin_time`, `admin_login_end_time`, `admin_status`, `admin_create_date`,
                              `admin_mobile_phone`, `admin_email`, `admin_is_default`, `has_authorize_right`,
                              `admin_pwd_state`, `admin_login_fail_count`)
VALUES (1, 'user', '$2a$10$dwo7t5CCGZjmpC.DFtx/..XZMsJMz2b/LGs.7LH8jOWmc80GxqxE.', 'mac', 'mac', '2023-03-21 19:52:59',
        NULL, 1, '2023-03-21 19:53:12', NULL, NULL, NULL, NULL, 1, 0);

# 添加两个角色
INSERT INTO `vhr`.`sys_role`(`role_id`, `role_name_en`, `role_name_cn`, `role_type`, `role_status`, `role_remark`)
VALUES (1, 'ROLE_user', 'ROLE_user', 0, 1, NULL);
INSERT INTO `vhr`.`sys_role`(`role_id`, `role_name_en`, `role_name_cn`, `role_type`, `role_status`, `role_remark`)
VALUES (2, 'ROLE_admin', 'ROLE_admin', 0, 1, NULL);

# 用户角色绑定
INSERT INTO `vhr`.`sys_admin_ref_role`(`arr_id`, `arr_role_id`, `arr_admin_id`)
VALUES (1, 1, 1);

# 插入两个模块
INSERT INTO `vhr`.`sys_modules`(`module_id`, `module_name_en`, `module_name_cn`, `module_url`, `module_fid`,
                                `module_order`, `module_type`, `visible`)
VALUES (1, 'aa', 'aa', '/user/**', 0, 1, 1, NULL);
INSERT INTO `vhr`.`sys_modules`(`module_id`, `module_name_en`, `module_name_cn`, `module_url`, `module_fid`,
                                `module_order`, `module_type`, `visible`)
VALUES (2, 'bb', 'bb', '/admin/**', 0, 1, 1, NULL);


# 模块角色绑定
INSERT INTO `vhr`.`sys_role_ref_module`(`rrm_id`, `module_id`, `role_id`)
VALUES (1, 1, 1);
INSERT INTO `vhr`.`sys_role_ref_module`(`rrm_id`, `module_id`, `role_id`)
VALUES (2, 2, 2);







