-- 服装商品后台管理系统数据库脚本
-- 创建数据库
CREATE DATABASE IF NOT EXISTS clothing_admin DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE clothing_admin;

-- 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  `nickname` VARCHAR(50) DEFAULT NULL COMMENT '昵称',
  `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像',
  `status` TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-正常',
  `role` VARCHAR(20) DEFAULT 'admin' COMMENT '角色',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 分类表
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '分类 ID',
  `name` VARCHAR(50) NOT NULL COMMENT '分类名称',
  `parent_id` BIGINT DEFAULT 0 COMMENT '父分类 ID',
  `level` TINYINT DEFAULT 1 COMMENT '层级',
  `sort` INT DEFAULT 0 COMMENT '排序',
  `status` TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-正常',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- 商品表
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品 ID',
  `name` VARCHAR(100) NOT NULL COMMENT '商品名称',
  `description` TEXT COMMENT '商品描述',
  `category_id` BIGINT DEFAULT NULL COMMENT '分类 ID',
  `main_image` VARCHAR(255) DEFAULT NULL COMMENT '主图',
  `images` TEXT COMMENT '图片列表',
  `price` DECIMAL(10,2) DEFAULT 0 COMMENT '价格',
  `status` TINYINT DEFAULT 1 COMMENT '状态 0-下架 1-上架',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 商品 SKU 表
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'SKU ID',
  `product_id` BIGINT NOT NULL COMMENT '商品 ID',
  `sku_code` VARCHAR(50) DEFAULT NULL COMMENT 'SKU 编码',
  `color` VARCHAR(20) DEFAULT NULL COMMENT '颜色',
  `size` VARCHAR(20) DEFAULT NULL COMMENT '尺码',
  `price` DECIMAL(10,2) DEFAULT 0 COMMENT '价格',
  `stock` INT DEFAULT 0 COMMENT '库存',
  `status` TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-正常',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品 SKU 表';

-- 库存表
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '库存 ID',
  `sku_id` BIGINT NOT NULL COMMENT 'SKU ID',
  `stock` INT DEFAULT 0 COMMENT '库存数量',
  `locked_stock` INT DEFAULT 0 COMMENT '锁定库存',
  `available_stock` INT DEFAULT 0 COMMENT '可用库存',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';

-- 库存流水表
DROP TABLE IF EXISTS `inventory_log`;
CREATE TABLE `inventory_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '流水 ID',
  `sku_id` BIGINT NOT NULL COMMENT 'SKU ID',
  `change_qty` INT NOT NULL COMMENT '变动数量',
  `before_qty` INT NOT NULL COMMENT '变动前数量',
  `after_qty` INT NOT NULL COMMENT '变动后数量',
  `type` VARCHAR(20) NOT NULL COMMENT '类型 IN-入库 OUT-出库 CHECK-盘点',
  `reason` VARCHAR(255) DEFAULT NULL COMMENT '原因',
  `related_id` BIGINT DEFAULT NULL COMMENT '关联 ID(订单 ID 等)',
  `operator_id` BIGINT DEFAULT NULL COMMENT '操作人 ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存流水表';

-- 会员表
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '会员 ID',
  `name` VARCHAR(50) NOT NULL COMMENT '姓名',
  `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
  `gender` TINYINT DEFAULT 1 COMMENT '性别 0-女 1-男',
  `birthday` VARCHAR(20) DEFAULT NULL COMMENT '生日',
  `total_amount` DECIMAL(10,2) DEFAULT 0 COMMENT '消费总额',
  `total_orders` INT DEFAULT 0 COMMENT '订单总数',
  `points` INT DEFAULT 0 COMMENT '积分',
  `level` TINYINT DEFAULT 1 COMMENT '等级',
  `status` TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-正常',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员表';

-- 订单表
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单 ID',
  `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
  `member_id` BIGINT DEFAULT NULL COMMENT '会员 ID',
  `total_amount` DECIMAL(10,2) DEFAULT 0 COMMENT '订单总额',
  `pay_amount` DECIMAL(10,2) DEFAULT 0 COMMENT '实付金额',
  `status` TINYINT DEFAULT 1 COMMENT '状态 1-待付款 2-待发货 3-已发货 4-已完成',
  `receiver_name` VARCHAR(50) DEFAULT NULL COMMENT '收货人',
  `receiver_phone` VARCHAR(20) DEFAULT NULL COMMENT '收货电话',
  `receiver_address` VARCHAR(255) DEFAULT NULL COMMENT '收货地址',
  `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `pay_time` DATETIME DEFAULT NULL COMMENT '支付时间',
  `delivery_time` DATETIME DEFAULT NULL COMMENT '发货时间',
  `receive_time` DATETIME DEFAULT NULL COMMENT '收货时间',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 订单明细表
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单项 ID',
  `order_id` BIGINT NOT NULL COMMENT '订单 ID',
  `product_id` BIGINT NOT NULL COMMENT '商品 ID',
  `sku_id` BIGINT NOT NULL COMMENT 'SKU ID',
  `product_name` VARCHAR(100) NOT NULL COMMENT '商品名称',
  `sku_name` VARCHAR(100) DEFAULT NULL COMMENT 'SKU 名称',
  `quantity` INT NOT NULL COMMENT '数量',
  `price` DECIMAL(10,2) NOT NULL COMMENT '单价',
  `total_amount` DECIMAL(10,2) NOT NULL COMMENT '小计',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- 初始化数据
-- 默认管理员账号 (密码：123456)
INSERT INTO `user` (`username`, `password`, `nickname`, `role`, `status`) 
VALUES ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', '管理员', 'admin', 1);

-- 初始化分类
INSERT INTO `category` (`name`, `parent_id`, `level`, `sort`) VALUES
('上衣', 0, 1, 1),
('裤子', 0, 1, 2),
('裙子', 0, 1, 3),
('外套', 0, 1, 4),
('T 恤', 1, 2, 1),
('衬衫', 1, 2, 2);

-- 初始化商品
INSERT INTO `product` (`name`, `description`, `category_id`, `price`, `status`) VALUES
('经典圆领 T 恤', '舒适纯棉材质，经典百搭', 5, 99.00, 1),
('商务休闲衬衫', '免烫面料，商务休闲两相宜', 6, 299.00, 1),
('修身牛仔裤', '弹性面料，舒适修身', 2, 399.00, 1);
