# 工程简介
实体类表结构设计:

用户（user）
	普通用户
	管理员
客房（room）
	房型
订单（order）
	客房订单
		房型
		客房
	餐桌订单
		菜品
		餐桌
餐桌（table）
留言板（message）
菜品（food）


用户：登录注册、浏览特色餐饮(有图片+描述)、预约客房(按客房类型【有图片】进行预定，包含预定时间+离店时间+预约天数)、预约餐桌、查看个人订单列表(对订单进行取消，包含餐桌预定和客房预订)、留言板(浏览、留言)
管理员：首页希望有一个客房、餐桌使用情况的图表(饼图即可)
客房订单管理（1.确认用户的预约后安排入住房间；客户完成离店后更改订单信息为已完成、对用户提交的取消申请进行处理）、入住信息管理（为已预约的用户添加入住人信息【姓名+身份证号+入住时间+离店时间】；浏览已入住信息；为线下入住人登记入住信息）0、餐桌订单管理(1.确认客户的预约单 2.添加线下预定的预订单 3.对客户的取消申请进行处理4.客户离店后修改订单状态为已完成)、财务分析(统计客房订单量、餐桌订单量)
 
客房管理(客房信息的增删改查、客房类型信息的增删改查、添加客房信息or类型时可以上传图片)、餐桌管理(餐桌信息的增删改查)、特色菜色管理（菜色信息的增删改查、、添加菜色时可以上传菜色图片、留言管理（增删改查）
用户管理



## 餐饮酒店管理系统，分为管理员端和用户端。springboot框架
接口：
页面房型展示部分:
+ 获取房型typelist √
+ 获取所有的房型  √

+ 特色菜统一查询前五(order by id)√


### 留言展示部分：
+ 留言板查询所有(主要是前端页面的调整)√



### 用户部分：
+ 订单
### 编写弹出框填写入驻信息
+ 用户注册页面

### 统计
+ 统计管理

layer生成iframe
442x600特色菜
370x440房间

+ 订单中心 √
+ 登录、注册 √

+ 统计管理 3.11号完成
住店时间*单日价格=订单总消费金额
按照类型type统计收入:
a.统计每个客房种类的月收入
b.统计每个餐桌种类的月收入

![Image of Yaktocat](https://github.com/innocent12/hotel/blob/master/cadc9b0ee4e49cf84f80efd8e59a5a7.png)
# 延伸阅读

