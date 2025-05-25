-- user 用户表
CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(64) NOT NULL,
                      password VARCHAR(128) NOT NULL,  -- 密码加密存储
                      role VARCHAR(20)
);

-- product_category 商品分类表
CREATE TABLE product_category (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  name VARCHAR(64) NOT NULL,
                                  parent_id BIGINT DEFAULT NULL
);

-- product 商品表
CREATE TABLE product (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(128) NOT NULL,
                         category_id BIGINT,
                         price DECIMAL(10, 2) NOT NULL,
                         description TEXT,
                         image_url VARCHAR(255),
                         stock INT NOT NULL,
                         freeze_stock INT NOT NULL,
                         status TINYINT DEFAULT 1,  -- 1: 上架，0: 下架
                         create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);
-- 订单表（orders）
CREATE TABLE main_order (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            user_id BIGINT NOT NULL,
                            total_amount DECIMAL(10, 2) NOT NULL,
                            status TINYINT NOT NULL, -- 建议用状态码，0: unpaid, 1: paid, 2: shipped, ...
                            payment_method TINYINT NOT NULL, -- 1：微信，2：支付宝，3：银联，4：信用卡
                            logistics_id BIGINT DEFAULT NULL,
                            pay_time DATETIME DEFAULT NULL,
                            create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                            update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- 订单项表（order_item）
CREATE TABLE order_item (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            order_id BIGINT NOT NULL,
                            product_id BIGINT NOT NULL,
                            product_name VARCHAR(255), -- 下单当时的名称（记录快照信息，因为商品配置后续可能会变）
                            product_spec VARCHAR(255), -- 下单当时的规格
                            quantity INT NOT NULL,
                            price DECIMAL(10, 2) NOT NULL -- 下单当时的价格
);
