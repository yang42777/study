CREATE TABLE user (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(64) NOT NULL,
                      password VARCHAR(128) NOT NULL,  -- 密码加密存储
                      role VARCHAR(20)
);