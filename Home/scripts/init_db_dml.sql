-- 一级分类
INSERT INTO product_category (id, name, parent_id) VALUES
(1, '电子产品', NULL),
(2, '服装鞋帽', NULL),
(3, '家居生活', NULL),
(4, '图书音像', NULL);

-- 二级分类（电子产品）
INSERT INTO product_category (id, name, parent_id) VALUES
(11, '手机', 1),
(12, '笔记本电脑', 1),
(13, '平板电脑', 1),
(14, '耳机音响', 1);

-- 二级分类（服装鞋帽）
INSERT INTO product_category (id, name, parent_id) VALUES
(21, '男装', 2),
(22, '女装', 2),
(23, '运动鞋', 2);

-- 二级分类（家居生活）
INSERT INTO product_category (id, name, parent_id) VALUES
(31, '厨房用品', 3),
(32, '床上用品', 3),
(33, '收纳用品', 3);

-- 二级分类（图书音像）
INSERT INTO product_category (id, name, parent_id) VALUES
(41, '文学小说', 4),
(42, '计算机与互联网', 4),
(43, '儿童读物', 4);

INSERT INTO product (name, category_id, price, description, image_url, stock, freeze_stock, status, create_time) VALUES
('无线鼠标', 2, 79.90, '无线蓝牙鼠标，适合办公和游戏', '/images/product1.jpg', 150, 0, 1, NOW()),
('机械键盘', 2, 299.00, 'RGB背光机械键盘，青轴手感', '/images/product2.jpg', 100, 5, 1, NOW()),
('27寸显示器', 3, 1299.50, '高清27寸IPS显示器，144Hz刷新率', '/images/product3.jpg', 50, 2, 1, NOW()),
('USB-C数据线', 4, 19.90, '高质量USB-C充电数据线，1.5米', '/images/product4.jpg', 500, 0, 1, NOW()),
('智能手环', 5, 249.00, '支持心率监测和睡眠分析', '/images/product5.jpg', 200, 10, 1, NOW()),
('蓝牙音箱', 6, 159.90, '便携无线蓝牙音箱，音质出色', '/images/product6.jpg', 120, 0, 1, NOW()),
('移动电源', 4, 89.00, '10000mAh大容量移动电源，支持快充', '/images/product7.jpg', 300, 0, 1, NOW()),
('游戏鼠标垫', 2, 49.50, '超大加厚鼠标垫，防滑耐磨', '/images/product8.jpg', 400, 20, 1, NOW()),
('4K摄像头', 7, 499.00, '高性能4K网络摄像头，支持自动对焦', '/images/product9.jpg', 80, 0, 1, NOW()),
('智能灯泡', 5, 79.90, '支持APP控制，多色彩变换', '/images/product10.jpg', 250, 15, 1, NOW()),
('笔记本散热器', 3, 129.00, '静音双风扇，USB供电', '/images/product11.jpg', 90, 0, 1, NOW()),
('无线充电器', 4, 139.00, '支持快速无线充电', '/images/product12.jpg', 180, 10, 1, NOW()),
('机械键盘青轴', 2, 329.00, '机械键盘，青轴，RGB背光', '/images/product13.jpg', 75, 0, 1, NOW()),
('智能手表', 5, 1599.00, '健康监测，多功能智能手表', '/images/product14.jpg', 40, 5, 1, NOW()),
('电竞椅', 8, 999.00, '人体工学电竞椅，舒适耐用', '/images/product15.jpg', 30, 0, 1, NOW()),
('耳机', 6, 199.00, '降噪无线耳机，续航长', '/images/product16.jpg', 150, 20, 1, NOW()),
('手机壳', 9, 39.90, '软硅胶手机壳，多色可选', '/images/product17.jpg', 500, 0, 1, NOW()),
('机械键盘红轴', 2, 319.00, '机械键盘，红轴，RGB背光', '/images/product18.jpg', 85, 5, 1, NOW()),
('智能门锁', 5, 699.00, '指纹识别智能门锁', '/images/product19.jpg', 60, 10, 1, NOW()),
('无线耳机', 6, 299.00, '真无线蓝牙耳机，高清音质', '/images/product20.jpg', 130, 0, 1, NOW()),
('显示器支架', 3, 159.00, '可调节显示器支架', '/images/product21.jpg', 120, 15, 1, NOW()),
('便携式投影仪', 7, 1299.00, '高清便携投影仪，支持无线连接', '/images/product22.jpg', 20, 0, 1, NOW()),
('智能音箱', 6, 499.00, '语音助手智能音箱', '/images/product23.jpg', 90, 10, 1, NOW()),
('机械键盘茶轴', 2, 299.00, '机械键盘，茶轴，RGB背光', '/images/product24.jpg', 70, 5, 1, NOW()),
('手机支架', 9, 49.00, '多角度可调手机支架', '/images/product25.jpg', 400, 0, 1, NOW()),
('智能摄像头', 7, 399.00, '支持夜视，远程监控', '/images/product26.jpg', 110, 20, 1, NOW()),
('机械键盘黑轴', 2, 339.00, '机械键盘，黑轴，RGB背光', '/images/product27.jpg', 65, 0, 1, NOW()),
('蓝牙键盘', 2, 159.00, '便携蓝牙键盘，支持多设备', '/images/product28.jpg', 140, 10, 1, NOW()),
('智能手环2代', 5, 299.00, '升级版支持多运动模式', '/images/product29.jpg', 180, 0, 1, NOW()),
('无线鼠标2代', 2, 89.90, '升级版无线蓝牙鼠标', '/images/product30.jpg', 170, 15, 1, NOW()),
('机械键盘静音轴', 2, 329.00, '机械键盘，静音轴，RGB背光', '/images/product31.jpg', 80, 0, 1, NOW()),
('智能门铃', 5, 799.00, '视频智能门铃，远程查看', '/images/product32.jpg', 55, 5, 1, NOW()),
('移动硬盘', 4, 499.00, '高速USB3.0移动硬盘，1TB', '/images/product33.jpg', 95, 0, 1, NOW()),
('电动牙刷', 10, 299.00, '声波电动牙刷，长续航', '/images/product34.jpg', 200, 20, 1, NOW()),
('无线充电宝', 4, 129.00, '支持无线充电的移动电源', '/images/product35.jpg', 210, 0, 1, NOW()),
('机械键盘银轴', 2, 349.00, '机械键盘，银轴，RGB背光', '/images/product36.jpg', 60, 5, 1, NOW()),
('智能灯带', 5, 199.00, '彩色LED智能灯带', '/images/product37.jpg', 150, 0, 1, NOW()),
('笔记本包', 8, 129.00, '防水笔记本电脑包', '/images/product38.jpg', 130, 10, 1, NOW()),
('无线麦克风', 6, 399.00, '专业无线麦克风', '/images/product39.jpg', 70, 0, 1, NOW()),
('机械键盘蓝轴', 2, 319.00, '机械键盘，蓝轴，RGB背光', '/images/product40.jpg', 90, 15, 1, NOW()),
('智能遥控器', 5, 299.00, '红外遥控智能遥控器', '/images/product41.jpg', 100, 0, 1, NOW()),
('游戏手柄', 6, 499.00, '蓝牙无线游戏手柄', '/images/product42.jpg', 85, 5, 1, NOW()),
('智能体脂秤', 5, 299.00, '体脂监测智能秤', '/images/product43.jpg', 130, 0, 1, NOW()),
('USB集线器', 4, 89.00, '多口USB集线器', '/images/product44.jpg', 220, 10, 1, NOW()),
('机械键盘黄金轴', 2, 379.00, '机械键盘，黄金轴，RGB背光', '/images/product45.jpg', 50, 0, 1, NOW()),
('VR眼镜', 7, 1499.00, '高端VR虚拟现实眼镜', '/images/product46.jpg', 25, 5, 1, NOW()),
('游戏耳机', 6, 399.00, '7.1声道环绕立体声', '/images/product47.jpg', 120, 0, 1, NOW()),
('智能空气净化器', 5, 999.00, '家用智能空气净化器', '/images/product48.jpg', 30, 10, 1, NOW()),
('机械键盘红轴2代', 2, 349.00, '升级版机械键盘红轴', '/images/product49.jpg', 75, 0, 1, NOW()),
('无线充电鼠标垫', 4, 159.00, '带无线充电功能的鼠标垫', '/images/product50.jpg', 160, 20, 1, NOW());